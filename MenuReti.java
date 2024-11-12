import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MenuReti extends JMenu{
    private JMenuItem rKIAttack, rGambit;
    
    public MenuReti(ActionListener listener){
        super("Réti");
        rKIAttack = new JMenuItem("R - King's Indian Attack");
        rGambit = new JMenuItem("R - Réti Gambit");


        rKIAttack.setActionCommand("Abertura:reti:r-kings-indian-attack");
        rGambit.setActionCommand("Abertura:reti:r-reti-gambit");

        rKIAttack.addActionListener(listener);
        rGambit.addActionListener(listener);

        add(rKIAttack);
        add(rGambit);
    }
}