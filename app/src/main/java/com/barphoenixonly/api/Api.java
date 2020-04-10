package com.barphoenixonly.api;


import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.POST;
import rx.Observable;

public interface Api {

   @POST("k9gBgyd8")
   Observable<Response<ResponseBody>> sendRequest();
}
