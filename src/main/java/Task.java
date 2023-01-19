public class Task {
    String name;
    boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        String str = "";
        if(done) {
            str = str + "[X]";
        } else {
            str = str + "[ ]";
        }
        return str + " " + this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(obj instanceof Task) {
            Task x = (Task) obj;
            if(this.name.equals(x.name)) {
                return true;
            }
        }
        return false;
    }
}
