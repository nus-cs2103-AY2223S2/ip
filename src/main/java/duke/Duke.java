package duke;

import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidDateFormatException;

import java.io.FileNotFoundException;
import java.io.IOException;

/** A class that represents a Personal Assistant Chatbot
 * that helps keep track of various things.
 */
public class Duke {
    /** Storage that handles hard-disk saving */
    private Storage storage;

    /** TaskList that keeps track of all the tasks added */
    private TaskList tasks;

    /** Ui that handles user interface jobs */
    private Ui ui;

    /**
     * Initializes an Duke object with the given values.
     *
     * @param filePath The name of the file where you keep list of tasks
     * @return A Duke instance
     * @throws FileNotFoundException
     */
    public Duke(String filePath) throws FileNotFoundException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.initialize());
    }

    /**
     * Starts running the Duke
     *
     * @throws IOException
     */
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
            } else if (str.length() >= 5 && str.toLowerCase().startsWith("find ")) {
                ui.findCommand(str);
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
