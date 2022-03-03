package controller;

import model.NotaFiscal;
import model.Produto;
import model.Venda;
import view.TelaCadastroProduto;

public class ControllerProduto {
	
	/* 
	 * TODO GRASP.04
	 * 
	 * 1. Implemente todos os metodos que este controlador GRASP deveria se responsabilizar por prover a objetos clientes da camada de 
	 * visualiza��o (view).
	 * 
	 *  2. Tenha em mente que as assinaturas (nome e parametriza��o) desses m�todos controladores devem aludir a implementa��o
	 *  do que poderia ser acionado e devolvido pela camada de neg�cio em virtude de um evento de intera��o consolidado na interface
	 *  por algum usu�rio.  
	 *  
	 *  3. � muito comum na literatura vermos variantes do conceito de controlador. Por exemplo, teremos um controller fachada 
	 *  (fa�ade controller) se abstrairmos o sistema como um todo ou um subm�dulo dele em um conjunto de m�todos. Teremos
	 *  um controler de ator (role controller) se dispusermos m�todos sobre o que um tipo de usu�rio espec�fico do sistema pode fazer
	 *  quando estiver ativo. Aqui seremos diretos e pragm�ticos sobre o entendimento GRASP essencial do papel de uma classe controller, 
	 *  que � o de prover m�todos capazes de receber dados presentes na interface e dar-lhes um tratamento via acionamento de uma
	 *  opera��o do sistema, sem contaminar o c�digo de interface gr�fica (view) com o da l�gica de neg�cio (model ou business logic) 
	 *  e vice -versa, pelas raz�es apresentadas em aula.
	 * 
	 *  4. A depender do resultado do acionamento da opera��o na camada model pelo controller, o respectivo controller pode repassar
	 *  o resultado dessa opera��o para que possa ser apresentada na view (mensagem, objetos atualizados, etc.). Controllers assim
	 *  costumam conhecer um ou mais objetos da view que sejam capazes de prover opera��es para apresentar esse resultado em tela e 
	 *  esse acoplamento do controller com a view para por a�.  Verifique na sua implementa��o a conveni�ncia deste controlador 
	 *  manter refer�ncias (como atributos da classe) para objetos view e /ou model envolvidos no processo de "controlar" eventos.
	 *  
	 *  5. Na parametriza��o de entrada e no retorno de m�todos que vierem a ser definidos neste controller, voc� pode optar pelo 
	 *  recep��o de tipos primitivos apenas, no lugar de passar objetos de classe da camada model a fim de reduzir o acoplamento, fazendo-se
	 *  as devidas adapta��es no processo de ida (controller acionando opera��o model) e de volta (controller repassando o resultado da opera��o
	 *  do model para a view, para exibi��o). Isso n�o � imposto pelo GRASP Controller, depende das conveni�ncias e vantagens, sendo
	 *  opcional tal ades�o nesta pr�tica.
	 *  
	 *  6. Elimine focos de acoplamento na classe da camada view {TelaCadastroProduto} que ela porventura possua com classes 
	 *  da camada model, presentes em tratadores handlers/observers/listener, aderindo a uma substitui��o em seus c�digos para que acesse 
	 *  e invoquem o que for conveniente deste controller.
	 *  
	 * [PERGUNTA D] 
	 * Poder�amos aderir a qual princ�pio de atribui��o de responsabilidade a objetos (GRASP) aqui e por qu�? 
	 * Relate as vantagens de sua escolha, considerando controle de acoplamento, coes�o e reuso.
	 * [O especialista da informa��o, porque o ControllerProduto controla/coordena o acesso a todos os dados
	 *  necess�rios a implementa��o, iniciando a��es na TelaCadstroProduto. Sem mencionar, que conhece os dados 
	 *  encapsulados e os objetos relacionados.]
	 * 
	 * */
 
	private TelaCadastroProduto view;

	public ControllerProduto(TelaCadastroProduto view) {
		this.view = view;
	} 
	
	public void realizarCadastroProduto() {
		
		String nomeProduto = view.getFieldNome().getText();
		if (nomeProduto == null || nomeProduto.isEmpty()) {
			view.exibirMensagem("Informe um nome para o produto", true);
			return;
		}

		float valorProduto = 0;
		try {
			Float.parseFloat(view.getFieldPreco().getText());
		} catch (Exception exception) {
			view.exibirMensagem("Preco deve usar ponto para separar casas decimais e conter somente numeros", true);
			return;
		}
		

		Produto produto = new Produto(nomeProduto, valorProduto);
	
		Venda venda = new Venda();

		venda.addItemVenda(1, produto);
		
		NotaFiscal notaFiscal = new NotaFiscal();
		

		if (notaFiscal.enviarNotaFiscalPorEmail(venda)) {
			view.exibirMensagem("Venda registrada com sucesso!", false);
		} else {
			view.exibirMensagem("Erro ao registrar venda (nao foi possivel contatar o servidor de email para envio da nota fiscal ou login e senha do servidor estao incorretos)", true);
		}
		
	}

}
