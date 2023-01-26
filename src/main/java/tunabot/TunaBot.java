package tunabot;

import tunabot.exceptions.InputException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class TunaBot {
    private Storage storage;
    private Ui ui;
    private static final Scanner s = new Scanner(System.in);
    private static TaskList tasks;
    private static boolean toExit = false;
    public TunaBot(Path savePath) {
        ui = new Ui();
        storage = new Storage(savePath);
        try {
            tasks = storage.load();
        } catch (IOException e) {
            ui.saveFileProblem();
        }
    }
    public void run() {
        ui.greeting();
        while (!toExit) {
            try {
                String input = s.nextLine();
                ui.line();
                toExit = Parser.parse(input, tasks);
            } catch (InputException e) {
                ui.printErrorMessage(e);
            } catch (DateTimeParseException e) {
                ui.printDateTimeFormatError();
            }
            ui.line();
        }
        storage.save(tasks);
    }
    public static void main(String[] args) {
        Path savePath = Paths.get("data", "save.txt");
        new TunaBot(savePath).run();
    }
}
