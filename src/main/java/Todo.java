public class Todo extends Task{
    public Todo(String cont){
        super(cont);
    }

    public String toString(){
        return "[T]["+this.checkStatus()+"] "+this.cont;
    }
}
