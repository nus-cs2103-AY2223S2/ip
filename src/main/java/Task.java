public class Task {
    public String desc;
    public boolean isDone;

    public Task(String desc){
        this.desc = desc;
        this.isDone = false;
    }

    public boolean mark(){
        this.isDone = !this.isDone;
        return this.isDone;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + desc;
    }

    public String toString(int index) {
        return index + ". " + this;
    }
}
