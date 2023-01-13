package duke.parser;

import duke.exception.*;
import duke.instruction.*;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TodoTask;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* A parser that parse the input String into a Duke Instruction
* with respective information encapsulated
*/

public class Parser {
    public static GeneralDukeInstruction parseInstruction(String input) throws GeneralDukeException {
        Pattern emptyStringChecker = Pattern.compile("\\S.*+");

        //@@author Yufannnn-reused
        //Reused from https://github.com/wweqg/ip/blob/master/src/main/java/duke/parser/Parser.java
        //with minor modification, it is a pretty clean and concise regular expression for general instructions
        Matcher instructionExtractor = Pattern.
                compile("(?<instructionTag>\\S++)(?<information>.*)").matcher(input.strip());
        //@@author

        if (!instructionExtractor.matches()) {
            throw new EmptyInputException("☹ OOPS!!! The instruction cannot be empty");
        }
        String instructionTag = instructionExtractor.group("instructionTag").strip();
        String information = instructionExtractor.group("information").strip();

        if (instructionTag.equalsIgnoreCase("bye")) {
            return new ExitInstruction();
        } else if (instructionTag.equalsIgnoreCase("list")) {
            return new ListInstruction();
        } else if (instructionTag.equalsIgnoreCase("mark")) {
            Matcher numberChecker = Pattern.compile("\\d+?").matcher(information);
            if (numberChecker.matches()) {
                return new MarkAsDoneInstruction(Integer.parseInt(information) - 1);
            } else {
                throw new InvalidMarkInputException("The input task index is not a number,\n" +
                        "Please input a valid task index");
            }
        } else if (instructionTag.equalsIgnoreCase("unmark")) {
            Matcher numberChecker = Pattern.compile("\\d+?").matcher(information);
            if (numberChecker.matches()) {
                return new UnmarkInstruction(Integer.parseInt(information) - 1);
            } else {
                throw new InvalidUnmarkInputException("The input task index is not a number,\n" +
                        "Please input a valid task index");
            }
        } else if(instructionTag.equalsIgnoreCase("delete")) {
            Matcher numberChecker = Pattern.compile("\\d+?").matcher(information);
            if (numberChecker.matches()) {
                return new DeleteInstruction(Integer.parseInt(information) - 1);
            } else {
                throw new InvalidUnmarkInputException("The input task index is not a number,\n" +
                        "Please input a valid task index");
            }
        } else if (instructionTag.equalsIgnoreCase("todo")) {
            if (!emptyStringChecker.matcher(information).matches()) {
                throw new InvalidTodoException("☹ OOPS!!! The description of a todo cannot be empty.");
            } else {
                return new AddToDoTaskInstruction(new TodoTask(information));
            }
        } else if (instructionTag.equalsIgnoreCase("deadline")) {
            if (!emptyStringChecker.matcher(information).matches()) {
                throw new InvalidDeadlineException("☹ OOPS!!! The description of a deadline cannot be empty.");
            } else {
                Matcher dateChecker = Pattern.compile("(?<name>.*)/by(?<date>.*)").matcher(information);
                if (dateChecker.matches()) {
                    String name = dateChecker.group("name").strip();
                    String date = dateChecker.group("date").strip();
                    return new AddDeadlineTaskInstruction(new DeadlineTask(name, date));
                } else {
                    throw new InvalidDeadlineException("☹ OOPS!!! Please input the deadline in the correct format.");
                }
            }
        } else if (instructionTag.equalsIgnoreCase("event")) {
            if (!emptyStringChecker.matcher(information).matches()) {
                throw new InvalidEventException("☹ OOPS!!! The description of a event cannot be empty.");
            } else{
                Matcher intervalChecker = Pattern.compile("(?<name>.*)/from(?<from>.*)/to(?<to>.*)").matcher(information);
                if (intervalChecker.matches()) {
                    String name = intervalChecker.group("name").strip();
                    String from = intervalChecker.group("from").strip();
                    String to = intervalChecker.group("to").strip();
                    return new AddEventTaskInstruction(new EventTask(name, from, to));
                } else {
                    throw new InvalidEventException("☹ OOPS!!! Please input the event in the correct format.");
                }
            }
        } else {
            throw new UnrecognizedInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
