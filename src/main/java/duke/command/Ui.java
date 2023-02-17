package duke.command;

import java.io.IOException;
import java.util.Scanner;

import duke.exception.EmptyTaskListException;
import duke.exception.IndexNotNumberException;
import duke.exception.InvalidDeadlineDateException;
import duke.exception.InvalidEventDateTimeException;
import duke.exception.InvalidIndexException;
import duke.exception.MissingContentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Deals with interaction with users
 */
public class Ui {
    public Ui() {}

    /**
     * Shows logo and welcome.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\nHello, I'm Kyle");
        System.out.println("What can I do for you?");
    }

    /**
     * Let Duke say something
     * @param message given message that Duke needs to say
     */
    public void says(String message) {
        System.out.println(message);
    }

    /**
     * Let Duke (or Kyle) says what he should say when deleting the whole task list
     * @return the message
     */
    public static String saysDeleteAllMessage() {
        return "YAY! Kyle clears your task list! It's now empty! "
                + "Boss, hurry, add more jobs for the Minions to do!";
    }

    /**
     * Prints out unknown error message if command is invalid.
     * @return unknown message.
     */
    public String showUnknownError() {
        return ("WOOF!!! Kyle is so sorry boss, "
                + "but I don't know what that means :-(");
    }

    /**
     * Prints out goodbye message.
     * @return goodbye message
     */
    public String bye() {
        Parser.updateLastCommand("bye");
        return ("WOOF WOOF WOOF! Kyle is sad to see you leave!");
    }

    /**
     * Returns what Duke will respond to an Undo command
     * @param commandType type of command (valid)
     * @param commandDetail detail of task
     * @return Duke's response
     */
    public String saysUnDo(String commandType, String commandDetail) {
        return ("WOOF! Got it! Kyle will undo the last command!" + "\n"
                + "The following task has been un-" + commandType
                + "-ed: " + "\n" + commandDetail);
    }

    /**
     * Returns what Duke will respond to an Undo command without command detail
     * @param commandType last command type
     * @return Duke's message
     */
    public String saysUnDo(String commandType) {
        return ("WOOF! Got it! Kyle will undo the last command!" + "\n"
                + "The task(s) has been un-" + commandType
                + "-ed!");
    }

    /**
     * Returns Duke's response to an unable-to-be-undone command
     * @return Duke's response
     */
    public String saysUnableToUndo() {
        return "WOOF!! Kyle thinks the last command cannot be undone!";
    }

    /**
     * Returns Duke's message to add command
     * @param task task added
     * @param numberOfTasks number of tasks after added
     * @return Duke's message
     */
    public static String saysAddCommand(String task, int numberOfTasks) {
        return "Got it. Kyle's added this EVIL task:" + "\n" + task
                + "\n"
                + String.format("Now Boss has %d "
                + "tasks in the EVIL list", numberOfTasks);
    }

    /**
     * Returns Duke's message to delete command
     * @param task task deleted
     * @param numberOfRemainingTasks number of tasks after deletion
     * @return Duke's message
     */
    public static String saysDeleteCommand(String task, int numberOfRemainingTasks) {
        return "Noted. Kyle's removed this EVIL task:" + "\n" + task + "\n"
                + String.format("Now Boss has %d "
                + "tasks in the EVIL list", numberOfRemainingTasks);
    }


    /**
     * Returns Duke's respond to find command from user
     * Update the most recent command correspondingly
     * @param arr input array.
     * @return appropriate message
     */
    public String findWord(TaskList taskList, String[] arr, boolean containsKeyword) {
        if (arr.length >= 1) {
            if (containsKeyword) {
                Parser.updateLastCommand("find");
                return (taskList.findWord(arr[1]));
            } else {
                return ("Sorry boss! Kyle cannot find any tasks recorded!");
            }
        }
        return (new MissingContentException()).getMessage();
    }

    /**
     * Returns message for list command from user
     * @param taskList original task list
     * @return appropriate message
     * @throws EmptyTaskListException if task list is currently empty
     */
    public String list(TaskList taskList) throws EmptyTaskListException {
        if (taskList.isEmpty()) {
            throw new EmptyTaskListException();
        }
        Parser.updateLastCommand("list");
        return (taskList.list());
    }

    /**
     * Returns message to undo command
     * @param tasklist original tasklist
     * @return Duke's respond
     */
    public String undo(TaskList tasklist) {
        return Parser.undo(tasklist);
    }

