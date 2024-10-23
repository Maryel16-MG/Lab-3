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
   private JLabel[] slots;             
    private JButton spinButton;          
    private JButton exitButton;         
    private JLabel saldoLabel;           
    private Jugador jugador;             
    private Tragamonedas tragamonedas;  
    public TragamonedasGUI() {
       
        setTitle("Máquina de Tragamonedas");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        jugador = new Jugador(100); 
        tragamonedas = FabricaDeMaquinasTragamonedas.crearMaquinaTragamonedas("clásica", jugador);

        
        JPanel slotPanel = new JPanel();
        slotPanel.setLayout(new GridLayout(1, 3)); 
        slots = new JLabel[3];

        for (int i = 0; i < slots.length; i++) {
            slots[i] = new JLabel();
            slots[i].setHorizontalAlignment(SwingConstants.CENTER);
            slots[i].setIcon(new ImageIcon("C:\\Users\\thyfa\\OneDrive\\Documentos\\java\\LabTragaMonedas\\LabTragaMonedas\\src\\Icons\\cereza (1).png")); // Imagen inicial
            slots[i].setIcon(new ImageIcon("C:\\Users\\thyfa\\OneDrive\\Documentos\\java\\LabTragaMonedas\\LabTragaMonedas\\src\\Icons\\estrella.png"));
            slots[i].setIcon(new ImageIcon("C:\\Users\\thyfa\\OneDrive\\Documentos\\java\\LabTragaMonedas\\LabTragaMonedas\\src\\Icons\\lingotes-de-oro.png"));
            slots[i].setIcon(new ImageIcon("C:\\Users\\thyfa\\OneDrive\\Documentos\\java\\LabTragaMonedas\\LabTragaMonedas\\src\\Icons\\siete.png"));
            slots[i].setIcon(new ImageIcon("C:\\Users\\thyfa\\OneDrive\\Documentos\\java\\LabTragaMonedas\\LabTragaMonedas\\src\\Icons\\uvas.png"));
            slotPanel.add(slots[i]);
        }

        add(slotPanel, BorderLayout.CENTER);

        
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        saldoLabel = new JLabel("Saldo: " + jugador.obtenerSaldo() + "€");
        controlPanel.add(saldoLabel);

       
        spinButton = new JButton("Girar");
        spinButton.addActionListener(new SpinAction());
        controlPanel.add(spinButton);

       
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
            jugador.deducirSaldo(10); 
            saldoLabel.setText("Saldo: " + jugador.obtenerSaldo() + "€"); 
            Ruleta[] ruletas = tragamonedas.getCarretes();

            
            new Thread(() -> {
               
                Thread[] hilos = new Thread[ruletas.length];
                Simbolo[] resultados = new Simbolo[ruletas.length];

               
                for (int i = 0; i < ruletas.length; i++) {
                    final int index = i; 
                    hilos[i] = new Thread(() -> {
                        ruletas[index].girar(); 
                        resultados[index] = ruletas[index].getSimboloActual(); 
                    });
                    hilos[i].start(); 
                }

                
                for (Thread hilo : hilos) {
                    try {
                        hilo.join(); 
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }

               
                SwingUtilities.invokeLater(() -> {
                    for (int i = 0; i < ruletas.length; i++) {
                        String nombreSimbolo = resultados[i].obtenerNombre().toLowerCase(); 

                        switch (nombreSimbolo) {
                            case "cereza":
                                slots[i].setIcon(new ImageIcon("C:\\Users\\thyfa\\OneDrive\\Documentos\\java\\LabTragaMonedas\\LabTragaMonedas\\src\\Icons\\cereza (1).png"));
                                break;
                            case "estrella":
                                slots[i].setIcon(new ImageIcon("C:\\Users\\thyfa\\OneDrive\\Documentos\\java\\LabTragaMonedas\\LabTragaMonedas\\src\\Icons\\estrella.png"));
                                break;
                            case "lingote":
                                slots[i].setIcon(new ImageIcon("C:\\Users\\thyfa\\OneDrive\\Documentos\\java\\LabTragaMonedas\\LabTragaMonedas\\src\\Icons\\lingotes-de-oro.png"));
                                break;
                            case "siete":
                                slots[i].setIcon(new ImageIcon("C:\\Users\\thyfa\\OneDrive\\Documentos\\java\\LabTragaMonedas\\LabTragaMonedas\\src\\Icons\\siete.png"));
                                break;
                            case "uvas":
                                slots[i].setIcon(new ImageIcon("C:\\Users\\thyfa\\OneDrive\\Documentos\\java\\LabTragaMonedas\\LabTragaMonedas\\src\\Icons\\uvas.png"));
                                break;
                            default:
                                
                                slots[i].setIcon(null);
                                break;
                        }
                    }

                    if (resultados[0].obtenerNombre().equals(resultados[1].obtenerNombre()) &&
                        resultados[0].obtenerNombre().equals(resultados[2].obtenerNombre())) {
                        JOptionPane.showMessageDialog(null, "¡Felicidades! ¡Has ganado!");
                        jugador.agregarSaldo(50); 
                        saldoLabel.setText("Saldo: " + jugador.obtenerSaldo() + "€"); 
                    }

                    
                    if (jugador.obtenerSaldo() <= 0) {
                        JOptionPane.showMessageDialog(null, "No tienes saldo suficiente.");
                        spinButton.setEnabled(false); 
                    }
                });
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

