import models.Post;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class SampleTestCases{

    private final String API_BASE = "https://jsonplaceholder.typicode.com/";
    private Retrofit retrofit;
    private APIService apiService;
    private Call call;
    private Response response;

    // Presets
    @Before
    public void setUp(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(this.API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.apiService = this.retrofit.create(APIService.class);
    }

    // Getting a list of all resources
    @Test
    public void testGetPostList() throws IOException {
        this.call = this.apiService.getMyJSONList();
        this.response = this.call.execute();
        Post[] postList = (Post[]) response.body();
        System.out.println(response + "\n");
        if (postList != null){
            for (Post post : postList){
                System.out.println(post + "\n");
            }
        }
    }

    // Getting a resource by ID
    @Test
    public void testGetPostById() throws IOException {
        String id = "1";
        this.call = this.apiService.getMyJSON(id);
        this.response = this.call.execute();
        Post post = (Post) response.body();
        System.out.println("Response: " + response);
        System.out.println(post);
    }

    // Report for 'posts/0' request
    @Test
    public void testGetPost0Answer() throws IOException{
        String id = "0";
        this.call = this.apiService.getMyJSON(id);
        this.response = this.call.execute();
        int necessaryCode = 200;
        System.out.println("Response: " + response);
        Assert.assertEquals(this.response.code() + " returned.", necessaryCode, this.response.code());
    }

}