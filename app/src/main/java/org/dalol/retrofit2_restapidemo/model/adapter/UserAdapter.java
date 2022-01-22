
package org.dalol.retrofit2_restapidemo.model.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;

import org.dalol.retrofit2_restapidemo.R;
import org.dalol.retrofit2_restapidemo.model.pojo.User;

import java.util.ArrayList;
import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.Holder> {

    private static final String TAG = UserAdapter.class.getSimpleName();
    private final UserClickListener mListener;
    private List<User> mUsers;

    private RequestManager mGlide;

    public UserAdapter(UserClickListener listener, RequestManager glide) {
        mUsers = new ArrayList<>();
        mListener = listener;
        this.mGlide = glide;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, null, false);
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        User currUser = mUsers.get(position);
        holder.bind(currUser);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void setUsers(List<User> users) {
        mUsers = users;
        notifyDataSetChanged();
    }


    public User getSelectedUser(int position) {
        return mUsers.get(position);
    }

    public void reset() {
        mUsers.clear();
        notifyDataSetChanged();
    }


    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mPhoto;
        private TextView mName, mBalance;

        public Holder(View itemView) {
            super(itemView);
            mPhoto = itemView.findViewById(R.id.userPhoto);
            mName = itemView.findViewById(R.id.userName);
            mBalance = itemView.findViewById(R.id.userBalance);
            itemView.setOnClickListener(this);
        }

        private void bind(User user){
            mName.setText(user.getName());
            mBalance.setText(user.getBalance());
            mGlide.load(user.getPicture()).into(mPhoto);
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(getLayoutPosition());
        }
    }

    public interface UserClickListener {

        void onClick(int position);
    }
}
