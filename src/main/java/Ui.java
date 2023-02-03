import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Ui {
    private final String intro = "Hi! I'm Duke! :)\nHow may I help?";
    private final String outro = "Goodbye!";
    private TaskList tList;
    private Storage savedList;

    public void initialise() throws IOException {
        System.out.println(this.intro);
        System.out.println();
        Storage saved = new Storage("./dukeSaved.txt");
        this.savedList = saved;
        this.tList = new TaskList();

        if(saved.isSaved()) {
            this.tList.setList(saved.load());
            if(this.tList.size() > 0) {
                System.out.println("Duke: Previously saved list available!");
            } else {
                System.out.println("Duke: No previously saved list found.");
            }
        } else {
            System.out.println("Duke: No previously saved list found.");
        }
    }

    public void save() throws IOException {
        savedList.save(this.tList.getList());
    }

    public String getIntro() {
        return this.intro;
    }

    public String getOutro() {
        return this.outro;
    }

    public void end() {
        System.out.print("Duke: ");
        System.out.println(this.outro);
    }

    public ArrayList<Task> getList() {
        return this.tList.getList();
    }

    public void reset() {
        System.out.println();
        System.out.print("User: ");
    }
    
    public void addToList(Task t) {
        this.tList.add(t);
        System.out.print("Duke: ");
        System.out.println("added " + t.getTask() + "!");
        System.out.println("Now you have " + tList.size() + " tasks in the list.");
        reset();
    }

    public void emptyErr() {
        System.out.println("Duke: Error!! List empty! Try again.");
        reset();
    }

    public void viewList() {
        System.out.println("Here's your list of tasks:");
        System.out.println();
        for(int i  = 0; i < this.tList.size(); i++) {
        System.out.println(i+1 + ". " + this.tList.get(i));
        }
        reset();
    }

    public void markTask(int num) {
        if(num >= this.tList.size()) {
            System.out.println("Duke: Task no." + (num+1) + " not found. Try again.");
        } else {
            this.tList.get(num).mark();
            System.out.println("Duke: Nice It's marked!");
            System.out.println(this.tList.get(num));
        }
        reset();
    }

    public void unmarkTask(int num) {
        if(num >= this.tList.size()) {
            System.out.println("Duke: Task no." + (num+1) + " not found. Try again.");
        } else {
            this.tList.get(num).unmark();
            System.out.println("Duke: Ok! It's unmarked!");
            System.out.println(this.tList.get(num));
        }
        reset();
    }

    public ToDo makeToDo(String[] task) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < task.length; i++) {
            sb.append(task[i] + " ");
        }
        return new ToDo(sb.toString());
    }

    public Event makeEvent(String[] task) throws DateTimeParseException {
        StringBuilder start = new StringBuilder();
        StringBuilder end = new StringBuilder();
        StringBuilder desc = new StringBuilder();
        boolean isDescripton = true;
        boolean first = false;
        boolean second = false;

        for(int i = 1; i < task.length; i++) {
            if(task[i].equalsIgnoreCase("/from")) {
                first = true;
                isDescripton = false;
                continue;
            }
            if(task[i].equalsIgnoreCase("/to")) {
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

    public Deadline makeDeadline(String[] task) throws DateTimeParseException {
        StringBuilder desc = new StringBuilder();
        StringBuilder by = new StringBuilder();
        boolean isDesc = true;

        for(int i = 1; i < task.length; i++) {
            if(task[i].equalsIgnoreCase("/by")) {
                isDesc = false;
                continue;
            }
            if(isDesc) {
                desc.append(task[i] + " ");
            } else {
                by.append(task[i]);
                if (i != task.length - 1) {
                    by.append(" ");
                }
            }
        }
        return new Deadline(desc.toString(), by.toString());
    }

    public void delete(int num) {
        if(num >= this.tList.size()) {
            System.out.println("Duke: Task no." + (num+1) + " not found. Try again.");
        } else {
            System.out.println("Duke: Ok! Following task is removed: ");
            System.out.println(this.tList.get(num));
            this.tList.remove(num);
        }
        reset();
    }
}
