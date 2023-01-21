package aqua.aquatask;


public abstract class AquaTask {
    public static final String IS_COMPLETED_TAG = "completed";

    private final String name;


    public AquaTask(String name) {
        this.name = name;
    }

    public AquaTask(AquaTask task) {
        this.name = task.name;
    }


    public abstract AquaTask mark(boolean isComplete);
    
    public abstract boolean isComplete();


    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return String.format("%s %s",
            getStatusString(),
            getName()
        );
    }

    private String getStatusString() {
        return isComplete() ? "[X]" : "[ ]";
    }
}
