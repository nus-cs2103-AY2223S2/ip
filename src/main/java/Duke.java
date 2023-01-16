import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String greeting = "____________________________________________________________\n" + "Hello! I'm Duke\n" + "What can I do for you?\n" + "____________________________________________________________";
    private static final String goodbye = "____________________________________________________________\n" + "Bye. Hope to see you again soon!\n" + "____________________________________________________________";
    private static boolean active = true;
    private static final ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println(greeting);
        while(active) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String command = input.split(" ")[0];
            switch (command) {
                case "bye":
                    active = false;
                    System.out.println(goodbye);
                    break;
                case "list":
                    System.out.println("____________________________________________________________");
                    for (int i = 0; i < tasks.size(); i++) {
                        if (tasks.get(i) != null) {
                            System.out.println(i + 1 + "." + tasks.get(i).toString());
                        }
                    }
                    System.out.println("____________________________________________________________");
                    break;

                case "deadline": {
                    try {
                    String rest = input.substring(command.length());
                    if (rest.equals("")) {
                        throw new DukeException("____________________________________________________________\n" +
                                " ☹ OOPS!!! The description or time/date of a deadline cannot be empty.\n" +
                                "____________________________________________________________");
                    }
                    String[] arr = rest.split("/");
                    Deadline item = new Deadline(arr[0], arr[1].substring(3));
                    tasks.add(item);
                    System.out.println("____________________________________________________________\n Got it. I've added this task: \n"
                            + "   " + item + "\n Now you have " + tasks.size() + " tasks in the list."
                            + "\n____________________________________________________________");
                    }
                    catch(DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case "todo": {
                    try {
                        String rest = input.substring(command.length());
                        if (rest.equals("")) {
                            throw new DukeException("____________________________________________________________\n" +
                                    " ☹ OOPS!!! The description of a todo cannot be empty.\n" +
                                    "____________________________________________________________");
                        }
                        String[] arr = rest.split("/");
                        Todo item = new Todo(arr[0]);
                        tasks.add(item);
                        System.out.println("____________________________________________________________\n Got it. I've added this task: \n"
                                + "   " + item + "\n Now you have " + tasks.size() + " tasks in the list."
                                + "\n____________________________________________________________");
                    }
                    catch(DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case "event": {
                    try{
                    String rest = input.substring(command.length());
                    if (rest.equals("")) {
                        throw new DukeException("____________________________________________________________\n" +
                                " ☹ OOPS!!! The description or time/date of an event cannot be empty.\n" +
                                "____________________________________________________________");
                    }
                    String[] arr = rest.split("/");
                    Event item = new Event(arr[0], arr[1].substring(5), arr[2].substring(3));
                    tasks.add(item);
                    System.out.println("____________________________________________________________\n Got it. I've added this task: \n"
                            + "   " + item + "\n Now you have " + tasks.size() + " tasks in the list."
                            + "\n____________________________________________________________");
                    }
                    catch(DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case "mark": {
                    int num = Integer.parseInt(input.split(" ")[1]);
                    tasks.get(num - 1).markDone();
                    System.out.println("____________________________________________________________\n" +
                            "Nice! I've marked this task as done:\n" +
                            "   " + tasks.get(num - 1).toString() + "\n" +
                            "____________________________________________________________");
                    break;
                }

                case "unmark": {
                    int num = Integer.parseInt(input.split(" ")[1]);
                    tasks.get(num - 1).markUndone();
                    System.out.println("____________________________________________________________\n" +
                            "OK, I've marked this task as not done yet:\n" +
                            "   " + tasks.get(num - 1).toString() + "\n" +
                            "____________________________________________________________");
                    break;
                }
                default:
                    try{
                        throw new DukeException("____________________________________________________________\n" +
                                " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                                "____________________________________________________________");
                    }
                    catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
    }
}