    /**
     * Gets input/command from users
     * @return array consists of user's command line
     */
    public String[] getInput() {
        Scanner sc = new Scanner(System.in);
        String newLine = sc.nextLine();
        assert newLine != "" : "Empty input from user";
        String[] arr = newLine.split(" ");
        return arr;
    }

    /**
     * Gets input/command from users
     * @param s input string from user command
     * @return array consists of user's command line
     */
    public String[] getInput(String s) {
        String[] arr = s.split(" ");
        return arr;
    }

    /**
     * Marks task at given index
     * Updates the most recent command correspondingly
     * @param listOfAction original tasklist
     * @param commands array of user command line
     * @return new updated task list
     */
    public String mark(TaskList listOfAction, String[] commands) {
        String reply = "";
        try {
            int index = Parser.getTaskIndex(listOfAction, commands);
            reply = (listOfAction.mark(index - 1));
            Parser.updateLastCommand(String.format("mark %d", index - 1));
            return reply;
        } catch (MissingContentException | InvalidIndexException | IOException | IndexNotNumberException e) {
            return (e.getMessage());
        }
    }


    /**
     * Unmark task at given index
     * Updates the most recent command correspondingly
     * @param listOfAction original task list
     * @param commands array of user command line
     * @return new updated task list
     */
    public String unmark(TaskList listOfAction, String[] commands) {
        String reply = "";
        try {
            int index = Parser.getTaskIndex(listOfAction, commands) - 1;
            reply = (listOfAction.unmark(index));
            Parser.updateLastCommand(String.format("unmark %d", index - 1));
            return reply;
        } catch (MissingContentException | InvalidIndexException | IOException | IndexNotNumberException e) {
            return (e.getMessage());
        }
    }

    /**
     * Deletes task at specific index
     * Updates the most recent command correspondingly
     * @param listOfAction original task list
     * @param command user command
     * @return new updated task list
     */
    public String delete(TaskList listOfAction, String[] command) {
        String reply = "";
        try {
            int index = Parser.getTaskIndex(listOfAction, command) - 1;
            reply = (listOfAction.delete(index));
            Parser.updateLastCommand(String.format("delete %d", index - 1));
            return reply;
        } catch (IOException e) {
            return (new InvalidIndexException().getMessage());
        } catch (MissingContentException | InvalidIndexException e) {
            return (e.getMessage());
        } catch (IndexNotNumberException e) {
            return Parser.deleteAll(listOfAction, command);
        }
    }

    /**
     * Adds todo task to task list
     * Updates the most recent command correspondingly
     * @param listOfAction original task list
     * @param command command from user input
     * @return new updated task list
     */
    public String addToDo(TaskList listOfAction, String[] command) {
        String remaining = "";
        try {
            remaining = Parser.parseToDo(command);
        } catch (MissingContentException e) {
            return e.getMessage();
        }
        if (remaining.equals("")) {
            return (new MissingContentException().getMessage());
        }
        Todo newTask = new Todo(command[0], remaining, false);
        Parser.updateLastCommand("todo");
        return (listOfAction.add(newTask));
    }

    /**
     * Adds new deadline to task list
     * Updates the most recent command correspondingly
     * @param listOfAction original task list
     * @param command user input
     * @return new updated task list
     */
    public String addDeadline(TaskList listOfAction, String[] command) {
        try {
            String detail = new Parser().getDeadlineDetail(command);
            String remaining = new Parser().getDeadlineFull(command);
            Parser.updateLastCommand("deadline");
            Deadline newTaskDeadline = new Deadline(command[0], detail, remaining);
            return (listOfAction.add(newTaskDeadline));
        } catch (MissingContentException | InvalidDeadlineDateException e) {
            return (e.getMessage());
        }
    }

    /**
     * Adds new event to task list
     * Updates the most recent command correspondingly
     * @param listOfAction original tasklist
     * @param command from user's input
     * @return Duke's response to user's input
     */
    public String addEvent(TaskList listOfAction, String[] command) throws InvalidEventDateTimeException {
        try {
            Event newEvent = Parser.getEventFull(command);
            Parser.updateLastCommand("event");
            return (listOfAction.add(newEvent));
        } catch (MissingContentException | InvalidEventDateTimeException e) {
            return (e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidEventDateTimeException();
        }
    }
}
