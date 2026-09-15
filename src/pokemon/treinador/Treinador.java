package pokemon.treinador;

import pokemon.modelo.Pokemon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Treinador {
    private final String nome;
    private final ArrayList<Pokemon> equipe = new ArrayList<>();
    private final Pokedex pokedex = new Pokedex();
    private final Random random;

    public Treinador(String nome) { this(nome, new Random()); }
    public Treinador(String nome, Random random) {
        this.nome = nome;
        this.random = random;
    }

    public boolean adicionarNaEquipe(Pokemon pokemon) {
        if (equipe.size() >= 6) return false;
        equipe.add(pokemon);
        pokedex.registrar(pokemon);
        return true;
    }

    public boolean capturar(Pokemon selvagem) {
        pokedex.registrar(selvagem);
        if (equipe.size() >= 6) return false;
        double proporcaoVida = (double) selvagem.getVida() / selvagem.getVidaMaxima();
        double chance = 0.25 + (1.0 - proporcaoVida) * 0.70; // 25% a 95%
        boolean sucesso = random.nextDouble() < chance;
        if (sucesso) equipe.add(selvagem);
        return sucesso;
    }

    public void avistar(Pokemon pokemon) { pokedex.registrar(pokemon); }
    public String getNome() { return nome; }
    public List<Pokemon> getEquipe() { return Collections.unmodifiableList(equipe); }
    public Pokedex getPokedex() { return pokedex; }
}
