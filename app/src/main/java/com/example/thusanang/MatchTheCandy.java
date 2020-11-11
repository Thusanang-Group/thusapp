package com.example.thusanang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.GridLayout;
import android.widget.ImageView;

public class MatchTheCandy extends AppCompatActivity {

    int[] candies = {
            R.drawable.candy,
            R.drawable.purplecandy,
            R.drawable.yellowcandy,
            R.drawable.redcandy,
            R.drawable.bluecandy,
            R.drawable.administrator
    };

    int widthOfBlock, noOfBlock = 8, widthOfScreen ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_the_candy);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        widthOfScreen = displayMetrics.widthPixels;
        int heightOfScreen = displayMetrics.heightPixels;
        widthOfBlock = widthOfScreen / noOfBlock;
        createBlock();

    }
    private void createBlock(){
        GridLayout gridLayout = findViewById(R.id.board);
        gridLayout.setColumnCount(noOfBlock);
        gridLayout.setRowCount(noOfBlock);


        //dealing with squares
        gridLayout.getLayoutParams().width = widthOfScreen;
        gridLayout.getLayoutParams().height = widthOfScreen;

        for(int i = 0; i < noOfBlock * noOfBlock; i++){
            ImageView  imageView = new ImageView(this);
            imageView.setId(i);
            imageView.setLayoutParams(new android.view.ViewGroup.LayoutParams(widthOfBlock, widthOfBlock));
            imageView.setMaxHeight(widthOfBlock);
            imageView.setMaxWidth(widthOfBlock);

            int randomCandy = (int) Math.floor(Math.random() * candies.length);
            imageView.setImageResource(candies[randomCandy]);
            gridLayout.addView(imageView);

        }

    }
}