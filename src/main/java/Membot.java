import commands.Command;
import model.Task;
import storage.StorageManager;
import utils.*;
import view.Printable;
import view.Printer;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main application class.
 */
public class Membot {
    private static final String FILE_NAME = "./data/tasks.txt";
    private static final String LOGO =
              "                             _             _   \n"
            + " _ __ ___    ___  _ __ ___  | |__    ___  | |_ \n"
            + "| '_ ` _ \\  / _ \\| '_ ` _ \\ | '_ \\  / _ \\ | __|\n"
            + "| | | | | ||  __/| | | | | || |_) || (_) || |_ \n"
            + "|_| |_| |_| \\___||_| |_| |_||_.__/  \\___/  \\__|\n";

    /**
     * Main application loop.
     */
    private static void run() {
        Printable ui = new Printer();
        StorageManager manager = null;
        try {
            manager = new StorageManager(FILE_NAME);
            Task.load(manager.loadFromFile());
        } catch (IOException e) {
            ui.printlnError(e.toString());
        }
        ui.println("Welcome to\n" + LOGO);
        ui.println("How may I assist you today?");
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String input = scanner.nextLine().trim();

            Command command;

            try {
                command = Command.parse(input, ui);
            } catch (EmptyInputException | InvalidCommandException e) {
                ui.printlnError("Sorry I do not understand what to do!");
                continue;
            }

            command.execute();

            if (command.isExit()) {
                break;
            }
        }

        scanner.close();

        if (manager != null) {
            try {
                Task.save(manager);
            } catch (IOException e) {
                ui.printlnError(e.toString());
            }
        }
    }

    /**
     * Main entry point to Membot.
     *
     * @param args Optional arguments when starting Membot.
     */
    public static void main(String[] args) {
        run();
    }
}
