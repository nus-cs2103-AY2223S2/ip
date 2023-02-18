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
        String output;
        String[] storage = new String[100];
        int storeIndex = 0;


        while(true) {
            input = inputScanner.nextLine();

            // level_1 feature: exit when user types "bye"
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("ok see you brother all love no cringe!");
                break;
            }

            // level_2 feature: add input to array + display array when required
            if (input.equalsIgnoreCase("display")) {
                System.out.println("Here you go my brother!/n" );
                for(int i = 0; i < storeIndex; i++) {
                    System.out.println(i + ". " + storage[i]);
                }
                System.out.println("Anything else I can do for you top dawg/n" );
            } else {
                storage[storeIndex] = input;
                System.out.println("added to list my brother: " + input);
            }

            storeIndex++;



        }

    }

}

