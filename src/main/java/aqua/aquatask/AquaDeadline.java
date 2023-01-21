package aqua.aquatask;


public class AquaDeadline extends AquaTask {
    private final boolean isComplete;
    private final String by;


    public AquaDeadline(String name, String by) {
        this(name, false, by);
    }

    public AquaDeadline(String name, boolean isComplete, String by) {
        super(name);
        this.isComplete = isComplete;
        this.by = by;
    }


    @Override
    public AquaDeadline mark(boolean isComplete) {
        return new AquaDeadline(this.getName(), this.isComplete, this.by);
    }


    @Override
    public boolean isComplete() {
        return this.isComplete;
    }


    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
