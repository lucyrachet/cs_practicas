import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import javax.crypto.SecretKey;


public class RSA{

    public RSA(){}


    public void crearParClaves() throws NoSuchAlgorithmException{
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024);
        KeyPair pair = generator.generateKeyPair();
    }


    public PairKey encryptAESKey(SecretKey clave) {
        return null;
    }
}

