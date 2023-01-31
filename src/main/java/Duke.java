import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import exception.DukeException;
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
    public static void run() {
        try {
            ui.greet();
            storage = new Storage();
            tasks = new TaskList(storage.load());
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            while (!input.equals("bye")) {
                ui.printLine();

                if (input.equals("list")) {
                    System.out.printf("     %s%n", "Here are the tasks in your list:");
                    for (int i = 0; i < tasks.getSize(); i++) {
                        System.out.printf("     %d.%s%n",
                                i + 1,
                                tasks.getTask(i).toString());
                    }
                } else {
                    try {
                        String command = (input.split(" ")[0]).toLowerCase();
                        switch (command) {
                        case "mark":
                            ui.markTask(tasks, input);
                            break;
                        case "unmark":
                            ui.unmarkTask(tasks, input);
                            break;
                        case "todo":
                            ui.addToDo(tasks, input);
                            break;
                        case "deadline":
                            ui.addDeadline(tasks, input);
                            break;
                        case "event":
                            ui.addEvent(tasks, input);
                            break;
                        case "delete":
                            ui.deleteTask(tasks, input);
                            break;
                        default:
                            throw new DukeException("I'm sorry, but I don't know what that means :-(");
                        }
                    } catch (DukeException e) {
                        System.out.printf("     ☹ OOPS!!! %s%n", e.getMessage());
                    }
                }
                ui.printLine();
                input = sc.nextLine();
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