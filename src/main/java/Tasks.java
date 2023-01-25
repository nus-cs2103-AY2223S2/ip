
public abstract class Tasks {
    private boolean isMarked;
    private String content;

    public Tasks(String Task_content) {
        this.content = Task_content;
        this.isMarked = false;
    }

    public Tasks(Boolean isMarked, String Task_content) {
        this.content = Task_content;
        this.isMarked = isMarked;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public String get_content () {
        return content;
    }

    public void mark() {
        System.out.println("        Nice! I've marked this task as done:");
        isMarked = true;
        System.out.println("        " + this);
    }

    public void unmark() {
        System.out.println("        OK, I've marked this task as not done yet:");
        isMarked = false;
        System.out.println("        " + this);
    }

    public abstract String addDivider();

    public boolean is_Marked() {
        return isMarked;
    }

    public String toString() {
        if(isMarked) {
            return "[X] " + content;
        } else {
            return "[ ] " + content;
        }
    }

}
