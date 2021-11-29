public class CompartirCS extends Thread {

    class AtenderSolicitud extends Thread{
        public AtenderSolicitud() {
        }
        @Override
        public void run() {
            while(ProyectoCS.estadoCS!=EstadoCS.DejarDeCompartir){
                //TODO: hacer solicitud
    
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
            /* TODO:
            if(HAY SOLICITUD){
                solicitud = new AtenderSolicitud();
                solicitud.start();
            }
            */
        }
    }
}