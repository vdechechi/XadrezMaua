import java.util.List;
import java.util.ArrayList;

public class JogadorClient{
    private List<JogadaInfoClient> jogadas;
    private int indiceJogadaAtual;
    private String abertura;
    private String variacao;

    public JogadorClient(String[] JogadasS, String abertura, String variacao){
        jogadas = new ArrayList<>();
        this.abertura = abertura;
        this.variacao = variacao;
        for(String jogada : JogadasS){
            String[] info = jogada.split(",");
            String jogador = info[0];
            String peca = info[1];
            String posInicial = info[2];
            String posFinal = info[3];
            JogadaInfoClient jInfo = new JogadaInfoClient(peca, posInicial, posFinal, jogador);
            jogadas.add(jInfo);
        }
        indiceJogadaAtual = 0;
    }

    public JogadaInfoClient proximaJogada() {
        if (indiceJogadaAtual < jogadas.size()) {
            JogadaInfoClient jogada = jogadas.get(indiceJogadaAtual);
            indiceJogadaAtual++;
            return jogada;
        }
        return null; 
    }

    public void resetarJogada() {
        indiceJogadaAtual = 0;
    }

    public List<JogadaInfoClient> getJogadas() {
        return jogadas;
    }

    public void setJogadas(List<JogadaInfoClient> jogadas) {
        this.jogadas = jogadas;
    }

    public int getIndiceJogadaAtual() {
        return indiceJogadaAtual;
    }

    public void setIndiceJogadaAtual(int indiceJogadaAtual) {
        this.indiceJogadaAtual = indiceJogadaAtual;
    }

    public String getAbertura() {
        return abertura;
    }

    public void setAbertura(String abertura) {
        this.abertura = abertura;
    }

    public String getVariacao() {
        return variacao;
    }

    public void setVariacao(String variacao) {
        this.variacao = variacao;
    }
}