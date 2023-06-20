package com.example.chessgame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.chessgame.Pieces.Piece;
import com.example.chessgame.Pieces.Position;
import com.example.chessgame.adapters.MyAdapter;
import com.example.chessgame.api.Api;
import com.example.chessgame.api.RetrofitClient;
import com.example.chessgame.databinding.ActivityChessBoardOnlineBinding;
import com.example.chessgame.databinding.ActivitySpectatorScreenBinding;
import com.example.chessgame.models.GlobalData;
import com.example.chessgame.models.getnextmovedetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Spectator_Screen extends AppCompatActivity implements View.OnClickListener {
    ActivitySpectatorScreenBinding Binding;


    ArrayList<String> holder = new ArrayList<>();
    ArrayList<String> holder2 = new ArrayList<>();

    MyAdapter adapter;

    ImageView[][] board = new ImageView[8][8];
    Piece[][] pieces = new Piece[8][8];

    boolean isWhiteMoving = true;

    ////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////################ function for intialize image       #################//////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////
    private void initializePieces() {

        //white pieces
        pieces[0][0] = new Piece("wr", 0, 0);
        pieces[0][1] = new Piece("wn", 0, 1);
        pieces[0][2] = new Piece("wb", 0, 2);
        pieces[0][3] = new Piece("wk", 0, 3);
        pieces[0][4] = new Piece("wq", 0, 4);
        pieces[0][5] = new Piece("wb", 0, 5);
        pieces[0][6] = new Piece("wn", 0, 6);
        pieces[0][7] = new Piece("wr", 0, 7);


        pieces[1][0] = new Piece("wp", 1, 0);
        pieces[1][1] = new Piece("wp", 1, 1);
        pieces[1][2] = new Piece("wp", 1, 2);
        pieces[1][3] = new Piece("wp", 1, 3);
        pieces[1][4] = new Piece("wp", 1, 4);
        pieces[1][5] = new Piece("wp", 1, 5);
        pieces[1][6] = new Piece("wp", 1, 6);
        pieces[1][7] = new Piece("wp", 1, 7);


        //black pieces

        pieces[6][0] = new Piece("bp", 6, 0);
        pieces[6][1] = new Piece("bp", 6, 1);
        pieces[6][2] = new Piece("bp", 6, 2);
        pieces[6][3] = new Piece("bp", 6, 3);
        pieces[6][4] = new Piece("bp", 6, 4);
        pieces[6][5] = new Piece("bp", 6, 5);
        pieces[6][6] = new Piece("bp", 6, 6);
        pieces[6][7] = new Piece("bp", 6, 7);


        pieces[7][0] = new Piece("br", 7, 0);
        pieces[7][1] = new Piece("bn", 7, 1);
        pieces[7][2] = new Piece("bb", 7, 2);
        pieces[7][3] = new Piece("bk", 7, 3);
        pieces[7][4] = new Piece("bq", 7, 4);
        pieces[7][5] = new Piece("bb", 7, 5);
        pieces[7][6] = new Piece("bn", 7, 6);
        pieces[7][7] = new Piece("br", 7, 7);


    }

////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////################ function for Populate Grid of  (2d Board array      #################//////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////


    private void populateGrid() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null) {
                    Piece p = pieces[i][j];

                    if (p.name.equals("wr")) {

                        board[i][j].setImageResource(R.drawable.wr);
                    } else if (p.name.equals("wn")) {
                        board[i][j].setImageResource(R.drawable.wn);
                    } else if (p.name.equals("wb")) {
                        board[i][j].setImageResource(R.drawable.wb);
                    } else if (p.name.equals("wk")) {
                        board[i][j].setImageResource(R.drawable.wk);
                    } else if (p.name.equals("wq")) {
                        board[i][j].setImageResource(R.drawable.wq);
                    } else if (p.name.equals("wp")) {
                        board[i][j].setImageResource(R.drawable.wp);
                    } else if (p.name.equals("br")) {
                        board[i][j].setImageResource(R.drawable.br);
                    } else if (p.name.equals("bn")) {
                        board[i][j].setImageResource(R.drawable.bn);
                    } else if (p.name.equals("bb")) {
                        board[i][j].setImageResource(R.drawable.bb);
                    } else if (p.name.equals("bk")) {
                        board[i][j].setImageResource(R.drawable.bk);
                    } else if (p.name.equals("bq")) {
                        board[i][j].setImageResource(R.drawable.bq);
                    } else if (p.name.equals("bp")) {
                        board[i][j].setImageResource(R.drawable.bp);
                    }


                } else {
                    board[i][j].setImageResource(R.drawable.empty);
                }
            }
        }
    }


    int moveId = -1;

    private void getMove() {
        try {
            Api api = RetrofitClient.getInstance().getMyApi();
            api.GETNEXTMOVEDETAILS_CALL(GlobalData.obj.id, GlobalData.matchId).enqueue(new Callback<getnextmovedetails>() {
                @Override
                public void onResponse(Call<getnextmovedetails> call, Response<getnextmovedetails> response) {
                    if (response.isSuccessful()) {
                        getnextmovedetails obj = response.body();
                        if (obj.id != moveId) {
                            moveId = obj.id;
                            Piece p = pieces[obj.fromrow][obj.fromcol];
                            Log.d("GET_MOVE", "FROM-> "+p);

                            Log.d("GET_MOVE", "TO-> "+pieces[obj.torow][obj.tocol]);
                            p.col = obj.tocol;
                            p.row = obj.torow;
                            pieces[obj.fromrow][obj.fromcol] = null;
                            pieces[obj.torow][obj.tocol] = p;


                            if (GlobalData.player1Id == GlobalData.obj.id) {
                                isWhiteMoving = true;
                            } else {
                                isWhiteMoving = false;
                            }
//                        if(isWhiteMoving){
//                            isWhiteMoving = !isWhiteMoving;
//                        }
                            populateGrid();

                        }
                    }

                }

                @Override
                public void onFailure(Call<getnextmovedetails> call, Throwable t) {

                }
            });
        } catch (Exception ex) {
            Log.d("GET_MOVE", ex.toString());
        }
    }


    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = ActivitySpectatorScreenBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());

        Binding.textiew1.setText("Player Two : " + GlobalData.player2Id);
        Binding.textiew.setText("Player One : " + GlobalData.player1Id);


        handler = new Handler(Looper.getMainLooper());
        runnable = new Runnable() {
            @Override
            public void run() {
                getMove();
                handler.postDelayed(this, 5000);
            }
        };
        handler.postDelayed(runnable, 5000);


        Binding.buttonget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMove();
            }
        });


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 6);
        Binding.gvBoard.setLayoutManager(gridLayoutManager);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this, 6);
        Binding.gvBoard2.setLayoutManager(gridLayoutManager2);
        //make an 2d array for displaying board

        // ImageView[][] board = new ImageView[8][8];


        //8
        board[0][0] = findViewById(R.id.L8H);
        board[0][1] = findViewById(R.id.L8G);
        board[0][2] = findViewById(R.id.L8F);
        board[0][3] = findViewById(R.id.L8E);
        board[0][4] = findViewById(R.id.L8D);
        board[0][5] = findViewById(R.id.L8C);
        board[0][6] = findViewById(R.id.L8B);
        board[0][7] = findViewById(R.id.L8A);

        //7
        board[1][0] = findViewById(R.id.L7H);
        board[1][1] = findViewById(R.id.L7G);
        board[1][2] = findViewById(R.id.L7F);
        board[1][3] = findViewById(R.id.L7E);
        board[1][4] = findViewById(R.id.L7D);
        board[1][5] = findViewById(R.id.L7C);
        board[1][6] = findViewById(R.id.L7B);
        board[1][7] = findViewById(R.id.L7A);

        //6
        board[2][0] = findViewById(R.id.L6H);
        board[2][1] = findViewById(R.id.L6G);
        board[2][2] = findViewById(R.id.L6F);
        board[2][3] = findViewById(R.id.L6E);
        board[2][4] = findViewById(R.id.L6D);
        board[2][5] = findViewById(R.id.L6C);
        board[2][6] = findViewById(R.id.L6B);
        board[2][7] = findViewById(R.id.L6A);

        //5


        board[3][0] = findViewById(R.id.L5H);
        board[3][1] = findViewById(R.id.L5G);
        board[3][2] = findViewById(R.id.L5F);
        board[3][3] = findViewById(R.id.L5E);
        board[3][4] = findViewById(R.id.L5D);
        board[3][5] = findViewById(R.id.L5C);
        board[3][6] = findViewById(R.id.L5B);
        board[3][7] = findViewById(R.id.L5A);

        //4
        board[4][0] = findViewById(R.id.L4H);
        board[4][1] = findViewById(R.id.L4G);
        board[4][2] = findViewById(R.id.L4F);
        board[4][3] = findViewById(R.id.L4E);
        board[4][4] = findViewById(R.id.L4D);
        board[4][5] = findViewById(R.id.L4C);
        board[4][6] = findViewById(R.id.L4B);
        board[4][7] = findViewById(R.id.L4A);


        //3
        board[5][0] = findViewById(R.id.L3H);
        board[5][1] = findViewById(R.id.L3G);
        board[5][2] = findViewById(R.id.L3F);
        board[5][3] = findViewById(R.id.L3E);
        board[5][4] = findViewById(R.id.L3D);
        board[5][5] = findViewById(R.id.L3C);
        board[5][6] = findViewById(R.id.L3B);
        board[5][7] = findViewById(R.id.L3A);

        //2

        board[6][0] = findViewById(R.id.L2H);
        board[6][1] = findViewById(R.id.L2G);
        board[6][2] = findViewById(R.id.L2F);
        board[6][3] = findViewById(R.id.L2E);
        board[6][4] = findViewById(R.id.L2D);
        board[6][5] = findViewById(R.id.L2C);
        board[6][6] = findViewById(R.id.L2B);
        board[6][7] = findViewById(R.id.L2A);


        //1
        board[7][0] = findViewById(R.id.L1H);
        board[7][1] = findViewById(R.id.L1G);
        board[7][2] = findViewById(R.id.L1F);
        board[7][3] = findViewById(R.id.L1E);
        board[7][4] = findViewById(R.id.L1D);
        board[7][5] = findViewById(R.id.L1C);
        board[7][6] = findViewById(R.id.L1B);
        board[7][7] = findViewById(R.id.L1A);


        initializePieces();
        populateGrid();

