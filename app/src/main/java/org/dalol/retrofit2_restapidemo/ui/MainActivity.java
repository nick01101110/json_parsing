
package org.dalol.retrofit2_restapidemo.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import org.dalol.retrofit2_restapidemo.R;
import org.dalol.retrofit2_restapidemo.model.UsersRepository;
import org.dalol.retrofit2_restapidemo.model.adapter.UserAdapter;
import org.dalol.retrofit2_restapidemo.model.helper.Constants;
import org.dalol.retrofit2_restapidemo.model.pojo.User;

import java.util.List;


public class MainActivity extends AppCompatActivity implements UserAdapter.UserClickListener, UsersRepository.Listener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private UserAdapter mUserAdapter;
    private Button mReload;
    private ProgressDialog mDialog;

    private UsersRepository mRepository;
    private boolean mLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = UsersRepository.getInstance(this);
        setContentView(R.layout.activity_main);
        configViews();
        mReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRepository.reload();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "pause");
        mRepository.removeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "resume");
        mRepository.addListener(this);
        if (!mLoaded){
            mLoaded = true;
            List<User> users = mRepository.getUsers();
            if (users != null){
                mUserAdapter.setUsers(users);
            }
        }
    }

    private void configViews() {
        mReload = findViewById(R.id.reload);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        RequestManager glide = Glide.with(this);
        mUserAdapter = new UserAdapter(this, glide);
        mRecyclerView.setAdapter(mUserAdapter);
    }

    @Override
    public void onClick(int position) {
        User selectedUser = mUserAdapter.getSelectedUser(position);
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra(Constants.REFERENCE.USER, selectedUser);
        startActivity(intent);
    }

    @Override
    public void onLoadingStarted() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mDialog = new ProgressDialog(MainActivity.this);
                mDialog.setMessage("Loading User Data...");
                mDialog.setCancelable(true);
                mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mDialog.setIndeterminate(true);
                mDialog.show();
            }
        });
    }

    @Override
    public void onLoadingFailed() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mDialog.dismiss();
            }
        });
    }

    @Override
    public void onLoaded(final List<User> users) {
        Log.i(TAG, "loading succeeded");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mDialog.dismiss();
                mUserAdapter.setUsers(users);
            }
        });
    }
}
