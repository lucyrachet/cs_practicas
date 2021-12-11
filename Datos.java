import java.sql.*;
import java.util.ArrayList;
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
    public void insertarClave (String nombre_archivo, String clave, String usuario, int tipo){
        try{
            Connection con = crearConexion();
            Statement stm=con.createStatement();
            stm.execute("insert into archivoclaves values('"+nombre_archivo+"','"+clave+"','"+usuario+"',"+tipo+")");
            con.close();
        }catch(Exception e){
            System.out.println("No se ha podido insertar la clave del archivo: "+nombre_archivo+" en la bbdd");
            System.err.println(e);
        }
    }

    //Se le pasa por parametro el nombre del archivo (ya que la clave supuestamente no se conoce) y se recoge la clave
    public String recogerClave(String nombre_archivo, String usuario){
        String clave="";
        try{
            Connection con = crearConexion();

            PreparedStatement pstmt = con.prepareStatement("select clave from archivoclaves where nombre like ? AND usuario='"+usuario+"'");
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

    //Recoge el nombre del archivo
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

    //devuelve el tipo del archivo
    public String recogerTipoArchivo(String nombre_archivo){
        String tipo="";
        try{
            Connection con = crearConexion();

            PreparedStatement pstmt = con.prepareStatement("select t.nombre from archivoclaves a,tiposarchivo t where a.nombre like ? AND a.tipo = t.id");
            pstmt.setString(1, nombre_archivo + "%");

            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next()){
                tipo=rs.getString(1);
            }
            con.close();
        }catch(Exception e){
            System.out.println("No se ha podido encontrar el tipo para el archivo: "+nombre_archivo);
            System.err.println(e);
        }
        return tipo;
    }

    /*
    //Recoge todos los archivos que hay en la base de datos, cada string contendrá la información de un archivo
    //separado por el símbolo de ";"
    public ArrayList<String> recogerArchivos(){
        ArrayList<String> archivos = new ArrayList<>();
        String datos = "";
        try{
            Connection con = crearConexion();

            PreparedStatement pstmt = con.prepareStatement("select nombre,usuario from archivoclaves");
            //PreparedStatement pstmt = con.prepareStatement("select * from archivoclaves");

            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                //datos+=rs.getString(1)+";"+rs.getString(2)+";"+rs.getString(3)+";"+rs.getString(4);
                datos+=rs.getString(1)+" ("+rs.getString(2)+")";
                archivos.add(datos);
                datos = "";
            }
            con.close();
        }catch(Exception e){
            System.out.println("No se ha podido ejecutar la recuperación de la lista de archivos");
            System.err.println(e);
        }
        return archivos;
    }
    */

    //Crea un arraylist de string con los datos según el permiso introducido
    public ArrayList<String> recogerArchivos(String nombre, int permiso){
        ArrayList<String> archivos = new ArrayList<>();
        String datos = "";
        try{
            Connection con = crearConexion();
            PreparedStatement pstmt = con.prepareStatement("select nombre,usuario from archivoclaves where tipo="+permiso+" AND usuario='admin'");
            
            if(permiso==1){
                pstmt = con.prepareStatement("select nombre,usuario from archivoclaves where usuario='admin'");
            }
            
            //PreparedStatement pstmt = con.prepareStatement("select * from archivoclaves");

            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                //datos+=rs.getString(1)+";"+rs.getString(2)+";"+rs.getString(3)+";"+rs.getString(4);
                datos+=rs.getString(1)+" ("+rs.getString(2)+")";
                archivos.add(datos);
                datos = "";
            }

            if(!nombre.equals("admin")){
                pstmt = con.prepareStatement("select nombre,usuario from archivoclaves where usuario='"+nombre+"'");
                
                if(permiso==1){
                    pstmt = con.prepareStatement("select nombre,usuario from archivoclaves where usuario='"+nombre+"'");
                }
                
                //PreparedStatement pstmt = con.prepareStatement("select * from archivoclaves");
    
                rs = pstmt.executeQuery();
                
                while(rs.next()){
                    //datos+=rs.getString(1)+";"+rs.getString(2)+";"+rs.getString(3)+";"+rs.getString(4);
                    datos+=rs.getString(1)+" ("+rs.getString(2)+")";
                    archivos.add(datos);
                    datos = "";
                }
            }


            con.close();
        }catch(Exception e){
            System.out.println("No se ha podido ejecutar la recuperación de la lista de archivos");
            System.err.println(e);
        }
        return archivos;
    }
    

    //Se le pasa por parametro un STRING de tanto el usuario, su clavePublica y su contraseña en HASH que se insertan en la bbdd
    public void insertarUsuario (String nombre_usuario, String clavePublica, String password, int tipo){
        try{
            Connection con = crearConexion();
            Statement stm=con.createStatement();
            stm.execute("insert into usuarios values('"+nombre_usuario+"','"+clavePublica+"','"+password+"',"+tipo+")");
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

    //Recoge los permisos del usuario
    public String recogerTipoUsuario(String nombre_usuario){
        String tipo="";
        try{
            Connection con = crearConexion();

            PreparedStatement pstmt = con.prepareStatement("select t.nombre from usuarios u,tiposarchivo t where u.nombre like ? AND u.acceso = t.id");
            pstmt.setString(1, nombre_usuario + "%");
            ResultSet rs = pstmt.executeQuery(); 
            if(rs.next()){
                tipo=rs.getString(1);
            }
            con.close();
        }catch(SQLException e){
            System.out.println("No se ha podido encontrar el permiso para el usuario: "+nombre_usuario);
            System.err.println(e);
        }
        return tipo;
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
    
    //Devuelve todos los permisos posibles
    public ArrayList<String> recogerPermisos(){
        ArrayList<String> tipos = new ArrayList<>();
        try{
            Connection con = crearConexion();

            PreparedStatement pstmt = con.prepareStatement("select nombre from tiposarchivo");
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                tipos.add(rs.getString(1));
            }
            con.close();
        }catch(Exception e){
            System.out.println("No se ha podido ejecutar la recuperación de la lista de tipos de permisos");
            System.err.println(e);
        }
        return tipos;
    }

    //Devuelve el id a partir del nombre del permiso
    public int recogerIDpermiso(String nombre_tipo){
        int id = 0;
        
        try{
            Connection con = crearConexion();

            PreparedStatement pstmt = con.prepareStatement("select id from tiposarchivo where nombre like ?");
            pstmt.setString(1, nombre_tipo + "%");
            ResultSet rs = pstmt.executeQuery(); 
            if(rs.next()){
                id = rs.getInt(1);
            }
            con.close();
        }catch(SQLException e){
            System.out.println("No se ha podido encontrar el id del permiso: "+nombre_tipo);
            System.err.println(e);
        }

        return id;
    }

    //Devuelve el nombre a partir del int
    public String recogerNombrePermiso(int id){
        String nombre = "";
        
        try{
            Connection con = crearConexion();

            PreparedStatement pstmt = con.prepareStatement("select nombre from tiposarchivo where id="+id);
            ResultSet rs = pstmt.executeQuery(); 
            if(rs.next()){
                nombre = rs.getString(1);
            }
            con.close();
        }catch(SQLException e){
            System.out.println("No se ha podido encontrar el nombre del permiso: "+id);
            System.err.println(e);
        }

        return nombre;
    }

    //Ejemplo de los métodos que se pueden realizar con esta clase, deben de ejecutarse "parrafo x parrafo"
    public static void main(String[] args){
        Datos bbdd = new Datos();

        System.out.println(bbdd.recogerTipoUsuario("test"));
        System.out.println(bbdd.recogerIDpermiso(bbdd.recogerTipoUsuario("test")));
        /*ArrayList<String> datos = bbdd.recogerArchivos(); 

        for(int i=0;i<datos.size();i++){
            System.out.println("Archivo: "+datos.get(i));
        }

        
        bbdd.insertarUsuario("usuario1", "clavep1", "password1");
        bbdd.insertarUsuario("usuario2", "clavep2", "password2");
        
        bbdd.insertarClave("archivo1", "clave1","admin",2);
        bbdd.insertarClave("archivo2", "clave2","admin",4);

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
        */
    }
    
    
}