package jarvis;

import jarvis.command.Command;
import jarvis.exception.InvalidActionException;
import jarvis.task.TaskList;

import java.util.Scanner;

public class Jarvis {
    private static final String BOT_NAME = Jarvis.class.getSimpleName();

    private final Storage storage;
    private final Ui ui;
    private final TaskList taskList;

    public Jarvis() {
        this.storage = new Storage();
        this.ui = new Ui(BOT_NAME);
        this.taskList = new TaskList(storage.readTasks());
    }

    public void run() {
        this.ui.printLogo();
        this.ui.printStandard(Ui.Response.INTRO);

        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);
        while (!isExit && scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isBlank()) {
                this.ui.printUserPrompt();
                continue;
            }

            Command command;
            try {
                command = Parser.parse(line);
            } catch (InvalidActionException e) {
                this.ui.printStandard(Ui.Response.CONFUSED);
                continue;
            }
            command.execute(this.ui, this.taskList, this.storage);
            isExit = command.isExit();
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Jarvis().run();
    }
}
