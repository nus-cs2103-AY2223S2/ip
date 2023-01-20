import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        String[] array = new String[100];

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        int index = 0;

        while (!Objects.equals(input, "bye")) {
            array[index] = input;
            if (Objects.equals(input, "list")) {
                for (int i = 0; i < index; i++) {
                    System.out.println(i + 1 + ". " + array[i]);
                }
            } else {
                System.out.println("added:" + input);
            }
            input = scan.nextLine();
            index++;
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
