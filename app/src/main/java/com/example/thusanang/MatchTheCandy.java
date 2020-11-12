package com.example.thusanang;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MatchTheCandy extends AppCompatActivity {

    int[] candies = {
            R.drawable.candy,
            R.drawable.purplecandy,
            R.drawable.yellowcandy,
            R.drawable.redcandy,
            R.drawable.bluecandy,
            R.drawable.administrator
    };

    int widthOfBlock, noOfBlock = 4, widthOfScreen ;
    ArrayList <ImageView> candy = new ArrayList<>();
    int candyToBeDragged, candyToBeReplaced;


    @SuppressLint("ClickableViewAccessibility")
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
        for(final ImageView imageView : candy){
            imageView.setOnTouchListener(new OnSwipeListener(this){
                @Override
                void onSwipeLeft() {
                    super.onSwipeLeft();
                    candyToBeDragged = imageView.getId();
                    candyToBeReplaced = candyToBeDragged -1;
                    Interchange();

                }

                @Override
                void onSwipeRight() {
                    super.onSwipeRight();
                    candyToBeDragged = imageView.getId();
                    candyToBeReplaced = candyToBeDragged +1;
                    Interchange();
                }

                @Override
                void onSwipeTop() {
                    super.onSwipeTop();
                    candyToBeDragged = imageView.getId();
                    candyToBeReplaced = candyToBeDragged - noOfBlock;
                    Interchange();
                }

                @Override
                void onSwipeBottom() {
                    super.onSwipeBottom();
                    candyToBeDragged = imageView.getId();
                    candyToBeReplaced = candyToBeDragged + noOfBlock;
                    Interchange();
                }
            });
        }

    }
    private void Interchange(){
        int background = (int) candy.get(candyToBeReplaced).getTag();
        int background1 = (int) candy.get(candyToBeDragged).getTag();
        candy.get(candyToBeDragged).setImageResource(background);
        candy.get(candyToBeReplaced).setImageResource(background1);
        candy.get(candyToBeDragged).setTag(background);
        candy.get(candyToBeReplaced).setTag(background1);

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
            imageView.setTag(candies[randomCandy]);
            candy.add(imageView);
            gridLayout.addView(imageView);

        }

    }
}