package seedu.duke;

import java.util.ArrayList;

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
            return "Bye!";
        }
        case("find"): {
            String search = chat.split(" ")[1];
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
            int index = Integer.valueOf(chat.split(" ")[1]);
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
