public class todoList {
    private String[] arr = new String[100]; //1-based indexing
    private int todoCount;
    public todoList() {
        this.todoCount = 0;
    }

    public void add(String task) {
        ++this.todoCount;
        arr[todoCount] = task;
    }

    @Override
    public String toString() {
        String divider = "@~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~@\n";
        String output = divider;
        for (int i=1; i<=this.todoCount; i++) {
            output = output + i + ". " + arr[i] + "\n";
        }
        output = output + divider;
        return output;
    }
}
