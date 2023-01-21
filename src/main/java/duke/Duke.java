package duke;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import commands.CommandStore;
import duke.tasks.Task;

public class Duke {
    private final List<Task> tasks;
    private final CommandStore commands;

    public Duke() {
      this.tasks = new ArrayList<>();
      this.commands = new CommandStore();
    }

    public final List<Task> getTaskList() { return tasks; }

    public void runDuke() throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String input = reader.readLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Ok bye bye!");
                return;
            }

            Stream<String> outputs = this.commands.executeCommand(input, this);
            outputs.forEach(System.out::println);
        }
    }
}