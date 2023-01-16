import java.util.Scanner;

public class Book {
    /** Logo for Book */
    private static final String LOGO = " ____\n"
            + "|  _ \\  ___   ___  _\n"
            + "| |_| |/ _ \\ / _ \\| |  _\n"
            + "|  _ <| | | | | | | |/ /\n"
            + "| |_| | |_| | |_| |   <  \n"
            + "|____/ \\___/ \\___/|_|\\_\\\n";
    /** Horizontal line for separation. */
    private static final String LINE =
            "________________________________________________________________________________\n";

    private static final Scanner input = new Scanner(System.in);
    private static String command = "";
    private static Task[] list = new Task[100];
    private static int index = 0;
    public static void main(String[] args) {
        System.out.print(LINE + "Good day! This is\n" + LOGO + "What may I help you with?\n" + LINE);
        command = input.nextLine();
        while (!command.equals("bye")) {
            parse(command);
            command = input.nextLine();
        }
        System.out.print(LINE + "Bye. Pick up Book again soon!\n" + LINE);
    }

    private static void parse(String text) {
        String[] inputs = text.split(" ", 2);
        if (inputs[0].equals("list")) {
            System.out.print(LINE);
            for (int i = 0; i < index; i++) {
                System.out.println((i + 1) + ". " + list[i]);
            }
            System.out.print(LINE);
        } else if (inputs[0].equals("mark") || inputs[0].equals("unmark")) {
            int taskIndex = Integer.parseInt(inputs[1]) - 1;
            if (inputs[0].equals("mark")) {
                list[taskIndex].mark();
                System.out.println(LINE + "The following task has been marked as done:\n"
                        + list[taskIndex] + "\n" + LINE);
            } else {
                list[taskIndex].unmark();
                System.out.println(LINE + "The following task has been marked as not done:\n"
                        + list[taskIndex] + "\n" + LINE);
            }
        } else {
            list[index++] = new Task(text);
            System.out.print(LINE + "added: " + command + "\n" + LINE);
        }
    }
}
