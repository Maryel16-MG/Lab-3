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

    public Ruleta[] getCarretes() {
        return carretes;
    }

    public void girar() {
        // Inicia los hilos para cada carrete
        for (Ruleta carrete : carretes) {
            new Thread(carrete).start();
        }
        
        // Espera hasta que todos los carretes se detengan
        boolean todosCarretesDetenidos = false;
        while (!todosCarretesDetenidos) {
            todosCarretesDetenidos = true;
            for (Ruleta carrete : carretes) {
                if (carrete.estaGirando()) {
                    todosCarretesDetenidos = false;
                    break; // Sale del bucle si algún carrete sigue girando
                }
            }
            
            try {
                Thread.sleep(50); // Evita una espera activa intensa
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        // Muestra el resultado cuando todos los carretes se han detenido
        mostrarResultado();
    }

    private void mostrarResultado() {
        System.out.println("Resultado del giro:");
        for (Ruleta carrete : carretes) {
            System.out.println(carrete.getSimboloActual().getNombre());
        }
    }

    public Jugador getJugador() {
        return jugador;
    }
}

   

