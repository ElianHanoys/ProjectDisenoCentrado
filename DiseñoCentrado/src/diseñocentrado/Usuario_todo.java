
package diseñocentrado;
import diseñocentrado.Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Usuario_todo {
    
    private Connection cn;
    
    
    public Usuario_todo()
    {
        Conexion con= new Conexion();
        cn=con.conectar();
    }
    
    public boolean insertarCliente(Usuario usuario)
     {
         String insertarSql= "INSERT INTO usuario (user_id,nombre,Apellido,correo,telefono) "
                  + "VALUES (  ?, ?, ?, ?)";
        try (PreparedStatement ps = cn.prepareStatement(insertarSql)) {
            ps.setInt(1, usuario.getUser_id());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido());
            ps.setString(4, usuario.getCorreo());
            ps.setString(5, usuario.getTelefono());
       
            int resultado = ps.executeUpdate();
            return resultado > 0;
        } catch (SQLException ex) {
            System.out.println("Error al insertar empleado: " + ex);
            return false;
        }
    }
    
    public boolean ActualizarCliente(Usuario usuario) {
         String actualizarSQL = "UPDATE usuario SET user_id=?, nombre=?, apellido=?, correo=?, Telefono=?";
        try {
            PreparedStatement ps = cn.prepareStatement(actualizarSQL);
            ps.setInt(1, usuario.getUser_id());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido());
            ps.setString(4, usuario.getCorreo());
            ps.setString(5, usuario.getTelefono());
            
           
            int resultado = ps.executeUpdate();
            return resultado > 0;
        } catch (SQLException ex) {
            System.out.println("Error al insertar empleado: " + ex);
            return false;
        }
    
}
     public boolean eliminarClientePorid(int user_id) {
        String eliminarSQL = "DELETE FROM usuario WHERE user_id=?";
        try {
            PreparedStatement ps = cn.prepareStatement(eliminarSQL);
            ps.setInt(1, user_id);
            int resultado = ps.executeUpdate();
            return resultado > 0;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar empleado: " + ex);
            return false;
        }
        
        
}

 public List<Usuario> obtenerCliente() {
        List<Usuario> listausuario = new ArrayList<>();
        String consultaSQL = "SELECT * FROM usuario";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(consultaSQL);
            while (rs.next()) {
                Usuario user = new Usuario();
                
                user.setUser_id(rs.getInt("user_id"));
                user.setNombre(rs.getString("nombre"));
                user.setApellido(rs.getString("Apellido"));
                user.setCorreo(rs.getString("correo"));
                user.setTelefono(rs.getString("telefono"));
                
               
                listausuario.add(user);
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener empleados: " + ex);
        }
        return listausuario;
}
 
 public Usuario obtenerClientePorID(int user_id) {
        String consultaSQL = "SELECT * FROM usuario WHERE user_id=?";
        try {
            PreparedStatement ps = cn.prepareStatement(consultaSQL);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario user = new Usuario();
                user.setUser_id(rs.getInt("user_id"));
                user.setNombre(rs.getString("nombre"));
                user.setApellido(rs.getString("Apellido"));
                user.setCorreo(rs.getString("correo"));
                user.setTelefono(rs.getString("telefono"));
                
                
                return user;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener empleado: " + ex);
            return null;
        }
}
}