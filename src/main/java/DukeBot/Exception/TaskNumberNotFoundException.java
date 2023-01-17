package DukeBot.Exception;

public class TaskNumberNotFoundException extends DukeException {
    public TaskNumberNotFoundException() {
        super("\n" + "    ____________________________________________________________\n" +
                "Either there is no task with that number or you didn't format your command correctly!" + "\n" +
                "Use \"mark N\", \"unmark N\" or \"delete N\" where N is your task number." + "\n" +
                "    ____________________________________________________________\n");
    }
}
