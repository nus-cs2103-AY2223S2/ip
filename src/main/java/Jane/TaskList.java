package jane;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
        protected static ArrayList<jane.task.Task> tasks;

        public TaskList(ArrayList<jane.task.Task> tasks) {
            this.tasks = tasks;
        }

    /**
     * Processes input commands
     * @param output is user's input
     * @throws JaneException if input format is wrong
     * @throws JaneException if input is invalid
     */
        public String useCommand(String output) {
            StringBuilder finalOutput = new StringBuilder();
            if (output.equals("bye")) {
                List<String> currentList = new ArrayList<>();
                for (jane.task.Task t : tasks) {
                    currentList.add(t.save());
                }
                try {
                    jane.Storage.updateList(tasks);
                    return "BYE! see you soon";

                } catch (Exception err) {
                    err.printStackTrace();
                    return ("cannot save list");
                }
            } else if (output.startsWith("find")) {
                String[] s = output.split(" ");
                String action = s[1];
                for (jane.task.Task i : tasks) {
                    if (i.description.contains(action)) {
                        finalOutput.append(i);

                    }
                }
                return finalOutput.toString();
            } else if (output.startsWith("mark")) {
                String[] s = output.split(" ");
                int num = Integer.parseInt(s[1]);
                try {
                    finalOutput.append("Nice! I've marked this task as done");
                    jane.task.Task n = tasks.get(num - 1);
                    n.changeState(true);
                    finalOutput.append(n);
                    return finalOutput.toString();
                } catch (Exception err) {
                    err.printStackTrace();
                    return ("Number out of index");
                }

            } else if (output.equals("todo") || output.equals("deadline") || output.equals("event")) {
                finalOutput.append("Please specify the task to be done :(((");
                return finalOutput.toString();
            } else if (output.startsWith("todo")) {
                jane.task.Todo todo = jane.Parser.parserT(output, tasks.size());
                tasks.add(todo);
                finalOutput.append(todo);
                return finalOutput.toString();
            } else if (output.startsWith("deadline")) {
                jane.task.Deadline d = jane.Parser.parserD(output, tasks.size());
                tasks.add(d);
                finalOutput.append(d);
                return finalOutput.toString();
            } else if (output.startsWith("event")) {
                //here I am assuming an event only lasts 1 day since the day it starts is the day it ends
                jane.task.Event e = jane.Parser.parserE(output, tasks.size() );
                tasks.add(e);
                finalOutput.append(e);
                return finalOutput.toString();
            } else if (output.startsWith("unmark")) {
                String[] s = output.split(" ");
                int num = Integer.parseInt(s[1]);
                try {
                    finalOutput.append("OK, I've marked this task as not done yet" + "\n");
                    jane.task.Task n = tasks.get(num - 1);
                    n.changeState(false);
                    finalOutput.append(n);
                    return finalOutput.toString();
                } catch (Exception err) {
                    finalOutput.append("Number out of index");
                    err.printStackTrace();
                    return finalOutput.toString();
                }

            } else if (output.startsWith("delete")) {
                String[] s = output.split(" ");
                int num = Integer.parseInt(s[1]);
                try {
                    finalOutput.append("Noted. I've removed this task:" + "\n");
                    jane.task.Task n = tasks.get(num - 1);
                    finalOutput.append(n.toString());
                    for (int j = num; j < tasks.size(); j++) {
                        jane.task.Task t = tasks.get(j);
                        t.changeNum();
                    }

                    tasks.remove(n);
                    finalOutput.append("You now have " + tasks.size() + " tasks");
                    return finalOutput.toString();
                } catch (Exception err) {
                    finalOutput.append("Number out of index");
                    err.printStackTrace();
                }
                return finalOutput.toString();
            } else if (output.equals("list")) {
                for (jane.task.Task task : tasks) {
                    String s1 = task.toString() + "\n";
                    finalOutput.append(s1);

                }
                return finalOutput.toString();
            }
        return "Sorry I don't understand what do you mean :((";
        }
    }






