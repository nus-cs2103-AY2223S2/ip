package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Gui represents a Ui that helps to handle input and ouput for graphics.
 */
public class Gui implements IoHandler {
    private String input;
    private String command;
    private String others;

    public Gui() {}

    /**
     * Constuctor for Gui.
     *
     * @param input The graphical input for gui to handle.
     */
    public Gui(String input) {
        this.input = input;
        breakInputIntoSubstrings();
    }

    public void setInput(String input) {
        this.input = input;
        breakInputIntoSubstrings();
    }

    private void breakInputIntoSubstrings() {
        String[] commands = input.split(" ", 2);
        try {
            command = commands[0];
            others = commands[1];
        } catch (IndexOutOfBoundsException e) {
            others = "";
        } catch (NullPointerException e) {
            System.out.println(input);
            System.out.println(commands);
        }
    }

    @Override
    public String produceInputAsOutput() {
        return produceDukeOutput("Duke: " + others.strip() + "\n");
    }

    @Override
    public void throwAwayInput() {
        input = "";
        command = "";
        others = "";
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public String getName() throws DukeException {
        String name = others.strip();
        if (name == "") {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return name;
    }

    @Override
    public String[] getDeadline() throws DukeException {
        try {
            String[] sorted = others.split(" /by ");
            String[] data = new String[] {sorted[0].strip(), sorted[1].strip()};
            return data;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(
                    "The deadline command should be used like this:\n" + "\tdeadline {name} /by {date}");
        }
    }

    @Override
    public String[] getEvent() throws DukeException {
        try {
            String[] line = others.split(" /from ", 2);
            String[] dates = line[1].split(" /to ", 2);
            String[] data = new String[] {line[0].strip(), dates[0].strip(), dates[1].strip()};
            return data;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The event command should be used like this:\n"
                    + "\tevent {name} /from {YYYY-MM-DD} /to {YYYY-MM-DD}");
        }
    }

    @Override
    public int getTaskNum() throws DukeException {
        try {
            String s = others.strip();
            int num = Integer.parseInt(s) - 1;
            return num;
        } catch (NumberFormatException e) {
            throw new DukeException("Please input a valid task number");
        }
    }

    @Override
    public String produceDukeOutput(String s) {
        return s;
    }

    @Override
    public String greet() {
        // @formatter:off
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        // @formatter:on
        String greetString = "Hello from\n" + logo + "\nWhat can I do for you?\n";

        return greetString;
    }

    @Override
    public String produceTaskListOutput(TaskList tasks) {
        String ls = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task temp = tasks.get(i);
            ls = ls + "" + Integer.toString(i + 1) + "." + temp.toString() + "\n";
        }
        return produceDukeOutput(ls);
    }

    @Override
    public String produceGoodbyeOutput() {
        return produceDukeOutput("\tGoodbye!\n");
    }

    @Override
    public String produceExceptionOutput(String message) {
        return produceDukeOutput("\t" + message + "\n");
    }

    @Override
    public String undoOutput() {
        return null;
    }

}
