import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    private int numTasks;

    public TaskList() {
        this.taskList = new ArrayList<>();
        this.numTasks = 0;
    }

    public int getCount(){
        return this.numTasks;
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
