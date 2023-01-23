package duke.parser;

import duke.command.*;
import duke.exception.InvalidInputException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TodoTask;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Decipherer {
    private static final Pattern emptyStringChecker = Pattern.compile("\\S.*+");

    public static UpdateCommand updateDecoder(String information) throws InvalidInputException {
        String[] info = information.split(" ");
        String index = info[0];
        StringBuilder description = new StringBuilder();
        if (info.length > 1) {
            for (int i = 1; i < info.length; i ++) {
                description.append(" ").append(info[i]);
            }
        }
        Matcher numberChecker = Pattern.compile("\\d+?").matcher(info[0]);
        String newDescription = String.valueOf(description).strip();

        if (numberChecker.matches()) {
            if (!emptyStringChecker.matcher(newDescription).matches()) {
                throw new InvalidInputException("OOPS!!! The description cannot be empty.");
            } else {
                return new UpdateCommand(Integer.parseInt(index) - 1, newDescription);
            }
        } else {
            throw new InvalidInputException("OOPS!!! The input task index is not a number,\n"
                    + "Please input a valid task index");
        }
    }

    public static MarkAsDoneCommand markDecoder(String information) throws InvalidInputException {
        Matcher numberChecker = Pattern.compile("\\d+?").matcher(information);
        if (numberChecker.matches()) {
            return new MarkAsDoneCommand(Integer.parseInt(information) - 1);
        } else {
            throw new InvalidInputException("OOPS!!! The input task index is not a number,\n"
                    + "Please input a valid task index");
        }
    }

    public static UnmarkCommand unmarkDecoder(String information) throws InvalidInputException {
        Matcher numberChecker = Pattern.compile("\\d+?").matcher(information);
        if (numberChecker.matches()) {
            return new UnmarkCommand(Integer.parseInt(information) - 1);
        } else {
            throw new InvalidInputException("OOPS!!! The input task index is not a number,\n"
                    + "Please input a valid task index");
        }
    }

    public static DeleteCommand deleteDecoder(String information) throws InvalidInputException {
        Matcher numberChecker = Pattern.compile("\\d+?").matcher(information);
        if (numberChecker.matches()) {
            return new DeleteCommand(Integer.parseInt(information) - 1);
        } else {
            throw new InvalidInputException("OOPS!!! The input task index is not a number,\n"
                    + "Please input a valid task index");
        }
    }

    public static AddTaskCommand todoDecoder(String information) throws InvalidInputException {
        if (!emptyStringChecker.matcher(information).matches()) {
            throw new InvalidInputException("OOPS!!! The description of a todo cannot be empty.");
        } else {
            return new AddTaskCommand(new TodoTask(information));
        }
    }

    public static AddTaskCommand deadlineDecoder(String information) throws InvalidInputException {
        if (!emptyStringChecker.matcher(information).matches()) {
            throw new InvalidInputException("OOPS!!! The description of a deadline cannot be empty.");
        } else {
            Matcher dateChecker = Pattern.compile("(?<name>.*)/by(?<date>.*)").matcher(information);
            if (dateChecker.matches()) {
                String name = dateChecker.group("name").strip();
                String date = dateChecker.group("date").strip();
                try {
                    return new AddTaskCommand(new DeadlineTask(name, LocalDate.parse(date)));
                } catch (DateTimeParseException e) {
                    throw new InvalidInputException("OOPS!!! The input date format is invalid\n"
                            + "Please input the date in the format of yyyy-mm-dd");
                }
            } else {
                throw new InvalidInputException("OOPS!!! Please input the deadline in the correct format.");
            }
        }
    }

    public static AddTaskCommand eventDecoder(String information) throws InvalidInputException {
        if (!emptyStringChecker.matcher(information).matches()) {
            throw new InvalidInputException("OOPS!!! The description of a event cannot be empty.");
        } else {
            Matcher intervalChecker = Pattern.compile("(?<name>.*)/from(?<from>.*)/to(?<to>.*)")
                    .matcher(information);
            if (intervalChecker.matches()) {
                String name = intervalChecker.group("name").strip();
                String from = intervalChecker.group("from").strip();
                String to = intervalChecker.group("to").strip();
                try {
                    return new AddTaskCommand(
                            new EventTask(name, LocalDate.parse(from), LocalDate.parse(to)));
                } catch (DateTimeParseException e) {
                    throw new InvalidInputException("OOPS!!! The input date format is invalid\n"
                            + "Please input the date in the format of yyyy-mm-dd");
                }
            } else {
                throw new InvalidInputException("OOPS!!! Please input the event in the correct format.");
            }
        }
    }

    public static FindCommand findDecoder(String information) throws InvalidInputException {
        if (!emptyStringChecker.matcher(information).matches()) {
            throw new InvalidInputException("OOPS!!! The description of a todo cannot be empty.");
        } else {
            String[] descriptions = information.split(" ");
            return new FindCommand(descriptions);
        }
    }

    public static SearchCommand searchDecoder(String information) throws InvalidInputException {
        if (!emptyStringChecker.matcher(information).matches()) {
            throw new InvalidInputException("OOPS!!! The description of a todo cannot be empty.");
        } else {
            try {
                return new SearchCommand(LocalDate.parse(information));
            } catch (DateTimeParseException e) {
                throw new InvalidInputException("OOPS!!! The input date format is invalid\n"
                        + "Please input the date in the format of yyyy-mm-dd");
            }
        }
    }
}
