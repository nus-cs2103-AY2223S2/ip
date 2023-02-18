import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /**
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
         **/

        System.out.println("Hello Brother\nWelcome to Brother Bot\n" + "Whats up what can I do for you mi amigo");

        Scanner inputScanner = new Scanner(System.in);
        String input;
        Task[] storage = new Task[100];
        int storeIndex = 0;



        while(true) {
            input = inputScanner.nextLine();

            // level_1 feature: exit when user types "bye"
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("ok see you brother all love no cringe!");
                break;
            }

            // level_3 feature: use input to construct Task object and add to array + display array when required + mark Task as done
            if (input.equalsIgnoreCase("display")) {
                System.out.println("Here you go my brother!" );
                for(int i = 0; i < storeIndex; i++) {
                    System.out.println((i + 1) + ". " + storage[i].toString());
                }
                System.out.println("Anything else I can do for you top G" );
            } else if (input.length() >= 6 && input.substring(0,4).equalsIgnoreCase("mark")) {
                int i = Integer.parseInt(input.substring(5)) - 1;
                storage[i].markAsDone();
                System.out.println("Marked as you wish my brother:");
                System.out.println(i + ". " + storage[i].toString());
                System.out.println(" Whats next?");
            } else {
                storage[storeIndex] = new Task(input);
                System.out.println("added to list my brother: " + input);
                storeIndex++;
            }




        }

    }

}

