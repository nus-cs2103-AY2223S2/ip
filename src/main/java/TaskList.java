import java.util.*;

public class TaskList {
    private List<Task> tasks;
    public enum Tasks { TODO, DEADLINE, EVENT }

    public static final Map<String, Tasks> inputToTask = new HashMap<>();
    public static final Map<String, Tasks> symbolToTask = new HashMap<>();

    static{
        inputToTask.put("todo", Tasks.TODO);
        inputToTask.put("deadline", Tasks.DEADLINE);
        inputToTask.put("event", Tasks.EVENT);

        symbolToTask.put("T", Tasks.TODO);
        symbolToTask.put("D", Tasks.DEADLINE);
        symbolToTask.put("E", Tasks.EVENT);
    }


    TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task getTask(int i) {
        return this.tasks.get(i);
    }

    private String addTask(Tasks t, Map<String,String> args) throws Exception {
        Task item;
        String desc;
        try {
            switch (t) {
                case TODO:
                    desc = args.get("todo");
                    if (Objects.isNull(desc)|| desc.equals("") ) {
                        throw new InvalidInputException("eh ur description is blank");
                    }
                    item = new ToDo(desc);
                    break;
                case DEADLINE:
                    desc = args.get("deadline");
                    if (Objects.isNull(desc) || desc.equals("")) {
                        throw new InvalidInputException("eh ur description is blank");
                    }
                    String by = args.get("by");
                    if ( Objects.isNull(by) || by.equals("")) {
                        throw new InvalidInputException("eh need to specify ur deadline by when - deadline <desc> /by <when>");
                    }

                    item = new Deadline(desc, by);
                    break;
                case EVENT:
                    desc = args.get("event");
                    if ( Objects.isNull(desc) || desc.equals("")) {
                        throw new InvalidInputException("eh ur description is blank");
                    }

                    String from = args.get("from");
                    String to = args.get("to");
                    if (Objects.isNull(from) ||  Objects.isNull(to) || from.equals("") || to.equals("")) {
                        throw new InvalidInputException("eh need to specify ur event from when to when - event <desc> /from <when> /to <when>");
                    }

                    item = new Event(desc, from, to);
                    break;
                default:
                    throw new Exception("Invalid task type");
            }
        } catch (InvalidInputException e) {
            return e.getMessage();
        }
        this.tasks.add(item);
        return ("Item added!\n" + item +"\nYou now have " + this.tasks.size() +" task(s).");
    }

    public String deleteTask(String index) {
        //removes a task at given index
        int i;
        try {
            i = Integer.valueOf(index) - 1;
        } catch (NumberFormatException e) {
            return "Please specify the task by its index number.";
        }

        if (this.tasks.isEmpty()) {
            return "There's nothing to delete la";
        }

        try {
            Task removed = this.tasks.get(i);
            this.tasks.remove(i);
            return "Okie I removed this task:\n" + removed.toString() + "\nYou now have " + this.tasks.size() + " task(s) left.";
        } catch (IndexOutOfBoundsException e) {
            return "This task doesn't exist in your list.";
        }
    }

    public List<Task> getAllTasks() {
        return this.tasks;
    }

    public boolean markAsDone(int index) throws IndexOutOfBoundsException {

        boolean alreadyMarked = false;
        try {
            Task task = this.tasks.get(index);
            if (task.isDone()) {
                alreadyMarked = true;
            }
            task.markAsDone();
            return alreadyMarked;
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }

    public boolean unmarkDone(int index) throws IndexOutOfBoundsException {

        boolean alreadyMarked = false;
        try {
            Task task = this.tasks.get(index);
            if (!task.isDone()) {
                alreadyMarked = true;
            }
            task.unmarkDone();
            return alreadyMarked;
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }

}
