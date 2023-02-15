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
                return goodbyeMessage();

            case missions:
                return printMissions(l);

            case unmark:
                return unmarkTask(l, Integer.parseInt(parts[1]));

            case mark:
                return markTask(l, Integer.parseInt(parts[1]));

            case todo:
                return addTodo(l, userinput);

            case deadline:
                return addDeadline(l, userinput);

            case event:
                return addEvent(l, userinput);

            case delete:
                return l.delete(Integer.parseInt(parts[1]));

            case find:
                return l.find(parts[1]);

            case help:
                return Instructions.generate();

            default:
                return impossibleFunction();
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

    private String goodbyeMessage() {
        return "Roger. Agent Bond signing off ~";
    }

    private String printMissions(TaskList t) {
        return t.print();
    }

    private String unmarkTask(TaskList t, int idx) {
        return t.unmark(idx);
    }

    private String markTask(TaskList t, int idx) {
        return t.mark(idx);
    }

    private String addTodo(TaskList t, String label) {
        assert label.length() > 4 : "Wrong instruction passed in";
        return t.add(new Todo(label));
    }

    private String addDeadline(TaskList t, String label) throws DukeException {
        assert label.length() > 8 : "Wrong instruction passed in";
        String[] parts = label.split("/");
        if (parts.length != 2) {
            throw new DukeException("Please enter valid end date.");
        }
        return t.add(new Deadline(parts[0], parts[1]));
    }

    private String addEvent(TaskList t, String label) throws DukeException {
        assert label.length() > 5 : "Wrong instruction passed in";
        String[] parts = label.split("/");
        if (parts.length != 3) {
            throw new DukeException("Please enter valid start and end dates.");
        }
        return t.add(new Event(parts[0], parts[1], parts[2]));
    }

    private String impossibleFunction() {
        assert false;
        return "Something has gone wrong!";
    }
}
