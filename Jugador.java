/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author thyfa
 */
 public class Jugador {
    private int saldo;

    public Jugador(int saldo) {
        this.saldo = saldo;
    }

    public int obtenerSaldo() {
        return saldo;
    }

    public void deducirSaldo(int cantidad) {
        this.saldo -= cantidad;
    }

    public void agregarSaldo(int cantidad) {
        this.saldo += cantidad;
    }
 }