import java.util.ArrayList;//Importando classe para trabalhar com ArrayList

public class Idoso extends Pessoa//A classe Adulto recebe os atributos e metodos da classe Pessoa, utilizando heranca atraves do comando extends(filha)
{
	public Idoso(String nome, int idade, Assento assento)//Metodo construtor
	{
		super(nome,idade,assento);//Armazena os dados na classe pai(Pessoa) atraves do comando super
	}
	public void Reservar(Assento assento,Assento [][]listAssento, ArrayList<Pessoa> listPessoa)//Metodo reservar
	{
		listAssento[assento.getFileira()][assento.getCadeira()].setCadeira(1);//Mudando a matriz de assentos (cadeira) na posicao escolhida para 1
		listAssento[assento.getFileira()][assento.getCadeira()].setFileira(1);//Mudando a matriz de assentos (fileira) na posicao escolhida para 1
		
		System.out.print("\n\nCADASTRO (PARA IDOSO) E RESERVA CONCUIDOS COM SUCESSO !!! ");
	}
}
