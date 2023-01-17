import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Chattime {

    private static final String greet = "Hey! I'm your friend, Chattime!\n" + "     How can I help you *^*";
    private static final String line = "--------------------------------------******************CHATTIME";
    private static final String goodBye = "Bye bye >^<! Visit me again when you need me ~";
    private static final ArrayList<Task> storeList = new ArrayList<>();

    public static void main(String[] args) {
        String logo = "      ___\n"
                    + "     /*  \\       *@ ^ @*\n"
                    + "    /::\\  \\     ___\n"
                    + "   /:/::\\  \\   /*  \\ \n"
                    + "  /:/  \\:\\  \\  \\:\\  \\ \n"
                    + " /:/__/ \\:\\__\\  \\:\\  \\ \n"
                    + " \\:\\ \\   \\/__/  /::\\  \\ \n"
                    + "  \\:\\ \\        /:/::\\__\\ \n"
                    + "   \\:\\ \\*H*A*T/:/  \\/__/*I*M*E\n"
                    + "    \\:\\_\\    /:/  / \n"
                    + "     \\/__/   \\/__/  \n";

        System.out.println("Hello from\n" + logo);

        replyUser(greet);

        Scanner sc = new Scanner(System.in);

        chat(sc);

    }

    public static void replyUser(String message) {
        System.out.println("    " + line);
        System.out.println("     " + message);
        System.out.println("    " + line);
    }

    public static void chat(Scanner sc) {
        String userInput = sc.nextLine();
        String[] splitCmd = userInput.split(" ", 2);
        String command = splitCmd[0];

        while (!command.equals("bye")) {
            if (command.equals("list") && splitCmd.length == 1) {
                displayList();

            } else if (command.equals("mark") && splitCmd.length == 2 &&
                    Pattern.matches("^[0-9]*$", splitCmd[1])) {

                int index = Integer.parseInt(splitCmd[1]);
                mark(index);

            } else if (command.equals("unmark") && splitCmd.length == 2 &&
                    Pattern.matches("^[0-9]*$", splitCmd[1])) {

                int index = Integer.parseInt(splitCmd[1]);
                unmark(index);

            } else {
                store(userInput);
            }

            userInput = sc.nextLine();
            splitCmd = userInput.split(" ", 2);
            command = splitCmd[0];
        }
        exit();
    }


    public static void store(String item) {
        Task task = new Task(item);
        storeList.add(task);
        replyUser("added: " + task.description);
    }

    public static void displayList() {
        int i = 1;
        String message = "Task(s) waiting to be completed:";
        for (Task task : storeList) {
            message = message.concat(String.format("\n     %d. %s", i, task.checkItem()));
            i++;
        }
        replyUser(message);
    }

    public static void mark(int index) {
        storeList.get(index - 1).markAsDone();
    }

    public static void unmark(int index) {
        storeList.get(index - 1).unmarkDone();
    }

    public static void echo(String userInput) {
        replyUser(userInput);
    }

    public static void exit() {
        replyUser(goodBye);
    }
}
