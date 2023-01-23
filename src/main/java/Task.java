public class Task {

    private boolean state;
    private String taskName;

    public Task(String taskName) {
        this(taskName,false);
    }

    public Task(String taskName, boolean state){
        this.state = state;
        this.taskName = taskName;
    }
    public void toggleState(){
        state = !state;
    }

    public boolean getState(){
        return state;
    }

    @Override
    public String toString(){
        return "[" + (state?"X":" ") + "] " + taskName;
    }

}
