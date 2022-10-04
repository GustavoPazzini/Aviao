import java.util.ArrayList;//Importando ArrayList para construcao e manipulacao de ArrayList

public class Pessoa//Classe Pessoa (pai)
{//Declaracao dos atributos
	private String Nome;
	private int Idade;
	private Assento assento;
	
	public Pessoa(String nome, int idade, Assento assento)//Metodo construtor
	{
		this.Nome=nome;
		this.Idade=idade;
		this.assento=assento;
	}
	
	
	public void Reservar(Assento assento,Assento [][]listAssento, ArrayList<Pessoa> listPessoa)
	{//Metodo Reservar(), que recebe o assento escolhido, a lista de assentos e a lista de pessoa;

		listAssento[assento.getFileira()][assento.getCadeira()].setCadeira(1);//Mudando a matriz de assentos (cadeira) na posicao escolhida para 1
		listAssento[assento.getFileira()][assento.getCadeira()].setFileira(1);//Mudando a matriz de assentos (fileira) na posicao escolhida para 1
		//Pois, se o lugar tiver 1, esta ocupado
		System.out.print("\n\nCADASTRO E RESERVA CONCUIDOS COM SUCESSO !!! ");
		
	}
	
	
	public void setNome(String n)//Metodo set(mutante) para alterar o atributo Nome
	{
		this.Nome = n;
	}
	public String getNome()//Metodo get(acessor) que retorna o atributo Nome
	{
		return Nome;
	}
	
	
	public void setIdade(int i)//Metodo set(mutante) para alterar o atributo Idade
	{
		this.Idade = i;
	}
	public int getIdade()//Metodo get(acessor) que retorna o atributo Idade
	{
		return Idade;
	}
	
	
	public void setAssento(Assento a)//Metodo set(mutante) para alterar o atributo Assento, do tipo Assento
	{
		this.assento = a;
	}
	public Assento getAssento()//Metodo get(acessor) que retorna o atributo assento, do tipo Assento
	{
		return assento;
	}
	
	
	public String getAssento_exibe()//Metodo get(acessor) que retorna a String que esta na classe Assento, utilizada no menu 2
	{
		return assento.concatena();//"(Fileira: X / Cadeira: X)."
	}
	
	public String pesquisa_pessoa()//Metodo get(acessor) que retorna a String que esta na classe Assento, utilizada para comparacao no menu 4
	{
		return assento.pesquisa_assento();//Retorna "XX" para saber se o assento esta livre "00"/"22" ou ocupado "11"
	}
	
	//Sendo X, o numero da fileira ou cadeira
	
	public void mostrar_dados(int i)//Metodo que recebe 1 se a idade for menor de 18, 2 se a idade for < 60 e 3 se nao for nenhum dos anteriores
	{
		if (i==1)//Crianca
		{
			System.out.println("\nNome: "+getNome()+ " / Idade: "+getIdade()+ " / Categoria: Menor de idade "+ " / Assento: " + getAssento_exibe());
		}
		else if(i==2)//Adulto
		{
			System.out.println("\nNome: "+getNome()+ " / Idade: "+getIdade()+ " / Categoria: Adulto "+ " / Assento: " + getAssento_exibe());
		}
		else//Idoso
		{
			System.out.println("\nNome: "+getNome()+ " / Idade: "+getIdade()+ " / Categoria: Idoso "+ " / Assento: " + getAssento_exibe());
		}
	}
	
}
