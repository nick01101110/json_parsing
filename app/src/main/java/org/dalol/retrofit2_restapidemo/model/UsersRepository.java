package org.dalol.retrofit2_restapidemo.model;

import android.content.Context;
import android.util.Log;

import org.dalol.retrofit2_restapidemo.controller.RestManager;
import org.dalol.retrofit2_restapidemo.model.database.UserDatabase;
import org.dalol.retrofit2_restapidemo.model.pojo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersRepository {
    private static UsersRepository instance;

    public static UsersRepository getInstance(Context context){
        if (instance == null){
            instance = new UsersRepository(context);
        }
        return instance;
    }

    private List<Listener> mListeners = new ArrayList<>();

    public void addListener(Listener listener){
        mListeners.add(listener);
    }

    public void removeListener(Listener listener){
        mListeners.remove(listener);
    }

    private List<User> mUsers = null;
    private boolean mIsLoading = false;
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    private RestManager mManager = new RestManager();
    UserDatabase mDatabase;

    private UsersRepository(Context context){
        mDatabase = new UserDatabase(context);
    }

    private void startLoading(){
        mIsLoading = true;
        for (Listener listener : mListeners){
            listener.onLoadingStarted();
        }

        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<User> users = mDatabase.fetchUsers();
                if (users.size() == 0){
                    loadFromNetwork();
                }else{
                    mUsers = users;
                    for (Listener listener: mListeners){
                        listener.onLoaded(mUsers);
                    }
                }
            }
        });
    }

    private void loadFailed(){
        for (Listener listener: mListeners){
            listener.onLoadingFailed();
        }
    }

    public void reload(){
        mIsLoading = true;
        for (Listener listener : mListeners){
            listener.onLoadingStarted();
        }
        mUsers = null;
        loadFromNetwork();
    }

    private void loadFromNetwork() {
        Call<List<User>> listCall = mManager.getUserService().getAllUsers();
        listCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    mUsers = response.body();
                    if (mUsers != null) {
                        for (Listener listener: mListeners){
                            listener.onLoaded(mUsers);
                        }
                        mExecutor.execute(new Runnable() {
                            @Override
                            public void run() {
                                mDatabase.addUsers(mUsers);
                            }
                        });
                    }else{
                        loadFailed();
                    }
                } else {
                    int sc = response.code();
                    switch (sc) {
                        case 400:
                            Log.e("Error 400", "Bad Request");
                            break;
                        case 404:
                            Log.e("Error 404", "Not Found");
                            break;
                        default:
                            Log.e("Error", "Generic Error");
                    }
                    loadFailed();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                loadFailed();
            }
        });
    }



    public List<User> getUsers(){
        if (mUsers == null && !mIsLoading){
            startLoading();
        }
        return mUsers;
    }

    public interface Listener{
        void onLoadingStarted();
        void onLoadingFailed();
        void onLoaded(List<User> users);
    }
}
