public class Commands {
    private TaskList lst;
    private Ui ui;
    private Storage storage;
    private boolean isBye;

    public Commands(TaskList lst, Ui ui, Storage storage) {
        this.lst = lst;
        this.ui = ui;
        this.storage = storage;
        this.isBye = false;
    }

    public boolean getIsBye() {
        return this.isBye;
    }

    void bye() {
        storage.save(lst);
        System.out.println("See you! Roarrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr!");
        this.isBye = true;
    }

    void list() {
        try {
            if(lst.size()==0) throw new DukeException("Roarrrrrrrrrrrrrrrrr! You did not add anything in the list!");
            System.out.println("Roarrrrrrrrrrrrrrr! Task list shown below!");
            for (int i = 1; i <= lst.size(); ++i) {
                System.out.println(i + "." + lst.get(i-1).toString());
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    void mark(String cmd) {
        try {
            if(cmd.length()<=5) throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you want to mark any task or not?");
            int i = Integer.parseInt(cmd.substring(5));
            lst.get(i-1).mark();
            System.out.println("Good! You finished that! I marked that as done. Roarrrrrrrrrrrrrr!");
            System.out.println("  "+this.lst.get(i-1).toString());
            storage.save(lst);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Roarrrrrrrrrrrrrrrrrrrr! I cannot identify that task as it is not an integer!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Roarrrrrrrrrrrrrrrrrrrr! You did not add that many tasks in the list!");
        }
    }

    void unmark(String cmd) {
        try {
            if(cmd.length()<=7) throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you want to unmark any task or not?");
            int i=Integer.parseInt(cmd.substring(7));
            lst.get(i-1).unmark();
            System.out.println("Roarrrrrrrrrrrrrr! You said you did not finish that? Fine! Unmarked!");
            System.out.println("  "+lst.get(i-1).toString());
            storage.save(lst);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Roarrrrrrrrrrrrrrrrrrrr! I cannot identify that task as it is not an integer!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Roarrrrrrrrrrrrrrrrrrrr! You did not add that many tasks in the list!");
        }
    }

    void todo(String cmd) {
        try {
            if(cmd.length()<=5) throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you really have this Todo task or not?");
            String task=cmd.substring(5);
            lst.add(new Todo(task));
            System.out.println("New Todo task is added. Roarrrrrrrrrrrrrrrrrrrrrrrrr!");
            System.out.println("  "+lst.get(lst.size()-1).toString());
            System.out.println("You save "+lst.size()+" tasks in the list. Roarrrrrrrrrrrrrrrrrrrr!");
            storage.save(lst);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    void deadline(String cmd) {
        try {
            if(cmd.length()<=9) throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you really have this Deadline task or not?");
            String task=cmd.substring(9);
            int pos=task.indexOf("/by");
            if(pos==-1) throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you have a deadline for this task or not?");
            String time=task.substring(pos+4);
            task=task.substring(0,pos-1);
            if(task.isEmpty()) throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you really have this Deadline task or not?");
            if(time.isEmpty()) throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you have a deadline for this task or not?");
            lst.add(new Deadline(task,time));
            System.out.println("New Deadline task is added. Roarrrrrrrrrrrrrrrrrrrrrrrrr!");
            System.out.println("  "+lst.get(lst.size()-1).toString());
            System.out.println("You save "+lst.size()+" tasks in the list. Roarrrrrrrrrrrrrrrrrrrr!");
            storage.save(lst);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Roarrrrrrrrrrrrrrrr! I cannot add this Deadline task! Check your input format!");
        }
    }

    void event(String cmd) {
        try {
            if(cmd.length()<=6) throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you really have this Event task or not?");
            String task=cmd.substring(6);
            int pos1=task.indexOf("/from");
            if(pos1==-1) throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you have a start time for this task or not?");
            String time1=task.substring(pos1+6);
            int pos2=time1.indexOf("/to");
            if(pos2==-1) throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you have a end time for this task or not?");
            String time2=time1.substring(pos2+4);
            time1=time1.substring(0,pos2-1);
            task=task.substring(0,pos1-1);
            if(task.isEmpty()) throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you really have this Event task or not?");
            if(time1.isEmpty()) throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you have a start time for this task or not?");
            if(time2.isEmpty()) throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you have a end time for this task or not?");
            lst.add(new Event(task,time1,time2));
            System.out.println("New Event task is added. Roarrrrrrrrrrrrrrrrrrrrrrrrr!");
            System.out.println("  "+lst.get(lst.size()-1).toString());
            System.out.println("You save "+lst.size()+" tasks in the list. Roarrrrrrrrrrrrrrrrrrrr!");
            storage.save(lst);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Roarrrrrrrrrrrrrrrr! I cannot add this Event task! Check your input format!");
        }
    }

    void delete(String cmd) {
        try {
            if(cmd.length()<=7) throw new DukeException("Roarrrrrrrrrrrrrrrrrrr! Do you want to delete any task or not?");
            int i = Integer.parseInt(cmd.substring(7));
            System.out.println("Fine! This task is deleted. Roarrrrrrrrrrrrrr!");
            System.out.println("  "+lst.get(i-1).toString());
            lst.remove(i-1);
            System.out.println("You save "+lst.size()+" tasks in the list. Roarrrrrrrrrrrrrrrrrrrr!");
            storage.save(lst);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Roarrrrrrrrrrrrrrrrrrrr! I cannot identify that task as it is not an integer!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Roarrrrrrrrrrrrrrrrrrrr! You did not add that many tasks in the list!");

        }
    }

    void notACommand() {
        System.out.println("Roarrrrrrrrrrrrrrrrr! What on earth are you talking about?");
    }
}
