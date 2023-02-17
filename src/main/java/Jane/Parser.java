package jane;

import jane.task.Todo;

import java.time.LocalDateTime;

public class Parser {
    /**
     * Parses user input to a Todo object
     * @param count the size of TaskList containing this object
     * @param output the user input given
     * @return a Todo object
     * @throws JaneException if description given is empty
     */
    public static Todo parserT(String output, int count) {
        String des = output.substring(5);
        Todo todo = new Todo(count + 1, des);
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
        String des = output.substring(9);
        String[] s = des.split("\\|");
        try {
            if (s.length == 1) {
                throw new JaneException("Please specify when the deadline is :(((");
            }
        } catch(jane.JaneException err) {
            System.out.println("Please specify when the deadline is :(((");

        }
        jane.task.Deadline d = new jane.task.Deadline(count +1, s[0], LocalDateTime.parse(s[1]));
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
        String des = output.substring(6);
        String[] s = des.split("\\|");
        try {
            if (s.length == 1) {
                throw new JaneException("Please specify when the event is :(((");
            }
        } catch (jane.JaneException err) {
            System.out.println("Please specify when event is");
        }
        String[] start = s[1].split(" ");
        //input event is "event meeting /2019-02-03 10:30/12:40"
        LocalDateTime startE = LocalDateTime.parse(String.format("%sT%s", start[0], start[1]));
        //here I am assuming an event only lasts 1 day since the day it starts is the day it ends
        LocalDateTime end = LocalDateTime.parse(String.format("%sT%s", start[0], s[2]));
        jane.task.Event e = new jane.task.Event(count + 1, s[0], startE, end);
        return e;
    }
}