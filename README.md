# Votação Simples

**Nesse projeto possui o objetivo de implementar um sistema de votação. Para facilitar o desenvolvimento de algumas funcionalidades foi disponibilizado um código inicial.**

```
public class main {  
  
  private static void showAllOptions() {  
	  System.out.println("=============================================");  
	  System.out.println("Escolha a opção que deseja:");  
	  System.out.println("1 - Votar");  
	  System.out.println("2 - Resultados");  
	  System.out.println("5 - Sair");  
	  System.out.println("=============================================");  
  }
  public static void main(String[] args) {
        VoteService voteService = new VoteService();
        Scanner scanner = new Scanner(System.in);
        Scanner votar = new Scanner(System.in);
        var selectedOption = 0;
        while( selectedOption != 5) {
            showAllOptions();
            selectedOption = scanner.nextInt();
            switch (selectedOption) {
                case 1: {
                    System.out.println("Votar");
                    break;
                }
                case 2: {
                    System.out.println("Resultado");
                    break;
                }
                case 5: {
                    System.out.println("Sair");
                    break;
                }
                default: {
                    System.out.println("Opção invalida");
                }
            }
        }
    }
}
```
Esta é a classe principal do nosso projeto, temos nela o método chamado showAllOptions() que é responsável por mostrar na tela as opções para interagir e o método main(), este segundo é responsável por controlar o fluxo de execução do programa.

No método main temos a criação de 3 objetos que serão utilizados no fluxo, que são: voteService, scanner e votar. A classe VoteService é reponsável por apresentar os "brothers" no paredão, para salvar os votos no "banco de dados" e mostrar os resultados. Quanto a classe Scanner, nesse caso foi utilizada para receber os dados de entrada do usuário.

Na próxima linha foi criado a variável *selectedOption*, que será utilizado no loop do while, e possui a responsabilidade de armazenar o número digitado em cada rodada. Este While irá ficar repetindo as opções até o usuário digitar a opção para sair (nesse caso o número 5).

