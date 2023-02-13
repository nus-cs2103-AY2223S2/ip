package duke;

import java.util.ArrayList;

/**
 * The class handling all the user input
 */
public class Parser {
    private TaskList taskListHandler = new TaskList();

    /**
     * A method that check the user input and pass to the correct method to handle it
     * @param taskArrayList arraylist containing all the task
     * @param inputType     the user input of the type of tasks
     * @return boolean type to allow the user know if a task was added into the list
     * @throws DukeException when there is error in user input
     */
    public String parserInput(ArrayList<Task> taskArrayList, String inputType) throws DukeException {
        String[] tokens = inputType.split("\\s+");
        if (tokens[0].equalsIgnoreCase("list")) {
            return taskListHandler.showList(taskArrayList);
        } else if (tokens[0].equalsIgnoreCase("mark")) {
            if (tokens.length == 1) {
                throw new MissingDescription();
            }
            int index = Integer.parseInt(tokens[1]);
            return taskListHandler.mark(taskArrayList, index);
        } else if (tokens[0].equalsIgnoreCase("unmark")) {
            if (tokens.length == 1) {
                throw new MissingDescription();
            }
            int index = Integer.parseInt(tokens[1]);
            return taskListHandler.unMark(taskArrayList, index);
        } else if (tokens[0].equalsIgnoreCase("todo")) {
            if (tokens.length == 1) {
                throw new MissingDescription();
            }
            String des = inputType.substring(inputType.indexOf(" ")).trim();
            return taskListHandler.toDo(taskArrayList, des);
        } else if (tokens[0].equalsIgnoreCase("deadline")) {
            if (tokens.length == 1) {
                throw new MissingDescription();
            }
            return taskListHandler.deadline(taskArrayList, inputType);
        } else if (tokens[0].equalsIgnoreCase("event")) {
            if (tokens.length == 1) {
                throw new MissingDescription();
            }
            return taskListHandler.event(taskArrayList, inputType);
        } else if (tokens[0].equalsIgnoreCase(("delete"))) {
            if (tokens.length == 1) {
                throw new MissingDescription();
            }
            return taskListHandler.delete(taskArrayList, inputType);
        } else if (inputType.contains("by")) {
            if (tokens.length == 1) {
                throw new DukeException("I do not understand what you type >.< !! Enter in by/ YYYY-MM-DD");
            }
            return taskListHandler.deadlineChecker(taskArrayList, inputType);
        } else if (tokens[0].equalsIgnoreCase("find")) {
            if (tokens.length == 1) {
                throw new MissingDescription();
            }
            return taskListHandler.find(tokens[1], taskArrayList);
        } else {
            return "OOPS!! I'm sorry, but I don't know what that means :-(";
        }
    }
}
