package duke;

import duke.DukeException;

import java.io.FileNotFoundException;

public class Parser {

    public void parse(String userInput, Tasklist tasklist, Ui ui, Storage storage) throws DukeException, FileNotFoundException {
        if (userInput.equals("list")) {
            tasklist.printList();
        } else if (userInput.contains("mark") && userInput.substring(0,4).equals("mark")) {
            int position = Integer.valueOf(userInput.substring(5));
            tasklist.updateTask("mark", position - 1);
        } else if (userInput.contains("unmark") && userInput.substring(0,6).equals("unmark")) {
            int position = Integer.valueOf(userInput.substring(7));
            tasklist.updateTask("unmark", position - 1);
        } else if (userInput.contains("delete") && userInput.substring(0,6).equals("delete")) {
            int position = Integer.valueOf(userInput.substring(7));
            tasklist.updateTask("delete",position - 1);
        } else if (userInput.contains("todo") && userInput.substring(0,4).equals("todo")) {
            if (userInput.substring(5).equals("")){
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            } else {
                tasklist.addingActivities("todo", userInput);
            }
        } else if (userInput.contains("deadline") && userInput.substring(0,8).equals("deadline")) {
            if (userInput.substring(9).equals("")){
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            } else {
                tasklist.addingActivities("deadline", userInput);
            }
        } else if (userInput.contains("event") && userInput.substring(0,5).equals("event")) {
            if (userInput.substring(6).equals("")){
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            } else {
                tasklist.addingActivities("event", userInput);
            }
        } else if (userInput.equals("bye")) {
                ui.bye();
                storage.saveToFile(tasklist);
                storage.storageClose();
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
