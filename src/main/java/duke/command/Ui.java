package duke.command;

import java.io.IOException;
import java.util.Scanner;

import duke.exception.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Deals with interaction with users
 */
public class Ui {
    public Ui() {
    }

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
        System.out.println("\nHello, I'm Duke");
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
     * Prints out error message.
     * @return error message
     */
    public String showError(String message) {
        return(message);
    }

    /**
     * Prints out loading error message.
     * @return file not existed error message
     */
    public String showLoadingError() {
        return("File not existed");
    }

    /**
     * Prints out unknown error message.
     * @return unknown message.
     */
    public String showUnknownError() {
        return("WOOF!!! I'm sorry boss, "
                + "but I don't know what that means :-(");
    }

    /**
     * Prints out goodbye message.
     * @return goodbye message
     */
    public String bye() {
        return("WOOF WOOF WOOF! Kyle is sad to see you leave!");
    }

    /**
     * Prints out line for separation.
     */
    public void showLine() {
        System.out.println("_____________________________");
    }


    /**
     * Prints out if input array is not empty
     *
     * @param arr input array.
     * @print instruction.
     * @return appropriate message
     * @throw MissingContentException if input array is empty.
     */
    public String findWordIntro(TaskList taskList,String[] arr, boolean containsKeyword) {
        if (arr.length >= 1) {
            if (containsKeyword) {
                //return("Here are the matching tasks in your list:");
                return(taskList.findWord(arr[1]));
            } else {
                return("Sorry boss! No task found!");
            }
        }
        return(new DukeException("WOOF WOOF! The content can not be left empty!").getMessage());
    }

    /**
     * Returns message for listing out task list
     * @param taskList original task list
     * @return appropriate message
     */
    public String list(TaskList taskList) {
        if (taskList.isEmpty()) {
            return("WOOF! You do not have any tasks in your task list!");
        }
        return(taskList.list());
    }

    /**
     * Gets input/command from users
     * @return array consists of user's command line
     */
    public String[] getInput() {
        Scanner sc = new Scanner(System.in);
        String newLine = sc.nextLine();
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
     * @param listOfAction original tasklist
     * @param commands array of user command line
     * @return new updated task list
     */
    public String mark(TaskList listOfAction, String[] commands) {
        try {
            int index = Parser.getTaskIndex(listOfAction, commands);
            return(listOfAction.mark(index - 1));
        } catch (MissingContentException | InvalidIndexException | IOException e) {
            return(e.getMessage());
        }
        //return listOfAction;
    }


    /**
     * Unmarks task at given index
     * @param listOfAction original tasklist
     * @param commands array of user command line
     * @return new updated task list
     */
    public String unmark(TaskList listOfAction, String[] commands) {
        try {
            int index = Parser.getTaskIndex(listOfAction, commands) - 1;
            return(listOfAction.unmark(index));
        } catch (MissingContentException | InvalidIndexException | IOException e) {
            return(e.getMessage());
        }
    }

    /**
     * Deletes task at specific index
     * @param listOfAction original task list
     * @param command user command
     * @return new updated task list
     */
    public String delete(TaskList listOfAction, String[] command) {
        try {
            int index = Parser.getTaskIndex(listOfAction, command) - 1;
            try {
                return(listOfAction.delete(index));
               // System.out.println(String.format("Now you have %d "
                 //       + "tasks in the list", listOfAction.validLen()));
            } catch (IOException e) {
                return(new InvalidIndexException().getMessage());
            }
        } catch (MissingContentException | InvalidIndexException e) {
            return(e.getMessage());
        }
        //return listOfAction;
    }

    /**
     * Adds todo task to task list
     * @param listOfAction original task list
     * @param command command from user input
     * @return new updated task list
     */
    public String toDo(TaskList listOfAction, String[] command) {
        String remaining = Parser.toDo(command);
        if (remaining.equals("")) {
            return(new MissingContentException().getMessage());
            //return listOfAction;
        }
        Todo newTask = new Todo(command[0], remaining, false);
        return(listOfAction.add(newTask));
       // return listOfAction;
    }

    /**
     * Adds new deadline to task list
     * @param listOfAction original task list
     * @param command user input
     * @return new updated task list
     */
    public String addDeadline(TaskList listOfAction, String[] command) {
        try {
            //System.out.println("Got it. I've added this task:");
            String detail = new Parser().deadlineDetail(command);
            String remaining = new Parser().getDeadlineFull(command);
            Deadline newTaskDeadline = new Deadline(command[0], detail, remaining);
            return(listOfAction.add(newTaskDeadline));
            //System.out.println(String.format("Now you have %d "
              //      + "tasks in the list", listOfAction.validLen()));
        } catch (MissingContentException | InvalidDeadlineDateException e) {
            return(e.getMessage());
        }
       // return listOfAction;
    }

    public String addEvent(TaskList listOfAction, String[] command) {
        try {
            try {
                int startIndex = new Parser()
                        .getEventStartTimeIndex(command);
                int endIndex = new Parser()
                        .getEventEndTimeIndex(command, startIndex);
                String detail = new Parser()
                        .getEventDetail(command);
                String start = (new Parser()
                        .getEventTime(command, startIndex, endIndex))[0];
                String end = (new Parser()
                        .getEventTime(command, startIndex, endIndex))[1];
                System.out.println("Got it. I've added this task:");
                return(listOfAction.add(new Event("event",
                        detail, start, end)));
            } catch (MissingContentException | InvalidEventDateTimeException e) {
                return(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidEventDateTimeException();
            }
        } catch (InvalidEventDateTimeException e) {
            return(e.getMessage());
        }
       // return listOfAction;
    }
}