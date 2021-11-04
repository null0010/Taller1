package cl.ucn.ei.pa.sistemaRitoGames.logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        SistemaRitoGames sistemaRitoGames = new SistemaRitoGamesImpl();


    }


    public static void cargarPersonajes(SistemaRitoGames sistemaRitoGames) {
        File archivoPersonajes = new File("archivos/Personaje.txt");
        try (Scanner scannerFile = new Scanner(archivoPersonajes)) {
            while (scannerFile.hasNext()) {
                String linea = scannerFile.next();
                String[] partes = linea.split(",");
                String nombre = partes[0];
                String rol = partes[1];
                int cantidadSkins = Integer.parseInt(partes[2]);
                sistemaRitoGames.ingresarPersonaje(nombre, rol);
                for (int i = 3; i < cantidadSkins; i += 2) {
                    String nombreSkin = partes[i];
                    String calidad = partes[i + 1];
                    sistemaRitoGames.ingresarSkinPersonaje(nombreSkin, calidad);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void cargarCuentas(SistemaRitoGames sistemaRitoGames) {
        File archivoCuentas = new File("archivos/Cuentas.txt");
        try (Scanner scannerFile = new Scanner(archivoCuentas)) {
            while (scannerFile.hasNext()) {
                String linea = scannerFile.next();
                String[] partes = linea.split(",");
                String nombreCuenta = partes[0];
                String contraseña = partes[1];
                String nick = partes[2];
                int nivelCuenta = Integer.parseInt(partes[3]);
                int rp = Integer.parseInt(partes[4]);
                int cantidadPersonajes = Integer.parseInt(partes[5]);
                String region = partes[partes.length - 1];
                sistemaRitoGames.ingresarCuenta(nombreCuenta, contraseña, nick, nivelCuenta, rp, region);
                int posicionCampo = 6;
                for(int i = 0; i < cantidadPersonajes; i++){
                    String nombrePersonaje = partes[posicionCampo];
                    sistemaRitoGames.asociarPersonajeCuenta(nombreCuenta, nombrePersonaje);
                    int cantidadSkins = Integer.parseInt(partes[posicionCampo + 1]);
                    for(int j = 0; j < cantidadSkins; j++){
                        String nombreSkin = partes[posicionCampo + 2 + j];
                        sistemaRitoGames.asociarSkinPersonaje(nombreSkin, nombreCuenta);
                        sistemaRitoGames.asociarSkinCuenta(nombreSkin, nombreCuenta);
                    }
                    posicionCampo = posicionCampo + 2 + cantidadSkins;
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void cargarEstadisticas(SistemaRitoGames sistemaRitoGames) {
        File archivoEstadistica = new File("archivos/Estadistica.txt");

        try (Scanner scannerFile = new Scanner(archivoEstadistica)) {
            while (scannerFile.hasNext()) {
                String linea = scannerFile.next();
                String[] partes = linea.split(",");
                String nombrePersonaje = partes[0];
                int recaudacion = Integer.parseInt(partes[1]);
                sistemaRitoGames.ingresarRecaudacion(nombrePersonaje, recaudacion);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}


