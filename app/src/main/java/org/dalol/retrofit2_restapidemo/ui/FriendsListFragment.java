package org.dalol.retrofit2_restapidemo.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.dalol.retrofit2_restapidemo.model.helper.Constants;
import org.dalol.retrofit2_restapidemo.model.pojo.User;

public class FriendsListFragment extends Fragment {

    private User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        if (args == null) throw new IllegalArgumentException("Arguments must be provided for FriendsFragment ");
        user = (User) args.getSerializable(Constants.REFERENCE.USER);

        RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setAdapter(new Adapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        return recyclerView;
    }

    private static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView text;

        ViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(android.R.id.text1);
        }

        private void bind(String name){
            text.setText(name);
        }
    }

    private class Adapter extends RecyclerView.Adapter<ViewHolder>{

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.bind(user.getFriends()[position].getName());
        }

        @Override
        public int getItemCount() {
            return user.getFriends().length;
        }
    }
}
