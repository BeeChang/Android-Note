package com.example.androidnote.touchEvent.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintLayout

class SecLayout @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr){


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.e("touchEvent" , "SecLayout dispatchTouchEvent" )
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        Log.e("touchEvent" , "SecLayout onInterceptTouchEvent" )
//        return super.onInterceptTouchEvent(ev)
            return false

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e("touchEvent" , "SecLayout onTouchEvent" )

        return super.onTouchEvent(event)
    }


}