import java.util.Scanner;

public class Duke {
    private static String[] txtList = new String[100];
    private static int itemCount = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                System.out.println("Duke's list of commands");
                for (int i = 0; i < itemCount; i++) {
                    System.out.printf("%d %s \n", i + 1, txtList[i]);
                }
            }
            else {
                addInputToList(userInput);
            }
        }
    }


    public static void addInputToList(String userInput) {
        System.out.printf("added: %s\n", userInput);
        txtList[itemCount++] = userInput;
    }

}
