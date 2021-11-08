package cl.ucn.ei.pa.sistemaRitoGames.dominio;

import cl.ucn.ei.pa.sistemaRitoGames.logica.ListaPersonajes;
import cl.ucn.ei.pa.sistemaRitoGames.logica.ListaSkins;
import cl.ucn.ei.pa.sistemaRitoGames.logica.Region;

public class Cuenta {
    private String nombre;
    private String contrasena;
    private String nick;
    private int nivel;
    private int rp;
    private Region region;
    private int recaudacion;
    private boolean isBloqueado;
    private ListaPersonajes listaPersonajes;
    private ListaSkins listaSkins;

    /**
     *
     *
     * @param nombre
     * @param contrasena
     * @param nick
     * @param nivel
     * @param rp
     * @param region
     */
    public Cuenta(String nombre, String contrasena, String nick, int nivel, int rp, Region region) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.nick = nick;
        this.nivel = nivel;
        this.rp = rp;
        this.region = region;
        this.listaPersonajes = new ListaPersonajes(155);
        this.listaSkins = new ListaSkins(999);
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
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     *
     * @param contrasena
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     *
     * @return the nick
     */
    public String getNick() {
        return nick;
    }

    /**
     *
     * @param nick
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     *
     * @return the nivel
     */
    public int getNivel() {
        return nivel;
    }

    /**
     *
     * @param nivel
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    /**
     *
     * @return the rp
     */
    public int getRp() {
        return rp;
    }

    /**
     *
     * @param rp
     */
    public void setRp(int rp) {
        this.rp = rp;
    }

    /**
     *
     * @return the region
     */
    public Region getRegion() {
        return region;
    }

    /**
     *
     * @param region
     */
    public void setRegion(Region region) {
        this.region = region;
    }

    /**
     *
     * @return the recaudacion
     */
    public int getRecaudacion() {
        return recaudacion;
    }

    /**
     *
     * @param recaudacion
     */
    public void setRecaudacion(int recaudacion) {
        this.recaudacion = recaudacion;
    }

    /**
     *
     * @return the isBloqueado
     */
    public boolean getIsBloqueado() {
        return isBloqueado;
    }

    /**
     *
     * @param isBloqueado
     */
    public void setIsBloqueado(boolean isBloqueado) {
        this.isBloqueado = isBloqueado;
    }

    /**
     *
     * @return the listaPersonajes
     */
    public ListaPersonajes getListaPersonajes() {
        return listaPersonajes;
    }

    /**
     *
     * @param listaPersonajes
     */
    public void setListaPersonajes(ListaPersonajes listaPersonajes) {
        this.listaPersonajes = listaPersonajes;
    }

    /**
     *
     * @return the listaSkins
     */
    public ListaSkins getListaSkins() {
        return listaSkins;
    }

    /**
     *
     * @param listaSkins
     */
    public void setListaSkins(ListaSkins listaSkins) {
        this.listaSkins = listaSkins;
    }
}