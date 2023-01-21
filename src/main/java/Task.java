public class Task {
    String content;
    boolean isDone;

    Task(String content) {
        this.content = content;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getDone() {
        if(isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    @Override
    public String toString() {
        return "";
    }

}
