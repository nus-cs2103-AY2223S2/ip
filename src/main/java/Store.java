public class Store extends Commands {
    public Store(String str) {
        this.setCommandStorage(str);
    }
    @Override
    public void execute(User user) {
        user.addTask(new Tasks(this.getCommandStorage()));
        System.out.println("added: " + this.getCommandStorage());
    }
}