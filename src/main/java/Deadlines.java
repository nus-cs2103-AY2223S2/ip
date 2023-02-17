import java.util.Scanner;

public class Deadlines extends Item {
    private static final String TYPE = "[D]";
    private String deadline;

    public Deadlines(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    public Deadlines(String name, String deadline, boolean isMarked) {
        super(name, isMarked);
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

    public String getDeadline() {
        return this.deadline;
    }

    public static Deadlines createDeadlineFromLog(String[] logStringArray) {
        String name = logStringArray[2];
        String deadline = logStringArray[3];
        boolean isMarked = !logStringArray[1].equals("0");
        return new Deadlines(name, deadline, isMarked);
    }

    @Override
    public String messageWhenAdded() {
        return "DukeyList just added a new deadline:";
    }

    @Override
    public String getLogString() {
        return "D" + "/" + this.isDoneStatus() + "/" + this.getName() + "/" + this.getDeadline();
    }

    @Override
    public String toString() {
        if (this.isDone()) {
            return TYPE + "[X] " + this.getName() + " (by " + this.deadline + ")";
        }
        return TYPE + "[ ] " + this.getName() + " (by " + this.deadline + ")";
    }
}
