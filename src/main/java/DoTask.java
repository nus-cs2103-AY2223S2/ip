import java.util.Scanner;

class DoTask extends Event {
    boolean firstGreet;
    String lastCommand;
    TaskList taskList;

    public DoTask() {
        super(false);
        this.firstGreet = true;
        this.lastCommand = "";
        this.taskList = new TaskList();
    }

    public DoTask(boolean firstGreet, String lastCommand, TaskList taskList) {
        super(false);
        this.firstGreet = firstGreet;
        this.lastCommand = lastCommand;
        this.taskList = taskList;
    }
    public Event toNext() {
        Scanner sc = new Scanner(System.in);
        String nextTask = sc.nextLine();
        if (nextTask.equals("BYE")) {
            return new Ending();
        } else {
            if (nextTask.equals("LIST")) {
                return new DoTask(false, nextTask, this.taskList);
            } else {
                return new DoTask(false, nextTask, this.taskList.addTask(nextTask));
            }
        }
    }

    @Override
    public String toString() {
        String toPrintOut = "";
        toPrintOut += "_".repeat(22) + '\n';
        if (this.firstGreet) {
            toPrintOut += "INTERESTING. VERY INTERESTING. WHAT'S YOUR PLANS?" + '\n';
        } else {
            if (lastCommand.equals("LIST")) {
                toPrintOut += this.taskList.toString();
            } else {
                toPrintOut += "SO YOU WANT TO ADD " + '"' + this.lastCommand + '"' + ". VERY WELL..." + '\n';
                toPrintOut += '\n' + "ADDED: " + lastCommand + '\n';
            }
        }
        toPrintOut += "_".repeat(22) + '\n';
        return toPrintOut;
    }
}