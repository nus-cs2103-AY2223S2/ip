import java.util.ArrayList;

public class TaskList {
    
    ArrayList<Task> listOfTasks;

    TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public void toRead() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < listOfTasks.size(); i++) {
            Integer currIndex = i + 1;
            Task currTask = listOfTasks.get(i);
            String toUse = currIndex.toString() + "." + currTask.toString();
            System.out.println(toUse);
        }
    }

    public void deleteTask(int index) throws DukeExceptions {
        try {
            int indexToUse = index - 1;
            if (indexToUse >= listOfTasks.size() || indexToUse < 0) {
                throw new DukeExceptions("Wrong size for mark/unmark");
            }
            Task gettingTask = listOfTasks.remove(indexToUse);
            String toOutput = "Noted. I've removed this task:\n  " + gettingTask.toString() + "\nNow you have " + listOfTasks.size() + " tasks in the list";
            System.out.println(toOutput);    
        } catch (DukeExceptions DE) {
            System.out.println(DE.toString());
        }
    }

    public void addTask(Task toAdd) {
        listOfTasks.add(toAdd);
        String toPrint = "";
        if (listOfTasks.size() == 1) {
            toPrint = "Got it. I've added this task:\n  " + toAdd.toString() + "\nNow you have " + listOfTasks.size() + " task in the list.";
        } else {
            toPrint = "Got it. I've added this task:\n  " + toAdd.toString() + "\nNow you have " + listOfTasks.size() + " tasks in the list.";                   
        }
        System.out.println(toPrint);
    }

    public void markTask(int index) throws DukeExceptions {
        try {
            int indexToUse = index - 1;
            if (indexToUse >= listOfTasks.size() || indexToUse < 0) {
                throw new DukeExceptions("Wrong size for mark/unmark");
            }
            Task currTask = listOfTasks.get(indexToUse);
            currTask.setDone();
            String toOutput = "Nice! I've marked this task as done:\n  " + currTask.toString();
            System.out.println(toOutput);
        } catch (DukeExceptions DE) {
            DE.toString();
        }
    }

    public void unmarkTask(int index) throws DukeExceptions {
        try {
            int indexToUse = index - 1;
            if (indexToUse >= listOfTasks.size() || indexToUse < 0) {
                throw new DukeExceptions("Wrong size for mark/unmark");
            }
            Task currTask = listOfTasks.get(indexToUse);
            currTask.setUndone();
            String toOutput = "Ok, I've marked this task as not done yet:\n  " + currTask.toString();
            System.out.println(toOutput);
        } catch (DukeExceptions DE) {
            DE.toString();
        }
    }

    public void checkDueDate(int index) {
        int indexToUse = index - 1;
        if (listOfTasks.get(indexToUse) instanceof Deadlines) {
            Deadlines taskOfInterest = (Deadlines) listOfTasks.get(indexToUse);
            System.out.println(taskOfInterest.taskDate());
        } else {
            System.out.println("This task does not have a due date!");
        }
    }

}
