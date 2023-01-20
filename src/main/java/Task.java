public class Task {
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

        public Deadline(String task) throws DukeExceptions {
            super();
            String[] commands = task.split(" /by ");
            if (commands.length == 1) {
                throw new DukeExceptions("OOPS!!! Looks like someone forget his/her deadline :)\n Please use /by to indicate deadline");
            }
            this.task = commands[0];
            this.deadline = commands[1];
        }

        @Override
        public String toString() {
            String name = super.isDone ? "[D][X] " + task + " (by: " + this.deadline +")" : "[D][ ] " + task + " (by: " + this.deadline +")";
            return name;
        }
    }

    class Event extends Task {
        private String task;
        private String from;
        private String to;

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
        }

        @Override
        public String toString() {
            String name = super.isDone ? "[E][X] " + task + " (from: " + this.from +" to: " + this.to + ")"
                    : "[E][ ] " + task + " (from: " + this.from +" to: " + this.to + ")";
            return name;
        }
    }
}
