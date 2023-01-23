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

    public int size() {
        return this.tasks.size();
    }

    public Task addTask(String inputTaskType, Map<String,String> args) throws CommandNotFoundException, InvalidInputException {
        Tasks taskType = this.inputToTask.get(inputTaskType);
        if (taskType == null) {
            throw new InvalidInputException("Please specify what type of task you want to add!");
        }
        Task newTask;
        String desc;
        switch (taskType) {
            case TODO:
                desc = args.get("todo");
                if (Objects.isNull(desc) || desc.equals("")) {
                    throw new InvalidInputException("eh ur description is blank");
                }
                newTask = new ToDo(desc);
                break;
            case DEADLINE:
                desc = args.get("deadline");
                if (Objects.isNull(desc) || desc.equals("")) {
                    throw new InvalidInputException("eh ur description is blank");
                }
                String by = args.get("by");
                if (Objects.isNull(by) || by.equals("")) {
                    throw new InvalidInputException("eh need to specify ur deadline by when - deadline <desc> /by <when>");
                }

                newTask = new Deadline(desc, by);
                break;
            case EVENT:
                desc = args.get("event");
                if (Objects.isNull(desc) || desc.equals("")) {
                    throw new InvalidInputException("eh ur description is blank");
                }
                String from = args.get("from");
                String to = args.get("to");
                if (Objects.isNull(from) || Objects.isNull(to) || from.equals("") || to.equals("")) {
                    throw new InvalidInputException("eh need to specify ur event from when to when - event <desc> /from <when> /to <when>");
                }

                newTask = new Event(desc, from, to);
                break;
            default:
                throw new CommandNotFoundException("I don't recognise this type of task. Try add todo <desc>!");
        }
        this.tasks.add(newTask);
        return newTask;
    }

    public Task deleteTask(int index) throws InvalidInputException {
        //removes a task at given index

        if (this.tasks.isEmpty()) {
            throw new InvalidInputException("Hello hello there is no task to delete!!");
        }

        try {
            Task deletedTask = this.tasks.get(index);
            this.tasks.remove(index);
            return deletedTask;

        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("This task doesn't exist in your list.");
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
