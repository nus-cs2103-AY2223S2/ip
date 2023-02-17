package duke.helpers;

import java.time.format.DateTimeParseException;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Intermediary between UI which receive instructions
 * and TaskList which contains logic to process instructions.
 *
 * @author jengoc415
 */
public class Parser {
    private TaskList taskList;
    private Task task;
    private String instruction;
    private String[] instrSplit;
    private String command;
    private String message;
    private int index;

    /**
     * Constructor for Parser: logic intermediary.
     *
     * @param taskList Contains ArrayList of tasks,
     *                as well as task manipulation methods
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Process instructions by interacting with relevant classes
     *
     * @param instr Full string instruction
     * @return Determines whether program terminates.
     */
    public String parse(String instr) {
        this.instruction = instr;
        this.instrSplit = instr.split(" ");
        this.command = instrSplit[0];

        switch (command) {
        case "bye":
            message = "Bye. Hope to see you again soon!\n";
            break;
        case "list":
            message = "Here are the tasks in your list:\n" + taskList.list();
            break;
        case "mark":
            index = Integer.parseInt(instrSplit[1]);
            message = "Nice! I've marked this task as done:\n";
            message += "    " + taskList.mark(index) + "\n";
            this.taskList.save();
            break;
        case "unmark":
            index = Integer.parseInt(instrSplit[1]);
            message = "OK, I've marked this task as not done yet:\n";
            message += "    " + taskList.unmark(index) + "\n";
            this.taskList.save();
            break;
        case "delete":
            index = Integer.parseInt(instrSplit[1]);
            message = "Noted. I've removed this task:\n";
            message += "    " + taskList.delete(index) + "\n";
            this.taskList.save();
            break;
        case "todo":
            task = new Todo(instr);
            message = "Got it. I've added this task:\n";
            message += taskList.add(task);
            this.taskList.save();
            break;
        case "deadline":
            try {
                task = new Deadline(instruction);
                message = "Got it. I've added this task:\n";
                message += taskList.add(task);
            } catch (DateTimeParseException e) {
                message = "Date format incorrect!!\n";
                message += "Usage: 'deadline task /by YYYY-MM-DD'\n";
            } catch (DukeException e) {
                message = e.getMessage();
            }
            this.taskList.save();
            break;
        case "event":
            task = new Event(instruction);
            message = "Got it. I've added this task:\n";
            message += taskList.add(task);
            this.taskList.save();
            break;
        case "find":
            String keyword = instrSplit[1];
            message = taskList.find(keyword);
            break;
        default:
            message = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
            break;
        }
        return message;
    }
}
