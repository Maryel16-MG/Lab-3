package FabricaDeMaquinasTragamonedas;


import Jugador.Jugador;
import Ruleta.Ruleta;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author thyfa
 */
    public class Tragamonedas {
    private Ruleta[] carretes;
    private Jugador jugador;

    public Tragamonedas(Ruleta[] carretes, Jugador jugador) {
        this.carretes = carretes;
        this.jugador = jugador;
    }

    public void girar() {
        // Inicia los hilos para cada carrete
        for (Ruleta carrete : carretes) {
            new Thread((Runnable) carrete).start();
        }
        
        // Usa recursividad para continuar girando si el jugador tiene saldo
        if (jugador.obtenerSaldo() > 0) {
            System.out.println("¿Quieres jugar otra vez?");
            // Si el jugador decide continuar, llamamos recursivamente a girar()
            // Aquí puedes usar un Scanner o una forma de pedir la entrada del jugador
            girar();
        } else {
            System.out.println("No tienes saldo suficiente.");
        }
    }
}

