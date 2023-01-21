package aqua.aquatask;


public class AquaEvent extends AquaTask {
    public static final String FROM_TAG = "from";
    public static final String TO_TAG = "to";

    private final boolean isComplete;
    private final String from;
    private final String to;


    public AquaEvent(String name, String from, String to) {
        this(name, false, from, to);
    }

    public AquaEvent(String name, boolean isComplete, String from, String to) {
        super(name);
        this.isComplete = isComplete;
        this.from = from;
        this.to = to;
    }


    @Override
    public AquaEvent mark(boolean isComplete) {
        return new AquaEvent(this.getName(), isComplete, this.from, this.to);
    }


    @Override
    public boolean isComplete() {
        return isComplete;
    }


    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }
}
