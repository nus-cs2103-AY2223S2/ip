package Duke.Tasks;

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

    public void fromSave(String saveData){
        ArrayList saveTaskList = new ArrayList<>();
        String data[] = saveData.split("\n");
        this.numTasks = 0;
        Task task = new ToDo("");
        for(int i = 0; i<data.length; i++){
            if(data[i].equals("")) continue;
            String parameters[] = data[i].split("\\|");
            String type = parameters[0].replace("[", "").replace("]", "").replace(" ","");
            String mark = parameters[1].replace("[", "").replace("]", "").replace(" ","");
            String name = parameters[2].trim();
            if(type.equals("T")){
                task = new ToDo(name);
            }
            else if(type.equals("E")){
                String start = parameters[4].trim();
                String end = parameters[6].trim();
                task = new Event(name, start, end);
            }
            else if(type.equals("D")){
                String start = parameters[4].trim();
                task = new Deadline(name,start);
            }

            if(mark.equals("X")){
                task.mark();
            }
            saveTaskList.add(task);
            this.numTasks += 1;
        }
        this.taskList = saveTaskList;

    }
    public String findTasks(String query){
        int matches = 0;
        String output = "Here are your matching tasks:\n";
        for (int i = 0; i < this.numTasks; i++) {
            if(taskList.get(i).toString().contains(query)){
                matches += 1;
                output+= i +". " + taskList.get(i) + "\n";
            }
        }
        if(matches == 0){
            return "Oops! Could not find any tasks matching your query";
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
