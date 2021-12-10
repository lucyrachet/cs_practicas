import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKey;

public class archivos {
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
                File fKeyRSA = new File(keyRSA);                                //cojo el archivo
                String keyRSAString = base.fileToString(keyRSA);                //cojo el archivo y lo paso a String

                //DESENCRIPTAR CON AES DE ADMIN
                String aesAdmin = ""; //METER AQUI EL AES DEL ADMIN
                SecretKey claveAESAdmin = base.asciiSecretKey(aesAdmin);

                PrivateKey privKey = base.asciiToPrivateKey(aes.decryptString(keyRSAString, claveAESAdmin));      //paso el string a PrivateKey
                
                nombre_archivo = nombre_archivo.substring(0, nombre_archivo.lastIndexOf('.'));  // Quita el .enc

                String archivo_decript = datos.recogerNombre(nombre_archivo);                   //coges el nombre del archivo de la bbdd
                String clave = datos.recogerClave(nombre_archivo);                              //cogemos la clave AES de ese archivo

                SecretKey claveAES = rsa.decryptKey(clave.getBytes(), privKey);                 //desencriptamos la clave AES con la privada de RSA
                PublicKey clavePublica = base.asciiToPublicKey(clavePublicaUsr);

                byte[] bytes = rsa.encryptKey(claveAES, clavePublica);

            } catch (Exception e) {
                System.err.println(e);
            }  
        }
    }
}
