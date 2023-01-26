package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Parser {
    private enum Command {
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        DELETE("delete"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        FIND("find");
        private final String typeStr;
        Command(String typeStr) {
            this.typeStr = typeStr;
        }
        @Override
        public String toString() {
            return typeStr;
        }
    }

    /**
     * Parses a given input string.
     *
     * @param inputStr The input string given by the user.
     * @param tasks The TaskList to be managed.
     * @param ui The Ui to be used to print messages.
     * @throws BadCommandException If the parameters of the input string are insufficient, or if
     * the command given is not recognised.
     */
    public void parseString(String inputStr, TaskList tasks, Ui ui) throws BadCommandException {
        inputStr = inputStr.trim();
        try {
            if (inputStr.equals(Command.LIST.toString())) {
                ui.showNormalMessage(String.format(
                        "Here are the tasks in your list:\n%s",
                        tasks
                ));
                return;
            }
            String[] inputSplit = inputStr.split(" ", 2);
            if (inputSplit.length < 2) {
                throw new BadCommandException("There are insufficient parameters!");
            }
            String commandStr = inputSplit[0].trim();
            String params = inputSplit[1].trim();
            Task newTask = null;
            if (commandStr.equals(Command.FIND.toString())) {
                TaskList matchingTasks = tasks.getTasksByKeyword(params);
                ui.showNormalMessage(String.format(
                        "Here are the matching tasks in your list:\n%s",
                        matchingTasks
                ));
            } else if (commandStr.equals(Command.MARK.toString())) {
                int idx = Integer.parseInt(params) - 1;
                tasks.markTaskAsDone(idx);
                ui.showNormalMessage(String.format(
                        "Nice! I've marked this task as done:\n\t\t%s",
                        tasks.getTask(idx)
                ));
            } else if (commandStr.equals(Command.UNMARK.toString())) {
                int idx = Integer.parseInt(params) - 1;
                tasks.unmarkTaskAsDone(idx);
                ui.showNormalMessage(String.format(
                        "OK, I've marked this task as not done yet:\n\t\t%s",
                        tasks.getTask(idx)
                ));
            } else if (commandStr.equals(Command.DELETE.toString())) {
                int idx = Integer.parseInt(params) - 1;
                Task deletedTask = tasks.removeTask(idx);
                ui.showNormalMessage(String.format(
                        "Got it. I've removed this task:\n\t\t%s\n\tNow you have %d task%s in the list.",
                        deletedTask,
                        tasks.getSize(),
                        tasks.getSize() > 1 ? "s" : ""
                ));
            } else if (commandStr.equals(Command.TODO.toString())) {
                newTask = new Todo(params.trim());
            } else if (commandStr.equals(Command.DEADLINE.toString())) {
                String[] paramsSplit = params.split("/by", 2);
                if (paramsSplit.length < 2) {
                    throw new BadCommandException("There are insufficient parameters!");
                }
                newTask = new Deadline(paramsSplit[0].trim(), paramsSplit[1].trim());
            } else if (commandStr.equals(Command.EVENT.toString())) {
                String[] paramsSplit = params.split("/from|/to", 3);
                if (paramsSplit.length < 3) {
                    throw new BadCommandException("There are insufficient parameters!");
                }
                newTask = new Event(paramsSplit[0].trim(), paramsSplit[1].trim(), paramsSplit[2].trim());
            } else {
                throw new BadCommandException("I'm sorry, but I don't know what that means :-(");
            }

            if (newTask != null) {
                tasks.addTask(newTask);
                ui.showNormalMessage(String.format(
                        "Got it. I've added this task:\n\t\t%s\n\tNow you have %d task%s in the list.",
                        newTask,
                        tasks.getSize(),
                        tasks.getSize() > 1 ? "s" : ""
                ));
            }
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }
}
