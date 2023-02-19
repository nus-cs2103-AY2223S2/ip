package seedu.duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the UI portion of application
 * Responsible for processing inputs and performing functions based on user inputs
 */
public class Ui {

    public static void showError(Exception e) {
        System.out.println(e);
    }

    /**
     * Responds to the user by printing a response, based on the chat sent by user
     * @param chat User input
     * @param ls The TaskList in Duke
     */
    public static String respond(String chat, TaskList ls) {
        String keyword = chat.split(" ")[0];
        switch(keyword) {
        case("list"): {
            String response = ls.toString();
            return response;
        }
        case("mark"): {
            Task task = ls.mark(chat.substring(5));
            String response = "Nice! I've marked this task as done:\n";
            response += task.toString();
            return response;
        }
        case("unmark"): {
            Task task = ls.unmark(chat.substring(7));
            String response = "OK, I've marked this task as not done yet:\n";
            response += task.toString();
            return response;
        }
        case("todo"): {
            try {
                Task task = ls.addToDo(chat);
                assert task != null : "Todo should not be null";
                String response = "Got it. I've added this task:\n";
                response += task.toString() + "\n";
                response += "Now you have " + ls.getSize() + " tasks in the list.";
                return response;
            } catch (DukeException e) {
                return e.toString();
            }
        }
        case("deadline"): {
            try {
                Task task = ls.addDeadline(chat);
                assert task != null : "Deadline should not be null";
                String response = "Got it. I've added this task:\n";
                response += task.toString() + "\n";
                response += "Now you have " + ls.getSize() + " tasks in the list.";
                return response;
            } catch (DukeException e) {
                return e.toString();
            }
        }
        case("event"): {
            try {
                Task task = ls.addEvent(chat);
                assert task != null : "Event should not be null";
                String response = "Got it. I've added this task:\n";
                response += task.toString() + "\n";
                response += "Now you have " + ls.getSize() + " tasks in the list.";
                return response;
            } catch (DukeException e) {
                return e.toString();
            }
        }
        case("delete"): {
            Task task = ls.delete(chat.substring(7));
            String response = "Noted. I've removed this task:\n";
            response += task.toString() + "\n";
            response += "Now you have " + ls.getSize() + " tasks in the list.";
            return response;
        }
        case("bye"): {
            try {
                Storage.saveList(ls);
            } catch (IOException e) {
                System.out.println("Error initializing stream");
            }
            return "Bye!";
        }
        case("find"): {
            String search = chat.split(" ")[1];
            assert search != null : "Searching keyword cannot be null";
            ArrayList<Task> list = new ArrayList<>();
            for (Task task : ls.getList()) {
                if (task.contains(search)) {
                    list.add(task);
                }
            }
            TaskList filteredList = new TaskList(list);
            String response = "Here are the matching tasks in your list:\n";
            response += filteredList.toString();
            return response;
        }
        case("tag"): {
            int index = Integer.valueOf(chat.split(" ")[1]) - 1;
            Tag tag = new Tag(chat.split(" ")[2]);
            Task task = ls.addTag(index, tag);
            String response = "Noted. I've added the tag to this task:\n";
            response += task.toString() + "\n";
            return response;
        }
        default:
            return "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }
}
