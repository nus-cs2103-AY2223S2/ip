public class Task {
    private final String str;
    private boolean checked;
    public Task(String str, boolean checked) {
        this.str = str;
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public String getStr() {
        return str;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
