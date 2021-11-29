//import javax.crypto.SecretKey;
import java.io.Console;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;


public class ProyectoCS {
    public static void main(String[] args) throws Exception {
        
        EstadoCS estadoCS = EstadoCS.SinEstado;
        Interfaz interfaz = new Interfaz(estadoCS);

        //interfaz.setVisible(true);
        
        //para la comprobacion del login
        Datos bbdd = new Datos();
        
        Boolean esAdmin = false;    //controlar que sea el admin

        Boolean existeUsuario = false;
        Boolean usuarioValidado=false;
        String usuario_dado = null;
        String contrasena_dada = null;
        String contrasena_dada1 = null;
        String contrasena_dada2 = null;
/*
        RSA rsa = new RSA();
        Probarbase64 base = new Probarbase64("");
        
        KeyPair pairRSA = rsa.crearParClaves();
        PublicKey publicKeyRSA = pairRSA.getPublic();
        bbdd.insertarUsuario("admin", base.base64PublicKey(publicKeyRSA), "admin");
*/

        //----------PRIMERA FASE-----------//
        System.out.println("----------PRIMERA FASE-----------");
        //SIN ESTADO HASTA IDENTIFICACIÓN     
        while(usuarioValidado==false){
            System.out.print("");

            switch(estadoCS){

                //ESTADO LOGIN
                case Login:
                    usuario_dado=interfaz.dameNombreLogin();
                    contrasena_dada = interfaz.dameContrasenaLogin();
                    
                    //Comprobacion de login bien hecho
                    existeUsuario = bbdd.existeUsuario(usuario_dado);
                    if(existeUsuario==true){
                        String contrasena_bbdd=bbdd.recogerPassword(usuario_dado);
                        if(contrasena_bbdd.equals(contrasena_dada)){
                            
                            //SALIR DEL BUCLE
                            usuarioValidado=true;

                            interfaz.ExitoLogin();
                            if(usuario_dado.equals("admin")){
                                esAdmin = true;
                            }
                        }else{
                            interfaz.ErrorLogin();
                        }
                    }else{
                        interfaz.ErrorLogin();
                    }
                    break;

                //ESTADO REGISTRO
                case Registro:
                    //AES aes = new AES();
                    RSA rsa = new RSA();
                    Probarbase64 base = new Probarbase64("");

                    usuario_dado=interfaz.dameNombreRegistro();
                    contrasena_dada1 = interfaz.dameContrasena1Registro();
                    contrasena_dada2 = interfaz.dameContrasena2Registro();

                    Boolean existe=bbdd.existeUsuario(usuario_dado); 
                    //Boolean existe=true;   
                    if(existe==false){                                      //si el usuario no existe lo creamos
                        if(contrasena_dada1==contrasena_dada2){             //si las contrasenas coinciden
                            KeyPair pairRSA = rsa.crearParClaves();         //generamos par de claves
                            PublicKey publicKeyRSA = pairRSA.getPublic();   //cogemos la publica y la metemos en base de datos
                            PrivateKey privateKeyRSA = pairRSA.getPrivate();   //cogemos la privada 
                                                                                
                            base.bFichero(base.base64PrivateKey(privateKeyRSA).getBytes(), "datos/"+usuario_dado+".pvk");   //guardamos rsa privada en un archivo
                            bbdd.insertarUsuario(usuario_dado, base.base64PublicKey(publicKeyRSA), contrasena_dada1);   //insertamos el usuario
                            interfaz.ExitoRegistro();
                        }else{
                            interfaz.ErrorRegistro("Las contrasenas no coinciden");
                        }
                    }else{
                        interfaz.ErrorRegistro("El usuario ya existe.");
                    }
                    break;

                
                //Aqui llegan los estados de la fase 2 y SinEstado
                default:
                    if(estadoCS!=EstadoCS.SinEstado){
                        System.err.println("No pueden haber estados de la FASE 2 en la FASE 1");
                    }
                    break;
            }
        }

        //----------SEGUNDA FASE-----------//
        System.out.println("----------SEGUNDA FASE-----------");
        //SIN ESTADO hasta acción de la interfaz

        //SecretKey supuestaclaveAES = null;
        while (usuarioValidado==true ) {    
            System.out.print("");

            switch (estadoCS){

                //ESTADO OBTENER FICHEROS
                    //Obtener lista de ficheros con el usuario y permiso [LLamado desde: Login,Registro,Desencriptar,Encriptar]
                case ObtenerFicheros:
                    //TODO: Obtener ficheros con BBDD
                    break;

                //ESTADO SOLICITAR FICHERO
                    //Solicitar un fichero para desencriptar [Llamado desde: interfaz]
                case SolicitarFichero:
                    //TODO: Solicitar fichero por cliente servidor
                    TipoSolicitud solicitud = TipoSolicitud.TuMismo; //= solicitud();
                    switch(solicitud){
                        case OtroUsuario:
                            //TODO: Enviar solicitud al usuario, permisos y te tiene que responder

                            //Devuelve el estado EsperarRespuesta
                            estadoCS=EstadoCS.EsperarRespuesta;
                            break;
                        case Servidor:
                            //TODO: Enviar solicitud al servidor y este revisa tus permisos

                            //Devuelve el estado EsperarRespuesta
                            estadoCS=EstadoCS.EsperarRespuesta;
                            break;
                        case TuMismo:
                            //Devuelve el estado Desencriptar
                            estadoCS=EstadoCS.Desencriptar;
                            break;               
                    }
                    break;
                
                //ESTADO ESPERAR RESPUESTA
                    //Comprobar que haya una respuesta [LLamado desde: SolicitarFichero]
                case EsperarRespuesta:
                    //TODO: Comprobar que haya una respuesta en la carpeta (se puede poner cooldown para que no consuma mucho)

                    //Devuelve el estado LeerRespuesta si hay respuesta
                    estadoCS = EstadoCS.LeerRespuesta;
                    break;
                
                //ESTADO LEER RESPUESTA
                    //Procesamos los datos de la respuesta [LLamado desde: EsperarRespuesta]
                case LeerRespuesta:
                    //TODO: Leer el archivo de respuesta y procesar los datos
                    //Devuelve el estado Desencriptar cuando termina el proceso
                    estadoCS = EstadoCS.Desencriptar;
                    break;
                
                //ESTADO DESENCRIPTAR
                    //Desencriptamos el archivo [Llamado desde: LeerRespuesta,SolicitarFichero]
                case Desencriptar:
                    break;
                
                //ESTADO COMPARTIR FICHEROS
                    //Compartir los ficheros haciendo un hilo de ejecución [Llamado desde: interfaz]
                case CompartirFicheros:
                    break;
                
                //ESTADO DEJAR DE COMPARTIR
                    //Dejar de compartir los ficheros [Llamado desde: interfaz]
                case DejarDeCompartir:
                    break;

                //ESTADO ENCRIPTAR
                    //Encriptar un fichero [Llamado desde: interfaz]
                case Encriptar:
                    break;
                
                //Aqui entraran los estados de la fase 1 y SinEstado
                default:
                    if(estadoCS!=EstadoCS.SinEstado){
                        System.err.println("No pueden haber estados de la FASE 1 en la FASE 2");
                    }
                    break;
            }

           
            if(estadoCS==EstadoCS.LeerRespuesta){
                //TODO: Solicitar fichero por cliente servidor
            }

            
           
                if(esAdmin==true){

                    if(estadoCS==EstadoCS.Encriptar){
                        //Encriptar
                        System.out.println("");
                        /************* */
                            // AES aes = new AES();
                            // String path = interfaz.damePathFichero();
    
                            // File f = new File(path);
                            // String nombre_archivo = f.getName();
                            // SecretKey clave = aes.getAESKey();
    
                            // supuestaclaveAES = clave;
    
                            // String clave_string = base.base64SecretKey(clave);
    
                            // PublicKey publicKey = pairRSA.getPublic();
                            // PrivateKey privateKey = pairRSA.getPrivate();
    
                            // byte[] claveEncriptada = rsa.encryptKey(clave,publicKey);
                            // String claveEncriptadaString = base.bytebase64(claveEncriptada);
    
                            // //convertimos las claves a base64
                            // String publicRSAKeyString = base.base64PublicKey(publicKey);
                            // String privateRSAKeyString = base.base64PrivateKey(privateKey);
    
                            // //mete la clave en la bbdd en la tabla de archivos
                            // bbdd.insertarClave(nombre_archivo, claveEncriptadaString, usuario_dado);
    
                            // //coge el nombre del fichero
                            // nombre_archivo = nombre_archivo.substring(0, nombre_archivo.lastIndexOf('.'));
    
                            // //se guarda en fichero la clave privada de RSA
                            // base.stringToFile(privateRSAKeyString, "encript/"+nombre_archivo+".key");
                            
                            // // mete en la carpeta encript el archivo con su_nombre.enc
                            // base.bFichero(aes.encryptFile(path, clave), "encript/"+nombre_archivo+".enc");
    
                            // interfaz.ExitoEncriptar();
    
                        /************* */
                        //Encript encriptar = new Encript(interfaz.pathEncriptar);
                        //interfaz.pathEncriptar = "";
                    }
                }

                if(estadoCS==EstadoCS.Desencriptar){
                    //Desencriptar
                    //Decript desencriptar = new Decript(interfaz.pathDesencriptar);
                    /*************/
                    //String path = interfaz.pathDesencriptar;
                    // AES aes = new AES();
                    // String path = interfaz.damePathFichero();
                    // File f = new File(path);                    //cogemos el file de desencriptar
                    // String nombre_archivo = f.getName();        //cogemos el nombre que tenga
                    // nombre_archivo = nombre_archivo.substring(0, nombre_archivo.lastIndexOf('.'));  //quitamos lo q hay despues del ."enc"
                
                    // String subpath = path.substring(0,path.lastIndexOf('\\'))+"\\";
                    // String keyRSA = subpath+nombre_archivo+".key";                      //pongo como es el nombre de la key
                    // //File fKeyRSA = new File(keyRSA);                          //cojo el archivo
                    // String keyRSAString = base.fileToString(keyRSA);            //cojo el archivo y lo paso a String
                                       
                    // PrivateKey privKey = base.asciiToPrivateKey(keyRSAString);  //paso el string a PrivateKey

                    // String archivo_decript = bbdd.recogerNombre(nombre_archivo);    //coges el nombre del archivo de la bbdd
                    // String clave = bbdd.recogerClave(nombre_archivo);               //cogemos la clave AES de ese archivo

                    // SecretKey claveTest = base.asciiSecretKey(clave);

                    // byte[] clave_bytes = claveTest.getEncoded();
                    // SecretKey claveAES = rsa.decryptKey(clave_bytes, privKey);                          //desencriptamos la clave AES con la privada de RSA

                    // base.bFichero(aes.decryptFile(path,claveAES), "decript/"+archivo_decript);   //pasamos de base64 la clave AES a Secret Key
                    //                                                                                                 //desencriptamos y 
                    //                                                                                                 // convertimos a fichero
                    // interfaz.ExitoDesencriptar();
                    

                }
            
        }
      
    }
}

