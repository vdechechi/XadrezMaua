import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import java.util.LinkedList;

public class ChessServer{
    public static final String ADDRESS = "127.0.0.1";
    public static final int PORT = 4000;
    private ServerSocket serverSocket;
    private final List<SocketClient> clients = new LinkedList<>();

    public void start() throws IOException{
        serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor iniciado na porta: "+PORT);
        clientConnectionLoop();
    }

    private void clientConnectionLoop() throws IOException{
        System.out.println("Aguardando conexão com cliente!");
        while(true){
            SocketClient clientSocket = new SocketClient(serverSocket.accept());
            clients.add(clientSocket);
            new Thread(() -> clientMessageLoop(clientSocket)).start();
        }
    }

    private void clientMessageLoop(SocketClient clientSocket){
        String msg;
        try{
            while((msg = clientSocket.getMessage()) != null){
                if("sair".equalsIgnoreCase(msg)) return;
                System.out.printf("<- Cliente %s: %s\n", clientSocket.getRemoteSocketAddress(), msg);
                returnToClient(clientSocket, msg);
            }
        }finally{
            clientSocket.close();
        }
    }

    private void returnToClient(SocketClient sender, String msg){
        String mandar = editarOuConfirmarMensagem(msg);
        sender.sendMessage(mandar);
    }

    private static String editarOuConfirmarMensagem(String msgRecebida){
        System.out.println("Mensagem recebida: " + msgRecebida);
        String[] detalhes = msgRecebida.split(":");
        String abertura = detalhes[0];
        String variacao = detalhes[1];
        Jogador jogadorAtual;
        jogadorAtual = new Jogador(abertura, variacao);
        StringBuilder construtor = new StringBuilder();
        List<JogadaInfo> jogadas = jogadorAtual.getJogadas();
        for(JogadaInfo jogada : jogadas){
            construtor.append(jogada.toCSV() + ";");
        }
        if(construtor.length()>0){
            construtor.deleteCharAt(construtor.length()-1);
        }
        construtor.append("Estatisticas");
        Estatisticas estatisticas = jogadorAtual.carregarEstatisticas();
        construtor.append(estatisticas.toCSV());

        return construtor.toString();
    }

    public static void main(String[] args) {
        System.out.println("*v*v*v* CONSOLE DO SERVIDOR *V*V*V*");
        try{
            ChessServer server = new ChessServer();
            server.start();
        }catch(IOException ex){
            System.out.println("Erro ao inicicar o servidor: " + ex.getMessage());
        }
        System.out.println("Servido finalizado!");
    }
}