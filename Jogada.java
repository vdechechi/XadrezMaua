import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jogada {

    private static final String url = "jdbc:mysql://localhost:3306/trablp";
    private static final String user = "root";
    private static final String password = "123456";

    private static Connection conn;

    public static Connection getConexao() {
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url, user, password);
                System.out.println("Conexão estabelecida com sucesso.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC não encontrado: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    public static void fecharConexao() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexão fechada com sucesso.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
