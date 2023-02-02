package leo.task;

import java.io.IOException;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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
            .asList("todo", "deadline", "event", "mark", "unmark", "delete");

    final static List<String> commands = Arrays
            .asList("list", "bye", "todo", "deadline", "event", "mark", "unmark", "delete");

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

    public static Task createTask(String[] taskArray) throws LeoTaskException {

        try {

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
        } catch (LeoTaskException e) {
            return null;
        }
    }

    public void setDone() {
        isDone = true;
        Ui.printDivider();
        System.out.println(
                "Well done on completing the task! Let me mark that as done! Campeon del mundo!");
        System.out.println(this);
        Ui.printDivider();

    }

    public void setNotDone() {
        isDone = false;
        Ui.printDivider();
        System.out.println("Ok, I've marked that as not done! Please get to it :(");
        System.out.println(this);
        Ui.printDivider();
    }

    public boolean getDone() {
        return isDone;
    }

    public char getDoneChar() {
        return isDone ? 'X' : ' ';
    }

    public static LocalDateTime stringToDate(String dateTimeString) {
        LocalDateTime date = LocalDateTime.parse(dateTimeString.trim(), DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        return date;
    }

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
                Deadline dl = new Deadline(taskDesc.split(" ", 2)[0], cmd);
                String[] dlDetails = taskDesc.split("/by", 2);
                if (dlDetails.length < 2) {
                    throw new MissingDeadlineException();
                }

                dl.by = stringToDate(dlDetails[1]);
                return dl;
            } catch (MissingDeadlineException e) {
                Ui.printError(e);
                return null;
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
            Event ev = new Event(taskDesc.split(" ", 2)[0], cmd);
            String[] evDetails = taskDesc.split("/from", 2)[1].split("/to", 2);
            try {
                System.out.println(Arrays.toString(evDetails));
                if (evDetails.length < 2) {
                    throw new MissingTimelineException();
                }
                ev.from = stringToDate(evDetails[0]);
                ev.to = stringToDate(evDetails[1]);
                return ev;
            } catch (MissingTimelineException e) {
                Ui.printError(e);
                return null;
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