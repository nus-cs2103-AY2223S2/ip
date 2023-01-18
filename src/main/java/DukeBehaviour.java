public class DukeBehaviour {
    Boolean isActive = true;
    public DukeBehaviour() {
        System.out.println("Duke constructor called...");
    }

    public void receiveInput(String userIn){
        if (userIn.equals("bye")){
            System.out.println("exit command received, exiting...");
            isActive = false;
        }
        else {
            echo(userIn);
        }
    }

    private void echo(String userIn){
        System.out.println(userIn);
    }
}
