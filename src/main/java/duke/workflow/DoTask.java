package duke.workflow;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import duke.util.TaskList;
import duke.io.input.exception.UserInputException;
import duke.io.input.exception.DukeException;
import duke.util.service.ToDo;
import duke.util.service.Deadline;
import duke.util.service.ScheduledEvent;

/**
 * A more specific implementation of {@code Event}.
 *
 * Part of the workflow where the chatbot determines the next
 * possible action based on the user's input
 */

public class DoTask extends Event {
    boolean firstGreet;
    String lastCommand;
    TaskList taskList;
    String removedTask;

    /**
     * Constructs the initial {@code DoTask} event when
     * user uses Duke for the first time. The last command,
     * as well as the taskList, is empty.
     *
     */
    public DoTask() {
        super(false);
        this.firstGreet = true;
        this.lastCommand = "";
        this.taskList = new TaskList();
    }

    /**
     * Constructs the new {@code DoTask} event after the user has
     * entered an input.
     *
     * @param firstGreet is true if it's the first time user interacts with Duke;
     *                   false otherwise
     * @param lastCommand the last input entered by user
     * @param taskList the current list of tasks at the moment
     *                 the user enters the new input
     */
    public DoTask(boolean firstGreet, String lastCommand, TaskList taskList) {
        super(false);
        this.firstGreet = firstGreet;
        this.lastCommand = lastCommand;
        this.taskList = taskList;
    }

    /**
     * Constructs the new {@code DoTask} event after the user has
     * deleted a task in the list of tasks.
     *
     * @param firstGreet is true if it's the first time user interacts with Duke;
     *                   false otherwise
     * @param lastCommand the last input entered by user
     * @param taskList the current list of tasks at the moment
     *                 the user enters the new input
     * @param removedTask the task removed by user from the previous command
     */

    public DoTask(boolean firstGreet, String lastCommand, TaskList taskList, String removedTask) {
        super(false);
        this.firstGreet = firstGreet;
        this.lastCommand = lastCommand;
        this.taskList = taskList;
        this.removedTask = removedTask;
    }

    /**
     * Determines the next possible action after the user has entered an input.
     *
     * @return a new event that follows from the last user input
     */

    public Event toNext() {
        Scanner sc = new Scanner(System.in);
        String nextTask = sc.nextLine();
        if (nextTask.equals("BYE")) {
            return new Ending(this.taskList);
        } else {
            try {
                UserInputException.checkUserInput(nextTask, this.taskList.getSize());
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
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                        return new DoTask(false, deadlinePhraseList.get(1), this.taskList.addTask(
                                new Deadline(LocalDateTime.parse(deadlineList.get(1), format), deadlinePhraseList.get(1))));
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
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                        return new DoTask(false, eventPhraseList.get(1), this.taskList.addTask(
                                new ScheduledEvent(LocalDateTime.parse(eventBegin, format), LocalDateTime.parse(eventEnd, format), eventPhraseList.get(1))));
                    }
                    if (words.get(0).equals("DELETE")) {
                        this.removedTask = this.taskList.getTask(Integer.valueOf(words.get(1)) - 1);
                        return new DoTask(false, nextTask, this.taskList.removeTask(Integer.valueOf(words.get(1)) - 1), this.removedTask);
                    }
                }
            }  catch (DukeException exception) {
                System.out.println(exception);
            } catch (Exception exception) {
                System.out.println("ERRRR ERROR ERRR. SYSTEM FAILURE. UNKNOWN EXCEPTION. ERR ERR");
            }
        }
        return this;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    @Override
    public String toString() {
        String toPrintOut = "";
        if (this.firstGreet) {
            toPrintOut += "INTERESTING. VERY INTERESTING. WHAT'S YOUR PLANS?";
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
                        if (words.get(0).equals("DELETE")) {
                            toPrintOut += "KABOOM. GONE. REDUCED TO ATOMS. HOW EXCITING!" + '\n';
                            toPrintOut += '\n' + "TASK " + this.removedTask + " NO LONGER EXISTED" + '\n';
                            int numberOfTasks = this.taskList.getSize();
                            if (numberOfTasks > 1) {
                                toPrintOut += numberOfTasks + " TASKS LEFT. BETTER HURRY." + '\n';
                            } else {
                                toPrintOut += "ONLY " + numberOfTasks + " TASK LEFT. BORING DAYS AHEAD." + '\n';
                            }
                        } else {
                            toPrintOut += "SO YOU WANT TO ADD " + '"' + this.lastCommand + '"' + ". VERY WELL..." + '\n';
                            toPrintOut += '\n' + "ADDED: " + lastCommand + '\n';
                            int numberOfTasks = this.taskList.getSize();
                            if (numberOfTasks > 1) {
                                toPrintOut += numberOfTasks + " TASKS. BETTER HURRY." + '\n';
                            } else {
                                toPrintOut += "ONLY " + numberOfTasks + " TASK. BORING DAYS AHEAD." + '\n';
                            }
                        }
                    }
                }
            }
            toPrintOut += '\n' + "WHAT ELSE?";
        }
        return toPrintOut;
    }
}