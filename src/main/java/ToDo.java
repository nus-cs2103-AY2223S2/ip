public class ToDo extends Task {
    public ToDo(String des) {
        super(des);
    }

    @Override
    public String getStatusIcon() {
        return String.format("[T]%s", super.getStatusIcon());
    }
}