/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hundirlaflota;

/**
 *
 * @author infor04
 */
public class Barco {

    //Atributos
    private int[][] barco;
    private boolean hundido;

    //Constructores
    public Barco() {
    }

    public Barco(int tbarco) {
        this.barco = new int[tbarco][3];
        this.hundido = false;
    }

    //Getters y Setters
    public int[][] getBarco() {
        return barco;
    }

    public void setBarco(int[] casilla, String orient) {
        if ("H".equals(orient)) {
            for (int i = 0; i < barco.length; i++) {
                this.barco[i][0] = casilla[0];
                this.barco[i][1] = casilla[1] + i;
                this.barco[i][2] = 0; //control tocado (tocado = 1)
            }
        } else {
            for (int i = 0; i < barco.length; i++) {
                this.barco[i][0] = casilla[0] + i;
                this.barco[i][1] = casilla[1];
                this.barco[i][2] = 0; //control tocado (tocado = 1)
            }
        }
    }

    public boolean isHundido() {
        return hundido;
    }

    //si todas las posiciones del barco están tocadas > barco hundido
    public void setHundido() {
        int i = 0;
        boolean hund = true;
        while (hund && i < barco.length) {
            if (barco[i][2] == 0) { // el barco está hundido si todas sus casillas están tocadas (1)
                hund = false;
            }
            i++;
        }
        this.hundido = hund;
    }

    //Métodos
    //imprime las posiciones de un barco en un tablero (no se usa)
    public void imprimirBarco() {
        for (int i = 0; i < barco.length; i++) {
            System.out.print(this.barco[i][0] + ", " + this.barco[i][1]);
        }
    }

    // comprueba si ha tocado el barco y marca la posición si ha tocado el barco
    public boolean isTocado(int[] casilla) {
        boolean tocado = false;
        int i = 0;
        while (!tocado && i < this.barco.length) {
            if (this.barco[i][0] == casilla[0] && barco[i][1] == casilla[1]) {
                tocado = true;
                this.barco[i][2] = 1; // marca la casilla del barco a "tocado"
            }
            i++;
        }
        return tocado;
    }

}
