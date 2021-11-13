import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import java.io.*;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;



public class AES{

    public AES(){}
    
    public SecretKey getAESKeyPSW(String psw) throws NoSuchAlgorithmException{
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128, SecureRandom.getInstanceStrong());
        SecretKey sk = keyGen.generateKey();
        sk = new SecretKeySpec(psw.getBytes(), 0, 16, "AES");
        return sk;
    }

    public static SecretKey getAESKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        return keyGen.generateKey();
    }


    public static byte[] encryptFile(String path, SecretKey sKey) throws Exception {
        if (sKey == null) {
                         System.out.print ("La clave es nula"); //Comprobar que la clave no sea nula
            return new byte[0];
        }
        File archivo = new File(path); //Almacenar el archivo en una variable
        byte[] archivoBytes = Files.readAllBytes(archivo.toPath()); //Pasar el archivo a bytes para luego encriptarlo
        Cipher cipher = Cipher.getInstance ("AES / ECB / PKCS5Padding"); // "algoritmo / modo / método de complemento"
        cipher.init(Cipher.ENCRYPT_MODE, sKey); 
        return cipher.doFinal(archivoBytes); 
    }

    public byte[] decryptFile(String path, SecretKey sKey) throws Exception {
        System.out.println("path: "+ path);
        try {
                         // Determine si la clave es correcta
            if (sKey == null) {
                return new byte[0];
            }
            File archivo = new File(path); //Almacenar el archivo en una variable
            byte[] archivoBytes = Files.readAllBytes(archivo.toPath()); //Pasar el archivo a bytes para luego encriptarlo
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, sKey);
            return cipher.doFinal(archivoBytes); 
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return new byte[0];
        }
    }

    
    // cifrado
    
    public static String encryptString(String sSrc, SecretKey sKey) throws Exception {
        if (sKey == null) {
                         System.out.print ("La clave es nula");
            return null;
        }
        //byte[] raw = sKey.getBytes("utf-8");
        //SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance ("AES / ECB / PKCS5Padding"); // "algoritmo / modo / método de complemento"
        cipher.init(Cipher.ENCRYPT_MODE, sKey);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
 
                 return Base64.getEncoder().encodeToString(encrypted); // Aquí, BASE64 se utiliza para la función de transcodificación, que también puede desempeñar el papel de cifrado doble.
    }
 
         // descifrar
    public static String decryptString(String sSrc, SecretKey sKey) throws Exception {
        try {
                         // Determine si la clave es correcta
            if (sKey == null) {
                                 System.out.print ("La clave es nula");
                return null;
            }
            //byte[] raw = sKey.getBytes("utf-8");
            //SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, sKey);
            byte [] encrypted1 = Base64.getDecoder().decode(sSrc); // Primero descifra con base64
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    
    /*public static boolean bFichero(byte[] bytes, String salida){
        boolean hecho=false;

        try (FileOutputStream fos = new FileOutputStream(salida)) {
           fos.write(bytes);
           hecho=true;
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return hecho;

    } */

    /*public static String base64(byte[] cadena) {
        String encoded = Base64.getEncoder().encodeToString(cadena);
        return encoded;
	}*/
     

    
    public static void main(String[] args) throws Exception {
        SecretKey cKey = getAESKey(); //Llamo al método para obtener  la clave
        System.out.println(cKey);
        String p = "patata";
        System.out.println(p);
        String ep = encryptString(p, cKey);
        System.out.println("CLAVE ENCRIPTADA: "+ep);
        System.out.println("CLAVE DESENCRIPTADA: "+decryptString(ep, cKey));

        
    }

}