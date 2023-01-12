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
        String strippedInput = input.strip();
        Matcher InstructionExtractor = Pattern
                .compile("(?<instructionTag>\\S++)(?<information>.*)").matcher(strippedInput);

        if (!InstructionExtractor.matches()) {
            throw new UnrecognizedInputException("☹ OOPS!!! I'm sorry, " +
                    "but I don't know what that means :-(");
        }
        String instructionTag = InstructionExtractor.group("instructionTag").strip();
        String information = InstructionExtractor.group("information").strip();

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
        } else if (instructionTag.equalsIgnoreCase("todo")) {
            return new AddToDoTaskInstruction(new TodoTask(information));
        } else if (instructionTag.equalsIgnoreCase("deadline")) {
            Matcher dateChecker = Pattern.compile("(?<name>.*)/by(?<date>.*)").matcher(information);
            if (dateChecker.matches()) {
                String name = dateChecker.group("name").strip();
                String date = dateChecker.group("date").strip();
                return new AddDeadlineTaskInstruction(new DeadlineTask(name, date));
            } else {
                throw new InvalidDeadlineException("☹ OOPS!!! " +
                        "The description of a deadline cannot be empty.");
            }
        } else if (instructionTag.equalsIgnoreCase("event")) {
            Matcher intervalChecker = Pattern.
                    compile("(?<name>.*)/from(?<from>.*)/to(?<to>.*)").matcher(information);
            if (intervalChecker.matches()) {
                String name = intervalChecker.group("name").strip();
                String from = intervalChecker.group("from").strip();
                String to = intervalChecker.group("to").strip();
                return new AddEventTaskInstruction(new EventTask(name, from, to));
            } else {
                throw new InvalidDeadlineException("☹ OOPS!!! " +
                        "The description of a deadline cannot be empty.");
            }
        } else {
            throw new UnrecognizedInputException("☹ OOPS!!! I'm sorry, " +
                    "but I don't know what that means :-(");
        }
    }

//    public static void main(String[] args) {
//        Matcher numberChecker = Pattern.compile("\\d+?").matcher("12a3");
//        System.out.println(numberChecker.matches());
//    }
}
