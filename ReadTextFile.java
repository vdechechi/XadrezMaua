import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadTextFile {
    private Scanner input;

    public void openFile() throws IOException {
        try {
            input = new Scanner(new File("./texto_cifrado.txt"), StandardCharsets.ISO_8859_1);
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Erro ao abrir o arquivo.");
            System.exit(1);
        }
    }

    public String readRecords() {
        StringBuilder texto = new StringBuilder();
        try {
            while (input.hasNext()) {
                String str = input.nextLine().trim(); // remove espaços extras
                texto.append(str);
            }
        } catch (NoSuchElementException elementException) {
            System.err.println("Arquivo com formatação incorreta.");
            input.close();
            System.exit(1);
        }
        return texto.toString().trim(); // remove espaços ao final
    }

    public void closeFile() {
        if (input != null) {
            input.close();
        }
    }
}
