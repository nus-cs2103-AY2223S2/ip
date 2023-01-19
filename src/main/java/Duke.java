import java.util.*;
import java.io.*;
public class Duke {
    public static void main(String[] args) throws IOException {
        /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"; */
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = "";
        ArrayList < String > list = new ArrayList < String >();

        while( !(text = br.readLine()).equals("bye")) {
            if ( text.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    int j = i + 1;
                    System.out.println(j + " " + list.get(i));
                }
            } else {
                list.add(text);
                System.out.println("Added : " + text);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");


    }
}
