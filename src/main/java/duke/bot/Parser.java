package duke.bot;

import duke.exceptions.EmptyDescException;
import duke.exceptions.UnrecogException;
import duke.exceptions.UnspecTimeException;
import duke.taskmanager.*;

/**
 * Parser class containing storage of previously saved tasks.
 */
public class Parser {

    /*function parses applicable inputs and prints out respective outputs based on saved state from storage*/
    public static void parseDelete(String str, TaskList tasks, StringBuilder res) {
        if (tasks.isEmpty()) {
            res.append("There is nothing on your list to delete");
        } else {
            int index = Integer.parseInt((str.substring(7)));
            Tasks t = tasks.get(index - 1);
            res.append("Noted, I've removed this task:\n" + index + ": " + t.icon() + t.completed() + " " + t.getDesc());
            tasks.remove(index - 1);
            TaskList.rewrite(tasks);
            res.append("\nNow you have ").append(tasks.size()).append(" tasks in the list");
            System.out.println(t.deleted() +
                    "\nNow you have " +
                    tasks.size() +
                    " tasks in the list");
        }
    }
    public static void parseFind(String str, TaskList tasks, StringBuilder res) {
        System.out.println("Here are the tasks matching the description:");
        res.append("Here are the tasks matching the description:");
        if (str.split(" ", 2).length == 1) {
            System.out.println("enter description you're looking for");
            res.append("enter description you're looking for");
        }
        String keyword = str.split(" ", 2)[1];
        int n = 1;
        for (Tasks t : tasks.getList()) {
            if (t.getDesc().contains(keyword)) {
                System.out.println(n + ". "
                        + t.icon()
                        + t.completed() + " "
                        + t.getDesc());
                res.append("\n" + n + ". "
                        + t.icon()
                        + t.completed() + " "
                        + t.getDesc());
                n++;
            }
        }
    }
    public static void parseList(String str, TaskList tasks, StringBuilder res) {
        System.out.println("Here are the tasks in your list:");
        res.append("Here are the tasks in your list:");
        if (tasks.isEmpty()) {
            System.out.println("You have nothing scheduled, add something to the list.");
            res.append("\nYou have nothing scheduled, add something to the list.");
        } else {
            int n = 1;
            for (Tasks t : tasks.getList()) {
                System.out.println(n + ". "
                        + t.icon()
                        + t.completed() + " "
                        + t.getDesc());
                res.append("\n" + ". "
                        + t.icon()
                        + t.completed() + " "
                        + t.getDesc());
                n++;
            }
        }
    }

    public static void processMark(String str, TaskList tasks, StringBuilder res) {
        if (str.contains("un")) {
            int index = Integer.parseInt((str.substring(7)));
            Tasks t = tasks.get(index - 1);
            t.unmark();
            TaskList.rewrite(tasks);
            res.append("Oops! Stop procrastinating: \n"
                    + t.completed() + " " + t.getDesc());
            System.out.println("Oops! Stop procrastinating: \n"
                    + t.completed() + " " + t.getDesc());
        } else {
            int index = Integer.parseInt(str.substring(5));
            Tasks t = tasks.get(index - 1);
            t.mark();
            TaskList.rewrite(tasks);
            res.append("Nice! I've marked this task as done: \n"
                    + t.completed() + " " + t.getDesc());
            System.out.println("Nice! I've marked this task as done: \n"
                    + t.completed() + " " + t.getDesc());
        }
    }

    public static String parse(String str, TaskList tasks) {
        assert tasks != null;
        StringBuilder res = new StringBuilder();
        if (str.equals("bye")) {
            Exit();
        } else if (str.contains("delete")) {
            parseDelete(str, tasks, res);
            return res.toString();
        } else if (str.contains("find")) {
            parseFind(str, tasks, res);
            return res.toString();
        } else if (str.contains("cleartags")){
            int index = Integer.parseInt(str.split(" ",2 )[1]);
            Tasks t = tasks.get(index - 1);
            Tag.clearTags(t);
            Tag.rewrite();
            System.out.println("You've cleared tags for item" + index);
            res.append("You've cleared tags for item" + index);
        } else if (str.contains("tag")) {
            if (str.contains("ged")) {
                String k = str.split(" ", 2)[1];
                System.out.println("Here are the items tagged as " + k);
                res.append("Here are the items tagged as " + k + "\n");
                System.out.println(Tag.returnTagged(k));
                res.append(Tag.returnTagged(k));
                return res.toString();
            }
            System.out.println("Nice I've tagged this item as: ");
            res.append("Nice I've tagged this item as:");
            Tasks taggedTask = tasks.get(Integer.parseInt(str.substring(3, 4))-1);
            if(str.split(" ",2 ).length == 1) {
                System.out.println("enter your tags(eg: fun, important...)");
                res.append("enter your tags(eg: fun, important...)");
                return res.toString();
            }
            String tags = str.split(" ", 2)[1];
            int n = 1;
            for (String t : tags.split(", ")) {
                taggedTask.tag(t, taggedTask);
                System.out.println(t + ", ");
                res.append(t + ", ");
            }
            return res.toString();
        } else {
            if (str.equals("list")) {
                parseList(str, tasks, res);
                return res.toString();

            } else if (str.contains("mark")) {
                processMark(str, tasks, res);
                return res.toString();

            } else {
                String type = str.split(" ", 2)[0];
                try {
                    switch (type) {
                        case "todo":
                            Tasks t = new ToDo(str);
                            tasks.add(t);
                            System.out.println(t.added() +
                                    "\nNow you have " +
                                    tasks.size() +
                                    " tasks in the list");
                            res.append(t.added() +
                                    "\nNow you have " +
                                    tasks.size() +
                                    " tasks in the list");
                            return res.toString();
                        case "deadline":
                            Tasks d = new Deadline(str);
                            tasks.add(d);
                            System.out.println(d.added() +
                                    "\nNow you have " +
                                    tasks.size() +
                                    " tasks in the list");
                            res.append(d.added() +
                                    "\nNow you have " +
                                    tasks.size() +
                                    " tasks in the list");
                            return res.toString();
                        case "event":
                            Tasks e = new Event(str);
                            tasks.add(e);
                            System.out.println(e.added() +
                                    "\nNow you have " +
                                    tasks.size() +
                                    " tasks in the list");
                            res.append(e.added() +
                                    "\nNow you have " +
                                    tasks.size() +
                                    " tasks in the list");
                            return res.toString();
                        default:
                            return res.toString();
                    }

                } catch (UnrecogException e) {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                    res.append("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                    return res.toString();
                } catch (EmptyDescException e) {
                    System.out.println("☹ OOPS!!! The description of a " + type + " cannot be empty.\n");
                    res.append("☹ OOPS!!! The description of a " + type + " cannot be empty.\n");
                    return res.toString();
                } catch (UnspecTimeException e) {
                    if (type.equals("event")) {
                        System.out.println(" Please specify a timeframe (from/ ... to/ ...)\n");
                        res.append(" Please specify a timeframe (from/ ... to/ ...)\n");
                        return res.toString();
                    } else {
                        System.out.println(" Please specify a deadline (by/...)\n");
                        res.append(" Please specify a deadline (by/...)\n");
                        return res.toString();
                    }
                } catch (Exception ignored) {
                    return "OOps something went wrong!";
                }
            }
        }
        return res.toString();
    }

    /*Exit code to end the program*/
    public static void Exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

}
