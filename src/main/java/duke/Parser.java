package duke;

import command.Command;
import command.CommandBye;
import command.CommandDeadline;
import command.CommandDelete;
import command.CommandEvent;
import command.CommandFind;
import command.CommandInvalid;
import command.CommandList;
import command.CommandMark;
import command.CommandToDo;
import command.CommandUnMark;
import task.TaskList;

/**
 * Class use for decoding user input.
 */
public class Parser {

    private final TaskList taskList;

    /**
     * Constructor for Parser.
     *
     * @param taskList List of all task.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Match user input to a command.
     *
     * @param userInput Input of user.
     * @return Command interpreted.
     */
    public Command decode(String userInput) {

        String[] arr = userInput.split(" ", 2);

        if (arr[0].equals(DukeKeyword.EXIT)) {
            return new CommandBye();
        }
        if (arr[0].equals(DukeKeyword.VIEW_LIST)) {
            return new CommandList(taskList);
        }
        if (arr[0].equals(DukeKeyword.MARK_TASK)) {
            String index = arr[1];
            return new CommandMark(taskList, index);
        }
        if (arr[0].equals(DukeKeyword.UNMARK_TASK)) {
            String index = arr[1];
            return new CommandUnMark(taskList, index);
        }
        if (arr[0].equals(DukeKeyword.DELETE_TASK)) {
            String index = arr[1];
            return new CommandDelete(taskList, index);
        }
        if (arr[0].equals(DukeKeyword.ADD_TODO_TASK)) {
            String taskDetails = arr[1];
            return new CommandToDo(taskList, taskDetails);
        }
        if (arr[0].equals(DukeKeyword.ADD_DEADLINE_TASK)) {
            String taskDetails = arr[1];
            return new CommandDeadline(taskList, taskDetails);
        }
        if (arr[0].equals(DukeKeyword.ADD_EVENT_TASK)) {
            String taskDetails = arr[1];
            return new CommandEvent(taskList, taskDetails);
        }
        if (arr[0].equals(DukeKeyword.FIND)) {
            String phrase = arr[1];
            return new CommandFind(taskList, phrase);
        }
        return new CommandInvalid();
    }
}
