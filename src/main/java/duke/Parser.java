package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.CommandType;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Parser class parses the full user's input to understand what command the user has typed.
 */
public class Parser {
    /**
     * Split the input string from the user to interpret what command is typed by the user.
     * Returns a Command that is meant by the user.
     * If the input from the user is incomplete, it will throw an error.
     *
     * @param inp Full input from the user.
     * @param ui UI of the current session.
     * @param tasks Tasks for the current session.
     * @return Command Type.
     * @throws DukeException If user input is incomplete.
     */
    public static Command parse(String inp, Ui ui, TaskList tasks) throws DukeException {
        String[] temp = inp.split(" ");
        CommandType c;
        boolean isExist = false;
        CommandType[] types = CommandType.values();
        for (CommandType type: types) {
            if (type.name().equals(temp[0])) {
                isExist = true;
            }
        }
        if (!isExist) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        c = CommandType.valueOf(temp[0]);
        Command cur = new ExitCommand();

        try {
            switch (c) {
            case bye:
                cur = new ExitCommand();
                break;
            case list:
                cur = new ListCommand();
                break;
            case find:
                String keyword = "";
                for (int i = 1; i < temp.length; i++) {
                    keyword += temp[i];
                    if (i != temp.length - 1) {
                        keyword += " ";
                    }
                }
                if (keyword.equals("")) {
                    throw new DukeException("☹ OOPS!!! The keyword cannot be empty.");
                }
                cur = new FindCommand(keyword);
                break;
            case mark:
                int idx = Integer.parseInt(temp[1]);
                if (idx > tasks.size()) {
                    throw new DukeException("☹ OOPS!!! The index does not exist.");
                }
                cur = new MarkCommand(idx);
                break;
            case unmark:
                int idx2 = Integer.parseInt(temp[1]);
                if (idx2 > tasks.size()) {
                    throw new DukeException("☹ OOPS!!! The index does not exist.");
                }
                cur = new UnmarkCommand(idx2);
                break;
            case delete:
                int idx3 = Integer.parseInt(temp[1]);
                if (idx3 > tasks.size()) {
                    throw new DukeException("☹ OOPS!!! The index does not exist.");
                }
                cur = new DeleteCommand(idx3);
                return cur;
            case todo:
                String desc = "";
                for (int i = 1; i < temp.length; i++) {
                    desc += temp[i];
                    if (i != temp.length - 1) {
                        desc += " ";
                    }
                }
                if (desc.equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                cur = new AddCommand(new Todo(desc));
                break;
            case deadline:
                String desc2 = "";
                int counter = temp.length;
                for (int i = 1; i < temp.length; i++) {
                    desc2 += temp[i];
                    if ((i + 1 < temp.length - 1) && temp[i + 1].equals("/by")) {
                        counter = i + 2;
                        break;
                    }
                    if (i != temp.length - 1) {
                        desc2 += " ";
                    }
                }
                if (desc2.equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                String by = "";
                for (int i = counter; i < temp.length; i++) {
                    by += temp[i];

                    if (i != temp.length - 1) {
                        by += " ";
                    }
                }
                if (by.equals("")) {
                    throw new DukeException("☹ OOPS!!! The deadline of a deadline cannot be empty.");
                }
                cur = new AddCommand(new Deadline(desc2, by));
                break;
            case event:
                String desc3 = "";
                int counter2 = temp.length;
                for (int i = 1; i < temp.length; i++) {
                    desc3 += temp[i];
                    if ((i + 1 < temp.length - 1) && temp[i + 1].equals("/from")) {
                        counter2 = i + 2;
                        break;
                    }
                    if (i != temp.length - 1) {
                        desc3 += " ";
                    }
                }
                if (desc3.equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                }
                String from = "";
                int counter3 = temp.length;
                for (int i = counter2; i < temp.length; i++) {
                    from += temp[i];
                    if ((i + 1 < temp.length - 1) && temp[i + 1].equals("/to")) {
                        counter3 = i + 2;
                        break;
                    }
                    if (i != temp.length - 1) {
                        from += " ";
                    }
                }
                if (from.equals("")) {
                    throw new DukeException("☹ OOPS!!! The start date of a deadline cannot be empty.");
                }
                String by2 = "";
                for (int i = counter3; i < temp.length; i++) {
                    by2 += temp[i];
                    if (i != temp.length - 1) {
                        by2 += " ";
                    }
                }
                cur = new AddCommand(new Event(desc3, by2, from));
                if (by2.equals("")) {
                    throw new DukeException("☹ OOPS!!! The end date of a deadline cannot be empty.");
                }
                break;
            default:
                throw new DukeException("");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The index cannot be empty.");
        }

        return cur;
    }
}
