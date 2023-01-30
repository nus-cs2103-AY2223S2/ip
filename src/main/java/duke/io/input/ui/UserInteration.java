import java.util.Scanner;
class UserInteraction {
    public void chat() {
        Scanner sc = new Scanner(System.in);
        Greeting greeting = new Greeting();
        System.out.println(greeting.toString());
        String isPlaying = sc.nextLine();
        if (isPlaying.equals("NO")) {
            greeting = new Greeting(0);
            Event nextEvent = greeting.toNext();
            System.out.println(nextEvent.toString());
        } else if (isPlaying.equals("YES")) {
            greeting = new Greeting(1);
            Event nextEvent = greeting.toNext();
            System.out.println(nextEvent.toString());
            while (nextEvent.getStatus() == false) {
                nextEvent = nextEvent.toNext();
                System.out.println(nextEvent.toString());
            }
        } else {
            while (!isPlaying.equals("YES") && !isPlaying.equals("NO")) {
                System.out.println("_".repeat(22));
                System.out.println("FOCUS, HUMAN. " +
                        "YOU ARE TO ENTER INPUT WITH FULL CAPS.");
                System.out.println("_".repeat(22));
                isPlaying = sc.nextLine();
            }
            if (isPlaying.equals("NO")) {
                greeting = new Greeting(0);
                Event end = greeting.toNext();
                System.out.println(end.toString());
            } else {
                greeting = new Greeting(1);
                Event nextEvent = greeting.toNext();
                System.out.println(nextEvent.toString());
                while (nextEvent.getStatus() == false) {
                    nextEvent = nextEvent.toNext();
                    System.out.println(nextEvent.toString());
                }
            }
        }
    }
}