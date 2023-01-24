public class Task {
    private final String instruction;
    private boolean status;

    public Task(String instruction) {
        this.instruction = instruction;
        this.status = false;
    }

    public void complete() {
        if (this.status) {
            System.out.println("Mission is already completed.");
            return;
        }
        this.status = true;
        System.out.println("Mission Completed!\n" + this);
    }

    public void incomplete() {
        if (!this.status) {
            System.out.println("Mission is originally incomplete.");
            return;
        }
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
