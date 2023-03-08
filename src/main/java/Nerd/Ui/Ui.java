package Nerd.Ui;

import Nerd.entities.Deadline;
import Nerd.entities.Event;
import Nerd.entities.Task;
import Nerd.entities.TaskList;

import java.time.LocalDate;

/**
 * Represents the user interface of the Chat bot.
 */
public class Ui {
    /**
     * Prints the welcome message.
     *
     * @return The welcome message to be displayed in the GUI.
     */
    public String showWelcome() {
        return ("Hello! I'm NerdBot\nI am very smart and can do many things!");
    }

    /**
     * Prints the default command list.
     *
     * @return The available command list as a string to be displayed in the GUI.
     */
    public String showCommandList() {
        String availableCommands = "Available commands:\n"
                + "todo [description]\n"
                + "event [description] /from [date] /to [date]\n"
                + "deadline [description] /by [date]\n"
                + "mark [index]\n"
                + "unmark [index]\n"
                + "list\n"
                + "delete [index]\n"
                + "date [date]\n"
                + "find [description]\n"
                + "reminder [days from today]\n"
                + "date formats are in yyyy-mm-dd";
        return availableCommands;
    }

    /**
     * Wrapper method for printing errors through this UI class.
     *
     * @param error The string representation of the error.
     * @return The welcome message to be displayed in the GUI.
     */
    public String printError(String error) {
        return error;
    }

    public String printBye() {
        return "Bye. Hope to see you again soon!";
    }

    public String printDeadlineResponse(String deadline, int size) {
        return String.format("Received, I've added the following deadlines:\n %s\nNow you have %d tasks in the list.\n" +
                "Be sure to finish these tasks before the deadline!", deadline, size);
    }

    public String printDeleteResponse(String task, int size) {
        return String.format("Finally done?!, Sure, this task has been removed:\n %s\nNow you have %d tasks in the list",
                task, size);
    }

    public String printEventResponse(String event, int size) {
        return String.format("Sure!, I've added the following events:\n %s\nNow you have %d tasks in the list.",
                event, size);
    }

    public String printSearchResponse(String result, String description) {
        String output = "Easy command for me! Here are the tasks that are associated with " + description + ":\n";
        output += result;
        return output;
    }

    /**
     * Formats the tasks of the tasklist of be printed by the GUI.
     *
     * @param list The tasklist of the current nerd bot state.
     * @return The list of tasks currently in the tasklist.
     */
    public String printListResponse(TaskList list) {
        if(list.getSize() == 0) {
            return "Current Task List is empty!";
        }
        String output = "Here is the current list:\n";
        for (int i = 0; i < list.getSize(); i++) {
            Task t = list.getTask(i);
            output += String.format("%d.%s\n", i + 1, t.toString());
        }
        return output;
    }

    public String printMarkResponse(String task) {
        return String.format("Nice, this task has been marked as done:\n %s", task);
    }

    public String printUnmarkResponse(String task) {
        return String.format("Nice, this task has been unmarked:\n %s", task);
    }

    public String printTodoResponse(String todo, int size) {
        return String.format("Sure!, I've added the following todo task:\n %s\nNow you have %d tasks in the list.",
                todo, size);
    }

    public String printSuccessfulConnection() {
        return "Successfully connected to the database!";
    }

    /**
     * Searches the current tasklist for a specific input date.
     *
     * @param dateString The string representation of the date to be searched.
     * @param list The tasklist of the current nerd bot state.
     * @return The list of tasks that associate with the date given in the tasklist.
     */
    public String printSearchDate(String dateString, TaskList list) {
        String output = "Tasks occurring on " + dateString + ":\n";
        LocalDate date = LocalDate.parse(dateString.trim());
        for (int i = 0; i < list.getSize(); i++) {
            Task currTask = list.getTask(i);
            if (currTask instanceof Deadline) {
                if (date.equals(((Deadline) currTask).getBy())) {
                    output = output + currTask.toString() + "\n";
                }
            } else if (currTask instanceof Event) {
                if (date.equals(((Event) currTask).getEndDate()) || date.equals(((Event) currTask).getStartDate())) {
                    output = output + currTask.toString() + "\n";
                }
            }
        }
        return output;
    }
}
