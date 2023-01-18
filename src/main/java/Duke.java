import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    /**
     * A level 3 chat bot Duke.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner ai = new Scanner(System.in);
        String greetings = "Hello! I'm Duke" + "\nWhat can I do for you?";
        System.out.println(greetings);
        ArrayList<Task> todo = new ArrayList<>();
        String input = ai.nextLine();
        while (!input.equals("bye")) {
            String arr[] = input.split(" ");
            switch (arr[0]) {
                case "":
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < todo.size(); i++) {
                        System.out.print(i + 1 + ".");
                        if (todo.get(i).getStatus()) {
                            System.out.print("[X] ");
                        } else {
                            System.out.print("[ ] ");
                        }
                        System.out.println(todo.get(i).getName());
                    }
                    break;
                case "mark":
                    int index = Integer.parseInt(arr[1]);
                    if (index < 1 || index > todo.size()) {
                        System.out.println("Sorry, this task number is invalid.");
                        break;
                    }
                    todo.get(index - 1).setStatus(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[X] " + todo.get(index - 1).getName());
                    break;
                case "unmark":
                    int No = Integer.parseInt(arr[1]);
                    if (No < 1 || No > todo.size()) {
                        System.out.println("Sorry, this task number is invalid.");
                        break;
                    }
                    todo.get(No - 1).setStatus(false);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[ ] " + todo.get(No - 1).getName());
                    break;
                default:
                    todo.add(new Task(input));
                    System.out.println("added: " + input);
            }
            input = ai.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
