package jane;

import jane.task.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
        protected static ArrayList<jane.task.Task> tasks;

        TaskList(ArrayList<jane.task.Task> tasks) {
            this.tasks = tasks;
        }

    /**
     * Processes input commands
     * @param output is user's input
     * @throws JaneException if input format is wrong
     * @throws JaneException if input is invalid
     */
        public static void useCommand(String output) {
            if (output.equals("bye")) {
                List<String> currentList = new ArrayList<>();
                for (jane.task.Task t : tasks) {
                    currentList.add(t.save());
                }
                try {
                    jane.Storage.updateList(tasks);
                } catch (Exception err) {
                    System.out.println("cannot save list");
                    err.printStackTrace();
                }
            } else if (output.startsWith("find")) {
                String[] s = output.split(" ");
                int count = 0;
                String action = s[1];
                for (jane.task.Task i : tasks) {
                    if (i.description.contains(action)) {
                        System.out.println(i.toString());

                    }
                }


            } else if (output.startsWith("mark")) {
                String[] s = output.split(" ");
                int num = Integer.parseInt(s[1]);
                try {
                    System.out.println("Nice! I've marked this task as done");
                    jane.task.Task n = tasks.get(num - 1);
                    n.changeState(true);
                    System.out.println(n.toString());
                } catch (Exception err) {
                    err.printStackTrace();
                    System.out.println("Number out of index");
                }

            } else if (output.equals("todo") || output.equals("deadline") || output.equals("event")) {
                System.out.println("Please specify the task to be done :(((");
            } else if (output.startsWith("todo")) {
                jane.task.Todo todo = jane.Parser.parserT(output, tasks.size());
                tasks.add(todo);
                System.out.println(todo.toString());
            } else if (output.startsWith("deadline")) {
                jane.task.Deadline d = jane.Parser.parserD(output, tasks.size() + 1);
                tasks.add(d);
                System.out.println(d.toString());
            } else if (output.startsWith("event")) {
                String des = output.substring(6);
                String[] s = des.split("/");
                if (s.length == 1) {
                    System.out.println("Please specify when the event is :(((");

                }
                String[] start = s[1].substring(5).split(" ");
                LocalDateTime startE = LocalDateTime.parse(String.format("%sT%s", start[0], start[1]));
                //here i am assuming an event only lasts 1 day since the day it starts is the day it ends
                LocalDateTime end = LocalDateTime.parse(String.format("%sT%s", start[0], s[2]));
                jane.task.Event e = jane.Parser.parserE(output, tasks.size()+1);
                tasks.add(e);
                System.out.println(e.toString());
            } else if (output.startsWith("unmark")) {
                String[] s = output.split(" ");
                int num = Integer.parseInt(s[1]);
                try {
                    System.out.println("OK, I've marked this task as not done yet");
                    jane.task.Task n = tasks.get(num - 1);
                    n.changeState(false);
                    System.out.println(n.toString());
                } catch (Exception err) {
                    System.out.println("Number out of index");
                    err.printStackTrace();
                }
            } else if (output.startsWith("delete")) {
                String[] s = output.split(" ");
                int num = Integer.parseInt(s[1]);
                try {
                    System.out.println("Noted. I've removed this task:");
                    jane.task.Task n = tasks.get(num - 1);
                    System.out.println(n.toString());
                    for (int j = num; j < tasks.size(); j++) {
                        jane.task.Task t = tasks.get(j);
                        t.changeNum();
                    }

                    tasks.remove(n);
                    System.out.println("You now have " + tasks.size() + " tasks");
                } catch (Exception err) {
                    System.out.println("Number out of index");
                    err.printStackTrace();
                }
            } else if (output.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(tasks.get(i).toString());
                }

            } else {
                System.out.println("Im sorry I don't understand what you mean :((");
            }
        }
    }






