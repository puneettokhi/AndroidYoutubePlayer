package com.example.puneettokhi.lab3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by puneettokhi on 5/11/18.
 */

// special class for recyclerview which is called when an items is clicked in recycler view
public class RecyclerViewOnClickListener implements RecyclerView.OnItemTouchListener {


    private OnItemClickListener itemListener;
    private GestureDetector gestureDetector;  // detects user gesture

    // implementing an interface with one method
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    // constructor with 2 arguments
    public RecyclerViewOnClickListener(Context context, OnItemClickListener listener) {
        itemListener = listener;

        // initializing new gesture detector
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;      // returns true if its tapped
            }
        });
    }

    // this method returns a booleans depending on a motion event in view
    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && itemListener != null && gestureDetector.onTouchEvent(e)) {
            itemListener.onItemClick(childView, view.getChildAdapterPosition(childView));
            return true;
        }
        return false;
    }

    // required method for RecyclerView.OnItemTouchListener interface
    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
    }

    // this is required method for RecyclerView.OnItemTouchListener interface
    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

}