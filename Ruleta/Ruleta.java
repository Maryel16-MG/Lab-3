/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ruleta;

import Simbolo.Simbolo;
import java.util.Random;


/**
 *
 * @author user
 */
public class Ruleta implements Runnable {
    private Simbolo[] simbolos;       // Lista de símbolos disponibles en la ruleta
    private Simbolo simboloActual;    // Símbolo que está actualmente en la pantalla
    private boolean girando;          // Estado de si la ruleta está girando

    public Ruleta(Simbolo[] simbolos) {
        this.simbolos = simbolos;
        this.simboloActual = simbolos[0]; // Empieza con el primer símbolo.
        this.girando = false;
    }

    // Método para obtener el símbolo actual
    public Simbolo getSimboloActual() {
        return simboloActual;
    }

    // Método getter para obtener todos los símbolos
    public Simbolo[] getSimbolos() {
        return simbolos;
    }

    // Método para ejecutar el giro
    @Override
    public void run() {
        girar();
    }

    // Método que contiene la lógica para girar la ruleta
    public void girar() {
        girando = true;
        Random random = new Random();

        try {
            // Simula el giro de los carretes
            for (int i = 0; i < 20; i++) { // El bucle determina cuánto tiempo gira
                simboloActual = simbolos[random.nextInt(simbolos.length)]; // Cambia el símbolo actual aleatoriamente
                Thread.sleep(100); // Pausa de 100ms entre cada cambio de símbolo
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Si se interrumpe el hilo, se detiene
        } finally {
            girando = false; // Al finalizar, marca el carrete como detenido
        }
    }

    // Método para verificar si la ruleta aún está girando
    public boolean estaGirando() {
        return girando;
    }
}