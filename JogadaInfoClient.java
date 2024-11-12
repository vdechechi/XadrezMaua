public class JogadaInfoClient{
    private String peca;
    private String posInicial;
    private String posFinal;
    private String jogador;

    public JogadaInfoClient(String peca, String posInicial, String posFinal, String jogador) {
        this.peca = peca;
        this.posInicial = posInicial;
        this.posFinal = posFinal;
        this.jogador = jogador;
    }

    public String getPeca() {
        return peca;
    }

    public String getPosInicial() {
        return posInicial;
    }

    public String getPosFinal() {
        return posFinal;
    }

    public String getJogador() {
        return jogador;
    }

    @Override
    public String toString() {
        return posInicial + " -> " + posFinal;
    }

    public String toCSV(){
        return "" + jogador + "," + peca + "," + posInicial + "," + posFinal + "";
    }
}