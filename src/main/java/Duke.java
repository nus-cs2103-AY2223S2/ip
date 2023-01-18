import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        introMessage();

        Checker checker = new Checker();
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        ArrayList<Task> lstOfItems = new ArrayList<>();

        while (!checker.checkEnd(userInput)) {
            if (checker.checkListRequest(userInput)) {
                if (lstOfItems.size() == 0 ) {
                    System.out.println("Nothing here yet. Add your 1st item!");
                } else {
                    for (int i = 0; i < lstOfItems.size(); i++) {
                        System.out.print(String.valueOf(i + 1) + ".");
                        System.out.println(lstOfItems.get(i));
                    }
                }
            } else {
                System.out.println("added: " + userInput);
                lstOfItems.add(new Task(userInput));
            }
            userInput = scan.nextLine();
        }

        endMessage();

    }

    public static void introMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void endMessage() {
        System.out.println("Bye. Hope to see you again!");
    }
}
