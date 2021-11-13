/**
 * Interfaz_abstract
 */

enum EstadoInterfaz{
    Nada,Login,Registro,Encriptar,Desencriptar
}

public abstract class Interfaz_abstract {
    
    protected EstadoInterfaz estado;
    
    //Metodos estado
    public abstract EstadoInterfaz dameEstado();
    protected abstract void cambiarEstado(EstadoInterfaz _estado);

    //Metodos para encriptar y desencriptar
    public abstract String damePathFichero();

    public abstract String dameNombreLogin();         //devuelve el nombre que haya introducido en el login
    public abstract String dameContrasenaLogin();     //devuelve la contrasena que haya introducido en el login

    public abstract String dameNombreRegistro();
    public abstract String dameContrasena1Registro();
    public abstract String dameContrasena2Registro();
    
    public abstract void setVisible(Boolean visible);
    // public abstract String dameNombreUsuario();         //devuelve el nombre que haya introducido en el login
    // public abstract String dameContrasenaUsuario();     //devuelve la contrasena que haya introducido en el login
    
}