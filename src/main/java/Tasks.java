public class Tasks {
    private String content;
    private Boolean is_Done;
    protected char type;
    public Tasks(String content) {
        this.content = content;
        this.is_Done = false;
    }
    public String seeTaskContent() {
        return content;
    }

    public void markTask(boolean Done) {
        this.is_Done = Done;
    }

    public String getDuration() {
        return "";
    }

    //Credits: Copied from https://nus-cs2103-ay2223s2.github.io/website/schedule/week2/project.html
    public String getStatusIcon() {
        return (this.is_Done ? "X" : " "); // mark done task with X
    }
    public char getTypeIcon() {
        return this.type; // mark done task with X
    }
}
