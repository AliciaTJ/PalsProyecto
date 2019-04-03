package e.alicia.pals.modelo;
import java.util.List;

public class Usuario {
    private String nombre;
    private String codigo;
    private String email;
    private List<Plan> planes;


    public Usuario(){

    }
    public Usuario(String nombre, String codigo, String email, List planes) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.email = email;
        this.planes=planes;
    }

    public List<Plan> getPlanes() {
        return planes;
    }

    public void setPlanes(List<Plan> planes) {
        this.planes = planes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
