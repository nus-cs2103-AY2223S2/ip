package Duke;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.time.LocalDate;

public class Saver {
    public Saver(){

    }
    public String save(TaskList taskList){
        return "Tasks saved.";
    }

    public TaskList load(){ //create the tasks file if it doesnt exist, otherwise returns the tasklist
        TaskList taskList = new TaskList();;
        return taskList;
    }

}
