import java.io.File;

public class CompartirCS extends Thread {

    class AtenderSolicitud extends Thread{
        String directorioActual = null;
        String directorioCheck = null;
        File ruta = null;
        String[] archivos = null;
        String[] archivo = null;

        public AtenderSolicitud() {
            directorioActual = System.getProperty("user.dir");
            directorioCheck = directorioActual+"/datos/admin/solicitudes";
            ruta = new File(directorioCheck);
            archivos = ruta.list();
            archivo = archivos[0].split("_");

            ruta = new File(directorioCheck+"/"+archivos[0]);
            System.out.println(ruta);
            ruta.delete();  //borramos el fichero
        }
        @Override
        public void run() {
            //TODO: Hacer lógica atender solicitud
            archivos acceder = new archivos();
            
            String nombre = archivo[0];
            String user = archivo[1].substring(0, archivo[1].indexOf("."));

            acceder.accederArchivo(nombre, user);
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