import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;

public class SocketClient {
    private final Socket socket;
    private BufferedReader entrada;
    private final PrintWriter saida;

    public SocketClient(final Socket socket) throws IOException{
        this.socket = socket;
    System.out.println("COnectado com "+ socket.getRemoteSocketAddress() + "!");
    this.entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    this.saida = new PrintWriter(socket.getOutputStream(),true);
    }
    public SocketAddress getRemoteSocketAddress(){
        return socket.getRemoteSocketAddress();
    }

    public void close(){
        try{
            entrada.close();
            saida.close();
            socket.close();
        }
        catch(IOException ex){
            System.out.println("Erro ao fechar o Socket: "+ ex.getMessage());
        }
    }

    public String getMessage(){
        try{
            return entrada.readLine();
        }
        catch(IOException ex){
            return null;
        }
    }

    public boolean sendMessage(String msg){
        saida.println(msg);
        return !saida.checkError();
    }
}
