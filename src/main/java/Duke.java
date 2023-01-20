import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc= new Scanner(System.in);
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        System.out.println("------------------------------------------------------------------------------");
        String cur = sc.nextLine();
            while (!cur.equals("bye")) {
                try {
                    String command = cur.split(" ")[0].toUpperCase();
                    Commands input = Enum.valueOf(Commands.class, command);
                    switch (input) {
                        case LIST :
                            System.out.println("------------------------------------------------------------------------------");
                            System.out.println("Here are the tasks in your list:");
                            for (int j = 0; j < tasks.size(); j++) {
                                int k = j + 1;
                                System.out.println(k + "." + tasks.get(j).toString());
                            }
                            System.out.println("------------------------------------------------------------------------------");
                            break;
                        case MARK:
                            int index = Integer.valueOf(cur.split(" ")[1]);
                            tasks.get(index-1).markAsDone();
                            System.out.println("------------------------------------------------------------------------------");
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("  " + tasks.get(index - 1).toString());
                            System.out.println("------------------------------------------------------------------------------");
                            break;
                        case UNMARK:
                            index = Integer.valueOf(cur.split(" ")[1]);
                            tasks.get(index - 1).markAsUnDone();
                            System.out.println("------------------------------------------------------------------------------");
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println("  " + tasks.get(index - 1).toString());
                            System.out.println("------------------------------------------------------------------------------");
                            break;
                        case EVENT:
                            tasks.add(new Event(cur));
                            System.out.println("------------------------------------------------------------------------------");
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + tasks.get(tasks.size() - 1).toString());
                            System.out.println("Now you have " + Task.counter + " tasks in the list.");
                            System.out.println("------------------------------------------------------------------------------");
                            break;
                        case DEADLINE:
                            tasks.add(new Deadline(cur));
                            System.out.println("------------------------------------------------------------------------------");
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + tasks.get(tasks.size() - 1).toString());
                            System.out.println("Now you have " + Task.counter + " tasks in the list.");
                            System.out.println("------------------------------------------------------------------------------");
                            break;
                        case DELETE:
                            System.out.println("------------------------------------------------------------------------------");
                            System.out.println("Noted. I've removed this task:");
                            System.out.println("  " + tasks.get(Integer.valueOf(cur.split(" ")[1]) - 1).toString());
                            Task.decreaseCounter();
                            System.out.println("Now you have " + Task.counter + " tasks in the list.");
                            System.out.println("------------------------------------------------------------------------------");

                            tasks.remove(Integer.valueOf(cur.split(" ")[1]) - 1);
                            break;
                        case TODO: {
                            if (cur.split(" ").length == 1) {
                                throw new ToDoException("____________________________________________________________\n" +
                                        "  ☹ OOPS!!! The description of a todo cannot be empty.\n" +
                                        "____________________________________________________________");
                            }
                            tasks.add(new Todo(cur));
                            System.out.println("------------------------------------------------------------------------------");
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + tasks.get(tasks.size() - 1));
                            System.out.println("Now you have " + Task.counter + " tasks in the list.");
                            System.out.println("------------------------------------------------------------------------------");
                            break;
                        }
                    }
                }
                catch(ToDoException e) {
                    System.out.println(e.toString());

                }
                catch(IllegalArgumentException e) {
                    System.out.println("____________________________________________________________\n" +
                            "  ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                            "____________________________________________________________");
                }
                finally {
                    cur = sc.nextLine();
                }
            }
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------------------------------------------------------------------------");
    }
}
