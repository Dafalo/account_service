import okhttp3.*;

import java.io.IOException;

public class PostValueRequest implements Runnable {
    private int id;
    private Long value;

    public PostValueRequest(int id, Long value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public void run() {
        System.out.println("Add value: " + value + " for id: " + id);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), value.toString());
        Request request = new Request.Builder()
                .url(Client.BASE_URL + id)
                .post(body)
                .build();
        try {
            Client.CLIENT.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
