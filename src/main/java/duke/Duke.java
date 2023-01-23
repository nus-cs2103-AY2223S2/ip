package duke;

import duke.task.TaskList;

import java.util.ArrayList;

public class Duke {
    private static String[] spStg;
    private Ui ui;
    private TaskList tasks;


    public Duke() {
        ui = new Ui();
        try {
            tasks = new TaskList(Storage.loadFile());
        } catch (Exception e) {
            tasks = new TaskList(new ArrayList<>());
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        ui.showWelcome();

        while (true) {
            String fullCommand = ui.readCommand();
            String firstWord = Parser.getFirstWord(fullCommand);

            switch (firstWord) {
                case "bye":
                    ui.showBye();
                    return;

                case "list":
                    tasks.list();
                    break;

                case "delete":
                    tasks.delete(Parser.getIndex(fullCommand));
                    break;

                case "mark":
                    tasks.mark(Parser.getIndex(fullCommand));
                    break;

                case "unmark":
                    tasks.unmark(Parser.getIndex(fullCommand));
                    break;

                case "todo":
                    tasks.addTodo(fullCommand);
                    break;

                case "deadline":
                    tasks.addDeadline(fullCommand);
                    break;

                case "event":
                    tasks.addEvent(fullCommand);
                    break;

                case "find":
                    tasks.find(fullCommand);
                    break;

                default:
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    continue;
            }

            Storage.saveToFile(tasks.getList());
        }
    }
}



