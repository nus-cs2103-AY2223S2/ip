package duke.driver;


import duke.command.DukeCommand;
import duke.parser.DukeParser;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

public class GuiDriver {
    public static String getResponse(String inputString) {
        TaskList taskList = Storage.readTaskList();
        try {
            DukeCommand command = DukeParser.parseCommand(inputString);
            String[] commandArgs = DukeParser.parseCommandArgs(command, inputString);

            switch (command) {
            case LIST: {
                String taskStringList = taskList.toString();
                return taskStringList;
            }
            case BYE: {
                return "Bye. Hope to see you again soon!";
            }
            case DEADLINE: {
                String description = commandArgs[0];
                String by = commandArgs[1];
                Deadline deadline = new Deadline(description, by);
                taskList.addTask(deadline);
                int numTasks = taskList.getNumTasks();
                return Ui.getAddTaskString(deadline, numTasks);
            }
            case EVENT: {
                String description = commandArgs[0];
                String from = commandArgs[1];
                String to = commandArgs[2];
                Event event = new Event(description, from, to);

                taskList.addTask(event);
                int numTasks = taskList.getNumTasks();

                return Ui.getAddTaskString(event, numTasks);
            }
            case TODO: {
                String description = commandArgs[0];
                Task task = new ToDo(description);

                taskList.addTask(task);
                int numTasks = taskList.getNumTasks();

                return Ui.getAddTaskString(task, numTasks);

            }
            case FIND: {
                String keyword = commandArgs[0];

                return Ui.getPrettyString("Here are the matching tasks in your list:",
                        taskList.find(keyword).toString());
            }

            case MARK: {
                int taskIndex = Integer.parseInt(commandArgs[0]);
                try {
                    return Ui.getPrettyString("Nice! I've marked this task as done:",
                            taskList.markTask(taskIndex));
                } catch (Exception e) {
                    // TODO : remove pokemon exception
                    return Ui.getPrettyString(e.getMessage());
                }
            }
            case UNMARK: {
                int taskIndex = Integer.parseInt(commandArgs[0]);
                return Ui.getPrettyString("OK, I've marked this task as not done yet:",
                        taskList.unmarkTask(taskIndex));

            }
            case DELETE: {
                int taskIndex = Integer.parseInt(commandArgs[0]);
                return Ui.getPrettyString("Noted. I've removed this task:",
                        taskList.deleteTask(taskIndex));
            }
            }

        } catch (Error e) {
            return e.getMessage();
        }
        return inputString;
    }
}
