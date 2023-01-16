public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    @Override
    public void mark() {
        super.mark();
        System.out.println(String.format(" [%s][%s] %s",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description));
    }

    @Override
    public void unmark() {
        super.unmark();
        System.out.println(String.format(" [%s][%s] %s",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description));
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return String.format(String.format("[%s][%s] %s",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description));
    }
}
