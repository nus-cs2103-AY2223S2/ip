public abstract class Task {
    private String taskText;
    private boolean status;

    public Task(String taskText) {
        this.taskText = taskText;
        this.status = false;
    }

    public void changeStatus() {
        if(this.status) {
            this.status = false;
        } else {
            this.status = true;
        }
    }

    public boolean getStatus() {
        return this.status;
    }

    private String getStatusText() {
        return status ? "X" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusText(), this.taskText);
    }



}
