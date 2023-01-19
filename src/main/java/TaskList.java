import dukeexceptions.MissingArgumentException;
import dukeexceptions.UnknownCommandException;
import tasks.Deadline;
import tasks.Events;
import tasks.Task;
import tasks.ToDo;

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

    public void addToDo(String description) throws MissingArgumentException {
        if (description.trim().equals("")) {
            throw new MissingArgumentException("The description of a todo cannot be empty.");
        }

        this.list[len] = new ToDo(description);
        this.len++;

        String reply = "  ________________________________\n"
                + "  Got it. I've added this task:\n"
                + "    " + taskToString(len - 1)
                + "  Now you have " + this.len + " tasks in the list.\n"
                + "  ________________________________\n";
        System.out.print(reply);
    }

    public void addDeadline(String requestContent) throws MissingArgumentException {
        String[] splitWithBy = requestContent.split(" /by ", 2);
        String description = splitWithBy[0].trim();

        if (description.equals("")) {
            throw new MissingArgumentException("The description of a deadline cannot be empty.");
        } else if (splitWithBy.length != 2 || splitWithBy[1].trim().equals("")) {
            throw new MissingArgumentException("The deadline cannot be empty.");
        }

        String by = splitWithBy[1].trim();
        this.list[len] = new Deadline(description, by);
        this.len++;

        String reply = "  ________________________________\n"
                + "  Got it. I've added this task:\n"
                + "    " + taskToString(len - 1)
                + "  Now you have " + this.len + " tasks in the list.\n"
                + "  ________________________________\n";
        System.out.print(reply);
    }

    public void addEvent(String requestContent) throws MissingArgumentException{
        String[] splitFrom = requestContent.split(" /from ", 2);
        String description = splitFrom[0].trim();

        if (description.equals("")) {
            throw new MissingArgumentException("The description of an event cannot be empty.");
        } else if (splitFrom.length != 2) {
            throw new MissingArgumentException("The from cannot be empty.");
        }

        String[] splitTo = splitFrom[1].split(" /to ", 2);
        String from = splitTo[0].trim();
        String to = splitTo[1].trim();

        if (from.equals("")) {
            throw new MissingArgumentException("The from cannot be empty.");
        } else if (splitTo.length != 2 || splitTo[1].trim().equals("")) {
            throw new MissingArgumentException("The to cannot be empty.");
        }

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

    public void unknownCommand() throws MissingArgumentException{
        throw new UnknownCommandException();
    }

//    public void delete(String requestContent) throws MissingArgumentException{
//        if(requestContent.trim() == ""){
//            throw new MissingArgumentException("The index cannot be empty.");
//        }
//
//        int index = Integer.parseInt(requestContent.trim()) - 1;
//
//        for (int i = index+1; i < this.len; i++){
//            this.list[i] = this.list[i+1];
//        }
//
//        this.len--;
//        String reply = "  ________________________________\n"
//                + "  Noted. I've removed this task:\n"
//                + "    " + list[index].toString()
//                + "  Now you have " + this.len + " tasks in the list.\n"
//                + "  ________________________________\n";
//        System.out.print(reply);
//
//    }
}
