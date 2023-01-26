import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private final static String BYE = "bye";

    public Duke() {
        storage = new Storage();
        parser = new Parser();
    }

    private void run() {
        Ui.printLogo();

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equals(BYE)) {
            try {
                tasks = storage.fileToArrayList();
                parser.parseInput(str, tasks, storage);
            } catch (DukeException e1) {
                System.err.println("     " + e1);
            } catch (IOException e2) {
                System.err.println(e2.getMessage());
            } finally {
                System.out.println();
                str = sc.nextLine();
            }
        }
        Ui.indentedPrintln(Ui.GOODBYE_MESSAGE);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
