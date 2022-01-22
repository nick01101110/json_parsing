
package org.dalol.retrofit2_restapidemo.model.callback;

import org.dalol.retrofit2_restapidemo.model.pojo.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface UserService {

    @GET("/v2/59c92a123f0000780183f72d")
    Call<List<User>> getAllUsers();
}
