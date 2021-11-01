package cl.ucn.ei.pa.sistemaRitoGames.logica;

public class SistemaRitoGamesImpl implements SistemaRitoGames {
    @Override
    public void ingresarPrecioPersonajes(int precio) {

    }

    @Override
    public boolean ingresarPersonaje(String nombre, String rool) {
        return false;
    }

    @Override
    public boolean ingresarSkinPersonaje(String nombreSkin, String calidad) {
        return false;
    }

    @Override
    public boolean ingresarSkin(String nombreSkin, String calidad) {
        return false;
    }

    @Override
    public boolean ingresarCuenta(String nombre, String contraseña, String nick, int nivel, int rp, String region) {
        return false;
    }

    @Override
    public void asociarPersonajeCuenta(String nombrePersonaje, String nombreCuenta) {

    }

    @Override
    public void asociarSkinPersonaje(String nombreSkin, String nombreCuenta) {

    }

    @Override
    public void asociarSkinCuenta(String nombreSkin, String nombreCuenta) {

    }

    @Override
    public void ingresarRecaudacion(String nombrePersonaje, int recaudacion) {

    }

    @Override
    public boolean isUsuarioRegistrado(String nombreCuenta) {
        return false;
    }

    @Override
    public boolean isContraseñaCorrecta(String nombreCuenta, String contraseña) {
        return false;
    }

    @Override
    public String obtenerSkinsDisponiblesPersonaje(String nombreCuenta, String nombrePersonaje) {
        return null;
    }

    @Override
    public boolean comprarSkin(String nombreCuenta, String nombrePersonaje, String nombreSkin) {
        return false;
    }

    @Override
    public boolean comprarPersonaje(String nombreCuenta, String nombrePersonaje) {
        return false;
    }

    @Override
    public String obtenerSkinsDisponibles(String nombreCuenta) {
        return null;
    }

    @Override
    public String obtenerInventario(String nombreCuenta) {
        return null;
    }

    @Override
    public void recargarRp(String nombreCuenta, int cantidadRp) {

    }

    @Override
    public String obtenerDatosCuenta(String nombreCuenta) {
        return null;
    }

    @Override
    public void cambiarContraseña(String nombreCuenta, String nuevaContraseña) {

    }

    @Override
    public String obtenerRecaudacionPorRol() {
        return null;
    }

    @Override
    public String obtenerRecaudacionPorRegion() {
        return null;
    }

    @Override
    public String obtenerRecaudacionPorPersonaje() {
        return null;
    }

    @Override
    public String obtenerCantidadPersonajesPorRol() {
        return null;
    }

    @Override
    public void bloquearJugador(String nombreCuenta) {

    }

    @Override
    public String obtenerDatosCuentasOrdenadasPorNivel() {
        return null;
    }

    @Override
    public String obtenerDatosPersonajes() {
        return null;
    }

    @Override
    public String obtenerDatosCuentas() {
        return null;
    }

    @Override
    public String obtenerDatosEstadistica() {
        return null;
    }
}
