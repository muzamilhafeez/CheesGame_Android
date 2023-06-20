package com.example.chessgame.Pieces.pawns.darkpawn;

import android.view.View;

import com.example.chessgame.R;
import com.example.chessgame.databinding.ActivityMain2Binding;

public class DPnRow7Pcs2 {

    public void movePawn(View view, ActivityMain2Binding Binding){
        int viewID= view.getId();
        if(viewID== R.id.L7G){
            Binding.L7G.setImageResource(R.drawable.light);
            Binding.L6G.setImageResource(R.drawable.bp);
        }
        else  if(viewID==R.id.L6G){
            Binding.L6G.setImageResource(R.drawable.dark);
            Binding.L5G.setImageResource(R.drawable.bp);
        }
        else if(viewID==R.id.L6G){
            Binding.L6G.setImageResource(R.drawable.light);
            Binding.L5G.setImageResource(R.drawable.bp);
        }
    }
}
