package proyectoposfx.dao;
import proyectoposfx.bd.ConexionBD;
import proyectoposfx.modelos.Producto;
 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class ProductoDAO extends ConexionBD{
        public Producto buscarPorId(int id) throws Exception {
        String sql = "SELECT id, descripcion, id_categoria, precio, activo "
                   + "FROM producto WHERE id = ? AND activo = TRUE";
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setPrecio(rs.getDouble("precio"));
                p.setActivo(rs.getBoolean("activo"));
                return p;
            }
        } finally {
            cerrar();
        }
        return null;
    }
}
