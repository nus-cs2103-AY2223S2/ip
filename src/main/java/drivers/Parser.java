package drivers;

import java.time.format.DateTimeParseException;

import support.Commands;
import support.DukeException;
import support.Instructions;
import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;


/**
 * Mechanism that interprets command given by user.
 */

public class Parser {

    // default constructor


    /**
     * Returns a boolean value to indicate if program should continue expecting inputs.
     * Executes command given.
     *
     * @param userinput string to be read as command for execution
     * @return whether program should continue
     */
    public String readCommand(String userinput, TaskList l) {
        try {
            userinput = userinput.toLowerCase();
            String[] parts = userinput.split(" ");
            Commands command = Commands.valueOf(parts[0]);

            switch (command) {
            case bye:
                return "Roger. Agent Bond signing off ~";

            case missions:
                return l.print();

            case unmark:
                return l.unmark(Integer.parseInt(parts[1]));

            case mark:
                return l.mark(Integer.parseInt(parts[1]));

            case todo:
                assert userinput.length() > 4: "Wrong instruction passed in";
                return l.add(new Todo(userinput));

            case deadline:
                assert userinput.length() > 8 : "Wrong instruction passed in";
                parts = userinput.split("/");
                if (parts.length != 2) {
                    throw new DukeException("Please enter valid end date.");
                }
                return l.add(new Deadline(parts[0], parts[1]));

            case event:
                assert userinput.length() > 5 : "Wrong instruction passed in";
                parts = userinput.split("/");
                if (parts.length != 3) {
                    throw new DukeException("Please enter valid start and end dates.");
                }
                return l.add(new Event(parts[0], parts[1], parts[2]));

            case delete:
                return l.delete(Integer.parseInt(parts[1]));

            case find:
                return l.find(parts[1]);

            case help:
                return "B: " + Instructions.generate();

            default:
                assert false;
                return "Something has gone wrong!";
            }
        } catch (DukeException e) {
            return "B: " + e.getMessage();
        } catch (DateTimeParseException e) {
            return "B: Please enter a valid date format";
        } catch (IndexOutOfBoundsException e) {
            return "B: Please key in a valid command. Enter 'help' for instructions.";
        } catch (NumberFormatException e) {
            return "B: Please key in an integer number";
        } catch (IllegalArgumentException e) {
            return "B: Command not recognised. Please re-try";
        }
    }
}
