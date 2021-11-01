package cl.ucn.ei.pa.sistemaRitoGames.dominio;

import cl.ucn.ei.pa.sistemaRitoGames.logica.ListaSkins;
import cl.ucn.ei.pa.sistemaRitoGames.logica.Rol;

public class Personaje {
    private static int precio;
    private String nombre;
    private Rol rol;
    private int recaudacion;
    private ListaSkins listaSkins;

    /**
     *
     * @param nombre
     * @param rol
     */
    public Personaje(String nombre, Rol rol) {
        this.nombre = nombre;
        this.rol = rol;
    }

    /**
     *
     * @return the precio
     */
    public static int getPrecio() {
        return precio;
    }

    /**
     *
     * @param precio
     */
    public static void setPrecio(int precio) {
        Personaje.precio = precio;
    }

    /**
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return the rol
     */
    public Rol getRol() {
        return rol;
    }

    /**
     *
     * @param rol
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    /**
     *
     * @return the recaudacion
     */
    public int getRecaudacion() {
        return recaudacion;
    }

    /**
     *
     * @param recaudacion
     */
    public void setRecaudacion(int recaudacion) {
        this.recaudacion = recaudacion;
    }

    /**
     *
     * @return the listaSkins
     */
    public ListaSkins getListaSkins() {
        return listaSkins;
    }

    /**
     *
     * @param listaSkins
     */
    public void setListaSkins(ListaSkins listaSkins) {
        this.listaSkins = listaSkins;
    }
}