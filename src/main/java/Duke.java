import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> list = new ArrayList<Task>();

    private static void displayList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + "." + list.get(i));
        }
    }

    private static void addTask(String description) {
        System.out.println("MEL added: " + description);
        list.add(new Task(description));
    }

    private static void mark(int itemNo) {
        list.get(itemNo).setStatus(true);
        System.out.println("Nice! I've marked this task as done:\n" + list.get(itemNo).toString());
    }

    private static void unmark(int itemNo) {
        list.get(itemNo).setStatus(false);
        System.out.println("OK, I've marked this task as not done yet:\n" + list.get(itemNo).toString());
    }


    public static void main(String[] args) {
        String logo
                = " ___________________\n"
                + " | _______________ |\n"
                + " | |             | |\n"
                + " | |     MEL     | |\n"
                + " | |     <3      | |\n"
                + " | |   CS2103    | |\n"
                + " | |_____________| |\n"
                + " |_________________|\n"
                + "     _[_______]_\n"
                + " ___[___________]___\n"
                + "|         [_____] []|__\n"
                + "|         [_____] []|  \\__\n"
                + "L___________________J     \\ \\___\\/\n"
                + " ___________________      / \\\n"
                + "/###################\\    (__)\n";
        System.out.println(logo + "Hello! I'm MEL\nWhat can I do for you?\n-----------------------");

        Scanner sc = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            System.out.print("> ");
            String userInput = sc.nextLine();
            String cmd = userInput.split(" ")[0];

            switch (cmd) {
                case "bye":
                    quit = true;
                    System.out.println("MEL: Bye. Hope to see you again soon!");
                    break;
                case "list":
                    displayList();
                    break;
                case "mark":
                    mark(Integer.parseInt(userInput.split(" ")[1]) - 1);
                    break;
                case "unmark":
                    unmark(Integer.parseInt(userInput.split(" ")[1]) - 1);
                    break;
                default:
                    addTask(userInput);
                    break;
            }
        }
    }
}
