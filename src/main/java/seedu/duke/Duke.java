package seedu.duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * A personal assistant chatbox to manage daily tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private final static String BYE = "bye";

    /**
     * Constructs a new Duke object
     */
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
