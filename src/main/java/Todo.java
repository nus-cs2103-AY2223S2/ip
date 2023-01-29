public class Todo extends Task {
    public Todo(String str, boolean checked) {
        super(str, checked);
    }
    @Override
    public String toString() {
        boolean checked = this.isChecked();
        String str = this.getStr();
        if (checked) {
            return "[T][X] " + str;
        } else{
            return "[T][ ] " + str;
        }
    }
}
