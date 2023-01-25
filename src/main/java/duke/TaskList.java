package duke;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import java.util.ArrayList;

public class TaskList{
    private ArrayList<Task> listOfTasks;
    public TaskList(){
        this.listOfTasks = new ArrayList<>();
    }

    public TaskList(ArrayList<String> taskList) throws DukeException {
        this.listOfTasks = new ArrayList<Task>();
        for (String data : taskList) {
            listOfTasks.add(Task.loadTask(data));
        }
    }
    public void addDeadline(String description, String by)  {
        Deadline deadline = new Deadline(description, by, false);
        listOfTasks.add(deadline);
    }
    public void addToDo(String description) {
        ToDo todo = new ToDo(description, false);
        listOfTasks.add(todo);
    }
    public void addEvent(String description, String from, String to) {
        Event event = new Event(description, from, to, false);
        listOfTasks.add(event);
    }
    public Task deleteTask(int index) {
        return listOfTasks.remove(index);
    }
    public void markTask(int index) {
        listOfTasks.get(index).markDone();
    }

    public void unmarkTask(int index) {
        listOfTasks.get(index).markUnDone();
    }
    public void printList() {
        int i = 1;
        System.out.println("\n\tHere are the tasks in your list:");
        for(Task task : listOfTasks) {
            System.out.println("\t"
                    + i
                    + ". "
                    + task.toString());
            i++;
        }
    }
    public int getSize() {
        return listOfTasks.size();
    }
    public boolean isEmpty(){
        return listOfTasks.isEmpty();
    }

    public void addTask(Task task){
        listOfTasks.add(task);
    }

    public Task getTask(int index) {
        return listOfTasks.get(index);
    }

    public ArrayList<Task> getListOfTasks(){
        return listOfTasks;
    }

    public String printTaskCount() {
        return "\tNow you have "
                + listOfTasks.size()
                + " tasks in the list.";
    }


}
