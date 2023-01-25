package src.main.java.duke;

public class Parser {

    protected String input;
    protected String[] inputArr;
    protected String command;

    public Parser(String input) {
        this.input = input;
        this.inputArr = input.split(" ");
        this.command = this.inputArr[0];
    }

    public String checkDescription(String str, String task) throws DukeException {
        if(str.equals("")) {
            String message = "☹ OOPS!!! The description of a " + task + " cannot be empty.";
            throw new DukeException(message);
        }
        return str;
    }

    public String checkTime(String str, String task, String type) throws DukeException {
        if(str.equals("")) {
            String message = "☹ OOPS!!! The /" + type + " part of a " + task + " cannot be empty.";
            throw new DukeException(message);
        }
        return str;
    }

    public Duke.Commands checkCommand(String command) throws DukeException {
        boolean flag = true;
        for(Duke.Commands c : Duke.Commands.values()) {
            if(command.equals(c.name())) flag = false;
        }
        if(flag) {
            String message = "____________________________________________________________\n"
                    + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            throw new DukeException(message);
        }
        return Duke.Commands.valueOf(command);
    }
}
