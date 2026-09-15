package pokemon;

import pokemon.view.JanelaJogo;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
                // Usa o visual padrao do Java se o visual do sistema nao estiver disponivel.
            }
            new JanelaJogo().setVisible(true);
        });
    }
}
