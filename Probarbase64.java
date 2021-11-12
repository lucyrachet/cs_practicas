import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


import java.security.NoSuchAlgorithmException;


import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;


public class Probarbase64 {
	private String ruta = "C:\\Users\\lucyr\\eclipse-workspace\\CS-Encriptacion\\src\\archivos\\";
	
	//se inicializa con la ruta deseada
	public Probarbase64(String ruta) {
		this.setRuta(ruta);
	}

	//pasa a base64 un String
	public String base64(String cadena) {
		byte[] authBytes = cadena.getBytes();
        String encoded = Base64.getEncoder().encodeToString(authBytes);
        return encoded;
	}
	
	public String base64SecretKey(SecretKey key) {
		String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
		return encodedKey;
	}
	
	public SecretKey asciiSecretKey(String encodedKey) {
		byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
		SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES"); 
		return originalKey;
	}

	public String base64PublicKey(PublicKey publicKey){
		byte[] encodedPublicKey = publicKey.getEncoded();
		String b64PublicKey = Base64.getEncoder().encodeToString(encodedPublicKey);
		
		return b64PublicKey;
	}

	public PublicKey asciiToPublicKey(String publicKeyStr) throws  NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory kf = KeyFactory.getInstance("RSA");

        PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(publicKeyStr));
        PublicKey publicKey = kf.generatePublic(keySpecPKCS8);

		
		return publicKey;
    }

	public PrivateKey asciiToPrivateKey(String privateKeyStr) throws NoSuchAlgorithmException, InvalidKeySpecException{
		

		KeyFactory kf = KeyFactory.getInstance("RSA");

        PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyStr));
        PrivateKey privateKey = kf.generatePrivate(keySpecPKCS8);

		return privateKey;
	}

	public String base64PrivateKey(PrivateKey privateKey){
		byte[] encodedPublicKey = privateKey.getEncoded();
		String b64PrivateKey = Base64.getEncoder().encodeToString(encodedPublicKey);
		
		return b64PrivateKey;
	}

	/*
	public List<String> cogerMuchosArchivosBase64(ArrayList<String> archivos){
		System.out.println(ruta);
		List<String> listaFinal= new ArrayList<String>();
		for(String file : archivos){
			String rutaFinal = ruta+file+".001";
			System.out.println(rutaFinal);
			List<String> lista=base64File2(new File(rutaFinal));
			for(String ele:lista) {
				
				listaFinal.add(ele);
			}
			
			
		}
		return listaFinal;
		
	}*/
	

	//coge una lista de archivos con el nombre que le pasas numerados con la extension 001,002, etc.
	public List<String> base64File2(File archivo) {
	
		byte[] authBytes = null;
		probarSepararArchivo separar= new probarSepararArchivo();
		
		List<File> lista = separar.listOfFilesToMerge(archivo);
		List<byte[]> listabytes = new ArrayList<>();
		for(int i=0;i<lista.size();i++) {
			try {
				authBytes = Files.readAllBytes(lista.get(i).toPath());
		        
		         
		    } catch (IOException e) {
		        throw new IllegalStateException("could not read file ", e);
		    }
			listabytes.add(authBytes);
		}
		//authBytes = Files.readAllBytes(archivo.toPath());
		List<String> codificadas= new ArrayList<>();
		String encoded = null;
		for(int i=0;i<listabytes.size();i++) {
			encoded=Base64.getEncoder().encodeToString(listabytes.get(i));
			codificadas.add(encoded);
		}
		
        
        return codificadas;
	}
	
	//convierte a base 64 un archivo
	public byte[] base64File(File archivo) {
		
		byte[] authBytes = null;
		File archivoruta = new File(ruta+archivo);
		try {
			authBytes = Files.readAllBytes(archivoruta.toPath());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		byte[] encoded = Base64.getEncoder().encode(authBytes);
        return encoded;
	}
	
	
	//convierte a ascii un string y lo devuelve en byte[]
	public byte[] ascii(String codificada) {
		
		byte[] bytes = Base64.getDecoder().decode(codificada);
        
        return bytes;
	}
	
	//convierte una lista de strings en base64 a una lista de byte[] decodificados
	public List<byte[]> ascii(List<String> codificada) {
		List<byte[]> lista = new ArrayList<>();
		byte[] bytes = null;
		for(int i=0;i<codificada.size();i++) {
			 bytes=Base64.getDecoder().decode(codificada.get(i));
			 lista.add(bytes);
		}

        return lista;
	}
	
	//convierte un archivo a ascii
	public String asciiFile(File archivocod) {
		
		byte[] archivoByte = null;
		try {
			archivoByte = Files.readAllBytes(archivocod.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//final byte[] authBytes = base.getBytes(StandardCharsets.UTF_8);
        byte[] decoded = Base64.getDecoder().decode(archivoByte);
        String decodedString = new String (decoded);
        
     
        return decodedString;
	}
	
	//convierte a fichero 
	public boolean bFichero(byte[] bytes, String salida){
        boolean hecho=false;

        try (FileOutputStream fos = new FileOutputStream(ruta+salida)) {
           fos.write(bytes);
           hecho=true;
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

        return hecho;

    }
	//convierte a ficheros el string ya descodificado
	public boolean bFichero(List<byte[]> bytes, String salida){
        boolean hecho=false;
        probarSepararArchivo separar= new probarSepararArchivo();
        List<File> lista = separar.listOfFilesToMerge(salida);
        for(int i=0;i<bytes.size();i++) {
        	try (FileOutputStream fos = new FileOutputStream(lista.get(i))) {
                fos.write(bytes.get(i));
                hecho=true;
             } catch (FileNotFoundException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		} catch (IOException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
        }

        return hecho;

    }
	
	public boolean stringToFile(String cadena, String salida){
        boolean hecho=false;
        
        
		try(FileOutputStream fos = new FileOutputStream(salida);
        BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            //convert string to byte array
            byte[] bytes = cadena.getBytes();
            //write byte array to file
            bos.write(bytes);
            bos.close();
            fos.close();
            //System.out.print("Data written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        return hecho;

    }

	public String fileToString(String filePath) throws IOException{
		String contenido="";

		//Path fileName= Path.of(filePath);
		Charset encoding = Charset.defaultCharset();
        // reading all lines of file as List of strings
		List<String> lines = Files.readAllLines(Paths.get(filePath), encoding);
		
		// converting List<String> to palin string using java 8 api.
		contenido = lines.stream().collect(Collectors.joining("\n"));
		
 
		return contenido;
	}

	public boolean aFichero(List<String> cadena, ArrayList<String> salida){
        boolean hecho=false;
        List<byte[]> bytes =new ArrayList<byte[]>();
        for(String ele:cadena) {
        	byte[] bytes0 = ele.getBytes();
        	bytes.add(bytes0);
        }
        
       
        
        probarSepararArchivo separar= new probarSepararArchivo();
        List<File> listaFinal = new ArrayList<File>();
        for(String ele:salida) {
        	List<File> lista = separar.listOfFilesToMerge(ele);
        	listaFinal.addAll(lista);
        }
        
        for(int i=0;i<bytes.size();i++) {
        	try (FileOutputStream fos = new FileOutputStream(listaFinal.get(i))) {
                fos.write(bytes.get(i));
                hecho=true;
             } catch (FileNotFoundException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		} catch (IOException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
        }
        return hecho;

    }
	
	public void aFichero2(List<String> cadena,String salida){
        boolean hecho=false;
        List<byte[]> bytes =new ArrayList<>();
        for(String ele:cadena) {
        	byte[] bytes0 = ele.getBytes();
        	bytes.add(bytes0);
        }

       hecho=bFichero(bytes,salida);
 
	}
	

	
	//lee el fichero guardado en Base64
	public List<String> leerFicheroBase64(List<File> archivos) {
		
		List<String> lista =new ArrayList<>();
		for(File archivo:archivos) {
			try {
				lista.add(new String(Files.readAllBytes(Paths.get(archivo.getAbsolutePath())), StandardCharsets.UTF_8));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		return lista;
	}
	//lee un solo archivo
	public String leerFicheroBase64Uno(File archivo) {
		
		List<String> lista =new ArrayList<>();
		File archivoruta = new File(ruta+archivo);
		try {
			lista.add(new String(Files.readAllBytes(Paths.get(archivoruta.getAbsolutePath())), StandardCharsets.UTF_8));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista.get(0);
	}
	
	List<String> listSourceFiles(String dir) throws IOException {
	       List<String> result = new ArrayList<>();
	       
	       try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir))) {
	           for (Path entry: stream) {
	               result.add(entry.getFileName().toString());
	           }
	       } catch (DirectoryIteratorException ex) {
	           // I/O error encounted during the iteration, the cause is an IOException
	           throw ex.getCause();
	       }
	       
	       return result;
	}
	
	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	

}
