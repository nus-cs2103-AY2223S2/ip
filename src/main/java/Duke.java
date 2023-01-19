import java.sql.SQLOutput;
import java.util.Scanner;
public class Duke {

    private static Task[] storage_list = new Task[100];
    private static int list_index = 0;

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
        storage_list[list_index] = list_item;
        list_index++;
        System.out.println("> Duke's response:");
        System.out.println("I've added the following task to your list:");
        System.out.println(list_item.printTask());
        System.out.println("--------------------------------\n");
    }

    public static void display_list() {
        int pos = 0;
        System.out.println("Here are the tasks in your list:");
        while (pos < list_index) {
            System.out.println((pos + 1) + ". " + storage_list[pos].printTask());
            pos++;
        }
        System.out.println("End of list!\n");
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
                if (user_input.length() > 5 && user_input.startsWith("mark ")){
                    int task_num = Integer.parseInt(user_input.substring(5));
                    storage_list[task_num - 1].setCompleted(true);
                }

                // If input = "unmark x" set task x completed? to False
                else if (user_input.length() > 7 && user_input.startsWith("unmark ")){
                    int task_num = Integer.parseInt(user_input.substring(7));
                    storage_list[task_num - 1].setCompleted(false);
                }

                // Else create and add task to list
                else {
                    add_to_list(new Task(false, user_input));
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
