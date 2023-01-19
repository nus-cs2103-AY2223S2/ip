import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Welcome welcome!");
        echo();
    }

    public static void echo() {
        System.out.println("Start by typing something and Dukey will echo!! Type bye to exit!! ");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println("Dukey: " + input);
            if (input.equals("Bye")) {
                System.out.println("Dukey: Did you mean 'bye'? Type 'bye' to exit!!");
            }
        }
        System.out.println("\nDukey: Goodbye!! Please return to Dukey again soon!! :) ");
        scanner.close();

    }



}
