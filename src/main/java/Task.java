public class Task {
    private String name;
    private boolean doneOrNot;
    private int rank;
    private static int numOfThings = 1;

    //constructor
    public Task(String name) {
        this.name = name;
        this.doneOrNot = false;
        this.rank = numOfThings;
        numOfThings += 1;
    }

    public String isCompleted() {
        if (!doneOrNot) {
            return "[ ] ";
        } else {
            return "[X] ";
        }
    }

    public void strike() {
        this.doneOrNot = true;
    }

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }
}
