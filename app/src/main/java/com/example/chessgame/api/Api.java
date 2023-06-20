package com.example.chessgame.api;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.chessgame.models.GetFriendsdatamodel;
import com.example.chessgame.models.GetOnlineMatchList;
import com.example.chessgame.models.GetPendingRequests;
import com.example.chessgame.models.GetmatchRequestDetails;
import com.example.chessgame.models.PlayOnlineDetails;
import com.example.chessgame.models.SendmatchrequestDetails;
import com.example.chessgame.models.UserDetails;
import com.example.chessgame.models.getnextmovedetails;
import com.example.chessgame.models.modelfriendlist;
import com.example.chessgame.models.playerjoineddetails;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface Api {
    public static String
            BASE_URL="http://192.168.43.220/ChessGameAPI/api/";
   // BASE_URL="http://192.168.1.40/ChessGameAPI/api/";
    public static String
            IMAGE_BASE_URL="http://192.168.1.40/FYPApi/";



    @POST("game/NewMove")
    public Call<Integer> newMove(@QueryMap Map<String, String> queryParams);

    @GET("game/Login")
    public Call<UserDetails> login(
            @Query("name") String name,
            @Query("password") String password
    );

    @GET("game/pendingrequest")
    public Call<ArrayList<GetPendingRequests>> GET_PENDING_REQUESTS_CALL(
            @Query("playerid") int playerid
            );
    @GET("game/getplayerdata")
    public Call<ArrayList<modelfriendlist>> getplayerdata(

            @Query("playerid") int playerid
    );

    @GET("game/getonlinematchlist")
    public Call<ArrayList<GetOnlineMatchList>> getonlinematchlist(

    );
    @GET("game/getfriends")
    public Call<ArrayList<GetFriendsdatamodel>> getFriendsdata_call(
            @Query("userid") int userid

    );
    @POST("game/CreateAccount")
    public Call<String> CreateAccount(
            @Query("name") String name,
            @Query("password") String password
    );


    @POST("game/acceptfriendrequest")
    public Call<String> acceptfriendrequest_call(
            @Query("userid") int userid,
            @Query("reqid") int reqid,
            @Query("status") int status
    );

    @POST("game/sendmatchrequest")
    public Call<SendmatchrequestDetails> SendmatchRequest(
            @Query("playe1id") int player1id,
            @Query("player2id") int player2id
    );

    @POST("game/sendrequest")
    public Call<String> SendRequest(
            @Query("req_from") int reqfrom,
            @Query("req_to") int reqto
    );
    @GET("game/GetMatchRequest")
    public Call<ArrayList<GetmatchRequestDetails>> GetMatchRequest(
            @Query("playerid") int playerid
    );

    @GET("game/getnextmove")
    public Call<getnextmovedetails> GETNEXTMOVEDETAILS_CALL(
            @Query("playerid") int playerid,
             @Query("matchid") int matchid
    );
    @GET("game/playonline")
    public Call <PlayOnlineDetails> playonline(
            @Query("playerid") int playerid
    );


    @GET("game/playerjoined")
    public Call <playerjoineddetails> playerjoined(
            @Query("matchid") int matchid
    );

    @NonNull
    public default MultipartBody.Part prepareFilePart(String partName, Uri fileUri, Context context) throws IOException {
        File file = FileUtil.from(context, fileUri);
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(context.getContentResolver().getType(fileUri)),
                        file
                );
        return MultipartBody.Part.createFormData(partName,
                file.getName(),
                requestFile);
    }
    public default RequestBody createPartFromString(String descriptionString){
        RequestBody description =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, descriptionString);
        return  description;
    }
}