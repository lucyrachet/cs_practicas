import java.io.File;
import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;



public class ProyectoCS {
    public static void main(String[] args) throws Exception {
        Interfaz_abstract interfaz = new Interfaz();

        interfaz.setVisible(true);
        
        //para la comprobacion del login
        Datos bbdd = new Datos();

        Boolean existeUsuario = false;
        Boolean usuarioValidado=false;
        String usuario_dado = null;
        String contrasena_dada = null;
        String contrasena_dada1 = null;
        String contrasena_dada2 = null;

        //while estado sea login o registro
        while(usuarioValidado==false && (interfaz.dameEstado()==EstadoInterfaz.Login || interfaz.dameEstado()==EstadoInterfaz.Registro)){
            //Login
            if(interfaz.dameEstado()==EstadoInterfaz.Login){

            }
            //Registro
            if(interfaz.dameEstado()==EstadoInterfaz.Registro){

            }
            if(interfaz.pathLogin.isBlank()==false|| interfaz.pathRegistro.isBlank()==false){
                if(interfaz.pathLogin.isBlank()==false){
                    //Login
                    System.out.println(interfaz.pathLogin);
                    /************* */
                    usuario_dado=interfaz.dameNombreUsuario();
                    contrasena_dada = interfaz.dameContrasenaLogin();

                    //Comprobacion de login bien hecho
                    existeUsuario = bbdd.existeUsuario(usuario_dado);
            if(interfaz.estado==EstadoInterfaz.Login){
                usuario_dado=interfaz.dameNombreLogin();
                contrasena_dada = interfaz.dameContrasenaLogin();

                //Comprobacion de login bien hecho
                existeUsuario = bbdd.existeUsuario(usuario_dado);

                if(existeUsuario==true){
                    String contrasena_bbdd=bbdd.recogerPassword(usuario_dado);

                    if(contrasena_bbdd == contrasena_dada){
                        usuarioValidado=true;
                    }else{
                        //?????? no se que puede hacer para comunicarlo
                        System.out.println("Las contrasenas no coinciden");
                    }
                }
            }
            //Registro
            if(interfaz.estado==EstadoInterfaz.Registro){
                AES aes = new AES();
                    RSA rsa = new RSA();
                    Probarbase64 base = new Probarbase64("");

                    usuario_dado=interfaz.dameNombreRegistro();
                    contrasena_dada1 = interfaz.dameContrasena1Registro();
                    contrasena_dada2 = interfaz.dameContrasena2Registro();

                    Boolean existe=bbdd.existeUsuario(usuario_dado);    
                    if(existe==false){                                      //si el usuario no existe lo creamos
                        if(contrasena_dada1==contrasena_dada2){             //si las contrasenas coinciden
                            KeyPair pairRSA = rsa.crearParClaves();
                            PublicKey publicKeyRSA = pairRSA.getPublic();

                            bbdd.insertarUsuario(usuario_dado, base.base64PublicKey(publicKeyRSA), contrasena_dada1);   //insertamos el usuario
                        }
                    }else{
                        System.out.println("El usuario ya existe elija otro nombre");
                    }

            }
            System.out.print("");
            // if(interfaz.pathLogin.isBlank()==false|| interfaz.pathRegistro.isBlank()==false){
            //     if(interfaz.pathLogin.isBlank()==false){
            //         //Login
            //         System.out.println(interfaz.pathLogin);
            //         /************* */
            //         usuario_dado=interfaz.dameNombreLogin();
            //         contrasena_dada = interfaz.dameContrasenaLogin();

            //         //Comprobacion de login bien hecho
            //         existeUsuario = bbdd.existeUsuario(usuario_dado);

            //         if(existeUsuario==true){
            //             String contrasena_bbdd=bbdd.recogerPassword(usuario_dado);

            //             if(contrasena_bbdd == contrasena_dada){
            //                 usuarioValidado=true;
            //             }else{
            //                 //?????? no se que puede hacer para comunicarlo
            //                 System.out.println("Las contrasenas no coinciden");
            //             }
            //         }

            //         interfaz.pathLogin = "";
            //     } else{
            //         //Registro
            //         //Decript desencriptar = new Decript(interfaz.pathRegistro);
            //         /***************/

            //         AES aes = new AES();
            //         RSA rsa = new RSA();
            //         Probarbase64 base = new Probarbase64("");

            //         usuario_dado=interfaz.dameNombreUsuario();
            //         contrasena_dada1 = interfaz.dameContrasena1Registro();
            //         contrasena_dada2 = interfaz.dameContrasena2Registro();

            //         Boolean existe=bbdd.existeUsuario(usuario_dado);    
            //         if(existe==false){                                      //si el usuario no existe lo creamos
            //             if(contrasena_dada1==contrasena_dada2){             //si las contrasenas coinciden
            //                 KeyPair pairRSA = rsa.getRSAKeys();
            //                 PublicKey publicKeyRSA = pairRSA.getPublic();

            //                 bbdd.insertarUsuario(usuario_dado, base.base64PublicKey(publicKeyRSA), contrasena_dada1);   //insertamos el usuario
            //             }
            //         }else{
            //             System.out.println("El usuario ya existe elija otro nombre");
            //         }

            //         /***************/
            //         interfaz.pathRegistro = "";

            //     }
            //}
        }


        /********* */
        // //Comprobacion de login bien hecho
        // existeUsuario = bbdd.existeUsuario(usuario_dado);

        // if(existeUsuario==true){
        //     String contrasena_bbdd=bbdd.recogerPassword(usuario_dado);

        //     if(contrasena_bbdd == contrasena_dada){
        //         usuarioValidado=true;
        //     }
        // }

        //while estado sea encriptar o desencriptar
        /***********************
         * Interfaz anyadir estado
         * 
         */
        
        while ((usuarioValidado==true) /*&& (interfaz.estado=="encriptar" || interfaz.estado=="desencriptar")*/) {
            System.out.print("");
            String path = interfaz.damePathFichero();
            if(path.isBlank()==false){
                if(interfaz.estado==EstadoInterfaz.Encriptar || interfaz.estado==EstadoInterfaz.Desencriptar){
                    //Encriptar
                    System.out.println(path);
                    /************* */
                        AES aes = new AES();
                        RSA rsa = new RSA();
                        Probarbase64 base = new Probarbase64("");
                        

                        File f = new File(path);
                        String nombre_archivo = f.getName();
                        SecretKey clave = aes.getAESKey();
                        String clave_string = base.base64SecretKey(clave);
                        
                        KeyPair pairRSA = rsa.crearParClaves();
                        SecretKey keyAESEncrypted = aes.getAESKey();

                        PublicKey publicKey = pairRSA.getPublic();
                        PrivateKey privateKey = pairRSA.getPrivate();

                        byte[] claveEncriptada = rsa.encryptKey(clave,publicKey);
                        String claveEncriptadaString = new String(claveEncriptada);  
                    
                        //convertimos las claves a base64
                        String publicRSAKeyString = base.base64PublicKey(publicKey);
                        String privateRSAKeyString = base.base64PrivateKey(privateKey);

                        //coge el nombre del fichero
                        nombre_archivo = nombre_archivo.substring(0, nombre_archivo.lastIndexOf('.'));

                        //se guarda en fichero la clave privada de RSA
                        base.stringToFile(privateRSAKeyString, "encript/"+nombre_archivo+".key");

                        
                        //mete la clave en la bbdd en la tabla de archivos
                        bbdd.insertarClave(nombre_archivo, claveEncriptadaString, usuario_dado);
                        
                        
                        // mete en la carpeta encript el archivo con su_nombre.enc
                        base.bFichero(aes.encryptFile(path, clave), "encript/"+nombre_archivo+".enc");


                    /************* */
                    //Encript encriptar = new Encript(interfaz.pathEncriptar);
                    //interfaz.pathEncriptar = "";
                } else{
                    //Desencriptar
                    //Decript desencriptar = new Decript(interfaz.pathDesencriptar);
                    /*************/
                    //String path = interfaz.pathDesencriptar;
                    AES aes = new AES();
                    RSA rsa = new RSA();
                    Probarbase64 base = new Probarbase64("");
                    
                    
                    File f = new File(path);                    //cogemos el file de desencriptar
                    String nombre_archivo = f.getName();        //cogemos el nombre que tenga
                    nombre_archivo = nombre_archivo.substring(0, nombre_archivo.lastIndexOf('.'));  //quitamos lo q hay despues del ."enc"
                    
                    String keyRSA = nombre_archivo+".key";                      //pongo como es el nombre de la key
                    //File fKeyRSA = new File(keyRSA);                          //cojo el archivo
                    String keyRSAString = base.fileToString(keyRSA);            //cojo el archivo y lo paso a String
                    PrivateKey privKey = base.asciiToPrivateKey(keyRSAString);  //paso el string a PrivateKey

                    String archivo_decript = bbdd.recogerNombre(nombre_archivo);    //coges el nombre del archivo de la bbdd
                    String clave = bbdd.recogerClave(nombre_archivo);               //cogemos la clave AES de ese archivo
                    byte[] clave_bytes = clave.getBytes();
                    SecretKey claveAES = rsa.decryptKey(clave_bytes, privKey);                          //desencriptamos la clave AES con la privada de RSA
                    
                    base.bFichero(aes.decryptFile(path,claveAES), "decript/"+archivo_decript);   //pasamos de base64 la clave AES a Secret Key
                                                                                                                    //desencriptamos y 
                                                                                                                    // convertimos a fichero
                    f.delete(); //borramos el archivo de la carpeta

                    /*************/
                    // interfaz.pathDesencriptar = "";

                }
            }
        }
      
    }
}

