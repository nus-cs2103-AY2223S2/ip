import java.util.*;
public class Duke {
    public static void main(String[] args) {
        /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo); */
        System.out.println("Hello! I am Munch hehe :)");
        System.out.println("How may I help you?");
        System.out.println("__________________________");
        // String word = "random";
        Boolean exit = true;
        while (exit) {
            Scanner text = new Scanner(System.in);
            String word = text.nextLine();

            if (word.equals("bye")) {
                System.out.println("See ya champ! Enjoy your day!");
                exit = false;
            } else {
                System.out.println(word);
            }
            System.out.println("__________________________");
        }


        

    }
}
