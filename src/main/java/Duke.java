import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public final class Duke {
    public final static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();

        while (true) {
            String input = reader.readLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Ok bye bye!");
                return;
            } else if (input.equalsIgnoreCase("list")) {
                if (list.size() == 0) {
                    System.out.println("No stored items!");
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        System.out.format("%d. %s\n", i + 1, list.get(i));
                    }
                }
                continue;
            }

            list.add(input);
            System.out.format("added: %s\n", input);
        }
    }
}
