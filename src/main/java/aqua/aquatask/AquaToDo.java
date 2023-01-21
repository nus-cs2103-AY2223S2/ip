package aqua.aquatask;


public class AquaToDo extends AquaTask {
    private final boolean isComplete;

    
    public AquaToDo(String name) {
        this(name, false);
    }

    public AquaToDo(String name, boolean isComplete) {
        super(name);
        this.isComplete = isComplete;
    }


    @Override
    public AquaToDo mark(boolean isComplete) {
        return new AquaToDo(this.getName(), this.isComplete);
    }

    
    @Override
    public boolean isComplete() {
        return this.isComplete;
    }


    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
