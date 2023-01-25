public class Task {
    final String name;
    boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }
    public  Task(String name, String isDone) {
        this.name = name;
        this.isDone = isDone.equals("true");
    }

    public String getName() {
        return name;
    }
    public boolean getDone() {
        return isDone;
    }
    public boolean markDone() {
        this.isDone = true;
        return true;
    }
    public boolean unmarkDone() {
        this.isDone = false;
        return false;
    }
    public String saveFormat() {
        return "T;" + this.name + ";" +this.isDone;
    }
    
    @Override
    public String toString() {
        String box;
        if (isDone) {
            box = "[X] ";
        } else {
            box = "[ ] ";
        }
        return"[T]" + box + this.getName();
    }
}
