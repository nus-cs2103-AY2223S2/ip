public class Task {
    protected String desc;
    protected boolean isMarked;
    protected static int count = 0;

    Task(String desc) {
        this.desc = desc;
        this.isMarked = false;
        Task.count ++;
    }

    public void mark() {
        if (isMarked) {
            System.out.println("This task has already been marked as done.");
        } else {
            isMarked = true;
            System.out.println("Great job at completing this task! I've marked it as done:");
            System.out.println(this);
        }
    }

    public void unMark() {
        if (!isMarked) {
            System.out.println("Oops! This task has not been marked as done before.");
        } else {
            isMarked = false;
            System.out.println("Alright, I've marked this task as not done yet:");
            System.out.println(this);
        }
    }

    public static int getCount() {
        return Task.count;
    }

    @Override
    public String toString() {
        if (isMarked) {
            return "[X] " + desc;
        } else {
            return "[ ] " + desc;
        }
    }

}
