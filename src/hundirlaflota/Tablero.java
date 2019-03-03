/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hundirlaflota;

import java.util.Scanner;

/**
 *
 * @author infor04
 */
public class Tablero {

    //Atributos
    private char[][] tablero;

    //Constructores
    public Tablero(int tamano) {
        this.tablero = new char[tamano][tamano];
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                tablero[i][j] = ' ';
            }
        }
    }

    public Tablero() {
    }

    public Tablero(Tablero t) {
        this.tablero = t.tablero;
    }

    //Getters & Setters
    // devuelve el contenido de una casilla del tablero
    public char getCasilla(int[] casilla) {
        return this.tablero[casilla[0]][casilla[1]];
    }

    // modifica el contenido de una casilla del tablero
    public void setCasilla(int[] casilla, char c) {
        this.tablero[casilla[0]][casilla[1]] = c;
    }

    public void colocarBarco(int tbarco, int[] casilla, String orient) {
        if (orient.contains("H")) {
            for (int i = 0; i < tbarco; i++) {
                this.tablero[casilla[0]][casilla[1] + i] = '@';
            }
        } else {
            for (int i = 0; i < tbarco; i++) {
                this.tablero[casilla[0] + i][casilla[1]] = '@';
            }
        }
    }

    //Metodos
    // pide el tamaño del tablero
    public static int pedirTamano() {
        Scanner leer = new Scanner(System.in);
        System.out.print("  Introduce el tamaño del tablero: ");
        int t = 0;
        do {
            t = leer.nextInt();
            if (t < 5) {
                System.out.print("  El tablero debe ser de 5x5 como mínimo "
                        + "\n  vuelve a introducir el tamaño: ");
            }
        } while (t < 5);
        return t;
    }

    // pide la posición en el tablero donde empezar a colocar el barco
    public static int[] pedirPosBarco(int tamano) {
        Scanner leer = new Scanner(System.in);
        System.out.print("  Introduce la fila donde colocar el barco "
                + "de tamaño " + tamano + ": ");
        int fila = leer.nextInt();
        System.out.print("  Introduce la columna donde colocar el barco "
                + "de tamaño " + tamano + ": ");
        int columna = leer.nextInt();
        int[] casilla = {fila - 1, columna - 1};
        return casilla;
    }

    // pide la orientación para colocar el barco en el tablero (H / V)
    public static String pedirOrBarco() {
        Scanner leer = new Scanner(System.in);
        System.out.print("  Introduce la orientación del barco (H / V): ");
        return leer.next().toUpperCase();
    }

    //Dibuja el tablero por pantalla
    public void dibujarTablero() {
        System.out.print("  ");
        for (int i = 1; i <= this.tablero.length; i++) {
            if (i < 10) {
                System.out.print("  " + i + " ");
            } else {
                System.out.print("  " + i);
            }
        }
        System.out.println();
        System.out.print("  ");
        for (int i = 0; i < this.tablero.length; i++) {
            System.out.print("|---");
        }
        System.out.println("|");
        for (int i = 0; i < this.tablero.length; i++) {
            if ((i + 1) < 10) {
                System.out.print((i + 1) + " ");
            } else {
                System.out.print((i + 1));
            }
            for (int j = 0; j < this.tablero.length; j++) {
                System.out.print("| " + tablero[i][j] + " ");
            }
            System.out.println("|");
            System.out.print("  ");
            for (int j = 0; j < this.tablero.length; j++) {
                System.out.print("|---");
            }
            System.out.println("|");
        }
    }

    // comprueba si un barco cabe en el tablero
    public boolean cabeTablero(String orient, int[] casilla, int tbarco) {
        boolean cabe = true;
        int cont = 0;
        if (this.tablero[casilla[0]][casilla[1]] == ' ') {
            if (orient.contains("H")) { //Si se coloca en horizontal
                if ((casilla[1] + tbarco) <= this.tablero.length) {
                    while ((cabe) && (cont < tbarco)) {
                        if (this.tablero[casilla[0]][casilla[1] + cont] != ' ') {
                            cabe = false;
                        }
                        cont++;
                    }
                } else {
                    cabe = false;
                }
            } else { //Si se coloca en vertical
                if ((casilla[0] + tbarco) <= this.tablero.length) {
                    while ((cabe) && (cont < tbarco)) {
                        if (this.tablero[casilla[0] + cont][casilla[1]] != ' ') {
                            cabe = false;
                        }
                        cont++;
                    }
                } else {
                    cabe = false;
                }
            }
        } else {
            cabe = false;
        }
        return cabe;
    }

}
