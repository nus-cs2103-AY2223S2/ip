package baymax;

import commands.ByeCommand;
import commands.WelcomeCommand;

import exceptions.BaymaxException;

import java.util.Scanner;

import commands.Command;

public class Baymax {

    private final Storage storage;
    private TaskList tasks;
    private Scanner input = new Scanner(System.in);
    private Parser parser = new Parser();
    private static Ui ui = new Ui();

    public Baymax(String filepath) {
        Ui ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks= new TaskList(storage.load());
        } catch (BaymaxException e) {
            ui.showLoadingErrorMessage();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Baymax("./data/Baymax.txt").run();
    }

    public void run() {
        Command welcomeCommand = new WelcomeCommand();
        welcomeCommand.execute(tasks, ui, storage);
        while (input.hasNextLine()) {
            Command command = parser.parse(input.nextLine());
            command.execute(tasks, ui, storage);
            if (command instanceof ByeCommand) {
                break;
            }
        }
    }
}

