package duke;
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private static List<Task> toDoList = new ArrayList<>();

    public static void markDone(int ind){
        toDoList.get(ind).markDone();
    }

    public static void markUndone(int ind){
        toDoList.get(ind).markUndone();
    }
    
    public static void add(Task task){
        toDoList.add(task);
    }
    
    public static void remove(int ind){
        toDoList.remove(ind);
    }

    public static Task get(int ind){
        return toDoList.get(ind);
    }

    public static void print(){
        for (int i = 0; i < toDoList.size(); i++){
            System.out.print(Integer.toString(i+1) + ". ");
            toDoList.get(i).printTask();
        }
    }

    public static int size(){
        return toDoList.size();
    }
}
