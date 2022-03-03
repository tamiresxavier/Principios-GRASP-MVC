package model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Venda {

	private Date data;

	private List<ItemVenda> itensVenda = new ArrayList<>();
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	/*
	 * TODO GRASP.01
	 * 1. Este método objetiva guardar todos ItemVenda, mas assume que os objetos da classe {ItemVenda} seriam obtidos ou criados pelo 
	 * próprios códigos clientes. Assumamos que objetos {ItemVenda} possuem o seu ciclo de vida (de sua instanciação a sua destruição) 
	 * gerido ou determinado por um objeto da classe {Venda}.  Também sabemos que um objeto {ItemVenda} depende de uma associação
	 * com um objeto {Produto} para fazer sentido, indicando-lhe uma respectiva quantidade no contexto da {Venda} da qual  vier a fazer parte.
	 * 
	 * 2.  Considerando o contexto supracitado, poderíamos ficar em dúvida sobre deixar a assinatura e implementação deste método
	 * tal como está ou se deveríamos modificá-lo para que códigos clientes deste método fiquem mais responsáveis por ter que 
	 * criar e configurar objetos {ItemVenda} no processo de adicioná-los a um objeto {Venda}.  
	 * 
	 * ---------- 
	 * [PERGUNTA A] 
	 * Poderíamos aderir a qual princípio de atribuição de responsabilidade a objetos (GRASP) aqui e por quê? 
	 * Relate as vantagens de sua escolha, considerando controle de acoplamento, coesão e reuso.
	 * [O criador (Creator), porque ao aderir esse princípio, o cliente acaba sendo poupado de instanciar objetos e
	 * configurá-los, deixando o código mais reutilizável no adicionar. Sem contar, que a coesão da classe Venda 
	 * não estaria abalada e ao mesmo tempo retiraria um foco de acoplamento presente antes, do código cliente.]
	 * ----------
	 */
	public void addItemVenda(int quantidade, Produto produto) {
		itensVenda.add(new ItemVenda(quantidade, produto));	
	}

	public float getTotal() {
		float total = 0;
		for (ItemVenda itemVenda : this.itensVenda) {
			total += itemVenda.getSubtotal();
		}
		return total;
	}
	
	/*
	 * TODO GRASP.02
	 * 1. Este método  objetiva calcular o subtotal de uma {Venda}, que é a quantidade do produto multiplicada pelo preço do mesmo.
	 * Essas informações estão disponíveis no objeto {ItemVenda}.  Veja que esse código é do tipo uma única linha, muito simples, 
	 * apenas aplicando uma multiplicação sobre os valores.
	 * 
	 * 2. Apesar de simples, o código atual é forçado a acessar um objeto da classe {Produto} para obter-lhes o preço. Com isso,
	 * percebemos que {Venda.getSubtotal()} gera um foco de acoplamento adicional com essa classe e poderíamos nos questionar
	 * se haveria uma forma de evitarmos isso. Será que {Venda} deve se acoplar assim com {Produto} para ser responsável por calcular
	 * um subtotal de um {ItemVenda} com o qual já está associada?  Este método deveria ficar realmente aqui, sendo uma {Venda} apta
	 * em termos de acesso a informações para prover tal serviço?
	 *   
	 * ---------- 
	 * [PERGUNTA B] 
	 * Poderíamos aderir a qual princípio de atribuição de responsabilidade a objetos (GRASP) aqui e por quê? 
	 * Relate as vantagens de sua escolha, considerando controle de acoplamento, coesão e reuso.
	 * [O baixo acoplamento, porque antes de aplicar esse princípio havia um foco de acoplamento, já que Venda 
	 * estava forçando acesso ao um objeto pertencente da classe Produto e após aplicado o baixo acomplamento,
	 * houve uma ótima compreensão e dimunuição de códigos desnecessários dentro desta classe Venda. Com isso,
	 * a responsabilidade do método getSubtotal() foi passada para a classe ItemVenda.]
	 * ----------
	 */
	

	/*
	 * TODO GRASP.03
	 * 1. O objetivo de um objeto da classe {Venda} é o de ABSTRTAIR o conjunto de informações sobre uma compra realizada, reunindo dados
	 * sobre produtos e quantidades adquiridas em memória. Com esse entendimento, assumamos que qualquer código
	 * e métodos sobre {Venda} deve focar apenas em garantir o acesso e a validade desses dados, em memória.  
	 * 
	 * 2. Este método possui código que permite elaborar uma nota fiscal com base na {Venda} em texto e enviá-lo por e-mail 
	 * e resolvemos num primeiro momento responsabilizar a classe {Venda} por provê-lo, já que é "especialista na informação" por
	 * possuir o acesso a todos os dados necessários a implementação disso e para que esse código fique reusável (invocável) por aqueles 
	 * códigos clientes que desejarem isso. 
	 *   
	 * 3.  Entretanto, percebemos que esse código acaba assumindo a responsabilidade questionável de acessar
	 * objetos da API JavaMail e para piorar, nem sempre códigos clientes atuais ou futuros de objetos {Venda} podem querer emitir 
	 * nota fiscal sobre uma {Venda} ou poderiam se interssar sobre outras formas de geração ou envio. Seria plausível que esta 
	 * classe {Venda} permanecesse coesa perante todos os seus  códigos clientes atuais ou futuros assumindo a responsabilidade de 
	 * "enviar e-mail", ainda que os dados sejam dela mesma? Será que qualquer código baseado em dados de {Venda} deva realmente 
	 * ficar nela para oportunizar que códigos clientes possam convenientemente reutilizá-lo?  Para se refletir esse dilema, partamos
	 * de um outro exemplo similar: seria plausível que devido a um código cliente precisar representar um objeto {Venda} em tela 
	 * usando Java Swing devamos prover métodos para na própria {Venda} para a conveniência desse cliente, embora muitos outros 
	 * não vejam a {Venda} como útil para isso? 
	 * 
	 * ---------- 
	 * [PERGUNTA C] 
	 * Poderíamos aderir a qual princípio de atribuição de responsabilidade a objetos (GRASP) aqui e por quê? 
	 * Relate as vantagens de sua escolha, considerando controle de acoplamento, coesão e reuso.
	 * [A alto coesão, porque como esse método é questionável em relação ao acesso de objetos da API JavaMail e
	 * também nem sempre códigos clientes atuais ou possivelmente futuros de objetos pertencentes nesta classe 
	 * Venda solicitarão uma emissão da nota fiscal provida sobre uma Venda. É mais viável que esse metódo 
	 * seja deslocado para outra classe, na qual, já criada NotaFiscal. Isso, causaria mais coesão. Nesse modo, 
	 * evitamento de acoplamento.]
	 * ----------
	 */
	
	
}


