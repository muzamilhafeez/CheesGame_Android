package com.example.chessgame.Pieces.pawns.darkpawn;

import android.view.View;

import com.example.chessgame.R;
import com.example.chessgame.databinding.ActivityMain2Binding;

public class DPnRow7PcsA {
    public void movePawn(View view, ActivityMain2Binding Binding) {
        int viewID = view.getId();

        if (viewID == R.id.L7H) {

            //Binding.L7H.setImageResource(R.color.white);
            Binding.L6H.setImageResource(R.color.white);
            Binding.L5H.setImageResource(R.color.white);
            //  viewID= Integer.parseInt(null);


            Binding.L6H.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Binding.L7H.setBackgroundResource(R.drawable.dark);
                    Binding.L7H.setImageResource(0);
                    Binding.L6H.setImageResource(0);
                    Binding.L5H.setImageResource(0);
                    Binding.L7H.setImageResource(R.drawable.dark);
                    Binding.L7H.setImageDrawable(null);
                    Binding.L6H.setImageResource(R.drawable.bp);
                    Binding.L7H.setOnClickListener(null);
                    //viewID= Integer.parseInt(null);
                }
            });

        }
        else if (viewID == R.id.L6H) {
            Binding.L5H.setImageResource(R.color.white);
            Binding.L4H.setImageResource(R.color.white);


            Binding.L5H.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Binding.L7H.setBackgroundResource(R.drawable.dark);
                    Binding.L6H.setImageResource(0);
                    Binding.L5H.setImageResource(0);
                    Binding.L4H.setImageResource(0);
                    Binding.L6H.setImageResource(R.drawable.dark);
                    Binding.L6H.setImageDrawable(null);
                    Binding.L5H.setImageResource(R.drawable.bp);
                    Binding.L6H.setOnClickListener(null);
                }
            });
        }

        else if (viewID == R.id.L5H) {
            Binding.L4H.setImageResource(R.color.white);
            Binding.L3H.setImageResource(R.color.white);


            Binding.L4H.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Binding.L7H.setBackgroundResource(R.drawable.dark);
                    Binding.L5H.setImageResource(0);
                    Binding.L4H.setImageResource(0);
                    Binding.L3H.setImageResource(0);
                    Binding.L5H.setImageResource(R.drawable.dark);
                    Binding.L5H.setImageDrawable(null);
                    Binding.L4H.setImageResource(R.drawable.bp);
                    Binding.L5H.setOnClickListener(null);
                }
            });
        }
    }
}
