package kuromi.parser;

import kuromi.command.AddCommand;
import kuromi.command.Command;
import kuromi.command.CommandType;
import kuromi.command.DeleteCommand;
import kuromi.command.ExitCommand;
import kuromi.command.FindCommand;
import kuromi.command.HelpCommand;
import kuromi.command.ListCommand;
import kuromi.command.MarkCommand;
import kuromi.command.MistakesCommand;
import kuromi.command.RemindCommand;
import kuromi.command.UnmarkCommand;
import kuromi.exception.KuromiException;
import kuromi.task.Deadline;
import kuromi.task.Event;
import kuromi.task.TaskList;
import kuromi.task.Todo;
import kuromi.view.Ui;

/**
 * Parser class parses the full user's input to understand what command the user has typed.
 */
public class Parser {
    /**
     * Splits the input string from the user to interpret what command is typed by the user.
     * Returns a Command that is meant by the user.
     * If the input from the user is incomplete, it will throw an error.
     *
     * @param inp Full input from the user.
     * @param ui UI of the current session.
     * @param tasks Tasks for the current session.
     * @return Command Type.
     * @throws KuromiException If user input is incomplete.
     */
    public static Command parse(String inp, Ui ui, TaskList tasks) throws KuromiException {
        String[] temp = Parser.getInputSplit(inp);
        Parser.isValidCommand(temp);
        CommandType c = Parser.getCommandName(temp);
        try {
            switch (c) {
            case bye:
                return Parser.exitCommand(temp);
            case help:
                return Parser.helpCommand(temp);
            case mistakes:
                return Parser.mistakesCommand(temp);
            case list:
                return Parser.listCommand(temp);
            case remind:
                return Parser.remindCommand(temp);
            case find:
                return Parser.findCommand(temp);
            case mark:
                return Parser.markCommand(temp, tasks);
            case unmark:
                return Parser.unmarkCommand(temp, tasks);
            case delete:
                return Parser.deleteCommand(temp, tasks);
            case todo:
                return Parser.todoCommand(temp);
            case deadline:
                return Parser.deadlineCommand(temp);
            case event:
                return Parser.eventCommand(temp);
            default:
                throw new KuromiException("");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new KuromiException("OOPS!!! The index cannot be empty.");
        } catch (NumberFormatException e) {
            throw new KuromiException("OOPS!!! The index should be an number.");
        }
    }

    private static String[] getInputSplit(String inp) {
        return inp.split(" ");
    }

    private static void isValidCommand(String[] temp) throws KuromiException {
        boolean isExist = false;
        CommandType[] types = CommandType.values();
        for (CommandType type: types) {
            if (type.name().equals(temp[0])) {
                isExist = true;
            }
        }
        if (!isExist) {
            throw new KuromiException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static CommandType getCommandName(String[] temp) {
        return CommandType.valueOf(temp[0]);
    }

    private static Command exitCommand(String[] temp) throws KuromiException {
        if (temp.length > 1) {
            throw new KuromiException("OOPS!!! I don't understand what you mean :(\nDo you mean 'bye'?");
        }
        return new ExitCommand();
    }

    private static Command helpCommand(String[] temp) throws KuromiException {
        if (temp.length > 1) {
            throw new KuromiException("OOPS!!! I don't understand what you mean :(\nDo you mean 'help'?");
        }
        return new HelpCommand();
    }

    private static Command mistakesCommand(String[] temp) throws KuromiException {
        if (temp.length > 1) {
            throw new KuromiException("OOPS!!! I don't understand what you mean :(\nDo you mean 'mistakes'?");
        }
        return new MistakesCommand();
    }

    private static Command listCommand(String[] temp) throws KuromiException {
        if (temp.length > 1) {
            throw new KuromiException("OOPS!!! I don't understand what you mean :(\nDo you mean 'list'?");
        }
        return new ListCommand();
    }

    private static Command remindCommand(String[] temp) throws KuromiException {
        if (temp.length == 1) {
            return new RemindCommand(5);
        }
        int idx = Integer.parseInt(temp[1]);
        if (idx == 0) {
            throw new KuromiException("OOPS!!! I'm not reminding you anything!\n(Your command: 0 task)");
        }
        return new RemindCommand(idx);
    }
    private static Command findCommand(String[] temp) throws KuromiException {
        String keyword = "";
        for (int i = 1; i < temp.length; i++) {
            keyword += temp[i];
            if (i != temp.length - 1) {
                keyword += " ";
            }
        }
        if (keyword.equals("")) {
            throw new KuromiException("OOPS!!! The keyword cannot be empty.");
        }
        if (keyword.contains(" ")) {
            throw new KuromiException("OOPS!!! The keyword can only contain 1 word.");
        }
        return new FindCommand(keyword);
    }

    private static Command markCommand(String[] temp, TaskList tasks) throws KuromiException {
        int idx = Integer.parseInt(temp[1]);
        if (idx > tasks.size()) {
            throw new KuromiException("OOPS!!! The index does not exist.");
        }
        return new MarkCommand(idx);
    }

    private static Command unmarkCommand(String[] temp, TaskList tasks) throws KuromiException {
        int idx2 = Integer.parseInt(temp[1]);
        if (idx2 > tasks.size()) {
            throw new KuromiException("OOPS!!! The index does not exist.");
        }
        return new UnmarkCommand(idx2);
    }

    private static Command deleteCommand(String[] temp, TaskList tasks) throws KuromiException {
        int idx3 = Integer.parseInt(temp[1]);
        if (idx3 > tasks.size()) {
            throw new KuromiException("OOPS!!! The index does not exist.");
        }
        return new DeleteCommand(idx3);
    }

    private static Command todoCommand(String[] temp) throws KuromiException {
        String desc = "";
        for (int i = 1; i < temp.length; i++) {
            desc += temp[i];
            if (i != temp.length - 1) {
                desc += " ";
            }
        }
        if (desc.equals("")) {
            throw new KuromiException("OOPS!!! The description of a todo cannot be empty.");
        }
        return new AddCommand(new Todo(desc));
    }

    private static Command deadlineCommand(String[] temp) throws KuromiException {
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
        if (desc2.equals("") || desc2.startsWith("/by")) {
            throw new KuromiException("OOPS!!! The description of a deadline cannot be empty.");
        }
        String by = "";
        for (int i = counter; i < temp.length; i++) {
            by += temp[i];

            if (i != temp.length - 1) {
                by += " ";
            }
        }
        if (by.equals("") || desc2.contains("/by")) {
            throw new KuromiException("OOPS!!! The deadline of a deadline cannot be empty.");
        }
        return new AddCommand(new Deadline(desc2, by));
    }

    private static Command eventCommand(String[] temp) throws KuromiException {
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
        if (desc3.equals("") || desc3.startsWith("/from")) {
            throw new KuromiException("OOPS!!! The description of an event cannot be empty.");
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
        if (from.equals("") || desc3.contains("/from") || from.startsWith("/to")) {
            throw new KuromiException("OOPS!!! The start date of an event cannot be empty.");
        }
        String by2 = "";
        for (int i = counter3; i < temp.length; i++) {
            by2 += temp[i];
            if (i != temp.length - 1) {
                by2 += " ";
            }
        }
        if (by2.equals("") || from.contains("/to")) {
            throw new KuromiException("OOPS!!! The end date of an event cannot be empty.");
        }
        return new AddCommand(new Event(desc3, by2, from));
    }
}
