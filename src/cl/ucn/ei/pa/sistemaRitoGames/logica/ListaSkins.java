package cl.ucn.ei.pa.sistemaRitoGames.logica;

import cl.ucn.ei.pa.sistemaRitoGames.dominio.Skin;

public class ListaSkins {
    private int cantidad;
    private Skin[] lista;
    private int capacidad;

    /**
     *
     * @param capacidad
     */
    public ListaSkins(int capacidad) {
        this.capacidad = capacidad;
        this.lista = new Skin[this.capacidad];
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
    public Skin[] getLista() {
        return lista;
    }

    /**
     *
     * @param lista
     */
    public void setLista(Skin[] lista) {
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
     * Se encargará de ingresar un objeto de tipo Skin, devolviendo true si el objeto se ingresó correctamente y falso si no.
     * @param skin
     * @return boolean
     */
    public boolean agregarSkin(Skin skin) {
        if (cantidad < capacidad) {
            lista[cantidad] = skin;
            cantidad++;
            return true;
        }

        return false;
    }

    /**
     * Se encargará de buscar un determinado skin, devolviendo el skin si el nombre fue encontrado y un null si no.
     * @param nombre
     * @return the skin
     */
    public Skin buscarSkin(String nombre) {
        int i;
        for (i = 0; i < cantidad && !lista[i].getNombre().equals(nombre); i++);

        if (i == cantidad) {
            return null;
        }

        return lista[i];
    }

    /**
     * Se encargará de devolver un skin en una determinada posición.
     * @param indice
     * @return the skin
     */
    public Skin getSkinI(int indice) {
        if (indice < capacidad) {
            return lista[indice];
        }

        return null;
    }
}