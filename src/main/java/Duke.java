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
                isChatting = false;
                break;
            }
            respond(chat);
        }
        System.out.println("----------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("----------------------------------");
    }

    private static void respond(String chat) {
        if (chat.equals("list")) {
            String response = "";
            ArrayList<DukeTask> list = ls.getList();
            for (int i = 0; i < list.size(); i++) {
                DukeTask task = list.get(i);
                response += Integer.toString(i + 1) + ". ";
                response += task.toString();
                response += "\n";
            }
            print(response);
        } else if (chat.length() > 4 && chat.substring(0, 4).equals("mark")) {
            DukeTask task = ls.mark(chat.substring(5));
            String response = "Nice! I've marked this task as done:\n";
            response += task.toString();
            print(response);
        } else if (chat.length() > 6 && chat.substring(0, 6).equals("unmark")) {
            DukeTask task = ls.unmark(chat.substring(7));
            String response = "OK, I've marked this task as not done yet:\n";
            response += task.toString();
            print(response);
        } else {
            ls.addTask(chat);
            print("added: " + chat);
        }
    }

    private static void print(String response) {
        System.out.println("----------------------------------");
        System.out.println(response);
        System.out.println("----------------------------------");
    }
}
