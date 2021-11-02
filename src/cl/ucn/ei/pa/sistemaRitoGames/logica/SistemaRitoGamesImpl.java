package cl.ucn.ei.pa.sistemaRitoGames.logica;

import cl.ucn.ei.pa.sistemaRitoGames.dominio.Cuenta;
import cl.ucn.ei.pa.sistemaRitoGames.dominio.Personaje;
import cl.ucn.ei.pa.sistemaRitoGames.dominio.Skin;

public class SistemaRitoGamesImpl implements SistemaRitoGames {
    private ListaPersonajes listaPersonajes;
    private ListaSkins listaSkins;
    private ListaCuentas listaCuentas;

    public SistemaRitoGamesImpl() {
        listaPersonajes = new ListaPersonajes(999);
        listaSkins = new ListaSkins(999);
        listaCuentas = new ListaCuentas(999);
    }

    @Override
    public void ingresarPrecioPersonajes(int precio) {
        Personaje.setPrecio(precio);
    }

    @Override
    public boolean ingresarPersonaje(String nombre, String rol) {
        Personaje personaje = new Personaje(nombre, Rol.valueOf(rol));
        return listaPersonajes.agregarPersonaje(personaje);
    }

    @Override
    public boolean ingresarSkinPersonaje(String nombreSkin, String calidad) {
        int indicePersonaje = listaPersonajes.getCantidad() - 1;
        Personaje personaje = listaPersonajes.getPersonajeI(indicePersonaje);
        ListaSkins listaSkinsPersonaje = personaje.getListaSkins();
        Skin skin = new Skin(nombreSkin, Calidad.valueOf(calidad));
        return listaSkinsPersonaje.agregarSkin(skin);
    }

    @Override
    public boolean ingresarSkinListaGeneral(String nombreSkin) {
        int indicePersonaje = listaPersonajes.getCantidad() - 1;
        Personaje personaje = listaPersonajes.getPersonajeI(indicePersonaje);
        Skin skin = personaje.getListaSkins().buscarSkin(nombreSkin);
        return listaSkins.agregarSkin(skin);
    }

    @Override
    public boolean ingresarCuenta(String nombre, String contraseña, String nick, int nivel, int rp, String region) {
        Cuenta cuenta = new Cuenta(nombre, contraseña, nick, nivel, rp, Region.valueOf(region));
        return listaCuentas.agregarCuenta(cuenta);
    }

    @Override
    public boolean asociarPersonajeCuenta(String nombrePersonaje, String nombreCuenta) {
        int indiceCuenta = listaCuentas.getCantidad() - 1;
        Cuenta cuenta = listaCuentas.getCuentaI(indiceCuenta);
        ListaPersonajes listaPersonajesCuenta = cuenta.getListaPersonajes();
        Rol rol = listaPersonajes.buscarPersonaje(nombrePersonaje).getRol();
        Personaje personaje = new Personaje(nombrePersonaje, rol);
        return listaPersonajesCuenta.agregarPersonaje(personaje);
    }

    @Override
    public boolean asociarSkinPersonaje(String nombreSkin, String nombreCuenta) {
        int indiceCuenta = listaCuentas.getCantidad() - 1;
        Cuenta cuenta = listaCuentas.getCuentaI(indiceCuenta);
        ListaPersonajes listaPersonajesCuenta = cuenta.getListaPersonajes();
        int indicePersonaje = listaPersonajesCuenta.getCantidad() - 1;
        Personaje personaje = listaPersonajesCuenta.getPersonajeI(indicePersonaje);
        Skin skin = listaSkins.buscarSkin(nombreSkin);
        ListaSkins listaSkinsPersonajeCuenta = personaje.getListaSkins();
        return listaSkinsPersonajeCuenta.agregarSkin(skin);
    }

    @Override
    public boolean asociarSkinCuenta(String nombreSkin, String nombreCuenta) {
        int indiceCuenta = listaCuentas.getCantidad() - 1;
        Cuenta cuenta = listaCuentas.getCuentaI(indiceCuenta);
        ListaSkins listaSkinsCuenta =  cuenta.getListaSkins();
        Skin skin = listaSkins.buscarSkin(nombreSkin);
        return listaSkinsCuenta.agregarSkin(skin);
    }

    @Override
    public void ingresarRecaudacion(String nombrePersonaje, int recaudacion) {
        Personaje personaje = listaPersonajes.buscarPersonaje(nombrePersonaje);
        personaje.setRecaudacion(recaudacion);
    }

    @Override
    public boolean isUsuarioRegistrado(String nombreCuenta) {
        Cuenta cuenta = listaCuentas.buscarCuenta(nombreCuenta);
        return cuenta != null;
    }

    @Override
    public boolean isContraseñaCorrecta(String nombreCuenta, String contraseña) {
        Cuenta cuenta = listaCuentas.buscarCuenta(nombreCuenta);
        return cuenta.getContraseña().equals(contraseña);
    }

    @Override
    public String obtenerSkinsDisponiblesPersonaje(String nombreCuenta, String nombrePersonaje) {
        StringBuilder skinsDisponiblesPersonaje = new StringBuilder();
        Cuenta cuenta = listaCuentas.buscarCuenta(nombreCuenta);
        Personaje personajeCuenta = cuenta.getListaPersonajes().buscarPersonaje(nombrePersonaje);
        if (personajeCuenta == null) {
            throw new NullPointerException("El personaje no existe en la cuenta.");
        }

        Personaje personajeJuego = listaPersonajes.buscarPersonaje(nombrePersonaje);
        ListaSkins listaSkinsPersonajeCuenta = personajeCuenta.getListaSkins();
        ListaSkins listaSkinsPersonajeJuego = personajeJuego.getListaSkins();

        for (int i = 0; i < listaSkinsPersonajeJuego.getCantidad(); i++) {
            String nombreSkin = listaSkinsPersonajeJuego.getSkinI(i).getNombre();
            if (listaSkinsPersonajeCuenta.buscarSkin(nombreSkin) == null) {
                skinsDisponiblesPersonaje.append(nombreSkin).append("\n");
            }
        }

        return skinsDisponiblesPersonaje.toString();
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
