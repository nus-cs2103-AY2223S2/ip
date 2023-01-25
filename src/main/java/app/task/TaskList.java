package task;

import command.CommandNotFoundException;

import java.util.*;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task getTask(int i) {
        return this.tasks.get(i);
    }

    public List<Task> getAllTasks() {
        return this.tasks;
    }

    public int size() {
        return this.tasks.size();
    }


    public Task addTask(TaskType.Type type, Map<String,String> args) throws InvalidDateTimeException, CommandNotFoundException, InvalidInputException {
        Task newTask;
        String desc;
        switch (type) {
            case TODO:
                desc = args.get("Description");
                if (Objects.isNull(desc) || desc.equals("")) {
                    throw new InvalidInputException("eh ur description is blank");
                }
                newTask = new ToDo(desc);
                break;
            case DEADLINE:
                desc = args.get("Description");
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
                desc = args.get("Description");
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
        this.tasks.add(this.tasks.size(), newTask); // always adds to the end
        return newTask;
    }

    public Task addDoneTask(TaskType.Type type, Map<String,String> args) throws InvalidDateTimeException, CommandNotFoundException, InvalidInputException {
        Task newTask = addTask(type, args); // add to end of list
        int index = this.tasks.size() - 1; // mark last(est) item as done
        markAsDone(index);
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
