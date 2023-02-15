package duke.gui;


import duke.task.Task;

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
                + "Commands are: list,todo,mark,deadline,event & archive";

        return logo;
    }

    public String showMarkSucess(Task value) {
        return "Nice! I've marked this task as done: " + value;
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

    public String addCommandsFormat(String command) {
        if(command.equals("todo")) {
            return "\nAn example:\ntodo CS2103 Assignment";
        } else if (command.equals("deadline")) {
            return "\nAn example:\ndeadline CS2103 Assignment /by 01/03/2023:1800";
        } else if (command.equals("event") || command.contains("event") ) {
            return "\nAn example:\nevent CS2103 Exam /from 01/03/2023:1800" +
                    " /to 01/03/2023:2000";
        }
        return null;
    }

    public String wrongInput() {
        return "Missing input or input is of wrong type";
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
