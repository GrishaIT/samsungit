package com.example.samsunghomework;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int WIDTH = 10;
    private int HEIGHT = 10;
    int count = 10;
    Button[][] cells;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cells);
        makeCells();

        generate();

    }

    void generate() {

        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++)
                if (Math.random() >= 0.5) {
                    cells[i][j].setBackgroundColor(BLACK);
                    cells[0][0].setText(count + "");


                }
        Task.showMessage(this, "ЗАКРАСЬТЕ ВСЕ КЛЕТКИ ОДНИМ ЦВЕТОМ ЗА 10 ХОДОВ");
    }


    @Override
    public void onClick(View v) {
        Button tappedCell = (Button) v;


        int tappedX = getX(tappedCell);
        int tappedY = getY(tappedCell);
        int color = ((ColorDrawable) cells[tappedY][tappedX].getBackground()).getColor();
        if (color == BLACK) {


            for (int x = 0; x < WIDTH; x++) {
                cells[tappedY][x].setBackgroundColor(WHITE);
            }
            for (int y = 0; y < WIDTH; y++) {
                cells[y][tappedX].setBackgroundColor(WHITE);
            }
        }

        if (color == WHITE) {


            for (int x = 0; x < WIDTH; x++) {
                cells[tappedY][x].setBackgroundColor(BLACK);
            }
            for (int y = 0; y < WIDTH; y++) {
                cells[y][tappedX].setBackgroundColor(BLACK);
            }
        }
        count--;
        cells[0][0].setText(count + "");

        if (count == 0) {

            System.exit(1);
        }
        int check = 0;
        int check1;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int color1 = ((ColorDrawable) cells[i][j].getBackground()).getColor();
                if ((i != 0) && (j != 0)) {
                    check1 = ((ColorDrawable) cells[i - 1][j - 1].getBackground()).getColor();
                } else check1 = ((ColorDrawable) cells[i][j].getBackground()).getColor();


                if (check1 != color1) {

                    break;


                } else check++;
            }
        }

        if (check == 100) {
            Task.showMessage(this, "ПОЗДРАВЛЯЮ, ВЫ ПОБЕДИЛИ");


        }
    }





    /*
     * NOT FOR THE BEGINNERS
     * ==================================================
     */

    int getX(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[1]);
    }

    int getY(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[0]);
    }

    void makeCells() {
        cells = new Button[HEIGHT][WIDTH];
        GridLayout cellsLayout = (GridLayout) findViewById(R.id.CellsLayout);
        cellsLayout.removeAllViews();
        cellsLayout.setColumnCount(HEIGHT);
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                cells[i][j] = (Button) inflater.inflate(R.layout.cell, cellsLayout, false);
                cells[i][j].setOnClickListener(this);

                cells[i][j].setTag(i + "," + j);
                cellsLayout.addView(cells[i][j]);
            }
    }
}