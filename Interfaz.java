public class Interfaz extends Interfaz_abstract{
    
    public Interfaz(){
        estado = EstadoInterfaz.Nada;
    }

    @Override
    public EstadoInterfaz dameEstado() {
        return estado;
    }

    @Override
    protected void cambiarEstado(EstadoInterfaz _estado) {
        if(estado != _estado){
            estado = _estado;
        } else{
            System.err.println("Para cambiar los estados de interfaz tienen que ser diferentes");
        }
        
    }

    @Override
    public String damePathFichero() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String dameNombreLogin() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String dameContrasenaLogin() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String dameNombreRegistro() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String dameContrasena1Registro() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String dameContrasena2Registro() {
        // TODO Auto-generated method stub
        return null;
    }

    
    
}
