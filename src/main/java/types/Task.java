package types;

public abstract class Task {
    protected String name;
    protected String typeMark;
    protected boolean done;

    protected Task(String n, String tm) {
        name = n;
        typeMark = tm;
        done = false;
    }

    public String getDoneMark() {
        return done ? "[X]" : "[ ]";
    }

    public String getTypeMark() {
        return String.format("[%s]", typeMark);
    }

    public String getName() {
        return name;
    }

    public void setDone() {
        done = true;
    }

    public void setUndone() {
        done = false;
    }

    @Override
    public String toString() {
        return String.format("%s%s %s", getTypeMark(), getDoneMark(), name);
    }
}
