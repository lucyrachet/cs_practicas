import java.io.File;

public class Decript {
    public Decript(String path) throws Exception{
        AES aes = new AES();
        Probarbase64 base = new Probarbase64("");
        Datos bbdd = new Datos();

        File f = new File(path);
        String nombre_archivo = f.getName();
        nombre_archivo = nombre_archivo.substring(0, nombre_archivo.lastIndexOf('.'));

        String archivo_decript = bbdd.recogerNombre(nombre_archivo);
        String clave = bbdd.recogerClave(nombre_archivo);

        base.bFichero(aes.decryptFile(path, base.asciiSecretKey(clave)), "decript/"+archivo_decript);

        f.delete();
    }

    /*
    public static void main(String[] args){
        try {
            Decript cript = new Decript("encript/foto.enc");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
}
