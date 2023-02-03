package Task;
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }



    public static Task getDescriptionFromString(String description) {
        String[] words = description.split("] ");
        String newDescription = words[1];
        String firstWord = words[0];
        char marked = firstWord.charAt(firstWord.length()-1);
        if (marked == 'X') {
            return new Task(newDescription, true);
        } else {
            return new Task(newDescription, false);
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}


