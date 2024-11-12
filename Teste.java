public class Teste {
    public static void main(String[] args) {
        // Inicializa o gerenciador de idioma
        IdiomaManager idiomaManager = new IdiomaManager();

        // Define o idioma padrão para português do Brasil
        idiomaManager.setLocale("pt", "BR");

        // Cria a janela de login com o gerenciador de idioma e a torna visível
        new MenuLogin(idiomaManager).setVisible(true);
    }
}
