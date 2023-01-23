package duke;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import duke.commands.*;
import duke.commands.taskCommand.*;
import duke.main.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class Duke {
    private final TaskList tasks;
    private final Parser commands;

    public Duke(List<Task> tasks) {
      this.tasks = TaskList.fromIterable(tasks);
      this.commands = new Parser(Stream.<Supplier<Command>>of(
        ListCommand::new,
        MarkCommand::new,
        UnmarkCommand::new,
        TodoCommand::new,
        DeadlineCommand::new,
        EventCommand::new,
        DeleteCommand::new,
        SaveCommand::new
      ).map(Supplier::get));
    }

    public Duke() {
      this(new ArrayList<>());
    }

    public final TaskList getTaskList() { return tasks; }

    public void runDuke() throws IOException {
      Ui.printLogo();   

      try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
        while (true) {
            String input = reader.readLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Ok bye bye!");
                return;
            } else if (input.isEmpty()) continue;

            this.commands.executeCommand(input, this);
        }
      }
    }
}