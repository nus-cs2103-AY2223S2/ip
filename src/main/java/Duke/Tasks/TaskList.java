package Duke.Tasks;

import Duke.Exception.ProgramException;

import java.util.ArrayList;
import java.util.Collections;

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
        String parameters[] = data.split("] ",2);
        if(parameters.length!=2){
            throw new ProgramException("Save file data corrupted.");
        }
        String name = parameters[1];
        Task task = new ToDo(name.strip());
        if(parameters[0].equals("[X")){
            task.mark();
        } else if(parameters[0].equals("[ ")) {
            task.unMark();
        } else {
            throw new ProgramException("Save file data corrupted.");
        }
        return task;

    }
    public Task EventFromSave(String data) throws ProgramException{
        String parameters[] = data.split("] ",2);
        Boolean isdone = null;
        if(parameters.length!=2){
            throw new ProgramException("Save file data corrupted.");
        }

        if(parameters[0].equals("[X")){
            isdone = true;
        } else if(parameters[0].equals("[ ")) {
            isdone = false;
        } else {
            throw new ProgramException("Save file data corrupted.");
        }

        parameters = parameters[1].split("from: ", 2);
        if(parameters.length!=2){
            throw new ProgramException("Save file data corrupted.");
        }
        String name = parameters[0].strip();
        parameters = parameters[1].split("to: ", 2);
        if(parameters.length!=2){
            throw new ProgramException("Save file data corrupted.");
        }
        String start = parameters[0].strip();
        String end = parameters[1].strip();
        Task task = new Event(name,start,end);
        if(isdone){
            task.mark();
        }
        return task;

    }
    public Task DeadlineFromSave(String data) throws ProgramException{
        String parameters[] = data.split("] ",2);
        Boolean isdone = null;
        if(parameters.length!=2){
            throw new ProgramException("Save file data corrupted.");
        }
        if(parameters[0].equals("[X")){
            isdone = true;
        } else if(parameters[0].equals("[ ")) {
            isdone = false;
        } else {
            throw new ProgramException("Save file data corrupted.");
        }
        parameters = parameters[1].split("by: ", 2);
        if(parameters.length!=2){
            throw new ProgramException("Save file data corrupted.");
        }
        String name = parameters[0].strip();
        String end = parameters[1].strip();
        Task task = new Deadline(name,end);
        if(isdone){
            task.mark();
        }
        return task;
    }

    public ArrayList<String> tagsFromSave(String data) throws ProgramException{
        ArrayList<String> tags = new ArrayList<>();
        String[] parameters = data.split(" ",2);
        if(parameters.length!=2) {
            throw new ProgramException("Save file data corrupted.");
        } else if(!parameters[0].equals("tags:")) {
            System.out.println(parameters[0]);
            throw new ProgramException("Save file data corrupted.");
        }
        parameters[1] = parameters[1].replace("[","").replace("]","");
        parameters = parameters[1].split(" ");
        for(int i = 0; i< parameters.length; i++){
            if(!parameters[i].strip().equals("")){
                tags.add(parameters[i]);
            }
        }
        return tags;
    }

    public void fromSave(String saveData) throws ProgramException {
        ArrayList saveTaskList = new ArrayList<>();
        String data[] = saveData.split("\n");
        ArrayList tags = new ArrayList();
        Task task = null;
        this.numTasks = 0;
        for(int i = 0; i<data.length/2; i++){
            if(data[i*2].equals("")) continue;
            String parameters[] = data[i*2].split(" ",2);
            String type = parameters[0];
            if(type.equals("[T]")) {
                task = ToDoFromSave(parameters[1]);
            } else if(type.equals("[D]")) {
                task = DeadlineFromSave(parameters[1]);
            } else if(type.equals("[E]")) {
                task = EventFromSave(parameters[1]);
            } else {
                throw new ProgramException("Save file data corrupted.");
            }
            tags = tagsFromSave(data[i*2+1]);
            if(tags.size() != 0) {
                task.setTags(tags);
            }
            saveTaskList.add(task);

        }
        this.numTasks = data.length/2;
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
