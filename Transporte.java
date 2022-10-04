public class Transporte//Classe Transporte
{
	private Assento [][]Assentos;//Declaracao do atributo de matriz de assentos e os demais
	private int TotalAssentos;
	private int AssentosNormais;
	private int AssentosPref;
	
	public Transporte(Assento [][]Assentos, int total, int normal, int preferencial)//Metodo construtor
	{
		this.Assentos=Assentos;
		this.TotalAssentos=total;
		this.AssentosNormais=normal;
		this.AssentosPref=preferencial;
	}
	
	public void setAssentos(Assento [][]a)//Metodo set(mutante) para alterar a matriz de assentos
	{
		this.Assentos = a;
	}
	public Assento[][] getAssentos()//Metodo get(acessor) que retorna uma matriz de assentos
	{
		return Assentos;
	}
	
	
	public void setTotalAssentos(int t)//Metodo set(mutante) para alterar o numero total de assentos
	{
		this.TotalAssentos = t;
	}
	public int getTotalAssentos()//Metodo get(acessor) que retorna o numero do total de assentos
	{
		return TotalAssentos;
	}
	
	
	public void setAssentosNormais(int n)//Metodo set(mutante) que retorna o numero de assentos normais
	{
		this.AssentosNormais = n;
	}
	public int getAssentosNormais()//Metodo get(acessor) que retorna o numero de assentos normais
	{
		return AssentosNormais;
	}
	
	
	public void setAssentosPref(int p)//Metodo set(mutante) que retorna o numero de assentos preferenciais
	{
		this.AssentosPref = p;
	}
	public int getAssentosPref()//Metodo get(acessor) que retorna o numero de assentos preferenciais
	{
		return AssentosPref;
	}
}
