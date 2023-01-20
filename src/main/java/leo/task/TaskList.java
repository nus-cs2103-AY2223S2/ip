package leo.task;

import java.util.*;

public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<>();

    static class Task {
        enum Type {
            TODO, DEADLINE, EVENT
        };

        private String taskDesc;
        private boolean isDone = false;
        private Type type;
        private final static List<String> descCommands = Arrays
                .asList(new String[] { "todo", "deadline", "event", "mark", "unmark", "delete" });

        private final static List<String> commands = Arrays
                .asList(new String[] { "list", "bye", "todo", "deadline", "event", "mark", "unmark", "delete" });

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

        public static Task createTask(String request) throws LeoTaskException {
            String[] taskArray = request.split(" ", 2);

            try {
                if (taskArray.length < 2) {
                    if (descCommands.contains(taskArray[0])) {
                        throw new EmptyFieldException();
                    }
                    throw new InvalidCommandException();
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
                e.printStackTrace();
                System.out.println();
                return null;
            }
        }

        public void setDone() {
            isDone = true;
            System.out.println(
                    "Well done on completing the task! Let me mark that as done! Campeon del mundo!");
            System.out.println(this);

        }

        public void resetDone() {
            isDone = false;
            System.out.println("Ok, I've marked that as not done! Please get to it :(");
            System.out.println(this);
        }

        public boolean getDone() {
            return isDone;
        }

        public char getDoneChar() {
            return isDone ? 'X' : ' ';
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
            String by;

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

                    dl.by = dlDetails[1];
                    return dl;
                } catch (MissingDeadlineException e) {
                    e.printStackTrace();
                    System.out.println();
                    return null;
                }
            }

            @Override
            public String toString() {
                return String.format("%s (by: %s)", super.toString(), this.by);
            }
        }

        private static class Event extends Task {
            String from;
            String to;

            private Event(String taskDesc, String cmd) {
                super(taskDesc.split("/", 2)[0], cmd);
            }

            public static Event createEvent(String taskDesc, String cmd) throws MissingTimelineException {
                Event ev = new Event(taskDesc.split(" ", 2)[0], cmd);
                String[] evDetails = taskDesc.split("/", 3);
                try {
                    System.out.println(Arrays.toString(evDetails));
                    if (evDetails.length < 3) {
                        throw new MissingTimelineException();
                    }
                    ev.from = evDetails[1].substring(5);
                    ev.to = evDetails[2].substring(3);
                    return ev;
                } catch (MissingTimelineException e) {
                    e.printStackTrace();
                    System.out.println();
                    return null;
                }
            }

            @Override
            public String toString() {
                return String.format("%s (from: %s, to: %s)", super.toString(), from, to);
            }
        }
    }

    private void addTask(String taskString) throws LeoTaskException {
        try {
            Task task = Task.createTask(taskString);

            if (task == null) {
                throw new LeoTaskException("I'm sorry, I don't know what you want. ¿Que miras bobo?\n");
            }

            tasks.add(task);
            System.out.printf("Alright, I've added: %s to your task list.\n", tasks.get(tasks.size() - 1));
            System.out.printf("You have %d tasks in your list, vamos, get moving!\n", tasks.size());
        } catch (LeoTaskException e) {
            e.printStackTrace();
            System.out.println();
        }
    }

    private void deleteTask(int taskId) throws LeoTaskException {
        try {
            if (tasks.isEmpty()) {
                throw new EmptyDeletionException();
            }
            String taskDesc = tasks.remove(taskId - 1).toString();
            System.out.printf("Alright! I've removed this from your list: %s\n", taskDesc);
            System.out.printf("You have %d tasks in your list, vamos, get moving!\n", tasks.size());
        } catch (LeoTaskException e) {
            e.printStackTrace();
            System.out.println();
        }
    }

    public void processRequest(String request) throws LeoTaskException {

        try {
            for (int i = 0; i < 25; i++) {
                System.out.print("*");
            }
            System.out.println();

            String[] rqDetails = request.split(" ", 2);
            if (!Task.commands.contains(rqDetails[0])) {
                throw new InvalidCommandException();
            }

            else if (Task.descCommands.contains(rqDetails[0]) && rqDetails.length <= 1) {
                throw new EmptyFieldException();
            }

            switch (rqDetails[0]) {
                case "bye":
                    System.out.println("It was nice talking, see you soon!");
                    break;
                case "list":
                    System.out.println("Here are your tasks you legend: ");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.printf("%d) %s\n", i + 1, tasks.get(i));
                    }
                    break;
                case "mark":
                    tasks.get(Integer.parseInt(rqDetails[1]) - 1).setDone();
                    break;
                case "unmark":
                    tasks.get(Integer.parseInt(rqDetails[1]) - 1).resetDone();
                    break;
                case "delete":
                    deleteTask(Integer.parseInt(rqDetails[1]));
                    break;
                default:
                    addTask(request);
                    break;
            }

            for (int i = 0; i < 25; i++) {
                System.out.print("*");
            }
            System.out.println();
        } catch (LeoTaskException e) {
            e.printStackTrace();
            System.out.println();
        }
    }

}
