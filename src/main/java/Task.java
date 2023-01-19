public class Task {
    String name;
    boolean done;
    char type;
    String notes = null;

    public Task(String name) {
        this.name = name;
        this.done = false;
        this.type = ' ';
    }

    public Task(String name, char type) {
        this.name = name;
        this.done = false;
        this.type = type;
    }

    public Task(String name, char type, String notes) {
        this.name = name;
        this.type = type;
        this.notes = notes;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        String str = "[" + this.type + "] ";
        if(done) {
            str = str + "[X]";
        } else {
            str = str + "[ ]";
        }
        if(notes == null) {
            return str + " " + this.name;
        } else {
            return str + " " + this.name + " " + notes;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(obj instanceof Task) {
            Task x = (Task) obj;
            if(this.name.equals(x.name) && this.type == x.type) {
                return true;
            }
        }
        return false;
    }
}
