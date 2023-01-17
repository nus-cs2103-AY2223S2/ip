
public class Tasks {
    private boolean marked;
    private String content;

    public Tasks(String Task_content) {
        this.content = Task_content;
        this.marked = false;
    }

    public String get_content () {
        return content;
    }

    public void mark() {
        System.out.println("    Nice! I've marked this task as done:");
        marked = true;
        System.out.println(this);
    }

    public void unmark() {
        System.out.println("    OK, I've marked this task as not done yet:");
        marked = false;
        System.out.println(this);
    }

    public boolean is_Marked() {
        return marked;
    }

    public String toString() {
        if(marked) {
            return "[X] " + content;
        } else {
            return "[ ] " + content;
        }
    }
}
