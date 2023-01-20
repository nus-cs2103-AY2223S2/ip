public class ToDo extends Item {
    private static final String TYPE = "[T]";
    public ToDo(String name) {
        super(name);
    }


    @Override
    public String messageWhenAdded() {
        return "DukeyList just added a new todo:";
    }

    @Override
    public String toString() {
        if (this.isDone()) {
            return TYPE + "[X]" + " " + this.getName();
        }

        return TYPE + "[ ]" + " " + this.getName();
    }
}

