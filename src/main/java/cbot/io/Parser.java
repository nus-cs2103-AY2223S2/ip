package cbot.io;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import cbot.task.Deadline;
import cbot.task.Event;
import cbot.task.Task;
import cbot.task.TaskList;
import cbot.time.TimeStuff;

/**
 * Handles much of the parsing of user inputs.
 */
public class Parser {
    private Command command;
    private String text;

    /**
     * Constructs a new instance to process the user's current input. Attempts to recognize the Command,
     * and the rest of the input body.
     *
     * @param input The user's input.
     * @throws PoorInputException If the input command is not recognized.
     * @see Command
     */
    public Parser(String input) throws PoorInputException {
        this.command = null;
        this.text = "";

        boolean matchFound = false;

        if (input.contains(Task.SEP)) {
            throw new BadInputException("Please avoid using: \"" + Task.SEP + "\"");
        }

        for (Command c : Command.values()) {
            if (c.matches(input)) {
                this.command = c;
                this.text = c.getText(input);
                matchFound = true;
                break;
            }
        }

        if (!matchFound) {
            throw new PoorInputException("Sorry, I don't recognize that command :<");
        }
    }

    /**
     * Returns true if the stored Command is the BYE Command.
     *
     * @return true if the stored Command is BYE.
     */
    public boolean isBye() {
        return (this.command == Command.BYE);
    }

    /**
     * Returns true if the stored Command requires the task list to be saved.
     *
     * @return Whether the task list needs to be saved after the Command.
     */
    public boolean needSave() {
        return this.command.needSave();
    }

