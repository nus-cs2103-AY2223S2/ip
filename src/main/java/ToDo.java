public class ToDo extends Task {
    private ToDo(String description) {
        super(TaskType.TODO, description);
    }

    public static ToDo to(String str) {
        return new ToDo(str);
    }

    @Override
    public String toString() {
        String str = super.toString();
        str += "\n";
        return str;
    }
}
