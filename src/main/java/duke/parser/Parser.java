package duke.parser;

import duke.exception.DukeException;
import duke.task.TaskList;

public class Parser {
    private TaskList tasksList = new TaskList();

    public String getInput(String input) {
        try {
            String command = input.split(" ")[0];

            switch (command) {
            case "bye":
                // TODO: HOW DO I CLOSE THE PROGRAM AT THIS STAGE???
                return "Bye. Hope to see you again soon!";
            case "list":
                return tasksList.listTaskList();
            case "mark":
                return tasksList.markTask(input);
            case "unmark":
                return tasksList.unmarkTask(input);
            case "todo":
                return tasksList.addToDo(input);
            case "deadline":
                return tasksList.addDeadline(input);
            case "event":
                return tasksList.addEvent(input);
            case "delete":
                return tasksList.deleteTask(input);
            case "find":
                return tasksList.findEvent(input);
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            // if you try and catch the exception, you can still continue to run the program! WOW!
            e.printStackTrace();
            return "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }
}
