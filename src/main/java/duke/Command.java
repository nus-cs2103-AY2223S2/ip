package duke;

import duke.task.*;

public class Command {
    private String command;
    private String args;

    public Command(String command, String args){
        this.command = command;
        this.args = args;
    }

    public void execArgs(TaskList taskList) throws DukeException {
        switch(this.command.toLowerCase()){
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
            throw new DukeException("What is the duke.task.ToDo task???");
        } else {
            taskList.add(new ToDo(this.args.trim()));
        }
    }

    private void deadline(TaskList taskList) throws DukeException {
        if (args.length() == 0){
            throw new DukeException("What is the duke.task.Deadline task???");
        } else {
            String desc = args.substring(
                    args.indexOf("deadline") + "deadline ".length(),
                    args.indexOf("/by")
            );

            String by = args.substring(
                    args.indexOf("/by") + "/by ".length()
            );

            taskList.add(new Deadline(desc, by));
        }
    }

    private void event(TaskList taskList) throws DukeException {
        if (args.length() == 0){
            throw new DukeException("What is the Event task???");
        } else {
            String desc = args.substring(
                    args.indexOf("events") + "events ".length(),
                    args.indexOf("/from")
            );

            String from = args.substring(
                    args.indexOf("/from") + "/from ".length(),
                    args.indexOf("/to")
            );

            String to = args.substring(
                    args.indexOf("/to") + "/to ".length()
            );

            taskList.add(new Events(desc, from, to));
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
}
