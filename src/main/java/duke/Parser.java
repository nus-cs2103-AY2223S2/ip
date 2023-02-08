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
            if (instruction[0].equals("list")) { // printing list
                return new ListCommand();

            } else if (instruction[0].equals("mark")) { //marking

                if (instruction.length <= 1) {
                    throw new ArgumentException("What are we marking again?");
                } else if (!instruction[1].matches("[0-9]+")) {
                    throw new ArgumentException("What are we marking again?");
                }
                int index = Integer.parseInt(instruction[1]);

                if ((index - 1) < 0 || (index - 1) >= tasklist.getNumberOfTask()) {
                    throw new ArgumentException("Can't find the index");
                }

                return new MarkCommand(index);

            } else if (instruction[0].equals("unmark")) { //unmarking

                if (instruction.length <= 1) {
                    throw new ArgumentException("What are we unmarking again?");
                } else if (!instruction[1].matches("[0-9]+")) {
                    throw new ArgumentException("What are we unmarking again?");
                }
                int index = Integer.parseInt(instruction[1]);

                if ((index - 1) < 0 || (index - 1) >= tasklist.getNumberOfTask()) {
                    throw new ArgumentException("Can't find the index");
                }

                return new UnmarkCommand(index);

            } else if (instruction[0].equals("delete")) {

                if (instruction.length <= 1) {
                    throw new ArgumentException("What are we deleting again?");
                } else if (!instruction[1].matches("[0-9]+")) {
                    throw new ArgumentException("What are we deleting again?");
                }
                int index = Integer.parseInt(instruction[1]);

                if ((index - 1) < 0 || (index - 1) >= tasklist.getNumberOfTask()) {

                    throw new ArgumentException("Can't find the index");
                }

                return new DeleteCommand(index);

            } else if (instruction[0].equals("bye")) {
                return new ExitCommand();

            } else if (instruction[0].equals("find")) {
                return new FindCommand(instruction[1]);
            } else { // adding into list
                String command = instruction[0];

                if (!command.equals("todo") && !command.equals("deadline") &&
                        !command.equals("event") && !command.equals("delete")) {
                    throw new ArgumentException("*sigh* No such commands, please be serious.");
                }

                if (instruction.length == 1) {
                    throw new ArgumentException("Yeah let's keep wasting each other time." +
                            " You're missing the parameters in case you don't already know.");
                }

                String item = instruction[1];
                if (item.trim().isEmpty()) {
                    throw new ArgumentException("Spacing out already?");
                }

                if (command.equals("todo")) {

                    Task task = new Task(item, "T");
                    return new AddCommand(task);

                } else if (command.equals("deadline")) {
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
                    return new AddCommand(deadline);

                } else {

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

                    Task task = new Event(nameItem, "E", startDate, endDate, startEnd[0], startEnd[1]);
                    return new AddCommand(task);
                }
            }

        } catch (ArgumentException ex2) {
            ui.printText(ex2.getMessage());
        } catch (ParseException e) {
           ui.printText("Please enter the time of the format dd/MM/yyyy HH:mm");
        }

        return new InvalidCommand();
    }

}
