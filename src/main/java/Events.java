public class Events extends Task{
    String des;
    String start;
    String end;
    public Events(boolean status, String des) {
        super(status, des);
        String[] s = des.split(" /from ");
        this.des = s[0];
        String[] x = s[1].split(" /to ");
        this.start = x[0];
        this.end = x[1];
    }

    @Override
    public void printStatus() {
        String s = (status)? "X":" ";
        System.out.println("[E][" +s+ "] " + this.des + " (from: " +this.start+ " to: "+this.end+")");
    }

    @Override
    public String toString() {
        String s = (status)? "X":" ";
        return "E | " + s + " | " + this.des + " | " + this.start + " to " + this.end;
    }
}
