import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class GetValueRequest implements Runnable {
    private final int id;

    GetValueRequest(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        Request request = new Request.Builder()
                .url(Client.BASE_URL + id)
                .get()
                .build();

        try {
            Response response = Client.CLIENT.newCall(request).execute();

            String result = response.body().string();
            System.out.println("For id: " + id + " Value: " + result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
