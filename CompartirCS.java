import java.io.File;

public class CompartirCS extends Thread {

    class AtenderSolicitud extends Thread{
        public AtenderSolicitud() {
        }
        @Override
        public void run() {
            while(ProyectoCS.estadoCS!=EstadoCS.DejarDeCompartir){
                //TODO: Hacer lógica atender solicitud
                archivos acceder = new archivos();
                String directorioActual = System.getProperty("user.dir");
                String directorioCheck = directorioActual+"/datos/admin/solicitudes";
                File ruta = new File(directorioCheck);
                String[] archivos = ruta.list();
                String[] archivo = archivos[0].split("_");
                String nombre = archivo[0];
                String user = archivo[1];

                ruta = new File(directorioCheck+archivo[0]);
                acceder.accederArchivo(nombre, user);
                
                
                ruta.delete();  //borramos el fichero
            }
        }
    }

    AtenderSolicitud solicitud;
    public CompartirCS() {
        solicitud = null;
    }

    @Override
    public void run() {
        while(ProyectoCS.estadoCS!=EstadoCS.DejarDeCompartir){
            String directorioActual = System.getProperty("user.dir");
            String directorioCheck = directorioActual+"/datos/admin/solicitudes";
            File ruta = new File(directorioCheck);
            Boolean haysolicitud = false;
            while(true){
                String[] archivos = ruta.list();
                if(archivos.length > 0){
                    haysolicitud=true;
                    break;
                }
                Thread.currentThread();
                try {
                    Thread.sleep(100);  //digamos que esto funciona
                } catch (InterruptedException e) {
                    
                    e.printStackTrace();
                } 
            }
            
            if(haysolicitud){
                solicitud = new AtenderSolicitud();
                solicitud.start();
            }
            

        }
    }
}