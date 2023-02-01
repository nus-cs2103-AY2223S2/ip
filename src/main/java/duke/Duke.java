package duke;

import exceptions.DukeException;
import exceptions.UnknownInputException;

import java.io.IOException;

public class Duke {
    private UIText ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor for the Duke class.
     */
    public Duke() {
        try {
            ui = new UIText();
            taskList = new TaskList();
            storage = new Storage("./data/tasks.txt");
            storage.initialize();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        new Duke().run();

    }

    /**
     * Method to run the Duke program.
     */
    public void run() {
        boolean isClosed = false;
        TaskHandler handler = new TaskHandler(taskList);

        System.out.println(ui.greet());
        while (!isClosed) {
            try {
                String input = ui.getInput();
                System.out.println(ui.separate());
                if (input.equals("bye")) {
                    System.out.println(ui.exit());
                    isClosed = true;
                } else if (input.equals("list")) {
                    System.out.println(handler.display());
                } else if (input.startsWith("mark")) {
                    System.out.println(handler.markAsDone(input));
                    storage.saveTasks();
                } else if (input.startsWith("unmark")) {
                    System.out.println(handler.markAsUndone(input));
                    storage.saveTasks();
                } else if (input.startsWith("event")) {
                    System.out.println(handler.eventHandler(input));
                    storage.saveTasks();
                } else if (input.startsWith("todo")) {
                    System.out.println(handler.todoHandler(input));
                    storage.saveTasks();
                } else if (input.startsWith("deadline")) {
                    System.out.println(handler.deadlineHandler(input));
                    storage.saveTasks();
                } else if (input.startsWith("delete")) {
                    System.out.println(handler.deleteHandler(input));
                    storage.saveTasks();
                } else if (input.startsWith("find")) {
                    System.out.println(handler.findHandler(input));
                    storage.saveTasks();
                } else {
                    throw new UnknownInputException();
                }
                System.out.println(ui.separate());
            } catch (DukeException e) {
                System.out.println(e.getMessage() + "\n");
            }
        }
    }
}