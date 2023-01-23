package bot;

import exceptions.emptyDescException;
import exceptions.unrecogException;
import exceptions.unspecTimeException;
import taskmanager.*;

import java.io.IOException;

public class Parser {
    static Storage storage = new Storage("data/tasks.txt");
    static TaskList todoList;

    public static void parse(String str, TaskList tasks) throws IOException {
        todoList = tasks;
        if (str.equals("bye")) {
            Exit();
        } else if(str.contains("delete")) {
            if(todoList.isEmpty()) {
                System.out.println("There is nothing on your list to delete");
            } else {
                int index = Integer.parseInt((str.substring(7)));
                Tasks t = todoList.get(index-1);
                todoList.remove(index - 1);
                TaskList.rewrite(todoList);
                System.out.println(t.deleted() +
                        "\nNow you have " +
                        todoList.size() +
                        " tasks in the list");
            }
        } else {
            if (str.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                if (todoList.isEmpty()) {
                    System.out.println("You have nothing scheduled, add something to the list.");
                } else {
                    int n = 1;
                    for (Tasks t : todoList.getList()) {
                        System.out.println(n + ". "
                                + t.icon()
                                + t.completed() + " "
                                + t.getDesc());
                        n++;
                    }
                }
            } else if (str.contains("mark")) {
                if (str.contains("un")) {
                    int index = Integer.parseInt((str.substring(7)));
                    Tasks t = todoList.get(index - 1);
                    t.unmark();
                    TaskList.rewrite(todoList);
                    System.out.println("Oops! Stop procrastinating: \n"
                            + t.completed() + " " + t.getDesc());

                } else {
                    int index = Integer.parseInt(str.substring(5));
                    Tasks t = todoList.get(index - 1);
                    t.mark();
                    TaskList.rewrite(todoList);
                    System.out.println("Nice! I've marked this task as done: \n"
                            + t.completed() + " " + t.getDesc());
                }
            } else {
                String type = str.split(" ", 2)[0];
                try {
                    switch (type) {
                        case "todo":
                            Tasks t = new ToDo(str);
                            todoList.add(t);
                            System.out.println(t.added() +
                                    "\nNow you have " +
                                    todoList.size() +
                                    " tasks in the list");
                            break;
                        case "deadline":
                            Tasks d = new Deadline(str);
                            todoList.add(d);
                            System.out.println(d.added() +
                                    "\nNow you have " +
                                    todoList.size() +
                                    " tasks in the list");
                            break;
                        case "event":
                            Tasks e = new Event(str);
                            todoList.add(e);
                            System.out.println(e.added() +
                                    "\nNow you have " +
                                    todoList.size() +
                                    " tasks in the list");
                            break;
                        default:
                            Tasks wrong = new Event(str);

                    }
                } catch (unrecogException e){
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                } catch(emptyDescException e) {
                    System.out.println("☹ OOPS!!! The description of a "+type+" cannot be empty.\n");
                }catch(unspecTimeException e) {
                    if(type.equals("event")) {
                        System.out.println(" Please specify a timeframe (from/ ... to/ ...)\n");
                    } else {
                        System.out.println(" Please specify a deadline (by/...)\n");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void Exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

}
