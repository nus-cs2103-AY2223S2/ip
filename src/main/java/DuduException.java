public class DuduException extends Exception {
    private String detail;
    public DuduException(String msg) {
        super(msg);
    }
    public DuduException(String detail, String msg) {
        super(msg);
        this.detail = detail;
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
