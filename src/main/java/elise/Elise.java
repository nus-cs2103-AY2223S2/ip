package elise;

import java.io.IOException;
import java.util.Scanner;

/**
 * Elise is a personal assistant chat-bot that help to keep track of various stuff.
 */
public class Elise {
    // Attribute
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for Elise.
     *
     * @param filePath Path of initial data file.
     * @throws EliseException if filePath is invalid.
     */
    public Elise(String filePath) {
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            taskList = new TaskList(storage.load());
        } catch (EliseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs Elise chat-bot.
     *
     * @throws IOException Unexpected IOException
     */
    public void run() {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            try {
                Command c = Parser.read(sc);
                System.out.println(c.execute(ui, taskList, storage));
                if (c.isExit()) {
                    return;
                }
            } catch (EliseException e) {
                ui.showError(e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getResponse(String input) {
        Scanner sc = new Scanner(input);
        try {
            Command c = Parser.read(sc);
            c.execute(ui, taskList, storage);
            if (c.isExit()) {
                return "Bye";
            }
        } catch (EliseException e) {
            return e.toString();
        } catch (IOException e) {
            return e.toString();
        }
        return "";
    }

    /**
     * Driver function.
     *
     * @param args Command line arguments
     * @throws EliseException If filePath is invalid
     * @throws IOException Unexpected IOException
     */
    public static void main(String[] args) throws EliseException, IOException {
        Elise elise1 = new Elise("./data/list.txt");
        elise1.run();
    }
}
