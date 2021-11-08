

public class ProyectoCS {
    private static Interfaz interfaz;
    
    
    
    public static void main(String[] args) throws Exception {
       interfaz = new Interfaz();
        
        interfaz = new Interfaz();
        interfaz.setVisible(true);
        
        
        while (true) {
            System.out.print("");
            if(interfaz.pathDesencriptar.isBlank()==false|| interfaz.pathEncriptar.isBlank()==false){
                if(interfaz.pathEncriptar.isBlank()==false){
                    //Encriptar
                    System.out.println(interfaz.pathEncriptar);
                    Encript encriptar = new Encript(interfaz.pathEncriptar);
                    interfaz.pathEncriptar = "";
                } else{
                    //Desencriptar
                    Decript desencriptar = new Decript(interfaz.pathDesencriptar);
                    interfaz.pathDesencriptar = "";

                }
            }
        }
      
    }
}

