package cl.ucn.ei.pa.sistemaRitoGames.logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        SistemaRitoGames sistemaRitoGames = new SistemaRitoGamesImpl();
        cargarPersonajes(sistemaRitoGames);
        System.out.println(sistemaRitoGames.obtenerDatosPersonajes());
        cargarCuentas(sistemaRitoGames);
        System.out.println(sistemaRitoGames.obtenerDatosCuentas());
        cargarEstadisticas(sistemaRitoGames);
        System.out.println(sistemaRitoGames.obtenerDatosEstadistica());
    }


    public static void cargarPersonajes(SistemaRitoGames sistemaRitoGames) {
        File archivoPersonajes = new File("archivos/Personajes.txt");
        try (Scanner scannerFile = new Scanner(archivoPersonajes)) {
            while (scannerFile.hasNext()) {
                String linea = scannerFile.nextLine();
                String[] partes = linea.split(",");
                String nombre = partes[0].strip();
                String rol = partes[1].strip();
                int cantidadSkins = Integer.parseInt(partes[2].strip());
                sistemaRitoGames.ingresarPersonaje(nombre, rol);
                for (int i = 3; i < cantidadSkins + 4; i += 2) {
                    String nombreSkin = partes[i].strip();
                    String calidad = partes[i + 1].strip();
                    sistemaRitoGames.ingresarSkinPersonaje(nombreSkin, calidad);
                    sistemaRitoGames.ingresarSkinListaGeneral();
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
                String linea = scannerFile.nextLine();
                String[] partes = linea.split(",");
                String nombreCuenta = partes[0].strip();
                String contraseña = partes[1].strip();
                String nick = partes[2].strip();
                int nivelCuenta = Integer.parseInt(partes[3].strip());
                int rp = Integer.parseInt(partes[4].strip());
                int cantidadPersonajes = Integer.parseInt(partes[5].strip());
                String region = partes[partes.length - 1];
                sistemaRitoGames.ingresarCuenta(nombreCuenta, contraseña, nick, nivelCuenta, rp, region);

                int posicionCampo = 6;
                for(int i = 0; i < cantidadPersonajes; i++){
                    String nombrePersonaje = partes[posicionCampo].strip();
                    sistemaRitoGames.asociarPersonajeCuenta(nombrePersonaje, nombreCuenta);
                    int cantidadSkins = Integer.parseInt(partes[posicionCampo + 1].strip());

                    for(int j = 0; j < cantidadSkins; j++){
                        String nombreSkin = partes[posicionCampo + 2 + j].strip();
                        //System.out.println(nombreSkin);
                        sistemaRitoGames.asociarSkinPersonajeCuenta(nombreSkin);
                        sistemaRitoGames.asociarSkinCuenta(nombreSkin);
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


