package skittles;
public abstract class Task {
    private String name;
    private boolean doneOrNot;
    //private int rank;
    //private static int numOfThings = 1;

    //constructor
    public Task(String name) {
        this.name = name;
        this.doneOrNot = false;
        //this.rank = numOfThings;
        //numOfThings += 1;
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

    public void unstrike() {
        this.doneOrNot = false;
    }

    public String getName() {
        return name;
    }

    public String getCompletedOrNotSign() {
        if (!doneOrNot) {
            return "[ ] ";
        } else {
            return "[X] ";
        }
    }

    /* public int getRank() {
        return rank;
    } */

    /* public void setRank(int n) {
        this.rank = n;
    } */

    public Boolean getDoneOrNot() {
        return this.doneOrNot;
    }

    @Override
    public String toString() {
        return this.getCompletedOrNotSign() + this.name;
    }

    public abstract String convertToText();


}
