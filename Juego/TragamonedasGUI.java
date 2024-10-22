/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Juego;

import javax.swing.JFrame;
import FabricaDeMaquinasTragamonedas.FabricaDeMaquinasTragamonedas;
import FabricaDeMaquinasTragamonedas.Tragamonedas;
import Jugador.Jugador;
import Ruleta.Ruleta;
import Simbolo.Simbolo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
/**
 *
 * @author fabia
 */
public class TragamonedasGUI extends JFrame{
private JLabel[] slots;             // Etiquetas para mostrar los símbolos
    private JButton spinButton;          // Botón para girar
    private JButton exitButton;          // Botón para salir
    private JLabel saldoLabel;           // Etiqueta para mostrar el saldo
    private Jugador jugador;             // Jugador
    private Tragamonedas tragamonedas;   // Máquina tragamonedas

    public TragamonedasGUI() {
        // Configuración de la ventana
        setTitle("Máquina de Tragamonedas");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Inicialización del jugador
        jugador = new Jugador(100); // saldo inicial
        tragamonedas = FabricaDeMaquinasTragamonedas.crearMaquinaTragamonedas("clásica", jugador);

        // Panel de slots
        JPanel slotPanel = new JPanel();
        slotPanel.setLayout(new GridLayout(1, 3)); // 3 carretes
        slots = new JLabel[3];

        // Inicializar etiquetas de los símbolos
        for (int i = 0; i < slots.length; i++) {
            slots[i] = new JLabel();
            slots[i].setHorizontalAlignment(SwingConstants.CENTER);
            slots[i].setIcon(new ImageIcon("C:\\Users\\fabia\\Documents\\NetBeansProjects\\LabTragaMonedas\\src\\Icons\\cereza (1).png")); // Imagen inicial
            slots[i].setIcon(new ImageIcon("C:\\Users\\fabia\\Documents\\NetBeansProjects\\LabTragaMonedas\\src\\Icons\\estrella.png"));
            slots[i].setIcon(new ImageIcon("C:\\Users\\fabia\\Documents\\NetBeansProjects\\LabTragaMonedas\\src\\Icons\\lingotes-de-oro.png"));
            slots[i].setIcon(new ImageIcon("C:\\Users\\fabia\\Documents\\NetBeansProjects\\LabTragaMonedas\\src\\Icons\\siete.png"));
            slots[i].setIcon(new ImageIcon("C:\\Users\\fabia\\Documents\\NetBeansProjects\\LabTragaMonedas\\src\\Icons\\uvas.png"));
            slotPanel.add(slots[i]);
        }

        add(slotPanel, BorderLayout.CENTER);

        // Panel para botones y saldo
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        saldoLabel = new JLabel("Saldo: " + jugador.obtenerSaldo() + "€");
        controlPanel.add(saldoLabel);

        // Botón para girar
        spinButton = new JButton("Girar");
        spinButton.addActionListener(new SpinAction());
        controlPanel.add(spinButton);

        // Botón para salir
        exitButton = new JButton("Salir");
        exitButton.addActionListener(e -> System.exit(0));
        controlPanel.add(exitButton);

        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class SpinAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (jugador.obtenerSaldo() > 0) {
                jugador.deducirSaldo(10); // Deducir costo por giro
                saldoLabel.setText("Saldo: " + jugador.obtenerSaldo() + "€"); // Actualizar saldo
                Ruleta[] ruletas = tragamonedas.getCarretes();

                // Crear un hilo para girar
                new Thread(() -> {
                    for (Ruleta ruleta : ruletas) {
                        new Thread(ruleta).start(); // Girar cada ruleta
                    }

                    // Esperar un momento y actualizar la interfaz
                    try {
                        Thread.sleep(2000); // Simula el tiempo de giro
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }

                    // Actualizar la interfaz con los símbolos finales
                    for (int i = 0; i < ruletas.length; i++) {
                        Simbolo simbolo = ruletas[i].getSimboloActual();
                        String nombreSimbolo = simbolo.obtenerNombre().toLowerCase(); // Nombre del símbolo en minúsculas
                        slots[i].setIcon(new ImageIcon("icons/" + nombreSimbolo + ".png")); // Cambiar el ícono
                    }

                    // Verificar si se ha agotado el saldo
                    if (jugador.obtenerSaldo() <= 0) {
                        JOptionPane.showMessageDialog(null, "No tienes saldo suficiente.");
                        spinButton.setEnabled(false); // Desactivar el botón de girar
                    }
                }).start();
            } else {
                JOptionPane.showMessageDialog(null, "No tienes saldo suficiente.");
            }
        }
    }

    public static void main(String[] args) {
        new TragamonedasGUI();
    }
}
