package pokemon.modelo;

public class PokemonPlanta extends Pokemon {
    public PokemonPlanta(String nome, int vida, int ataque) {
        super(nome, TipoElemental.PLANTA, vida, ataque);
    }
    @Override public int calcularDano(Pokemon alvo) { return danoComVantagem(alvo); }
}
