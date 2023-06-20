package com.example.chessgame.Pieces;

import android.media.Image;
import android.widget.ImageView;

public class Piece {
    public String name;
    public int row;
    public int col;

    @Override
    public String toString() {
        return "Piece{" +
                "name='" + name + '\'' +
                ", row=" + row +
                ", col=" + col +
                '}';
    }

    public Piece(String name, int row, int col) {
        this.name = name;
        this.row = row;
        this.col = col;

    }
}
