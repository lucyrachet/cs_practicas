import java.io.File;
import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;



public class ProyectoCS {
    private static Interfaz interfaz;
    
    
    
    public static void main(String[] args) throws Exception {
       interfaz = new Interfaz();
        
        interfaz = new Interfaz();
        interfaz.setVisible(true);
        
        //para la comprobacion del login
        Datos bbdd = new Datos();

        Boolean existeUsuario = false;
        Boolean usuarioValidado=false;
        String usuario_dado = null;
        String contrasena_dada = null;

        //while estado sea login o registro
        while(interfaz.estado=="login" || interfaz.estado=="registro"){
            System.out.print("");
            if(interfaz.pathLogin.isBlank()==false|| interfaz.pathRegistro.isBlank()==false){
                if(interfaz.pathLogin.isBlank()==false){
                    //Encriptar
                    System.out.println(interfaz.pathLogin);
                    /************* */
                        
                    interfaz.pathLogin = "";
                } else{
                    //Desencriptar
                    Decript desencriptar = new Decript(interfaz.pathRegistro);
                    interfaz.pathRegistro = "";

                }
            }
        }


        /********* */
        //Comprobacion de login bien hecho
        existeUsuario = bbdd.userExists(usuario_dado);

        if(existeUsuario==true){
            String contrasena_bbdd=bbdd.getContrasena(usuario_dado);

            if(contrasena_bbdd == contrasena_dada){
                usuarioValidado=true;
            }
        }

        //while estado sea encriptar o desencriptar
        /***********************
         * Interfaz anyadir estado
         * 
         */
        
        while ((usuarioValidado==true) && (interfaz.estado=="encriptar" || interfaz.estado=="desencriptar")) {
            System.out.print("");
            if(interfaz.pathDesencriptar.isBlank()==false|| interfaz.pathEncriptar.isBlank()==false){
                if(interfaz.pathEncriptar.isBlank()==false){
                    //Encriptar
                    System.out.println(interfaz.pathEncriptar);
                    /************* */
                        AES aes = new AES();
                        RSA rsa = new RSA();
                        Probarbase64 base = new Probarbase64("");
                        

                        File f = new File(interfaz.pathEncriptar);
                        String nombre_archivo = f.getName();
                        SecretKey clave = aes.getAESKey();
                        String clave_string = base.base64SecretKey(clave);
                        
                        rsa.encryptAESKey(clave);
                        KeyPair pairRSA = rsa.getRSAKeys();
                        SecretKey keyAESEncrypted = rsa.getAESKey();

                        PublicKey publicKey = pairRSA.getPublic();
                        PrivateKey privateKey = pairRSA.getPrivate();
                    
                        //convertimos las claves a base64
                        String publicRSAKeyString = base.base64PublicKey(publicKey);
                        String privateRSAKeyString = base.base64PrivateKey(privateKey);

                        //coge el nombre del fichero
                        nombre_archivo = nombre_archivo.substring(0, nombre_archivo.lastIndexOf('.'));

                        //se guarda en fichero la clave privada de RSA
                        base.stringToFile(privateRSAKeyString, "encript/"+nombre_archivo+".key");

                        
                        //mete la clave en la bbdd en la tabla de archivos
                        bbdd.insertarClave(nombre_archivo, clave_string);
                        
                        
                        // mete en la carpeta encript el archivo con su_nombre.enc
                        base.bFichero(aes.encryptFile(interfaz.pathEncriptar, clave), "encript/"+nombre_archivo+".enc");


                    /************* */
                    //Encript encriptar = new Encript(interfaz.pathEncriptar);
                    interfaz.pathEncriptar = "";
                } else{
                    //Desencriptar
                    Decript desencriptar = new Decript(interfaz.pathDesencriptar);
                    interfaz.pathDesencriptar = "";

                }
            }
        }
      
    }
}

