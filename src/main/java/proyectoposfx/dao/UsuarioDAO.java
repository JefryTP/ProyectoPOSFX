package proyectoposfx.dao;

import proyectoposfx.bd.ConexionBD;
import proyectoposfx.modelos.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO extends ConexionBD {

    public Usuario login(String clave) throws Exception {
        String sql = "SELECT u.id, u.dni, u.nombre, u.telefono, u.clave, "
                   + "u.id_rol, r.cargo "
                   + "FROM usuario u "
                   + "JOIN rol r ON u.id_rol = r.id "
                   + "WHERE u.clave = ? AND u.activo = TRUE";
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, clave);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setDni(rs.getString("dni"));
                u.setNombre(rs.getString("nombre"));
                u.setTelefono(rs.getString("telefono"));
                u.setClave(rs.getString("clave"));
                u.setIdRol(rs.getInt("id_rol"));
                u.setCargoRol(rs.getString("cargo"));
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

