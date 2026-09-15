# Pokemon - Captura e Batalha

Projeto Java de Programacao Orientada a Objetos com interface grafica Swing.

## Requisitos
- JDK 17
- Maven 3.8+ (para testes)

## Como executar pela IDE
Abra o projeto, localize `src/pokemon/Main.java` e execute o metodo `main`.
Uma janela grafica sera aberta para escolher o Pokemon inicial, batalhar, capturar Pokemon, consultar a equipe e a Pokedex.

## Como testar
```bash
mvn test
```

## Funcionalidades
- Interface grafica jogavel em Java Swing
- Escolha de Pokemon inicial
- Encontros aleatorios
- Batalha 1x1 por turnos
- Vantagem elemental: Fogo > Planta, Planta > Agua, Agua > Fogo
- Captura com chance influenciada pela vida restante
- Equipe com ate 6 Pokemon
- Pokedex sem registros duplicados
- Cura da equipe e troca do Pokemon ativo
- Testes JUnit
