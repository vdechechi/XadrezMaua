import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuGambitoRainha extends JMenu {
    public MenuGambitoRainha(ActionListener listener) {
        super("Gambito da Rainha");

        JMenuItem grRejeitado = new JMenuItem("Gambito Rejeitado");
        JMenuItem grAceito = new JMenuItem("Gambito Aceito");

        add(grRejeitado);
        add(grAceito);

        grRejeitado.setActionCommand("Abertura:gambito_da_rainha:gr-gambito-rejeitado");
        grAceito.setActionCommand("Abertura:gambito_da_rainha:gr-gambito-aceito");

        grRejeitado.addActionListener(listener);
        grAceito.addActionListener(listener);
    }
}
