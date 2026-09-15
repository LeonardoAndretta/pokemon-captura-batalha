package pokemon.treinador;

import pokemon.modelo.Pokemon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pokedex {
    private final ArrayList<Pokemon> registrados = new ArrayList<>();

    public void registrar(Pokemon pokemon) {
        boolean existe = registrados.stream()
                .anyMatch(p -> p.getNome().equalsIgnoreCase(pokemon.getNome()));
        if (!existe) registrados.add(pokemon);
    }

    public boolean contem(String nome) {
        return registrados.stream().anyMatch(p -> p.getNome().equalsIgnoreCase(nome));
    }

    public List<Pokemon> getRegistrados() {
        return Collections.unmodifiableList(registrados);
    }
}
