import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
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
                String[] command = nextTask.split(" ");
                List<String> words = Arrays.asList(command);
                if (words.get(0).equals("MARK")) {
                    return new DoTask(false, nextTask, this.taskList.markDone(Integer.valueOf(words.get(1)) - 1));
                } else {
                    if (words.get(0).equals("UNMARK")) {
                        return new DoTask(false, nextTask, this.taskList.unMark(Integer.valueOf(words.get(1)) - 1));
                    }
                    else {
                        return new DoTask(false, nextTask, this.taskList.addTask(nextTask));
                    }
                }
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
                String[] command = lastCommand.split(" ");
                List<String> words = Arrays.asList(command);
                if (words.get(0).equals("MARK")) {
                    toPrintOut += "MARKED. ONE STEP CLOSER..." + '\n';
                    toPrintOut += "[X] " + this.taskList.getTask(Integer.valueOf(words.get(1)) - 1) + '\n';
                } else {
                    if (words.get(0).equals("UNMARK")) {
                        toPrintOut += "HAVING OTHER PLANS I SEE..." + '\n';
                        toPrintOut += "[ ] " + this.taskList.getTask(Integer.valueOf(words.get(1)) - 1) + '\n';
                    } else {
                        toPrintOut += "SO YOU WANT TO ADD " + '"' + this.lastCommand + '"' + ". VERY WELL..." + '\n';
                        toPrintOut += '\n' + "ADDED: " + lastCommand + '\n';
                        toPrintOut += '\n' + "WHAT ELSE?" + '\n';
                    }
                }
            }
        }
        toPrintOut += "_".repeat(22) + '\n';
        return toPrintOut;
    }
}