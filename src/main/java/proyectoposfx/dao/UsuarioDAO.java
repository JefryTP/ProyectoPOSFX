package proyectoposfx.dao;

import proyectoposfx.bd.ConexionBD;
import proyectoposfx.modelos.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends ConexionBD {
    
    // Listar todos los usuarios
    public List<Usuario> listar() throws Exception {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setUsuario(rs.getString("usuario"));
                u.setContrasena(rs.getString("contrasena"));
                u.setRol(rs.getString("rol"));
                lista.add(u);
            }
        } catch (Exception e) {
            System.out.println("Error al listar: " + e.getMessage());
        } finally {
            cerrar();
        }
        return lista;
    }
    
    // Buscar por usuario y contraseña (para el login)
    public Usuario login(String usuario, String contrasena) throws Exception {
        String sql = "SELECT * FROM usuarios WHERE usuario=? AND contrasena=?";
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, contrasena);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setUsuario(rs.getString("usuario"));
                u.setContrasena(rs.getString("contrasena"));
                u.setRol(rs.getString("rol"));
                return u;
            }
        } catch (Exception e) {
            System.out.println("Error en login: " + e.getMessage());
        } finally {
            cerrar();
        }
        return null;
    }
}