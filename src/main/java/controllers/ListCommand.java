package controllers;

import entities.TaskList;
import enums.CommandType;
import enums.TaskType;
import exceptions.DukeException;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListCommand extends Command {
    private final String arguments;
    private final Pattern VALID_LIST_CMD = Pattern.compile("^(list)(\\s+(?<filter>todo|deadline|event))?$");

    public ListCommand(String arguments) {
        super(CommandType.LIST);
        this.arguments = arguments;
    }

    @Override
    public void execute(Supplier<? extends TaskList> store) throws DukeException{
        TaskList taskList = store.get();
        Matcher matcher = VALID_LIST_CMD.matcher(arguments);
        if (matcher.find()) {
            String filter = matcher.group("filter");
            TaskType type = Optional.ofNullable(filter)
                    .map(enumTask -> TaskType.valueOf(enumTask.toUpperCase()))
                    .orElse(TaskType.ALL);

            taskList.listTasks(task -> type.isAll() || task.getTaskType() == type);
        } else {
            throw new DukeException(INVALID_FORMAT_ERROR);
        }
    }
}