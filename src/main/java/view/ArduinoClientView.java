package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import control.Arduino;
import model.OperationsEnum;

/**
 * @author Danyllo
 */
public class ArduinoClientView extends JFrame implements ActionListener {

	private static final long serialVersionUID = -5313060376526219954L;
	
	private JButton btLigar;
	private JButton btDesligar;
	private JButton btFechar;
	
	public ArduinoClientView(){
		super();
		this.setTitle("Controlando arduino pela porta serial!");
		GridLayout gridLayout = new GridLayout(1,3);
		this.getContentPane().setLayout(gridLayout);
		
		this.setSize(600, 300);
		this.setLocation(300, 80);
		this.getContentPane().setBackground(Color.WHITE);
			
		this.btLigar = new JButton("Ligar");
		this.btLigar.addActionListener(this);
		this.add(btLigar);
		
		this.btDesligar = new JButton("Desligar");
		this.btDesligar.addActionListener(this);
		this.add(btDesligar);
		
		this.btFechar = new JButton("Fechar");
		this.btFechar.addActionListener(this);
		this.add(btFechar);
		
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent source) {
		if(source.getSource() == this.btLigar){
			Arduino conn = Arduino.getSingleton();
			String result = conn.executa(OperationsEnum.LIGAR);
			JOptionPane.showMessageDialog(this, result);
		}
		
		if(source.getSource() == this.btDesligar){
			Arduino conn = Arduino.getSingleton();
			String result = conn.executa(OperationsEnum.DESLIGAR);
			JOptionPane.showMessageDialog(this, result);
		}
		
		if(source.getSource() == this.btFechar){
			 System.exit(0);
		}
	}

	public static void main(String[] args) {
		new ArduinoClientView();
	}

}