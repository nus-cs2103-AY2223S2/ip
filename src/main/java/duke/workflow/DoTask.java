package duke.workflow;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import duke.io.input.exception.DukeException;
import duke.io.input.exception.UserInputException;
import duke.util.Storage;
import duke.util.Task;
import duke.util.TaskList;
import duke.util.Parser;

import duke.util.service.Deadline;
import duke.util.service.ScheduledEvent;
import duke.util.service.ToDo;


/**
 * A more specific implementation of {@code Event}.
 *
 * Part of the workflow where the chatbot determines the next
 * possible action based on the user's input
 */

public class DoTask extends Event {
    private boolean firstTimeUsing;
    private String lastCommand;
    private TaskList taskList;
    private Task removedTask;
    private Storage storage;
    private TaskList foundList;

    /**
     * Constructs the {@code DoTask} as the user interacts
     * with Duke.
     */
    public DoTask() {
        super(false);
        this.firstTimeUsing = true;
        this.lastCommand = "";
        this.taskList = new TaskList();
        this.storage = new Storage();
        this.foundList = new TaskList();
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
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

    public Event toNextEvent(String nextTask) {
        this.firstTimeUsing = false;
        this.lastCommand = nextTask;
        if (nextTask.equals("BYE")) {
            return new Ending(this.taskList);
        } else if (nextTask.equals("LIST")) {
            return this;
        } else {
            try {
                UserInputException.checkUserInput(nextTask, this.taskList.getSize());

                String[] userInput = nextTask.split(" ");
                List<String> userInputSplit = Arrays.asList(userInput);
                String mainCommand = userInputSplit.get(0);

                if (mainCommand.equals("MARK")) {
                    assert (userInputSplit.size() > 0);
                    int indexOfTask = Integer.valueOf(userInputSplit.get(1)) - 1;
                    this.taskList = this.taskList.markDone(indexOfTask);
                    assert (this.taskList.getTask(indexOfTask).getStatus() == true);
                } else if (mainCommand.equals("UNMARK")) {
                    assert (userInputSplit.size() > 0);
                    int indexOfTask = Integer.valueOf(userInputSplit.get(1)) - 1;
                    this.taskList = this.taskList.unMark(indexOfTask);
                    assert (this.taskList.getTask(indexOfTask).getStatus() == false);
                } else if (mainCommand.equals("TODO") || mainCommand.equals("DEADLINE")
                        || mainCommand.equals("EVENT")) {
                    parseTask(nextTask);
                } else if (mainCommand.equals("DELETE")) {
                    int indexOfTask = Integer.valueOf(userInputSplit.get(1)) - 1;
                    this.removedTask = this.taskList.getTask(indexOfTask);
                    this.taskList = this.taskList.removeTask(indexOfTask);
                    this.storage = this.storage.removeFromKeywordStorage(this.removedTask);
                } else if (mainCommand.equals("FIND")) {
                    String[] keywordToFind = nextTask.split("FIND ");
                    List<String> keywords = Arrays.asList(keywordToFind);
                    TaskList findList = this.storage.getTaskList(keywords.get(1));
                    this.foundList = findList;
                }
            } catch (DukeException exception) {
                System.out.println(exception);
            } catch (Exception exception) {
                System.out.println("ERRRR ERROR ERRR. SYSTEM FAILURE. UNKNOWN EXCEPTION. ERR ERR");
            }
        }
        return this;
    }


    public TaskList getTaskList() {
        return this.taskList;
    }

    public String printMarkedItem(int index) {
        String toPrintOut = "";
        toPrintOut += "MARKED. ONE STEP CLOSER..." + '\n';
        toPrintOut += this.taskList.getTask(index).toString() + '\n';
        return toPrintOut;
    }

    @Override
    public String toString() {
        String toPrintOut = "";
        if (this.firstTimeUsing) {
            if (this.taskList.getSize() == 0) {
            toPrintOut += "INTERESTING. VERY INTERESTING. WHAT'S YOUR PLANS?";
            } else {
                toPrintOut += "WELCOME BACK... WHAT'S YOUR PLAN TODAY...";
            }

        } else if (lastCommand.equals("LIST")) {
            toPrintOut += this.taskList.toString();
        } else {
            String[] command = lastCommand.split(" ");
            List<String> commandWords = Arrays.asList(command);

            if (commandWords.get(0).equals("MARK")) {
                int index = Integer.valueOf(commandWords.get(1)) - 1;
                toPrintOut += printMarkedItem(index);
            } else if (commandWords.get(0).equals("UNMARK")) {
                toPrintOut += "HAVING OTHER PLANS I SEE..." + '\n';
                toPrintOut += this.taskList.getTask(Integer.valueOf(commandWords.get(1)) - 1).toString() + '\n';
            } else if (commandWords.get(0).equals("DELETE")) {
                toPrintOut += "KABOOM. GONE. REDUCED TO ATOMS. HOW EXCITING!" + '\n';
                toPrintOut += '\n' + "TASK " + this.removedTask.toString() + " NO LONGER EXISTED" + '\n';
                int numberOfTasks = this.taskList.getSize();
                if (numberOfTasks > 1) {
                    toPrintOut += numberOfTasks + " TASKS LEFT. BETTER HURRY." + '\n';
                } else {
                    toPrintOut += "ONLY " + numberOfTasks + " TASK LEFT. BORING DAYS AHEAD." + '\n';
                }
            } else if (commandWords.get(0).equals("FIND")) {
                toPrintOut += "YOU'RE STARTING TO FORGET..." + '\n';
                if (this.foundList.getSize() == 0) {
                    toPrintOut += "THIS WAS NEVER IN YOUR PLANS";
                } else {
                    toPrintOut += '\n' + "LET ME REMIND YOU OF WHAT YOU STARTED" + '\n';
                    for (int i = 0; i < this.foundList.getSize(); i++) {
                        toPrintOut += this.foundList.getTask(i).toString() + '\n';
                    }
                }
            } else {
                toPrintOut += "SO YOU WANT TO ADD " + '"' + this.lastCommand + '"' + ". VERY WELL..." + '\n';
                toPrintOut += '\n' + "ADDED: " + lastCommand + '\n';
                int numberOfTasks = this.taskList.getSize();
                if (numberOfTasks > 1) {
                    toPrintOut += numberOfTasks + " TASKS. BETTER HURRY." + '\n';
                } else {
                    toPrintOut += "ONLY " + numberOfTasks + " TASK. BORING DAYS AHEAD." + '\n';
                }
                toPrintOut += '\n' + "WHAT ELSE?";
            }
        }
        return toPrintOut;
    }
}

