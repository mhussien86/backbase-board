package com.backbase.boardmembers.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mohamed on 17/02/17.
 */
public class ServiceGenerator {

    public static final String API_BASE_URL = APIConstants.BASE_SERVICE_URL;

    OkHttpClient.Builder httpClient;
    Retrofit.Builder builder;

    public ServiceGenerator(){
        httpClient = new OkHttpClient.Builder();
        Gson gson = new GsonBuilder() .setLenient() .create();
        builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));
    }



    // method to create any retrofit service
    public  <T> T createService(Class<T> serviceClass) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.interceptors().add(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {

                Request original = chain.request();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder().method(original.method(), original.body());
                requestBuilder.addHeader("Content-Type", "application/json; text/html; charset=UTF-8");

                Request request = requestBuilder.build();


                return chain.proceed(request);
            }
        });

        httpClient.addInterceptor(logging);

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();

        return retrofit.create(serviceClass);
    }
}
