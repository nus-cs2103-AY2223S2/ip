package types.data;

public abstract class Task {
    protected String name;
    protected String typeMark;
    protected TaskStatus status;

    protected Task(String n, String tm) {
        name = n;
        typeMark = tm;
        status = TaskStatus.INCOMPLETE;
    }

    public String getDoneMark() {
        switch (status) {
            case COMPLETED:
                return "X";
            case INCOMPLETE:
                return " ";
        }
        return "ERROR";
    }

    public String getTypeMark() {
        return String.format("[%s]", typeMark);
    }

    public String getName() {
        return name;
    }

    public void setDone() {
        status = TaskStatus.COMPLETED;
    }

    public void setUndone() {
        status = TaskStatus.INCOMPLETE;
    }

    @Override
    public String toString() {
        return String.format("%s%s %s", getTypeMark(), getDoneMark(), name);
    }
}
