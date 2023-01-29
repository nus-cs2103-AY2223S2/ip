package components;

public class Task {
    private int done;
    public String msg;

    public Task(String msg) {
        this.msg = msg;
        this.done = 0;
    }
    
    public void complete() {
        this.done = 1;
    }
    
    public void uncomplete() {
        this.done = 0;
    }

    public String getInfo() {
        StringBuffer sb = new StringBuffer("");
        if (this instanceof Todo) {
            sb.append('T');
            if (this.done == 0) {
                sb.append('0');
            } else {
                sb.append('1');
            }
            sb.append(this.msg);
        } else if (this instanceof Deadline) {
            sb.append('D');
            if (this.done == 0) {
                sb.append('0');
            } else {
                sb.append('1');
            }
            sb.append(this.msg + " /by " + ((Deadline) this).by);
        } else if (this instanceof Event) {
            sb.append('E');
            if (this.done == 0) {
                sb.append('0');
            } else {
                sb.append('1');
            }
            sb.append(this.msg + " /from " + ((Event) this).from + " /to " + ((Event) this).to);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        if (this.done==0)  return "[ ] " + this.msg;
        else               return "[X] " + this.msg;
    }
}

