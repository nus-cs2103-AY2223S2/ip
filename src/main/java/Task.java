import java.io.Serializable;

public class Task implements Serializable {
    protected String cont;
    protected boolean finished;

    public Task(String cont) {
        this.cont=cont;
        this.finished=false;
    }

    public String checkStatus() {
        return this.finished ? "X" : " ";
    }

    public void mark() {
        this.finished = true;
    }

    public void unmark() {
        this.finished = false;
    }

    @Override
    public String toString() {
        return "[" + this.checkStatus() + "]" + this.cont;
    }
}