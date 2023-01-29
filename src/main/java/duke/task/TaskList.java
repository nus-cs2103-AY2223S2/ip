package duke.task;

import duke.DukeException;
import duke.command.Parser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<String> storedTasks) throws DukeException {
        for(String task: storedTasks) {
            String[] parsedTask = Parser.parseTask(task);
            switch (parsedTask[0]) {
                case "T":
                    tasks.add(new Todo(parsedTask[2]));
                    break;
                case "D":
                    tasks.add(new Deadline(parsedTask[2], LocalDate.parse(parsedTask[3])));
                    break;
                case "E":
                    tasks.add(new Event(parsedTask[2], parsedTask[3], parsedTask[4]));
            }
            if (parsedTask[1].equals("1")) {
               tasks.get(tasks.size() - 1).mark();
            }
        }

    }

    public void addTodo(String description) throws DukeException{
        if (description.equals("")){
            throw new DukeException("The description of a todo cannot be empty.");
        }
        Todo newTodo = new Todo(description);
        tasks.add(newTodo);
    }

    public void addDeadline(String description, String by) throws DukeException {
        Deadline newDd = new Deadline(description, LocalDate.parse(by));
        tasks.add(newDd);
    }

    public void addEvent(String description, String from, String to) throws DukeException {
        Event newEvent = new Event(description, from, to);
        tasks.add(newEvent);
    }

    public String showList() {
        int len = tasks.size();
        if(len == 0){
            return "";
        }
        String result = "Here are the tasks in your list:\n";
        for(int i = 0; i < len - 1; i ++){
            result += (i + 1) + "." + tasks.get(i) + "\n";
        }
        result += len + "." + tasks.get(len - 1);
        return result;
    }

    public String markTask(int taskNo){
        tasks.get(taskNo).mark();
        String result = "Nice! I've marked this task as done:\n";
        result += tasks.get(taskNo);
        return result;
    }

    public String unmarkTask(int taskNo){
        tasks.get(taskNo).unmark();
        String result = "OK, I've marked this task as not done yet:\n";
        result += tasks.get(taskNo);
        return result;
    }

    public String delete(int taskNo){
        Task deleted = tasks.remove(taskNo);
        return String.format("OK, I've deleted: %s\n", deleted);
    }

    public List<String> getTasksToSave() {
        List<String> toSave = new ArrayList<>();
        for(int i = 0; i < tasks.size(); i ++) {
            Task t = tasks.get(i);
            toSave.add(t.toSaveableString());
        }
        return toSave;
    }

    public int size() {
        return tasks.size();
    }

}

