package com.example.rohan.limetray;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Rohan on 02-Jun-16.
 */
public interface TransactionAPI {

    @GET("/api/jsonBlob/{blobId}")

    Call<Response> getExpenses(@Path("blobId") String blobID);


    @POST("/api/jsonBlob")
    Call<Response> updateExpenseState(@Body Response response);
}
