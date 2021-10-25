import java.sql.*;
public class Datos {

    public Datos(){}

    /*En cada metodo se abre y se cierra la conexión. 
    Si habeis hecho cambios en los nombres, cambiad la url donde 
        p1cs = nombre de la base de datos
        root = nombre del usuario
        practica1CS = contraseña (se define en la configuracion de la descarga de MySQL https://dev.mysql.com/downloads/installer/)
    */
    public Connection crearConexion(){
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/p1cs", "root", "practica1CS");

        }catch(Exception e){
            System.out.println("No se ha podido conectar a la base de datos");
            System.err.println(e);
        }
        return con;
    }

    //Se le pasa por parametro un STRING de tanto el archivo y la clave y se insertan en la bbdd
    public void insertarClave (String nombre_archivo, String clave){
        try{
            Connection con = crearConexion();
            Statement stm=con.createStatement();
            stm.execute("insert into archivoclaves values('"+nombre_archivo+"','"+clave+"')");
            con.close();
        }catch(Exception e){
            System.out.println("No se ha podido insertar la clave del archivo: "+nombre_archivo+" en la bbdd");
            System.err.println(e);
        }
    }

    //Se le pasa por parametro el nombre del archivo (ya que la clave supuestamente no se conoce) y se recoge la clave
    // También llama al método que elimina el archivo con la clave ya que solo se llama para descomprimir y eso se hace 1 vez solo
    public String recogerClave(String nombre_archivo){
        String clave="";
        try{
            Connection con = crearConexion();

            PreparedStatement pstmt = con.prepareStatement("select clave from archivoclaves where nombre like ?");
            pstmt.setString(1, nombre_archivo + "%");
            ResultSet rs = pstmt.executeQuery(); 
            /*
            Statement stm=con.createStatement();
            ResultSet rs=stm.executeQuery("select clave from archivoclaves where nombre like 'foto.jpg'");
            */
            if(rs.next()){
                clave=rs.getString(1);
            }            
            con.close();
            eliminar(nombre_archivo);
        }catch(SQLException e){
            System.out.println("No se ha podido encontrar la clave para el archivo: "+nombre_archivo);
            System.err.println(e);
        }
        return clave;
    }

    public String recogerNombre(String nombre_archivo){
        String clave="";
        try{
            Connection con = crearConexion();

            PreparedStatement pstmt = con.prepareStatement("select nombre from archivoclaves where nombre like ?");
            pstmt.setString(1, nombre_archivo + "%");

            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()){
                clave=rs.getString(1);
            }            
            con.close();
        }catch(Exception e){
            System.out.println("No se ha podido encontrar la clave para el archivo: "+nombre_archivo);
            System.err.println(e);
        }
        return clave;
    }
    
    //Metodo que elimina el archivo para evitar que si se comprime de nuevo, no de error de primary key duplicada
    public void eliminar(String nombre_archivo){
        try{
            Connection con = crearConexion();
            String sql =  "DELETE FROM archivoclaves WHERE nombre= ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nombre_archivo);
            pstmt.executeUpdate(); 
            con.close();
        }catch(Exception e){
            System.out.println("No se ha podido eliminar la fila que contiene el archivo: "+nombre_archivo);
            System.err.println(e);
        }
        
    }

    //Ejemplo de los 2 métodos que se pueden realizar con esta clase

    /*
    public static void main(String[] args){
        Datos bbdd = new Datos();
        bbdd.insertarClave("nombre_archivo", "clave");
        bbdd.insertarClave("nombre_archivo2", "clave2");
        System.out.println(bbdd.recogerClave("nombre_archivo"));
        
    }
    */
}
