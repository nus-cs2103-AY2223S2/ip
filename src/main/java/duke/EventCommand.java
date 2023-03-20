package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    private final String userInput;
    private final TaskList list;
    private final Storage storage;

    private final String[] splitTimes;


    /**
     * EventCommand that calls the relevant memthods to execute this command
     * @param userInput - the details for the command entered in by the user
     * @param list - the list that stores all the users tasks
     * @param storage - the instance of the Storage class that handles writing to and reading from a text file
     * @param splitTimes an array holding the dates and times for the event
     */
    public EventCommand (String userInput, TaskList list, Storage storage, String[] splitTimes) {
        this.userInput = userInput;
        this.list = list;
        this.storage = storage;
        this.splitTimes = splitTimes;
    }

    /**
     * Method to execute the EventCommand
     * @return String representing the output associated with an EventCommand
     * @throws IOException
     */
    @Override
    public String execute() throws IOException {

        String description = splitTimes[0].substring(6);
        String startDayTime = splitTimes[1];
        String endDayTime = splitTimes[2];
        try {
            String firstOutput = list.addTask(new Event(startDayTime, endDayTime, description));
            String secondOutput = list.getTaskDetails();
            storage.updateStorage();
            return firstOutput + "\n" + secondOutput;
        } catch (DateTimeParseException e) {
            return "The date needs to be in this format: dd-Mmm-yyyy" +
                    " and the time needs to be in this format: HHmm";
        }
    }
}
