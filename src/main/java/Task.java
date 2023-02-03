import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task implements Serializable {
    private Boolean isDone;

    public Task() {
        this.isDone = false;
    }

    public Task createNewTask(String type, String task) throws DukeExceptions{
        if (type.equals("todo")) {
            return new Todo(task);
        } else if (type.equals("deadline")) {
            return new Deadline(task);
        } else if (type.equals("event")) {
            return new Event(task);
        } else {
            System.out.println("unknown command! Please try again.");
            return null;
        }
    }

    public void markTask() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    public LocalDate parseDate(String[] possibleDateTime) throws DateTimeParseException {
        try {
            //parse date
            LocalDate possibleDeadlineDate = LocalDate.parse(possibleDateTime[0]);
            return possibleDeadlineDate;
        } catch (DateTimeParseException ex) {
            //can not parse date
            return null;
        }
    }

    public String parseStringDate(LocalDate possibleDeadlineDate) {
        return possibleDeadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public LocalTime parseTime(String[] possibleDateTime) throws DateTimeParseException {
        try {
            //parse date
            LocalTime possibleDeadlineTime = (possibleDateTime.length == 1)
                    ? LocalTime.parse(possibleDateTime[0])
                    : LocalTime.parse(possibleDateTime[1]);
            return possibleDeadlineTime;
        } catch (DateTimeParseException ex) {
            //can not parse date
            return null;
        }
    }

    public String parseStringTime(LocalTime possibleDeadlineTime) {
        return possibleDeadlineTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    class Todo extends Task {
        private String task;

        public Todo(String task) {
            super();
            this.task = task;
        }

        @Override
        public String toString() {
            String name = super.isDone ? "[T][X] " + task : "[T][ ] " + task;
            return name;
        }
    }

    class Deadline extends Task {
        private String task;
        private String deadline;
        private Boolean isDate = false;
        private Boolean isTime = false;
        private LocalDate deadlineDate;
        private LocalTime deadlineTime;
        private String deadlineDateString;
        private String deadlineTimeString;

        public Deadline(String task) throws DukeExceptions, DateTimeParseException {
            super();
            String[] commands = task.split(" /by ");
            if (commands.length == 1) {
                throw new DukeExceptions("OOPS!!! Looks like someone forget his/her deadline :)\n Please use /by to indicate deadline");
            }
            this.task = commands[0];
            this.deadline = commands[1];
            String[] possibleDateTime = this.deadline.split((" "));
            LocalDate possibleDeadlineDate = parseDate(possibleDateTime);
            if (possibleDeadlineDate != null) {
                this.isDate = true;
                this.deadlineDate = possibleDeadlineDate;
                this.deadlineDateString = parseStringDate(possibleDeadlineDate);
            }
            LocalTime possibleDeadlineTime = parseTime(possibleDateTime);
            if (possibleDeadlineTime != null) {
                this.isTime = true;
                this.deadlineTime = possibleDeadlineTime;
                this.deadlineTimeString = parseStringTime(possibleDeadlineTime);
            }
        }

        @Override
        public String toString() {
            if (isDate && isTime) {
                String name = super.isDone ? "[D][X] " + task + " (by: " + this.deadlineDateString + " "
                        + this.deadlineTimeString +")" : "[D][ ] " + task + " (by: " + this.deadlineDateString + " "
                        + this.deadlineTimeString + ")";
                return name;
            } else if (isDate) {
                String name = super.isDone ? "[D][X] " + task + " (by: " + this.deadlineDateString + " "
                        +")" : "[D][ ] " + task + " (by: " + this.deadlineDateString + ")";
                return name;
            } else if (isTime) {
                String name = super.isDone ? "[D][X] " + task + " (by: " + this.deadlineTimeString + " "
                        +")" : "[D][ ] " + task + " (by: " + this.deadlineTimeString + ")";
                return name;
            }
            String name = super.isDone ? "[D][X] " + task + " (by: " + this.deadline +")" : "[D][ ] " + task + " (by: " + this.deadline +")";
            return name;
        }
    }

    class Event extends Task {
        private String task;
        private String from;
        private String to;
        private Boolean isFromDate = false;
        private Boolean isFromTime = false;
        private Boolean isToDate = false;
        private Boolean isToTime = false;
        private LocalDate fromDate;
        private LocalTime fromTime;
        private LocalDate toDate;
        private LocalTime toTime;
        private String fromDateString;
        private String fromTimeString;
        private String toDateString;
        private String toTimeString;

        public Event(String task) throws DukeExceptions {
            super();
            String[] commands = task.split(" /from ");
            if (commands.length == 1) {
                throw new DukeExceptions("OOPS!!! Looks like someone forget when the event begins :)\n Please use /from to indicate begin time");
            }
            this.task = commands[0];
            String[] from_to_timeline = commands[1].split(" /to ");
            if (from_to_timeline.length == 1) {
                throw new DukeExceptions("OOPS!!! Looks like someone forget when the event ends :)\n Please use /to to indicate end time");
            }
            this.from = from_to_timeline[0];
            this.to = from_to_timeline[1];
            //parse date and time of /from
            String[] possibleDateTime = this.from.split((" "));
            LocalDate possibleFromDate = parseDate(possibleDateTime);
            if (possibleFromDate != null) {
                this.isFromDate = true;
                this.fromDate = possibleFromDate;
                this.fromDateString = parseStringDate(possibleFromDate);
            }
            LocalTime possibleFromTime = parseTime(possibleDateTime);
            if (possibleFromTime != null) {
                this.isFromTime = true;
                this.fromTime = possibleFromTime;
                this.fromTimeString = parseStringTime(possibleFromTime);
            }

            //parse date and time of /to
            possibleDateTime = this.to.split((" "));
            LocalDate possibleToDate = parseDate(possibleDateTime);
            if (possibleToDate != null) {
                this.isToDate = true;
                this.toDate = possibleToDate;
                this.toDateString = parseStringDate(possibleToDate);
            }
            LocalTime possibleToTime = parseTime(possibleDateTime);
            if (possibleToTime != null) {
                this.isToTime = true;
                this.toTime = possibleToTime;
                this.toTimeString = parseStringTime(possibleToTime);
            }
        }

        @Override
        public String toString() {
            String fromString = this.from;
            if (isFromDate || isFromTime) {
                fromString = isFromDate ? fromDateString : "";
                fromString += isFromTime ? " " + fromTimeString : "";
            }

            String toString = this.to;
            if (isToDate || isToTime) {
                toString = isToDate ? toDateString : "";
                toString += isToTime ? " " + toTimeString : "";
            }
            String name = super.isDone ? "[E][X] " + task + " (from: " + fromString +" to: " + toString + ")"
                    : "[E][ ] " + task + " (from: " + fromString +" to: " + toString + ")";
            return name;
        }
    }
}
