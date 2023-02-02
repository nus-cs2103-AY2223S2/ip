package duke.taskmanager;

/**
 * Parent class of different types of Tasks
 */
public class Tasks {
    final protected String desc;
    protected Boolean done;

    /*Constructor for generic task*/
    public Tasks(String str) throws Exception {
        this.desc = str;
        this.done = false;
    }

    /*returns description of task as is overwritten by inheritors*/
    public String getDesc(){
        return desc;
    }

    /*marks a task as complete*/
    public void mark() {
        this.done = true;
    }

    /*un-marks a task as not complete*/
    public void unmark() {
        this.done = false;
    }

    /*returns symbol representing the boolean value of done*/
    public String completed() {
        if(done) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /*returns empty string to be overwritten by inheritors*/
    public String icon() {
        return "";
    }

    /*returns generic message to indicate that task has been added*/
    public String added() {
        return "Got it I've added this task: \n";
    }

    /*returns generic message to indicate that task has been removed */
    public String deleted() {return "Noted. I've removed this task:\n";}
}
