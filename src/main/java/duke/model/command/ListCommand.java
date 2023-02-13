package duke.model.command;

import java.util.function.Function;
import java.util.stream.Collectors;

import duke.model.task.Task;
import duke.model.task.TaskList;

public class ListCommand extends Command {

    @Override
    public String execute(TaskList list) {
        String content = list.stream().map(new Function<Task, String>() {
            private int index = 1;

            @Override
            public String apply(Task element) {
                String out = String.format("%d.%s", index, element);
                index++;
                return out;
            }
        }).collect(Collectors.joining("\n"));
        return "Here are the tasks in your list:\n" + content;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand;
    }
}
