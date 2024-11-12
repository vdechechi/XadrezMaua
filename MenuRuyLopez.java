import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuRuyLopez extends JMenu {
    public MenuRuyLopez(ActionListener listener) {
        super("Ruy-López");

        JMenuItem rlMain = new JMenuItem("Main Variation");
        JMenuItem rlClosed = new JMenuItem("Closed Variation");
        JMenuItem rlOpen = new JMenuItem("Open Variation");
        JMenuItem rlExchange = new JMenuItem("Exchange Variation");

        add(rlMain);
        add(rlClosed);
        add(rlOpen);
        add(rlExchange);

        rlMain.setActionCommand("Abertura:ruy_lopez:rl-main-variation");
        rlClosed.setActionCommand("Abertura:ruy_lopez:rl-closed-variation");
        rlOpen.setActionCommand("Abertura:ruy_lopez:rl-open-variation");
        rlExchange.setActionCommand("Abertura:ruy_lopez:rl-exchange-variation");

        rlMain.addActionListener(listener);
        rlClosed.addActionListener(listener);
        rlOpen.addActionListener(listener);
        rlExchange.addActionListener(listener);
    }
}
