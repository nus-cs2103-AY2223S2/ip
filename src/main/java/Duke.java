import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello i'm\n" + logo);
        System.out.println("What can i do for you?");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        ArrayList<String> list = new ArrayList<String>(); 
        int counter = 0;

        while (!input.equals("bye")){
            if (input.equals("list")){
                for (int i = 0; i < counter; i++) {
                    System.out.println( i + 1 + ". " + list.get(i));
                }
            }
            list.add(input);
            counter = counter + 1;
            System.out.println(" added: " + input);
            input = scan.nextLine();
        }

        System.out.println("Bye. Hope to see you again!");
    }
}
