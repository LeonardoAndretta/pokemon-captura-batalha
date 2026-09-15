package pokemon.modelo;

public abstract class Pokemon {
    protected String nome;
    protected TipoElemental tipo;
    protected int vida;
    protected int vidaMaxima;
    protected int ataque;
    private int experiencia;

    public Pokemon(String nome, TipoElemental tipo, int vida, int ataque) {
        this.nome = nome;
        this.tipo = tipo;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.ataque = ataque;
        this.experiencia = 0;
    }

    public abstract int calcularDano(Pokemon alvo);

    public int atacar(Pokemon alvo) {
        int dano = calcularDano(alvo);
        alvo.receberDano(dano);
        return dano;
    }

    public void receberDano(int dano) {
        vida = Math.max(0, vida - Math.max(0, dano));
    }

    public void ganharExperiencia(int quantidade) {
        if (quantidade > 0) experiencia += quantidade;
    }

    public void restaurarVida() {
        vida = vidaMaxima;
    }

    protected int danoComVantagem(Pokemon alvo) {
        return tipo.temVantagemSobre(alvo.getTipo()) ? ataque * 2 : ataque;
    }

    public String getNome() { return nome; }
    public TipoElemental getTipo() { return tipo; }
    public int getVida() { return vida; }
    public int getVidaMaxima() { return vidaMaxima; }
    public int getAtaque() { return ataque; }
    public int getExperiencia() { return experiencia; }
    public boolean estaVivo() { return vida > 0; }

    @Override
    public String toString() {
        return nome + " [" + tipo + "] HP=" + vida + "/" + vidaMaxima;
    }
}
