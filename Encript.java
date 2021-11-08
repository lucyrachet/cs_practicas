import java.io.File;

import javax.crypto.SecretKey;

public class Encript {
    public Encript(String path) throws Exception{
        AES aes = new AES();
        Probarbase64 base = new Probarbase64("");
        Datos bbdd = new Datos();

        File f = new File(path);
        String nombre_archivo = f.getName();
        SecretKey clave = aes.getAESKey();
        String clave_string = base.base64SecretKey(clave);

        bbdd.insertarClave(nombre_archivo, clave_string);

        nombre_archivo = nombre_archivo.substring(0, nombre_archivo.lastIndexOf('.'));
        

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
