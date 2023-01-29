import java.io.File; 
import java.io.IOException;

public class Saver {
    public Saver(){

    }
    public String save(Task[] tasks){
        return "Tasks saved.";
    }

    public String delete(Task task){
        return "Task deleted";
    }
    public String load(){
        return "Tasks loaded.";
    }

}
