import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

public class TaskList extends ArrayList<Task>{
    private static String tasksFile = new File("data/duke.txt").getAbsolutePath();
    private static Path tfPath = Paths.get(tasksFile);
    public TaskList(){
        super();
        File f = new File(tasksFile);
        //if file exists
        if (f.exists()) {
            //load tasks into arrayList
            try{
                //load existing values into TaskList
                ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(tfPath, StandardCharsets.UTF_8));
                //System.out.println(fileContent.size());
                for (int i = 0; i < fileContent.size(); i++) {
                    String currLine = fileContent.get(i);
                    //parse String into two
                    String splitLine[] = currLine.split(" \\| ");
                    //check first index
                    String type = splitLine[0];
                    //switch for all 3 events
                    switch (type){
                    case "T":
                        this.addTask(splitLine[2]);
                        if (splitLine[1].equals("1")){
                            //mark task
                            this.markTask(this.getSize() - 1);
                        }
                        break;
                    case "D":
                        this.addTask(splitLine[2], splitLine[3]);
                        if (splitLine[1].equals("1")){
                            //mark task
                            this.markTask(this.getSize() - 1);
                        }
                        break;
                    case "E":
                        this.addTask(splitLine[2], splitLine[3], splitLine[4]);
                        if (splitLine[1].equals("1")){
                            //mark task
                            this.markTask(this.getSize() - 1);
                        }
                        break;
                    }
                }
            } catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public String deleteTask(String taskNum){
        int index = Integer.parseInt(taskNum) - 1;
        Task task = this.remove(index);
        return "Noted. I've removed this task:\n" + task.toString()
                + "\nNow you have " + this.getSize() + " tasks in the list";
    }

    public String listTasks(){
        String list = "Here are the tasks in your list:\n";
        for (int i = 0; i < this.size(); i++){
            list += (i + 1) + "." + this.get(i).toString() + "\n";
        }
        return list;
    }

    public String addTask(String desc){
        Task todo = new Todo(desc);
        this.add(todo);
        return addTaskMsg(todo);
    }

    public String addTask(String desc, String by){
        Task deadline = new Deadline(desc, by);
        this.add(deadline);
        return addTaskMsg(deadline);
    }

    public String addTask(String desc, String from, String to){
        Task event = new Event(desc, from, to);
        this.add(event);
        return addTaskMsg(event);
    }

    private String addTaskMsg(Task task){
        int totalTasks = this.getSize();
        return "Got it, I have added this task:\n" + task.toString() + "\n" +
        "Now you have " + totalTasks + " tasks in the list.";
    }

    public String markTask(int index){
        Task task = this.get(index);
        task.updateState();
        return "Nice I have marked this task as done\n" + task.toString();
    }

    public String unmarkTask(int index){
        Task task = this.get(index);
        task.updateState();
        return "Ok I have marked as not done\n" + task.toString();
    }

    public int getSize(){
        return this.size();
    }

}
