import java.util.Scanner;

public class Duke {
    private static Scanner scanner = new Scanner(System.in);
    private static String horizontalLine = "************************";
    private static Task[] taskArr = new Task[100];
    private static int count = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(horizontalLine);
        System.out.println("HELLO! I'M DUKE!");
        System.out.println("HOW CAN I HELP?");
        System.out.println(horizontalLine);
        readInput();
    }

    private static void readInput() {
        String input = scanner.nextLine();

        if (input.equals("bye")) {
            System.out.println(horizontalLine);
            System.out.println("BYE!");
            System.out.println(horizontalLine);
        } else if (input.equals("list")) {
            System.out.println(horizontalLine);
            System.out.println("HERE ARE YOUR TASKS!");
            for (int i = 0; i < count; i++) {
                System.out.println("" + (i+1) + ". " +
                        "[" + taskArr[i].getStatusIcon() + "] " +
                        taskArr[i].getDescription());
            }
            System.out.println(horizontalLine);
            readInput();
        } else if (input.substring(0, 4).equals("mark")) {
            int index = Integer.parseInt(input.substring(5, 6));
            System.out.println(horizontalLine);
            System.out.println("I'VE MARKED THIS TASK AS DONE: ");
            System.out.println("[X] " + taskArr[index-1].getDescription());
            taskArr[index-1].setDone(true);
            System.out.println(horizontalLine);
            readInput();
        } else if (input.substring(0, 6).equals("unmark")) {
            int index = Integer.parseInt(input.substring(7, 8));
            System.out.println(horizontalLine);
            System.out.println("I'VE MARKED THIS TASK AS UNDONE: ");
            System.out.println("[ ] " + taskArr[index-1].getDescription());
            taskArr[index-1].setDone(false);
            System.out.println(horizontalLine);
            readInput();
        } else {
            System.out.println(horizontalLine);
            System.out.println("ADDED: " + input);
            taskArr[count] = new Task(input);
            count++;
            System.out.println(horizontalLine);
            readInput();
        }
    }

}
