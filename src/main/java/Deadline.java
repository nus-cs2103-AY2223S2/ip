public class Deadline extends Task{
    protected String time;

    public Deadline(String cont, String time){
        super(cont);
        this.time=time;
    }

    public String toString(){
        return "[D]["+this.checkStatus()+"] "+this.cont+" (by: "+this.time+")";
    }
}
