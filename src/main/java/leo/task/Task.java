package leo.task;

import java.io.IOException;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import leo.parser.Parser;
import leo.ui.Ui;

/**
 * Represents a task.
 */
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    
    enum Type {
        TODO, DEADLINE, EVENT
    };

    private String taskDesc;
    private boolean isDone = false;
    private Type type;
    final static List<String> descCommands = Arrays
            .asList("todo", "deadline", "event", "mark", "unmark", "delete", "find");

    final static List<String> commands = Arrays
            .asList("list", "bye", "help", "todo", "deadline", "event", "mark", "unmark", "delete", "find");

    private Task(String taskDesc, String cmd) {
        this.taskDesc = taskDesc;
        this.type = cmd.equals("event") ? Type.EVENT : cmd.equals("deadline") ? Type.DEADLINE : Type.TODO;
    }

    private char getType() {
        switch (type) {
            case DEADLINE:
                return 'D';
            case EVENT:
                return 'E';
            default:
                return 'T';
        }
    }

    /**
     * Creates a task based on the command given and content of the task.
     * @param taskArray
     * @return
     * @throws LeoTaskException
     */
    public static Task createTask(String[] taskArray) throws LeoTaskException {
        if (!commands.contains(taskArray[0])) {
            throw new InvalidCommandException();
        }

        if (taskArray.length < 2 && descCommands.contains(taskArray[0])) {
                throw new EmptyFieldException();
        }

        String cmd = taskArray[0], desc = taskArray[1];

        switch (cmd) {
        case "deadline":
            return Deadline.createDeadline(desc, cmd);
        case "event":
            return Event.createEvent(desc, cmd);
        case "todo":
            return new Todo(desc, cmd);
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Marks the task as done.
     */
    public void setDone() {
        isDone = true;
        Ui.printDivider();
        System.out.println(
                "Well done on completing the task! Let me mark that as done! Campeon del mundo!");
        System.out.println(this);
        Ui.printDivider();

    }

    /**
     * Marks the task as not done.
     */
    public void setNotDone() {
        isDone = false;
        Ui.printDivider();
        System.out.println("Ok, I've marked that as not done! Please get to it :(");
        System.out.println(this);
        Ui.printDivider();
    }

    /**
     * Returns the character representing the status of the task.
     * @return 'X' if task is done, ' ' if task is not done.
     */
    public char getDoneChar() {
        return isDone ? 'X' : ' ';
    }

    /**
     * Returns the String representation of the task.
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%c][%c] %s", getType(), getDoneChar(), taskDesc);
    }

    private static class Todo extends Task {
        public Todo(String taskDesc, String cmd) {
            super(taskDesc, cmd);
        }
    }

    private static class Deadline extends Task {
        LocalDateTime by;

        private Deadline(String taskDesc, String cmd) {
            super(taskDesc.split("/by")[0], cmd);
        }

        public static Deadline createDeadline(String taskDesc, String cmd) throws MissingDeadlineException {
            try {
                Deadline dl = new Deadline(taskDesc.split("/by", 2)[0], cmd);
                String[] dlDetails = taskDesc.split("/by", 2);
                if (dlDetails.length < 2 || dlDetails[1].equals("")) {
                    throw new MissingDeadlineException();
                }
                dl.by = Parser.stringToDate(dlDetails[1]);
                return dl;
            } catch (MissingDeadlineException e) {
                Ui.printError(e);
                throw e;
            }
        }

        @Override
        public String toString() {
            String byStr = by.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
            return String.format("%s (by: %s)", super.toString(), byStr);
        }

        private void writeObject(java.io.ObjectOutputStream out) throws IOException {
            out.defaultWriteObject();
            out.writeObject(by);
        }

        private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
            in.defaultReadObject();
            by = (LocalDateTime) in.readObject();
        }
    }

    private static class Event extends Task {
        LocalDateTime from;
        LocalDateTime to;

        private Event(String taskDesc, String cmd) {
            super(taskDesc.split("/", 2)[0], cmd);
        }

        public static Event createEvent(String taskDesc, String cmd) throws MissingTimelineException {
            Event ev = new Event(taskDesc.split("/from", 2)[0], cmd);
            String[] evDetails = taskDesc.split("/from", 2)[1].split("/to", 2);
            try {
                System.out.println(Arrays.toString(evDetails));
                if (evDetails.length < 2) {
                    throw new MissingTimelineException();
                }
                ev.from = Parser.stringToDate(evDetails[0]);
                ev.to = Parser.stringToDate(evDetails[1]);
                return ev;
            } catch (MissingTimelineException e) {
                Ui.printError(e);
                throw e;
            }
        }


        @Override
        public String toString() {
            String fromStr = from.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
            String toStr = to.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
            return String.format("%s (from: %s, to: %s)", super.toString(), fromStr, toStr);
        }

        private void writeObject(java.io.ObjectOutputStream out) throws IOException {
            out.defaultWriteObject();
            out.writeObject(from);
            out.writeObject(to);
        }

        private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
            in.defaultReadObject();
            from = (LocalDateTime) in.readObject();
            to = (LocalDateTime) in.readObject();
        }
    }
}