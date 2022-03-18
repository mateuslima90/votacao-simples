package br.com.votacao.service;

import br.com.votacao.database.MemoryDatabase;

import java.util.Map;

public class VoteService {

    private MemoryDatabase memoryDatabase;

    public VoteService() {
        this.memoryDatabase = new MemoryDatabase();
    }

    public void showAllBrothers() {
        System.out.println("Paredão desse semana:");
        System.out.println("1 - Kratos");
        System.out.println("2 - Gerald");
        System.out.println("3 - Samanosuke");
    }

    public Map<String, Integer> showResults() {
        return memoryDatabase.resultado();
    }

    public void votar(String meuVoto) {
        switch (meuVoto){
            case "1": {
                this.memoryDatabase.votar("Kratos");
                System.out.println("Voto processado com sucesso para Kratos");
                break;
            }
            case "2": {
                this.memoryDatabase.votar("Gerald");
                System.out.println("Voto processado com sucesso para Gerald");
                break;
            }
            case "3": {
                this.memoryDatabase.votar("Samanosuke");
                System.out.println("Voto processado com sucesso para Samanosuke");
                break;
            }
            default: {
                System.out.println("Voto não contabilizado");
            }
        }
    }
}
