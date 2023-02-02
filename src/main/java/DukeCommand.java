public enum DukeCommand {
    BYE("bye"),
    LIST("list"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    MARK("mark"),
    UNMARK("unmark"),
    INVALID("invalid");

    private final String command;
    private String description;

    DukeCommand(String command) {
        this.command = command;
        this.description = "";
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public String getCommand() {
        return command;
    }

    /**
     * Executes the command.
     * @param taskList task list.
     * @param ui ui.
     * @param storage storage.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        switch (this) {
        case BYE:
            ui.printGoodbye();
            break;
        case LIST:
            ui.prettifyOut(taskList.toString());
            break;
        case DELETE:
            int taskToDelete = Integer.parseInt(this.description) - 1;
            try {
                Task deletedTask = taskList.deleteTask(taskToDelete);
                ui.printTaskDeleted(deletedTask, taskList.getSize());
                storage.save(taskList);
            } catch (DukeException e) {
                ui.printDukeException(e);
            }
            break;
        case TODO:
            try {
                ToDo todo = Parser.parseTodo(this.description);
                taskList.addTask(todo);
                ui.printTaskAdded(todo, taskList.getSize());
                storage.save(taskList);
            } catch (DukeException e) {
                ui.printDukeException(e);
            }
            break;
        case DEADLINE:
            try {
                Deadline deadline = Parser.parseDeadline(this.description);
                taskList.addTask(deadline);
                ui.printTaskAdded(deadline, taskList.getSize());
                storage.save(taskList);
            } catch (DukeException e) {
                ui.printDukeException(e);
            }
            break;
        case EVENT:
            try {
                Event event = Parser.parseEvent(this.description);
                taskList.addTask(event);
                ui.printTaskAdded(event, taskList.getSize());
                storage.save(taskList);
            } catch (DukeException e) {
                ui.printDukeException(e);
            }
            break;
        case MARK:
            try {
                int taskToMark = Integer.parseInt(this.description) - 1;
                Task markedTask = taskList.markTaskDone(taskToMark);
                ui.printTaskMarked(markedTask);
                storage.save(taskList);
            } catch (DukeException e) {
                ui.printDukeException(e);
            }
            break;
        case UNMARK:
            try {
                int taskToUnmark = Integer.parseInt(this.description) - 1;
                Task unmarkedTask = taskList.markTaskUndone(taskToUnmark);
                ui.printTaskUnmarked(unmarkedTask);
                storage.save(taskList);
            } catch (DukeException e) {
                ui.printDukeException(e);
            }
            break;
        case INVALID:
            ui.prettifyOut("Invalid command!");
            break;
        }
    }

    public static DukeCommand getCommand(String command) {
        for (DukeCommand c : DukeCommand.values()) {
            if (c.getCommand().equals(command.split(" ")[0])) {
                if (c != LIST && c != BYE) c.setDescription(command.split(" ", 2)[1].trim());
                return c;
            }
        }
        return INVALID;
    }
}