    /**
     * Processes the Command and the rest of the input. If needed, the TaskList is modified and the UI is given
     * outputs to print. The main bulk of Cbot's parsing is done here.
     *
     * @param tl The current list of tasks.
     * @return The String response to the command.
     * @throws PoorInputException If the input text is improper or erroneous.
     * @throws DateTimeParseException If some provided datetime is not in a recognized format.
     */
    public String respond(TaskList tl) throws PoorInputException, DateTimeParseException {
        String output;

        if (this.command.isMissingText(this.text)) {
            throw new PoorInputException("That command needs an input");
        }

        switch (this.command) {
        case BYE:
            output = UI.sayBye();
            break;

        case LIST:
            if (tl.getCount() == 0) {
                output = UI.say("Freedom! You have no tasks :D");
            } else {
                output = UI.say("Here's what you have:\n")
                        + UI.printMany(tl.listTasks());
            }
            break;

        case MARK:
            try {
                int num = Integer.parseInt(this.text);

                if (tl.notInRange(num)) {
                    throw new PoorInputException(tl.rangeError(num));
                }

                output = UI.say(tl.mark(num));

            } catch (NumberFormatException e) {
                throw new BadInputException("Invalid index!");
            }
            break;

        case UNMARK:
            try {
                int num = Integer.parseInt(this.text);

                if (tl.notInRange(num)) {
                    throw new PoorInputException(tl.rangeError(num));
                }

                output = UI.say(tl.unmark(num));

            } catch (NumberFormatException e) {
                throw new BadInputException("Invalid index!");
            }
            break;

        case DELETE:
            // Fallthrough
        case REMOVE:
            try {
                int num = Integer.parseInt(this.text);

                if (tl.notInRange(num)) {
                    throw new PoorInputException(tl.rangeError(num));
                }

                output = UI.say(tl.delTask(num));

            } catch (NumberFormatException e) {
                throw new BadInputException("Invalid index!");
            }
            break;

        case TODO:
            output = UI.say(tl.addTask(new Task(this.text)));
            break;

        case DEADLINE:
            String byKeyword = "/by ";
            int byLength = byKeyword.length();

            if (!this.text.contains(byKeyword)) {
                // no /by
                throw new PoorInputException("Missing \"/by\" keyword");
            }

            int byIndex = this.text.indexOf(byKeyword);

            if (byIndex == 0) {
                // no desc
                throw new BadInputException("Missing deadline description");
            } else if (byIndex + byLength == this.text.length()) {
                // no due date
                throw new BadInputException("Missing due date");
            }

            String dlDesc = this.text.substring(0, byIndex);
            String dlDueStr = this.text.substring(byIndex + byLength);

            LocalDateTime dlDue = TimeStuff.parseDT(dlDueStr);
            output = UI.say(tl.addTask(new Deadline(dlDesc, dlDue)));
            break;

        case EVENT:
            String fromKeyword = "/from ";
            String toKeyword = "/to ";
            int fromLength = fromKeyword.length();
            int toLength = toKeyword.length();

            if (!this.text.contains(fromKeyword)) {
                // no /from
                throw new PoorInputException("Missing \"/from\" keyword");
            } else if (!this.text.contains(toKeyword)) {
                // no /to
                throw new PoorInputException("Missing \"/to\" keyword");
            }

            int fromIndex = this.text.indexOf(fromKeyword);
            int toIndex = this.text.indexOf(toKeyword);

            if (toIndex < fromIndex) {
                // /to before /from
                throw new PoorInputException("\"/from\" before \"/to\", please!");
            } else if (fromIndex == 0) {
                // no desc
                throw new BadInputException("Missing event description");
            } else if (fromIndex + fromLength >= toIndex) {
                // no start
                throw new BadInputException("Missing start date");
            } else if (toIndex + toLength == this.text.length()) {
                // no end
                throw new BadInputException("Missing end date");
            }

            String eDesc = this.text.substring(0, fromIndex);
            String eStartStr = this.text.substring(fromIndex + fromLength, toIndex);
            String eEndStr = this.text.substring(toIndex + toLength);

            LocalDateTime eStart = TimeStuff.parseDT(eStartStr);
            LocalDateTime eEnd = TimeStuff.parseDT(eEndStr);

            if (eStart.isAfter(eEnd)) {
                throw new BadInputException("Hey! You have to start *before* you end...");
            }

            output = UI.say(tl.addTask(new Event(eDesc, eStart, eEnd)));

            break;

        case SORT:
            if (tl.getCount() == 0) {
                throw new PoorInputException("You have no tasks to sort :P");
            }

            tl.sort();
            output = UI.say("Okay! I've sorted your tasks by date:\n")
                    + UI.printMany(tl.listTasks());
            break;

        case BEFORE:
            if (tl.getCount() == 0) {
                throw new PoorInputException("Eh? You have no tasks to filter");
            }

            LocalDateTime bef = TimeStuff.parseDT(this.text);

            ArrayList<String> arrBef = tl.listFilter(t ->
                    t.hasTime() && t.compareTo(new Deadline("", bef)) < 0);

            if (arrBef.isEmpty()) {
                output = UI.say("You don't have any tasks before " + this.text);
            } else {
                output = UI.say("Here are your tasks before " + this.text + ":\n")
                        + UI.printMany(arrBef);
            }
            break;

        case AFTER:
            if (tl.getCount() == 0) {
                throw new PoorInputException("Eh? You have no tasks to filter");
            }

            LocalDateTime aft = TimeStuff.parseDT(this.text);

            ArrayList<String> arrAft = tl.listFilter(t ->
                    t.hasTime() && t.compareTo(new Deadline("", aft)) > 0);

            if (arrAft.isEmpty()) {
                output = UI.say("You don't have any tasks after " + this.text);
            } else {
                output = UI.say("Here are your tasks after " + this.text + ":\n")
                        + UI.printMany(arrAft);
            }
            break;

        case FILTER:
            if (tl.getCount() == 0) {
                throw new PoorInputException("Eh? You have no tasks to filter");
            }

            String msg;
            ArrayList<String> arrFilter;

            switch (this.text.toLowerCase()) {
            case "todo":
                // Fallthrough
            case "td":
                // Fallthrough
            case "t":
                msg = "Ok! These are on your ToDo list:\n";
                arrFilter = tl.listFilter(t -> t.getSymbol().equals(Task.TODO_SYMBOL));
                break;

            case "deadline":
                // Fallthrough
            case "dl":
                // Fallthrough
            case "d":
                msg = "Ok! Here are your Deadlines:\n";
                arrFilter = tl.listFilter(t -> t.getSymbol().equals(Deadline.DEADLINE_SYMBOL));
                break;

            case "event":
                // Fallthrough
            case "ev":
                // Fallthrough
            case "e":
                msg = "Ok! Here are your Events:\n";
                arrFilter = tl.listFilter(t -> t.getSymbol().equals(Event.EVENT_SYMBOL));
                break;

            case "complete":
                // Fallthrough
            case "done":
                // Fallthrough
            case "completed":
                // Fallthrough
            case "x":
                msg = "Ok! Here are the Tasks you've completed:\n";
                arrFilter = tl.listFilter(t -> t.getStatus().equals(Task.DONE_TRUE));
                break;

            case "incomplete":
                // Fallthrough
            case "not done":
                // Fallthrough
            case "!done":
                // Fallthrough
            case "undone":
                msg = "Ok! Here are the Tasks you haven't completed yet:\n";
                arrFilter = tl.listFilter(t -> !t.getStatus().equals(Task.DONE_TRUE));
                break;

            default:
                throw new PoorInputException("I'm not sure what Task type that is :(");
            }

            if (arrFilter.isEmpty()) {
                output = UI.say("You don't have any of those :/");
            } else {
                output = UI.say(msg)
                        + UI.printMany(arrFilter);
            }
            break;

        case FIND:
            if (tl.getCount() == 0) {
                throw new PoorInputException("Eh? You have no tasks to filter");
            }

            String lowText = this.text.toLowerCase();

            ArrayList<String> arrFind = tl.listFilter(t ->
                    t.getDesc().toLowerCase().contains(lowText));

            if (arrFind.isEmpty()) {
                output = UI.say("Nope, nothing matches your search!");
            } else {
                output = UI.say("Here! I found these:\n")
                        + UI.printMany(arrFind);
            }
            break;

        default:
            // catches all the BADs
            throw new PoorInputException("That command needs an input");
        }

        return output;
    }
}
