import duke.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Scanner;

/**
 * Duke Program behaves like a chatbot that interacts with user input.
 * It allows the user to create a list of Tasks consisting of todos, deadlines, and events
 * @author oliverloo
 * @version 1.0
 *
 */
public class Duke {
    /**
     * Main method to initiliase duke chatbot
     * @param args
     */
        public static void main (String[]args) {
            Tasklist list = new Tasklist();
            try {
                Storage.loadFile(list);
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }

                Ui.greet();
                Scanner sc = new Scanner(System.in);
                //echo

                while (true) {
                    try {
                    String input = sc.nextLine();

                    if (Parser.is_Bye(input)) {
                        Ui.displayByeMessage();
                        break;
                    }

                    //list
                    else if (Parser.is_List(input)) {
                        Duke.executeList(list);
                    }

                    //mark
                    else if (Parser.is_Mark(input)) {
                        Duke.executeMark(input, list);
                    }

                    //unmark
                    else if (Parser.is_Unmark(input)) {
                        Duke.executeUnmark(input, list);
                    }

                    //delete
                    else if (Parser.is_Delete(input)) {
                        Duke.executeDelete(input, list);
                    }

                    //clear
                    else if (Parser.is_Clear(input)) {
                        Duke.executeClear(input, list);
                    }

                    //todo
                    else if (Parser.is_toDo(input)) {
                        Duke.executeToDo(input, list);
                    }

                    //deadline
                    else if (Parser.is_Deadline(input)) {
                        Duke.executeDeadline(input, list);
                    }

                    //event
                    else if (Parser.is_Event(input)) {
                        Duke.executeEvent(input, list);
                    }

                    //find
                    else if (Parser.is_Find(input)) {
                        Duke.executeFind(input, list);
                    }

                    else {
                        System.out.println("Invalid Input!");
                        //throw new Duke.DukeException("Invalid Input!");
                    }
                    try {
                        Storage.saveToFile(list);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                sc.close();
    }

    /**
     * Executes the clear command
     * @param input
     * @param list
     */
    private static void executeClear(String input, Tasklist list) {
            list.clear();
            Ui.displayClear();
    }

    /**
     * Executes list command
     * @param list
     */
    public static void executeList(Tasklist list) {
        Ui.displayList(list);
    }

    /**
     * Executes the mark command
     * @param input
     * @param list
     */
    public static void executeMark(String input, Tasklist list) {
            int index = Parser.getIndex(input);
            if (list.size() != 0 && index > 0 && index <= list.size()) {
                list.get(index - 1).mark();
            } else {
                Ui.displayInvalidIndexMessage();
            }
    }

<<<<<<< HEAD
        public static void executeFind(String input, Tasklist list) throws DukeException {
            String wordToFind = Parser.get_Findable(input);
            int count = 1;
            Ui.displayFindHeader();
            for(int i = 0; i < list.size(); i++) {
                Task currTask = list.get(i);
                if (currTask.get_content().contains(wordToFind)) {
                    Ui.displayMatchedTask(list.get(i), count);
                    count++;
                }

            }
        }

        public static void executeUnmark(String input, Tasklist list) {
=======
    /**
     * Executes the unmark command
     * @param input
     * @param list
     */
    public static void executeUnmark(String input, Tasklist list) {
>>>>>>> branch-A-JavaDoc
            int index = Parser.getIndex(input);
            if (list.size() != 0 && index > 0 && index <= list.size()) {
            list.get(index - 1).unmark();
            } else {
            Ui.displayInvalidIndexMessage();
            }

    }

    /** Executes the delete command
     *
     * @param input
     * @param list
     */
    public static void executeDelete(String input, Tasklist list) {
            int index = Parser.getIndex(input);
            if (list.size() != 0 && index > 0 && index <= list.size()) {
            Ui.displayDelete(index - 1, list);
            } else {
            Ui.displayInvalidIndexMessage();
            }

    }

    /**
     * Executes the Deadline command
     * @param input
     * @param list
     * @throws Duke Exception
     */
    public static void executeDeadline(String input, Tasklist list) throws DukeException {
        Deadline deadline = Parser.parseDeadline(input);
        list.add(deadline);
        Ui.displayAddTask(list.get(list.size() - 1), list);
    }

    /**
     * Executes the event command
     * @param input
     * @param list
     * @throws Duke Exception
     */
    public static void executeEvent(String input, Tasklist list) throws DukeException {
        Event event = Parser.parseEvent(input);
        list.add(event);
        Ui.displayAddTask(list.get(list.size() - 1), list);
    }

    /** Executes the todo command
     * @param input
     * @param list
     */
    public static void executeToDo(String input, Tasklist list) {
        String todo = Parser.getTodo(input);
        list.add(new ToDo(todo));
        Ui.displayAddTask(list.get(list.size() - 1), list);
    }








}
