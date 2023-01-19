import java.sql.SQLOutput;
import java.util.Scanner;
public class Duke {

    private static String[] storage_list = new String[100];
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

    public static void add_to_list(String list_item) {
        storage_list[list_index] = list_item;
        list_index++;
        System.out.println("> Duke's response:");
        System.out.println("Added to list: " + list_item);
        System.out.println("--------------------------------\n");
    }

    public static void display_list() {
        int pos = 0;
        System.out.println("List items:");
        while (pos < list_index) {
            System.out.println((pos + 1) + ". " + storage_list[pos]);
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
                add_to_list(user_input);
            }
            else {
                display_list();
            }
            user_input = sc.nextLine();
        }
        exit();

    }
}
