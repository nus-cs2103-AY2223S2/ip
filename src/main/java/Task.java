public class Task {
    private String text;
    private boolean marked;
    public Task() {
        this.text = "";
        marked = false;
    }
    public Task(String text) {
        this.text = text;
    }

    public void markOut(boolean mark) {
        if (mark != marked) {
            marked = !marked;
            if (marked) {
                System.out.println("Alright, I've marked it out : \n"
                        + this.toString());
            } else {
                System.out.println("Alright, I've erased the mark: \n"
                        + this.toString());
            }
        } else {
            System.out.println("Hey how I can change this mark?");
        }


    }

    public boolean isCorrectTask(String t) {
        return t.equals(this.text);
    }

    @Override
    public String toString() {
        if (marked) {
            return "[X] " + text;
        } else {
            return "[] " + text;
        }
    }
}
