package duke.task;

import duke.task.Task;

public class Todo extends Task {
    public Todo(String type, String detail, boolean marked){
        super(type, detail, marked);
    }

    public Todo(String type, String detail){
        super(type, detail);
    }


    /**
     * Returns todo printed out properly.
     *
     * @return todo in full details.
     */
    @Override
    public String toString(){
        if (marked) {
            return "[T][X] " + super.detail;
        } else {
            return "[T][ ] " + super.detail;
        }
    }
}
