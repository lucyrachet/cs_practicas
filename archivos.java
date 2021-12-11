import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKey;

public class archivos {
    
    //Guarda el archivo encriptado en la carpeta del usuario junto a una clave AES encriptada con su clave pública 
    public void accederArchivo(String nombre_archivo,String nombre_user){
        Datos datos = new Datos();
        Probarbase64 base = new Probarbase64("");
        AES aes = new AES();
        //nombre_archivo = nombre_archivo.substring(0, nombre_archivo.lastIndexOf('.'));
        System.out.println("Datos pasados: nombre = "+nombre_user+" | archivo = "+nombre_archivo);

        if(datos.existeUsuario(nombre_user) && !datos.recogerClave(nombre_archivo,"admin").equals("")){
            System.out.println("toi dentro chicos");
            String clavePublicaUsr = datos.recogerClavePublica(nombre_user);
            String claveArchivo = datos.recogerClave(nombre_archivo,"admin");
            RSA rsa = new RSA();
            try {
                String keyRSA = "datos/admin/admin.pvk";                    //pongo como es el nombre de la key
                //File fKeyRSA = new File(keyRSA);                                                //cojo el archivo
                String keyRSAString = base.fileToString(keyRSA);                                //cojo el archivo y lo paso a String

                //COGER CLAVE AES DE ADMIN
                String aesAdmin = "datos/admin/aesadmin.key";
                aesAdmin= base.fileToString(aesAdmin);
                SecretKey claveAESAdmin = base.asciiSecretKey(aesAdmin);

                //COGER CLAVE PRIVADA DE ADMIN
                String clavePrivString = AES.decryptString(keyRSAString, claveAESAdmin); //Desencriptar la clave privada con AES de admin
                PrivateKey privKey = base.asciiToPrivateKey(clavePrivString);            //Pasar de string a clave privada
                
                //COGER CLAVE AES DEL ARCHIVO
                String archivo_decript = datos.recogerNombre(nombre_archivo);           //coges el nombre del archivo de la bbdd
                String clave = datos.recogerClave(nombre_archivo,"admin");                      //cogemos la clave AES de ese archivo

                SecretKey claveAESencripted = base.asciiSecretKey(clave);
                SecretKey claveAES = rsa.decryptKey(claveAESencripted.getEncoded(), privKey);     //desencriptamos la clave AES con la privada de RSA
            
                base.bFichero(aes.decryptFile("datos/admin/encript/"+nombre_archivo+".enc",claveAES), "datos/admin/decript/"+archivo_decript);


                //COGER CLAVE PUBLICA DEL USUARIO
                PublicKey clavePublica = base.asciiToPublicKey(clavePublicaUsr);

                //ENCRIPTAR CLAVE AES DEL ARCHIVO CON CLAVE PUBLICA DEL USUARIO
                //byte[] bytesClave = rsa.encryptKey(claveAES, clavePublica);

                byte[] claveEncriptada = rsa.encryptKey(claveAES,clavePublica);           //encriptamos la clave publica
                String claveEncriptadaString = base.bytebase64(claveEncriptada);    //en string

                
                //String claveString = new String(bytesClave);
                //String claveBase64 = base.base64(claveString);
                //bytesClave= claveBase64.getBytes();
                //ALMACENAR LOS DATOS
                System.out.println("datos/"+nombre_user+"/respuestas/"+nombre_archivo+".key");
                System.out.println("datos/"+nombre_user+"/encript/"+nombre_archivo+".enc");

                String pathEncriptedFile = "datos/admin/encript/"+nombre_archivo+".enc";
                System.out.println("PATH: "+pathEncriptedFile);
                //String contenido = base.fileToString(pathEncriptedFile);
                //System.out.println("CONTENIDO: "+contenido);
                byte[] bytesArchivo = Files.readAllBytes(Paths.get(pathEncriptedFile));

                base.bFichero(claveEncriptadaString.getBytes(), "datos/"+nombre_user+"/respuestas/"+nombre_archivo+".key");
                base.bFichero(bytesArchivo, "datos/"+nombre_user+"/respuestas/"+nombre_archivo+".enc");

            } catch (Exception e) {
                System.err.println(e);
            }  
        }
    }

    /*
    //Desencripta el archivo que se genere en su carpeta de respuestas
    public void desencriptarArchivo(String nombre_archivo,String nombre_user,PrivateKey privKeyUser) throws Exception{
        Datos datos = new Datos();
        Probarbase64 base = new Probarbase64("");
        AES aes = new AES();
        RSA rsa = new RSA();

        accederArchivo(nombre_archivo, nombre_user);

        nombre_archivo = nombre_archivo.substring(0, nombre_archivo.lastIndexOf('.'));

        String archivo_decript = datos.recogerNombre(nombre_archivo);           //coges el nombre del archivo de la bbdd
        String clave = datos.recogerClave(nombre_archivo);                      //cogemos la clave AES de ese archivo

        SecretKey claveAESencripted = base.asciiSecretKey(clave);
        SecretKey claveAES = rsa.decryptKey(claveAESencripted.getEncoded(), privKeyUser);     //desencriptamos la clave AES con la privada de RSA

        base.bFichero(aes.decryptFile("datos/"+nombre_user+"/respuestas/"+nombre_archivo+".enc",claveAES), "datos/"+nombre_user+"/decript/"+archivo_decript);
    
        File deleteKey = new File("datos/"+nombre_user+"/respuestas/"+nombre_archivo+".key");
        File deleteArchivo = new File("datos/"+nombre_user+"/respuestas/"+nombre_archivo+".enc");
        
        deleteKey.delete();
        deleteArchivo.delete();
        
    }*/
}
