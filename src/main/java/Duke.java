import java.io.*;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String str = bufferedReader.readLine();
        ArrayList<String> store = new ArrayList<>();
        while (!str.equals("bye")) {
            if (str.equals("list")) {
                int number = 1;
                for(String stored : store) {
                    System.out.println(number + ". " + stored);
                    number++;
                }
                str = bufferedReader.readLine();
            } else {
                store.add(str);
                System.out.println(str);
                str = bufferedReader.readLine();
            }

        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
