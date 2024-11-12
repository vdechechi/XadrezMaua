import javax.swing.*;
import java.awt.*;

class Tabuleiro extends JPanel {
    private JLabel[][] casasTabuleiro;
    private int bRei = 9812, bRainha = 9813, bTorre = 9814, bBispo = 9815, bCavalo = 9816, bPeao = 9817;
    private int pRei = 9818, pRainha = 9819, pTorre = 9820, pBispo = 9821, pCavalo = 9822, pPeao = 9823;
    
    public Tabuleiro() {
        super();
        casasTabuleiro = new JLabel[8][8];
        setLayout(new GridLayout(8, 8));
        inicializaTabuleiro();
        setFonte();
        setPosicaoIni();
    }
    
    public JLabel getCasaTabuleiro(int linha, int coluna) {
        return casasTabuleiro[linha][coluna];
    }

    public void setCasaTabuleiro(int linha, int coluna, String texto) {
        casasTabuleiro[linha][coluna].setText(texto);
    }

    public String getTextoFromCasa(int linha, int coluna) {
        return getCasaTabuleiro(linha, coluna).getText();
    }

    public void inicializaTabuleiro() {
        Color azulClaro = new Color(52, 125, 220);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                casasTabuleiro[7 - i][j] = new JLabel("", SwingConstants.CENTER);
                casasTabuleiro[7 - i][j].setOpaque(true);
                if ((i + j) % 2 == 0) {
                    casasTabuleiro[7 - i][j].setBackground(Color.WHITE);
                } else {
                    casasTabuleiro[7 - i][j].setBackground(azulClaro);
                }
                add(casasTabuleiro[7 - i][j]);
            }
        }
    }

    public void setFonte() {
        Font fonte = new Font("DejaVu Sans", Font.PLAIN, 60);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                casasTabuleiro[i][j].setFont(fonte);
            }
        }
    }

    public void limpaTabuleiro() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                casasTabuleiro[i][j].setText("");
            }
        }
    }

    public void setPosicaoIni() {
        limpaTabuleiro();
        casasTabuleiro[0][0].setText("" + (char) bTorre);
        casasTabuleiro[0][1].setText("" + (char) bCavalo);
        casasTabuleiro[0][2].setText("" + (char) bBispo);
        casasTabuleiro[0][3].setText("" + (char) bRainha);
        casasTabuleiro[0][4].setText("" + (char) bRei);
        casasTabuleiro[0][5].setText("" + (char) bBispo);
        casasTabuleiro[0][6].setText("" + (char) bCavalo);
        casasTabuleiro[0][7].setText("" + (char) bTorre);

        casasTabuleiro[7][0].setText("" + (char) pTorre);
        casasTabuleiro[7][1].setText("" + (char) pCavalo);
        casasTabuleiro[7][2].setText("" + (char) pBispo);
        casasTabuleiro[7][3].setText("" + (char) pRainha);
        casasTabuleiro[7][4].setText("" + (char) pRei);
        casasTabuleiro[7][5].setText("" + (char) pBispo);
        casasTabuleiro[7][6].setText("" + (char) pCavalo);
        casasTabuleiro[7][7].setText("" + (char) pTorre);

        for (int i = 0; i < 8; i++) {
            casasTabuleiro[1][i].setText("" + (char) bPeao);
            casasTabuleiro[6][i].setText("" + (char) pPeao);
        }
    }
}
