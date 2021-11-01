package cl.ucn.ei.pa.sistemaRitoGames.logica;

public enum Calidad {
    M(3250),
    D(2750),
    L(1820),
    E(1350),
    N(975);

    private final int precio;

    /**
     *
     * @param precio
     */
    Calidad(int precio) {
        this.precio = precio;
    }

    /**
     *
     * @return the precio
     */
    public int getPrecio() {
        return precio;
    }
}