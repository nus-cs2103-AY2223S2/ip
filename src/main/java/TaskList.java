import java.util.ArrayList;

public class TaskList{
    private ArrayList<Task> listOfTasks;
    public TaskList(){
        this.listOfTasks = new ArrayList<>();
    }

    public TaskList(ArrayList<String> taskList) {
        this.listOfTasks = new ArrayList<Task>();
        for (String data : taskList) {
            listOfTasks.add(Task.loadTask(data));
        }
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
    public boolean isEmpty(){
        return listOfTasks.isEmpty();
    }

    public void addTask(Task task){
        listOfTasks.add(task);
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
