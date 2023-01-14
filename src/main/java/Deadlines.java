public class Deadlines extends Task{
    String des;
    String deadline;
    public Deadlines(boolean status, String des) {
        super(status, des);
        String[] s = des.split(" /by ");
        this.des = s[0];
        this.deadline = s[1];
    }

    @Override
    public void printStatus() {
        String s = (status)? "X":" ";
        System.out.println("[D][" +s+ "] " + this.des + " (by: " +this.deadline+ ")");
    }
}
