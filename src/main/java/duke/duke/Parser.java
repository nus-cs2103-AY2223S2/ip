package duke.duke;

import java.util.ArrayList;
import java.util.List;

import duke.commands.*;
import duke.exceptions.DukeException;
import duke.exceptions.IncompleteException;
import duke.exceptions.InvalidException;
import duke.exceptions.NoArgsException;
import duke.exceptions.UnrecognisableException;
import duke.tasks.Deadlines;
import duke.tasks.Events;
import duke.tasks.Todos;

/**
 * A class that interprets user-input.
 * Delegates appropriate commands to handling inputs.
 */
public class Parser {

    private final String filePath;
    public Parser(String filePath) {
        this.filePath = filePath;
    }

    public String readCommand(String[] readLine) {
        return readLine[0];
    }

    /**
     * @param line A string array split by 1 whitespace when the input is read.
     * @return A useful integer value, most often the index of the task list.
     * @throws DukeException If the string cannot be converted into an integer.
     */
    public int singleQueryInteger(String[] line) throws DukeException {
        int s;
        try {
            s = Integer.valueOf(line[1]);
        } catch (NumberFormatException arr) {
            throw new InvalidException();
        } catch (Exception err) {
            throw new IncompleteException();
        }
        return s;
    }
    /**
     * Parses a processed form of the user input and matches it to the correct command.
     * @param line A string array split by 1 whitespace when the input is read.
     * @return An appropriate command.
     * @throws DukeException
     */
    public Command parse(String[] line) throws DukeException {
        Command c;
        ArrayList<String> queries;
        String joined = String.join("", line);
        switch(this.readCommand(line)) {
        case "list":
            c = new ListCommand();
            break;
        case "delete":
            c = new DeleteCommand(this.singleQueryInteger(line));
            break;
        case "mark":
            c = new MarkCommand(this.singleQueryInteger(line), true);
            break;
        case "unmark":
            c = new MarkCommand(this.singleQueryInteger(line), false);
            break;
        case "todo":
            if (line.length == 1) {
                throw new NoArgsException("deadline");
            }
            String description = this.queries(line, Todos.KEYWORDS).get(0);
            c = new TodoCommand(new Todos(description));
            break;
        case "event":
            if (line.length == 1) {
                throw new NoArgsException("deadline");
            } else if (joined.split("/").length != 3) {
                throw new InvalidException();
            }
            queries = this.queries(line, Events.KEYWORDS);
            Events event = new Events(queries);
            c = new EventCommand(event);
            break;
        case "deadline":
            if (line.length == 1) {
                throw new NoArgsException("deadline");
            } else if (joined.split("/").length != 2) {
                throw new InvalidException();
            }
            queries = this.queries(line, Deadlines.KEYWORDS);
            c = new DeadLineCommand(new Deadlines(queries));
            break;
        case "bye":
            c = new ByeCommand();
            assert c.isBye();
            break;
        case "find":
            if (line.length == 1) {
                throw new NoArgsException("deadline");
            }
            String query = this.queries(line, List.<String>of()).get(0);
            c = new FindCommand(query);
            break;

        case "undo":
            int index;
            if (line.length == 1) {
                index = 1;
            } else {
                index = this.singleQueryInteger(line);
            }
            if (index < 0) {
                throw new InvalidException();
            }
            c = new UndoCommand(index, this.filePath);
            break;

        default:
            throw new UnrecognisableException();
        }
        return c;
    }

    /**
     * Obtains a set of queries/arguments for commands.
     * @param readLine A string array split by 1 whitespace when the input is read.
     * @param keywords A list of keywords associated with the task class, such as "from".
     * @return an array-list of queries.
     * @throws DukeException
     */
    public ArrayList<String> queries(
            String[] readLine, List<String> keywords) throws DukeException {
        ArrayList<String> arr = new ArrayList<>();

        try {
            int index = 0;
            String concat = "";

            for (int i = 1; i < readLine.length; i++) {
                String s = readLine[i];
                if (s.charAt(0) != '/') {
                    concat += s + " ";

                } else if (s.substring(1).equals(keywords.get(index))) {
                    arr.add(concat.substring(0, concat.length() - 1));
                    concat = "";
                    index++;

                } else {
                    throw new InvalidException();
                }
            }
            arr.add(concat.substring(0, concat.length() - 1));

        } catch (Exception err) {
            throw new InvalidException();
        }

        return arr;
    }


}
