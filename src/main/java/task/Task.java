package task;

public class Task {
    private boolean checked;
    private String details;

    public Task(String details) {
        checked = false;
        this.details = details;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString(){
        String checkSymbol = " ";
        if (checked) {
            checkSymbol = "X";
        }
        return "[" + checkSymbol + "] " + details;
    }

    public String toStorageString() {
        String checkSymbol = "0";
        if (checked) {
            checkSymbol = "1";
        }
        return checkSymbol + "#" + details;
    }
}
