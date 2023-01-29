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
                }
                if (words.get(0).equals("UNMARK")) {
                    return new DoTask(false, nextTask, this.taskList.unMark(Integer.valueOf(words.get(1)) - 1));
                }
                if (words.get(0).equals("TODO")) {
                    String[] toDoTask = nextTask.split("TODO ");
                    List<String> toDoAction = Arrays.asList(toDoTask);
                    return new DoTask(false, toDoAction.get(1), this.taskList.addTask(new ToDo(toDoAction.get(1))));
                }
                if (words.get(0).equals("DEADLINE")) {
                    String[] toDoTask = nextTask.split(" /BY ");
                    List<String> deadlineList = Arrays.asList(toDoTask);
                    String deadlineAction = deadlineList.get(0);
                    String[] deadlinePhraseArray = deadlineAction.split("DEADLINE ");
                    List<String> deadlinePhraseList = Arrays.asList(deadlinePhraseArray);
                    return new DoTask(false, deadlinePhraseList.get(1), this.taskList.addTask(new Deadline(deadlineList.get(1), deadlinePhraseList.get(1))));
                }
                if (words.get(0).equals("EVENT")) {
                    String[] splitFrom = nextTask.split(" /FROM ");
                    List<String> eventActionSplit = Arrays.asList(splitFrom);
                    String timeLinePhrase = eventActionSplit.get(1);
                    String eventAction = eventActionSplit.get(0);
                    String[] eventPhraseArray = eventAction.split("EVENT ");
                    List<String> eventPhraseList = Arrays.asList(eventPhraseArray);
                    String[] timeFrame = timeLinePhrase.split(" /TO ");
                    List<String> timeLineSplit = Arrays.asList(timeFrame);
                    String eventBegin = timeLineSplit.get(0);
                    String eventEnd = timeLineSplit.get(1);
                    return new DoTask(false, eventPhraseList.get(1), this.taskList.addTask(new ScheduledEvent(eventBegin, eventEnd, eventPhraseList.get(1))));
                }
            }
        }
        return this;
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
                    toPrintOut += this.taskList.getTask(Integer.valueOf(words.get(1)) - 1) + '\n';
                } else {
                    if (words.get(0).equals("UNMARK")) {
                        toPrintOut += "HAVING OTHER PLANS I SEE..." + '\n';
                        toPrintOut += this.taskList.getTask(Integer.valueOf(words.get(1)) - 1) + '\n';
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