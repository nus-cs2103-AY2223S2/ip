public class ToDo extends Task {
    public ToDo(String desc) {
        super(desc);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String getDetailsToSave() {
        return String.format("todo\n%s", desc);
    }

}