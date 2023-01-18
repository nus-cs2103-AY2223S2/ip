import java.io.IOException;
import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static Task[] storage = new Task[100];
    public static int pointer = -1;
    public static String pad(String val) {
        return "---------------------------\n" + val + "\n---------------------------";
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hello! I'm Duke. How may I be of assistance?");
        String str = sc.nextLine();
        while (true){
            String[] cmd_details = str.split(" ", 2);
            String cmd = cmd_details[0];
            String details = "";
            if (str.equals("bye")) {
                // exit
                break;
            }
            if (str.equals("list")) {
                // list tasks

                System.out.println("---------------------------");
                if (pointer == -1) {
                    System.out.println("No tasks added yet.");
                } else {
                    for (int i = 0; i < pointer + 1; i++) {
                        System.out.println(Integer.toString(i + 1) + ". " + storage[i].toString());
                    }
                }
                System.out.println("---------------------------");
                str = sc.nextLine();
                continue;
            }
            int len = cmd_details.length;
            if (len > 1) {
                details = cmd_details[1];
            }
            try {
                if (cmd.equals("mark")) {
                    if (pointer == -1 || len == 1) {
                        throw new DukeException("Unable to mark.");
                    }
                    // mark task as done
                    Task curr = storage[Integer.valueOf(details) - 1];
                    System.out.println(pad(curr.outputMarked() + curr.toString()));
                } else if (cmd.equals("unmark")) {
                    if (pointer == -1 || len == 1) {
                        throw new DukeException("Unable to unmark.");
                    }
                    // mark task as undone
                    Task curr = storage[Integer.valueOf(details) - 1];
                    System.out.println(pad(curr.outputUnmarked() + curr.toString()));
                } else if (cmd.equals("todo")) {
                    if (len == 1) {
                        throw new DukeException("Description of todo cannot be empty.");
                    }
                    pointer++;
                    Todo addition = new Todo(details);
                    storage[pointer] = addition;
                    System.out.println(pad("Understood. I have added the task:\n" + addition.toString()
                            + "\nThere are now " + Integer.toString(pointer + 1) + " task(s) in the list."));
                } else if (cmd.equals("deadline")) {
                    String[] temp = details.split(" /by ");
                    if (len == 1 || temp.length != 2) {
                        throw new DukeException("Description of deadline cannot be incomplete.");
                    }
                    pointer++;
                    Deadline addition = new Deadline(temp[0], temp[1]);
                    storage[pointer] = addition;
                    System.out.println(pad("Understood. I have added the task:\n" + addition.toString()
                            + "\nThere are now " + Integer.toString(pointer + 1) + " task(s) in the list."));

                } else if (cmd.equals("event")) {
                    String[] temp = details.split(" /from ");
                    if (len == 1 || temp.length != 2) {
                        throw new DukeException("Description of event cannot be incomplete.");
                    }
                    String[] temp2 = temp[1].split(" /to ");
                    if (temp2.length != 2) {
                        throw new DukeException("Description of event cannot be incomplete.");
                    }
                    pointer++;
                    Event addition = new Event(temp[0], temp2[0], temp2[1]);
                    storage[pointer] = addition;
                    System.out.println(pad("Understood. I have added the task:\n" + addition.toString()
                            + "\nThere are now " + Integer.toString(pointer + 1) + " task(s) in the list."));
                } else {
                    throw new DukeException("I do not quite understand that.");
                }
            } catch (DukeException err) {
                System.out.println(pad(err.getMessage()));
            }
            str = sc.nextLine();
        }
        System.out.println(pad("Thank you for your patronage. Goodbye!"));
    }
}

