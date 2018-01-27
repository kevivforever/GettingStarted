package in.finder.gettingstarted.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import in.finder.gettingstarted.R;

/**
 * Created by keviv on 12/10/2017.
 */

public class MyCustomView extends View {

    Paint mPaint;
    Rect mRect;
    static int mSquareColor;
    static int mPadding = 0;

    public MyCustomView(Context context) {
        super(context);
        init(null);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRect = new Rect();
        if(set == null){
            return;
        }

        TypedArray ta = getContext().obtainStyledAttributes(set, R.styleable.MyCustomView);
        mSquareColor = ta.getColor(R.styleable.MyCustomView_square_color, Color.GREEN);
        mPaint.setColor(mSquareColor);
        ta.recycle();
    }

    public void swapColor(){
        mPaint.setColor(mPaint.getColor()==mSquareColor?Color.RED:mSquareColor);
        postInvalidate();
    }

    public void customPaddingUp(int padding){
        mPadding = mPadding + padding;
        postInvalidate();
    }

    public void customPaddingDown(int padding){
        mPadding = mPadding -  padding;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mRect.left = mPadding;
        mRect.right = getWidth() - mPadding;
        mRect.top = mPadding;
        mRect.bottom = getHeight() - mPadding;

        canvas.drawRect(mRect, mPaint);
    }

}
