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
    private Simbolo[] simbolos;       
    private Simbolo simboloActual;   
    private boolean girando;          

    public Ruleta(Simbolo[] simbolos) {
        this.simbolos = simbolos;
        this.simboloActual = simbolos[0]; 
        this.girando = false;
    }

   
    public Simbolo getSimboloActual() {
        return simboloActual;
    }

   
    public Simbolo[] getSimbolos() {
        return simbolos;
    }

 
    @Override
    public void run() {
        girar();
    }

  
    public void girar() {
        girando = true;
        Random random = new Random();

        try {
            
            for (int i = 0; i < 20; i++) { 
                simboloActual = simbolos[random.nextInt(simbolos.length)];
                Thread.sleep(10); 
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            girando = false; 
        }
    }

 
    public boolean estaGirando() {
        return girando;
    }
}