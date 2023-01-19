public class tasks {
    private boolean mark;
    private String value;

    public tasks(boolean mark, String value) {
        this.mark = mark;
        this.value = value;
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


}
