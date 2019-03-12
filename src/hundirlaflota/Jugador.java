/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hundirlaflota;

import java.util.ArrayList;

/**
 *
 * @author infor04
 */
public abstract class Jugador {

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
    
    abstract public void prepararTablero();

}
