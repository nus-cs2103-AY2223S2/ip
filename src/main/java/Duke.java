import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    public static void main(String[] args)  {
        boolean terminate = false;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        try {
            while (!terminate) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String input = br.readLine();

                if (input.equals("bye")) {
                    terminate = true;
                    System.out.println("Bye. Hope to see you again soon!");
                } else {
                    System.out.println(input);
                }

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
