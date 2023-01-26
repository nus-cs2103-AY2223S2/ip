package seedu.duke;

public class Parser {
    private final static String LIST = "list";
    private final static String MARK = "mark";
    private final static String UNMARK = "unmark";
    private final static String DELETE = "delete";

    private void list(TaskList tasks) {
        Ui.indentedPrintln("Here are the tasks in your list:");
        int numOfTasks = tasks.size();
        for (int i = 1; i <= numOfTasks; i++) {
            Ui.indentedPrintln(i + "." + tasks.get(i - 1));
        }
    }

    private boolean isValidTask(String command) {
        if (command.length() >= 4 && command.substring(0, 4).equals("todo")) {
            if (command.length() <= 5) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            return true;
        } else if (command.length() >= 8 && command.substring(0, 8).equals("deadline")) {
            if (command.length() <= 9) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            return true;
        } else if (command.length() >= 5 && command.substring(0, 5).equals("event")) {
            if (command.length() <= 6) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            return true;
        }
        return false;
    }

    public void parseInput(String str, TaskList tasks, Storage storage) {
        if (str.equals(LIST)) {
            list(tasks);
        } else if (str.length() >= 4 && str.substring(0, 4).equals(MARK)) {
            tasks.mark(Character.getNumericValue(str.charAt(5)), storage);
        } else if (str.length() >= 6 && str.substring(0, 6).equals(UNMARK)) {
            tasks.unmark(Character.getNumericValue(str.charAt(7)), storage);
        } else if (isValidTask(str)) {
            tasks.addTask(str, storage);
        } else if (str.length() >= 6 && str.substring(0, 6).equals(DELETE)) {
            tasks.deleteTask(Character.getNumericValue(str.charAt(7)));
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
