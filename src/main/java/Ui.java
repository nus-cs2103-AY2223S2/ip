import java.util.Scanner;

public class Ui {
    private static final String WELCOME_MESSAGE = "HELLO! I am Aladar! I'll keep track of your tasks! " +
                                                   "Please let me know your tasks by typing in the proper commands!";

    private static final String NEXT_COMMAND_PROMPT = "Please enter the next command below: ";
    private TaskList list;

    public Ui (TaskList list) {
        this.list = list;
    }

   Scanner scanner = new Scanner(System.in);

    public static void horizontalLine() {
        for (int i = 0; i < 50; i++) {
            char horizontalBar = '\u2015';
            System.out.print(horizontalBar);

        }
        System.out.print("\n");
    }

    public void processNextCommand(Parser parser, TaskList list) {
        String userCommandString = scanner.nextLine();
        while (!userCommandString.strip().equals("exit")) {
            parser.executeCommand(userCommandString, list);
            userCommandString = scanner.nextLine();
        }


    }




}
