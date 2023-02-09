package duke;

import java.util.Scanner;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Class to represent a Duke instance.
 * Members include a Storage object for file I/O, a TaskList object to store
 * current list of tasks and a Ui object for output to user.
 */
public class Duke {

    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    /**
     * Initializes new Duke instance with associated Storage, TaskList and Ui members.
     */
    public Duke() {
        ui = new Ui();
        try {
            storage = new Storage();
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Performs ui operations according to parsed user inputs.
     * If user inputs "bye", store list of tasks and exit.
     * If user inputs "list", print current tasks.
     * If user inputs "mark NUMBER" or "unmark NUMBER", update the status of that task number.
     * If user inputs a task, add to current tasks.
     */
    public void run() {
        try {
            ui.greet();
            storage = new Storage();
            tasks = new TaskList(storage.load());
            Scanner sc = new Scanner(System.in);
            Parser parser = new Parser(sc.nextLine());
            while (!parser.getCommand().equals("bye")) {
                ui.printLine();

                if (parser.getCommand().equals("list")) {
                    ui.listTasks(tasks);
                } else {
                    try {
                        switch (parser.getCommand()) {
                        case "mark":
                            ui.markTask(tasks, parser);
                            break;
                        case "unmark":
                            ui.unmarkTask(tasks, parser);
                            break;
                        case "todo":
                            ui.addToDo(tasks, parser);
                            break;
                        case "deadline":
                            ui.addDeadline(tasks, parser);
                            break;
                        case "event":
                            ui.addEvent(tasks, parser);
                            break;
                        case "delete":
                            ui.deleteTask(tasks, parser);
                            break;
                        case "find":
                            ui.findAndListTasks(tasks, parser);
                            break;
                        default:
                            throw new DukeException("I'm sorry, but I don't know what that means :-(");
                        }
                    } catch (DukeException e) {
                        System.out.printf("     ☹ OOPS!!! %s%n", e.getMessage());
                    }
                }
                ui.printLine();
                parser.updateInput(sc.nextLine());
            }
            ui.farewell();
            storage.store(tasks);
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    /**
     * Begins running a new Duke instance.
     *
     * @param args Command-line arguments (ignored).
     */
    public static void main(String[] args) {
        new Duke().run();
    }

}
