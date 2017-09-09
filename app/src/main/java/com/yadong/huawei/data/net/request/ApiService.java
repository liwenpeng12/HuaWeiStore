package com.yadong.huawei.data.net.request;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 *
 */

public interface ApiService {


     String BASE_URL = "http://112.124.22.238:8081/appstore/";

    /**
     * 推荐页面的所有数据
     */
    @GET("recommend")
    Observable<ResponseBody> getRecommendData();


}
