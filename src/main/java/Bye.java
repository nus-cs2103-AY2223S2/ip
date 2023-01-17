public class Bye implements Command{
    public Bye() {
        
    }
    public void execute(TaskList list) {
        System.out.println("Duke: " + "Bye" + ". Hope I never see you again!");
    }
    
    public boolean isExit() {
        return true;
    }
}
