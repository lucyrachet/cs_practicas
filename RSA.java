import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class RSA{

    public RSA(){}


    public static KeyPair crearParClaves() throws NoSuchAlgorithmException{
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024);
        return generator.generateKeyPair();
    }

    //Encripta clave AES
    public static byte[] encryptKey(SecretKey sKey, PublicKey puk) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{

        byte[] encryptedKey; 
  
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, puk);
        encryptedKey = cipher.doFinal(sKey.getEncoded());

        return encryptedKey;

    }

    //Desencripta AES
    public static SecretKey decryptKey(byte[] sKey, PrivateKey prk) throws NoSuchAlgorithmException,NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        byte[] decryptedKey;

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, prk);
        decryptedKey = cipher.doFinal(sKey);
        return new SecretKeySpec(decryptedKey, 0, decryptedKey.length, "RSA");
    }

    /*public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        AES aes = new AES();
        SecretKey clave = aes.getAESKey();
        KeyPair kp = crearParClaves();
        PrivateKey prk = kp.getPrivate();
        PublicKey puk = kp.getPublic();
        Probarbase64 pb = new Probarbase64("ruta");
        String claveString = pb.base64SecretKey(clave);
        System.out.println(claveString);
        byte[] clavenecripted = encryptKey(clave, puk);
        System.out.println();
        System.out.println(clavenecripted);
        SecretKey sk = decryptKey(clavenecripted, prk);
        System.out.println();
        System.out.println(pb.base64SecretKey(sk));
    }*/
}

