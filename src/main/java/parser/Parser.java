package parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import commands.AddCommand;
import commands.CheckCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.EnumCommand;
import commands.ListTasksCommand;
import commands.MarkCommand;
import exceptions.InvalidDateFormatException;
import exceptions.LoadTaskException;
import exceptions.UnknownTaskException;

/**
 * This class parses user input commands into commands that Duke can understand and execute
 */
public abstract class Parser {

    /**
     * Parses the commandString input into an executable command
     *
     * @param commandString
     * @return executable Command object that performs the action requested by the commandString
     * @throws InvalidDateFormatException
     * @throws UnknownTaskException
     */
    public static Command parseCommand(String commandString) throws InvalidDateFormatException, UnknownTaskException {

        String[] commands = commandString.split(" ");
        String j = commands[0].toUpperCase();
        EnumCommand comm;

        try {
            comm = EnumCommand.valueOf(j);
        } catch (IllegalArgumentException e) {
            throw new UnknownTaskException(j);
        }

        switch(comm) {
        case LIST:
            return new ListTasksCommand();
        case CHECK:
            try {
                return new CheckCommand(LocalDate.parse(commands[1]));
            } catch (DateTimeParseException e) {
                throw new InvalidDateFormatException(e.getParsedString());
            }
        case MARK:
        case UNMARK:
        case DELETE:
            int index = Integer.parseInt(commands[1]) - 1;
            switch(comm) {
            case MARK:
                return new MarkCommand(true, index);
            case UNMARK:
                return new MarkCommand(false, index);
            case DELETE:
                return new DeleteCommand(index);
            default:
                break; // will not reach here
            }
            throw new UnknownTaskException(j); // will not reach here
        default:
            String[] commandDetails = new String[4];
            commandDetails[0] = comm.toString();

            String taskString = "";
            for (int i = 1; i < commands.length; i++) {
                taskString += commands[i] + " ";
            }
            String[] args = taskString.split("/");
            for (int i = 0; i < args.length; i++) {
                commandDetails[i + 1] = args[i];
            }
            return new AddCommand(commandDetails);
        }
    }

    /**
     * Parses string input into ParsedDate object
     *
     * @param s string input
     * @return ParsedDate object
     * @throws LoadTaskException
     */
    public static String stringToParsedDateString(String s) throws LoadTaskException {
        String[] dateTime = s.split(" ");
        String mIndex;
        try {
            Date date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(dateTime[0]);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int mth = cal.get(Calendar.MONTH) + 1;
            if (mth < 10) {
                mIndex = "0" + mth;
            } else {
                mIndex = String.valueOf(mth);
            }
        } catch (IllegalArgumentException | NullPointerException | ParseException e) {
            throw new LoadTaskException();
        }

        if (dateTime[1].length() == 1) {
            dateTime[1] = "0" + dateTime[1];
        }
        String dateTimeValue = dateTime[2].substring(0, 4) + "-" + mIndex + "-" + dateTime[1]
                                + "T" + dateTime[4].substring(0, 5);
        return dateTimeValue;
    }
}
