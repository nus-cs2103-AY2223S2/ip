package duke;

import java.util.*;

public class Duke {
    private static TaskList tasks;
    private static Storage dataSaver;
    private static Ui ui;

    public Duke() {
        ui = new Ui();
        try {
            dataSaver = new Storage();
            tasks = new TaskList(dataSaver.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<>());
        }
    }

    public void run() {
        try {
            Ui.greet();
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            while (!input.equals("bye")) {
                if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(String.valueOf(i + 1) + tasks.get(i).toString());
                    }
                } else {
                    try {
                        String command = (input.split(" ")[0]).toLowerCase();
                        switch (command) {
                            case "mark":
                                Ui.markTask(tasks, input);
                                break;
                            case "unmark":
                                Ui.unmarkTask(tasks, input);
                                break;
                            case "todo":
                                Ui.addToDo(tasks, input);
                                break;
                            case "deadline":
                                Ui.addDeadline(tasks, input);
                                break;
                            case "event":
                                Ui.addEvent(tasks, input);
                                break;
                            case "delete":
                                Ui.deleteTask(tasks, input);
                                break;
                            default:
                                throw new DukeException("Please enter a valid command.");
                        }
                    } catch (DukeException e) {
                        System.out.printf("Error: " + e.getMessage());
                    }
                }
                input = sc.nextLine();
            }
            Ui.farewell();
            dataSaver.save(tasks);
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
