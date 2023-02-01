package membot;

import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import membot.commands.Command;
import membot.model.Task;
import membot.storage.StorageManager;
import membot.utils.EmptyInputException;
import membot.utils.InvalidCommandException;
import membot.view.Printable;
import membot.view.Printer;

/**
 * Main application class.
 */
public class Membot {
    private static final String FILE_NAME = "./data/tasks.txt";
    private static final int EXIT_DELAY = 800;
    private static final String LOGO =
              "                             _             _   \n"
            + " _ __ ___    ___  _ __ ___  | |__    ___  | |_ \n"
            + "| '_ ` _ \\  / _ \\| '_ ` _ \\ | '_ \\  / _ \\ | __|\n"
            + "| | | | | ||  __/| | | | | || |_) || (_) || |_ \n"
            + "|_| |_| |_| \\___||_| |_| |_||_.__/  \\___/  \\__|\n";

    private final Printable ui;
    private StorageManager manager;

    public Membot(Printable ui) {
        this.ui = ui;

        try {
            this.manager = new StorageManager(FILE_NAME);
            Task.load(manager.loadFromFile());
        } catch (IOException e) {
            ui.printlnError(e.toString());
            exit();
        }
    }

    public void execute(String input) {
        String s = input.trim();
        Command command;

        try {
            command = Command.parse(s, ui, this);
            command.execute();
        } catch (EmptyInputException | InvalidCommandException e) {
            this.ui.printlnError("Sorry I do not understand what to do!");
        }
    }

    public void exit() {
        if (this.manager != null) {
            try {
                Task.save(this.manager);
            } catch (IOException e) {
                this.ui.printlnError(e.toString());
            }
        }

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, EXIT_DELAY);

    }

//    /**
//     * Main application loop.
//     */
//    private static void run() {
//        Printable ui = new Printer();
//        StorageManager manager = null;
//        try {
//            manager = new StorageManager(FILE_NAME);
//            Task.load(manager.loadFromFile());
//        } catch (IOException e) {
//            ui.printlnError(e.toString());
//        }
//        ui.println(true, "Welcome to\n" + LOGO);
//        ui.println(true, "How may I assist you today?");
//        Scanner scanner = new Scanner(System.in);
//
//        while (scanner.hasNext()) {
//            String input = scanner.nextLine().trim();
//
//            Command command;
//
//            try {
//                command = Command.parse(input, ui);
//            } catch (EmptyInputException | InvalidCommandException e) {
//                ui.printlnError("Sorry I do not understand what to do!");
//                continue;
//            }
//
//            command.execute();
//
//            if (command.isExit()) {
//                break;
//            }
//        }
//
//        scanner.close();
//
//        if (manager != null) {
//            try {
//                Task.save(manager);
//            } catch (IOException e) {
//                ui.printlnError(e.toString());
//            }
//        }
//    }
}
