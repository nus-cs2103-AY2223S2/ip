import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello Brother\n" + logo + "\nWhats up what can I do for you mi amigo");

        Scanner inputScanner = new Scanner(System.in);
        String input;
        String output;

        while(true) {
            input = inputScanner.nextLine();

            // level_1 feature: exit when user types "bye"
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("ok see you brother!");
                break;
            }

            output = input;
            System.out.println(output);

        }



    }


}

