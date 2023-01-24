import java.util.ArrayList;

public class RequestList {
    private ArrayList<Request> list;


    public RequestList(ArrayList<Request> list) {
        this.list = list;
    }

    public void addRequest(Request request) {
        this.list.add(request);
    }

    public void printItems () {
        int numOfRequests = this.list.size();
        for (int i = 0; i < numOfRequests; i++) {
            System.out.println(i + 1 + ". " + list.get(i).toString());
        }
    }


}
