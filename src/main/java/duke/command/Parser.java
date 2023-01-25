package duke.command;

import duke.exception.*;
import duke.tasklist.*;

public class Parser {
    private TaskList tasks;

    public Parser(TaskList arrList) {
        this.tasks = arrList;
    }

    public boolean parse(String command) {
        Task task = null;
        int indx = -1;
        switch(command){
        case "bye":
            return true;
        case "list" :
            System.out.println("Here are the tasks in your list my premier:");
            this.tasks.printList();
            break;
        default:
            String[] arrOfStr = command.split(" ", 2);
            try {
                validateCmd(arrOfStr[1]);
            } catch (MissingDescriptionException e) {
                System.out.println(e.getMessage());
            }
            switch (arrOfStr[0]) {
            case "delete":
                indx = Integer.parseInt(arrOfStr[1]);
                task = this.tasks.delete(indx);
                System.out.println("Noted. I've removed this task:" + task);
                System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
                break;
            case "unmark":
                indx = Integer.parseInt(arrOfStr[1]);
                task = this.tasks.get(indx);
                this.tasks.setToUnmark(indx);
                System.out.println("OK, I've marked this task as not done yet:\n" + task);
                break;
            case "mark":
                indx = Integer.parseInt(arrOfStr[1]);
                task = this.tasks.get(indx);
                this.tasks.setToMark(indx);
                System.out.println("Nice! I've marked this task as done:\n" + task);
                break;
            case "todo":
                task = new Todo(arrOfStr[1]);
                System.out.println("Very nice. I've added this task:\n" + task);
                this.tasks.add(task);
                System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
                break;
            case "deadline":
                String[] dl = arrOfStr[1].split("/by ");
                try {
                    validateDate(dl);
                } catch (InvalidCmdException e) {
                    System.out.println(e.getMessage());
                }
                task = new Deadline(dl[0], dl[1]);
                System.out.println("Very nice. I've added this task:\n" + task);
                this.tasks.add(task);
                System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
                break;
            case "event":
                String[] ev = arrOfStr[1].split("/from");
                String[] time = ev[1].split("/to");
                task = new Event(ev[0], time[0], time[1]);
                System.out.println("Very nice. I've added this task:\n" + task);
                this.tasks.add(task);
                System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
                break;
            }
        }
        return false;
    }

    public static void validateCmd(String cmd) throws MissingDescriptionException {
        if (cmd.length() == 0) {
            throw new MissingDescriptionException("You need to " +
                    "be more specific");
        }

    }

    public static void validateDate(String[] cmd) throws InvalidCmdException {
        if (cmd.length == 1) {
            throw new InvalidCmdException("Please specify date.");
        }
    }
    }

