package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import model.LedOperationsEnum;
import control.Arduino;

/**
 * @author Danyllo
 */
public class LedArduinoCliente extends JFrame implements ActionListener {

	private static final long serialVersionUID = -5313060376526219954L;
	
	private JButton btLigar;
	private JButton btDesligar;
	private JButton btFechar;
	
	public LedArduinoCliente(){
		super();
		getContentPane().setLayout(null);
		
		this.setSize(600, 500);
		this.setLocation(300, 80);
		this.getContentPane().setBackground(Color.WHITE);
			
		this.btLigar = new JButton("Ligar");
		this.btLigar.setBounds(10, 50, 150, 30);
		this.btLigar.addActionListener(this);
		this.add(btLigar);
		
		this.btDesligar = new JButton("Desligar");
		this.btDesligar.setBounds(10, 150, 150, 30);
		this.btDesligar.addActionListener(this);
		this.add(btDesligar);
		
		this.btFechar = new JButton("Cancelar");
		this.btFechar.setBounds(10, 250, 150, 30);
		this.btFechar.addActionListener(this);
		this.add(btFechar);
		
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent source) {
		if(source.getSource() == this.btLigar){
			Arduino conn = Arduino.getSingleton();
			conn.comunicacaoArduino(LedOperationsEnum.LIGAR);
		}
		
		if(source.getSource() == this.btDesligar){
			Arduino conn = Arduino.getSingleton();
			conn.comunicacaoArduino(LedOperationsEnum.DESLIGAR);
		}
	}

	
	public static void main(String[] args) {
		new LedArduinoCliente();
	}

}
