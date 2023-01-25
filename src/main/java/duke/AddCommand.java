package duke;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand (String[] fullCommand) throws DukeEmptyArgumentException, DukeInvalidArgumentException {
        try {
            this.task = createTask(fullCommand[0], fullCommand[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeEmptyArgumentException("The description of " + fullCommand[0] + " command cannot be empty.");
        }
    }

    public Task createTask(String cmd, String description) throws DukeInvalidArgumentException {
        try {
            Task task = null;
            switch (cmd) {
            case "todo":
                task = new ToDos(description);
                break;
            case "deadline":
                String[] s1 = description.split("/by ", 2);
                task = new Deadlines(s1[0], s1[1]);
                break;
            case "event":
                String[] s2 = description.split("/from ", 2);
                String[] s3 = s2[1].split(" /to ", 2);
                task = new Events(s2[0], s3[0], s3[1]);
            }
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidArgumentException("The description of " + cmd + " is invalid.");
        }
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList task, Ui ui, Storage storage) throws DukeIOException {
        task.add(this.task);
        storage.updateData(this.task);
        ui.responseToLAddTaskCommand(this.task, task);
    }
}
