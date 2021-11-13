import java.util.*;
import java.io.File;
import java.security.KeyPair;
import java.security.PublicKey;
import javax.crypto.SecretKey;

public abstract class RSA_abstract {
    
    public RSA_abstract(){}

    public abstract KeyPair getRSAKeys();

    public abstract SecretKey getAESKey();

    public abstract String encryptAESKey(SecretKey secretKey); 
}
