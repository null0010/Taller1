package cl.ucn.ei.pa.sistemaRitoGames.logica;

import cl.ucn.ei.pa.sistemaRitoGames.dominio.Personaje;

public class ListaPersonajes {
    private int cantidad;
    private Personaje[] lista;
    private int capacidad;

    /**
     *
     * @param capacidad
     */
    public ListaPersonajes(int capacidad) {
        this.capacidad = capacidad;
        this.lista = new Personaje[this.capacidad];
        this.cantidad = 0;
    }

    /**
     *
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     *
     * @param cantidad
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     *
     * @return the lista
     */
    public Personaje[] getLista() {
        return lista;
    }

    /**
     *
     * @param lista
     */
    public void setLista(Personaje[] lista) {
        this.lista = lista;
    }

    /**
     *
     * @return the capacidad
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     *
     * @param capacidad
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * Se encargará de ingresar un objeto de tipo Personaje, devolviendo true si el objeto se ingresó correctamente y falso si no.
     * @param personaje
     * @return boolean
     */
    public boolean agregarPersonaje(Personaje personaje) {
        if (cantidad < capacidad) {
            lista[cantidad] = personaje;
            cantidad++;
            return true;
        }

        return false;
    }

    /**
     * Se encargará de buscar un determinado personaje, devolviendo el personaje si el nombre fue encontrado y un null si no.
     * @param nombre
     * @return the personaje
     */
    public Personaje buscarPersonaje(String nombre) {
        int i;
        for (i = 0; i < cantidad && !lista[i].getNombre().equals(nombre); i++);

        if (i == cantidad) {
            return null;
        }

        return lista[i];
    }

    /**
     * Se encargará de devolver un personaje en una determinada posición.
     * @param indice
     * @return the personaje
     */
    public Personaje getPersonajeI(int indice) {
        if (indice < capacidad) {
            return lista[indice];
        }

        return null;
    }
}