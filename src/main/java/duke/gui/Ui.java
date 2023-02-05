package duke.gui;


import duke.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Ui class that handles the User interface, that the user sees.
 */
public class Ui {
    public String welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n"
                + "Commands are : list,todo,mark,deadline and event";

        return logo;
    }

    public void customMessage(String message) {
        System.out.println(message);
    }
    public void customMessage(Task value) {
        System.out.println(value);
    }

    public String showMarkSucess(Task value) {
        return "Nice! I've marked this task as done: [X]" + value;
    }

    public String showNumberOfListings(int index) {
        return "Now you have " + index + " tasks in the list";
    }

    public String showCommandNotFound() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
    public String showLoadingError() {
        return "Oh no ! An error has occurred";
    }

    public void showError(int error) {
        if (error == 1) {
            System.out.println("Missing input or input is of wrong type");
        }
    }

    public String byeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    public String printList(List<Task> list) {
        String message = "";
        for (int i = 0; i < list.size(); i++) {
            int j = i + 1;
            message = message + j + "." + list.get(i) + "\n";
        }
        return message;
    }
}
