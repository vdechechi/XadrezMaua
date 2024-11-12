import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuDeAberturas extends JMenu {

    public MenuDeAberturas(ActionListener listener, IdiomaManager idiomaManager) {
        super(idiomaManager.getMessage("menu.aberturas")); 

        add(new MenuDefesaSiciliana(listener));
        add(new MenuRuyLopez(listener));
        add(new MenuGambitoRainha(listener));
        add(new MenuSistemaLondon(listener));
        add(new MenuReti(listener));
    }
}
