package cl.ucn.ei.pa.sistemaRitoGames.logica;

import cl.ucn.ei.pa.sistemaRitoGames.dominio.Cuenta;

public class ListaCuentas {
    private int cantidad;
    private Cuenta[] lista;
    private int capacidad;

    /**
     *
     * @param capacidad
     */
    public ListaCuentas(int capacidad) {
        this.capacidad = capacidad;
        this.lista = new Cuenta[this.capacidad];
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
    public Cuenta[] getLista() {
        return lista;
    }

    /**
     *
     * @param lista
     */
    public void setLista(Cuenta[] lista) {
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
     * Se encargará de ingresar un objeto de tipo Cuenta, devolviendo true si el objeto se ingresó correctamente y falso si no.
     * @param cuenta
     * @return boolean
     */
    public boolean agregarCuenta(Cuenta cuenta) {
        if (cantidad < capacidad) {
            lista[cantidad] = cuenta;
            cantidad++;
            return true;
        }

        return false;
    }

    /**
     * Se encargará de buscar una determinada cuenta, devolviendo la cuenta si el nombre fue encontrado y un null si no.
     * @param nombre
     * @return the cuenta
     */
    public Cuenta buscarCuenta(String nombre) {
        int i;
        for (i = 0; i < cantidad && !lista[i].getNombre().equals(nombre); i++);

        if (i == cantidad) {
            return null;
        }

        return lista[i];
    }

    /**
     * Se encargará de ordenar la lista por nivel de mayor a menor.
     */
    public void ordenarPorNivel() {
        for (int i = 0; i < cantidad - 1; i++) {
            for (int j = i + 1; j < cantidad; j++) {
                if (lista[i].getNivel() < lista[j].getNivel()) {
                    Cuenta cuentaAux = lista[i];
                    lista[i] = lista[j];
                    lista[j] = cuentaAux;
                }
            }
        }
    }

    /**
     * Se encargará de devolver una cuenta en una determinada posición.
     * @param indice
     * @return the cuenta
     */
    public Cuenta getCuentaI(int indice) {
        if (indice < capacidad) {
            return lista[indice];
        }

        return null;
    }
}