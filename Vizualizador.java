import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

class Vizualizador extends JFrame implements ActionListener {
    private static Socket cliente;
    private static Scanner entrada;
    private static PrintStream saida;

    private Estatisticas estatisticas;
    private Tabuleiro tabuleiro;
    private MenuDeAberturas menuDeAberturas;
    private JPanel painelBotoes;
    private JButton botaoReset, botaoProximo, botaoSalvar, botaoEstatisticas;
    private JMenuBar barra;
    private JogadorClient jogadorAtual;
    private JLabel labelAberturaVariacao;
    private IdiomaManager idiomaManager;

    public Vizualizador(IdiomaManager idiomaManager) {
        super(idiomaManager.getMessage("vizualizador.title"));
        this.idiomaManager = idiomaManager;
        tabuleiro = new Tabuleiro();
        menuDeAberturas = new MenuDeAberturas(this, idiomaManager);
        painelBotoes = new JPanel();
        botaoReset = new JButton(idiomaManager.getMessage("botao.reset"));
        botaoProximo = new JButton(idiomaManager.getMessage("botao.proximo"));
        botaoSalvar = new JButton(idiomaManager.getMessage("botao.salvar"));
        botaoEstatisticas = new JButton(idiomaManager.getMessage("botao.estatisticas"));
        barra = new JMenuBar();
        labelAberturaVariacao = new JLabel(idiomaManager.getMessage("label.abertura_variacao"));

        painelBotoes.setLayout(new GridLayout(1, 4));
        botaoReset.addActionListener(this);
        botaoProximo.addActionListener(this);
        botaoSalvar.addActionListener(this);
        botaoEstatisticas.addActionListener(this);
        painelBotoes.add(botaoReset);
        painelBotoes.add(botaoProximo);
        painelBotoes.add(botaoSalvar);
        painelBotoes.add(botaoEstatisticas);

        barra.add(menuDeAberturas);
        setJMenuBar(barra);

        setLayout(new BorderLayout());
        add(labelAberturaVariacao, BorderLayout.NORTH);
        add(tabuleiro, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 900);
        setLocationRelativeTo(null);
        setVisible(true);

        try {
            iniciaCliente();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, idiomaManager.getMessage("erro.conexao"));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == botaoReset) {
            tabuleiro.limpaTabuleiro();
            tabuleiro.setPosicaoIni();
            if (jogadorAtual != null) {
                jogadorAtual.resetarJogada();
            }
        } else if (source == botaoProximo) {
            if (jogadorAtual != null) {
                JogadaInfoClient jogada = jogadorAtual.proximaJogada();
                if (jogada != null) {
                    atualizarTabuleiro(jogada);
                } else {
                    JOptionPane.showMessageDialog(this, idiomaManager.getMessage("todas.jogadas.realizadas"));
                }
            }
        } else if (source == botaoSalvar) {
            if (jogadorAtual != null) {
                salvarJogadas(jogadorAtual);
            }
        } else if (source == botaoEstatisticas) {
            if (jogadorAtual != null) {
                mostrarEstatisticas(jogadorAtual);
            }
        } else {
            String comando = e.getActionCommand();
            if (comando.startsWith("Abertura")) {
                tabuleiro.limpaTabuleiro();
                tabuleiro.setPosicaoIni();
                if (jogadorAtual != null) {
                    jogadorAtual.resetarJogada();
                }
                String[] partes = comando.split(":");
                String abertura = partes[1];
                String variacao = partes[2];
                enviarMensagem(abertura, variacao);
                labelAberturaVariacao.setText(idiomaManager.getMessage("label.abertura_variacao") + ": " + abertura + " | " + variacao);
            }
        }
    }

    private void salvarJogadas(JogadorClient jogador) {
        String nomeArquivo = jogador.getAbertura() + jogador.getVariacao() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./Aberturas/" + nomeArquivo))) {
            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", Locale.forLanguageTag("pt-BR"));
            String dataHoraAtual = agora.format(formatter);

            writer.write(dataHoraAtual);
            writer.newLine();

            for (JogadaInfoClient jogada : jogador.getJogadas()) {
                writer.write(jogada.toString());
                writer.newLine();
            }

            JOptionPane.showMessageDialog(this, idiomaManager.getMessage("arquivo.salvo.sucesso").replace("{0}", nomeArquivo));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, idiomaManager.getMessage("erro.salvar.arquivo").replace("{0}", e.getMessage()));
        }
    }

    private void atualizarTabuleiro(JogadaInfoClient jogadaInfoClient) {
        String posInicial = jogadaInfoClient.getPosInicial();
        String posFinal = jogadaInfoClient.getPosFinal();

        if (!posInicial.isEmpty() && !posFinal.isEmpty()) {
            int linhaInicial = Character.getNumericValue(posInicial.charAt(1));
            int colunaInicial = Character.getNumericValue(posInicial.charAt(0));
            int linhaFinal = Character.getNumericValue(posFinal.charAt(1));
            int colunaFinal = Character.getNumericValue(posFinal.charAt(0));

            String peca = jogadaInfoClient.getPeca();
            String jogador = jogadaInfoClient.getJogador();

            if (!"roque".equals(peca)) {
                peca = tabuleiro.getTextoFromCasa(linhaInicial, colunaInicial);
                tabuleiro.setCasaTabuleiro(linhaInicial, colunaInicial, "");
                tabuleiro.setCasaTabuleiro(linhaFinal, colunaFinal, peca);
            } else {
                if ("brancas".equals(jogador)) {
                    String rei = tabuleiro.getTextoFromCasa(0, 4);
                    tabuleiro.setCasaTabuleiro(0, 6, rei);
                    tabuleiro.setCasaTabuleiro(0, 4, "");
                    String torre = tabuleiro.getTextoFromCasa(0, 7);
                    tabuleiro.setCasaTabuleiro(0, 5, torre);
                    tabuleiro.setCasaTabuleiro(0, 7, "");
                } else {
                    String rei = tabuleiro.getTextoFromCasa(7, 4);
                    tabuleiro.setCasaTabuleiro(7, 6, rei);
                    tabuleiro.setCasaTabuleiro(7, 4, "");
                    String torre = tabuleiro.getTextoFromCasa(7, 7);
                    tabuleiro.setCasaTabuleiro(7, 5, torre);
                    tabuleiro.setCasaTabuleiro(7, 7, "");
                }
            }
        }
    }

    private void mostrarEstatisticas(JogadorClient jogador) {
        if (estatisticas != null) {
            String[] colunas = {
                idiomaManager.getMessage("estatisticas.total_jogos"),
                idiomaManager.getMessage("estatisticas.vitorias"),
                idiomaManager.getMessage("estatisticas.derrotas"),
                idiomaManager.getMessage("estatisticas.empates")
            };
            Object[][] dados = {
                {
                    estatisticas.getTotalGames(),
                    estatisticas.getVitoriasPercentual(),
                    estatisticas.getDerrotasPercentual(),
                    estatisticas.getEmpatesPercentual()
                }
            };

            JTable tabelaEstatisticas = new JTable(dados, colunas);
            JScrollPane scrollPane = new JScrollPane(tabelaEstatisticas);
            tabelaEstatisticas.setFillsViewportHeight(true);

            JDialog dialogEstatisticas = new JDialog(
                (JFrame) SwingUtilities.getWindowAncestor(this),
                idiomaManager.getMessage("vizualizador.title") + " " + jogador.getAbertura(),
                true
            );
            dialogEstatisticas.add(scrollPane);
            dialogEstatisticas.setSize(400, 200);
            dialogEstatisticas.setLocationRelativeTo(this);
            dialogEstatisticas.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, idiomaManager.getMessage("estatisticas.nao.encontradas"));
        }
    }

    private void iniciaCliente() throws IOException {
        cliente = new Socket(ChessServer.ADDRESS, ChessServer.PORT);
        entrada = new Scanner(cliente.getInputStream());
        saida = new PrintStream(cliente.getOutputStream());
    }

    private void enviarMensagem(String abertura, String variacao) {
        String msg = abertura + ":" + variacao;
        if (!msg.isEmpty()) {
            try {
                saida.println(msg);

                if (entrada.hasNextLine()) {
                    String echo = entrada.nextLine();
                    String[] msgDetalhes = echo.split("Estatisticas");
                    String[] jogadas = msgDetalhes[0].split(";");
                    jogadorAtual = new JogadorClient(jogadas, abertura, variacao);

                    String[] stats = msgDetalhes[1].split(",");
                    String totalGames = stats[0];
                    String vitoriasP = stats[1];
                    String derrotasP = stats[2];
                    String empatesP = stats[3];
                    this.estatisticas = new Estatisticas(totalGames, vitoriasP, derrotasP, empatesP);
                }
            } finally {
                if (saida != null) {
                    saida.flush();
                }
            }
        }
    }
}
