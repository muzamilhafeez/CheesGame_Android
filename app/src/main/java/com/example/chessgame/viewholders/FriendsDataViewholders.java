package com.example.chessgame.viewholders;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessgame.R;

public class FriendsDataViewholders extends RecyclerView.ViewHolder {

  public  TextView friendid;
    public TextView friendname;
    public Button  btnaccept;
    public FriendsDataViewholders(@NonNull View itemView) {
        super(itemView);

        friendid=itemView.findViewById(R.id.friendsdataid);
        friendname=itemView.findViewById(R.id.friendsdataname);
        btnaccept=itemView.findViewById(R.id.button7);
    }
}
