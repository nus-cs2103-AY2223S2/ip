public class ToDos extends Task {
    private String code;

    public ToDos(String msg) {
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
