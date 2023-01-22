public class Entry {
    private boolean checked;
    private String details;
    public Entry(String details) {
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
}
