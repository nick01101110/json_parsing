

package org.dalol.retrofit2_restapidemo.controller;

import org.dalol.retrofit2_restapidemo.model.callback.UserService;
import org.dalol.retrofit2_restapidemo.model.helper.Constants;

import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * @author Filippo Engidashet
 * @version 1.0.0
 * @date 1/22/2016
 */
public class RestManager {

    private UserService mUserService;

    public UserService getUserService() {
        if (mUserService == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.HTTP.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            mUserService = retrofit.create(UserService.class);
        }
        return mUserService;
    }
}
