public class CompartirCS extends Thread {

    class AtenderSolicitud extends Thread{
        public AtenderSolicitud() {
        }
        @Override
        public void run() {
            while(ProyectoCS.estadoCS!=EstadoCS.DejarDeCompartir){
                //TODO: Hacer lógica atender solicitud
    
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
            /* TODO: comprobar si hay una solicitud
            if(HAY SOLICITUD){
                solicitud = new AtenderSolicitud();
                solicitud.start();
            }
            */
        }
    }
}