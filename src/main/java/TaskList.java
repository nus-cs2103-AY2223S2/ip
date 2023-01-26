package duke.command;

import duke.task.*;

public class TaskList {
    private String[] arr;

    public TaskList(){
        arr = new String[100];
    }

    public TaskList(String[] arr) {
        this.arr = arr;
    }

    public String[] readTaskList() {
        return this.arr;
    }

    public void list() {
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] == null) {
                break;
            }
            System.out.println(String.format("%d.%s", j + 1, arr[j]));
        }
    }

    public int validLen() {
        int len = 0;
        while (arr[len] != null) {
            len++;
        }
        return len;
    }

    public TaskList mark(int num) {
        if (arr[num] != null) {
            System.out.println("OK, I've marked this task as not done yet:");
            String original = arr[num];
            arr[num] = new Task(String.valueOf(original.charAt(1)), original.substring(7), true).toString();
            System.out.println(arr[num]);
        }
        return new TaskList(arr);
    }

    public TaskList unmark(int num1) {
        if (arr[num1] != null) {
            System.out.println("OK, I've marked this task as not done yet:");
            String original = arr[num1];
            Task newTask = new Task(String.valueOf(original.charAt(1)), original.substring(7), false);
            arr[num1] = newTask.toString(); //String.format("%s[ ] %s", original.substring(0, 3), original.substring(7));
            System.out.println(arr[num1]);
        }
        return new TaskList(arr);
    }

    public boolean checkValidIndex(int index) {
        return (arr[index] != null);
    }
    public TaskList delete(int num1) {
        if (arr[num1] != null) {
            System.out.println("Noted. I've removed this task:");
            String original = arr[num1];
            System.out.println(original);
            int trace = num1;
            String[] originalList = new String[100];
            for (int k = 0; k < 100; k++) {
                originalList[k] = arr[k];
            }

            arr[trace] = arr[trace + 1];
            trace++;

            while ((trace >= 1) && (originalList[trace - 1] != null)) {
                arr[trace] = originalList[trace + 1];
                trace++;
            }
        }
        return new TaskList(arr);
    }

    public TaskList add(Task task) {
        int len = this.validLen();
        arr[len] = task.toString();
        System.out.println(task.toString());
        return new TaskList(arr);
    }


}
