import java.util.*;
public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("____________________________________________________________");
        System.out.println("Hello hello! I'm Panav");
        System.out.println("Whatsup bro");
        System.out.println("____________________________________________________________");

        String word = sc.nextLine();
        while(word.compareTo("bye") != 0) {
            System.out.println("____________________________________________________________");
            System.out.println(word);
            System.out.println();
            System.out.println("____________________________________________________________");
            word = sc.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println();
        System.out.println("____________________________________________________________");
    }
}
