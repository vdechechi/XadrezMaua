import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuSistemaLondon extends JMenu {
    public MenuSistemaLondon(ActionListener listener) {
        super("Sistema London");

        JMenuItem slMain = new JMenuItem("Main Variation");
        JMenuItem slIndian = new JMenuItem("Indian Setup");
        JMenuItem slJobava = new JMenuItem("Jobava London");

        add(slMain);
        add(slIndian);
        add(slJobava);

        slMain.setActionCommand("Abertura:sistema_london:sl-main-variation");
        slIndian.setActionCommand("Abertura:sistema_london:sl-indian-setup");
        slJobava.setActionCommand("Abertura:sistema_london:sl-jobava-london");

        slMain.addActionListener(listener);
        slIndian.addActionListener(listener);
        slJobava.addActionListener(listener);
    }
}
