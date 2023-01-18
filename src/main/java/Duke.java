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
        System.out.println(logo + "Hello! I'm Duke. How may I be of assistance?\n");
        String str = sc.nextLine();
        while (true){
            String[] cmd_details = str.split(" ", 2);
            String cmd = cmd_details[0];
            String details = "";
            boolean valid = false;
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
            if (cmd_details.length > 1) {
                details = cmd_details[1];
            }
            if (cmd.equals("mark") && pointer != -1) {
                // mark task as done
                Task curr = storage[Integer.valueOf(details) - 1];
                System.out.println(pad(curr.outputMarked() + curr.toString()));
                valid = true;
            } else if (cmd.equals("unmark") && pointer != -1) {
                // mark task as undone
                Task curr = storage[Integer.valueOf(details) - 1];
                System.out.println(pad(curr.outputUnmarked() + curr.toString()));
                valid = true;
            } else if (cmd.equals("todo")) {
                pointer++;
                Todo addition = new Todo(details);
                storage[pointer] = addition;
                System.out.println(pad("Understood. I have added the task:\n" + addition.toString()
                    + "\nThere are now " + Integer.toString(pointer + 1) + " task(s) in the list."));
                valid = true;
            } else if (cmd.equals("deadline")) {
                String[] temp = details.split(" /by ");
                if (temp.length == 2) {
                    pointer++;
                    Deadline addition = new Deadline(temp[0], temp[1]);
                    storage[pointer] = addition;
                    System.out.println(pad("Understood. I have added the task:\n" + addition.toString()
                            + "\nThere are now " + Integer.toString(pointer + 1) + " task(s) in the list."));
                    valid = true;
                }
            } else if (cmd.equals("event")) {
                String[] temp = details.split(" /from ");
                if (temp.length == 2) {
                    String[] temp2 = temp[1].split(" /to ");
                    if (temp2.length == 2) {
                        pointer++;
                        Event addition = new Event(temp[0], temp2[0], temp2[1]);
                        storage[pointer] = addition;
                        System.out.println(pad("Understood. I have added the task:\n" + addition.toString()
                                + "\nThere are now " + Integer.toString(pointer + 1) + " task(s) in the list."));
                        valid = true;
                    }
                }
            }
            if (!valid) {
                System.out.println(pad("Invalid input."));
            }
            str = sc.nextLine();
        }
        System.out.println(pad("Thank you for your patronage. Goodbye!"));
    }
}

