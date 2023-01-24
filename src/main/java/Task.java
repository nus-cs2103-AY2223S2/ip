public class Task {
    private boolean mark;
    private String value;

    public Task(String value) {
        this.value = value;
        this.mark = false;
    }

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        return "[" + (isMark() ? "X" : " ") + "] " + getValue()  ;
    }
}
