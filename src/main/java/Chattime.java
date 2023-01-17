import java.util.Scanner;

public class Chattime {

    private static final String greet = "Hey! I'm your friend, Chattime!\n" + "     How can I help you *^*";
    private static final String line = "--------------------------------------******************CHATTIME";
    private static final String goodBye = "Bye bye >^<! Visit me again when you need me ~";

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
        while (!userInput.equals("bye")) {
            echo(userInput);
            userInput = sc.nextLine();
        }
        replyUser(goodBye);
    }

    public static void echo(String userInput) {
        replyUser(userInput);
    }
}
