import java.util.Scanner;

public class Duke {
    static String[] taskList = new String[100];
    static int firstUnused = 0;

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
            if (userInput.equalsIgnoreCase("bye")) {
                printNormal("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                printList();
            } else {
                add(userInput);
                printNormal("added: " + userInput);
            }
        }
    }

    static void printNormal(String str) {
        System.out.println("    ------------------------------------------------------------ ");
//        System.out.println("    ____________________________________________________________ ");
        println(str);
        System.out.println("    ------------------------------------------------------------ ");
    }

    static void println(String str) {
        int length = str.length();
        String message = "   | " + str;
        for (int i = 0; i < 60 - length - 1; i++)
            message += " ";
        message += "|";
        System.out.println(message);
    }

    static void add(String str) {
        taskList[firstUnused++] = str;
    }

    static void printList() {
        System.out.println("    ------------------------------------------------------------ ");
        for (int i = 0; i < firstUnused; i++)
            println((i + 1) + ". " + taskList[i]);
        System.out.println("    ------------------------------------------------------------ ");

    }
}
