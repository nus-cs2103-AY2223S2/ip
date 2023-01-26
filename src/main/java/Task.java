public class Task {
    private boolean isMarked;
    private String taskName;
    private static String MARKED = "[X]";
    private static String UNMARKED = "[ ]";

    public Task(String taskName){
        this.taskName = taskName;
        this.isMarked = false;
    }

    //Marking the task as done
    public void mark(){
        this.isMarked = true;
    }

    //Unmark the task
    public void unmark(){
        this.isMarked = false;
    }

    @Override
    public String toString(){
        if (isMarked){
            return MARKED + " " + taskName;
        }else{
            return UNMARKED + " " + taskName;
        }
    }
}
