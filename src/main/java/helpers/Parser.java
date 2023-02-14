package helpers;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Parser {
    private TaskList taskList;
    private Task task;
    private String instruction;
    private String[] instrSplit;
    private String command;
    private String message;
    private int index;
    private boolean isBye = false;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    public boolean parse(String instr) {
        this.instruction = instr;
        this.instrSplit = instr.split(" ");
        this.command = instrSplit[0];

        switch (command) {
            case "bye":
                isBye = true;
                this.taskList.save();
                message = "Bye. Hope to see you again soon!\n";
                break;
            case "list":
                message = "Here are the tasks in your list:\n" + taskList.list();
                break;
            case "mark":
                index = Integer.parseInt(instrSplit[1]);
                message = "Nice! I've marked this task as done:\n";
                message += "    " + taskList.mark(index);
                break;
            case "unmark":
                index = Integer.parseInt(instrSplit[1]);
                message = "OK, I've marked this task as not done yet:\n";
                message += "    " + taskList.unmark(index);
                break;
            case "delete":
                index = Integer.parseInt(instrSplit[1]);
                message = "Noted. I've removed this task:\n";
                message += "    " + taskList.delete(index);
                break;
            case "todo":
                task = new Todo(instr);
                message = "Got it. I've added this task:\n";
                message += taskList.add(task);
                break;
            case "deadline":
                task = new Deadline(instruction);
                message = "Got it. I've added this task:\n";
                message += taskList.add(task);
                break;
            case "event":
                task = new Event(instruction);
                ;
                message = "Got it. I've added this task:\n";
                message += taskList.add(task);
                break;
            default:
                message = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        }

        UI.printWithLines(message);
        return isBye;
    }
}