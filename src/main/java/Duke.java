import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
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
        Boolean quit = false;
        ArrayList<String> list = new ArrayList<String>();

        while (!quit) {
            System.out.print("> ");
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                quit = true;
                System.out.println("MEL: Bye. Hope to see you again soon!");
            }
            else if (userInput.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i+1 + ". " + list.get(i));
                }
            }
            else {
                System.out.println("MEL added: " + userInput);
                list.add(userInput);
            }
        }
    }
}
