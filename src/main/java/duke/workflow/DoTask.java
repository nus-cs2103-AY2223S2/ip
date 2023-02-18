package duke.workflow;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import duke.io.input.ui.UserInterface;
import duke.util.Storage;
import duke.util.Task;
import duke.util.TaskList;
import duke.util.Parser;
import javafx.util.Pair;

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
    private TaskList taskListFromCommand;

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
        this.taskListFromCommand = new TaskList();
    }

    public void loadOldTasks(TaskList taskList) {
        this.taskList = taskList;
    }

    public void updateKeywordDatabase(TaskList oldTaskList) {
        for (int i = 0; i < oldTaskList.getSize(); i++) {
            Task taskToUpdate = oldTaskList.getTask(i);
            this.storage = this.storage.addToDatabase(taskToUpdate);
        }
    }

    private void parseTask(String userInput) {
        Task newTask = Parser.parseTask(userInput);
        this.taskList = this.taskList.addTask(newTask);
        this.storage = this.storage.addToDatabase(newTask);
    }

    private void findTaskOnDate(String userInput) {
        LocalDate searchDate = Parser.parseDate(userInput);
        String searchDateAsString = searchDate.toString();
        PriorityQueue<Pair<LocalDateTime, Task>> eventQueueOnDate
                = this.storage.getTaskScheduleOnDates(searchDateAsString);
        TaskList scheduleOnDate = new TaskList();
        for (Pair<LocalDateTime, Task> pair : eventQueueOnDate) {
            scheduleOnDate = scheduleOnDate.addTask(pair.getValue());
        }
        this.taskListFromCommand = scheduleOnDate;
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
            String[] userInputArray = userCommand.split(" ");
            List<String> userInputSplit = Arrays.asList(userInputArray);
            String mainCommand = userInputSplit.get(0);

            if (mainCommand.equals("MARK")) {
                int indexOfTask = Integer.valueOf(userInputSplit.get(1)) - 1;
                assert (userInputSplit.size() > 0);
                this.taskList = this.taskList.markTask(indexOfTask);
                assert (this.taskList.getTask(indexOfTask).getStatus() == true);
            } else if (mainCommand.equals("UNMARK")) {
                int indexOfTask = Integer.valueOf(userInputSplit.get(1)) - 1;
                assert (userInputSplit.size() > 0);
                this.taskList = this.taskList.unmarkTask(indexOfTask);
                assert (this.taskList.getTask(indexOfTask).getStatus() == false);
            } else if (mainCommand.equals("TODO") || mainCommand.equals("DEADLINE")
                    || mainCommand.equals("EVENT")) {
                parseTask(userCommand);
            } else if (mainCommand.equals("DELETE")) {
                int indexOfTask = Integer.valueOf(userInputSplit.get(1)) - 1;
                this.removedTask = this.taskList.getTask(indexOfTask);
                this.taskList = this.taskList.removeTask(indexOfTask);
                this.storage = this.storage.removeFromKeywordStorage(this.removedTask);
            } else if (mainCommand.equals("FIND")) {
                String[] keywordToFind = userCommand.split("FIND ");
                List<String> keywords = Arrays.asList(keywordToFind);
                TaskList findList = this.storage.getTaskWithKeywords(keywords.get(1));
                this.taskListFromCommand = findList;
            } else if (mainCommand.equals("SCHEDULE")) {
                findTaskOnDate(userCommand);
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
                return UserInterface.printSearchedTask(this.taskListFromCommand);
            } else if (mainCommand.equals("SCHEDULE")) {
                return UserInterface.printSearchedTask(this.taskListFromCommand);
            } else {
                int numberOfTasks = this.taskList.getSize();
                return UserInterface.printAddedTask(this.userCommand, numberOfTasks);
            }
        }
    }
}

