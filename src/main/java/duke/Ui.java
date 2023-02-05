package duke;

import duke.exception.DukeException;

public class Ui {
    private TaskList tasks;
    private static final String GREETINGS_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you soon!";

    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    String acceptCommand(String command) throws DukeException {
        String printable = Parser.processCommand(command, tasks);
        return printable;
    }

    void showGreetingsMessage() {
        System.out.println(display(GREETINGS_MESSAGE));
    }

    void showGoodbyeMessage() {
        System.out.println(display(GOODBYE_MESSAGE));
    }

    void showLoadingError() {
        System.out.println(display("Error loading saving"));
    }

    void showSavingError() {
        System.out.println(display("Error saving tasks"));
    }

    String showErrorMessage(Exception e) {
        return e.getMessage();
    }

    String display(String message) {
        String horizontalLine = "\t______________________________________\n";
        String[] responseArr = message.split("\\r?\\n");
        StringBuilder responseFinal = new StringBuilder();
        for (String r: responseArr) {
            responseFinal.append("\t").append(r).append("\n");
        }
        return String.format("%s%s%s", horizontalLine, responseFinal, horizontalLine);
    }
}
