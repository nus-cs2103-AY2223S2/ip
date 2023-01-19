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
            String arr1[] = input.split("/");
            String arr2[] = arr1[0].split(" ");
            switch (arr2[0]) {
                case "":
                    break;

                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < todo.size(); i++) {
                        System.out.print(i + 1 + ".");

                        if (todo.get(i) instanceof Todos) {
                            System.out.print("[T]");
                        } else if (todo.get(i) instanceof Deadlines) {
                            System.out.print("[D]");
                        } else if (todo.get(i) instanceof Events) {
                            System.out.print("[E]");
                        }

                        if (todo.get(i).getStatus()) {
                            System.out.print("[X] ");
                        } else {
                            System.out.print("[ ] ");
                        }
                        System.out.print(todo.get(i).getName());

                        if (todo.get(i) instanceof Deadlines) {
                            System.out.printf(" (by: %s)\n", ((Deadlines) todo.get(i)).getTime());
                        } else if (todo.get(i) instanceof Events) {
                            System.out.printf(" (from: %s to: %s)\n", ((Events) todo.get(i)).getStrtime(), ((Events) todo.get(i)).getEndtime());
                        } else {
                            System.out.println("");
                        }
                    }
                    break;

                case "mark":
                    int index = Integer.parseInt(arr2[1]);
                    if (index < 1 || index > todo.size()) {
                        System.out.println("Sorry, this task number is invalid.");
                        break;
                    }
                    todo.get(index - 1).setStatus(true);
                    System.out.println("Nice! I've marked this task as done:");
                    if (todo.get(index - 1) instanceof Todos) {
                        System.out.print("[T]");
                    } else if (todo.get(index - 1) instanceof Deadlines) {
                        System.out.print("[D]");
                    } else if (todo.get(index - 1) instanceof Events) {
                        System.out.print("[E]");
                    }
                    System.out.print("[X] " + todo.get(index - 1).getName());
                    if (todo.get(index - 1) instanceof Deadlines) {
                        System.out.printf(" (by: %s)\n", ((Deadlines) todo.get(index - 1)).getTime());
                    } else if (todo.get(index - 1) instanceof Events) {
                        System.out.printf(" (from: %s to: %s)\n", ((Events) todo.get(index - 1)).getStrtime(), ((Events) todo.get(index - 1)).getEndtime());
                    } else {
                        System.out.println("");
                    }
                    break;

                case "unmark":
                    int No = Integer.parseInt(arr2[1]);
                    if (No < 1 || No > todo.size()) {
                        System.out.println("Sorry, this task number is invalid.");
                        break;
                    }
                    todo.get(No - 1).setStatus(false);
                    System.out.println("OK, I've marked this task as not done yet:");
                    if (todo.get(No - 1) instanceof Todos) {
                        System.out.print("[T]");
                    } else if (todo.get(No - 1) instanceof Deadlines) {
                        System.out.print("[D]");
                    } else if (todo.get(No - 1) instanceof Events) {
                        System.out.print("[E]");
                    }
                    System.out.print("[ ] " + todo.get(No - 1).getName());
                    if (todo.get(No - 1) instanceof Deadlines) {
                        System.out.printf(" (by: %s)\n", ((Deadlines) todo.get(No - 1)).getTime());
                    } else if (todo.get(No - 1) instanceof Events) {
                        System.out.printf(" (from: %s to: %s)\n", ((Events) todo.get(No - 1)).getStrtime(), ((Events) todo.get(No - 1)).getEndtime());
                    } else {
                        System.out.println("");
                    }
                    break;

                case "todo":
                    String name = arr1[0].substring(arr1[0].indexOf(" ") + 1);
                    todo.add(new Todos(name));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("[T][ ] " + name);
                    System.out.printf("Now you have %d tasks in the list.\n", todo.size());
                    break;

                case "deadline":
                    String dname = arr1[0].substring(arr1[0].indexOf(" ") + 1, arr1[0].length() - 1);
                    String dtime = arr1[1].substring(arr1[1].indexOf(" ") + 1);
                    todo.add(new Deadlines(dname, dtime));
                    System.out.println("Got it. I've added this task:");
                    System.out.printf("[D][ ] " + dname + " (by: %s)\n", dtime);
                    System.out.printf("Now you have %d tasks in the list.\n", todo.size());
                    break;

                case "event":
                    String ename = arr1[0].substring(arr1[0].indexOf(" ") + 1, arr1[0].length() - 1);
                    String strtime = arr1[1].substring(arr1[1].indexOf(" ") + 1, arr1[1].length() - 1);
                    String endtime = arr1[2].substring(arr1[2].indexOf(" ") + 1);
                    todo.add(new Events(ename, strtime, endtime));
                    System.out.println("Got it. I've added this task:");
                    System.out.printf("[E][ ] " + ename + " (from: %s to: %s)\n", strtime, endtime);
                    System.out.printf("Now you have %d tasks in the list.\n", todo.size());
                    break;

                default:
                    System.out.println("You have input a invalid message!");
                    break;
            }
            input = ai.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
