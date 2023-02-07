package duke;

import duke.DukeException;
import duke.Storage;
import duke.Tasklist;
import duke.Ui;

import java.io.FileNotFoundException;

public class Parser {

    /**
     * Processes the user input and helps to run the user's commands.
     *
     * @param userInput instructions given by the user.
     * @param tasklist  the existing tasklist that has been created.
     * @param ui        user interface to handle interactions with user.
     * @param storage   current file to store the tasklist to.
     * @throws DukeException         if user command is not understood or incorrect.
     * @throws FileNotFoundException if file to store the tasklist do not exist.
     */
    public String parse(String userInput, Tasklist tasklist, Ui ui, Storage storage) throws DukeException,
            FileNotFoundException {
        if (userInput.equals("list")) {
            return tasklist.printList();
        } else if (userInput.contains("mark") && userInput.substring(0, 4).equals("mark")) {
            int position = Integer.valueOf(userInput.substring(5));
            return tasklist.updateTask("mark", position - 1);
        } else if (userInput.contains("unmark") && userInput.substring(0, 6).equals("unmark")) {
            int position = Integer.valueOf(userInput.substring(7));
            return tasklist.updateTask("unmark", position - 1);
        } else if (userInput.contains("delete") && userInput.substring(0, 6).equals("delete")) {
            int position = Integer.valueOf(userInput.substring(7));
            return tasklist.updateTask("delete", position - 1);
        } else if (userInput.contains("todo") && userInput.substring(0, 4).equals("todo")) {
            if (userInput.substring(5).equals("")) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            } else {
                return tasklist.addingActivities("todo", userInput);
            }
        } else if (userInput.contains("deadline") && userInput.substring(0, 8).equals("deadline")) {
            if (userInput.substring(9).equals("")) {
                return "☹ OOPS!!! The description of a deadline cannot be empty.";
//                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            } else {
                return tasklist.addingActivities("deadline", userInput);
            }
        } else if (userInput.contains("event") && userInput.substring(0, 5).equals("event")) {
            if (userInput.substring(6).equals("")) {
                return "☹ OOPS!!! The description of a event cannot be empty.";
//                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            } else {
                return tasklist.addingActivities("event", userInput);
            }
        } else if (userInput.contains("find") && userInput.substring(0, 4).equals("find")) {
            if (userInput.substring(5).equals("")) {
                return "☹ OOPS!!! The description of find cannot be empty.";
//                throw new DukeException("☹ OOPS!!! The description of find cannot be empty.");
            } else {
                return tasklist.findingActivities(userInput.substring(5));
            }
        } else if (userInput.equals("bye")) {
            storage.saveToFile(tasklist);
            storage.storageClose();
            return ui.bye();
        } else {
            return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
//            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
