package duke;

import duke.Command.Commands;
import duke.Exceptions.CommandException;
import duke.Tasks.Task;
import duke.Tasks.TaskList;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke(".\\tasks.txt").run();
    }

    public void run() {
        this.ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        while (!word.equals(Commands.bye.name())) {
            this.ui.showLine();
            if (word.equals(Commands.list.name())) {
                int count = 1;
                for (Task words: this.tasks) {
                    System.out.println(count++ + ": "+ words.toString());
                }
            } else if (word.startsWith(Commands.mark.name())) {
                try {
                    int index = Integer.parseInt(word.substring(5));
                    Task task = this.tasks.get(--index);
                    task.markDone();
                    this.ui.showMarked(task);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    // incorrect syntax
                    this.ui.showCommandError(word, new CommandException(e));
                }
            } else if (word.startsWith(Commands.delete.name())) {
                try {
                    int index = Integer.parseInt(word.substring(7));
                    this.ui.showDeleted(this.tasks.remove(--index));
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    // incorrect syntax
                    this.ui.showCommandError(word, new CommandException(e));
                }
            } else {
                try {
                    Task task = Task.commandToTask(word);
                    this.tasks.addingTask(task);
                    ui.showTaskAdded(task, this.tasks);
                } catch (CommandException | StringIndexOutOfBoundsException commandException) {
                    ui.showCommandError(word, commandException);
                }
            }
            this.ui.showLine();
            word = scanner.nextLine();
        }
        try {
            this.storage.store(this.tasks);
            this.ui.showStored(this.tasks);
        } catch (IOException ignored) {
        }
        this.ui.goodbye();
    }
}
