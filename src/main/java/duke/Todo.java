package duke;

import util.*;


import java.util.ArrayList;

public class Todo extends Task {

    static String divider = "    ═══*.·:·.☽✧    ✦    ✧☾.·:·.*═══";

    public Todo(String description) {
        super(description);
        this.taskType = "T";
    }

    public Todo(boolean isDone, String description) {
        super(isDone, description);
        this.taskType = "T";    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    
  
    public static void createTodoTask(ArrayList<Task> array, String[] splitInput) {
        if (splitInput.length == 1 || splitInput[1].equals("")){
            try {
                throw new DukeException("todo");
            } catch (Exception e) {
                System.out.println(divider);
                System.out.println(e.toString());
                System.out.println(divider);
            }
        } else {
            for(int j=2; j< splitInput.length; j++){
                splitInput[1] = splitInput[1] + " " + splitInput[j];
            }
               
            String desc = splitInput[1];
            Todo t = new Todo(desc);
            array.add(t);

            Ui.addTask(array, t);
        }
        
    }

    @Override
    public String saveFormat() {
        String divider = " | ";
        int marked = this.getIsDone() ? 1 : 0;
        return "T" + divider + marked + divider + description ;
    }
    

}