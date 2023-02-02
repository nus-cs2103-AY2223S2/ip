package duke.exception;

public class TaskNumberNotFoundException extends DukeException {
    public TaskNumberNotFoundException() {
        super("\n"
                + "Either there is no task with that number or you didn't format your command correctly!" + "\n"
                + "Use \"mark N\", \"unmark N\" or \"delete N\" where N is your task number." + "\n");
    }
}
