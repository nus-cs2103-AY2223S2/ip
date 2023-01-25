public class Task {
    int done;
    String msg;
    public Task(String msg) {
        this.msg = msg;
        this.done = 0;
    }

    @Override
    public String toString() {
        if (this.done==0)  return "[ ] " + this.msg;
        else               return "[X] " + this.msg;
    }
}

