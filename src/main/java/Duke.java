import java.util.ArrayList;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo); */
        System.out.println("Hello! I am Munchmunch hehe :)");
        System.out.println("How may I help you?");
        System.out.println("__________________________");
        ArrayList<String> inst = new ArrayList<String>();
        Boolean exit = true;
        while (exit) {

            Scanner text = new Scanner(System.in);
            String word = text.nextLine();

            if (word.equals("bye")) {
                System.out.println("See ya champ! Enjoy your day!");
                exit = false;
            } else if (word.equals("list")) {
                for(int i = 0; i < inst.size(); i++) {
                    System.out.println((i+1) + "." + inst.get(i));
                }
            } else {
                // adding word into list
                inst.add(word);
                System.out.println("Added: " + word);
            }
            System.out.println("__________________________");
        }




    }
}
