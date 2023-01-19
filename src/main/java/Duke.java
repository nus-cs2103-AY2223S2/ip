import java.util.Scanner;
import java.util.*;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Mash \nWhat can I do for you?");

        Scanner myObj = new Scanner(System.in);
        String input;
        List<String> list = new ArrayList<String>();
        input = myObj.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                int counter = 1;
                for (String task : list) {
                    System.out.println(counter + ". " + task);
                    counter++;
                }
                input = myObj.nextLine();
            } else {
                list.add(input);
                System.out.println("added: " + input);
                input = myObj.nextLine();
            }
        }

        System.out.println("Bye. Hope to see you again soon!");

//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
    }
}
