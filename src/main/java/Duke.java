import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("---------------------------------- \n Hello from\n" + logo + "\n What can I do for you? \n ---------------------------------\n");

        awaitInput();
    }

    public static void awaitInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String user_input = br.readLine();
        while (!user_input.toUpperCase().equals("BYE")) {   
            System.out.println("\n----------------------------------\n" + user_input + "\n----------------------------------\n");
            user_input = br.readLine();
        }
        System.out.println("\n----------------------------------\n Bye! Hope to see you again!\n ----------------------------------");
    }

}
