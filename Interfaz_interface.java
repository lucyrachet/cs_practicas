/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 * Interfaz_abstract
 */


public interface Interfaz_interface {
    
    
    
    //Metodos estado
    public void cambiarEstado(EstadoCS _estado);

    //Metodos para encriptar y desencriptar
    public String damePathFichero();

    public String dameNombreLogin();         //devuelve el nombre que haya introducido en el login
    public String dameContrasenaLogin();     //devuelve la contrasena que haya introducido en el login

    public String dameNombreRegistro();
    public String dameContrasena1Registro();
    public String dameContrasena2Registro();
    public int dameTipoPermisoRegistro();

    
    //public abstract void setVisible(Boolean visible);
    // public abstract String dameNombreUsuario();         //devuelve el nombre que haya introducido en el login
    // public abstract String dameContrasenaUsuario();     //devuelve la contrasena que haya introducido en el login
    
    /**
     * 
     * 
     */
}