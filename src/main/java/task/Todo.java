package task;
public class Todo extends Task {
    private String code;

    public Todo(String msg) {
        super(msg);
        this.code = "[T]";
    }

    public String getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        return code + super.toString();
    }

}
