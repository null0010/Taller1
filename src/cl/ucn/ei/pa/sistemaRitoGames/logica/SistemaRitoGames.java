package cl.ucn.ei.pa.sistemaRitoGames.logica;

public interface SistemaRitoGames {

    /**
     * Ingresa el precio que tendrán todos los personajes
     * @param precio Precio que tendrán todos los personajes
     */
    public void ingresarPrecioPersonajes(int precio);

    /**
     * Ingresa un personaje a la lista de personajes
     * @param nombre Nombre del personaje
     * @param rol Rol del personaje
     * @return boolean
     */
    public boolean ingresarPersonaje(String nombre, String rol);

    /**
     * Ingresa un skin a la lista individual de skins de un personaje
     * @param nombreSkin Nombre del skin de un personaje
     * @param calidad Calidad del skin
     * @return boolean
     */
    public boolean ingresarSkinPersonaje(String nombreSkin, String calidad);

    /**
     * Asocia un skin a un determinado personaje
     * @param nombrePersonaje Nombre del personaje
     * @param nombreSkin Nombre del skin
     * @param calidad Calidad del skin
     * @return boolean
     */
    public boolean asociarSkinPersonaje(String nombrePersonaje, String nombreSkin, String calidad);

    /**
     * Asocia un skin a la lista general de skins
     * @param nombrePersonaje Nombre del personaje
     * @param nombreSkin Nombre del skin
     * @return boolean
     */
    public boolean asociarSkinListaGeneral(String nombrePersonaje, String nombreSkin);


    /**
     * Ingresa un skin a la lista general de skins.
     * @return boolean
     */
    public boolean ingresarSkinListaGeneral();

    /**
     * Ingresa una cuenta a la lista general de cuentas
     * @param nombre Nombre de la cuenta
     * @param contrasena contrasena de la cuenta
     * @param nick Nick de la cuenta
     * @param nivel Nivel de la cuenta
     * @param rp Rp de la cuenta
     * @param region Region de la cuenta
     * @return boolean
     */
    public boolean ingresarCuenta(String nombre, String contrasena, String nick, int nivel, int rp, String region);


    /**
     * Asocia el personaje a una cuenta
     * @param nombrePersonaje Nombre del personaje
     * @param nombreCuenta Nombre de la cuenta
     * @return boolean
     */
    public boolean asociarPersonajeCuenta(String nombrePersonaje, String nombreCuenta);

    /**
     * Ingresa un skin a la lista individual de skins de un personaje de una cuenta
     * @param nombreSkin Nombre del skin
     * @return boolean
     */
    public boolean ingresarSkinPersonajeCuenta(String nombreSkin);

    /**
     * Ingresa un skin a la lista individual de skins de una cuenta
     * @param nombreSkin Nombre del skin
     * @return boolean
     */
    public boolean ingresarSkinCuenta(String nombreSkin);

    /**
     * Ingresa la recaudacion de un personaje
     * @param nombrePersonaje Nombre del personaje
     * @param recaudacion Recaudacion del personaje
     */
    public void ingresarRecaudacion(String nombrePersonaje, int recaudacion);

    /**
     * Comprueba si un usuario está registrado en el sistema
     * @param nombreCuenta Nombre de la cuenta
     * @return boolean
     */
    public boolean isUsuarioRegistrado(String nombreCuenta);

    /**
     *  Comprueba si la contrasena ingresada por el jugador es correcta
     * @param nombreCuenta Nombre de la cuenta
     * @param contrasena contrasena de la cuenta
     * @return boolean
     */
    public boolean isContrasenaCorrecta(String nombreCuenta, String contrasena);

    /**
     * Devuelve los skins que el jugador no tiene en su inventario
     * @param nombreCuenta Nombre de la cuenta
     * @param nombrePersonaje Nombre del personaje
     * @return String
     */
    public String obtenerSkinsDisponiblesPersonaje(String nombreCuenta, String nombrePersonaje);

    /**
     * Devuelve los personajes que el jugador no tiene en su inventario
     * @param nombreCuenta Nombre de la cuenta
     * @return String
     */
    public String obtenerPersonajesDisponibles(String nombreCuenta);

    /**
     * Será responsable de comprar un skin determinado por el jugador
     * @param nombreCuenta Nombre de la cuenta
     * @param nombrePersonaje Nombre del personaje
     * @param nombreSkin Nombre del skin
     * @return boolean
     */
    public boolean comprarSkin(String nombreCuenta, String nombrePersonaje, String nombreSkin);

    /**
     * Será responsable de comprar un personaje determinado por el jugador
     * @param nombreCuenta Nombre de la cuenta
     * @param nombrePersonaje Nombre del personaje
     * @return boolean
     */
    public boolean comprarPersonaje(String nombreCuenta, String nombrePersonaje);

    /**
     * Devuelve los skins que el jugador no tiene en su inventario
     * @param nombreCuenta Nombre de la cuenta
     * @return String
     */
    public String obtenerSkinsDisponibles(String nombreCuenta);

    /**
     * Devuelve el inventario de jugador
     * @param nombreCuenta Nombre de la cuenta
     * @return String
     */
    public String obtenerInventario(String nombreCuenta);

    /**
     *  Será responsable de recargar el rp de una cuenta
     * @param nombreCuenta Nombre de la cuenta
     * @param cantidadRp Cantidad de rp
     */
    public void recargarRp(String nombreCuenta, int cantidadRp);

    /**
     * Devuelve los datos de una cuenta
     * @param nombreCuenta Nombre de la cuenta
     * @return String
     */
    public String obtenerDatosCuenta(String nombreCuenta);

    /**
     * Será responsable de cambiar la contrasena de una cuenta
     * @param nombreCuenta Nombre de la cuenta
     * @param nuevacontrasena Nueva contrasena
     */
    public void cambiarContrasena(String nombreCuenta, String nuevacontrasena);

    /**
     * Devuelve las recaudaciones por rol
     * @return String
     */
    public String obtenerRecaudacionPorRol();

    /**
     * Devuelve las recaudaciones por region
     * @return String
     */
    public String obtenerRecaudacionPorRegion();

    /**
     * Devuelve las recaudaciones de cada uno de los personajes
     * @return String
     */
    public String obtenerRecaudacionPorPersonaje();

    /**
     * Devuelve la cantidad de personajes por rol
     * @return String
     */
    public String obtenerCantidadPersonajesPorRol();

    /**
     * Será responsable de bloquear una cuenta
     * @param nombreCuenta Nombre de la cuenta
     */
    public void bloquearJugador(String nombreCuenta);

    /**
     * Comprueba si un jugador se encuentra bloqueado
     * @param nombreCuenta
     * @return boolean
     */
    public boolean isJugadorBloqueado(String nombreCuenta);

    /**
     * Devuelve los datos de cada una de las cuentas ordenadas por nivel
     * @return String
     */
    public String obtenerDatosCuentasOrdenadasPorNivel();

    /**
     * Devuelve los datos actualizados de los personajes
     * @return String
     */
    public String obtenerDatosPersonajes();

    /**
     * Devuelve los datos actualizados de las cuentas
     * @return String
     */
    public String obtenerDatosCuentas();

    /**
     * Devuelve los datos actualizados de las estadisticas
     * @return String
     */
    public String obtenerDatosEstadisticas();
}