import java.util.ArrayList;
import java.util.Scanner;
import java.lang.StringBuilder;

public class Duke {
    private static final String intro = "Hi! I'm Duke! :)\nHow may I help?";
    private static final String outro = "Goodbye!";
    private static ArrayList<Task> list = new ArrayList<>();

    private static void reset() {
        System.out.println();
        System.out.print("User: ");
    }
    private static void addToList(Task t) {
        list.add(t);
        System.out.print("Duke: ");
        System.out.println("added " + t.getName() + "!");
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    private static void emptyErr() {
        System.out.println("Duke: Error!! List empty! Try again.");
        reset();
    }

    private static void viewList() {
        System.out.println("Here's your list of tasks:");
        System.out.println();
        for(int i  = 0; i < list.size(); i++) {
        System.out.println(i+1 + ". " + list.get(i));
        }
    }

    private static void markTask(int num) {
        if(num >= list.size()) {
            System.out.println("Duke: Task no." + (num+1) + " not found. Try again.");
        } else {
            list.get(num).mark();
            System.out.println("Duke: Nice It's marked!");
            System.out.println(list.get(num));
        }
    }

    private static void unmarkTask(int num) {
        if(num >= list.size()) {
            System.out.println("Duke: Task no." + (num+1) + " not found. Try again.");
        } else {
            list.get(num).unmark();
            System.out.println("Duke: Ok! It's unmarked!");
            System.out.println(list.get(num));
        }
    }

    private static ToDo makeToDo(String[] task) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < task.length; i++) {
            sb.append(task[i] + " ");
        }
        return new ToDo(sb.toString());
    }

    private static Event makeEvent(String[] task) {
        StringBuilder start = new StringBuilder();
        StringBuilder end = new StringBuilder();
        StringBuilder desc = new StringBuilder();
        boolean isDescripton = true;
        boolean first = false;
        boolean second = false;

        for(int i = 1; i < task.length; i++) {
            if(task[i].equals("/from")) {
                first = true;
                isDescripton = false;
                continue;
            }
            if(task[i].equals("/to")) {
                second = true;
                first = false;
                continue;
            }
            if(first) {
                start.append(task[i] + " ");
            }
            if(second) {
                end.append(task[i] + " ");
            }
            if(isDescripton) {
                desc.append(task[i] + " ");
            }
        }
        return new Event(desc.toString(), start.toString(), end.toString());
    }

    private static Deadline makeDeadline(String[] task) {
        StringBuilder desc = new StringBuilder();
        StringBuilder by = new StringBuilder();
        boolean isDesc = true;

        for(int i = 1; i < task.length; i++) {
            if(task[i].equals("/by")) {
                isDesc = false;
                continue;
            }
            if(isDesc) {
                desc.append(task[i] + " ");
            } else {
                by.append(task[i] + " ");
            }
        }
        return new Deadline(desc.toString(), by.toString());
    }

    private static void delete(int num) {
        if(num >= list.size()) {
            System.out.println("Duke: Task no." + (num+1) + " not found. Try again.");
        } else {
            System.out.println("Duke: Ok! Following task is removed: ");
            System.out.println(list.get(num));
            list.remove(num);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(intro);
        System.out.println();
        System.out.print("User: ");
        String[] raw = sc.nextLine().split(" ");
        String input = raw[0];
        
        while(!input.equals("bye")) {
            if (input.equals("list")) {
                if(list.isEmpty()) {
                    emptyErr();
                } else {
                    viewList();
                    reset();
                }
            } else if (input.equals("mark")) {
                if(list.isEmpty()) {
                    emptyErr();
                } else {
                    int num = (Integer.parseInt(raw[1]) - 1);
                    markTask(num);
                    reset();
                }
            } else if (input.equals("unmark")) {
                if(list.isEmpty()) {
                    emptyErr();
                } else {
                    int num = (Integer.parseInt(raw[1]) - 1);
                    unmarkTask(num);
                    reset();
                }
            } else if (input.equals("todo")) {
                ToDo temp = makeToDo(raw);
                addToList(temp);
                reset();
            } else if (input.equals("event")) {
                Event temp = makeEvent(raw);
                addToList(temp);
                reset();
            } else if (input.equals("deadline")) {
                Deadline temp = makeDeadline(raw);
                addToList(temp);
                reset();
            } else if (input.equals("delete")) {
                if(list.isEmpty()) {
                    emptyErr();
                } else {
                    int num = (Integer.parseInt(raw[1]) - 1);
                    delete(num);
                    reset();
                }
            } else {
                System.out.println("Duke; Sorry I don't recognise that command :( Please try again.");
                reset();
            }
            raw = sc.nextLine().split(" ");
            input = raw[0];
        }
        sc.close();
        System.out.print("Duke: ");
        System.out.println(outro);
    }

}
