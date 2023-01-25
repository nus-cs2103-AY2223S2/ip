public class ToDo extends Task{

    public ToDo(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
