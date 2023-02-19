package jane;

import jane.task.Todo;

import java.time.LocalDateTime;
/**
 * Manages inputs and parses them into objects
 */
public class Parser {
    /**
     * Parses user input to a Todo object
     * @param count the size of TaskList containing this object
     * @param output the user input given
     * @return a Todo object
     */
    public static Todo parserT(String output, int count) {
        if (output.startsWith("todo")) {
            output = output.substring(5);
        } else {
            output = output.substring(2);
        }

        Todo todo = new Todo(count + 1, output);
        return todo;
    }
    /**
     * Parses user input to a Deadline object
     * @param count the size of TaskList containing this object
     * @param output the user input given
     * @return a Deadline object
     * @throws JaneException if description given is empty
     */
    public static jane.task.Deadline parserD(String output, int count) {
        if (output.startsWith("deadline")) {
            output = output.substring(9);
        } else {
            output = output.substring(2);
        }
        String[] s = output.split("\\|");
        try {
            if (s.length == 1) {
                throw new JaneException("Please specify when the deadline is :(((");
            }
        } catch (jane.JaneException err) {
            System.out.println("Please specify when the deadline is :(((");
        }
        jane.task.Deadline d = new jane.task.Deadline(count + 1, s[0], LocalDateTime.parse(s[1]));
        return d;
    }
    /**
     * Parses user input to an Event object
     * @param count the size of TaskList containing this object
     * @param output the user input given
     * @return a Event object
     * @throws JaneException if description given is empty
     */
    public static jane.task.Event parserE(String output, int count) {
        if (output.startsWith("event")) {
            output = output.substring(6);
        } else {
            output = output.substring(2);
        }
        String[] s = output.split("\\|");
        try {
            if (s.length == 1) {
                throw new JaneException("Please specify when the event is :(((");
            }
        } catch (jane.JaneException err) {
            System.out.println("Please specify when event is");
        }
        String[] start = s[1].split(" ");
        //input event is "event meeting |2019-02-03 10:30|12:40"
        LocalDateTime startE = LocalDateTime.parse(String.format("%sT%s", start[0], start[1]));
        //here I am assuming an event only lasts 1 day since the day it starts is the day it ends
        LocalDateTime end = LocalDateTime.parse(String.format("%sT%s", start[0], s[2]));
        jane.task.Event e = new jane.task.Event(count + 1, s[0], startE, end);
        return e;
    }
    /**
     * Parses Stroage input to a Todo object
     * @param count the size of TaskList containing this object
     * @param output the user input given
     * @return a Todo object
     */
    public static Todo parseFromStorageT(String output, int count) {
        output = output.substring(4);
        Todo todo = new Todo(count + 1, output);
        return todo;
    }
    /**
     * Parses storage input to a Deadline object
     * @param count the size of TaskList containing this object
     * @param output the user input given
     * @return a Deadline object
     */
    public static jane.task.Deadline parseFromStorageD(String output, int count) {
        String s = output.substring(4);
        String[] string = s.split("\\|");
        jane.task.Deadline d = new jane.task.Deadline(count + 1, string[0], LocalDateTime.parse(string[1]));
        return d;
    }
    /**
     * Parses storage input to a Event object
     * @param count the size of TaskList containing this object
     * @param output the user input given
     * @return a Event object
     */
    public static jane.task.Event parseFromStorageE(String output, int count) {
        String s = output.substring(4);
        String[] string = s.split("\\|");
        LocalDateTime startE = LocalDateTime.parse(string[1]);
        LocalDateTime end = LocalDateTime.parse( string[2]);
        jane.task.Event e = new jane.task.Event(count + 1, string[0], startE, end);
        return e;
    }
}
