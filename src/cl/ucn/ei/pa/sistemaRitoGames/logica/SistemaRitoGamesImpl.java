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
        Rol rolEnum = Rol.valueOf(rol);
        Personaje personaje = new Personaje(nombre, rolEnum);
        return listaPersonajes.agregarPersonaje(personaje);
    }

    @Override
    public boolean ingresarSkinPersonaje(String nombreSkin, String calidad) {
        int indicePersonaje = listaPersonajes.getCantidad() - 1;
        Personaje personaje = listaPersonajes.getPersonajeI(indicePersonaje);
        ListaSkins listaSkinsPersonaje = personaje.getListaSkins();
        Calidad calidadEnum = Calidad.valueOf(calidad);
        Skin skin = new Skin(nombreSkin, calidadEnum);
        return listaSkinsPersonaje.agregarSkin(skin);
    }

    @Override
    public boolean asociarSkinPersonaje(String nombrePersonaje, String nombreSkin, String calidad) {
        Personaje personaje = listaPersonajes.buscarPersonaje(nombrePersonaje);
        if (personaje == null) {
            throw new NullPointerException("El personaje no existe.");
        }

        Calidad calidadEnum = Calidad.valueOf(calidad);
        Skin skin = new Skin(nombreSkin, calidadEnum);


        return personaje.getListaSkins().agregarSkin(skin);
    }

    @Override
    public boolean asociarSkinListaGeneral(String nombrePersonaje, String nombreSkin) {
        Personaje personaje = listaPersonajes.buscarPersonaje(nombrePersonaje);
        ListaSkins listaSkinsPersonaje = personaje.getListaSkins();
        Skin skin = listaSkinsPersonaje.getSkinI(listaSkinsPersonaje.getCantidad() - 1);
        return listaSkins.agregarSkin(skin);
    }

    @Override
    public boolean ingresarSkinListaGeneral() {
        int indicePersonaje = listaPersonajes.getCantidad() - 1;
        Personaje personaje = listaPersonajes.getPersonajeI(indicePersonaje);
        int indiceSkin = personaje.getListaSkins().getCantidad() - 1;
        Skin skin = personaje.getListaSkins().getSkinI(indiceSkin);
        return listaSkins.agregarSkin(skin);
    }

    @Override
    public boolean ingresarCuenta(String nombre, String contrasena, String nick, int nivel, int rp, String region) {
        Region regionEnum = Region.valueOf(region);
        Cuenta cuenta = new Cuenta(nombre, contrasena, nick, nivel, rp, regionEnum);
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
    public boolean ingresarSkinPersonajeCuenta(String nombreSkin) {
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
    public boolean ingresarSkinCuenta(String nombreSkin) {
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
    public boolean isContrasenaCorrecta(String nombreCuenta, String contrasena) {
        Cuenta cuenta = listaCuentas.buscarCuenta(nombreCuenta);
        return cuenta.getContrasena().equals(contrasena);
    }

    @Override
    public String obtenerSkinsDisponiblesPersonaje(String nombreCuenta, String nombrePersonaje) {
        String salida = "Skins disponibles: \n";
        Cuenta cuenta = listaCuentas.buscarCuenta(nombreCuenta);
        Personaje personajeCuenta = cuenta.getListaPersonajes().buscarPersonaje(nombrePersonaje);
        if (listaPersonajes.buscarPersonaje(nombrePersonaje) == null) {
          throw new NullPointerException("El personaje no existe en el juego.");
        }
        else {
            if (personajeCuenta == null) {
                throw new NullPointerException("El personaje no existe en la cuenta.");
            }
        }


        Personaje personajeJuego = listaPersonajes.buscarPersonaje(nombrePersonaje);
        ListaSkins listaSkinsPersonajeCuenta = personajeCuenta.getListaSkins();
        ListaSkins listaSkinsPersonajeJuego = personajeJuego.getListaSkins();

        for (int i = 0; i < listaSkinsPersonajeJuego.getCantidad(); i++) {
            String nombreSkin = listaSkinsPersonajeJuego.getSkinI(i).getNombre();
            if (listaSkinsPersonajeCuenta.buscarSkin(nombreSkin) == null) {
                salida += nombreSkin
                       + "\n";
            }
        }

        return salida;
    }


    public String obtenerPersonajesDisponibles(String nombreCuenta) {
        Cuenta cuenta = listaCuentas.buscarCuenta(nombreCuenta);
        ListaPersonajes listaPersonajesCuenta = cuenta.getListaPersonajes();
        String salida = "Personajes disponibles: \n";
        for (int i = 0; i < listaPersonajes.getCantidad(); i++) {
            String nombrePersonaje = listaPersonajes.getPersonajeI(i).getNombre();
            if (listaPersonajesCuenta.buscarPersonaje(nombrePersonaje) == null) {
                salida += nombrePersonaje + "\n";
            }
        }

        return salida;
    }


    @Override
    public boolean comprarSkin(String nombreCuenta, String nombrePersonaje, String nombreSkin) {
        Skin skin = listaSkins.buscarSkin(nombreSkin);
        if (skin == null) {
            throw new NullPointerException("El skin no existe en la tienda.");
        }

        Cuenta cuenta = listaCuentas.buscarCuenta(nombreCuenta);
        Personaje personajeCuenta = cuenta.getListaPersonajes().buscarPersonaje(nombrePersonaje);
        ListaSkins listaSkinsPersonajeCuenta = personajeCuenta.getListaSkins();
        ListaSkins listaSkinsCuenta = cuenta.getListaSkins();
        Personaje personajeJuego = listaPersonajes.buscarPersonaje(nombrePersonaje);
        int montoActualizado = cuenta.getRp() - skin.getCalidad().getPrecio();
        if (montoActualizado >= 0) {
            cuenta.setRecaudacion(cuenta.getRecaudacion() + skin.getCalidad().getPrecio());
            personajeJuego.setRecaudacion(personajeJuego.getRecaudacion() + skin.getCalidad().getPrecio());
            cuenta.setRp(montoActualizado);
            listaSkinsPersonajeCuenta.agregarSkin(skin);
            listaSkinsCuenta.agregarSkin(skin);
            return true;
        }

        return false;
    }

    @Override
    public boolean comprarPersonaje(String nombreCuenta, String nombrePersonaje) {
        Personaje personaje = listaPersonajes.buscarPersonaje(nombrePersonaje);
        if (personaje == null) {
            throw new NullPointerException("El personaje no existe en la tienda.");
        }

        Cuenta cuenta = listaCuentas.buscarCuenta(nombreCuenta);
        ListaPersonajes listaPersonajesCuenta = cuenta.getListaPersonajes();
        int montoActualizado = cuenta.getRp() - Personaje.getPrecio();
        if (montoActualizado >= 0) {
            cuenta.setRecaudacion(cuenta.getRecaudacion() + Personaje.getPrecio());
            personaje.setRecaudacion(personaje.getRecaudacion() + Personaje.getPrecio());
            Personaje personajeCompra = new Personaje(personaje.getNombre(), personaje.getRol());
            listaPersonajesCuenta.agregarPersonaje(personajeCompra);
            cuenta.setNivel(cuenta.getNivel() + 1);
            return true;
        }


        return false;
    }

    @Override
    public String obtenerSkinsDisponibles(String nombreCuenta) {
        String salida = "";
        Cuenta cuenta = listaCuentas.buscarCuenta(nombreCuenta);
        ListaSkins listaSkinsCuenta = cuenta.getListaSkins();
        for (int i = 0; i < listaSkins.getCantidad(); i++) {
            String nombreSkin = listaSkins.getSkinI(i).getNombre();
            if (listaSkinsCuenta.buscarSkin(nombreSkin) == null) {
                salida += nombreSkin + "\n";
            }
        }

        return salida;
    }

    @Override
    public String obtenerInventario(String nombreCuenta) {
        Cuenta cuenta = listaCuentas.buscarCuenta(nombreCuenta);
        ListaPersonajes listaPersonajesCuenta = cuenta.getListaPersonajes();
        String salida = "";
        for (int i = 0; i < listaPersonajesCuenta.getCantidad(); i++) {
            Personaje personajeCuenta = listaPersonajesCuenta.getPersonajeI(i);
            ListaSkins listaSkinsPersonajesCuenta = personajeCuenta.getListaSkins();
            salida += personajeCuenta.getNombre() + "\n";
            for (int j = 0; j < listaSkinsPersonajesCuenta.getCantidad(); j++) {
                Skin skin = listaSkinsPersonajesCuenta.getSkinI(j);
                String nombreSkin = skin.getNombre();
                salida += nombreSkin + "\n";
            }

            salida += "\n";
        }

        return salida;
    }

    @Override
    public void recargarRp(String nombreCuenta, int cantidadRp) {
        Cuenta cuenta = listaCuentas.buscarCuenta(nombreCuenta);
        cuenta.setRp(cuenta.getRp() + cantidadRp);
    }

    @Override
    public String obtenerDatosCuenta(String nombreCuenta) {
        Cuenta cuenta = listaCuentas.buscarCuenta(nombreCuenta);
        String salida = "";
        salida += cuenta.getNombre() + "\n"
                     + cuenta.getNick() + "\n";

        int longitudContrasena = cuenta.getContrasena().length();
        String contrasenaSemiCensurada = "";
        for (int i = 0; i < longitudContrasena - 3; i++) {
            contrasenaSemiCensurada += "*";
        }

        contrasenaSemiCensurada += cuenta.getContrasena().substring(longitudContrasena - 3, longitudContrasena);
        salida += contrasenaSemiCensurada + "\n";

        return salida;
    }

    @Override
    public void cambiarContrasena(String nombreCuenta, String nuevaContrasena) {
        Cuenta cuenta = listaCuentas.buscarCuenta(nombreCuenta);
        cuenta.setContrasena(nuevaContrasena);
    }

    @Override
    public String obtenerRecaudacionPorRol() {
        String salida = "";
        for (Rol rol : Rol.values()) {
            salida += rol.toString() + "\n";
            int recaudacionPorRol = 0;
            for (int i = 0; i < listaPersonajes.getCantidad(); i++) {
                Personaje personaje = listaPersonajes.getPersonajeI(i);
                int recaudacion = personaje.getRecaudacion();
                if (personaje.getRol() == rol) {
                    recaudacionPorRol += recaudacion;
                }
            }

            salida += recaudacionPorRol + "\n";
            salida += "\n";
        }

        return salida;
    }

    @Override
    public String obtenerRecaudacionPorRegion() {
        String salida = "";
        for (Region region : Region.values()) {
            salida += region.toString() + "\n";
            int recaudacionPorRegion = 0;
            for (int i = 0; i < listaCuentas.getCantidad(); i++) {
                Cuenta cuenta = listaCuentas.getCuentaI(i);
                int recaudacion = cuenta.getRecaudacion();
                if (cuenta.getRegion() == region) {
                    recaudacionPorRegion += recaudacion;
                }
            }

            salida += recaudacionPorRegion + "\n";
            salida += "\n";
        }

        return salida;
    }

    @Override
    public String obtenerRecaudacionPorPersonaje() {
        String salida = "";
        for (int i = 0; i < listaPersonajes.getCantidad(); i++) {
            Personaje personaje = listaPersonajes.getPersonajeI(i);
            int recaudacion = personaje.getRecaudacion();
            salida += personaje.getNombre() + ", " + recaudacion + "\n";
        }

        return salida;
    }

    @Override
    public String obtenerCantidadPersonajesPorRol() {
        String salida = "";
        for (Rol rol : Rol.values()) {
            int contador = 0;
            salida += rol.toString() + ",";
            for (int i = 0; i < listaPersonajes.getCantidad(); i++) {
                Personaje personaje = listaPersonajes.getPersonajeI(i);
                if (rol == personaje.getRol()) {
                    contador++;
                }
            }
            salida += contador + "\n";
        }

        return salida;
    }

    @Override
    public void bloquearJugador(String nombreCuenta) {
        Cuenta cuenta = listaCuentas.buscarCuenta(nombreCuenta);
        if (cuenta == null) {
            throw new NullPointerException("La cuenta no existe.");
        }

        cuenta.setIsBloqueado(true);
    }

    @Override
    public boolean isJugadorBloqueado(String nombreCuenta) {
        Cuenta cuenta = listaCuentas.buscarCuenta(nombreCuenta);
        return cuenta.getIsBloqueado();
    }

    @Override
    public String obtenerDatosCuentasOrdenadasPorNivel() {
        listaCuentas.ordenarPorNivel();
        String salida = "";
        for (int i = 0; i < listaCuentas.getCantidad(); i++) {
            Cuenta cuenta = listaCuentas.getCuentaI(i);
            salida += cuenta.getNick()
                    + " "
                    + cuenta.getNivel()
                    + "\n";
        }

        return salida;
    }

    @Override
    public String obtenerDatosPersonajes() {
        String salida = "";
        for (int i = 0; i < listaPersonajes.getCantidad(); i++) {
            Personaje personaje = listaPersonajes.getPersonajeI(i);
            ListaSkins listaSkinsPersonaje = personaje.getListaSkins();
            salida += personaje.getNombre()
                    + ","
                    + personaje.getRol().toString()
                    + ","
                    + listaSkinsPersonaje.getCantidad();

            for (int j = 0; j < listaSkinsPersonaje.getCantidad(); j++) {
                Skin skin = listaSkinsPersonaje.getSkinI(j);
                salida += ","
                        + skin.getNombre()
                        + ","
                        + skin.getCalidad();
            }

            salida += "\n";
        }

        return salida;
    }

    @Override
    public String obtenerDatosCuentas() {
        String salida = "";
        for (int i = 0; i < listaCuentas.getCantidad(); i++) {
            Cuenta cuenta = listaCuentas.getCuentaI(i);
            ListaPersonajes listaPersonajesCuenta = cuenta.getListaPersonajes();
            salida += cuenta.getNombre()
                    + ","
                    + cuenta.getContrasena()
                    + ","
                    + cuenta.getNick()
                    + ","
                    + cuenta.getNivel()
                    + ","
                    + cuenta.getRp()
                    + ","
                    + listaPersonajesCuenta.getCantidad();

            for (int j = 0; j < listaPersonajesCuenta.getCantidad(); j++) {
                Personaje personaje = listaPersonajesCuenta.getPersonajeI(j);
                ListaSkins listaSkinsPersonajeCuenta = personaje.getListaSkins();
                salida += ","
                        + personaje.getNombre()
                        + ","
                        + listaSkinsPersonajeCuenta.getCantidad();

                for (int k = 0; k < listaSkinsPersonajeCuenta.getCantidad(); k++) {
                    Skin skin = listaSkinsPersonajeCuenta.getSkinI(k);
                    salida += ","
                            + skin.getNombre();
                }
            }


            salida += ","
                    + cuenta.getRegion()
                    +"\n";
        }

        return salida;
    }

    @Override
    public String obtenerDatosEstadisticas() {
        String salida = "";
        for (int i = 0; i < listaPersonajes.getCantidad(); i++) {
            Personaje personaje = listaPersonajes.getPersonajeI(i);
            salida += personaje.getNombre()
                    + ","
                    + personaje.getRecaudacion()
                    + "\n";
        }

        return salida;
    }
}
