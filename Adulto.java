public class Adulto extends Pessoa//A classe Adulto recebe os atributos e metodos da classe Pessoa, utilizando heranca atraves do comando extends(filha)
{
	public Adulto(String nome, int idade, Assento assento)//Metodo construtor
	{
		super(nome,idade,assento);//Armazena os dados na classe pai(Pessoa) atraves do comando super
	}
}
