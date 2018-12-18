package com.example.mementopattern;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;



/**
 * Created by W on 2018/12/18.
 */

public class NoteEditText extends AppCompatEditText {
    public NoteEditText(Context context){
        this(context,null);
    }
    public NoteEditText(Context context, AttributeSet attrs){
        this(context,attrs,0);
    }
    public NoteEditText(Context context,AttributeSet attrs,int defStyleAttr){
        super(context,attrs,defStyleAttr);
    }
    /**
     * 创建备忘录对象，即存储编辑器的指定数据
     */
    public Memoto createMemoto(){
        Memoto noteMemoto = new Memoto();
        //存储文本与光标位置；
        noteMemoto.text = getText().toString();
        noteMemoto.cursor = getSelectionStart();
        return noteMemoto;
    }
    /**
     * 从备忘录中恢复数据
     */
    public void restore(Memoto memoto){
        setText(memoto.text);
        //设置光标位置
        setSelection(memoto.cursor);
    }

}
