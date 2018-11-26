import okhttp3.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Client {
    final static String BASE_URL = "http://localhost:8080/accounts/";
    final static Random RANDOM = new Random();
    final static OkHttpClient CLIENT = new OkHttpClient();

    public static void main(String[] args) throws InterruptedException {
        int rCount = Integer.parseInt(args[0]);

        int wCount = Integer.parseInt(args[1]);
        //123
        //12,23,123
        //1-100
        List<Integer> idList = parseIdList(args[2]);

        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < wCount; i++) {
            int id = getRandomId(idList);
            threadList.add(new Thread(new PostValueRequest(id,100L)));
        }

        for (int i = 0; i < rCount; i++) {
            int id = getRandomId(idList);
            threadList.add(new Thread(new GetValueRequest(id)));
        }
        threadList.forEach(Thread::start);

        for (Thread thread : threadList) {
            thread.join();
        }

    }

    private static int getRandomId(List<Integer> idList) {
        int nextPosition = RANDOM.nextInt(idList.size());
        return idList.get(nextPosition);
    }

    private static List<Integer> parseIdList(String arg) {
        if(arg.contains(",")){
            return
            Arrays.stream(arg.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }
        if(arg.contains("-")){
            String[] split = arg.split("-");
            int start = Integer.parseInt(split[0]);
            int end = Integer.parseInt(split[1]);
            return IntStream.range(start,end).boxed().collect(Collectors.toList());
        }
        return Collections.singletonList(Integer.parseInt(arg));
    }

}
