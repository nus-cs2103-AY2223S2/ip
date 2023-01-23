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
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        while (!word.equals(Commands.bye.name())) {
            System.out.println("-".repeat(20));
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
                    this.ui.showCommandError(word);
                }
            } else if (word.startsWith(Commands.delete.name())) {
                try {
                    int index = Integer.parseInt(word.substring(7));
                    this.ui.showDeleted(this.tasks.remove(--index));
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    // incorrect syntax
                    this.ui.showCommandError(word);
                }
            } else {
                try {
                    Task task = Task.commandToTask(word);
                    this.tasks.addingTask(task);
                    ui.showTaskAdded(task, this.tasks);
                } catch (CommandException commandException) {
                    ui.showCommandError(word);
                } catch (DescriptionException | StringIndexOutOfBoundsException descriptionException) {
                    ui.showDescriptionError(word);
                }
            }
            System.out.println("-".repeat(20));
            word = scanner.nextLine();
        }
        try {
            this.storage.store(this.tasks);
            this.ui.showStored(this.tasks);
        } catch (IOException ioException) {
            System.out.println(ioException);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
