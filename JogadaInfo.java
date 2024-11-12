public class JogadaInfo {
    private int id;
    private String peca;
    private String posInicial;
    private String posFinal;
    private String jogador;

    public JogadaInfo(int id, String peca, String posInicial, String posFinal, String jogador) {
        this.id = id;
        this.peca = peca;
        this.posInicial = posInicial;
        this.posFinal = posFinal;
        this.jogador = jogador;
    }

    public int getId() {
        return id;
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
        return jogador + " move " + peca + " de " + posInicial + " para " + posFinal;
    }

    public String toCSV(){
        return "" + jogador + "," + peca + "," + posInicial + "," + posFinal + "";
    }
}
