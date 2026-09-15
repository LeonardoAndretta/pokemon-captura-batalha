package pokemon.view;

import pokemon.modelo.*;
import pokemon.treinador.Treinador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class JanelaJogo extends JFrame {
    private final Treinador treinador;
    private final Random random = new Random();

    private Pokemon jogador;
    private Pokemon selvagem;

    private final JLabel lblJogador = new JLabel("", SwingConstants.CENTER);
    private final JLabel lblSelvagem = new JLabel("", SwingConstants.CENTER);
    private final JProgressBar vidaJogador = new JProgressBar();
    private final JProgressBar vidaSelvagem = new JProgressBar();
    private final JTextArea log = new JTextArea();
    private final JButton btnAtacar = new JButton("Atacar");
    private final JButton btnCapturar = new JButton("Capturar");
    private final JButton btnNovo = new JButton("Novo encontro");
    private final JComboBox<String> comboEquipe = new JComboBox<>();

    public JanelaJogo() {
        super("Pokemon - Captura e Batalha");
        treinador = new Treinador("Leonardo");

        configurarJanela();
        montarInterface();
        escolherInicial();
        gerarEncontro();
    }

    private void configurarJanela() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(920, 620);
        setMinimumSize(new Dimension(820, 560));
        setLocationRelativeTo(null);
    }

    private void montarInterface() {
        JPanel raiz = new JPanel(new BorderLayout(12, 12));
        raiz.setBorder(new EmptyBorder(14, 14, 14, 14));
        setContentPane(raiz);

        JLabel titulo = new JLabel("POKEMON - CAPTURA E BATALHA", SwingConstants.CENTER);
        titulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        raiz.add(titulo, BorderLayout.NORTH);

        JPanel arena = new JPanel(new GridLayout(1, 2, 18, 0));
        arena.add(criarCartao("SEU POKEMON", lblJogador, vidaJogador));
        arena.add(criarCartao("POKEMON SELVAGEM", lblSelvagem, vidaSelvagem));

        log.setEditable(false);
        log.setLineWrap(true);
        log.setWrapStyleWord(true);
        log.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        JScrollPane scrollLog = new JScrollPane(log);
        scrollLog.setPreferredSize(new Dimension(100, 170));

        JPanel centro = new JPanel(new BorderLayout(10, 10));
        centro.add(arena, BorderLayout.CENTER);
        centro.add(scrollLog, BorderLayout.SOUTH);
        raiz.add(centro, BorderLayout.CENTER);

        JPanel controles = new JPanel(new BorderLayout(8, 8));

        JPanel linhaEquipe = new JPanel(new FlowLayout(FlowLayout.LEFT));
        linhaEquipe.add(new JLabel("Pokemon ativo:"));
        comboEquipe.setPreferredSize(new Dimension(220, 28));
        linhaEquipe.add(comboEquipe);
        JButton btnEquipe = new JButton("Ver equipe");
        JButton btnPokedex = new JButton("Ver Pokedex");
        JButton btnCurar = new JButton("Curar equipe");
        linhaEquipe.add(btnEquipe);
        linhaEquipe.add(btnPokedex);
        linhaEquipe.add(btnCurar);

        JPanel linhaAcoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 4));
        btnAtacar.setPreferredSize(new Dimension(130, 38));
        btnCapturar.setPreferredSize(new Dimension(130, 38));
        btnNovo.setPreferredSize(new Dimension(150, 38));
        linhaAcoes.add(btnAtacar);
        linhaAcoes.add(btnCapturar);
        linhaAcoes.add(btnNovo);

        controles.add(linhaEquipe, BorderLayout.NORTH);
        controles.add(linhaAcoes, BorderLayout.SOUTH);
        raiz.add(controles, BorderLayout.SOUTH);

        btnAtacar.addActionListener(e -> atacar());
        btnCapturar.addActionListener(e -> capturar());
        btnNovo.addActionListener(e -> gerarEncontro());
        btnEquipe.addActionListener(e -> mostrarEquipe());
        btnPokedex.addActionListener(e -> mostrarPokedex());
        btnCurar.addActionListener(e -> curarEquipe());
        comboEquipe.addActionListener(e -> trocarPokemonAtivo());
    }

    private JPanel criarCartao(String cabecalho, JLabel nome, JProgressBar barra) {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(cabecalho),
                new EmptyBorder(18, 18, 18, 18)));

        nome.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        nome.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel icone = new JLabel("POKEMON", SwingConstants.CENTER);
        icone.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
        icone.setAlignmentX(Component.CENTER_ALIGNMENT);
        icone.setPreferredSize(new Dimension(280, 120));
        icone.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));

        barra.setStringPainted(true);
        barra.setAlignmentX(Component.CENTER_ALIGNMENT);
        barra.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));

        painel.add(Box.createVerticalGlue());
        painel.add(icone);
        painel.add(Box.createVerticalStrut(12));
        painel.add(nome);
        painel.add(Box.createVerticalStrut(12));
        painel.add(barra);
        painel.add(Box.createVerticalGlue());
        return painel;
    }

    private void escolherInicial() {
        String[] opcoes = {"Charmander (Fogo)", "Squirtle (Agua)", "Bulbasaur (Planta)"};
        int escolha = JOptionPane.showOptionDialog(
                this,
                "Escolha seu Pokemon inicial:",
                "Escolha inicial",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]);

        if (escolha == 1) jogador = new PokemonAgua("Squirtle", 100, 24);
        else if (escolha == 2) jogador = new PokemonPlanta("Bulbasaur", 110, 22);
        else jogador = new PokemonFogo("Charmander", 95, 26);

        treinador.adicionarNaEquipe(jogador);
        atualizarComboEquipe();
        escrever("Voce escolheu " + jogador.getNome() + "!");
    }

    private void gerarEncontro() {
        selvagem = criarSelvagemAleatorio();
        treinador.avistar(selvagem);
        btnAtacar.setEnabled(true);
        btnCapturar.setEnabled(true);
        escrever("Um " + selvagem.getNome() + " selvagem apareceu! Tipo: " + selvagem.getTipo() + ".");
        atualizarTela();
    }

    private Pokemon criarSelvagemAleatorio() {
        int tipo = random.nextInt(3);
        int variacaoVida = random.nextInt(21);
        int variacaoAtaque = random.nextInt(7);
        return switch (tipo) {
            case 0 -> new PokemonFogo(random.nextBoolean() ? "Vulpix" : "Growlithe", 85 + variacaoVida, 18 + variacaoAtaque);
            case 1 -> new PokemonAgua(random.nextBoolean() ? "Psyduck" : "Poliwag", 90 + variacaoVida, 17 + variacaoAtaque);
            default -> new PokemonPlanta(random.nextBoolean() ? "Oddish" : "Bellsprout", 88 + variacaoVida, 18 + variacaoAtaque);
        };
    }

    private void atacar() {
        if (!podeBatalhar()) return;

        int dano = jogador.atacar(selvagem);
        escrever(jogador.getNome() + " atacou " + selvagem.getNome() + " e causou " + dano + " de dano.");

        if (!selvagem.estaVivo()) {
            jogador.ganharExperiencia(50);
            escrever(selvagem.getNome() + " foi derrotado! " + jogador.getNome() + " ganhou 50 XP.");
            btnAtacar.setEnabled(false);
            btnCapturar.setEnabled(false);
            atualizarTela();
            return;
        }

        int danoInimigo = selvagem.atacar(jogador);
        escrever(selvagem.getNome() + " revidou e causou " + danoInimigo + " de dano.");

        if (!jogador.estaVivo()) {
            escrever(jogador.getNome() + " desmaiou! Escolha outro Pokemon da equipe ou cure a equipe.");
        }
        atualizarTela();
    }

    private void capturar() {
        if (selvagem == null || !selvagem.estaVivo()) {
            escrever("Nao ha Pokemon selvagem disponivel para capturar.");
            return;
        }
        if (treinador.getEquipe().size() >= 6) {
            escrever("Sua equipe ja possui 6 Pokemon. Nao e possivel capturar mais.");
            return;
        }

        int vidaAntes = selvagem.getVida();
        int vidaMax = selvagem.getVidaMaxima();
        boolean sucesso = treinador.capturar(selvagem);
        if (sucesso) {
            escrever("CAPTURA REALIZADA! " + selvagem.getNome() + " entrou para sua equipe.");
            btnAtacar.setEnabled(false);
            btnCapturar.setEnabled(false);
            atualizarComboEquipe();
        } else {
            double percentual = 100.0 * vidaAntes / vidaMax;
            escrever("A captura falhou. Vida restante do selvagem: " + String.format("%.0f%%", percentual) + ". Enfraqueca-o e tente novamente.");
        }
        atualizarTela();
    }

    private boolean podeBatalhar() {
        if (jogador == null || selvagem == null) return false;
        if (!jogador.estaVivo()) {
            escrever("Seu Pokemon ativo esta sem vida. Troque de Pokemon ou use 'Curar equipe'.");
            return false;
        }
        if (!selvagem.estaVivo()) {
            escrever("O Pokemon selvagem ja foi derrotado. Gere um novo encontro.");
            return false;
        }
        return true;
    }

    private void trocarPokemonAtivo() {
        int indice = comboEquipe.getSelectedIndex();
        List<Pokemon> equipe = treinador.getEquipe();
        if (indice >= 0 && indice < equipe.size()) {
            jogador = equipe.get(indice);
            atualizarTela();
        }
    }

    private void atualizarComboEquipe() {
        Pokemon atual = jogador;
        comboEquipe.removeAllItems();
        List<Pokemon> equipe = treinador.getEquipe();
        int selecionar = 0;
        for (int i = 0; i < equipe.size(); i++) {
            Pokemon p = equipe.get(i);
            comboEquipe.addItem(p.getNome() + " - " + p.getTipo());
            if (p == atual) selecionar = i;
        }
        if (!equipe.isEmpty()) comboEquipe.setSelectedIndex(selecionar);
    }

    private void curarEquipe() {
        for (Pokemon p : treinador.getEquipe()) p.restaurarVida();
        escrever("Todos os Pokemon da equipe foram curados.");
        atualizarTela();
    }

    private void mostrarEquipe() {
        StringBuilder sb = new StringBuilder("Equipe de " + treinador.getNome() + ":\n\n");
        int i = 1;
        for (Pokemon p : treinador.getEquipe()) {
            sb.append(i++).append(". ").append(p)
                    .append(" | ATK=").append(p.getAtaque())
                    .append(" | XP=").append(p.getExperiencia()).append('\n');
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Equipe", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarPokedex() {
        StringBuilder sb = new StringBuilder("Pokemon registrados na Pokedex:\n\n");
        for (Pokemon p : treinador.getPokedex().getRegistrados()) {
            sb.append("- ").append(p.getNome()).append(" [").append(p.getTipo()).append("]\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Pokedex", JOptionPane.INFORMATION_MESSAGE);
    }

    private void atualizarTela() {
        if (jogador != null) {
            lblJogador.setText(jogador.getNome() + " - " + jogador.getTipo());
            configurarBarra(vidaJogador, jogador);
        }
        if (selvagem != null) {
            lblSelvagem.setText(selvagem.getNome() + " - " + selvagem.getTipo());
            configurarBarra(vidaSelvagem, selvagem);
        }
    }

    private void configurarBarra(JProgressBar barra, Pokemon pokemon) {
        barra.setMaximum(pokemon.getVidaMaxima());
        barra.setValue(pokemon.getVida());
        barra.setString("HP " + pokemon.getVida() + " / " + pokemon.getVidaMaxima());
    }

    private void escrever(String mensagem) {
        log.append("> " + mensagem + System.lineSeparator());
        log.setCaretPosition(log.getDocument().getLength());
    }
}
