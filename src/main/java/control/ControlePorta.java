package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.swing.JOptionPane;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

/**
 * @author Danyllo
 */
public class ControlePorta implements SerialPortEventListener {

	private InputStream serialInp;
	private BufferedReader input;
	
	private OutputStream serialOut;
	
	private int taxa;
	private String portaCOM;
	
	private String lastInputValue;
	private SerialPort port;

	/**
	 * Construtor da classe ControlePorta
	 * 
	 * @param portaCOM
	 *            - Porta COM que sera utilizada para enviar os dados para o
	 *            arduino
	 * @param taxa
	 *            - Taxa de transferencia da porta serial geralmente e 9600
	 */
	public ControlePorta(String portaCOM, int taxa) {
		this.portaCOM = portaCOM;
		this.taxa = taxa;
		this.initialize();
	}

	/**
	 * Medoto que verifica se a comunicacao com a porta serial esta ok
	 */
	private void initialize() {
		try {
			// Define uma variavel portId do tipo CommPortIdentifier para
			// realizar a comunicacao serial
			CommPortIdentifier portId = null;
			try {
				// Tenta verificar se a porta COM informada existe
				portId = CommPortIdentifier.getPortIdentifier(this.portaCOM);
			} 
			catch (NoSuchPortException npe) {
				// Caso a porta COM nao exista sera exibido um erro
				JOptionPane.showMessageDialog(null, "Porta COM nao encontrada.", "Porta COM", JOptionPane.PLAIN_MESSAGE);
			}
			
			// Abre a porta COM
			port = (SerialPort) portId.open("Comunicacao serial", this.taxa);
			
			serialInp = port.getInputStream();
			input = new BufferedReader(new InputStreamReader(serialInp));
			
			serialOut = port.getOutputStream();
			
			port.setSerialPortParams(this.taxa, // taxa de transferencia da porta serial
					SerialPort.DATABITS_8, // taxa de 10 bits 8 (envio)
					SerialPort.STOPBITS_1, // taxa de 10 bits 1 (recebimento)
					SerialPort.PARITY_NONE); // receber e enviar dados
			
			// add event listeners
			port.addEventListener(this);
			port.notifyOnDataAvailable(true);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				this.lastInputValue= input.readLine();
				//System.out.println(lastInputValue);
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
		// Ignore all the other eventTypes, but you should consider the other ones.
	}
	
	/**
	 * Metodo que fecha a comunicacao com a porta serial
	 */
	public void close() {
		try {
			serialInp.close();
			serialOut.close();
			
			port.removeEventListener();
			port.close();
		} 
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Nao foi possivel fechar porta COM.", "Fechar porta COM", JOptionPane.PLAIN_MESSAGE);
		}
	}

	/**
	 * @param opcao
	 *            - Valor a ser enviado pela porta serial
	 */
	public void enviaDados(int opcao) {
		try {
			serialOut.write(opcao);// escreve o valor na porta serial para ser enviado
		} 
		catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Nao foi possivel enviar o dado. ", "Enviar dados", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	public String getLastInputValue() {
		return lastInputValue;
	}

	public void setLastInputValue(String lastInputValue) {
		this.lastInputValue = lastInputValue;
	}

}