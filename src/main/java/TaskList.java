public class TaskList {
    protected Task[] list;
    protected int len;

    public TaskList(){
        this.list = new Task[100];
        this.len = 0;
    }

    public int length(){
        return this.len;
    }

    public void list(){
        System.out.print("  ________________________________\n");
        System.out.print("  Here are the tasks in your list:\n");
        for(int i = 0; i < this.len ; i++){
            int index = i + 1;
            String item = "  " + index + ". " + list[i].toString();
            System.out.print(item);
        }
        System.out.print("  ________________________________\n");
    }

    public void setDone(int index){
        this.list[index].setDone();
        String reply = "  ________________________________\n"
                + "  Nice! I've marked this task as done:\n"
                + "    " + list[index].toString()
                + "  ________________________________\n";
        System.out.print(reply + "\n");
    }

    public void setNotDone(int index){
        this.list[index].setNotDone();
        String reply = "  ________________________________\n"
                + "  OK, I've marked this task as not done:\n"
                + "    " + list[index].toString()
                + "  ________________________________\n";
        System.out.print(reply + "\n");
    }

    public void addToDo(String description){
        this.list[len] = new ToDo(description);
        this.len++;

        String reply = "  ________________________________\n"
                + "  Got it. I've added this task:\n"
                + "    " + taskToString(len - 1)
                + "  Now you have " + this.len + " tasks in the list.\n"
                + "  ________________________________\n";
        System.out.print(reply);
    }

    public void addDeadline(String description, String by){
        this.list[len] = new Deadline(description, by);
        this.len++;

        String reply = "  ________________________________\n"
                + "  Got it. I've added this task:\n"
                + "    " + taskToString(len - 1)
                + "  Now you have " + this.len + " tasks in the list.\n"
                + "  ________________________________\n";
        System.out.print(reply);
    }

    public void addEvent(String description, String from, String to){
        this.list[len] = new Events(description, from, to);
        this.len++;

        String reply = "  ________________________________\n"
                + "  Got it. I've added this task:\n"
                + "    " + taskToString(len - 1)
                + "  Now you have " + this.len + " tasks in the list.\n"
                + "  ________________________________\n";
        System.out.print(reply);
    }

    public String taskToString(int index){
        return list[index].toString();
    }
}
