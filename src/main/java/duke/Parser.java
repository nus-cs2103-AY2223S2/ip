package duke;

import java.util.ArrayList;

/**
 * The class handling all the user input
 */
public class Parser {
    private TaskList handler = new TaskList();

    /**
     * @param taskArrayList arraylist containing all the task
     * @param inputType the user input of the type of tasks
     * @return boolean type to allow the user know if a task was added into the list
     * @throws DukeException when there is error in user input
     */
    public boolean parserInput(ArrayList<Task> taskArrayList, String inputType) throws DukeException {
        String[] tokens = inputType.split("\\s+");
        if (tokens[0].equalsIgnoreCase("bye")) {
            handler.bye();
            return true;
        } else if (tokens[0].equalsIgnoreCase("list")) {
            handler.showList(taskArrayList);
            return true;
        } else if (tokens[0].equalsIgnoreCase("mark")) {
            if (tokens.length == 1) {
                throw new MissingDescription();
            }
            int index = Integer.parseInt(tokens[1]);
            handler.mark(taskArrayList, index);
            return true;
        } else if (tokens[0].equalsIgnoreCase("unmark")) {
            if (tokens.length == 1) {
                throw new MissingDescription();
            }
            int index = Integer.parseInt(tokens[1]);
            handler.unMark(taskArrayList, index);
            return true;
        } else if (tokens[0].equalsIgnoreCase("todo")) {
            if (tokens.length == 1) {
                throw new MissingDescription();
            }
            String des = inputType.substring(inputType.indexOf(" ")).trim();
            handler.toDo(taskArrayList, des);
            return true;
        } else if (tokens[0].equalsIgnoreCase("deadline")) {
            if (tokens.length == 1) {
                throw new MissingDescription();
            }
            handler.deadline(taskArrayList, inputType);
            return true;
        } else if (tokens[0].equalsIgnoreCase("event")) {
            if (tokens.length == 1) {
                throw new MissingDescription();
            }
            handler.event(taskArrayList, inputType);
            return true;
        } else if (tokens[0].equalsIgnoreCase(("delete"))) {
            if (tokens.length == 1) {
                throw new MissingDescription();
            }
            handler.delete(taskArrayList, inputType);
            return true;
        } else if (inputType.contains("by")) {
            if (tokens.length == 1) {
                throw new MissingDescription();
            }
            handler.deadlineChecker(taskArrayList, inputType);
            return true;
        } else if (tokens[0].equalsIgnoreCase("find")) {
            if (tokens.length == 1) {
                throw new MissingDescription();
            }
            handler.find(tokens[1], taskArrayList);
            return false;
        } else {
            System.out.println("OOPS!! I'm sorry, but I don't know what that means :-(");
            return false;
        }
    }
}
