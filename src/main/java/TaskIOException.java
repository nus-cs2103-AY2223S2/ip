public class TaskIOException extends DuduException{
    private String msg;
    public TaskIOException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String toString() {
        return this.msg;
    }
}
