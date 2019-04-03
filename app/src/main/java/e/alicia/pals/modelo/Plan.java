package e.alicia.pals.modelo;

import java.util.ArrayList;
import java.util.Date;

public class Plan {
    private String nombre;
    private String informacion;
    private String fecha;
    private String maxusuarios;
    private String lugar;
    private String usuariocreador;
    private ArrayList<String> usuarios;

    public Plan() {

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMaximo() {
        return maxusuarios;
    }

    public void setMaximo(String maximo) {
        this.maxusuarios = maximo;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }


    public String getCreador() {
        return usuariocreador;
    }

    public void setCreador(String creador) {
        this.usuariocreador = creador;
    }

    public ArrayList<String> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<String> usuarios) {
        this.usuarios = usuarios;
    }
}
