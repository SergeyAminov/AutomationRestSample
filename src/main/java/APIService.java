import models.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {
    @GET("posts/")
    Call<Post[]> getMyJSONList();

    @GET("posts/{id}/")
    Call<Post> getMyJSON(
            @Path("id") String id
    );



}