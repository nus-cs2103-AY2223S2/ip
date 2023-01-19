import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public final class Duke {
    public final static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String input = reader.readLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Ok bye bye!");
                return;
            }
            System.out.println(input);
        }
    }
}
