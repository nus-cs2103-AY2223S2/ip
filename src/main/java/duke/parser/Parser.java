package duke.parser;

import duke.exception.*;
import duke.command.*;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TodoTask;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* A parser that parse the input String into a Duke Instruction
* with respective information encapsulated
*/

public class Parser {
    /**
     * A parse method that takes in a String representation of a Command, using
     * regular expression to parse it can construct to a Command object.
     *
     * @param input The given String of Command to be parsed by the parser
     * @return The actual Command objected represented by the given input String
     * @throws DukeException Throws exception when invalid input is given
     */
    public static Command parse(String input) throws DukeException {
        Pattern emptyStringChecker = Pattern.compile("\\S.*+");

        //@@author Yufannnn-reused
        //Reused from https://github.com/wweqg/ip/blob/master/src/main/java/duke/parser/Parser.java
        //with minor modification, it is a pretty clean and concise regular expression for general instructions
        Matcher instructionExtractor = Pattern.
                compile("(?<instructionTag>\\S++)(?<information>.*)").matcher(input.strip());
        //@@author

        if (!instructionExtractor.matches()) {
            throw new InvalidInputException("☹ OOPS!!! The instruction cannot be empty");
        }
        String instructionTag = instructionExtractor.group("instructionTag").strip();
        String information = instructionExtractor.group("information").strip();

        if (instructionTag.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (instructionTag.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (instructionTag.equalsIgnoreCase("mark")) {
            Matcher numberChecker = Pattern.compile("\\d+?").matcher(information);
            if (numberChecker.matches()) {
                return new MarkAsDoneCommand(Integer.parseInt(information) - 1);
            } else {
                throw new InvalidInputException("☹ OOPS!!! The input task index is not a number,\n" +
                        "Please input a valid task index");
            }
        } else if (instructionTag.equalsIgnoreCase("unmark")) {
            Matcher numberChecker = Pattern.compile("\\d+?").matcher(information);
            if (numberChecker.matches()) {
                return new UnmarkCommand(Integer.parseInt(information) - 1);
            } else {
                throw new InvalidInputException("☹ OOPS!!! The input task index is not a number,\n" +
                        "Please input a valid task index");
            }
        } else if(instructionTag.equalsIgnoreCase("delete")) {
            Matcher numberChecker = Pattern.compile("\\d+?").matcher(information);
            if (numberChecker.matches()) {
                return new DeleteCommand(Integer.parseInt(information) - 1);
            } else {
                throw new InvalidInputException("☹ OOPS!!! The input task index is not a number,\n" +
                        "Please input a valid task index");
            }
        } else if (instructionTag.equalsIgnoreCase("todo")) {
            if (!emptyStringChecker.matcher(information).matches()) {
                throw new InvalidInputException("☹ OOPS!!! The description of a todo cannot be empty.");
            } else {
                return new AddTaskCommand(new TodoTask(information));
            }
        } else if (instructionTag.equalsIgnoreCase("deadline")) {
            if (!emptyStringChecker.matcher(information).matches()) {
                throw new InvalidInputException("☹ OOPS!!! The description of a deadline cannot be empty.");
            } else {
                Matcher dateChecker = Pattern.compile("(?<name>.*)/by(?<date>.*)").matcher(information);
                if (dateChecker.matches()) {
                    String name = dateChecker.group("name").strip();
                    String date = dateChecker.group("date").strip();
                    try {
                        return new AddTaskCommand(new DeadlineTask(name, LocalDate.parse(date)));
                    } catch (DateTimeParseException e) {
                        throw new InvalidInputException("☹ OOPS!!! The input date format is invalid\n" +
                                "Please input the date in the format of yyyy-mm-dd");
                    }
                } else {
                    throw new InvalidInputException("☹ OOPS!!! Please input the deadline in the correct format.");
                }
            }
        } else if (instructionTag.equalsIgnoreCase("event")) {
            if (!emptyStringChecker.matcher(information).matches()) {
                throw new InvalidInputException("☹ OOPS!!! The description of a event cannot be empty.");
            } else{
                Matcher intervalChecker = Pattern.compile("(?<name>.*)/from(?<from>.*)/to(?<to>.*)").matcher(information);
                if (intervalChecker.matches()) {
                    String name = intervalChecker.group("name").strip();
                    String from = intervalChecker.group("from").strip();
                    String to = intervalChecker.group("to").strip();
                    try {
                        return new AddTaskCommand(
                                new EventTask(name, LocalDate.parse(from), LocalDate.parse(to)));
                    } catch (DateTimeParseException e) {
                        throw new InvalidInputException("☹ OOPS!!! The input date format is invalid\n" +
                                "Please input the date in the format of yyyy-mm-dd");
                    }
                } else {
                    throw new InvalidInputException("☹ OOPS!!! Please input the event in the correct format.");
                }
            }
        } else if (instructionTag.equalsIgnoreCase("find")) {
            if (!emptyStringChecker.matcher(information).matches()) {
                throw new InvalidInputException("☹ OOPS!!! The description of a todo cannot be empty.");
            } else {
                return new FindCommand(information);
            }
        } else {
            throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
