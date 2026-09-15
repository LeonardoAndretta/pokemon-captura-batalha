package pokemon.modelo;

public class PokemonAgua extends Pokemon {
    public PokemonAgua(String nome, int vida, int ataque) {
        super(nome, TipoElemental.AGUA, vida, ataque);
    }
    @Override public int calcularDano(Pokemon alvo) { return danoComVantagem(alvo); }
}
