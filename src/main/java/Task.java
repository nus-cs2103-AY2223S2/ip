public class Task {
    private Boolean isDone;

    public Task() {
        this.isDone = false;
    }

    public Task createNewTask(String type, String task) {
        if (type.equals("todo")) {
            return todo(task);
        } else if (type.equals("deadline")) {
            return deadline(task);
        } else if (type.equals("event")) {
            return event(task);
        } else {
            System.out.println("unknown command! Please try again.");
            return null;
        }
    }

    private Task todo(String task) {
        Task todo_task = new Todo(task);
        return todo_task;
    }

    private Task deadline(String task) {
        Task deadline_task = new Deadline(task);
        return deadline_task;
    }

    private Task event(String task) {
        Task event_task = new Event(task);
        return event_task;
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

        public Deadline(String task) {
            super();
            String[] commands = task.split(" /by ");
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

        public Event(String task) {
            super();
            String[] commands = task.split(" /from ");
            this.task = commands[0];
            String[] from_to_timeline = commands[1].split(" /to ");
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
