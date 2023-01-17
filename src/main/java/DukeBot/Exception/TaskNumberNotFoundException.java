package DukeBot.Exception;

public class TaskNumberNotFoundException extends DukeException {
    public TaskNumberNotFoundException() {
        super("\n" + "    ____________________________________________________________\n" +
                "Either there is no task with that number or you didn't format your command correctly!" + "\n" +
                "Use \"mark N\" or \"unmark N\" where N is your task number." + "\n" +
                "    ____________________________________________________________\n");
    }
}
