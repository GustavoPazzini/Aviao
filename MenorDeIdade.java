public class MenorDeIdade extends Pessoa//A classe MenorDeIdade recebe os atributos e metodos da classe Pessoa, utilizando herança atraves do comando extends(filha)
{//Declaracao de atributos
	private String Autorizacao;
	
	public MenorDeIdade(String nome, int idade, Assento assento, String autorizacao)//Metodo construtor
	{
		super(nome,idade,assento);//Armazena os dados na classe pai(Pessoa) atraves do comando super
		this.Autorizacao=autorizacao;//Recebe uma string com a autorização
	}
	
	public void setAutorizacao(String aut)//Metodo set(mutante) para alterar o atributo Autorizacao
	{
		this.Autorizacao = aut;
	}
	public String getAutorizacao()//Metodo get(acessor) que retorna o atributo Autorizacao
	{
		return Autorizacao;
	}
}
