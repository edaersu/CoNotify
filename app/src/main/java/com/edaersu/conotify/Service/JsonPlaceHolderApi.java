package com.edaersu.conotify.Service;

        import com.edaersu.conotify.Model.CoModel;

        import java.util.ArrayList;

        import retrofit2.Call;
        import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("gunlukRakamlar.json")
    Call<ArrayList<CoModel>> getStatistics();
}
