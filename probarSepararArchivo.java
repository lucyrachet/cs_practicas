import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class probarSepararArchivo {
	public probarSepararArchivo() {
		
	}
	/*
	 * Separa los archivos
	 */
	
	public void splitFile(File f, int size) throws IOException {
        int partCounter = 1;//I like to name parts from 001, 002, 003, ...
                            //you can change it to 0 if you want 000, 001, ...

        //int sizeOfFiles = 1024 * 1024;// 1MB
        int sizeOfFiles = 1024 * size;
        byte[] buffer = new byte[sizeOfFiles];

        String fileName = f.getName();

        //try-with-resources to ensure closing stream
        try (FileInputStream fis = new FileInputStream(f);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            int bytesAmount = 0;
            while ((bytesAmount = bis.read(buffer)) > 0) {
                //write each chunk of data into separate file with different number in name
                String filePartName = String.format("%s.%03d", fileName, partCounter++);
                File newFile = new File(f.getParent(), filePartName);
                try (FileOutputStream out = new FileOutputStream(newFile)) {
                    out.write(buffer, 0, bytesAmount);
                }
            }
        }
    }
	/*
	 * Junta los archivos
	 */
	
	public void mergeFiles(List<File> files, File into)
	        throws IOException {
	    try (FileOutputStream fos = new FileOutputStream(into);
	         BufferedOutputStream mergingStream = new BufferedOutputStream(fos)) {
	        for (File f : files) {
	            Files.copy(f.toPath(), mergingStream);
	        }
	    }
	}
	public void mergeFiles(File oneOfFiles, File into) throws IOException {
	    mergeFiles(listOfFilesToMerge(oneOfFiles), into);
	}
	/*
	 * Crea una lista de archivos que juntar
	 */
	public List<File> listOfFilesToMerge(File oneOfFiles) {
	    String tmpName = oneOfFiles.getName();//{name}.{number}
	    String destFileName = tmpName.substring(0, tmpName.lastIndexOf('.'));//remove .{number}
	    //if(oneOfFiles.getParentFile()!=null) {
	    	File[] files = oneOfFiles.getParentFile().listFiles(
		            (File dir, String name) -> name.matches(destFileName + "[.]\\d+"));
	    	Arrays.sort(files);//ensuring order 001, 002, ..., 010, ...
		    return Arrays.asList(files);
	   // }
	    
	   // return null;
	}
	
	public List<File> listOfFilesToMerge(String oneOfFiles) {
	    return listOfFilesToMerge(new File(oneOfFiles));
	}
	//este es el metodo que coge 2 strings uno con el primer archivo de los spliteados y otro con el nombre que quieras ponerle al juntarlo
	//ruta del archivo con .001 y coge el resto //ruta del archivo que mergea
	public void mergeFiles(String oneOfFiles, String into) throws IOException{
	    mergeFiles(new File(oneOfFiles), new File(into));
	}
}
