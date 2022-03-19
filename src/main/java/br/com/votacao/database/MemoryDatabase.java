package br.com.votacao.database;

import java.util.HashMap;
import java.util.Map;

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
