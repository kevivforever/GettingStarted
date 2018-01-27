package in.finder.gettingstarted.Network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class MovieDBApiClient {

    public static final String API_KEY = "d678e45c56f7d5dd79782aadcce22818";

    public static Retrofit retrofit;

    public void MovieDBClient(){

    }

    public static Retrofit getMovieDBApiClient(){

        if(retrofit==null){
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            OkHttpClient okHttpClient = builder.build();

            retrofit = new Retrofit.Builder()
                        .baseUrl("http://api.themoviedb.org/3/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(okHttpClient)
                        .build();

        }

        return retrofit;
    }

    private static OkHttpClient createOkHttpClient() {
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                final Request request = chain.request().newBuilder().addHeader("api_key", API_KEY).build();
                return chain.proceed(request);
            }
        });
        return httpClient.build();
    }
}
