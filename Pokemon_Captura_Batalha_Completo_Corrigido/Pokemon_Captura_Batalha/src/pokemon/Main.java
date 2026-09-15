package pokemon;

import pokemon.batalha.Batalha;
import pokemon.modelo.*;
import pokemon.treinador.Treinador;

public class Main {
    public static void main(String[] args) {
        Treinador treinador = new Treinador("Leonardo");
        Pokemon charmander = new PokemonFogo("Charmander", 100, 25);
        Pokemon squirtle = new PokemonAgua("Squirtle", 100, 25);
        Pokemon bulbasaur = new PokemonPlanta("Bulbasaur", 100, 25);

        treinador.adicionarNaEquipe(charmander);
        treinador.avistar(squirtle);
        treinador.avistar(bulbasaur);

        bulbasaur.receberDano(70);
        System.out.println("=== CAPTURA ===");
        System.out.println("Selvagem: " + bulbasaur);
        System.out.println(treinador.capturar(bulbasaur) ? "Captura realizada!" : "O Pokemon escapou!");

        System.out.println("\n=== EQUIPE ===");
        treinador.getEquipe().forEach(System.out::println);

        System.out.println("\n=== POKEDEX ===");
        treinador.getPokedex().getRegistrados().forEach(System.out::println);

        System.out.println("\n=== BATALHA ===");
        Pokemon fogo = new PokemonFogo("Charmander", 100, 25);
        Pokemon planta = new PokemonPlanta("Bulbasaur", 100, 25);
        new Batalha().batalhar(fogo, planta);
    }
}
