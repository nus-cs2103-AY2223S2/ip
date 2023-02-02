package duke.tasklist;
import duke.dukeexceptions.MissingArgumentException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

public class TaskList {
    protected Task[] list;
    protected int len;

    public TaskList(){
        this.list = new Task[100];
        this.len = 0;
    }

    public Task showTask(int index){
        return list[index];
    }

    public void list(){
        System.out.print("  Here are the tasks in your list:\n");
        for(int i = 0; i < this.len ; i++){
            int index = i + 1;
            String item = "  " + index + ". " + list[i].toString();
            System.out.print(item);
        }
    }

    public void setDone(int index){
        this.list[index].setDone();
        String reply = "  Nice! I've marked this task as done:\n"
                + "    " + list[index].toString();
        System.out.print(reply);
    }

    public void setNotDone(int index){
        this.list[index].setNotDone();
        String reply = "  OK, I've marked this task as not done:\n"
                + "    " + list[index].toString();
        System.out.print(reply);
    }

    public void addToDo(String description) throws MissingArgumentException {
        if (description.trim().equals("")) {
            throw new MissingArgumentException("The description of a todo cannot be empty.");
        }

        this.list[len] = new ToDo(description);
        this.len++;

        String reply = "Got it. I've added this task:\n"
                + "    " + taskToString(len - 1)
                + "  Now you have " + this.len + " tasks in the list.\n";
        System.out.print(reply);
    }

    public void addDeadline(String requestContent) throws MissingArgumentException {
        String[] splitWithBy = requestContent.split(" /by ", 2);
        String description = splitWithBy[0].trim();

        if (description.equals("")) {
            throw new MissingArgumentException("The description of a deadline cannot be empty.");
        } else if (splitWithBy.length != 2 || splitWithBy[1].trim().equals("")) {
            throw new MissingArgumentException("The deadline cannot be empty.");
        }

        String by = splitWithBy[1].trim();
        this.list[len] = new Deadline(description, by);
        this.len++;

        String reply = "  Got it. I've added this task:\n"
                + "    " + taskToString(len - 1)
                + "  Now you have " + this.len + " tasks in the list.\n";
        System.out.print(reply);
    }

    public void addEvent(String requestContent) throws MissingArgumentException{
        String[] splitFrom = requestContent.split(" /from ", 2);
        String description = splitFrom[0].trim();

        if (description.equals("")) {
            throw new MissingArgumentException("The description of an event cannot be empty.");
        } else if (splitFrom.length != 2) {
            throw new MissingArgumentException("The from cannot be empty.");
        }

        String[] splitTo = splitFrom[1].split(" /to ", 2);
        String from = splitTo[0].trim();
        String to = splitTo[1].trim();

        if (from.equals("")) {
            throw new MissingArgumentException("The from cannot be empty.");
        } else if (splitTo.length != 2 || splitTo[1].trim().equals("")) {
            throw new MissingArgumentException("The to cannot be empty.");
        }

        this.list[len] = new Event(description, from, to);
        this.len++;

        String reply = "  Got it. I've added this task:\n"
                + "    " + taskToString(len - 1)
                + "  Now you have " + this.len + " tasks in the list.\n";
        System.out.print(reply);
    }

    public String taskToString(int index){
        return list[index].toString();
    }

    public void delete(int index) {
        Task deletedTask = list[index];
        for (int i = index; i < this.len; i++){
            this.list[i] = this.list[i+1];
        }

        this.len--;
        String reply = "  Noted. I've removed this task:\n"
                + "    " + deletedTask.toString()
                + "  Now you have " + this.len + " tasks in the list.\n";
        System.out.print(reply);
    }

    public void addTask(Task task){
        this.list[len] = task;
        this.len++;
    }

    public String saveTaskList(){
        String result = "";
        for (int i = 0; i < this.len; i++){
            result += list[i].saveTask() + "\n";
        }
        return result;
    }
}
