import javax.crypto.*;
import java.io.*;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public abstract class AES_abstract {


    public abstract SecretKey getAESKey();
    
}
