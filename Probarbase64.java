import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

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
