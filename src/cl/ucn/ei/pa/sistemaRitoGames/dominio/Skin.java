package cl.ucn.ei.pa.sistemaRitoGames.dominio;

import cl.ucn.ei.pa.sistemaRitoGames.logica.Calidad;

public class Skin {
    private String nombre;
    private Calidad calidad;

    /**
     *
     * @param nombre
     * @param calidad
     */
    public Skin(String nombre, Calidad calidad) {
        this.nombre = nombre;
        this.calidad = calidad;
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
     * @return the calidad
     */
    public Calidad getCalidad() {
        return calidad;
    }

    /**
     *
     * @param calidad
     */
    public void setCalidad(Calidad calidad) {
        this.calidad = calidad;
    }
}