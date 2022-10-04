//Importando classe para ler o que o usuario digitou e para fazer a lista de pessoas
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

public class Main
{
	public static void cls()//Metodo para limpar a tela
	{
		try {
        	if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        	}
        	else {
            System.out.print("\033\143");
        	}
    } catch (IOException | InterruptedException ex) {}
	}
	public static void alert()//Metodo para travar o menu e esperar o usuario digitar para retornar
	{
		System.out.print("\n\n\nDigite qualquer numero para voltar ao menu: ");
		Scanner sc = new Scanner(System.in);
		sc.nextInt();
	}
	
	public static void main (String[] args)
	{
		//////////////////////DIGITE AQUI PARA MUDAR O NUMERO DE FILEIRAS E CADEIRAS:
		int fileiras=5, cadeiras=15; //MINIMO 2,2 (ULTIMA FILEIRA SEMPRE VAI SER PREFERENCIAL)
		////////////////////////////////////////////////////////////////////////////
		int menu, fil_consult, cad_consult, i, flag=0, num_f=fileiras+1, num_c=cadeiras+1, normal_pref=0;//Declaracao de variaveis
		
		//OBS: num_f RECEBE FILEIRA +1, POIS NAO TRABALHO COM FILEIRA 0, MESMA COISA PARA num_c, SERA DE GRANDE IMPORTANCIA PARA O For INICIAR DO 1.
		
		int num_t= fileiras*cadeiras, num_n= num_t - (cadeiras), num_p= cadeiras;//Calculo para retornar o numero total de cadeiras, cadeiras normais e preferenciais
		String  nome_consult;
		
		ArrayList<Pessoa> listPessoa = new ArrayList<Pessoa>();//Criacao de uma lista de Pessoa
		Assento [][]listAssento = new Assento[num_f][num_c];//Criacao de uma Matriz de Assentos
		
		for (i=1;i<num_f;i++)//For para atribuir valor 0 aos assentos normais e 2 para preferenciais
		{
			for(int j=1;j<num_c;j++)
			{
				if(i==fileiras)
				{
					Assento a = new Assento(2,2);
					listAssento[i][j]=a;
				}
				else
				{
					Assento a = new Assento(0,0);
					listAssento[i][j]=a;
				}
			}
		}
		
		Transporte trans = new Transporte (listAssento, num_t, num_n, num_p);//Criacao de um transporte, recebendo os calculos e a Matriz de Assentos
		
		
		
		do//Loop infinito do menu
		{
			int n = listPessoa.size();//Atribuindo a variavel n, o tamanho da lista
			
			cls();//Limpando a tela
			Scanner sc = new Scanner(System.in);//Limpando o buffer da variavel sc
			System.out.println("***MENU***\n");
			System.out.print("1- Reservar um assento\n2- Consultar o total de passageiros\n3- Consultar o numero total de idosos, criancas e adultos\n");
			System.out.print("4- Consultar dados de um assento\n5- Consultar assentos livres\n0- Sair\n\nDigite a opcao desejada: ");
			menu= sc.nextInt();
		
			switch (menu)//Menu para acao escolhida pelo usuario
			{
				case 1://Cadastro e reserva

					cls();
					
					String nome, aut="";
					int idade, fil, cad;
					
					System.out.println("***CADASTRANDO***\n");
					sc = new Scanner(System.in);
					
					if(trans.getTotalAssentos()-n == 0)//Se o total de assentos menos o total da lista for 0, transporte lotado
					{
						System.out.print("\nNENHUM ASSENTO DISPONIVEL !!!");
						alert();//Para a tela e espera o usuario digitar qualquer numero
						break;//Retorna ao menu
					}
					
					System.out.print("\nNome completo (igual do RG): ");
					nome= sc.nextLine();
					sc = new Scanner(System.in);
					
					if(n!=0)//Se o tamanho da lista for diferente de zero, verifica se o nome ja esta cadastrado
					{
						for(i=0;i<n;i++)//For para percorrer a lista
						{
							Pessoa p = listPessoa.get(i);//p recebe os dados da pessoa da posicao de i(0,1,2,...)
							
							if(p.getNome().equals(nome))//Se o nome for igual, retorna ao menu
							{
								System.out.print("\n\nNOME JA CADASTRADO !!! ");
								flag=1;//Uso de flag, pois se usar break no For, ele para o For somente
							}
						}
					}
					
					if (flag == 1)//Se o nome ja esta cadastrado, zera a flag e volta pro menu
					{
						flag = 0;
						alert();
						break;
					}
					
					System.out.print("\n\nIdade (em anos): ");
					idade= sc.nextInt();
					
					if(idade<0)//Se a idade digitada for negativa, volta pro menu
					{
						System.out.print("\n\nIDADE NAO PODE SER NEGATIVA !!!");
						alert();
						break;
					}
					
					if(idade<60 && trans.getAssentosNormais()==0)//Se a idade da pessoa for menor que 60, e os assentos normais forem igual a 0
					{
						System.out.print("\n\nASSENTO NORMAL ESGOTADO !!!");
						alert();
						break;
					}
					
					if(idade>=60)//Se a idade for maior igual a 60, abre a possibilidade do idoso escolher se quer assento preferencial ou normal
					{
						System.out.print("\n\nDigite 1 para reservar um assento normal ou 2 para preferencial: ");
						normal_pref=sc.nextInt();
						
						if(normal_pref<1 || normal_pref>2)//Se o numero nao for condizente ao sugerido, retorna ao menu
						{
							System.out.print("\n\nESCOLHA ENTRE 1 E 2 !!!");
							alert();
							break;
						}
					}
					
					if(idade>=60 && trans.getAssentosNormais()==0 && normal_pref==1)//Se a idade for maior que 60, assentos normais forem 0 e normal_pref for 1(idoso escolhendo assento normal)
					{
						System.out.print("\n\nASSENTO NORMAL ESGOTADO !!!");
						alert();
						break;
					}
					
					else if(idade>=60 && trans.getAssentosPref()==0 && normal_pref==2)//Se a idade for maior que 60, assentos preferenciais forem 0 e normal_pref for 2(idoso escolhendo assento preferencial)
					{
						System.out.print("\n\nASSENTO PREFERENCIAL ESGOTADO !!!");
						alert();
						break;
					}
					
					
					if(idade<60 || normal_pref == 1)//Se idade for menor que 60 ou normal_pref = 1, significa que sera reservado assento normal
					{
						System.out.print("\n\nDigite a fileira que deseja reservar (entre 1 e "+(fileiras-1)+"): ");
						fil= sc.nextInt();
						System.out.print("\n\nDigite a cadeira que deseja reservar (entre 1 e "+(cadeiras)+"): ");
						cad= sc.nextInt();
						
						if((fil<1 || fil>fileiras-1) || (cad<1 || cad>cadeiras))//Se o numero nao for condizente ao sugerido, retorna ao menu
						{
							System.out.print("\n\nPOLTRONA INEXISTENTE !!!");
							alert();
							break;
						}
						
						if(listAssento[fil][cad].getCadeira()==1)//Se a cadeira estiver marcada com 1, esta ocupada
						{
							System.out.print("\n\nASSENTO OCUPADO !!!");
							alert();
							break;
						}
						
						Assento assento_cad = new Assento(fil,cad);//Criando assento_cad para transformar a fileira e cadeira escolhida em um tipo Assento
						System.out.print("\n\nCaso algum dado esteja errado, digite 9. Senao digite qualquer numero : ");
						flag= sc.nextInt();//flag para erro de digitacao dos dados
																
						if(flag==9)//Se o usuario digitar 9, o cadastro sera cancelado
						{
							flag=0;//Zerando a flag
							System.out.print("\n\nCADASTRO CANCELADO !!! ");
							alert();
							break;
						}
						
						if(idade<18)//Se a idade for menor de 18(crianca)
						{
							sc = new Scanner(System.in);
							String auto;
							int escolha_auto=1;
							System.out.print("\n\n\nTem autorizacao(1- Sim / 2- Nao): ");
							escolha_auto= sc.nextInt();
							
							if(escolha_auto<1 || escolha_auto>2)//Se o que foi digitado nao condizer ao que foi sugerido
							{
								System.out.print("\n\nESCOLHA UMA OPCAO VALIDA !!! ");
								alert();
								break;
							}
							
							if(escolha_auto==2)//Se nao tiver autorizacao, cancela o cadastro
							{
								escolha_auto=1;
								System.out.print("\n\nCADASTRO CANCELADO !!! ");
								alert();
								break;
							}
							sc = new Scanner(System.in);
							System.out.print("\n\nAutorizacao: ");//Pedindo a autorizacao da crianca para poder cadastrar
							auto=sc.nextLine();
							MenorDeIdade m = new MenorDeIdade(nome,idade,assento_cad,auto);//Adiciona a variavel p, uma nova pessoa com os dados cadastrados
							m.Reservar(assento_cad, listAssento, listPessoa);//Chama o metodo Reservar() na classe Pessoa
							listPessoa.add(m);//Adiciona a lista de pessoas na main a pessoa cadastrada
							trans.setAssentosNormais(trans.getAssentosNormais()-1);//Subtrai 1 dos assentos normais
						}
						
						else if (idade<60)//Se a idade for menor que 60(adulto)
						{
							Adulto a = new Adulto(nome,idade,assento_cad);
							a.Reservar(assento_cad, listAssento, listPessoa);
							listPessoa.add(a);
							trans.setAssentosNormais(trans.getAssentosNormais()-1);//Subtrai 1 de assentos normais
						}
						
						else//Senao sera idoso
						{
							Idoso ido = new Idoso(nome,idade,assento_cad);
							listPessoa.add(ido);//Coloco ele na lista antes
							trans.setAssentosNormais(trans.getAssentosNormais()-1);
							for(i=0;i<listPessoa.size();i++)//Percorro a lista
							{
								Pessoa p = listPessoa.get(i);//Atribuo a p as pessoas da lista
								if(nome.equals(p.getNome()))//Encontro o idoso que acaba de ser adicionado
								{
									p.Reservar(assento_cad, listAssento, listPessoa);//Faco sobreposicao com o reservar, onde o programa reserva pelo idoso
								}
							}
						}
					}
					
					else if (normal_pref == 2)//Se for assento preferencial, escolhe somente a cadeira, pois a fileira sera a ultima
					{
						System.out.print("\n\nDigite a cadeira que deseja reservar (entre 1 e "+(cadeiras)+"): ");
						cad= sc.nextInt();
						
						if(cad<1 || cad>cadeiras)//Se o numero nao for condizente ao sugerido, retorna ao menu
						{
							System.out.print("\n\nPOLTRONA INEXISTENTE !!!");
							alert();
							break;
						}
						
						if(listAssento[fileiras][cad].getCadeira()==1)//Se a cadeira estiver marcada com 1, esta ocupada
						{
							System.out.print("\n\nASSENTO OCUPADO !!!");
							alert();
							break;
						}
						
						Assento assento_cad = new Assento(fileiras,cad);//Criando assento_cad para transformar a fileira e cadeira escolhida em um tipo Assento
						System.out.print("\n\nCaso algum dado esteja errado, digite 9. Senao digite qualquer numero : ");
						flag= sc.nextInt();//flag para erro de digitacao dos dados
																
						if(flag==9)//Se o usuario digitar 9, o cadastro sera cancelado
						{
							flag=0;//Zerando a flag
							System.out.print("\n\nCADASTRO CANCELADO !!! ");
							alert();
							break;
						}
					
						Idoso ido = new Idoso(nome,idade,assento_cad);//Processo para adicionar o idoso na lista para a sobreposicao
						listPessoa.add(ido);
						trans.setAssentosPref(trans.getAssentosPref()-1);
						
						for(i=0;i<listPessoa.size();i++)
						{
							Pessoa p = listPessoa.get(i);
							if(nome.equals(p.getNome()))
							{
								p.Reservar(assento_cad, listAssento, listPessoa);//Sobreposicao de reservar
							}
						}
					}
					
					normal_pref=0;//Zera normal_pref
					flag=0;//Zera a flag, caso nao tenha existido erro de digitacao
					
					alert();
					
				break;
					
				case 2://Consultado pessoas cadastradas e mostrando o nome, idade, assento e sua categoria(crianca, adulto ou idoso)
				
					cls();
							
					if(n==0)//Se o total da lista de pessoas for 0, nao existe pessoas cadastradas
					{
						System.out.print("***CONSULTANDO***\n\nNAO EXISTE PESSOAS CADASTRADAS !!!");
						alert();
						break;
					}
					
					System.out.print("***CONSULTANDO***\n");
					System.out.println("\n\nTotal de passageiros: " + n);
					
					for(i=0;i<n;i++)//For para percorrer a lista e atribuir a p as pessoas da lista
					{
						Pessoa p = listPessoa.get(i);
						
						if(p.getIdade()<18)//Se for crianca, mostra a categoria crianca
						{
							p.mostrar_dados(1);//mostrar_dados esta contido na classe Pessoa
						}
						else if(p.getIdade()<60)//Se for crianca, mostra a categoria adulto
						{
							p.mostrar_dados(2);
						}
						else//Se for idoso, mostra a categoria idoso
						{
							p.mostrar_dados(3);
						}
					}
					
					alert();
						
				break;
					
				case 3://Mostrando a quantidade de criancas, adultos e idosos cadastrados
				
					cls();
						
					if(n==0)//Se o total da lista de pessoas for 0, nao existe pessoas cadastradas
					{
						System.out.print("***CONSULTANDO***\n\nNAO EXISTE PESSOAS CADASTRADAS !!!");
						alert();
						break;
					}
					
					//Mostrando o numero de pessoas cadastradas por categoria
					System.out.print("***CONSULTANDO***");
					int x=0,y=0,z=0;
					for(i=0;i<n;i++)//For para percorrer a lista e atribuir a p as pessoas da lista
					{
						Pessoa p = listPessoa.get(i);
						
						if(p.getIdade()<18)//Se for crianca
						{
							x++;
						}
						else if(p.getIdade()<60)//Se for adulto
						{
							y++;
						}
						else//Se for idoso
						{
							z++;
						}
					}
					System.out.println("\n\nTotal de passageiros por categoria:\n\nCriancas: "+x+"\nAdultos: "+y+"\nIdosos: " +z);
					
					alert();
					
				break;
					
				case 4://Consultando um assento
				
					flag=0;
					cls();
					System.out.print("***CONSULTANDO***\n");
					System.out.print("\n\nDigite a fileira que deseja consultar (Entre 1 e "+ fileiras +", sendo "+ fileiras +" a preferencial): ");
					fil_consult= sc.nextInt();
					System.out.print("\n\nDigite a cadeira que deseja consultar (Entre 1 e "+ cadeiras +"): ");
					cad_consult= sc.nextInt();
					Assento assento = new Assento(fil_consult,cad_consult);//Atribuindo a assento (uma variavel Assento), a fileira e a cadeira para pesquisa
					
					if(fil_consult<=0 || fil_consult>=num_f || cad_consult<=0 || cad_consult>=num_c)//Se o numero nao for condizente ao sugerido, retorna ao menu
					{
						System.out.print("\n\nFILEIRA OU CADEIRA INEXISTENTE !!! ");
						alert();
						break;
					}
					
					for(i=0;i<n;i++)//For para percorrer a lista
					{
						Pessoa p = listPessoa.get(i);//Atribuindo p que e variavel do tipo Pessoa, todos os dados da pessoa de posicao i
						
						if(p.pesquisa_pessoa().equals(fil_consult+""+cad_consult))//Se a pesquisa_pessoa retornar a fileira e a cadeira escolhida
						{
							System.out.print("\n\nAssento ocupado por "+p.getNome()+".");//Mostra assento ocupado com o nome da pessoa
						}
						else//Senao
						{
							flag++;
						}
					}
					
					if(flag == n)//Se a flag for igual ao tamanho da lista,significa que a lista foi toda percorrida e nao foi encontrada nenhuma pessoa com esse assento
					{
						System.out.print("\n\nAssento livre !!!");
					}
					
					flag=0;//Zera a flag, pois ela esta do tamanho da lista
					
					alert();
					
				break;
					
				case 5://Mostrando graficamente os assentos livres
				
					cls();
					System.out.print("***CONSULTANDO***\n");
					System.out.print("\nTOTAL DE ASSENTOS: "+ (trans.getTotalAssentos()));
					System.out.print("\n\nASSENTOS LIVRES: "+ (trans.getTotalAssentos()-n));//Assentos livres sera o total de assentos menos o tamanho da lista
					System.out.print("\n\nASSENTOS NORMAIS LIVRES: "+ (trans.getAssentosNormais()));
					System.out.print("\nASSENTOS PREFERENCIAIS LIVRES: "+ (trans.getAssentosPref()));
					System.out.print("\n\nOs assentos sao indicados por:\n\n0- Assento livre.\n1- Assento ocupado.\n2- Assento preferencial livre.\n");
						
					for (i=1;i<num_f;i++)//For para fazer graficamente os assentos
					{
						System.out.print("\n");
						for(int j=1;j<num_c;j++)
						{
							System.out.print(listAssento[i][j].getCadeira()+" ");//Mostrando somente a cadeira, pois se mostrasse o assento, ficaria 00
						}
					}
					alert();
						
				break;
					
				case 0://Saindo da aplicacao
					
					System.exit(0);
					sc.close();//Fechando a variavel sc
						
				break;
					
				default://Se for digitado qualquer numero diferente do menu
					System.out.print("\n\nEntrada invalida !!!");
					alert();
			}
			
		}while(true);//Loop infinito do menu ate o usuario escolher 0 para sair do sistema
	}
}
