import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("---------------------------");
        System.out.println("Hello! I'm Duke\n What can I do for you?");
        System.out.println("---------------------------");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String[] strArr = str.split(" ");
        Task[] tasks = new Task[100];
        int index = 0;
        while (!str.equals("bye")) {
            if(str.equals("list")) {
                System.out.println("---------------------------");
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < index; i++) {
                    if(!tasks[i].mark) {
                        if(tasks[i].todo) {
                            System.out.print(i + 1 + "." + "[T][ ] ");
                        }
                        if(tasks[i].deadline) {
                            System.out.print(i + 1 + "." + "[D][ ] ");
                        }
                        if(tasks[i].event) {
                            System.out.print(i + 1 + "." + "[E][ ] ");
                        }
                    } else {
                        if(tasks[i].todo) {
                            System.out.print(i + 1 + "." + "[T][X] ");
                        }
                        if(tasks[i].deadline) {
                            System.out.print(i + 1 + "." + "[D][X] ");
                        }
                        if(tasks[i].event) {
                            System.out.print(i + 1 + "." + "[E][X] ");
                        }
                    }
                    if(tasks[i].deadline) {
                        System.out.println(tasks[i].string + " (by: " + tasks[i].time + ")");
                    } else if(tasks[i].event) {
                        System.out.println(tasks[i].string + " (from: "
                            + tasks[i].startTime + " to: " + tasks[i].endTime + ")");
                    } else {
                        System.out.println(tasks[i].string);
                    }
                }
                System.out.println("---------------------------");
            } else if(strArr[0].equals("mark")) {
                System.out.println("---------------------------");
                System.out.println("Nice! I've marked this task as done:");
                int curIndex = Integer.parseInt(strArr[1]) - 1;
                tasks[curIndex].mark();
                String curTask = tasks[curIndex].string;
                if(tasks[curIndex].deadline) {
                    System.out.print("[D]");
                }
                if(tasks[curIndex].todo) {
                    System.out.print("[T]");
                }
                if(tasks[curIndex].event) {
                    System.out.print("[E]");
                }
                System.out.print("[X] " + curTask);
                if(tasks[curIndex].deadline) {
                    System.out.println( " (by: " + tasks[curIndex].time + ")");
                } else if (tasks[curIndex].event){
                    System.out.println(" (from: " + tasks[curIndex].startTime
                        + " to: " + tasks[curIndex].endTime + ")");
                } else {
                    System.out.println();
                }
                System.out.println("---------------------------");
            } else if(strArr[0].equals("unmark")) {
                System.out.println("---------------------------");
                System.out.println("OK, I've marked this task as not done yet:");
                int curIndex = Integer.parseInt(strArr[1]) - 1;
                tasks[curIndex].demark();
                String curTask = tasks[curIndex].string;
                if(tasks[curIndex].deadline) {
                    System.out.print("[D]");
                }
                if(tasks[curIndex].todo) {
                    System.out.print("[T]");
                }
                if(tasks[curIndex].event) {
                    System.out.print("[E]");
                }
                System.out.print("[ ]" + curTask);
                if(tasks[curIndex].deadline) {
                    System.out.println( " (by: " + tasks[curIndex].time + ")");
                } else if (tasks[curIndex].event){
                    System.out.println(" (from: " + tasks[curIndex].startTime
                        + " to: " + tasks[curIndex].endTime + ")");
                } else {
                    System.out.println();
                }
                System.out.println("---------------------------");
            }
            else {
                if(strArr[0].equals("todo")) {
                    System.out.println("---------------------------");
                    System.out.println("Got it. I've added this task:");
                    StringBuilder curStr = new StringBuilder();
                    for(int i = 1; i < strArr.length; i++) {
                        curStr.append(" ").append(strArr[i]);
                    }
                    System.out.println(" [T][ ] " + curStr);
                    tasks[index] =new Task(curStr.toString());
                    tasks[index].todo();
                    index++;
                    System.out.println("Now you have " + index + " tasks in the list.");
                    System.out.println("---------------------------");
                } else if(strArr[0].equals("deadline")) {
                    System.out.println("---------------------------");
                    System.out.println("Got it. I've added this task:");
                    String[] strArr1 = str.split("/");
                    String timeWithBy = strArr1[strArr1.length-1];
                    String[] strArr2 = timeWithBy.split(" ");
                    StringBuilder sbTime = new StringBuilder();
                    for(int i = 1; i < strArr2.length; i++) {
                        sbTime.append(" ").append(strArr2[i]);
                    }
                    String time = sbTime.toString();
                    String[] strArr3 = strArr1[0].split(" ");
                    StringBuilder task = new StringBuilder();
                    for(int i = 1; i < strArr3.length; i++) {
                        task.append(" ").append(strArr3[i]);
                    }
                    System.out.println(" [D][ ] " + task + " (by: " + time + ")");
                    String curTask = task.toString();
                    tasks[index] =new Task(curTask);
                    tasks[index].deadline();
                    tasks[index].setTime(time);
                    index++;
                    System.out.println("Now you have " + index + " tasks in the list.");
                    System.out.println("---------------------------");
                } else if (strArr[0].equals("event")) {
                    System.out.println("---------------------------");
                    System.out.println("Got it. I've added this task:");
                    String[] strArr1 = str.split("/");
                    String timeWithFrom = strArr1[strArr1.length-2];
                    String timeWithTo = strArr1[strArr1.length-1];
                    String[] strArr2 = timeWithFrom.split(" ");
                    StringBuilder sbTime = new StringBuilder();
                    for(int i = 1; i < strArr2.length; i++) {
                        sbTime.append(" ").append(strArr2[i]);
                    }
                    String[] strArr3 = timeWithTo.split(" ");
                    StringBuilder sbTime1 = new StringBuilder();
                    for(int i = 1; i < strArr3.length; i++) {
                        sbTime1.append(" ").append(strArr3[i]);
                    }
                    String startTime = sbTime.toString();
                    String endTime = sbTime1.toString();
                    String[] strArr4 = strArr1[0].split(" ");
                    StringBuilder task = new StringBuilder();
                    for(int i = 1; i < strArr4.length; i++) {
                        task.append(" ").append(strArr4[i]);
                    }
                    System.out.println(" [E][ ] " + task + " (from: " + startTime + " to: " + endTime + ")");
                    String curTask = task.toString();
                    tasks[index] =new Task(curTask);
                    tasks[index].event();
                    tasks[index].setTime(startTime, endTime);
                    index++;
                    System.out.println("Now you have " + index + " tasks in the list.");
                    System.out.println("---------------------------");
                }
            }
            str = br.readLine();
            strArr = str.split(" ");
        }
        System.out.println("---------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("---------------------------");
        System.exit(0);
        }
    }

