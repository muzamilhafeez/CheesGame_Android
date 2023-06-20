package com.example.chessgame.Pieces.pawns.darkpawn;

import android.view.View;

import com.example.chessgame.Pieces.pawns.darkpawn.DPnRow7Pcs2;
import com.example.chessgame.R;
import com.example.chessgame.databinding.ActivityMain2Binding;

public class pawnmethod {

    public void moveallpawn(View view, ActivityMain2Binding Binding) {
        DPnRow7PcsA pa =new DPnRow7PcsA();
        pa.movePawn(view, Binding);

        //calling darkpawn row 7 pcs 2
        DPnRow7Pcs2 pp = new DPnRow7Pcs2();
        pp.movePawn(view, Binding);


    }


}
