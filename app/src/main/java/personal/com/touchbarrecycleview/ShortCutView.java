package personal.com.touchbarrecycleview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2016/6/20.
 */
public class  ShortCutView extends View
        
{
    String alphabet[] = { "@", "A", "B", "C", "D", "E", "F", "G", "H",
        "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
        "V", "W", "X", "Y", "Z", "#", };

// 字母的颜色
private int defaultColor = Color.RED;
private int selectColor = Color.BLUE;

// 被选中的字符
private int selectedIndex = 0;

        // 画笔--用于绘制右侧字母
        Paint paint = new Paint();

// 选中的字母被改变监听器
private OnTouchLetterChangedListener changedListener;
// 选中的字母被释放监听器
private OnTouchLetterReleasedListener releasedListener;

public ShortCutView(Context context) {
        super(context);
        }

public ShortCutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        }

public ShortCutView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        }

@Override
protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 获取当前View的宽度和高度
        int width = getWidth();
        // 计算单个字符所占高度
        int singleLetter = getHeight() / (alphabet.length);

        // 自上而下逐一绘制字母表中的每个字符
        for (int i = 0; i < alphabet.length; i++) {
        // 若没有没选中时显示默认颜色，若被选中显示指定的高亮色
        if (i != selectedIndex) {
        paint.setColor(defaultColor);
        paint.setAntiAlias(true);
        paint.setTextSize(25);
        } else {
        paint.setTextSize(25);
        paint.setColor(selectColor);
        }

        // 计算第i个字符在屏幕中的位置(x,y)
        float x = width / 2 - paint.measureText(alphabet[i]) / 2;
        float y = singleLetter * (i + 1);

        // 在指定位置绘制指定字符
        canvas.drawText(alphabet[i], x, y, paint);
        // 重置画笔的属性
        paint.reset();
        }
        }

@Override
public boolean dispatchTouchEvent(MotionEvent event) {
        // 被触摸的是字母表中的第几个字符
        int singleLetter = getHeight() / (alphabet.length);
        int currentIndex = (int) (event.getY() / singleLetter);

        int action = event.getAction();
        switch (action) {
        case MotionEvent.ACTION_DOWN:
        // 监听到按下事件后修改被选中字符位置标识，并显示字母表的背景同时利用接口函数修改被选中字符
        if (currentIndex >= 0 && currentIndex < alphabet.length) {
        selectedIndex = currentIndex;
        changedListener
        .onTouchLetterChangedListener(currentIndex);
       // setBackgroundResource(R.drawable.alpha_bg);
        invalidate();
        }
        break;
        case MotionEvent.ACTION_MOVE:
        // 监听到正在滑动中的事件后修改被选中字符位置标识，利用接口函数修改被选中字符
        if (currentIndex >= 0 && currentIndex < alphabet.length) {
        selectedIndex = currentIndex;
        changedListener
        .onTouchLetterChangedListener(currentIndex);
        invalidate();
        }
        break;
        case MotionEvent.ACTION_UP:
        // 监听到弹起事件后，调用相应的监听事件并将字母表的背景设置为没有背景
        releasedListener.onTouchLetterReleasedListener(alphabet[currentIndex]);
        setBackgroundDrawable(null);
        invalidate();
        break;
default:
        break;
        }

        return true;
        }

// 设置字母表中的字符
public void setAlphabet(String[] alphabet) {
        this.alphabet = alphabet;
        }

// 设置字母默认显示的颜色
public void setDefaultColor(int defaultColor) {
        this.defaultColor = defaultColor;
        }

// 设置字母被选中时显示的颜色
public void setSelectColor(int selectColor) {
        this.selectColor = selectColor;
        }

// 设置选中字母已改变监听事件
public void setOnTouchLetterChangedListener(
        OnTouchLetterChangedListener changedListener) {
        this.changedListener = changedListener;
        }

// 设置已释放字母选中监听事件
public void setOnTouchLetterReleasedListener(
        OnTouchLetterReleasedListener releasedListener) {
        this.releasedListener = releasedListener;
        }

public interface OnTouchLetterChangedListener {

    public void onTouchLetterChangedListener(int index);
}

public interface OnTouchLetterReleasedListener {

    public void onTouchLetterReleasedListener(String s);
}

}  