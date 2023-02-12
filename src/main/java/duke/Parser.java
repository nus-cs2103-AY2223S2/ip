package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

/**
 * This class is to check if user inputs are valid
 */
public class Parser {

    /**
     * Checks if the user input are valid and return a Command corresponding to user input
     * @param input is the user input
     * @param tasklist is where all the tasks are stored
     * @return a new Command object based on the user input
     */
    public Command parse(String input, TaskList tasklist, Ui ui) {

        String[] instruction = input.split(" ", 2);

        try {

            switch (instruction[0]) {
                case "list":
                    return new ListCommand();

                case "mark":
                    int markedIndex = checkMark(instruction, tasklist);
                    return new MarkCommand(markedIndex);

                case "unmark":
                    int unmarkedIndex = checkUnmark(instruction, tasklist);
                    return new UnmarkCommand(unmarkedIndex);

                case "delete":
                    int deletedIndex = checkDelete(instruction, tasklist);
                    return new DeleteCommand(deletedIndex);

                case "find":
                    checkFind(instruction);
                    return new FindCommand(instruction[1]);

                case "todo":
                    checkBasicAddTask(instruction);
                    String item = instruction[1];
                    Task task = new Task(item, "T");
                    return new AddCommand(task);

                case "deadline":
                    checkBasicAddTask(instruction);
                    Deadline deadline = checkDeadline(instruction);
                    return new AddCommand(deadline);

                case "event":
                    checkBasicAddTask(instruction);
                    Event event = checkEvent(instruction);
                    return new AddCommand(event);

                case "bye":
                    return new ExitCommand();

                default:
                    throw new ArgumentException("No such commands");
            }

        } catch (ArgumentException ex2) {
            ui.printText(ex2.getMessage());
        } catch (ParseException e) {
            ui.printText("Please enter the time of the format dd/MM/yyyy HH:mm");
        }

        return new InvalidCommand();
    }

    /**
     * To check if the command index given is valid for mark task
     * @param instruction is the command given by the user
     * @param tasklist to check if the to be mark task exist
     * @return the index of the to be mark task
     * @throws ArgumentException
     */
    public int checkMark(String instruction[], TaskList tasklist) throws ArgumentException {
        if (instruction.length <= 1) {
            throw new ArgumentException("What are we marking again?");
        } else if (!instruction[1].matches("[0-9]+")) {
            throw new ArgumentException("What are we marking again?");
        }
        int index = Integer.parseInt(instruction[1]);

        if ((index - 1) < 0 || (index - 1) >= tasklist.getNumberOfTask()) {
            throw new ArgumentException("Can't find the index");
        }

        return index;
    }

    /**
     * To check if the command index given is valid for unmark task
     * @param instruction is the command given by the user
     * @param tasklist to check if the to be unmark task exist
     * @return the index of the to be unmark task
     * @throws ArgumentException
     */
    public int checkUnmark(String instruction[], TaskList tasklist) throws ArgumentException {
        if (instruction.length <= 1) {
            throw new ArgumentException("What are we unmarking again?");
        } else if (!instruction[1].matches("[0-9]+")) {
            throw new ArgumentException("What are we unmarking again?");
        }
        int index = Integer.parseInt(instruction[1]);

        if ((index - 1) < 0 || (index - 1) >= tasklist.getNumberOfTask()) {
            throw new ArgumentException("Can't find the index");
        }

        return index;
    }

    /**
     * To check if the command index given is valid for delete task
     * @param instruction is the command given by the user
     * @param tasklist to check if the to be deleted task exist
     * @return the index of the to be deleted task
     * @throws ArgumentException
     */
    public int checkDelete(String instruction[], TaskList tasklist) throws ArgumentException {

        if (instruction.length <= 1) {
            throw new ArgumentException("What are we deleting again?");
        } else if (!instruction[1].matches("[0-9]+")) {
            throw new ArgumentException("What are we deleting again?");
        }
        int index = Integer.parseInt(instruction[1]);

        if ((index - 1) < 0 || (index - 1) >= tasklist.getNumberOfTask()) {

            throw new ArgumentException("Can't find the index");
        }

        return index;
    }

