package skittles.instruction;
import skittles.Data;
import skittles.Deadline;
import skittles.SkittlesException;
import skittles.Event;
import skittles.ListOfStuff;
import skittles.ToDo;
import skittles.Duke;
import skittles.Task;

public class AddInstruction extends Instruction {
    private final ListOfStuff skittlesList;
    private final Data data;
    private final String taskCategory;

    /**
     * Constructor for AddCommand
     * @param skittlesList list of Tasks
     * @param data Data of Tasks
     * @param taskCategory Task category itself
     */
    public AddInstruction(ListOfStuff skittlesList, Data data, String taskCategory) {
        this.skittlesList = skittlesList;
        this.data = data;

        assert taskCategory.matches("todo|event|deadline");

        this.taskCategory = taskCategory;
    }

    @Override
    public String getResponse(String input) {
        switch (taskCategory) {
            case "deadline":
                return addTimeSensitive(input);
            case "event":
                return addAnEvent(input);
            case "todo":
                return addAToDo(input);
            default:
                return "";
        }
    }

    /**
     * Adds a Deadline to all Tasks that Skittles has stored.
     * @param userTyped The entire String that the user has input i.e. "deadline xxx /by yyy".
     */
    private String addTimeSensitive(String userTyped) {
        // First check if the user has only input the one word "deadline".
        if (userTyped.split(" ", 2).length == 1) {
            return "Hey it looks like you are missing the description and the deadline date!";
        }
        // If "deadline" is entered with more words, check information.
        String[] information = userTyped.split(" ", 2);
        String [] description = information[1].split("/by ", 2);
        //In the case where date is not entered.
        if (description.length == 1) {
            return "Hey you are missing the deadline date! Please attempt again.";
        }
        Deadline newDeadline = new Deadline(description[0], description[1]);
        try {
            Data.addInsideFile(newDeadline);
        } catch (SkittlesException e) {
            return e.getMessage();
        }
        skittlesList.add(newDeadline);
        return "Got it. I've added this task:\n" + newDeadline.toString() + "\nNow you have " + ListOfStuff.numOfThings() + " tasks in the list";
    }

    /**
     * Adds an Event to all Tasks that Skittles has stored.
     * @param userTyped The entire String that the user has input i.e. "event xxx /from yyy /to zzz".
     */
    private String addAnEvent(String userTyped) {
        //Start by checking if user only inputted one word "event"
        if (userTyped.split(" ",2).length == 1) {
            return "Hey you didn't type the event and the time range!";
        }

        String[] generalInfo = userTyped.split(" ",2);
        String[] eventAndEntireTimeRange= generalInfo[1].split(" /from ",2);
        String[] startAndEndTime = eventAndEntireTimeRange[1].split(" /to ",2);

        //next possibility the word "event" is typed with actual event but no time range
        if (userTyped.split(" ",2)[1].split(" /from ",2).length == 1) {
            return "Hey looks like you're missing a time range!";
        }

        String actualEvent = eventAndEntireTimeRange[0];
        String startTime = startAndEndTime[0];
        String endTime = startAndEndTime[1];
        Event suitAndTie = new Event(actualEvent, startTime, endTime);
        skittlesList.add(suitAndTie);
        try {
            Data.addInsideFile(suitAndTie);
        } catch (SkittlesException e) {
            return e.getMessage();
        }
        return "Got it. I've added this task:\n" + suitAndTie.toString() +
                "\nNow you have " + ListOfStuff.numOfThings() + " tasks in the list";
    }

    /**
     * Adds a Todo to all Tasks that Skittles has stored.
     * @param userTyped The entire String that the user has input i.e. "todo xxx".
     */
    private String addAToDo(String userTyped) {
        //firstly we check if the user only inputted one word "todo"
        if (userTyped.split(" ",2).length == 1) {
            return "Hey you didn't include a todo!";
        }
        //otherwise if todo is inputted with other words, check the info
        String[] generalInfo = userTyped.split(" ",2);
        ToDo mustDo = new ToDo(generalInfo[1]);
        skittlesList.add(mustDo);
        try {
            Data.addInsideFile(mustDo);
        } catch (SkittlesException e) {
            return e.getMessage();
        }
        return "Got it. I've added this task:\n" + mustDo.toString() +
                "\nNow you have " + ListOfStuff.numOfThings() + " tasks in the list.";
    }

}
