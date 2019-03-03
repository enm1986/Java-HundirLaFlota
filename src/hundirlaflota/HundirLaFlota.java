/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hundirlaflota;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author infor04
 */
public class HundirLaFlota {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        Scanner leer = new Scanner(System.in);
        ArrayList<Jugador> j = new ArrayList<>();
        j.add(new Jugador()); // añadimos 2 jugadores
        j.add(new Jugador());
        boolean salir = false;

        // Variables de control de inicio de partida
        boolean ini = false; // si se ha asignado el tamaño del tablero
        boolean prep1 = false; // si se ha preparado el tablero del jugador 1
        boolean prep2 = false; // si se ha preparado el tablero del jugador 2

        while (!salir) {
            clrscr();
            switch (pedirOpcion()) {
                case 1: {
                    /*Inicializar partida*/
                    int tamano = Tablero.pedirTamano();
                    //jugador 1
                    j.get(0).setTablero(new Tablero(tamano));
                    j.get(0).setTablero_ataque(new Tablero(tamano));
                    j.get(0).setBarcos(tamano);
                    //jugador 2
                    j.get(1).setTablero(new Tablero(tamano));
                    j.get(1).setTablero_ataque(new Tablero(tamano));
                    j.get(1).setBarcos(tamano);
                    //control
                    ini = true; // jugadores inicializados
                    prep1 = false; // tablero j1 no preparado
                    prep2 = false; // tablero j2 no preparado
                    break;
                }
                case 2: {
                    /*Preparar tablero jugador 1*/
                    clrscr();
                    if (ini && !prep1) {
                        j.get(0).prepararTablero();
                        promptEnterKey();
                        prep1 = true;
                    } else if (prep1) {
                        System.out.println("\n  Ya has preparado el tablero");
                        TimeUnit.SECONDS.sleep(1);
                    } else {
                        System.out.println("\n  Primero debes inicializar la partida");
                        TimeUnit.SECONDS.sleep(1);
                    }
                    break;
                }
                case 3: {
                    /*Preparar tablero jugador 2*/
                    clrscr();
                    if (ini && !prep2) {
                        j.get(1).prepararTablero();
                        promptEnterKey();
                        prep2 = true;
                    } else if (prep2) {
                        System.out.println("\n  Ya has preparado el tablero");
                        TimeUnit.SECONDS.sleep(1);
                    } else {
                        System.out.println("\n  Primero debes inicializar la partida");
                        TimeUnit.SECONDS.sleep(1);
                    }
                    break;
                }
                case 4: {
                    /*Empezar partida*/
                    clrscr();
                    if (ini && prep1 && prep2) { //solo se puede empezar la partida si tablero inicializado y jugadores preparados
                        boolean finpartida = false;
                        Jugador atacante;
                        Jugador defensor;
                        int turno = 0;
                        System.out.println("\n  Empieza la partida...");
                        TimeUnit.SECONDS.sleep(1);
                        do {
                            clrscr();
                            atacante = j.get(turno % 2);        //cambia el jugador atacante según el turno
                            defensor = j.get((turno + 1) % 2);  //cambia el jugador defensor según el turno
                            System.out.println("\n  Turno de " + atacante.getNombre()+"...");
                            TimeUnit.SECONDS.sleep(2);
                            atacante.jugarFlota(defensor); //juega el jugador activo
                            if (defensor.barcosHundidos()) { //gana si todos los barcos del defensor han sido hundidos
                                finpartida = true;
                                System.out.println("\n" + atacante.getNombre() + " ¡¡HA GANADO!!");
                            }
                            turno++; // siguiente turno
                        } while (!finpartida);
                        promptEnterKey();

                        //reiniciar todo
                        ini = false;
                        prep1 = false;
                        prep2 = false;
                        j.get(0).setTablero(new Tablero());
                        j.get(0).setTablero_ataque(new Tablero());
                        j.get(0).setBarcos(0);
                        j.get(1).setTablero(new Tablero());
                        j.get(1).setTablero_ataque(new Tablero());
                        j.get(1).setBarcos(0);

                    } else {
                        System.out.println("\n  No puedes empezar sin inicializar "
                                + "la partida ni los tableros de cada jugador");
                        TimeUnit.SECONDS.sleep(1);
                    }
                    break;
                }
                case 5: {
                    salir = true;
                    break;
                }
                default: {
                    System.out.println("  Opción no válida");
                    break;
                }
            }
        }
    }

    //muestra el menú inicial y devuelve la opción elegida
    public static int pedirOpcion() {
        Scanner leer = new Scanner(System.in);
        System.out.println("  ####################################");
        System.out.println("  #          Undir la Flota          #");
        System.out.println("  ####################################");
        System.out.println("  # 1 - Inicializar partida          #");
        System.out.println("  # 2 - Preparar tablero Jugador 1   #");
        System.out.println("  # 3 - Preparar tablero Jugador 2   #");
        System.out.println("  # 4 - Empezar partida              #");
        System.out.println("  # 5 - Salir                        #");
        System.out.println("  ####################################");
        System.out.print("  Introduce la opción que deseas: ");
        return leer.nextInt();
    }

    //muestra la lista de barcos (usada sólo para hacer pruebas)
    public static void imprimirBarcos(ArrayList<Barco> lista) {
        System.out.print("  Tamaño barcos: ");
        for (int i = 0; i < (lista.size()); i++) {
            System.out.print(lista.get(i).getBarco().length + ", ");
        }
        System.out.println();
    }

    public static void clrscr() {
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException ex) {
        }
    }

    public static void promptEnterKey() {
        System.out.println("\n Pulsa INTRO para continuar...");
        try {
            int read = System.in.read(new byte[2]);
        } catch (IOException e) {
        }
    }
}