    /**
     * Check if the find command is valid
     * @param instruction check if user enter the right command
     * @throws ArgumentException
     */
    public void checkFind(String instruction[]) throws ArgumentException {
        if (instruction.length <= 1) {
            throw new ArgumentException("What are we finding again?");
        }

        if (instruction[1].trim().isEmpty() == true) {
            throw new ArgumentException("What are we finding again?");
        }
    }

    /**
     * Checks if the user enter the task name
     * @param instruction given by the user
     * @throws ArgumentException
     */
    public void checkBasicAddTask(String instruction[]) throws ArgumentException{
        if (instruction.length == 1) {
            throw new ArgumentException("You're missing some of the taskname");
        }

        String item = instruction[1];
        if (item.trim().isEmpty()) {
            throw new ArgumentException("Spacing out already?");
        }
    }


    /**
     * Check if user enter the command right when creating the deadline
     * @param instruction given by the user
     * @return the deadline task
     * @throws ArgumentException
     * @throws ParseException
     */
    public Deadline checkDeadline(String instruction[]) throws ArgumentException, ParseException {
        String item = instruction[1];
        String itemANDtime[] = item.split("/", 2);

        if (itemANDtime.length == 1) {
            throw new ArgumentException("How interesting a deadline without a time limit?");
        } else if (itemANDtime[0].trim().isEmpty()) {
            throw new ArgumentException("Oh? Looks like your item disappeared into space.");
        } else if (itemANDtime[1].trim().isEmpty()) {
            throw new ArgumentException("Hiding from reality I see. Too bad time waits for no man");
        }

        String time[] = itemANDtime[1].split(" ", 2);

        if (!time[0].equals("by")) {
            throw new ArgumentException("Where did your BY go?");
        } else if (time[1].trim().isEmpty()) {
            throw new ArgumentException("A blank time I see");
        }
        String deadlineTime = itemANDtime[1].split(" ", 2)[1];

        SimpleDateFormat converterDate = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = converterDate.parse(deadlineTime);

        Deadline deadline = new Deadline(itemANDtime[0], "D", date, deadlineTime);

        return deadline;
    }

    /**
     * Check if user enter the command right when creating the event
     * @param instruction given by the user
     * @return the event task
     * @throws ArgumentException
     * @throws ParseException
     */
    public Event checkEvent(String instruction[]) throws ArgumentException, ParseException {
        String item = instruction[1];

        if (!item.contains("/from") || !item.contains("/to")) {
            throw new ArgumentException("For event please enter the format: event nameOfEvent /from dd/MM/yyyy HH:mm" +
                    " /to dd/MM/yyyy HH:mm");
        }

        String itemANDtime[] = item.split(" /from ", 2);

        if (itemANDtime.length < 2) {
            throw new ArgumentException("You forget the event name!");
        } else if (itemANDtime[0].trim().isEmpty()) {
            throw new ArgumentException("Oh? Looks like your item disappeared into space.");
        }

        String nameItem = itemANDtime[0];

        String startEnd[] = itemANDtime[1].split(" /to ");

        if (startEnd.length < 2) {
            throw new ArgumentException("Please enter the format: event nameOfEvent /from xxx /to xxx");
        }

        if (startEnd[0].trim().isEmpty() || startEnd[1].trim().isEmpty()) {
            throw new ArgumentException("Please do not leave the timing blank!");
        }

        SimpleDateFormat converterStartDate = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date startDate = converterStartDate.parse(startEnd[0]);
        Date endDate = converterStartDate.parse(startEnd[1]);

        Event event = new Event(nameItem, "E", startDate, endDate, startEnd[0], startEnd[1]);
        return event;

    }

}
