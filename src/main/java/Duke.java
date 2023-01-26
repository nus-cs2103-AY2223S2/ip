import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|";
//        System.out.println("----------------------------------------------------------------");
//        System.out.println("Hello from\n" + logo);
//        System.out.println("----------------------------------------------------------------");
        System.out.println("Please type something...");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            if (userInput.contains("bye.") || userInput.contains("bye ") ||
                    userInput.contains("bye!") || userInput.contains("bye~") ||
                    userInput.contains("bye,") || userInput.contains("Bye ") ||
                    userInput.contains("Bye.") || userInput.contains("Bye!") ||
                    userInput.contains("Bye~") || userInput.contains("Bye,")) {
                print("Bye. Hope to see you again soon!");
                break;
            } else
                print(userInput);
        }
    }

    static void print(String str) {
        int length = str.length();
        System.out.println("    ------------------------------------------------------------ ");
//        System.out.println("    ____________________________________________________________ ");
        String message = "   |" + str;
        for (int i = 0; i < 60 - length; i++)
            message += " ";
        message += "|";
        System.out.println(message);
        System.out.println("    ------------------------------------------------------------ ");
    }
}
