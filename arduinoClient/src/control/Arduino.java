package control;

import model.LedOperationsEnum;

/**
 * @author Danyllo
 */
public class Arduino {
	
	private ControlePorta arduino;
	private static Arduino singleton = null;

	/**
	 * Construtor da classe Arduino
	 */
	public Arduino() {
		arduino = new ControlePorta("COM4", 9600);
		// Windows - porta e taxa de transmissao
		// arduino = new ControlePorta("/dev/ttyUSB0",9600);
		//Linux - porta e taxa de transmissao
	}

	/**
	 * Envia o comando para a porta serial
	 * 
	 * @param button
	 *            - Botao que e clicado na interface Java
	 */
	public String comunicacaoArduino(LedOperationsEnum operacao) {
		
		// PARA LIGAR ENVIA 1
		// PARA DESLIGAR ENVIA 2
		
		if (operacao == LedOperationsEnum.LIGAR) {
			arduino.enviaDados(1);
			return "Led Ligado";
		} 
		else if (operacao == LedOperationsEnum.DESLIGAR) {
			arduino.enviaDados(2);
			return "Led Desligado";
		} 
		else {
			return "Nenhuma acao. Operacao Invalida";
		}
	}

	public static Arduino getSingleton() {
		if (singleton == null)
			singleton = new Arduino();
		
		return singleton;
	}
}
