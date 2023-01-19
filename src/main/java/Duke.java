import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Consumer;

public final class Duke {
    public final static void main(String[] vargs) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final ArrayList<Task> tasks = new ArrayList<>();
        final Map<String, Consumer<String[]>> funcMap = Map.of("bye", (args) -> {
            System.out.println("Ok bye bye!");
            System.exit(0);
        },
        "list", (args) -> {
            if (tasks.size() == 0) {
                System.out.println("No stored items!");
            } else {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.format("%d. %s\n", i + 1, tasks.get(i).toString());
                }
            }
        },
        "mark", (args) -> {
            try {
                int index = Integer.parseInt(args[1]);
                if (index < 1 || index > tasks.size()) throw new NumberFormatException();
                else {
                    tasks.get(index - 1).done = true;
                    System.out.format("Marked this as done!\n\t%s\n", tasks.get(index - 1));
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid index!\n");
            }
        },
        "unmark", (args) -> {
            try {
                int index = Integer.parseInt(args[1]);
                if (index < 1 || index > tasks.size()) throw new NumberFormatException();
                else {
                    tasks.get(index - 1).done = false;
                    System.out.format("Marked this as not done!\n\t%s\n", tasks.get(index - 1));
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid index!\n");
            }
        });

        while (true) {
            String input = reader.readLine();
            String[] tokens = input.split(" ");

            if (funcMap.containsKey(tokens[0])) {
                funcMap.get(tokens[0]).accept(tokens);
            } else {
                tasks.add(new Task(input));
                System.out.format("added: %s\n", input);
            }
        }
    }
}
