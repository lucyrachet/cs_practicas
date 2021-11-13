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
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/p1cs", "root", "root");

        }catch(Exception e){
            System.out.println("No se ha podido conectar a la base de datos");
            System.err.println(e);
        }
        return con;
    }

    //Se le pasa por parametro un STRING de tanto el archivo, la clave y el usuario del cual es el archivo y se insertan en la bbdd
    public void insertarClave (String nombre_archivo, String clave, String usuario){
        try{
            Connection con = crearConexion();
            Statement stm=con.createStatement();
            stm.execute("insert into archivoclaves values('"+nombre_archivo+"','"+clave+"','"+usuario+"')");
            con.close();
        }catch(Exception e){
            System.out.println("No se ha podido insertar la clave del archivo: "+nombre_archivo+" en la bbdd");
            System.err.println(e);
        }
    }

    //Se le pasa por parametro el nombre del archivo (ya que la clave supuestamente no se conoce) y se recoge la clave
    public String recogerClave(String nombre_archivo){
        String clave="";
        try{
            Connection con = crearConexion();

            PreparedStatement pstmt = con.prepareStatement("select clave from archivoclaves where nombre like ?");
            pstmt.setString(1, nombre_archivo + "%");
            ResultSet rs = pstmt.executeQuery(); 
            if(rs.next()){
                clave=rs.getString(1);
            }
            con.close();
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

    //Se le pasa por parametro un STRING de tanto el usuario, su clavePublica y su contraseña en HASH que se insertan en la bbdd
    public void insertarUsuario (String nombre_usuario, String clavePublica, String password){
        try{
            Connection con = crearConexion();
            Statement stm=con.createStatement();
            stm.execute("insert into usuarios values('"+nombre_usuario+"','"+clavePublica+"','"+password+"')");
            con.close();
        }catch(Exception e){
            System.out.println("No se ha podido insertar la clavePublica del usuario: "+nombre_usuario+" en la bbdd");
            System.err.println(e);
        }
    }

    //Recoge la clave pública del usuario pasado por parámetro
    public String recogerClavePublica(String nombre_usuario){
        String clave="";
        try{
            Connection con = crearConexion();

            PreparedStatement pstmt = con.prepareStatement("select clavePublica from usuarios where nombre like ?");
            pstmt.setString(1, nombre_usuario + "%");
            ResultSet rs = pstmt.executeQuery(); 
            if(rs.next()){
                clave=rs.getString(1);
            }
            con.close();
        }catch(SQLException e){
            System.out.println("No se ha podido encontrar la clavePublica para el usuario: "+nombre_usuario);
            System.err.println(e);
        }
        return clave;
    }

    //Recoge la parte del HASH que contiene la contraseña del usuario pasado por parámetro
    public String recogerPassword(String nombre_usuario){
        String password="";
        try{
            Connection con = crearConexion();

            PreparedStatement pstmt = con.prepareStatement("select password from usuarios where nombre like ?");
            pstmt.setString(1, nombre_usuario + "%");
            ResultSet rs = pstmt.executeQuery(); 
            if(rs.next()){
                password=rs.getString(1);
            }
            con.close();
        }catch(SQLException e){
            System.out.println("No se ha podido encontrar la contraseña para el usuario: "+nombre_usuario);
            System.err.println(e);
        }
        return password;
    }

    //Comprueba si existe algún usuario 
    public Boolean existeUsuario(String nombre_usuario){
        Boolean existe=false;
        try{
            Connection con = crearConexion();

            PreparedStatement pstmt = con.prepareStatement("select password from usuarios where nombre like ?");
            pstmt.setString(1, nombre_usuario + "%");
            ResultSet rs = pstmt.executeQuery(); 
            if(rs.next()){
                // password=rs.getString(1);
                existe=true;
            }
            con.close();
        }catch(SQLException e){
            System.out.println("No se ha podido encontrar el usuario: "+nombre_usuario);
            existe=false;
            System.err.println(e);
        }
        return existe;
    }
    //Ejemplo de los métodos que se pueden realizar con esta clase, deben de ejecutarse "parrafo x parrafo"
    
    /*
    public static void main(String[] args){
        Datos bbdd = new Datos();

        bbdd.insertarUsuario("usuario1", "clavep1", "password1");
        bbdd.insertarUsuario("usuario2", "clavep2", "password2");
        
        bbdd.insertarClave("archivo1", "clave1","usuario1");
        bbdd.insertarClave("archivo2", "clave2","usuario2");

        System.out.println("La clave del archivo 1: "+bbdd.recogerClave("archivo1"));
        System.out.println("La clave del archivo 2: "+bbdd.recogerClave("archivo2"));

        System.out.println("La clave pública del usuario 1: "+bbdd.recogerClavePublica("usuario1"));
        System.out.println("La clave pública del usuario 2: "+bbdd.recogerClavePublica("usuario2"));
        
        System.out.println("La contraseña del usuario 1: "+bbdd.recogerPassword("usuario1"));
        System.out.println("La contraseña del usuario 2: "+bbdd.recogerPassword("usuario2"));
        
        if(bbdd.existeUsuario("usuario2")){
            System.out.println("Existe el usuario");
        }else{
            System.out.println("No existe el usuario");
        }
    }
    */
    
}