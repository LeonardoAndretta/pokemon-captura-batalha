# Pokémon — Captura e Batalha

Projeto Java de Programação Orientada a Objetos.

## Executar
Compile a pasta `src` em uma IDE Java 17+ e execute `pokemon.Main`.

## Testes
Com Maven instalado: `mvn test`. O projeto possui 9 testes JUnit 5.

## Estrutura
- `Pokemon`: classe base abstrata.
- `PokemonFogo`, `PokemonAgua`, `PokemonPlanta`: subclasses polimórficas.
- `TipoElemental`: centraliza a relação de vantagens.
- `Treinador`: equipe de até 6 e captura.
- `Pokedex`: registra sem duplicar.
- `Batalha`: batalha 1x1 por turnos.
