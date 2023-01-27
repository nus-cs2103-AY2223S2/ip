public class Task {
    private String name;
    private boolean isDone = false;

    public Task(String name){
        this.name = name;
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

}
