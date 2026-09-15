package pokemon.batalha;

import pokemon.modelo.Pokemon;

public class Batalha {
    public Pokemon batalhar(Pokemon primeiro, Pokemon segundo) {
        Pokemon atacante = primeiro;
        Pokemon defensor = segundo;
        int turno = 1;
        while (primeiro.estaVivo() && segundo.estaVivo()) {
            int dano = atacante.atacar(defensor);
            System.out.printf("Turno %d: %s atacou %s e causou %d de dano. HP de %s: %d%n",
                    turno, atacante.getNome(), defensor.getNome(), dano,
                    defensor.getNome(), defensor.getVida());
            Pokemon temp = atacante;
            atacante = defensor;
            defensor = temp;
            turno++;
        }
        Pokemon vencedor = primeiro.estaVivo() ? primeiro : segundo;
        vencedor.ganharExperiencia(50);
        System.out.println("Vencedor: " + vencedor.getNome());
        return vencedor;
    }
}
