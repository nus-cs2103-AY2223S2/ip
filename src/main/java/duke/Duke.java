package duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;

import storage.Storage;
import task.Command;
import task.Deadline;
import task.Event;
import task.Parser;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;

/**
 * Duke is the body part of the chatbot we designed.
 */
public class Duke {
    protected final TaskList taskList;
    protected final ArrayList<String> commandList;
    protected Parser parser;
    protected Storage storage;
    protected Ui ui;
    protected String directoryPath;
    protected String dataFilePath;

    /**
     * Constructor
     */
    public Duke() {
        this.dataFilePath = "./data/duke.txt";
        this.directoryPath = "./data";
        this.commandList = new ArrayList<>();
        this.taskList = new TaskList();
        this.parser = new Parser();
        this.storage = new Storage(directoryPath, dataFilePath);
        this.ui = new Ui();
    }

    /**
     * Adds the task to the taskList.
     *
     * @param taskGiven The task we want to add to the list.
     */
    public void addTask(Task taskGiven) {
        taskList.add(taskGiven);
    }

    /**
     * Returns the message of successfully adding a task to the taskList.
     *
     * @param task The task we want to add to the list.
     * @return Message of successful adding.
     */
    public String messageOfAdd(Task task) {
        return "Got it. I've added this task:\n " + task.toString() + "\nNow you have "
                + taskList.size() + " tasks in the list:D";
    }


    /**
     * Marks a task as done.
     *
     * @param index The index of the task we want to mark as done.
     * @return Message of marking a task as done.
     * @throws DukeException When any DukeException happens.
     */
    public String markAsDone(int index) throws DukeException {
        if (taskList.size() <= index) {
            throw new WrongIndexException();
        }
        Task task = this.taskList.get(index);
        task.mark();
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    /**
     * Marks a task as undone.
     *
     * @param index The index of the task we want to mark as undone.
     * @return Message of marking a task as undone.
     * @throws DukeException When any DukeException happens.
     */
    public String markAsUndone(int index) throws DukeException {
        if (taskList.size() <= index) {
            throw new WrongIndexException();
        }
        Task task = this.taskList.get(index);
        task.unmark();
        return "OK, I've marked this task as not done yet:\n" + task.toString();
    }

    /**
     * Deletes a task from the taskList.
     *
     * @param index The index of the task we want to delete.
     * @return Message of deletion.
     * @throws WrongIndexException If the index is out of bound.
     */
    public String deleteMessage(int index) throws WrongIndexException {
        if (taskList.size() <= index) {
            throw new WrongIndexException();
        }
        Task task = taskList.get(index);
        taskList.remove(index);
        return "Noted. I've removed this task:\n " + task.toString()
                + "\nNow you have " + taskList.size() + " tasks in the list.";

    }

    /**
     * Prints the matching tasks in the taskList.
     *
     * @param keywordList Keywords used for searching.
     * @return Tasks that contains the keyword.
     */
    public String findMessage(String... keywordList) {
        return "Here are the matching tasks in your list:\n" + taskList.getTasksWanted(keywordList);
    }

    public String handleUserInput(String userInput, boolean suppressPrint) throws DukeException {
        String output = "";
        String[] expressions = userInput.split(" ");
        String command = expressions[0];

        parser.checkEmpty(userInput, command);
        parser.checkInvalidInput(userInput, command);

        if (parser.isCommand(userInput, Command.LIST)) {
            Collections.sort(taskList);
            output = taskList.printCurrentTasks();
        } else if (parser.isCommand(command, Command.MARK)) {
            String[] words = userInput.split(" ");
            int index = Integer.parseInt(words[1]) - 1;
            output = markAsDone(index);
        } else if (parser.isCommand(command, Command.UNMARK)) {
            String[] words = userInput.split(" ");
            int index = Integer.parseInt(words[1]) - 1;
            output = markAsUndone(index);
        } else if (parser.isCommand(userInput, Command.BYE)) {
            output = ui.ending();
        } else if (parser.isCommand(command, Command.TODO)) {
            Todo todoTask = new Todo(userInput);
            addTask(todoTask);
            output = messageOfAdd(todoTask);
        } else if (parser.isCommand(command, Command.DEADLINE)) {
            Deadline ddlTask = new Deadline(userInput);
            addTask(ddlTask);
            output = messageOfAdd(ddlTask);
        } else if (parser.isCommand(command, Command.EVENT)) {
            Event eventTask = new Event(userInput);
            addTask(eventTask);
            output = messageOfAdd(eventTask);
        } else if (parser.isCommand(command, Command.DELETE)) {
            String[] words = userInput.split(" ");
            int index = Integer.parseInt(words[1]) - 1;
            output = deleteMessage(index);
        } else if (parser.isCommand(command, Command.FIND)) {
            String keywords = userInput.substring(5);
            String[] keywordList = keywords.split(" ");
            output = findMessage(keywordList);
        } else {
            throw new WeirdInputException();
        }

        if (!suppressPrint) {
            return output;
        } else {
            return "";
        }
    }

    /**
     * Loads the data stored in the disk.
     */
    public String loadDataFromDisk() {
        String errorMessage = storage.loadData(commandList);
        for (String userInput: commandList) {
            try {
                handleUserInput(userInput, true);
            } catch (DukeException exc) {
                String output = exc.toString();
            } catch (DateTimeParseException exc) {
                String output = exc.toString();
            }
        }
        return errorMessage;
    }

    /**
     * Output the response to user input.
     * @param userInput Command input by the user.
     * @return Corresponding output.
     */
    public String getResponse(String userInput) {
        storage.saveToDisk(userInput + "\n");
        try {
            return handleUserInput(userInput, false);
        } catch (DukeException exc) {
            return exc.toString();
        } catch (DateTimeParseException exc) {
            return "Sorry your input date format for the deadline task is wrong :(\n" + "Usage: yyyy-MM-dd HHmm";
        }
    }
}
