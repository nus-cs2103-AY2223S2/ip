public abstract class Task {

    public String task_name;
    public boolean status;
    public Task(String str) {
        task_name = str;
        status = false;
    }

    public void toggleTrue() {
        status = true;
    }

    public void toggleFalse() {
        status = false;
    }
}
