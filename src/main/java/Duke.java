import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> lst=new ArrayList<>();
    public static void main(String[] args) {
        String line="____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm T-Rex. Roarrrrrrrrrrrrrr!");
        System.out.println("What do you need from me?");
        System.out.println(line);
        System.out.println();

        Scanner sc=new Scanner(System.in);
        String cmd=sc.nextLine();
        while(!cmd.equals("bye")){
            boolean iscmd=false;
            try{
                if(cmd.equals("list")){
                    if(lst.size()==0) throw new ListException();
                    System.out.println(line);
                    System.out.println("Roarrrrrrrrrrrrrrr! Task list shown below!");
                    for (int i = 1; i <= lst.size(); ++i) {
                        System.out.println(i + "." + lst.get(i-1).toString());
                    }
                    System.out.println(line);
                    iscmd=true;
                }
                if(cmd.startsWith("mark")){
                    if(cmd.length()<=5) throw new MarkException();
                    int i;
                    try {
                        i = Integer.parseInt(cmd.substring(5));
                    }catch(NumberFormatException e){
                        throw new MarkException();
                    }
                    if(i>lst.size()) throw new MarkException();
                    lst.get(i-1).mark();
                    System.out.println(line);
                    System.out.println("Good! You finished that! I marked that as done. Roarrrrrrrrrrrrrr!");
                    System.out.println("  "+lst.get(i-1).toString());
                    System.out.println(line);
                    iscmd=true;
                }
                if(cmd.startsWith("unmark")){
                    if(cmd.length()<=7) throw new UnmarkException();
                    int i;
                    try{
                        i=Integer.parseInt(cmd.substring(7));
                    }catch(NumberFormatException e){
                        throw new UnmarkException();
                    }
                    if(i>lst.size()) throw new UnmarkException();
                    lst.get(i-1).unmark();
                    System.out.println(line);
                    System.out.println("Roarrrrrrrrrrrrrr! You said you did not finish that? Fine! Unmarked!");
                    System.out.println("  "+lst.get(i-1).toString());
                    System.out.println(line);
                    iscmd=true;
                }
                if(cmd.startsWith("todo")){
                    if(cmd.length()<=5) throw new TodoException();
                    String task=cmd.substring(5);
                    lst.add(new Todo(task));
                    System.out.println(line);
                    System.out.println("New Todo task is added. Roarrrrrrrrrrrrrrrrrrrrrrrrr!");
                    System.out.println("  "+lst.get(lst.size()-1).toString());
                    System.out.println("You save "+lst.size()+" tasks in the list. Roarrrrrrrrrrrrrrrrrrrr!");
                    System.out.println(line);
                    iscmd=true;
                }
                if(cmd.startsWith("deadline")){
                    if(cmd.length()<=9) throw new DeadlineException();
                    String task=cmd.substring(9);
                    int pos=task.indexOf("/by");
                    if(pos==-1) throw new DeadlineException();
                    String time=task.substring(pos+4);
                    task=task.substring(0,pos-1);
                    if(task.isEmpty()||time.isEmpty()) throw new DeadlineException();
                    lst.add(new Deadline(task,time));
                    System.out.println(line);
                    System.out.println("New Deadline task is added. Roarrrrrrrrrrrrrrrrrrrrrrrrr!");
                    System.out.println("  "+lst.get(lst.size()-1).toString());
                    System.out.println("You save "+lst.size()+" tasks in the list. Roarrrrrrrrrrrrrrrrrrrr!");
                    System.out.println(line);
                    iscmd=true;
                }
                if(cmd.startsWith("event")){
                    if(cmd.length()<=6) throw new EventException();
                    String task=cmd.substring(6);
                    int pos1=task.indexOf("/from");
                    if(pos1==-1) throw new EventException();
                    String time1=task.substring(pos1+6);
                    int pos2=time1.indexOf("/to");
                    if(pos2==-1) throw new EventException();
                    String time2=time1.substring(pos2+4);
                    time1=time1.substring(0,pos2-1);
                    task=task.substring(0,pos1-1);
                    if(task.isEmpty()||time1.isEmpty()||time2.isEmpty()) throw new EventException();
                    lst.add(new Event(task,time1,time2));
                    System.out.println(line);
                    System.out.println("New Event task is added. Roarrrrrrrrrrrrrrrrrrrrrrrrr!");
                    System.out.println("  "+lst.get(lst.size()-1).toString());
                    System.out.println("You save "+lst.size()+" tasks in the list. Roarrrrrrrrrrrrrrrrrrrr!");
                    System.out.println(line);
                    iscmd=true;
                }
                if(cmd.startsWith("delete")){
                    if(cmd.length()<=7) throw new DeleteException();
                    int i;
                    try {
                        i = Integer.parseInt(cmd.substring(7));
                    }catch(NumberFormatException e){
                        throw new DeleteException();
                    }
                    if(i>lst.size()) throw new DeleteException();
                    System.out.println(line);
                    System.out.println("Fine! This task is deleted. Roarrrrrrrrrrrrrr!");
                    System.out.println("  "+lst.get(i-1).toString());
                    lst.remove(i-1);
                    System.out.println("You save "+lst.size()+" tasks in the list. Roarrrrrrrrrrrrrrrrrrrr!");
                    System.out.println(line);
                    iscmd=true;
                }
                if(!iscmd) throw new DukeException();
            }catch(ListException e){
                System.out.println(line);
                System.out.println("Roarrrrrrrrrrrrrrrrr! You did not add anything in the list!");
                System.out.println(line);
            }catch(MarkException e){
                System.out.println(line);
                System.out.println("Roarrrrrrrrrrrrrrrrr! Do you want to mark any task or not?");
                System.out.println(line);
            }catch(UnmarkException e){
                System.out.println(line);
                System.out.println("Roarrrrrrrrrrrrrrrrr! Do you want to unmark any task or not?");
                System.out.println(line);
            }catch(TodoException e){
                System.out.println(line);
                System.out.println("Roarrrrrrrrrrrrrrrrr! I cannot add that Todo task because of you!");
                System.out.println(line);
            }catch(DeadlineException e){
                System.out.println(line);
                System.out.println("Roarrrrrrrrrrrrrrrrr! I cannot add that Deadline task because of you!");
                System.out.println(line);
            }catch(EventException e){
                System.out.println(line);
                System.out.println("Roarrrrrrrrrrrrrrrrr! I cannot add that Event task because of you!");
                System.out.println(line);
            }catch(DeleteException e){
                System.out.println(line);
                System.out.println("Roarrrrrrrrrrrrrrrrr! How can you delete a task like that?");
                System.out.println(line);
            }catch(DukeException e){
                System.out.println(line);
                System.out.println("Roarrrrrrrrrrrrrrrrr! What on earth are you talking about?");
                System.out.println(line);
            }


            System.out.println();
            cmd=sc.nextLine();
        }

        System.out.println(line);
        System.out.println("See you! Roarrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr!");
        System.out.println(line);
        System.out.println();
    }
}
