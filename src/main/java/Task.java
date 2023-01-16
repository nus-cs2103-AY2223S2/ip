public class Task {
    protected String cont;
    protected boolean finished;

    public Task(String cont){
        this.cont=cont;
        this.finished=false;
    }

    public String getCont(){
        return this.cont;
    }

    public String checkStatus(){
        return this.finished?"X":" ";
    }

    public void mark(){
        this.finished=true;
    }

    public void unmark(){
        this.finished=false;
    }
}
