import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws FileNotFoundException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.initialize());
    }

    public void run() throws IOException {
        ui.greet();
        String str;
        while (true) {
            str = ui.getLine();
            if (str.equals("bye")) {
                ui.goodBye();
                tasks.close();
                break;
            }
            if (str.equalsIgnoreCase("list")) {
                ui.listCommand();
            } else if (str.length() >= 5 && str.toLowerCase().startsWith("mark ")) {
                ui.markCommand(str, storage);
            } else if (str.length() >= 6 && str.toLowerCase().startsWith("unmark ")) {
                ui.unmarkCommand(str, storage);
            } else if (str.length() >= 5 && str.toLowerCase().startsWith("todo ")) {
                ui.todoCommand(str, storage);
            } else if (str.length() >= 6 && str.toLowerCase().startsWith("event ")) {
                ui.eventCommand(str, storage);
            } else if (str.length() >= 9 && str.toLowerCase().startsWith("deadline ")) {
                ui.deadlineCommand(str, storage);
            } else if (str.length() >= 7 && str.toLowerCase().startsWith("delete ")) {
                ui.deleteCommand(str, storage);
            } else {
                System.out.println(new InvalidCommandException().getMessage());
            }
        }

        ui.close();
    }
    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }
}
