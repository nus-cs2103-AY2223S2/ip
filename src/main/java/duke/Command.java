package duke;

import duke.task.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Command {
    private String command;
    private String args;

    public Command(String command, String args) {
        this.command = command.trim();
        this.args = args.trim();
    }

    public void execArgs(TaskList taskList) throws DukeException {
        switch(this.command.toLowerCase()) {
            case "bye":
                UI.end();
                break;
            case "list":
                taskList.generate();
                break;
            case "todo":
                todo(taskList);
                break;
            case "deadline":
                deadline(taskList);
                break;
            case "event":
                event(taskList);
                break;
            case "mark":
                mark(taskList);
                break;
            case "unmark":
                unMark(taskList);
                break;
            case "delete":
                delete(taskList);
                break;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private void todo(TaskList taskList) throws DukeException {
        if (this.args.length() == 0){
            throw new DukeException("What is the ToDo task???");
        } else {
            Task addTask = new ToDo(this.args.trim());
            taskList.add(addTask);
            System.out.println();
            System.out.println("Got it. I've added this task:\n" +
                    addTask.toString() + "\n" +
                    "Now you have " + taskList.taskCount() +
                    "in the list.\n");
        }
    }

    private void deadline(TaskList taskList) throws DukeException {
        if (args.length() == 0) {
            throw new DukeException("What is the Deadline task???");
        } else {
            Pattern deadlinePattern = Pattern.compile(".+/by \\d{2}/\\d{2}/\\d{4} \\d{4}");
            Matcher matchDeadline = deadlinePattern.matcher(this.args);
            if (matchDeadline.find()) {
                String desc = this.args.substring(0,
                        this.args.indexOf("/by")
                ).trim();

                String by = args.substring(
                        args.indexOf("/by") + "/by ".length()
                );
                Task addTask = new Deadline(desc, by);
                taskList.add(addTask);
                System.out.println("Got it. I've added this task:\n" +
                        addTask.toString() + "\n" +
                        "Now you have " + taskList.taskCount() +
                        "in the list.\n");
            } else {
                throw new DukeException("Incorrect format!\n" +
                        "Format should be: <desc> /by dd/MM/yy HHmm");
            }
        }
    }

    private void event(TaskList taskList) throws DukeException {
        if (args.length() == 0) {
            throw new DukeException("What is the Event task???");
        } else {
            Pattern eventPattern = Pattern.compile(".+/from \\d{2}/\\d{2}/\\d{4} \\d{4} /to \\d{2}/\\d{2}/\\d{4} \\d{4}");
            Matcher matchEvent = eventPattern.matcher(this.args);
            if (matchEvent.find()) {
                String desc = args.substring(0,
                        args.indexOf("/from")
                );

                String from = args.substring(
                        args.indexOf("/from") + "/from ".length(),
                        args.indexOf("/to")
                );

                String to = args.substring(
                        args.indexOf("/to") + "/to ".length()
                );

                Task addTask = new Events(desc, from, to);
                taskList.add(addTask);
                System.out.println("Got it. I've added this task:\n" +
                        addTask.toString() + "\n" +
                        "Now you have " + taskList.taskCount() +
                        "in the list.\n");
            } else {
                String errMessage = "Incorrect format!\n" +
                        "Format should be: <desc> /from dd/MM/yy HHmm" +
                        " /to dd/MM/yy HHmm";
                throw new DukeException(errMessage);
            }
        }
    }

    private void mark(TaskList taskList) throws DukeException {
        taskList.markTask(Integer.parseInt(this.args));
    }

    private void unMark(TaskList taskList) throws DukeException {
        taskList.unMarkTask(Integer.parseInt(this.args));
    }

    private void delete(TaskList taskList) throws DukeException {
        taskList.remove(Integer.parseInt(this.args));
    }

    @Override
    public String toString(){
        return this.command + " " + this.args;
    }
}
