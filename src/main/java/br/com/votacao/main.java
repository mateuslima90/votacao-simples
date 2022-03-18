package br.com.votacao;

import br.com.votacao.service.VoteService;

import java.util.Scanner;

public class main {

    private static void showAllOptions() {
        System.out.println("=============================================");
        System.out.println("Escolha a opção que deseja:");
        System.out.println("1 - Votar");
        System.out.println("2 - Resultados");
        System.out.println("5 - Sair");
        System.out.println("=============================================");
    }

    public static void main(String[] args) throws InterruptedException {
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
                    voteService.showAllBrothers();
                    System.out.println("Digite a sua opção de voto:");
                    var meuVoto = votar.nextInt();
                    voteService.votar(String.valueOf(meuVoto));
                    break;
                    //votar();
                }
                case 2: {
                    System.out.println("Resultado");
                    System.out.println(voteService.showResults());
                    //resultados();
                    break;
                }
                case 5: {
                    System.out.println("Sair");
                    //encerrar();
                    break;
                }
                default: {
                    System.out.println("Opção invalida");
                }
            }
        }
    }
}