package pokemon.modelo;

public enum TipoElemental {
    FOGO, AGUA, PLANTA;

    public boolean temVantagemSobre(TipoElemental outro) {
        return (this == FOGO && outro == PLANTA)
                || (this == PLANTA && outro == AGUA)
                || (this == AGUA && outro == FOGO);
    }
}
