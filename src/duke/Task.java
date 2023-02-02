package duke;
public class Task {
    private String name;
    private String inpString;
    private boolean isDone = false;

    public Task(String name, String inpString){
        this.name = name;
        this.inpString = inpString;
    }
    public void markDone(){
        isDone = true;
        this.printTask();
    }

    public void markUndone() {
        isDone = false;
        this.printTask();
    }

    public void printTask(){
        if (isDone){
            System.out.print("[X] " + name);
        } else {
            System.out.print("[ ] " + name);
        }
    }

    @Override
    public String toString(){
        if (isDone){
            return this.inpString + " @ " + "1\n";
        } else {
            return this.inpString + " @ " + "0\n";
        }
    }

}
