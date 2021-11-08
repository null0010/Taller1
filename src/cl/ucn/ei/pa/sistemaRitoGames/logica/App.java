package cl.ucn.ei.pa.sistemaRitoGames.logica;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class App {

    /**
     * Este metodo se encargara de iniciar el programa
     * @param args
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        SistemaRitoGames sistemaRitoGames = new SistemaRitoGamesImpl();
        cargarArchivos(sistemaRitoGames);
        iniciarSesion(sistemaRitoGames, input);
        sobrescribirArchivos(sistemaRitoGames);
    }

    /**
     * Este metodo se encará de iniciar una sesión.
     * @param sistemaRitoGames Sistema que contiene todos los metodos necesarios para la aplicación
     * @param input Entrada de datos
     */
    public static void iniciarSesion(SistemaRitoGames sistemaRitoGames, Scanner input) {
        boolean cerrarSistema = false;
        boolean isMenuFinalizado = false;
        while (!cerrarSistema) {
            System.out.print("Ingrese el nombre de su cuenta: ");
            String nombreCuenta = input.next();
            System.out.print("Ingrese su contrasena: ");
            String contrasena = input.next();
            if (nombreCuenta.equals("ADMIN") && contrasena.equals("ADMIN")) {
                iniciarMenuAdmin(sistemaRitoGames, input);
                isMenuFinalizado = true;
            }
            else {
                if (!nombreCuenta.equals("ADMIN")) {
                    if (!sistemaRitoGames.isUsuarioRegistrado(nombreCuenta)) {
                        System.out.println("Usted no se encuentra regitrado en el sistema.");
                        System.out.println("1) Registrarse.");
                        System.out.println("2) Volver a iniciar sesión.");
                        System.out.print("Ingrese una opción (1 o 2): ");
                        int opcion = input.nextInt();
                        while (opcion != 1 && opcion != 2) {
                            System.out.print("Opción no disponible, vuelva a ingresar una opción: ");
                            opcion = input.nextInt();
                        }

                        if (opcion == 1) {
                            registrarJugador(sistemaRitoGames, input);
                            System.out.println("Se ha registrado exitosamente.");
                        }
                    }
                    else {
                        if (sistemaRitoGames.isJugadorBloqueado(nombreCuenta)) {
                            System.out.println("Lo siento, su cuenta se encuentra bloqueada.");
                        }
                        else {
                            if (sistemaRitoGames.isContrasenaCorrecta(nombreCuenta, contrasena)) {
                                iniciarMenuCliente(sistemaRitoGames, input, nombreCuenta);
                                isMenuFinalizado = true;
                            }
                            else {
                                System.out.println("Nombre de cuenta y/o contrasena incorrectos.");
                            }
                        }
                    }
                }
                else {
                    System.out.println("Nombre de cuenta y/o contrasena incorrectos.");
                }
            }

            if (isMenuFinalizado) {
                System.out.println("1) Iniciar sesión nuevamente.");
                System.out.println("2) Cerrar sistema");
                System.out.print("Ingrese una opción (1 o 2): ");
                int opcion = input.nextInt();
                while (opcion != 1 && opcion != 2) {
                    System.out.print("Opción no disponible, vuelva a ingresar una opción: ");
                    opcion = input.nextInt();
                }

                if (opcion == 2) {
                    cerrarSistema = true;
                }

                isMenuFinalizado = false;
            }
        }
    }

    /**
     * Este metodo se encargará de registrar a un jugador
     * @param sistemaRitoGames Sistema que contiene todos los metodos necesarios para la aplicación
     * @param input Entrada de datos
     */
    public static void registrarJugador(SistemaRitoGames sistemaRitoGames, Scanner input) {
        System.out.print("Ingrese el nombre de la cuenta: ");
        String nombreCuenta = input.next();
        while (sistemaRitoGames.isUsuarioRegistrado(nombreCuenta)) {
            System.out.println("Lo siento, ese nombre ya se encuentra registrado, vuelta a intentarlo.");
            System.out.print("Ingrese el nombre de la cuenta: ");
            nombreCuenta = input.next();
        }

        System.out.print("Ingrese su contrasena: ");
        String contrasena = input.next();
        System.out.print("Ingrese su nick: ");
        String nick = input.next();
        System.out.print("Ingrese su región (LAS/LAN/EUW/KR/NA/RU): ");
        String region = input.next().toUpperCase();
        while (!region.equals("LAS") &&
               !region.equals("LAN") &&
               !region.equals("EUW") &&
               !region.equals("KR") &&
               !region.equals("NA") &&
               !region.equals("RU")) {
            System.out.print("Por favor, ingrese una región valida (LAS/LAN/EUW/KR/NA/RU): ");
            region = input.next().toUpperCase();
        }

        sistemaRitoGames.ingresarCuenta(nombreCuenta, contrasena, nick, 0, 0, region);
    }

    /**
     * Este metodo se encargará de iniciar el menu del cliente
     * @param sistemaRitoGames Sistema que contiene todos los metodos necesarios para la aplicación
     * @param input Entrada de datos
     * @param nombreCuenta Nombre de la cuenta que inicio sesión
     */
    public static void iniciarMenuCliente(SistemaRitoGames sistemaRitoGames, Scanner input, String nombreCuenta) {
        System.out.println("Bienvenido al Menu Cliente");
        boolean isCerrarSesion = false;
        while (!isCerrarSesion) {
            System.out.println(obtenerOpcionesMenuCliente());
            System.out.print("Eliga una opción: ");
            int opcion = input.nextInt();
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del personaje: ");
                    String nombrePersonaje = input.next();
                    try {
                        System.out.println(sistemaRitoGames.obtenerSkinsDisponiblesPersonaje(nombreCuenta, nombrePersonaje));
                        input.nextLine();
                        System.out.print("Ingrese el nombre de uno de los skins disponibles: ");
                        String nombreSkins = input.nextLine();
                        boolean isCompraExitosa = sistemaRitoGames.comprarSkin(nombreCuenta, nombrePersonaje, nombreSkins);
                        if (isCompraExitosa) {
                            System.out.println("La compra se ha realizado exitosamente.");
                        }
                        else {
                            System.out.println("No tienes saldo suficiente!.");
                        }
                    }
                    catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    break;

                case 2:
                    System.out.println(sistemaRitoGames.obtenerPersonajesDisponibles(nombreCuenta));
                    System.out.print("Ingrese el nombre de uno de los personajes disponibles: ");
                    nombrePersonaje = input.next();
                    try {
                        boolean isCompraExitosa = sistemaRitoGames.comprarPersonaje(nombreCuenta, nombrePersonaje);
                        if (isCompraExitosa) {
                            System.out.println("La compra se ha realizado exitosamente.");
                        }
                        else {
                            System.out.println("No tienes saldo suficiente!.");
                        }
                    }
                    catch (Exception exception) {
                        exception.printStackTrace();
                    }

                    break;

                case 3:
                    System.out.println(sistemaRitoGames.obtenerSkinsDisponibles(nombreCuenta));
                    break;


                case 4:
                    System.out.println(sistemaRitoGames.obtenerInventario(nombreCuenta));
                    break;


                case 5:
                    System.out.print("Ingrese la cantidad de rp a recargar: ");
                    int rp = input.nextInt();
                    sistemaRitoGames.recargarRp(nombreCuenta, rp);
                    break;

                case 6:
                    System.out.println();
                    System.out.println(sistemaRitoGames.obtenerDatosCuenta(nombreCuenta));
                    System.out.print("¿Desea cambiar su contrasena? (Si/No): ");
                    String respuesta = input.next();
                    while (!respuesta.equalsIgnoreCase("si") && !respuesta.equalsIgnoreCase("no")) {
                        System.out.print("¿Desea cambiar su contrasena? (Si/No): ");
                        respuesta = input.next();
                    }

                    if (respuesta.equalsIgnoreCase("si")) {
                        System.out.print("Ingrese su nueva contrasena: ");
                        String nuevacontrasena = input.next();
                        sistemaRitoGames.cambiarContrasena(nombreCuenta, nuevacontrasena);
                    }

                    break;

                case 7:
                    isCerrarSesion = true;
                    break;

                default:
                    System.out.println("Lo siento, esa opción no se encuentra disponible.");
                    break;
            }

        }


    }

    /**
     * Devuelve un String con todas las opciones del menu cliente
     * @return String
     */
    public static String obtenerOpcionesMenuCliente() {
        String opciones = "1) Comprar Skin\n";
        opciones += "2) Comprar Personaje\n";
        opciones += "3) Skins Disponibles\n";
        opciones += "4) Mostrar Inventario\n";
        opciones += "5) Recargar RP\n";
        opciones += "6) Mostrar Datos de cuenta\n";
        opciones += "7) Cerrar Sesión\n";
        return opciones;
    }

    /**
     * Este metodo se encargará de iniciar el menu del admin
     * @param sistemaRitoGames Sistema que contiene todos los metodos necesarios para la aplicación
     * @param input Entrada de datos
     */
    public static void iniciarMenuAdmin(SistemaRitoGames sistemaRitoGames, Scanner input) {
        System.out.println("Bienvenido al Menu Admin");
        boolean isCerrarSesion = false;
        while (!isCerrarSesion) {
          System.out.println(obtenerOpcionesMenuAdmin());
          System.out.print("Ingrese una opcion: ");
          int opcion = input.nextInt();
          switch (opcion) {
              case 1:
                  System.out.println(sistemaRitoGames.obtenerRecaudacionPorRol());
                  break;

              case 2:
                  System.out.println(sistemaRitoGames.obtenerRecaudacionPorRegion());
                  break;

              case 3:
                  System.out.println(sistemaRitoGames.obtenerRecaudacionPorPersonaje());
                  break;

              case 4:
                  System.out.println(sistemaRitoGames.obtenerCantidadPersonajesPorRol());
                  break;

              case 5:
                  System.out.print("Ingrese el nombre del nuevo personaje: ");
                  String nombrePersonaje = input.next();
                  System.out.print("Ingrese el rol del personaje (SUP/ADC/TOP/MID/JG): ");
                  String rol = input.next().toUpperCase();
                  while (!rol.equals("SUP") &&
                         !rol.equals("ADC") &&
                         !rol.equals("TOP") &&
                         !rol.equals("MID") &&
                         !rol.equals("JG")) {
                      System.out.print("Ingrese un rol valido (SUP/ADC/TOP/MID/JG): ");
                      rol = input.next().toUpperCase();
                  }

                  boolean isIngresadoPersonaje = sistemaRitoGames.ingresarPersonaje(nombrePersonaje, rol);
                  if (isIngresadoPersonaje) {
                      System.out.println("El personaje fue ingresado exitosamente.");
                      System.out.print("Ingrese la cantidad de skins del nuevo personaje: ");
                      int cantidadSkins = input.nextInt();
                      for (int i = 0; i < cantidadSkins; i++) {
                          input.nextLine();
                          System.out.print("Ingrese el nombre del skin: ");
                          String nombreSkin = input.nextLine();
                          System.out.print(
                                  "Ingrese la primera letra de la calidad del skin [(M)itica/(D)efinitiva/(L)egendaria/(E)pica/(N)ormal]: ");
                          String calidad = input.next().toUpperCase();
                          while (!calidad.equals("M") &&
                                  !calidad.equals("D") &&
                                  !calidad.equals("L") &&
                                  !calidad.equals("E") &&
                                  !calidad.equals("N")) {
                              System.out.print(
                                      "Ingrese la primera letra de la calidad del skin [(M)itica/(D)efinitiva/(L)egendaria/(E)pica/(N)ormal]: ");
                              calidad = input.next().toUpperCase();
                          }

                          boolean isIngresadoSkinPersonaje = sistemaRitoGames.ingresarSkinPersonaje(nombreSkin,
                                  calidad);
                          boolean isIngresadoSkinListaGeneral = sistemaRitoGames.ingresarSkinListaGeneral();
                          if (isIngresadoSkinPersonaje && isIngresadoSkinListaGeneral) {
                              System.out.println("El skin fue ingresado exitosamente.");
                          }
                      }
                  }
                  else {
                      System.out.println("No hay especio disponible.");
                  }

                  break;

              case 6:
                  System.out.print("Ingrese el nombre del personaje al cual desea agregarle un skin: ");
                  nombrePersonaje = input.next();
                  input.nextLine();
                  System.out.print("Ingrese el nombre del nuevo skin: ");
                  String nombreSkin = input.nextLine();
                  System.out.print("Ingrese la calidad del skin [(M)itica/(D)efinitiva/(L)egendaria/(E)pica/(N)ormal]: ");
                  String calidad = input.next().toUpperCase();
                  while (!calidad.equals("M") &&
                          !calidad.equals("D") &&
                          !calidad.equals("L") &&
                          !calidad.equals("E") &&
                          !calidad.equals("N")) {
                      System.out.print("Ingrese la calidad del skin [(M)itica/(D)efinitiva/(L)egendaria/(E)pica/(N)ormal]: ");
                      calidad = input.next().toUpperCase();
                  }

                  try {
                      boolean isAsociadoSkinPersonaje = sistemaRitoGames.asociarSkinPersonaje(nombrePersonaje, nombreSkin,
                              calidad);

                      boolean isAsociadoSkinListaGeneral = sistemaRitoGames.asociarSkinListaGeneral(nombrePersonaje, nombreSkin);

                      if (isAsociadoSkinPersonaje && isAsociadoSkinListaGeneral) {
                          System.out.println("El skin fue ingresado exitosamente.");
                      }
                  }
                  catch (Exception exception) {
                      exception.printStackTrace();
                  }

                  break;

              case 7:
                  System.out.print("Ingrese el nombre de la cuenta: ");
                  String nombreCuenta = input.next();
                  try {
                      sistemaRitoGames.bloquearJugador(nombreCuenta);
                      System.out.println("El jugador ha sido bloqueado exitosamente.");
                  }
                  catch (Exception exception) {
                      exception.printStackTrace();
                  }
                  break;

              case 8:
                  System.out.println(sistemaRitoGames.obtenerDatosCuentasOrdenadasPorNivel());
                  break;

              case 9:
                  isCerrarSesion = true;
                  break;

              default:
                  System.out.println("Lo siento, esa opción no se encuentra disponible.");
                  break;
          }
        }
    }

    /**
     * Devuelve un String con todas las opciones del menu admin
     * @return String
     */
    public static String obtenerOpcionesMenuAdmin() {
        String opciones = "1) Desplegar recaudacion por rol\n";
        opciones += "2) Desplegar recaudacion total de ventas por región\n";
        opciones += "3) Desplegar recaudacion de ventas por personaje\n";
        opciones += "4) Desplegar la cantidad de personajes por cada rol existente\n";
        opciones += "5) Agregar personaje al juego\n";
        opciones += "6) Agregar Skin\n";
        opciones += "7) Bloquear a un jugador\n";
        opciones += "8) Desplegar todas las cuentas\n";
        opciones += "9) Cerrar Sesión\n";
        return opciones;
    }

    /**
     * Este metodo se encargará de cargar todos los archivos necesarios para la aplicación
     * @param sistemaRitoGames Sistema que contiene todos los metodos necesarios para la aplicación
     */
    public static void cargarArchivos(SistemaRitoGames sistemaRitoGames) {
        cargarPersonajes(sistemaRitoGames);
        cargarCuentas(sistemaRitoGames);
        cargarEstadisticas(sistemaRitoGames);
    }

    /**
     * Este metodo se encargará de cargar los datos del archivo personajes
     * @param sistemaRitoGames Sistema que contiene todos los metodos necesarios para la aplicación
     */
    public static void cargarPersonajes(SistemaRitoGames sistemaRitoGames) {
        File archivoPersonajes = new File("archivos/Personajes.txt");
        sistemaRitoGames.ingresarPrecioPersonajes(975);
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

    /**
     * Este metodo se encargará de cargar los datos del archivo cuentas
     * @param sistemaRitoGames Sistema que contiene todos los metodos necesarios para la aplicación
     */
    public static void cargarCuentas(SistemaRitoGames sistemaRitoGames) {
        File archivoCuentas = new File("archivos/Cuentas.txt");
        try (Scanner scannerFile = new Scanner(archivoCuentas)) {
            while (scannerFile.hasNext()) {
                String linea = scannerFile.nextLine();
                String[] partes = linea.split(",");
                String nombreCuenta = partes[0].strip();
                String contrasena = partes[1].strip();
                String nick = partes[2].strip();
                int nivelCuenta = Integer.parseInt(partes[3].strip());
                int rp = Integer.parseInt(partes[4].strip());
                int cantidadPersonajes = Integer.parseInt(partes[5].strip());
                String region = partes[partes.length - 1];
                sistemaRitoGames.ingresarCuenta(nombreCuenta, contrasena, nick, nivelCuenta, rp, region);
                int posicionCampo = 6;
                for(int i = 0; i < cantidadPersonajes; i++){
                    String nombrePersonaje = partes[posicionCampo].strip();
                    sistemaRitoGames.asociarPersonajeCuenta(nombrePersonaje, nombreCuenta);
                    int cantidadSkins = Integer.parseInt(partes[posicionCampo + 1].strip());
                    for(int j = 0; j < cantidadSkins; j++){
                        String nombreSkin = partes[posicionCampo + 2 + j].strip();
                        sistemaRitoGames.ingresarSkinPersonajeCuenta(nombreSkin);
                        sistemaRitoGames.ingresarSkinCuenta(nombreSkin);
                    }

                    posicionCampo = posicionCampo + 2 + cantidadSkins;
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Este metodo se encargará de cargar los datos del archivo estadisticas
     * @param sistemaRitoGames Sistema que contiene todos los metodos necesarios para la aplicación
     */
    public static void cargarEstadisticas(SistemaRitoGames sistemaRitoGames) {
        File archivoEstadistica = new File("archivos/Estadisticas.txt");

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

    /**
     * Este metodo se encargará de sobrescribir todos los archivos que fueron modificados durante la aplicación
     * @param sistemaRitoGames Sistema que contiene todos los metodos necesarios para la aplicación
     */
    public static void sobrescribirArchivos(SistemaRitoGames sistemaRitoGames) {
        sobrescribirArchivoPersonajes(sistemaRitoGames);
        sobrescribirArchivoCuentas(sistemaRitoGames);
        sobrescribirArchivoEstadisticas(sistemaRitoGames);
    }

    /**
     * Este metodo se encargará de sobrescribir los datos del archivo personajes
     * @param sistemaRitoGames Sistema que contiene todos los metodos necesarios para la aplicación
     */
    public static void sobrescribirArchivoPersonajes(SistemaRitoGames sistemaRitoGames) {
        try (FileWriter fileWriter = new FileWriter("archivos/Personajes.txt")) {
            fileWriter.write(sistemaRitoGames.obtenerDatosPersonajes());
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    /**
     * Este metodo se encargará de sobrescribir los datos del archivo cuentas
     * @param sistemaRitoGames Sistema que contiene todos los metodos necesarios para la aplicación
     */
    public static void sobrescribirArchivoCuentas(SistemaRitoGames sistemaRitoGames) {
        try (FileWriter fileWriter = new FileWriter("archivos/Cuentas.txt")) {
            fileWriter.write(sistemaRitoGames.obtenerDatosCuentas());
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Este metodo se encargará de sobrescribir los datos del archivo estadisticas
     * @param sistemaRitoGames Sistema que contiene todos los metodos necesarios para la aplicación
     */
    public static void sobrescribirArchivoEstadisticas(SistemaRitoGames sistemaRitoGames) {
        try (FileWriter fileWriter = new FileWriter("archivos/Estadisticas.txt")) {
            fileWriter.write(sistemaRitoGames.obtenerDatosEstadisticas());
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}


