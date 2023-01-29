import java.util.ArrayList;
import java.time.LocalDate;
public class TaskList {
    private ArrayList<Task> taskList;
    private int numTasks;

    public TaskList() {
        this.taskList = new ArrayList<>();
        this.numTasks = 0;
    }
    public String processTask(Task task){
        this.taskList.add(task);
        this.numTasks +=1;
        return "Task added. You now have:" + this.numTasks + " task(s).";
    }
    public String addTask(String name){ //todo
        Task task = new ToDo(name);
        return processTask(task);
    }

    public String addTask(String name, LocalDate end){ //deadline
        Task task = new Deadline(name,end);
        return processTask(task);
    }

    public String addTask(String name, LocalDate  start, LocalDate  end){ //event
        Task task = new Event(name,start,end);
        return processTask(task);
    }

    public String listTasks(){
        String output = "";
        for(int i = 0; i<taskList.size(); i++){
            output += taskList.get(i) + "\n";
        }
        return output;
    }
    public String markTask(int index){
        this.taskList.get(index-1).mark();
        return "Task " + index + " marked.";
    }

    public String unmarkTask(int index){
        this.taskList.get(index-1).unMark();
        return "Task " + index + " unmarked.";
    }

    public String deleteTask(int index){
        this.taskList.remove(index-1);
        this.numTasks -= 1;
        return "Task " + index + " deleted.";
    }
}