////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////################ for loop for apply click listener on imageviews      #################//////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j].setOnClickListener(this);
            }
        }

       /*//
        board[0][0].setOnClickListener(view -> {
            board[0][0].setBackgroundResource(R.drawable.highlight);
        });

        board[0][1].setOnClickListener(view -> {
            board[0][1].setBackgroundResource(R.drawable.highlight);
        });
        board[0][2].setOnClickListener(view -> {
            board[0][0].setBackgroundResource(R.drawable.highlight);
        });

*/


    }

    int fromrow, fromclm;
    int torow, toclm;
    boolean ismoving = false;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (view.getId() == board[i][j].getId()) {
                    if (pieces[fromrow][fromclm] == pieces[i][j] && ismoving) {
                        Toast.makeText(getApplicationContext(), "Same", Toast.LENGTH_SHORT).show();
                        ismoving = false;
//                        fromclm = -1;
//                        torow = -1;
                        populateGrid();
                        return;
                    }
                    if (ismoving) {

                        if (isValidMove(new Position(i, j))) {
                            Piece p = pieces[fromrow][fromclm];
                            pieces[fromrow][fromclm] = null;
                            if (pieces[i][j] != null && pieces[i][j].name.length() > 0) {


                                String imageView = pieces[i][j].name;
                                String imageView2 = pieces[i][j].name;


                                if (pieces[i][j].name.startsWith("w")) {

                                    holder2.add(imageView);
                                    Toast.makeText(this, "" + holder2.size(), Toast.LENGTH_SHORT).show();
                                    adapter = new MyAdapter(holder2, getApplicationContext());
                                    Binding.gvBoard2.setAdapter(adapter);

                                } else if (pieces[i][j].name.startsWith("b")) {
                                    holder.add(imageView2);
                                    Toast.makeText(this, "kk" + holder.size(), Toast.LENGTH_SHORT).show();
                                    adapter = new MyAdapter(holder, getApplicationContext());
                                    Binding.gvBoard.setAdapter(adapter);

                                }


                                Toast.makeText(getApplicationContext(), "Killed", Toast.LENGTH_SHORT).show();
                            }
                            //send move to api calling
                            sendMoveToApi(fromrow, fromclm, i, j);
                            p.row = i;
                            p.col = j;
                            pieces[i][j] = p;
                            ismoving = false;
                            populateGrid();
                            isWhiteMoving = !isWhiteMoving;
                        } else {
                            Toast.makeText(Spectator_Screen.this, "Invalid Move", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }

                    if (pieces[i][j] != null) {


                        //agr piece praa hwa hy to..
                        if (ismoving != true) {            //agr moving true ni hy to..

                            fromrow = i;   //source row  equal ho gya ""i"" kay..
                            fromclm = j;   //source column  equal ho gya ""j"" kay..
                            if (isWhiteMoving) {
                                if (pieces[i][j].name.startsWith("w")) {

                                    ArrayList<Position> moves = computeValidMoves(pieces[i][j]);
                                    if (moves.size() == 0) {
                                        Toast.makeText(this, "can't move", Toast.LENGTH_SHORT).show();
                                    }
                                    for (Position p : moves) {


                                        board[p.row][p.column].setImageResource(R.drawable.highlight);
                                        ismoving = true;
                                    }
                                } else {

                                    Toast.makeText(getApplicationContext(), "its Player 1 turn", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            } else if (!isWhiteMoving) {
                                if (pieces[i][j].name.startsWith("b")) {

                                    ArrayList<Position> moves = computeValidMoves(pieces[i][j]);
                                    if (moves.size() == 0) {
                                        Toast.makeText(this, "can't move", Toast.LENGTH_SHORT).show();

                                    }
                                    for (Position p : moves) {
                                        board[p.row][p.column].setImageResource(R.drawable.highlight);
                                        ismoving = true;
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "its Player 2 turn", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }


                            //Toast.makeText(getApplicationContext(), moves[0][0] + " " + moves[1][0], Toast.LENGTH_SHORT).show();


                            break;
                        }
                        // Toast.makeText(getApplicationContext(), p.name, Toast.LENGTH_SHORT).show();


                    }
                }
            }
        }
    }

    //send move to api
    private void sendMoveToApi(int fromR, int fromC, int toR, int toC) {
        Api api = RetrofitClient.getInstance().getMyApi();
        Map<String, String> map = new HashMap<>();
        map.put("matchid", String.valueOf(GlobalData.matchId));
        map.put("playerid", String.valueOf(GlobalData.obj.id));
        map.put("fromrow", String.valueOf(fromR));
        map.put("torow", String.valueOf(toR));
        map.put("fromcolumn", String.valueOf(fromC));
        map.put("tocolumn", String.valueOf(toC));
        api.newMove(map).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    moveId = response.body();
                    Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "save"+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private boolean isValidMove(Position position) {
        ArrayList<Position> moves = computeValidMoves(pieces[fromrow][fromclm]);
        return moves.stream().anyMatch(m -> m.row == position.row && m.column == position.column);
    }

    public ArrayList<Position> computeValidMoves(Piece p) {
        ArrayList<Position> moves = new ArrayList<>();


        //////////////////////////////////////////////////////
        //////////////RULES APPLY FOR WHITE PAWN//////////////
        //////////////////////////////////////////////////////
        if (p.name.equals("wp")) {


            if (pieces[p.row + 1][p.col] == null) {
                moves.add(new Position(p.row + 1, p.col));
            }
            if (p.row == 1 && pieces[p.row + 2][p.col] == null && pieces[p.row + 1][p.col] == null) {
                moves.add(new Position(p.row + 2, p.col));
            }
            if (p.col < 7 && pieces[p.row + 1][p.col + 1] != null && pieces[p.row + 1][p.col + 1].name.startsWith("b")) {
                moves.add(new Position(p.row + 1, p.col + 1));
            }
            if (p.col > 0 && pieces[p.row + 1][p.col - 1] != null && pieces[p.row + 1][p.col - 1].name.startsWith("b")) {
                moves.add(new Position(p.row + 1, p.col - 1));
            }


        }


        //////////////////////////////////////////////////////
        //////////////RULES APPLY FOR BLACK PAWN//////////////
        //////////////////////////////////////////////////////

        if (p.name.equals("bp")) {


            if (pieces[p.row - 1][p.col] == null) {
                moves.add(new Position(p.row - 1, p.col));
            }
            if (p.row == 6 && pieces[p.row - 2][p.col] == null && pieces[p.row - 1][p.col] == null) {
                moves.add(new Position(p.row - 2, p.col));
            }
            if (p.col > 0 && pieces[p.row - 1][p.col - 1] != null && pieces[p.row - 1][p.col - 1].name.startsWith("w")) {
                moves.add(new Position(p.row - 1, p.col - 1));
            }
            if (p.col < 7 && pieces[p.row - 1][p.col + 1] != null && pieces[p.row - 1][p.col + 1].name.startsWith("w")) {
                moves.add(new Position(p.row - 1, p.col + 1));
            }

        }

        //////////////////////////////////////////////////////
        //////////////RULES APPLY FOR Rook////////////////////
        //////////////////////////////////////////////////////

        else if (p.name.endsWith("r")) {
////////////////////////////////////////////////////////////////////////////////
//            90 degree rook movement
////////////////////////////////////////////////////////////////////////////////
            int c = p.col;
            int r = p.row;
            r = r - 1;
            while (r > -1 && pieces[r][c] == null) {
                moves.add(new Position(r, c));
                r = r - 1;
            }
            if (r > -1 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")) {
                moves.add(new Position(r, c));
            }
            if (r > -1 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")) {
                moves.add(new Position(r, c));
            }

////////////////////////////////////////////////////////////////////////////////
//            270 degree rook movement
////////////////////////////////////////////////////////////////////////////////
            c = p.col;
            r = p.row;
            r = r + 1;
            while (r < 8 && pieces[r][c] == null) {
                moves.add(new Position(r, c));
                r = r + 1;
            }
            if (r < 8 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")) {
                moves.add(new Position(r, c));
            }
            if (r < 8 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")) {
                moves.add(new Position(r, c));
            }
////////////////////////////////////////////////////////////////////////////////
//            0 degree rook movement
////////////////////////////////////////////////////////////////////////////////
            c = p.col;
            r = p.row;
            c = c + 1;
            while (c < 8 && pieces[r][c] == null) {
                moves.add(new Position(r, c));
                c = c + 1;
            }
            if (c < 8 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")) {
                moves.add(new Position(r, c));
            }
            if (c < 8 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")) {
                moves.add(new Position(r, c));
            }
////////////////////////////////////////////////////////////////////////////////
//            180 degree rook movement
////////////////////////////////////////////////////////////////////////////////
            c = p.col;
            r = p.row;
            c = c - 1;
            while (c > -1 && pieces[r][c] == null) {
                moves.add(new Position(r, c));
                c = c - 1;
            }
            if (c > -1 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")) {
                moves.add(new Position(r, c));
            }
            if (c > -1 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")) {
                moves.add(new Position(r, c));
            }

        }


        //////////////////////////////////////////////////////
        //////////////RULES APPLY FOR Bishop////////////////////
        //////////////////////////////////////////////////////

        else if (p.name.endsWith("b")) {
            ////////////////////////////////////////////////////////////////////////////////
//            215  degree  bishop movement
////////////////////////////////////////////////////////////////////////////////
            int c = p.col;
            int r = p.row;
            c = c + 1;
            r = r + 1;
            while (r < 8 && c < 8 && pieces[r][c] == null) {
                moves.add(new Position(r, c));
                r = r + 1;
                c = c + 1;

            }
            if (c < 8 && r < 8 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")) {
                moves.add(new Position(r, c));
            }
            if (c < 8 && r < 8 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")) {
                moves.add(new Position(r, c));
            }


            ////////////////////////////////////////////////////////////////////////////////
//            135 degree  bishop movement
////////////////////////////////////////////////////////////////////////////////

            c = p.col;
            r = p.row;
            c = c - 1;
            r = r - 1;
            while (r > -1 && c > -1 && pieces[r][c] == null) {
                moves.add(new Position(r, c));
                r = r - 1;
                c = c - 1;

            }
            if (c > -1 && r > -1 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")) {
                moves.add(new Position(r, c));
            }
            if (c > -1 && r > -1 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")) {
                moves.add(new Position(r, c));
            }


            ////////////////////////////////////////////////////////////////////////////////
//            45 degree bishop  movement
////////////////////////////////////////////////////////////////////////////////

            c = p.col;
            r = p.row;
            c = c + 1;
            r = r - 1;
            while (r > -1 && c < 8 && pieces[r][c] == null) {
                moves.add(new Position(r, c));
                r = r - 1;
                c = c + 1;

            }
            if (c < 8 && r > -1 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")) {
                moves.add(new Position(r, c));
            }
            if (c < 8 && r > -1 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")) {
                moves.add(new Position(r, c));
            }


            ////////////////////////////////////////////////////////////////////////////////
//            215 degree bishop  movement
////////////////////////////////////////////////////////////////////////////////

            c = p.col;
            r = p.row;
            c = c - 1;
            r = r + 1;
            while (r < 8 && c > -1 && pieces[r][c] == null) {
                moves.add(new Position(r, c));
                r = r + 1;
                c = c - 1;

            }
            if (c > -1 && r < 8 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")) {
                moves.add(new Position(r, c));
            }
            if (c > -1 && r < 8 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")) {
                moves.add(new Position(r, c));
            }
        }
        //////////////////////////////////////////////////////
        //////////////RULES APPLY FOR Queen//////////////
        //////////////////////////////////////////////////////

        else if (p.name.endsWith("q")) {
            ////////////////////////////////////////////////////////////////////////////////
//            90 degree queen movement
////////////////////////////////////////////////////////////////////////////////
            int c = p.col;
            int r = p.row;


            r = r - 1;
            while (r > -1 && pieces[r][c] == null) {
                moves.add(new Position(r, c));
                r = r - 1;
            }
            if (r > -1 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")) {
                moves.add(new Position(r, c));
            }
            if (r > -1 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")) {
                moves.add(new Position(r, c));
            }


            ////////////////////////////////////////////////////////////////////////////////
//            270 degree queen movement
////////////////////////////////////////////////////////////////////////////////
            c = p.col;
            r = p.row;


            r = r + 1;
            while (r < 8 && pieces[r][c] == null) {
                moves.add(new Position(r, c));
                r = r + 1;
            }
            if (r < 8 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")) {
                moves.add(new Position(r, c));
            }
            if (r < 8 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")) {
                moves.add(new Position(r, c));
            }
            ////////////////////////////////////////////////////////////////////////////////
//            0 degree queen movement
////////////////////////////////////////////////////////////////////////////////
            c = p.col;
            r = p.row;
            c = c + 1;
            while (c < 8 && pieces[r][c] == null) {
                moves.add(new Position(r, c));
                c = c + 1;
            }
            if (c < 8 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")) {
                moves.add(new Position(r, c));
            }
            if (c < 8 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")) {
                moves.add(new Position(r, c));
            }
            ////////////////////////////////////////////////////////////////////////////////
//            180 degree queen movement
////////////////////////////////////////////////////////////////////////////////
            c = p.col;
            r = p.row;
            c = c - 1;
            while (c > -1 && pieces[r][c] == null) {
                moves.add(new Position(r, c));
                c = c - 1;
            }
            if (c > -1 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")) {
                moves.add(new Position(r, c));
            }
            if (c > -1 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")) {
                moves.add(new Position(r, c));
            }
            ////////////////////////////////////////////////////////////////////////////////
//            315 degree queen movement
////////////////////////////////////////////////////////////////////////////////

            c = p.col;
            r = p.row;
            c = c + 1;
            r = r + 1;
            while (r < 8 && c < 8 && pieces[r][c] == null) {
                moves.add(new Position(r, c));
                r = r + 1;
                c = c + 1;

            }
            if (c < 8 && r < 8 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")) {
                moves.add(new Position(r, c));
            }
            if (c < 8 && r < 8 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")) {
                moves.add(new Position(r, c));
            }


            ////////////////////////////////////////////////////////////////////////////////
//            135 degree  queen movement
////////////////////////////////////////////////////////////////////////////////

            c = p.col;
            r = p.row;
            c = c - 1;
            r = r - 1;
            while (r > -1 && c > -1 && pieces[r][c] == null) {
                moves.add(new Position(r, c));
                r = r - 1;
                c = c - 1;

            }
            if (c > -1 && r > -1 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")) {
                moves.add(new Position(r, c));
            }
            if (c > -1 && r > -1 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")) {
                moves.add(new Position(r, c));
            }


            ////////////////////////////////////////////////////////////////////////////////
//            45 degree queen  movement
////////////////////////////////////////////////////////////////////////////////

            c = p.col;
            r = p.row;
            c = c + 1;
            r = r - 1;
            while (r > -1 && c < 8 && pieces[r][c] == null) {
                moves.add(new Position(r, c));
                r = r - 1;
                c = c + 1;

            }
            if (c < 8 && r > -1 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")) {
                moves.add(new Position(r, c));
            }
            if (c < 8 && r > -1 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")) {
                moves.add(new Position(r, c));
            }


            ////////////////////////////////////////////////////////////////////////////////
//            215 degree queen  movement
////////////////////////////////////////////////////////////////////////////////

            c = p.col;
            r = p.row;
            c = c - 1;
            r = r + 1;
            while (r < 8 && c > -1 && pieces[r][c] == null) {
                moves.add(new Position(r, c));
                r = r + 1;
                c = c - 1;

            }
            if (c > -1 && r < 8 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")) {
                moves.add(new Position(r, c));
            }
            if (c > -1 && r < 8 && pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")) {
                moves.add(new Position(r, c));
            }


        }

        //////////////////////////////////////////////////////
        //////////////RULES APPLY FOR Knight//////////////
        //////////////////////////////////////////////////////

        else if (p.name.endsWith("n")) {
            ////////////////////////////////////////////////////////////////////////////////
///////////// 230 degree knight movement////////////
////////////////////////////////////////////////////////////////////////////////
            int r = p.row + 2;
            int c = p.col - 1;
            if (r < 8 && c > -1) {
                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")))
                    moves.add(new Position(r, c));

                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")))
                    moves.add(new Position(r, c));
            }
//
//////////////////////////////////////////////////////////////////////////////////
/////////////// 310 degree knight movement////////////
//////////////////////////////////////////////////////////////////////////////////
            r = p.row + 2;
            c = p.col + 1;

            if (r < 8 && c < 8) {
                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")))
                    moves.add(new Position(r, c));

                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")))
                    moves.add(new Position(r, c));
            }

//////////////////////////////////////////////////////////////////////////////////
/////////////// 205 degree knight movement////////////
//////////////////////////////////////////////////////////////////////////////////
            r = p.row + 1;
            c = p.col - 2;
            if (r < 8 && c > -1) {
                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")))
                    moves.add(new Position(r, c));

                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")))
                    moves.add(new Position(r, c));
            }
// ////////////////////////////////////////////////////////////////////////////////
/////////////// 335 degree knight movement////////////
//////////////////////////////////////////////////////////////////////////////////
            r = p.row + 1;
            c = p.col + 2;
            if (r < 8 && c < 8) {
                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")))
                    moves.add(new Position(r, c));

                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")))
                    moves.add(new Position(r, c));
            }
            // ////////////////////////////////////////////////////////////////////////////////
/////////////// 25 degree knight movement////////////
//////////////////////////////////////////////////////////////////////////////////
            r = p.row - 1;
            c = p.col + 2;
            if (r > -1 && c < 8) {
                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")))
                    moves.add(new Position(r, c));

                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")))
                    moves.add(new Position(r, c));
            }
            // ////////////////////////////////////////////////////////////////////////////////
/////////////// 50 degree knight movement////////////
//////////////////////////////////////////////////////////////////////////////////
            r = p.row - 2;
            c = p.col + 1;
            if (r > -1 && c < 8) {
                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")))
                    moves.add(new Position(r, c));

                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")))
                    moves.add(new Position(r, c));
            }
            // ////////////////////////////////////////////////////////////////////////////////
/////////////// 130 degree knight movement////////////
//////////////////////////////////////////////////////////////////////////////////
            r = p.row - 2;
            c = p.col - 1;
            if (r > -1 && c > -1) {
                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")))
                    moves.add(new Position(r, c));

                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")))
                    moves.add(new Position(r, c));
            }
            // ////////////////////////////////////////////////////////////////////////////////
/////////////// 155 degree knight movement////////////
//////////////////////////////////////////////////////////////////////////////////
            r = p.row - 1;
            c = p.col - 2;
            if (r > -1 && c > -1) {
                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")))
                    moves.add(new Position(r, c));

                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")))
                    moves.add(new Position(r, c));
            }

        }

        //////////////////////////////////////////////////////
        //////////////RULES APPLY FOR King//////////////
        //////////////////////////////////////////////////////

        else if (p.name.endsWith("k")) {

            ////////////////////////////////////////////////////////////////////////////////
///////////// 45 degree king movement////////////
////////////////////////////////////////////////////////////////////////////////
            int r = p.row - 1;
            int c = p.col + 1;
            if (r > -1 && c < 8) {
                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")))
                    moves.add(new Position(r, c));

                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")))
                    moves.add(new Position(r, c));
            }


////////////////////////////////////////////////////////////////////////////////
///////////// 90 degree king movement////////////
////////////////////////////////////////////////////////////////////////////////
            r = p.row - 1;
            c = p.col;
            if (r > -1) {
                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")))
                    moves.add(new Position(r, c));

                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")))
                    moves.add(new Position(r, c));
            }

////////////////////////////////////////////////////////////////////////////////
///////////// 135 degree king movement////////////
////////////////////////////////////////////////////////////////////////////////
            r = p.row - 1;
            c = p.col - 1;
            if (r > -1 && c > -1) {
                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")))
                    moves.add(new Position(r, c));

                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")))
                    moves.add(new Position(r, c));
            }


////////////////////////////////////////////////////////////////////////////////
///////////// 180 degree king movement////////////
////////////////////////////////////////////////////////////////////////////////
            r = p.row;
            c = p.col - 1;
            if (c > -1) {
                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")))
                    moves.add(new Position(r, c));

                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")))
                    moves.add(new Position(r, c));
            }


////////////////////////////////////////////////////////////////////////////////
///////////// 215 degree king movement////////////
////////////////////////////////////////////////////////////////////////////////
            r = p.row + 1;
            c = p.col - 1;
            if (r < 8 && c > -1) {
                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")))
                    moves.add(new Position(r, c));

                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")))
                    moves.add(new Position(r, c));
            }

////////////////////////////////////////////////////////////////////////////////
///////////// 270 degree king movement////////////
////////////////////////////////////////////////////////////////////////////////
            r = p.row + 1;
            c = p.col;
            if (r < 8) {
                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")))
                    moves.add(new Position(r, c));

                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")))
                    moves.add(new Position(r, c));
            }

            ////////////////////////////////////////////////////////////////////////////////
///////////// 315 degree king movement////////////
////////////////////////////////////////////////////////////////////////////////
            r = p.row + 1;
            c = p.col + 1;
            if (r < 8 && c < 8) {
                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")))
                    moves.add(new Position(r, c));

                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")))
                    moves.add(new Position(r, c));
            }

////////////////////////////////////////////////////////////////////////////////
///////////// 360 degree king movement////////////
////////////////////////////////////////////////////////////////////////////////
            r = p.row;
            c = p.col + 1;
            if (c < 8) {
                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("w") && p.name.startsWith("b")))
                    moves.add(new Position(r, c));

                if (pieces[r][c] == null || (pieces[r][c] != null && pieces[r][c].name != null && pieces[r][c].name.startsWith("b") && p.name.startsWith("w")))
                    moves.add(new Position(r, c));
            }


        }


        return moves;
    }
}