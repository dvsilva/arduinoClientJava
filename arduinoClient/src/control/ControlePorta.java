package control;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JOptionPane;

/**
 * @author Danyllo
 */
public class ControlePorta {
	
	private OutputStream serialOut;
	private int taxa;
	private String portaCOM;

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
			SerialPort port = (SerialPort) portId.open("Comunicacao serial", this.taxa);
			serialOut = port.getOutputStream();
			port.setSerialPortParams(this.taxa, // taxa de transferencia da porta serial
					SerialPort.DATABITS_8, // taxa de 10 bits 8 (envio)
					SerialPort.STOPBITS_1, // taxa de 10 bits 1 (recebimento)
					SerialPort.PARITY_NONE); // receber e enviar dados
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que fecha a comunicacao com a porta serial
	 */
	public void close() {
		try {
			serialOut.close();
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
}
