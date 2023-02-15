import java.util.Scanner;

public class Deadlines extends Item {
    private static final String TYPE = "[D]";
    private String deadline;

    public Deadlines(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    public static Deadlines createDeadline(Scanner scanner) throws DukeyException {
        System.out.print("Deadline task name: ");
        String deadlineName = scanner.nextLine();
        if (deadlineName.equals("")) {
            throw new DukeyException("Error!! Deadline task name cannot be empty!!");
        }
        System.out.print("Deadline: ");
        String deadlineTime = scanner.nextLine();
        if (deadlineTime.equals(""))  {
            throw new DukeyException("Error!! Deadline time cannot be empty!!");
        }

        return new Deadlines(deadlineName.strip(), deadlineTime.strip());

    }

    @Override
    public String messageWhenAdded() {
        return "DukeyList just added a new deadline:";
    }

    @Override
    public String toString() {
        if (this.isDone()) {
            return TYPE + "[X] " + this.getName() + " (by " + this.deadline + ")";
        }
        return TYPE + "[ ] " + this.getName() + " (by " + this.deadline + ")";
    }
}
