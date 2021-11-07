package cl.ucn.ei.pa.sistemaRitoGames.logica;

public interface SistemaRitoGames {

    /**
     *
     * @param precio
     */
    public void ingresarPrecioPersonajes(int precio);

    public boolean ingresarPersonaje(String nombre, String rool);

    public boolean ingresarSkinPersonaje(String nombreSkin, String calidad);

    public boolean ingresarSkinListaGeneral();

    public boolean ingresarCuenta(String nombre, String contraseña, String nick, int nivel, int rp, String region);

    public boolean asociarPersonajeCuenta(String nombrePersonaje, String nombreCuenta);

    public boolean asociarSkinPersonajeCuenta(String nombreSkin);

    public boolean asociarSkinCuenta(String nombreSkin);

    public void ingresarRecaudacion(String nombrePersonaje, int recaudacion);

    public boolean isUsuarioRegistrado(String nombreCuenta);

    public boolean isContraseñaCorrecta(String nombreCuenta, String contraseña);

    public String obtenerSkinsDisponiblesPersonaje(String nombreCuenta, String nombrePersonaje);

    public String obtenerPersonajesDisponibles(String nombreCuenta);

    public boolean comprarSkin(String nombreCuenta, String nombrePersonaje, String nombreSkin);

    public boolean comprarPersonaje(String nombreCuenta, String nombrePersonaje);

    public String obtenerSkinsDisponibles(String nombreCuenta);

    public String obtenerInventario(String nombreCuenta);

    public void recargarRp(String nombreCuenta, int cantidadRp);

    public String obtenerDatosCuenta(String nombreCuenta);

    public void cambiarContraseña(String nombreCuenta, String nuevaContraseña);

    public String obtenerRecaudacionPorRol();

    public String obtenerRecaudacionPorRegion();

    public String obtenerRecaudacionPorPersonaje();

    public String obtenerCantidadPersonajesPorRol();

    public void bloquearJugador(String nombreCuenta);

    public boolean isJugadorBloqueado(String nombreCuenta); //agregar a diagrama y contrato

    public String obtenerDatosCuentasOrdenadasPorNivel();

    public String obtenerDatosPersonajes();

    public String obtenerDatosCuentas();

    public String obtenerDatosEstadistica();
}