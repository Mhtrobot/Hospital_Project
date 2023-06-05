import java.util.ArrayList;

public class Admin {
    private static ArrayList<String> requests = new ArrayList<>();

    public static ArrayList<String> getRequests() {
        return requests;
    }

    public static void removeRequest(String request){
        requests.remove(request);
    }

    public static void addRequest(String request){
        requests.add(request);
    }

}
