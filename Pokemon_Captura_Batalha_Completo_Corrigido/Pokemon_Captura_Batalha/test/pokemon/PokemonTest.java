package pokemon;

import org.junit.jupiter.api.Test;
import pokemon.modelo.*;
import pokemon.treinador.Pokedex;
import pokemon.treinador.Treinador;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class PokemonTest {
    @Test void fogoTemVantagemSobrePlanta() {
        Pokemon fogo = new PokemonFogo("F", 100, 20);
        Pokemon planta = new PokemonPlanta("P", 100, 20);
        assertEquals(40, fogo.calcularDano(planta));
    }

    @Test void aguaTemVantagemSobreFogo() {
        Pokemon agua = new PokemonAgua("A", 100, 20);
        Pokemon fogo = new PokemonFogo("F", 100, 20);
        assertEquals(40, agua.calcularDano(fogo));
    }

    @Test void plantaTemVantagemSobreAgua() {
        Pokemon planta = new PokemonPlanta("P", 100, 20);
        Pokemon agua = new PokemonAgua("A", 100, 20);
        assertEquals(40, planta.calcularDano(agua));
    }

    @Test void semVantagemUsaDanoNormal() {
        Pokemon fogo1 = new PokemonFogo("F1", 100, 20);
        Pokemon fogo2 = new PokemonFogo("F2", 100, 20);
        assertEquals(20, fogo1.calcularDano(fogo2));
    }

    @Test void receberDanoNuncaDeixaVidaNegativa() {
        Pokemon agua = new PokemonAgua("A", 50, 10);
        agua.receberDano(999);
        assertEquals(0, agua.getVida());
    }

    @Test void capturaComVidaBaixaPodeTerSucesso() {
        Pokemon selvagem = new PokemonPlanta("Bulbasaur", 100, 10);
        selvagem.receberDano(90);
        Treinador t = new Treinador("Teste", new Random() { @Override public double nextDouble(){ return 0.10; }});
        assertTrue(t.capturar(selvagem));
        assertEquals(1, t.getEquipe().size());
    }

    @Test void capturaComVidaCheiaPodeFalhar() {
        Pokemon selvagem = new PokemonAgua("Squirtle", 100, 10);
        Treinador t = new Treinador("Teste", new Random() { @Override public double nextDouble(){ return 0.90; }});
        assertFalse(t.capturar(selvagem));
    }

    @Test void pokedexNaoDuplica() {
        Pokedex p = new Pokedex();
        p.registrar(new PokemonFogo("Charmander", 100, 20));
        p.registrar(new PokemonFogo("Charmander", 100, 20));
        assertEquals(1, p.getRegistrados().size());
    }

    @Test void equipeTemNoMaximoSeis() {
        Treinador t = new Treinador("Teste");
        for (int i=0;i<6;i++) assertTrue(t.adicionarNaEquipe(new PokemonFogo("F"+i,100,10)));
        assertFalse(t.adicionarNaEquipe(new PokemonAgua("Extra",100,10)));
    }
}
