package duke.workflow;

import java.util.Arrays;
import java.util.List;

import duke.io.input.ui.UserInterface;
import duke.util.Storage;
import duke.util.Task;
import duke.util.TaskList;
import duke.util.Parser;

/**
 * A more specific implementation of {@code Event}.
 *
 * Part of the workflow where the chatbot determines the next
 * possible action based on the user's input
 */

public class DoTask extends Event {
    private boolean justOpenedDuke;
    private String userCommand;
    private TaskList taskList;
    private Task removedTask;
    private Storage storage;
    private TaskList foundListFromKeyword;

    /**
     * Constructs the {@code DoTask} as the user interacts
     * with Duke.
     */
    public DoTask() {
        super(false);
        this.justOpenedDuke = true;
        this.userCommand = "LIST";
        this.taskList = new TaskList();
        this.storage = new Storage();
        this.foundListFromKeyword = new TaskList();
    }

    public void loadOldTasks(TaskList taskList) {
        this.taskList = taskList;
    }

    public void updateKeywordDatabase(TaskList oldTaskList) {
        for (int i = 0; i < oldTaskList.getSize(); i++) {
            String toUpdateKeywordDatabase = "";
            Task taskToUpdate = oldTaskList.getTask(i);
            if (taskToUpdate.getNature().equals("T")) {
                toUpdateKeywordDatabase = "TODO " + taskToUpdate.getAction();
            } else if (taskToUpdate.getNature().equals("D")) {
                toUpdateKeywordDatabase = "DEADLINE " + taskToUpdate.getAction()
                        + " " + taskToUpdate.getTimeInfo();
            } else {
                toUpdateKeywordDatabase = "EVENT " + taskToUpdate.getAction()
                        + " " + taskToUpdate.getTimeInfo();
            }
            this.storage = this.storage.addToKeywordStorage(
                    toUpdateKeywordDatabase, taskToUpdate);
        }
    }

    private void parseTask(String userInput) {
        Task newTask = Parser.parseTask(userInput);
        this.taskList = this.taskList.addTask(newTask);
        this.storage = this.storage.addToKeywordStorage(userInput, newTask);
    }

    /**
     * Determines the next possible action after the user has entered an input.
     *
     * @return a new event that follows from the last user input
     */

    public Event toNextEvent(String userCommand) {
        this.justOpenedDuke = false;
        if (userCommand.equals("BYE")) {
            this.userCommand = userCommand;
            return new Ending(this.taskList);
        } else if (userCommand.equals("LIST")) {
            this.userCommand = userCommand;
            return this;
        } else if (Parser.checkInputValidity(userCommand, this.taskList.getSize())) {
            this.userCommand = userCommand;
            String[] userInput = userCommand.split(" ");
            List<String> userInputSplit = Arrays.asList(userInput);
            String mainCommand = userInputSplit.get(0);
            int indexOfTask = Integer.valueOf(userInputSplit.get(1)) - 1;

            if (mainCommand.equals("MARK")) {
                assert (userInputSplit.size() > 0);
                this.taskList = this.taskList.markTask(indexOfTask);
                assert (this.taskList.getTask(indexOfTask).getStatus() == true);
            } else if (mainCommand.equals("UNMARK")) {
                assert (userInputSplit.size() > 0);
                this.taskList = this.taskList.unmarkTask(indexOfTask);
                assert (this.taskList.getTask(indexOfTask).getStatus() == false);
            } else if (mainCommand.equals("TODO") || mainCommand.equals("DEADLINE")
                    || mainCommand.equals("EVENT")) {
                parseTask(userCommand);
            } else if (mainCommand.equals("DELETE")) {
                this.removedTask = this.taskList.getTask(indexOfTask);
                this.taskList = this.taskList.removeTask(indexOfTask);
                this.storage = this.storage.removeFromKeywordStorage(this.removedTask);
            } else if (mainCommand.equals("FIND")) {
                String[] keywordToFind = userCommand.split("FIND ");
                List<String> keywords = Arrays.asList(keywordToFind);
                TaskList findList = this.storage.getTaskList(keywords.get(1));
                this.foundListFromKeyword = findList;
            }
        }
        return this;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    @Override
    public String toString() {
        if (justOpenedDuke) {
            boolean firstTimeUsingDuke = this.taskList.getSize() == 0;
            return UserInterface.welcomeUser(firstTimeUsingDuke);
        } else if (userCommand.equals("LIST")) {
            return this.taskList.toString();
        } else {
            String[] commands = userCommand.split(" ");
            List<String> commandWords = Arrays.asList(commands);
            String mainCommand = commandWords.get(0);
            if (mainCommand.equals("MARK") || mainCommand.equals("UNMARK")) {
                int index = Integer.valueOf(commandWords.get(1)) - 1;
                Task taskToPrint = this.taskList.getTask(index);
                return UserInterface.printMarkedUnmarkedTask(mainCommand, taskToPrint);
            } else if (mainCommand.equals("DELETE")) {
                int numberOfTasksRemaining = this.taskList.getSize();
                return UserInterface.printDeletedTask(numberOfTasksRemaining, this.removedTask);
            } else if (mainCommand.equals("FIND")) {
                return UserInterface.printSearchedTaskFromKeyword(this.foundListFromKeyword);
            } else {
                int numberOfTasks = this.taskList.getSize();
                return UserInterface.printAddedTask(this.userCommand, numberOfTasks);
            }
        }
    }
}

