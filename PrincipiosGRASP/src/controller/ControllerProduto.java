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
	 * visualização (view).
	 * 
	 *  2. Tenha em mente que as assinaturas (nome e parametrização) desses métodos controladores devem aludir a implementação
	 *  do que poderia ser acionado e devolvido pela camada de negócio em virtude de um evento de interação consolidado na interface
	 *  por algum usuário.  
	 *  
	 *  3. É muito comum na literatura vermos variantes do conceito de controlador. Por exemplo, teremos um controller fachada 
	 *  (façade controller) se abstrairmos o sistema como um todo ou um submódulo dele em um conjunto de métodos. Teremos
	 *  um controler de ator (role controller) se dispusermos métodos sobre o que um tipo de usuário específico do sistema pode fazer
	 *  quando estiver ativo. Aqui seremos diretos e pragmáticos sobre o entendimento GRASP essencial do papel de uma classe controller, 
	 *  que é o de prover métodos capazes de receber dados presentes na interface e dar-lhes um tratamento via acionamento de uma
	 *  operação do sistema, sem contaminar o código de interface gráfica (view) com o da lógica de negócio (model ou business logic) 
	 *  e vice -versa, pelas razões apresentadas em aula.
	 * 
	 *  4. A depender do resultado do acionamento da operação na camada model pelo controller, o respectivo controller pode repassar
	 *  o resultado dessa operação para que possa ser apresentada na view (mensagem, objetos atualizados, etc.). Controllers assim
	 *  costumam conhecer um ou mais objetos da view que sejam capazes de prover operações para apresentar esse resultado em tela e 
	 *  esse acoplamento do controller com a view para por aí.  Verifique na sua implementação a conveniência deste controlador 
	 *  manter referências (como atributos da classe) para objetos view e /ou model envolvidos no processo de "controlar" eventos.
	 *  
	 *  5. Na parametrização de entrada e no retorno de métodos que vierem a ser definidos neste controller, você pode optar pelo 
	 *  recepção de tipos primitivos apenas, no lugar de passar objetos de classe da camada model a fim de reduzir o acoplamento, fazendo-se
	 *  as devidas adaptações no processo de ida (controller acionando operação model) e de volta (controller repassando o resultado da operação
	 *  do model para a view, para exibição). Isso não é imposto pelo GRASP Controller, depende das conveniências e vantagens, sendo
	 *  opcional tal adesão nesta prática.
	 *  
	 *  6. Elimine focos de acoplamento na classe da camada view {TelaCadastroProduto} que ela porventura possua com classes 
	 *  da camada model, presentes em tratadores handlers/observers/listener, aderindo a uma substituição em seus códigos para que acesse 
	 *  e invoquem o que for conveniente deste controller.
	 *  
	 * [PERGUNTA D] 
	 * Poderíamos aderir a qual princípio de atribuição de responsabilidade a objetos (GRASP) aqui e por quê? 
	 * Relate as vantagens de sua escolha, considerando controle de acoplamento, coesão e reuso.
	 * [O especialista da informação, porque o ControllerProduto controla/coordena o acesso a todos os dados
	 *  necessários a implementação, iniciando ações na TelaCadstroProduto. Sem mencionar, que conhece os dados 
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
