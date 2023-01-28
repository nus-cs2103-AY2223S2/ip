package duke;

import duke.exception.DukeException;
import duke.task.TaskList;

import java.util.*;
public class Duke {
    public static boolean offBot = false;
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke.Duke\n" +
                "What can I do for you?");
        //CommandCreator creator = new CommandCreator();
        TaskList list = new TaskList(new Storage("tasks.ser"));
        while(!offBot) {
            Scanner sc= new Scanner(System.in); //System.in is a standard input stream
            try {
                Parser.createCommand(sc.nextLine());
                Parser.executeQueue(list);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
