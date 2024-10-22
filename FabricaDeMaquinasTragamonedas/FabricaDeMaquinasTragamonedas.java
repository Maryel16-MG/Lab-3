/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FabricaDeMaquinasTragamonedas;

import Jugador.Jugador;
import Ruleta.Ruleta;
import Simbolo.Simbolo;

/**
 *
 * @author user
 */
public class FabricaDeMaquinasTragamonedas {
     public static MaquinaTragamonedas crearMaquinaTragamonedas(String tipo, Jugador jugador) {
        if (tipo.equals("clásica")) {
            Ruleta[] ruletas = { new Ruleta(crearSimbolosClasicos()), new Ruleta(crearSimbolosClasicos()), new Ruleta(crearSimbolosClasicos()) };
            return new MaquinaTragamonedas(ruletas, jugador);
        }
        // Puedes crear diferentes tipos con más ruletas o temáticas.
        return null;
    }

    private static Simbolo[] crearSimbolosClasicos() {
        return new Simbolo[]{ new Simbolo("Cereza"), new Simbolo("Limón"), new Simbolo("Siete") };
    }
    }

