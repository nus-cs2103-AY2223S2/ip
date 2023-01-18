public abstract class UserTask implements Runnable {
    private final String line;

    public UserTask(String line) {
        this.line = line;
    }

    public abstract void run();
}