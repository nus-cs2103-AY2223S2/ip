package task;

public class Task {
    private boolean isChecked;
    private String details;

    public Task(String details) {
        isChecked = false;
        this.details = details;
    }

    public void setChecked(Boolean checked) {
        this.isChecked = checked;
    }

    @Override
    public String toString(){
        String checkSymbol = " ";
        if (isChecked) {
            checkSymbol = "X";
        }
        return "[" + checkSymbol + "] " + details;
    }

    public String toStorageString() {
        String checkSymbol = "0";
        if (isChecked) {
            checkSymbol = "1";
        }
        return checkSymbol + "#" + details;
    }
}
