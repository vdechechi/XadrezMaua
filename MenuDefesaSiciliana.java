import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuDefesaSiciliana extends JMenu {
    public MenuDefesaSiciliana(ActionListener listener) {
        super("Defesa Siciliana");

        JMenuItem dsOpen = new JMenuItem("Open Variation");
        JMenuItem dsDragon = new JMenuItem("Dragon Variation");
        JMenuItem dsNajdorf = new JMenuItem("Najdorf Variation");

        add(dsOpen);
        add(dsDragon);
        add(dsNajdorf);

        dsOpen.setActionCommand("Abertura:defesa_siciliana:ds-open-variation");
        dsDragon.setActionCommand("Abertura:defesa_siciliana:ds-dragon-variation");
        dsNajdorf.setActionCommand("Abertura:defesa_siciliana:ds-najdorf-variation");

        dsOpen.addActionListener(listener);
        dsDragon.addActionListener(listener);
        dsNajdorf.addActionListener(listener);
    }
}
