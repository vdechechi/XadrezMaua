import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private List<JogadaInfo> jogadas;
    private int indiceJogadaAtual;
    private Connection conn;
    private String abertura; 
    private String variacao; 

    public Jogador(String abertura, String variacao) {
        this.abertura = abertura; 
        this.variacao = variacao; 
        jogadas = new ArrayList<>();
        indiceJogadaAtual = 0;
        conn = Jogada.getConexao();

        if (conn == null) {
            System.out.println("Erro: Falha ao conectar com o banco de dados.");
            return; 
        }

        carregarJogadas(abertura, variacao);
    }

    public Estatisticas carregarEstatisticas() {
        Estatisticas estatisticas = null;
        String query = String.format(
            "SELECT total_games, vitorias_percentual, derrotas_percentual, empates_percentual " +
            "FROM estatisticas WHERE nome_abertura = '%s'", this.abertura
        );
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                String totalGames = rs.getString("total_games");
                String vitoriasPercentual = rs.getString("vitorias_percentual");
                String derrotasPercentual = rs.getString("derrotas_percentual");
                String empatesPercentual = rs.getString("empates_percentual");
                estatisticas = new Estatisticas(totalGames, vitoriasPercentual, derrotasPercentual, empatesPercentual);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estatisticas;
    }
    

    private void carregarJogadas(String abertura, String variacao) {
        String query = String.format("SELECT j.id, j.peca, j.pos_inicial, j.pos_final, j.jogador FROM jogada j " +
                                     "INNER JOIN jogo g ON j.id_jogo = g.id " +
                                     "WHERE g.nome_abertura = '%s' AND g.variacao = '%s' " +
                                     "ORDER BY j.numero_jogada", abertura, variacao);
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String peca = rs.getString("peca");
                String posInicial = rs.getString("pos_inicial");
                String posFinal = rs.getString("pos_final");
                String jogador = rs.getString("jogador");

                JogadaInfo jogada = new JogadaInfo(id, peca, posInicial, posFinal, jogador);
                jogadas.add(jogada);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public JogadaInfo proximaJogada() {
        if (indiceJogadaAtual < jogadas.size()) {
            JogadaInfo jogada = jogadas.get(indiceJogadaAtual);
            indiceJogadaAtual++;
            return jogada;
        }
        return null; 
    }

    public void resetarJogada() {
        indiceJogadaAtual = 0;
    }

    public List<JogadaInfo> getJogadas() {
        return jogadas;
    }

    public void setJogadas(List<JogadaInfo> jogadas) {
        this.jogadas = jogadas;
    }

    public int getIndiceJogadaAtual() {
        return indiceJogadaAtual;
    }

    public void setIndiceJogadaAtual(int indiceJogadaAtual) {
        this.indiceJogadaAtual = indiceJogadaAtual;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
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