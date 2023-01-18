import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static DukeList ls = new DukeList();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner in = new Scanner(System.in);

        boolean isChatting = true;
        while (isChatting) {
            String chat = in.nextLine();
            if (chat.equals("bye")) {
                break;
            }
            respond(chat);
        }
        System.out.println("----------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("----------------------------------");
    }

    /**
     * Responds to the user by printing a response, based on the chat sent by user
     * @param chat
     */
    private static void respond(String chat) {
        String keyword = chat.split(" ")[0];
        switch(keyword) {
            case("list"): {
                ArrayList<Task> list = ls.getList();
                String response = "";
                for (int i = 0; i < list.size(); i++) {
                    Task task = list.get(i);
                    response += Integer.toString(i + 1) + ". ";
                    response += task.toString();
                    response += "\n";
                }
                print(response);
                break;
            }
            case("mark"): {
                Task task = ls.mark(chat.substring(5));
                String response = "Nice! I've marked this task as done:\n";
                response += task.toString();
                print(response);
                break;
            }
            case("unmark"): {
                Task task = ls.unmark(chat.substring(7));
                String response = "OK, I've marked this task as not done yet:\n";
                response += task.toString();
                print(response);
                break;
            }
            case("todo"): {
                try {
                    Task task = ls.addToDo(chat);
                    String response = "Got it. I've added this task:\n";
                    response += task.toString() + "\n";
                    response += "Now you have " + ls.getSize() + " tasks in the list.";
                    print(response);
                } catch (DukeException e) {
                    print(e.toString());
                }
                break;
            }
            case("deadline"): {
                try {
                    Task task = ls.addDeadline(chat);
                    String response = "Got it. I've added this task:\n";
                    response += task.toString() + "\n";
                    response += "Now you have " + ls.getSize() + " tasks in the list.";
                    print(response);
                } catch (DukeException e) {
                    print(e.toString());
                }
                break;
            }
            case("event"): {
                try {
                    Task task = ls.addEvent(chat);
                    String response = "Got it. I've added this task:\n";
                    response += task.toString() + "\n";
                    response += "Now you have " + ls.getSize() + " tasks in the list.";
                    print(response);
                } catch (DukeException e) {
                    print(e.toString());
                }
                break;
            }
            case("delete"): {
                Task task = ls.delete(chat.substring(7));
                String response = "Noted. I've removed this task:\n";
                response += task.toString() + "\n";
                response += "Now you have " + ls.getSize() + " tasks in the list.";
                print(response);
                break;
            }
            default:
                print("OOPS!!! I'm sorry, but I don't know what that means :-(");

        }
    }

    /**
     * Prints out a response, wrapped with 2 hyphenated lines above and below
     * @param response
     */
    private static void print(String response) {
        System.out.println("----------------------------------");
        System.out.println(response.trim());
        System.out.println("----------------------------------");
    }
}
