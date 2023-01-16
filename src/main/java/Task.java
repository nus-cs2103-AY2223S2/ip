public class Task {
    private final String instruction;
    private boolean status;

    public Task(String instruction) {
        this.instruction = instruction;
        this.status = false;
    }

    public void complete() {
        this.status = true;
        System.out.println("Mission Completed!\n" + this);
    }

    public void incomplete() {
        this.status = false;
        System.out.println("Mission Re-initialised\n" + this);
    }

    @Override
    public String toString() {
        if (status) {
            return "[X] " + instruction;
        }
        return "[ ] " + instruction;
    }
}
