package com.bwie.day0616_spannablestring;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Spanned.SPAN_EXCLUSIVE_EXCLUSIVE， 这是在 setSpan 时需要指定的 flag，
 * 它的意义我试了很久也没试出来，睡个觉，今天早上才突然有点想法，试之，
 * 果然。它是用来标识在 Span 范围内的文本前后输入新的字符时是否把它们也应用这个效果。
 * 分别有 Spanned.SPAN_EXCLUSIVE_EXCLUSIVE(前后都不包括)、
 * Spanned.SPAN_INCLUSIVE_EXCLUSIVE(前面包括，后面不包括)、
 * Spanned.SPAN_EXCLUSIVE_INCLUSIVE(前面不包括，后面包括)、
 * Spanned.SPAN_INCLUSIVE_INCLUSIVE(前后都包括)。看个截图就更明白了
 */

public class MainActivity extends Activity {

    public static int[] resIDs = new int[] { R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    public static String[] smileArray = { "[干嘛]", "[鼓掌]", "[握手]", "[发疯]"
    };

    private TextView tv;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);

        editText = (EditText) findViewById(R.id.et);

//        SpannableString spannableString = new SpannableString("你好，[干嘛]吃饭了吗[握手][握手][握手][握手][握手][握手]");

        String content = "你好，[干嘛]吃饭了吗[握手][握手][握手][握手][握手][握手]" ;

        tv.setText(toImageSpan(this,content,resIDs,smileArray));

//        //        背景色 BackgroundColorSpan
//        spannableString.setSpan(new BackgroundColorSpan(Color.BLUE),0,5, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//
        //        ForegroundColorSpan 前景色
//        spannableString.setSpan(new ForegroundColorSpan(Color.RED), 5, 9, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//
////        下划线
//        spannableString.setSpan(new UnderlineSpan(),0,5,Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//
//        //显示图片
//        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
//
//        drawable.setBounds(0,0,20,20);
//
//        spannableString.setSpan(new ImageSpan(drawable),0,1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//
////                加粗 倾斜
//        spannableString.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),3,5,Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//
//        //下标
//        spannableString.setSpan(new SubscriptSpan(),3,5,Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//
//        //上标
//        spannableString.setSpan(new SuperscriptSpan(),0,2,Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//
////        超文本链接 （打开网页）
//        spannableString.setSpan(new URLSpan("http://www.baidu.com"),5,9,Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//

//
////        设置TextView可以点击
//        tv.setMovementMethod(new LinkMovementMethod());

//        //ClickableSpan点击事件
//        ClickableSpan span = new ClickableSpan() {
//            @Override
//            public void onClick(View widget) {
//
//                Toast.makeText(MainActivity.this, "---", Toast.LENGTH_SHORT).show();
//
//                System.out.println("widget = " + widget);
//
//            }
//
//            @Override
//            public void updateDrawState(TextPaint ds) {
//                super.updateDrawState(ds);
//
//                System.out.println("ds = " + ds);
//
//                ds.setColor(Color.YELLOW);
//
//                ds.setUnderlineText(false);
//
//
//            }
//        };
//        spannableString.setSpan(span, 0, 5, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//
//        tv.setText(spannableString);

//------------------------------------------------------------------------------------------------

        /*SpannableString spannableString2 = new SpannableString("1234567890");

        spannableString2.setSpan(new BackgroundColorSpan(Color.RED), 0, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        editText.setText(spannableString2);*/

    }

    public static SpannableString toImageSpan(Context context, String content, int[] emoImg, String[] emoText) {
        SpannableString ss = new SpannableString(content);
        for (int i = 0; i < emoText.length; i++) {
            int startPos = 0;
            String rep = emoText[i];
            int fromPos = 0;
            while ((startPos = content.indexOf(rep, fromPos)) != -1) {
                fromPos = startPos + rep.length();
                Bitmap bit = BitmapFactory.decodeResource(context.getResources(),emoImg[i]);

                BitmapDrawable bitmapDrawable = new BitmapDrawable(bit);
                bitmapDrawable.setBounds(0, 0, 50,50);
                ImageSpan span = new ImageSpan(bitmapDrawable);
                ss.setSpan(span, startPos, fromPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return ss;
    }

}