Para cada "volta" do While, ele irá mostrar as opções de interação chamando o método showAllOptions() e irá aguardar até o usuário digitar alguma coisa. Depois digitado um valor, vamos para o switch e nele é para compararmos o valor digitado pelo usuário com as opções que temos disponiveis no sistema. Quando a comparação for bem sucedida, será executado o código dessa opção. Para exemplificar imagine que o usuário digitou 1, então a condição  do switch que irá executar é do case 1 (em outras palavras é como tivessemos if(selectedOption == 1).

```
public class VoteService {

    private MemoryDatabase memoryDatabase;

    public VoteService() {
        this.memoryDatabase = new MemoryDatabase();
    }

    public void showAllBrothers() {
        System.out.println("Mostar os participantes do paredão");
    }

    public Map<String, Integer> showResults() {
        return memoryDatabase.resultado();
    }

    public void votar(String meuVoto) {
        // Já existe o método para votar
        // pode utilizar this.memoryDatabase.votar("meuVoto");
    }
}
```

Na classe VoteService, acima já fizemos uma breve introdução das suas responsabilidades. Agora iremos detalhar cada um dos métodos. No início temos a criação de uma variável chamada memoryDatabase, que irá representar o nosso banco de dados. Em seguida temos o nosso construtor VoteService() é responsável por criar o nosso banco de dados. E finalmente temos os métodos showAllBrothers() que mostra os participantes do paredão dessa votação, showResults que retorna consolidação dos votos e o votar método chamado para gravar os votos no nosso banco de dados.

Pontos importantes:
* No método showAllBrothers é onde deverá conter os participantes do paredão. Exemplificando:
  Deverá mostrar algo parecido com isso: 1- XXXX, 2 - YYYY e 3 - ZZZZ.

* No método votar coloquei um comentário de como fazer o voto e importante ressaltar que é nele que deverá ser válidado se voto é válido ou não.

```
public class MemoryDatabase {

    private Map<String, Integer> database;

    public MemoryDatabase(Map<String, Integer> database) {
        this.database = database;
    }

    public MemoryDatabase() {
        database = new HashMap<>();
    }

    public void votar(String meuVoto) {
        if (database.containsKey(meuVoto)) {
            var qnt_votos = database.get(meuVoto);
            database.replace(meuVoto, ++qnt_votos);
        } else {
            database.put(meuVoto, 1);
        }
    }

    public Map<String, Integer> resultado() {
        return database;
    }
}

```
A última classe é o MemoryDatabase, é a abstração de um banco de dados. Temos variavél chamada database do tipo Map<String, Integer>. Essa estrutura de dados iremos estudar num futuro próximo, nesse momento não se preocupe. O importante saber dessa classe que irá armazenar e contabilizar os votos feitos durante a execução do programa.

Agora, a pergunta que fica o que precisamos implementar? Em alto nível é fazer um voto e mostrar o resultado.

O fluxo:
* Digitar 1 (iniciar fluxo de votação)
* Mostrar os usuários a serem votados
* Digitar o número do usuário
* Chamar o método de votar
* Válidar o número digitado
* Voto contabilizado
* Digitar 2 (iniciar fluxo do resultado)
* Mostrar o resultado dos votos

## Implementação do Fluxo

Conforme descrito o código já mostra as opções do sistema, então podemos passar direto para desenvolver o fluxo de votação.

Seguindo o fluxo descrito acima, precisamos mostrar os usuários a serem votados. Para isto será preciso chamar o método showAllBrothers da classe VoteService. Importante lembrar que na classe Main já temos o objeto criado, então é só chamar.

O trecho de código abaixo deve ser inserido na classe Main.
```
case 1: {
           System.out.println("Votar");
           voteService.showAllBrothers();
           break;
        }
```
Conforme havia dito o método showAllBrothers é responsavel por mostrar os usuários que estão no paredão, uma maneira de fazer isso é imprimindo na tela o código e o nome da pessoa.

O trecho de código abaixo deve ser inserido na classe VoteService.
```
    public void showAllBrothers() {
        System.out.println("Mostar os participantes do paredão");
        System.out.println("1 - XXXX");
        System.out.println("2 - YYYY");
        System.out.println("3 - ZZZZ");
    }
```

O próximo passo é receber o número digitado pelo usuário do sistema. Com as opções sendo mostradas iremos pedir para o usuário digitar em quem ele deseja votar. Como eu faço isso? Lembrando que já temos um exemplo no código disso (Usamos essa mesma lógica para escolhar as opções de votar, mostrar resultados ou sair). Iremos usar a classe Scanner (se você pensou isso, está correto), na classe Main já temos também o objeto para receber o voto que é o votar.

O trecho de código abaixo deve ser inserido na classe Main.
```
case 1: {
           System.out.println("Votar");
           voteService.showAllBrothers();
           meuVoto = votar.nextInt();
           break;
        }
```
Próxima etapa é chamar o método para votar, para isso precisamos chamar votar do voteService. Importante ressaltar que esse método recebe como parametro o voto. Na variavel meuVoto temos armazenado o valor digitado pelo usuário.

O trecho de código abaixo deve ser inserido na classe Main.
```
case 1: {
           System.out.println("Votar");
           voteService.showAllBrothers();
           meuVoto = votar.nextInt();
           voteService.votar(meuVoto);
           break;
        }
```
Nessa etapa deve ocorrer um erro, pois o método votar espera como parametro uma variavel do tipo String e estamos passando uma do tipo int. Nesse momento precisamos fazer uma conversão do valor digitado pelo usuário para uma String. Iremos usar o método valueOf da classe String, que fará essa conversão de **int** para **String**.

O trecho de código abaixo deve ser inserido na classe Main.
```
case 1: {
           System.out.println("Votar");
           voteService.showAllBrothers();
           meuVoto = votar.nextInt();
           meuVotoString = String.valueOf(meuVoto)
           voteService.votar(meuVoto);
           break;
        }
```
O próximo item é Válidar o número digitado, isto será tratado no método votar da classe VoteService. Para fazer essas validações, temos duas maneiras usando o IF e usando SWITCH. Nesse exemplo iremos utilizar novamente o switch para isso.

Aproveita o trecho abaixo para implementar também contabilização dos votos que basicamente é chamar essa linha de código *this.memoryDatabase.votar("meuVoto");*

O trecho de código abaixo deve ser inserido na classe VoteService.
```
   public void votar(String meuVoto) {
        // Já existe o método para votar
        // pode utilizar this.memoryDatabase.votar("meuVoto");
        Switch(meuVoto) {
	        case "1": {
		        this.memoryDatabase.votar("XXXX");
		        break;
	        }
	        case "2": {
		        this.memoryDatabase.votar("YYYY");
		        break;
	        }
	         case "3": {
		        this.memoryDatabase.votar("ZZZZ");
		        break;
	        }
	        default: {
		        System.out.println("Opção invalida")
	        }
        }
   }
```
Com isso finalizamos o fluxo de votar.

Iremos agora para descrever como implementar para mostrar os resultados, para isso é bem simples. É importante lembrar que na classe VoteService temos o método showResults. Então para mostrar os resultados é só chama-lo, mas importante para mostrar na tela precisamos usar também o System.out.println().

O trecho de código abaixo deve ser inserido na classe Main.
```
   case 2: {
       System.out.println("Resultado");
       System.out.println(voteService.showResults());
       break;
   }
```
Com isso finalizamos o código para mostrar os resultados.

