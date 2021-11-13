import java.io.File;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.SecretKey;

public class Encript {
    public Encript(String path) throws Exception{
        AES aes = new AES();
        RSA rsa = new RSA();
        Probarbase64 base = new Probarbase64("");
        Datos bbdd = new Datos();

        File f = new File(path);
        String nombre_archivo = f.getName();
        SecretKey clave = aes.getAESKey();
        String clave_string = base.base64SecretKey(clave);
        
        // rsa.encryptAESKey(clave);
        // KeyPair pairRSA = rsa.getRSAKeys();
        // SecretKey keyAESEncrypted = rsa.getAESKey();

        // PublicKey publicKey = pairRSA.getPublic();
        // PrivateKey privateKey = pairRSA.getPrivate();
    
        // //convertimos las claves a base64
        // String publicRSAKeyString = base.base64PublicKey(publicKey);
        // String privateRSAKeyString = base.base64PrivateKey(privateKey);

        // //coge el nombre del fichero
        // nombre_archivo = nombre_archivo.substring(0, nombre_archivo.lastIndexOf('.'));

        // //se guarda en fichero la clave privada de RSA
        // base.stringToFile(privateRSAKeyString, "encript/"+nombre_archivo+".key");

        
        // //mete la clave en la bbdd en la tabla de archivos
        // bbdd.insertarClave(nombre_archivo, clave_string);
        
        
        // mete en la carpeta encript el archivo con su_nombre.enc
        base.bFichero(aes.encryptFile(path, clave), "encript/"+nombre_archivo+".enc");


    }
/*
    public static void main(String[] args){
        try {
            Encript cript = new Encript("archivos/foto.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }*/
}
