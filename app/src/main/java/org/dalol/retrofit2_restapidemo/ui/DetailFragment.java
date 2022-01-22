package org.dalol.retrofit2_restapidemo.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.dalol.retrofit2_restapidemo.R;
import org.dalol.retrofit2_restapidemo.model.helper.Constants;
import org.dalol.retrofit2_restapidemo.model.pojo.User;

public class DetailFragment extends Fragment{
    private User user;

    public DetailFragment() {}

    private String joinStrings(String [] strings){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strings.length; i++){
            builder.append(strings[i]);
            if (i < strings.length - 1){
                builder.append(", ");
            }
        }
        return builder.toString();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        if (args == null) throw new IllegalArgumentException("Arguments must be provided for DetailFragment ");
        user = (User) args.getSerializable(Constants.REFERENCE.USER);
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        ImageView mPhoto = view.findViewById(R.id.upicture);
        TextView mid = view.findViewById(R.id.uid);
        TextView misActive = view.findViewById(R.id.uisActive);
        TextView mbalance = view.findViewById(R.id.ubalance);
        TextView mage = view.findViewById(R.id.uage);
        TextView meyeColor = view.findViewById(R.id.ueyeColor);
        TextView mname = view.findViewById(R.id.uname);
        TextView mgender = view.findViewById(R.id.ugender);
        TextView mcompany = view.findViewById(R.id.ucompany);
        TextView memail = view.findViewById(R.id.uemail);
        TextView mphone = view.findViewById(R.id.uphone);
        TextView maddress = view.findViewById(R.id.uaddress);
        TextView mabout = view.findViewById(R.id.uabout);
        TextView mregistered = view.findViewById(R.id.uregistered);
        TextView mfavoriteFruit = view.findViewById(R.id.ufavoriteFruit);
        TextView mTags = view.findViewById(R.id.utags);

        mid.setText(String.valueOf(user.getId()));
        misActive.setText(user.isActive()? "true" : "false");
        mbalance.setText(user.getBalance());
        mage.setText(String.valueOf(user.getAge()));
        meyeColor.setText(user.getEyeColor());
        mname.setText(user.getName());
        mgender.setText(user.getGender());
        mcompany.setText(user.getCompany());
        memail.setText(user.getEmail());
        mphone.setText(user.getPhone());
        maddress.setText(user.getAddress());
        mabout.setText(user.getAbout());
        mregistered.setText(user.getRegistered());
        mfavoriteFruit.setText(user.getFavoriteFruit());
        mTags.setText(joinStrings(user.getTags()));

        Glide.with(this).load(user.getPicture()).into(mPhoto);

        return view;
    }
}
