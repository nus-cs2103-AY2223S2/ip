import java.util.Scanner;

import exception.DukeException;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class Duke {

    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

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
     * Replies to user inputs according to requirements.
     * If user inputs "bye", return to exit Duke.
     * If user inputs "list", print current tasks.
     * If user inputs "mark NUMBER" or "unmark NUMBER", update the doneness of that task number.
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

    public static void main(String[] args) {
        new Duke().run();
    }

}