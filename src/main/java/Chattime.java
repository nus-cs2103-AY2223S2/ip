import java.util.Scanner;
import java.util.ArrayList;

public class Chattime {

    private static final String greet = "Hey! I'm your friend, Chattime!\n" + "     How can I help you *^*";
    private static final String line = "--------------------------------------******************CHATTIME";
    private static final String goodBye = "Bye bye >^<! Visit me again when you need me ~";
    private static final ArrayList<String> storeList = new ArrayList<>();

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
        storeList.add(item);
        replyUser("added: " + item);
    }

    public static void displayList() {
        int i = 1;
        String message = "";
        for (String item : storeList) {
            if (i != 1) {
                message = message.concat("\n     ");
            }
            message = message.concat(String.format("%d. %s", i, item));
            i++;
        }
        replyUser(message);
    }


    public static void echo(String userInput) {
        replyUser(userInput);
    }

    public static void exit() {
        replyUser(goodBye);
    }
}
