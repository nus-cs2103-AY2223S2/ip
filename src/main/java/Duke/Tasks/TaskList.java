package Duke.Tasks;

import Duke.Exception.ProgramException;

import java.util.ArrayList;

/**
 * Class representing an array of tasks.
 * @author Bryan Juniano
 */

public class TaskList {
    private ArrayList<Task> taskList;
    private int numTasks;

    public TaskList() {
        this.taskList = new ArrayList<>();
        this.numTasks = 0;
    }

    public int size(){
        return this.numTasks;
    }

    public String processTask(Task task){
        this.taskList.add(task);
        this.numTasks +=1;
        return "Task added. You now have:" + this.numTasks + " task(s).";
    }
    public String addTask(String name){
        Task task = new ToDo(name);
        return processTask(task);
    }

    public String addTask(String name, String end){
        Task task = new Deadline(name,end);
        return processTask(task);
    }

    public String addTask(String name, String  start, String  end){
        Task task = new Event(name,start,end);
        return processTask(task);
    }

    public ArrayList getTasks(){
        return taskList;
    }
    public String listTasks(){
        String output = "";
        for(int i = 0; i<taskList.size(); i++){
            output += taskList.get(i) + "\n";
        }
        if(output.equals("")){
            output += "oops! You have no tasks at the moment.";
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

    public String toSave(){
        String output = "";
        for(int i = 0; i<numTasks; i++){
            output+=taskList.get(i)+"\n";
        }
        return output;
    }

    public Task ToDoFromSave(String data) throws ProgramException{
        String parameters[] = data.split(" ",2);
        if(parameters.length!=2){
            throw new ProgramException("Save file data corrupted.");
        }
        Task task = new ToDo(parameters[1]);
        if(parameters[0].equals("[X]")){
            task.mark();
        } else if(parameters[0].equals("[ ]")) {
            task.unMark();
        } else {
            throw new ProgramException("Save file data corrupted.");
        }
        return new ToDo(parameters[1]);

    }
    public Task EventfromSave(String data) throws ProgramException{
        String parameters[] = data.split(" ", 2);
        Task task = new ToDo(parameters[1]);
        return task;

    }
    public Task DeadlinefromSave(String data) throws ProgramException{
        String parameters[] = data.split(" ", 2);
        Task task = new ToDo(parameters[1]);
        return task;

    }
    public void fromSave(String saveData) throws ProgramException {
        ArrayList saveTaskList = new ArrayList<>();
        String data[] = saveData.split("\n");
        this.numTasks = 0;
        for(int i = 0; i<data.length; i++){
            if(data[i].equals("")) continue;
            String parameters[] = data[i].split(" ",2);
            String type = parameters[0];
            if(type.equals("[T]")) {
                ToDoFromSave(parameters[1]);
            } else if(type.equals("[D]")) {
                DeadlinefromSave(parameters[1]);
            } else if(type.equals("E")) {
                EventfromSave(parameters[1]);
            } else {
                throw new ProgramException("Save file data corrupted.");
            }


        }
        this.taskList = saveTaskList;

    }
    public String findTasks(String query){
        int matches = 0;
        String output = "Here are your matching tasks:\n";
        for (int i = 0; i < this.numTasks; i++) {
            if(taskList.get(i).toString().contains(query)){
                matches += 1;
                output+= i+1 +". " + taskList.get(i) + "\n";
            }
        }
        if(matches == 0){
            return "Oops! Could not find any tasks matching your query.";
        }
        return output;
    }

    public String tagTask(int index, ArrayList<String> tags){
        this.taskList.get(index-1).setTags(tags);
        return "Task " + index + " tagged.";
    }
    public String untagTask(int index, ArrayList<String> tags){
        this.taskList.get(index-1).removeTags(tags);
        return "Task " + index + " untagged.";
    }
}
