public class RoodyException{
    private String message = "Oh no :( ";
    public RoodyException(String s) {
        this.message += s;
        line();
        System.out.println(this.message);
        line();
    }
    // Provides basic line 
    private void line() {
        System.out.println("____________________________________________________________");
    }
}
