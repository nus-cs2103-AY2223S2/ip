import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.Collections;

public class TaskList {
    private static final int MAX_SIZE = 100;
    //private static final int GAPS = 11;
    private static final String GAP = "             ";
    private ArrayList<Task> tdl;
    
    TaskList() {
        this.tdl = new ArrayList<Task>();
    }
    
    int getCount() {
        return this.tdl.size();
    }
    
    String addTask(Task task) {
        this.tdl.add(task);
        return String.format("\"%s\" added!",
                task.toString());
    }
    
    ArrayList<String> listTasks() {
        return listFilter(t -> true);
    }
    
    ArrayList<String> listFilter(Predicate<Task> pred) {
        ArrayList<String> arr = new ArrayList<String>();
        
        for (int i = 0; i < getCount(); i++) {
            if (pred.test(this.tdl.get(i))) {
                arr.add(String.format("%3d. %s",
                        i + 1, this.tdl.get(i)));
            }
        }
        
        return arr;
    }
    
    boolean notInRange(int num) {
        return (num <= 0 || num > getCount());
    }
    
    String rangeError(int num) {
        if (num <= 0) {
            return "wadahek pls";
        } else if (num > getCount()) {
            return "Hm, you don't have that many tasks!";
        } else {
            return "All's good! That index is in range :D";
        }
    }
    
    String mark(int num) {
        if (notInRange(num)) {
            return rangeError(num);
        }
        
        int index = num - 1;
        
        if (tdl.get(index).yesDo()) {
            return "Woohoo! You've completed:\n" + GAP
                    + tdl.get(index).toString();
        } else {
            return "You've already done:\n" + GAP
                    + tdl.get(index).toString();
        }
    }
    
    String unmark(int num) {
        if (notInRange(num)) {
            return rangeError(num);
        }
        
        int index = num - 1;
        
        if (tdl.get(index).noDo()) {
            return "Aw, okay :( I've unmarked:\n" + GAP
                    + tdl.get(index).toString();
        } else {
            return "Hm, you haven't yet done:\n" + GAP
                    + tdl.get(index).toString();
        }
    }
    
    String delTask(int num) {
        if (notInRange(num)) {
            return rangeError(num);
        }
        
        return "Got it! Deleted:\n" + GAP
                + tdl.remove(num - 1).toString();
    }
    
    void sort() {
        Collections.sort(tdl);
    }
}