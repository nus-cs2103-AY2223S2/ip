public class UserTask {
    private final String description;
    public boolean status;

    public UserTask(String line) {
        this.description = line;
        status = false;
    }

    @Override
    public String toString() {
        return Resource.statusFmt(status) + ' ' + description;
    }
}