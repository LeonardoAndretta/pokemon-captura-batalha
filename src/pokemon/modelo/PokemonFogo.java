package pokemon.modelo;

public class PokemonFogo extends Pokemon {
    public PokemonFogo(String nome, int vida, int ataque) {
        super(nome, TipoElemental.FOGO, vida, ataque);
    }
    @Override public int calcularDano(Pokemon alvo) { return danoComVantagem(alvo); }
}
