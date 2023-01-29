package duke;

import duke.tasks.*;

import java.time.LocalDate;

public class Parser {
    /**
     * Parses user input to a ToDo object
     * @param size the size of TaskList containing this object
     * @param input the user input given
     * @return a ToDo object
     * @throws DukeExceptions if description given is empty
     */
    public static ToDo parseToDo(int size, String input) {
        String td = input.substring(5);
        try {
            if (td.equals("")) {
                throw new DukeExceptions("Description cannot be empty!");
            }
        } catch (DukeExceptions e) {
            System.out.println("Description cannot be empty!");
        }
        return new ToDo(td);
    }

    /**
     * Parses user input to a Deadline object
     * @param size the size of TaskList containing this object
     * @param input the user input given
     * @return a Deadline object
     * @throws DukeExceptions if input given is empty
     */
    public static Deadline parseDeadline(int size, String input) {
        try { //catching no desc and no deadline
            if (input.substring(9).equals("")) {
                throw new DukeExceptions("Input cannot be empty!");
            }
        } catch (DukeExceptions e) {
            System.out.println("Input cannot be empty!");
        }
        String[] inp = input.split("/");
        try { //catching no deadline
            String deadline = inp[1].substring(3);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Please input a deadline!");
        }
        try { //catching no description
            String undesc = inp[0];
            String desc = undesc.substring(9);
            if (desc.equals("")) {
                throw new DukeExceptions("Input cannot be empty!");
            }
        } catch (DukeExceptions e){
            System.out.println("Description cannot be empty!");
        }
        String deadline = inp[1].substring(3);
        LocalDate date = LocalDate.parse(deadline);
        String undesc = inp[0];
        String desc = undesc.substring(9);
        return new Deadline(desc, date);
    }
}
