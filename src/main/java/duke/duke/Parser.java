package duke.duke;

import duke.tasks.Deadlines;
import duke.tasks.Events;
import duke.commands.*;
import duke.exceptions.*;

import java.util.ArrayList;
import java.util.List;
public class Parser {

    Parser() {

    }



    public String readCommand(String[] readLine) {
        return readLine[0];
    }

    public int singleQueryInteger(String[] readLine) throws DukeException {
        int s;
        try {
            s = Integer.valueOf(readLine[1]);
        } catch (NumberFormatException arr) {
            throw new InvalidException();
        } catch (Exception err) {
            throw new IncompleteException();
        }
        return s;
    }

    public Command parse(String[] line) throws DukeException {
        Command c;
        ArrayList<String> queries;
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

                String description = this.queries(line, List.<String>of()).get(0);
                c = new TodoCommand(new Todos(description));
                break;
            case "event":
                if (line.length == 1) {
                    throw new NoArgsException("deadline");
                }
                queries = this.queries(line, List.<String>of("from, to"));
                c = new EventCommand(new Events(queries));
                break;

            case "deadline":
                if (line.length == 1) {
                    throw new NoArgsException("deadline");
                }
                queries = this.queries(line, List.<String>of("by"));
                c = new DeadLineCommand(new Deadlines(queries));
                break;
            case "bye":
                c = new ByeCommand();
                break;

            default:
                throw new EmptyException();
        }

        return c;
    }

    public ArrayList<String> queries(
            String[] readLine, List<String> keywords) throws DukeException {
        ArrayList<String> arr = new ArrayList<>();

        try {
            int index = 0;
            String concat = "";

            for (int i = 1; i < readLine.length; i++){
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
