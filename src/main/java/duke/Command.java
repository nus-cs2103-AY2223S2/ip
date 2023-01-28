package duke;

import duke.task.*;

public class Command {
    private String command;
    private String args;

    public Command(String command, String args) {
        this.command = command;
        this.args = args;
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
        }
    }

    private void event(TaskList taskList) throws DukeException {
        if (args.length() == 0) {
            throw new DukeException("What is the Event task???");
        } else {
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
        }
    }

    private void mark(TaskList taskList) {
        taskList.markTask(Integer.parseInt(this.args));
    }

    private void unMark(TaskList taskList) {
        taskList.unMarkTask(Integer.parseInt(this.args));
    }

    private void delete(TaskList taskList){
        taskList.remove(Integer.parseInt(this.args));
    }

    @Override
    public String toString(){
        return this.command + " " + this.args;
    }
}
