import java.util.*;
public class TaskList{
    protected ArrayList<Task> taskList;
    public TaskList(){
        taskList = new ArrayList<>();
    }
    public String listTasks(){
        String list = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++){
            list += (i + 1) + "." + taskList.get(i).toString() + "\n";
        }
        return list;
    }

    public String addTask(String desc){
        Task todo = new Todo(desc);
        taskList.add(todo);
        return addTaskMsg(todo);
    }

    public String addTask(String desc, String by){
        Task deadline = new Deadline(desc, by);
        taskList.add(deadline);
        return addTaskMsg(deadline);
    }

    public String addTask(String desc, String from, String to){
        Task event = new Event(desc, from, to);
        taskList.add(event);
        return addTaskMsg(event);
    }

    private String addTaskMsg(Task task){
        int totalTasks = this.getSize();
        return "Got it, I have added this task:\n" + task.toString() + "\n" +
        "Now you have " + totalTasks + " tasks in the list.";
    }

    public String markTask(String index){
        int idx = Integer.parseInt(index) - 1;
        Task task = taskList.get(idx);
        task.updateState();
        return "Nice I have marked this task as done\n" + task.toString();
    }

    public String unmarkTask(String index){
        int idx = Integer.parseInt(index) - 1;
        Task task = taskList.get(idx);
        task.updateState();
        return "Ok I have marked as not done\n" + task.toString();
    }

    private int getSize(){
        return taskList.size();
    }

}
