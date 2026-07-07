package proyectoposfx.modelos;

public class Sesion {
    private static Usuario usuarioActual;
 
    private Sesion() {}
 
    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }
 
    public static void setUsuarioActual(Usuario usuario) {
        usuarioActual = usuario;
    }
}
