import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuLogin extends JFrame {
    private JPasswordField passwordField;
    private JButton loginButton;
    private IdiomaManager idiomaManager;
    private JComboBox<String> languageComboBox;

    public MenuLogin(IdiomaManager idiomaManager) {
        this.idiomaManager = idiomaManager;
        idiomaManager.setLocale("pt", "BR"); 
        setTitle(idiomaManager.getMessage("login.title"));
        setSize(270, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        iniciaTela();
    }

    private void iniciaTela() {
        ImageIcon backgroundIcon = new ImageIcon("./ImagemDeFundoTelaMenu.jpg");

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());

        passwordField = new JPasswordField(15);
        loginButton = new JButton(idiomaManager.getMessage("login.button"));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String digitada = new String(passwordField.getPassword());
                try {
                    if(verificaSenha(digitada)){
                        SwingUtilities.invokeLater(() -> new Vizualizador(idiomaManager));

                        dispose();
                    }
                    else{
                        System.out.println("Senha incorreta");
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        loginButton.setBackground(new Color(102, 51, 0));
        loginButton.setForeground(Color.WHITE);

        
        languageComboBox = new JComboBox<>(new String[]{"Português", "English", "Español", "Française", "Italiano"});
        languageComboBox.setSelectedIndex(0); 
        languageComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    
                    switch (languageComboBox.getSelectedIndex()) {
                        case 0:
                            idiomaManager.setLocale("pt", "BR");
                            break;
                        case 1:
                            idiomaManager.setLocale("en", "US");
                            break;
                        case 2:
                            idiomaManager.setLocale("es", "ES");
                            break;
                        case 3:
                            idiomaManager.setLocale("fr", "FR");
                            break;
                        case 4:
                            idiomaManager.setLocale("it", "IT");
                            break;
                    }
                    
                    atualizarTextos();
                }
            }
        });

        JPanel loginPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        loginPanel.setOpaque(false);
        loginPanel.add(languageComboBox);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);

        backgroundPanel.add(loginPanel);
        setContentPane(backgroundPanel);
    }

    private void atualizarTextos() {
        
        setTitle(idiomaManager.getMessage("login.title"));
        loginButton.setText(idiomaManager.getMessage("login.button"));
    }

    private boolean verificaSenha(String digitada) throws Exception{
        String   sMsgCifrada = null;
        byte[]   bMsgClara = null;
        byte[]   bMsgCifrada = null;

        bMsgClara = digitada.getBytes("ISO-8859-1");
        CryptoDummy cdummy = new CryptoDummy();
        cdummy.geraCifra(bMsgClara, new File ("./chave.dummy"));
        bMsgCifrada = cdummy.getTextoCifrado();
        sMsgCifrada = (new String (bMsgCifrada, "ISO-8859-1"));

        ReadTextFile arqR = new ReadTextFile();
        arqR.openFile();
        String senhaSalvaCif = arqR.readRecords();
        arqR.closeFile();

        senhaSalvaCif = senhaSalvaCif.trim();

        System.out.println("Senha digitada cif: " + sMsgCifrada);
        System.out.println("Senha salva cif: " + senhaSalvaCif);
        return(sMsgCifrada.equals(senhaSalvaCif));
    }

    
}
