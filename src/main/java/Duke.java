import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {

    // Attributes
    private static ArrayList<Task> task_list = new ArrayList<>();
    private static int list_index = 0;

    // Methods
    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo);
        System.out.println("How may I help you today?\n");
    }

    public static void echo(String user_input) {
        System.out.println("> Duke's response:");
        System.out.println(user_input);
        System.out.println("--------------------------------\n");
    }

    public static void add_to_list(Task list_item) {
        task_list.add(list_item);
        list_index++;
        System.out.println("> Duke's response:");
        System.out.println("I've added the following task to your list:");
        System.out.println(list_item.toString());
        System.out.println("Current tasks count: " + (list_index));
        System.out.println("--------------------------------\n");
    }

    public static void remove_from_list(int pos) {
        Task curr = task_list.remove(pos - 1);
        list_index--;
        System.out.println("> Duke's response:");
        System.out.println("I've removed the following task from your list:");
        System.out.println(curr.toString());
        System.out.println("Current tasks count: " + (list_index));
        System.out.println("--------------------------------\n");
    }

    public static void display_list() {
        int pos = 0;
        System.out.println("Here are the tasks in your list:");
        while (pos < list_index) {
            System.out.println((pos + 1) + ". " + task_list.get(pos).toString());
            pos++;
        }
        System.out.println("End of list!\n");
    }

    public static void exception_thrower(String exception_type) throws DukeException {
        throw new DukeException(exception_type);
    }

    public static void exit() {
        System.out.println("> Duke's response:");
        System.out.println("Bye. Hope to see you again!");
        System.out.println("--------------------------------\n");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        String user_input =  sc.nextLine();
        String exit_command = "bye";
        while (!user_input.equals(exit_command)) {
            if (!user_input.equals("list")) {
                // If input = "mark x" set task x completed? to True
                if (user_input.startsWith("mark ")){
                    int task_num = Integer.parseInt(user_input.substring(5));
                    task_list.get(task_num - 1).setCompleted(true);
                }

                // If input = "unmark x" set task x completed? to False
                else if (user_input.startsWith("unmark ")){
                    int task_num = Integer.parseInt(user_input.substring(7));
                    task_list.get(task_num - 1).setCompleted(false);
                }

                // If input is a deadline, create deadline and add to task list
                else if (user_input.startsWith("deadline ")) {
                    if (user_input.contains("/by ")) {
                        add_to_list(new Deadline(user_input));
                    } else {
                        try {
                            exception_thrower("deadline");
                        } catch (DukeException de) {
                            System.out.println(de.toString());
                        }
                    }
                }

                // If input is an event, create event and add to task list
                else if (user_input.startsWith("event ")) {
                    if (user_input.contains("/from ") && user_input.contains("/to ")) {
                        add_to_list(new Event(user_input));
                    } else {
                        try {
                            exception_thrower("event");
                        } catch (DukeException de) {
                            System.out.println(de.toString());
                        }
                    }
                }

                // If input is a ToDos item, create ToDos item and add to task list
                else if (user_input.startsWith("todo ")) {
                    if (user_input.length() > 5) {
                        add_to_list(new Todo(user_input));
                    } else {
                        try {
                            exception_thrower("todo");
                        } catch (DukeException de) {
                            System.out.println(de.toString());
                        }
                    }
                }

                // If command is delete, then remove from task list and return deleted task
                else if (user_input.startsWith("delete ")) {
                    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!___________handle non-int input
                    try {
                        int num = Integer.parseInt(user_input.substring(7));
                        remove_from_list(num);
                    } catch (NumberFormatException ex) {
                        ex.printStackTrace();
                    }

                }

                // Else create and add task to list
                else {
                    try {
                        add_to_list(new Task(0));
                    }
                    catch (DukeException de){
                        System.out.println(de.toString());
                    }
                }
            }
            else {
                display_list();
            }
            user_input = sc.nextLine();
        }
        exit();

    }
}
