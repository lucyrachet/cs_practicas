import java.io.File;
import java.io.IOException;
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

        if(datos.existeUsuario(nombre_user) && !datos.recogerClave(nombre_archivo).equals("")){
            String clavePublicaUsr = datos.recogerClavePublica(nombre_user);
            String claveArchivo = datos.recogerClave(nombre_archivo);
            RSA rsa = new RSA();
            try {
                String keyRSA = "datos/"+nombre_user+"/"+nombre_user+".pvk";                    //pongo como es el nombre de la key
                File fKeyRSA = new File(keyRSA);                                                //cojo el archivo
                String keyRSAString = base.fileToString(keyRSA);                                //cojo el archivo y lo paso a String

                String aesAdmin = "datos/admin/aesadmin.key";
                aesAdmin= base.fileToString(aesAdmin);
                SecretKey claveAESAdmin = base.asciiSecretKey(aesAdmin);

                PrivateKey privKey = base.asciiToPrivateKey(AES.decryptString(keyRSAString, claveAESAdmin));      //paso el string a PrivateKey
                
                nombre_archivo = nombre_archivo.substring(0, nombre_archivo.lastIndexOf('.'));  // Quita el .enc

                String archivo_decript = datos.recogerNombre(nombre_archivo);                   //coges el nombre del archivo de la bbdd
                String clave = datos.recogerClave(nombre_archivo);                              //cogemos la clave AES de ese archivo

                SecretKey claveAES = rsa.decryptKey(clave.getBytes(), privKey);                 //desencriptamos la clave AES con la privada de RSA
                PublicKey clavePublica = base.asciiToPublicKey(clavePublicaUsr);

                byte[] bytes = rsa.encryptKey(claveAES, clavePublica);
                base.bFichero(bytes, "datos/"+nombre_user+"/respuestas/"+nombre_archivo+".key");
                base.bFichero(AES.encryptFile("datos/admin/archivos/"+archivo_decript, claveAES), "datos/"+nombre_user+"/respuestas/"+nombre_archivo+".enc");

            } catch (Exception e) {
                System.err.println(e);
            }  
        }
    }

    //Desencripta el archivo que se genere en su carpeta de respuestas
    public void desencriptarArchivo(String nombre_archivo,String nombre_user,PrivateKey privKeyUser) throws Exception{
        Datos datos = new Datos();
        Probarbase64 base = new Probarbase64("");
        AES aes = new AES();
        RSA rsa = new RSA();

        accederArchivo(nombre_archivo, nombre_user);

        String archivo_decript = datos.recogerNombre(nombre_archivo);           //coges el nombre del archivo de la bbdd
        String clave = datos.recogerClave(nombre_archivo);                      //cogemos la clave AES de ese archivo

        SecretKey claveAES = rsa.decryptKey(clave.getBytes(), privKeyUser);     //desencriptamos la clave AES con la privada de RSA

        base.bFichero(aes.decryptFile("datos/"+nombre_user+"/respuestas/"+nombre_archivo+".enc",claveAES), "datos/"+nombre_user+"/decript/"+archivo_decript);
    
        File deleteKey = new File("datos/"+nombre_user+"/respuestas/"+nombre_archivo+".key");
        File deleteArchivo = new File("datos/"+nombre_user+"/respuestas/"+nombre_archivo+".enc");

        deleteKey.delete();
        deleteArchivo.delete();
    }
}
