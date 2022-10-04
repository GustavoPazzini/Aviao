public class Assento//Classe assento
{//Declaracao dos atributos
	private int Fileira;
	private int Cadeira;
	
	
	public Assento(int fileira, int cadeira)//Metodo construtor
	{
		this.Fileira=fileira;
		this.Cadeira=cadeira;
	}
	
	
	public void setFileira(int f)//Metodo set(mutante) para alterar o atributo Fileira
	{
		this.Fileira = f;
	}
	public int getFileira()//Metodo get(acessor) que retorna o atributo Fileira
	{
		return Fileira;
	}
	
	
	public void setCadeira(int c)//Metodo set(mutante) para alterar o atributo Cadeira
	{
		this.Cadeira = c;
	}
	public int getCadeira()//Metodo get(acessor) que retorna o atributo Cadeira
	{
		return Cadeira;
	}
	
	
	public String concatena()//Concatena Fileira e Cadeira numa string, sera chamada pela classe Pessoa, para mostrar ao usuario
	{
		return "(Fileira: "+getFileira()+" / Cadeira: "+getCadeira()+").";//Envia "(Fileira: X / Cadeira: X)."
	}
	public String pesquisa_assento()//Concatena Fileira e Cadeira numa string, sera chamada pela classe Pessoa para comparacao
	{
		return getFileira()+""+getCadeira();//Envia "00"/"22" para assento livre ou "11" para assento ocupado
	}
	
}
