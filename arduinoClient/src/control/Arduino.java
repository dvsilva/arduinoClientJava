package control;

import model.OperationsEnum;

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
	public String executa(OperationsEnum operacao) {
		
		// PARA LIGAR ENVIA 1
		// PARA DESLIGAR ENVIA 2
		
		if (operacao == OperationsEnum.LIGAR) {
			arduino.enviaDados(1);
			return "Ligado";
		} 
		else if (operacao == OperationsEnum.DESLIGAR) {
			arduino.enviaDados(2);
			return "Desligado";
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
