package duke.task;

import duke.task.Task;

public class ToDo extends Task {

    public ToDo(String name, String inpString){
        super(name, inpString);
    }

    /**
     * Output the todo task for user to see.
     */
    @Override
    public void printTask(){
        System.out.print("[T]");
        super.printTask();
        System.out.println();
    }

}
