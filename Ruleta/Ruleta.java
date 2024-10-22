/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ruleta;

import java.util.Random;

/**
 *
 * @author user
 */
public class Ruleta {
     private Simbolo[] simbolos;
    private Simbolo simboloActual;

    public  Ruleta(Simbolo[] simbolos) {
        this.simbolos = simbolos;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            while (true) {
                int indice = random.nextInt(simbolos.length);
                simboloActual = simbolos[indice];
                System.out.println("Carrete: " + simboloActual.getNombre());
                Thread.sleep(200); // Pausa para simular el giro
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Simbolo getSimboloActual() {
        return simboloActual;
    }
    
}
