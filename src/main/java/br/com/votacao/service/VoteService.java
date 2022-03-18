package br.com.votacao.service;

import br.com.votacao.database.MemoryDatabase;

import java.util.Map;

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
