package todo;

import Task.Task;

public class todo extends Task {
    
    public todo(String description) {
        super(description);
    }

    

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
