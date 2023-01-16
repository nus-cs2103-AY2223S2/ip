public class Task {
    private String nameOfTask;
    private int indexOfTask;
    private static int totalNumOfTask = 1;

    public Task(String name) {
        this.nameOfTask = name;
        this.indexOfTask = totalNumOfTask;
        totalNumOfTask++;
    }

    public int getIndexOfTask() {
        return indexOfTask;
    }

    public String getNameOfTask() {
        return nameOfTask;
    }
}
