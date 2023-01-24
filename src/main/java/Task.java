import java.time.LocalDateTime;

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

    public String getCurrentStatus() {
        return this.status ? "1" : "0";
    }

    private String getStatusText() {
        return status ? "X" : " ";
    }

    public String getTaskText() {
        return this.taskText;
    }

    public abstract String writeFile();

    public abstract LocalDateTime getDate();

    public abstract String getTaskType();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusText(), this.taskText);
    }



}
