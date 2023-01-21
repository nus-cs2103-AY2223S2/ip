package duke;
public class ToDos extends Task{
    public ToDos(boolean status, String des) {
        super(status, des);
    }

    @Override
    public void printStatus() {
        String s = (status)? "X":" ";
        System.out.println("[T][" +s+ "] " + this.des);
    }

    @Override
    public String toString() {
        String s = (status)? "X":" ";
        return "T | " + s + " | " + this.des;
    }
}
