import java.util.Scanner;
class DoTask extends Event {
    boolean firstGreet;
    String lastCommand;

    public DoTask() {
        super(false);
        this.firstGreet = true;
        this.lastCommand = "";

    }

    public DoTask(boolean firstGreet, String lastCommand) {
        super(false);
        this.firstGreet = firstGreet;
        this.lastCommand = lastCommand;
    }
    public Event toNext() {
        Scanner sc = new Scanner(System.in);
        String nextTask = sc.nextLine();
        if (nextTask.equals("BYE")) {
            return new Ending();
        }
        else {
            return new DoTask(false, nextTask);
        }
    }


    @Override
    public String toString() {
        String toPrintOut = "";
        toPrintOut += "_".repeat(22) + '\n';
        if (this.firstGreet) {
            toPrintOut += "INTERESTING. VERY INTERESTING. WHAT'S YOUR PLANS?" + '\n';
        } else {
            toPrintOut += "SO YOU WANT TO " + this.lastCommand + " VERY WELL..." + '\n';
        }
        toPrintOut += "_".repeat(22) + '\n';
        return toPrintOut;
    }
}