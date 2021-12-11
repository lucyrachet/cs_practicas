//import javax.crypto.SecretKey;
import java.io.Console;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.SecretKey;


public class ProyectoCS {
    public static EstadoCS estadoCS = EstadoCS.SinEstado;
    
    public static void main(String[] args) throws Exception {
        Interfaz interfaz = new Interfaz();

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
        String nombre_archivo_dado = null;
        int tipo_permiso_dado = 0;

        CompartirCS compartirCS = null;
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
                    System.out.println("Intentando iniciar sesión");
                    usuario_dado=interfaz.dameNombreLogin();
                    contrasena_dada = interfaz.dameContrasenaLogin();
                    
                    //Comprobacion de login bien hecho
                    existeUsuario = bbdd.existeUsuario(usuario_dado);
                    if(existeUsuario==true){
                        Probarbase64 base = new Probarbase64("");
                        MessageDigest digest = MessageDigest.getInstance("SHA-256");
                        byte[] hash = digest.digest(contrasena_dada.getBytes(StandardCharsets.UTF_8));
                
                        String hashpsw = base.bytebase64(hash);
                        String pswbbdd = hashpsw.substring(hashpsw.length()/2);
                        String claveAES = hashpsw.substring(0, hashpsw.length()/2);

                        String contrasena_bbdd=bbdd.recogerPassword(usuario_dado);
                        if(contrasena_bbdd.equals(pswbbdd)){
                            
                            //PASO A LA FASE 2
                            usuarioValidado=true;
                            estadoCS = EstadoCS.SinEstado;
                            interfaz.ExitoLogin();
                            System.out.println("Inicio de sesión exitoso");
                            if(usuario_dado.equals("admin")){
                                esAdmin = true;
                            }
                        }else{
                            System.out.println("Error en inicio de sesión");
                            interfaz.ErrorLogin();
                            estadoCS = EstadoCS.SinEstado;
                        }
                    }else{
                        System.out.println("Error en inicio de sesión");
                        interfaz.ErrorLogin();
                        estadoCS = EstadoCS.SinEstado;
                        //a
                    }
                    break;

                //ESTADO REGISTRO
                case Registro:
                    AES aes = new AES();
                    RSA rsa = new RSA();
                    Probarbase64 base = new Probarbase64("");

                    usuario_dado=interfaz.dameNombreRegistro();
                    contrasena_dada1 = interfaz.dameContrasena1Registro();
                    contrasena_dada2 = interfaz.dameContrasena2Registro();
                    tipo_permiso_dado = interfaz.dameTipoPermisoRegistro();

                    Boolean existe=bbdd.existeUsuario(usuario_dado); 
                    //Boolean existe=true;   
                    if(existe==false){                           //si el usuario no existe lo creamos    
                        if(contrasena_dada1.equals(contrasena_dada2)){             //si las contrasenas coinciden    
                            MessageDigest digest = MessageDigest.getInstance("SHA-256");
                            byte[] hash = digest.digest(contrasena_dada1.getBytes(StandardCharsets.UTF_8));
                    
                            String hashpsw = base.bytebase64(hash);  
                            String pswbbdd = hashpsw.substring(hashpsw.length()/2);
                            String claveAES = hashpsw.substring(0,hashpsw.length()/2);
                            KeyPair pairRSA = rsa.crearParClaves();         //generamos par de claves
                            PublicKey publicKeyRSA = pairRSA.getPublic();   //cogemos la publica y la metemos en base de datos
                            PrivateKey privateKeyRSA = pairRSA.getPrivate();   //cogemos la privada 
                            SecretKey skAES = base.asciiSecretKey(claveAES);
                            String clavepvstring = aes.encryptString(base.base64PrivateKey(privateKeyRSA), skAES);
                            
                            base.bFichero(clavepvstring.getBytes(), "datos/"+usuario_dado+"/"+usuario_dado+".pvk");             //guardamos rsa privada en un archivo
                            bbdd.insertarUsuario(usuario_dado, base.base64PublicKey(publicKeyRSA), pswbbdd,tipo_permiso_dado);   //insertamos el usuario
                            
                            //base.stringToFile(base.base64PrivateKey(privateKeyRSA), "datos/"+usuario_dado+".pvk");
                            //base.bFichero(base.base64PrivateKey(privateKeyRSA).getBytes(), "datos/"+usuario_dado+".pvk");   //guardamos rsa privada en un archivo
                            usuarioValidado = true;
                            estadoCS = EstadoCS.SinEstado;
                            interfaz.ExitoRegistro();
                            System.out.println("Éxito al registrar usuario");
                        }else{
                            System.out.println("Error al registrar usuario");
                            interfaz.ErrorRegistro("Las contrasenas no coinciden");
                            estadoCS = EstadoCS.SinEstado;
                        }
                    }else{
                        System.out.println("Error al registrar usuario");
                        interfaz.ErrorRegistro("El usuario ya existe.");
                        estadoCS = EstadoCS.SinEstado;
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
                    //Obtener lista de ficheros con el usuario y permiso [LLamado desde interfaz]
                case ObtenerFicheros:
                    interfaz.ObtenerFicheros(bbdd.recogerArchivosporTipo(bbdd.recogerIDpermiso(bbdd.recogerTipoArchivo(usuario_dado))));
                    estadoCS = EstadoCS.SinEstado;
                    break;

                //ESTADO SOLICITAR FICHERO
                    //Solicitar un fichero para desencriptar [Llamado desde: interfaz]
                case SolicitarFichero:
                    nombre_archivo_dado = interfaz.dameNombreArchivoSolicitud();
                    System.out.println(nombre_archivo_dado);
                    estadoCS = EstadoCS.SinEstado;
                    /*
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
                    */
                    break;
                
                //ESTADO ESPERAR RESPUESTA
                    //Comprobar que haya una respuesta [LLamado desde: SolicitarFichero]
                case EsperarRespuesta:
                //TODO: Comprobar que haya una respuesta en la carpeta (se puede poner cooldown para que no consuma mucho)
                    String directorioActual = System.getProperty("user.dir");
                    String directorioCheck = directorioActual+"/datos/"+usuario_dado+"/respuestas";
                    File ruta = new File(directorioCheck);
                    while(true){
                        String[] archivos = ruta.list();
                        if(archivos.length > 0){
                            /*foreach(String arch : archivos){
                                if(arch.equals(usuario_dado)){
                                    break;
                                }
                            }*/
                            break;
                        }
                    }
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
                    Probarbase64 base = new Probarbase64("");
                    AES aes = new AES();
                    RSA rsa = new RSA();
                    archivos metodo = new archivos();
                    String path = interfaz.damePathFichero();
                    File f = new File(path);                    //cogemos el file de desencriptar
                    String nombre_archivo = f.getName();        //cogemos el nombre que tenga
                    nombre_archivo = nombre_archivo.substring(0, nombre_archivo.lastIndexOf('.'));  //quitamos lo q hay despues del ."enc"
                
                    String subpath = path.substring(0,path.lastIndexOf('\\'))+"\\";
                    String keyRSA = subpath+nombre_archivo+".key";                      //pongo como es el nombre de la key
                    //File fKeyRSA = new File(keyRSA);                          //cojo el archivo
                    String keyRSAString = base.fileToString(keyRSA);            //cojo el archivo y lo paso a String

                    int mitadHash = keyRSAString.length()/2;
                    String primeraMitad = keyRSAString.substring(0, mitadHash);
                    SecretKey skhashpsw = base.asciiSecretKey(primeraMitad);
                    keyRSAString = aes.decryptString(keyRSAString, skhashpsw);   //desencriptamos el .key con la 1a mitad del hash
                    
                    PrivateKey privKey = base.asciiToPrivateKey(keyRSAString);  //paso el string a PrivateKey
                    
                    String archivo_decript = bbdd.recogerNombre(nombre_archivo);    //coges el nombre del archivo de la bbdd
                    /*
                    String clave = bbdd.recogerClave(nombre_archivo);               //cogemos la clave AES de ese archivo

                    SecretKey claveTest = base.asciiSecretKey(clave);

                    byte[] clave_bytes = claveTest.getEncoded();
                    SecretKey claveAES = rsa.decryptKey(clave_bytes, privKey);                          //desencriptamos la clave AES con la privada de RSA
                    */
                    //para desencriptar necesitamos el nombre del usaurio que lo ha subido
                    
                    String[] nombre_user_duenyo = nombre_archivo_dado.split("(");
                    String nombre_duenyo = nombre_user_duenyo[1].substring(0, nombre_user_duenyo.length-1);

                    metodo.desencriptarArchivo(archivo_decript, nombre_duenyo, privKey);
                    //base.bFichero(aes.decryptFile(path,claveAES), "datos/"+usuario_dado+"/decript/"+archivo_decript);   //pasamos de base64 la clave AES a Secret Key
                                                                                                                    //desencriptamos y 
                                                                                                                    // convertimos a fichero
                    interfaz.ExitoDesencriptar();
                    
                    break;

                //ESTADO ENCRIPTAR
                    //Encriptar un fichero [Llamado desde: interfaz]
                case Encriptar:
                    //AES aes = new AES();
                    aes = new AES();
                    rsa = new RSA();
                    base = new Probarbase64("");
                    path = interfaz.damePathFichero();

                    File fEnc = new File(path);
                    nombre_archivo = fEnc.getName();
                    SecretKey claveEnc = aes.getAESKey();

                    //supuestaclaveAES = clave;

                    String clave_string = base.base64SecretKey(claveEnc);

                    //cogemos la clave publica del usuario
                    String publicKeyString = bbdd.recogerClavePublica(usuario_dado);
                    PublicKey publicKey =  base.asciiToPublicKey(publicKeyString);
                    // PublicKey publicKey = pairRSA.getPublic();
                    // cogemos la clave privada del usuario que es el .pvk
                    String pvkUsuario = base.fileToString("datos/"+usuario_dado+"/"+usuario_dado+".pvk");
                    //De string pasamos a PrivateKey
                    PrivateKey privateKey = base.asciiToPrivateKey(pvkUsuario);
                    // PrivateKey privateKey = pairRSA.getPrivate();

                    byte[] claveEncriptada = rsa.encryptKey(claveEnc,publicKey);           //encriptamos la clave publica
                    String claveEncriptadaString = base.bytebase64(claveEncriptada);    //en string

                    //convertimos las claves a base64
                    String publicRSAKeyString = base.base64PublicKey(publicKey);
                    String privateRSAKeyString = base.base64PrivateKey(privateKey);

                    //mete la clave en la bbdd en la tabla de archivos
                    int tipo = -1;
                    String extension = "";
                    int index = nombre_archivo.lastIndexOf('.');
                    if (index > 0) {
                        extension = nombre_archivo.substring(index + 1);
                    }
                    if(extension.equals("")==false){
                        switch(extension){
                            //audio
                            case ".mp3":
                            case ".wav":
                            case ".midi":
                                tipo = 2;
                            //imagen
                            case ".jpg":
                            case ".jpeg":
                            case ".png":
                            case ".gif":
                            case ".tiff":
                            case ".psd":
                            case ".raw":
                                tipo = 3;
                            //video
                            case ".flv":
                            case ".vob":
                            case ".mp4":
                            case ".avi":
                            case ".viv":
                            case ".mpg":
                            case ".mkv":
                                tipo = 4;
                            
                        }
                    }
                    if(tipo!=-1){

                        bbdd.insertarClave(nombre_archivo, claveEncriptadaString, usuario_dado, tipo);
    
                        //coge el nombre del fichero
                        nombre_archivo = nombre_archivo.substring(0, nombre_archivo.lastIndexOf('.'));
    
                        //se guarda en fichero la clave privada de RSA
                        base.stringToFile(privateRSAKeyString, "datos/"+usuario_dado+"/encript/"+nombre_archivo+".key");
                        // mete en la carpeta encript el archivo con su_nombre.enc
                        base.bFichero(aes.encryptFile(path, claveEnc),  "datos/"+usuario_dado+"/encript/"+nombre_archivo+".enc");
                    }

                    interfaz.ExitoEncriptar();

                    break;

                //ESTADO COMPARTIR FICHEROS
                    //Compartir los ficheros haciendo un hilo de ejecución [Llamado desde: interfaz]
                case CompartirFicheros:
                    compartirCS = new CompartirCS();
                    compartirCS.start();
                    //Devuelve el estado SIN ESTADO
                    estadoCS = EstadoCS.SinEstado;
                    break;
                
                //ESTADO DEJAR DE COMPARTIR
                    //Dejar de compartir los ficheros [Llamado desde: interfaz]
                case DejarDeCompartir:
                    //Al cambiar el estado a dejar de compartir los hilos se tienen que destruir
                    if(!compartirCS.isAlive()){
                        estadoCS = EstadoCS.SinEstado;
                    }
                    break;
                
                //Aqui entraran los estados de la fase 1 y SinEstado
                default:
                    if(estadoCS!=EstadoCS.SinEstado){
                        System.err.println("No pueden haber estados de la FASE 1 en la FASE 2");
                    }
                    break;
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

