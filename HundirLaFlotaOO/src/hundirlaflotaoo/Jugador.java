/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hundirlaflotaoo;

import static hundirlaflotaoo.HundirLaFlotaOO.clrscr;
import static hundirlaflotaoo.HundirLaFlotaOO.promptEnterKey;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author navar
 */
public class Jugador {

    //Atributos
    private String nombre;
    private Tablero tablero;
    private Tablero tablero_ataque;
    private ArrayList<Barco> barcos;

    //Constructores
    public Jugador() {
    }

    public Jugador(String nombre, Tablero tablero, Tablero tablero_ataque, ArrayList<Barco> Barcos) {
        this.nombre = nombre;
        this.tablero = tablero;
        this.tablero_ataque = tablero_ataque;
        this.barcos = Barcos;
    }

    //G&S
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public Tablero getTablero_ataque() {
        return tablero_ataque;
    }

    public void setTablero_ataque(Tablero tablero_ataque) {
        this.tablero_ataque = tablero_ataque;
    }

    public ArrayList<Barco> getBarcos() {
        return barcos;
    }

    public void setBarcos(int tamano) {
        //crea la lista de barcos según el tamaño del tablero
        this.barcos = new ArrayList<>();
        this.barcos.clear(); //vacia la lista
        for (int i = 0; i < (tamano - 2); i++) {
            Barco barco = new Barco(tamano - 1 - i);
            this.barcos.add(barco);
        }
    }

    //Métodos
    //el jugador coloca los barcos en su tablero)
    public void prepararTablero() {
        Scanner leer = new Scanner(System.in);
        clrscr();
        System.out.println();
        System.out.print("  Introduce tu nombre: ");
        this.setNombre(leer.next());
        clrscr();
        System.out.println();
        System.out.println("  "+this.getNombre() + " coloca los barcos.");
        System.out.println();
        this.tablero.dibujarTablero();
        System.out.println();
        for (int i = 0; i < this.barcos.size(); i++) { // Coloca los barcos de la lista de mayor a menor
            boolean colocado = false;
            do { // Hasta que no coloca el barco no pasa al siguiente barco
                int[] casilla = Tablero.pedirPosBarco(this.barcos.get(i).getBarco().length); // pide la casilla donde empezar a colocar el barco
                String orientacion = Tablero.pedirOrBarco(); //pide la orientacion del barco (horizontal o vertical)
                if (this.tablero.cabeTablero(orientacion, casilla, this.barcos.get(i).getBarco().length)) { //Comprueba si cabe el barco
                    this.tablero.colocarBarco(this.barcos.get(i).getBarco().length, casilla, orientacion);  // Coloca el barco en el tablero
                    this.barcos.get(i).setBarco(casilla, orientacion); // asigna las casillas al barco en la lista
                    colocado = true;
                    clrscr();
                    System.out.println();
                    System.out.println("  "+this.getNombre() + " coloca los barcos.");
                    System.out.println();
                    this.tablero.dibujarTablero();
                    System.out.println();
                } else {
                    System.out.println("  No cabe");
                }
            } while (!colocado);
        }
    }

    private int pedirJugada() {
        Scanner leer = new Scanner(System.in);
        System.out.println("\n     Turno de: "+this.getNombre());
        System.out.println("  ####################################");
        System.out.println("  #           M  E  N  Ú             #");
        System.out.println("  ####################################");
        System.out.println("  #   1) Ver mi tablero              #");
        System.out.println("  #   2) Ver tablero de ataque       #");
        System.out.println("  #   3) Atacar                      #");
        System.out.println("  ####################################");
        System.out.print("  Introduce la opción que deseas: ");
        return leer.nextInt();
    }

    public void jugarFlota(Jugador defensor) {
        Scanner leer = new Scanner(System.in);
        boolean salir = false;
        while (!salir) {
            clrscr();
            switch (this.pedirJugada()) {
                case 1: { // muestra el tablero del jugador
                    clrscr();
                    System.out.println();
                    this.getTablero().dibujarTablero();
                    promptEnterKey();
                    break;
                }
                case 2: { // muestra el tablero de ataque
                    clrscr();
                    System.out.println();
                    this.getTablero_ataque().dibujarTablero();
                    promptEnterKey();
                    break;
                }
                case 3: { // realiza el ataque
                    clrscr();
                    this.atacarFlota(defensor);
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

    // ejecuta el ataque del jugador activo
    private void atacarFlota(Jugador defensor) {
        Scanner leer = new Scanner(System.in);
        System.out.println();
        this.getTablero_ataque().dibujarTablero();
        System.out.print("\n  Introduce la fila donde quieres atacar: ");
        int fila = leer.nextInt() - 1;
        System.out.print("  Introduce la columna donde quieres atacar: ");
        int columna = leer.nextInt() - 1;
        int[] casilla = {fila, columna};
        switch (defensor.getTablero().getCasilla(casilla)) {
            case ' ':
                //agua
                this.getTablero_ataque().setCasilla(casilla, '*'); //marca agua en su tablero de ataque
                defensor.getTablero().setCasilla(casilla, '*'); //marca agua en el tablero rival
                clrscr();
                System.out.println();
                this.getTablero_ataque().dibujarTablero();
                System.out.println("\n  ¡AGUA!");
                break;
            case '@':
                this.getTablero_ataque().setCasilla(casilla, 'X'); //marca tocado en su tablero de ataque
                defensor.getTablero().setCasilla(casilla, 'X'); //marca tocado en el tablero rival
                clrscr();
                System.out.println();
                this.getTablero_ataque().dibujarTablero();
                int i = 0;
                while (!defensor.getBarcos().get(i).isTocado(casilla)) { //busca el barco en la lista del rival y marca la casilla tocada
                    i++;
                }
                System.out.println("\n  ¡¡TOCADO!!");
                defensor.getBarcos().get(i).setHundido(); // marca el barco a hundido si tiene todas sus casillas tocadas
                if (defensor.getBarcos().get(i).isHundido()) {
                    System.out.println("\n  ¡¡¡BARCO HUNDIDO!!!");
                }
                break;
            default:
                System.out.println("\n3"
                        + "  ¡Que pena! Ya habías tirado ahí");
                break;
        }
        promptEnterKey();
    }

    // comprueba si un jugador tiene todos sus barcos hundidos
    public boolean barcosHundidos() {
        boolean victoria = true;
        int i = 0;
        while (victoria && i < this.barcos.size()) {
            if (!this.barcos.get(i).isHundido()) { //si hay algún barco sin hundir termina
                victoria = false;
            }
            i++;
        }
        return victoria;
    }
}
