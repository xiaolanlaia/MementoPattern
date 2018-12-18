package com.example.mementopattern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    NoteEditText mNodeEditText;
    TextView mSaveTv;
    ImageView mUndoBtn;
    ImageView mRedoBtn;
    /**
     * 备忘录管理器
     */
    NoteCaretaker mCaretaker = new NoteCaretaker();

    /**
     * 最大存储数量
     */
    private static final int MAX = 30;
    /**
     * 存储30条记录
     */
    List<Memoto>mMemotos = new ArrayList<Memoto>(MAX);

    int mIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化视图
        initViews();
    }
    public void initViews(){
        mNodeEditText = (NoteEditText)findViewById(R.id.note_edittext);
        mUndoBtn = (ImageView)findViewById(R.id.undo_btn);
        mUndoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //返回上一个记录点
                mNodeEditText.restore(mCaretaker.getPrevMemoto());
                makeToast("撤销");
            }
        });
        mRedoBtn = (ImageView)findViewById(R.id.redo_btn);
        mRedoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //恢复状态，恢复到下一个记录点
                mNodeEditText.restore(mCaretaker.getNextMemoto());
                makeToast("重做");
            }
        });
        mSaveTv = (TextView)findViewById(R.id.save_btn);
        mSaveTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCaretaker.saveMemoto(mNodeEditText.createMemoto());
                makeToast("保存笔记");
            }
        });
    }
    private void makeToast(String msgPrex){
        Toast.makeText(this,msgPrex +mNodeEditText.getText() + ",光标位置 : " + mNodeEditText.getSelectionStart(),
                Toast.LENGTH_SHORT).show();
    }
    private void saveMemoto(Memoto memoto){
        if (mMemotos.size() > MAX){
            mMemotos.remove(0);
        }
        mMemotos.add(memoto);
        mIndex = mMemotos.size() - 1;
    }

    /**
     * 获取上一个存档，相当于撤销功能
     * @return
     */
    public Memoto getPrevMemoto(){
        mIndex = mIndex > 0 ? --mIndex : mIndex;
        return mMemotos.get(mIndex);
    }
    /**
     * 获取下一个存档，相当于重做功能
     */
    public Memoto getNextMemoto(){
        mIndex = mIndex < mMemotos.size() - 1 ? ++mIndex : mIndex;
        return mMemotos.get(mIndex);
    }

    /**
     * 为编辑器创建Memoto对象
     */
    private Memoto createMemotoForEditText(){
        Memoto memoto = new Memoto();
        memoto.text = mNodeEditText.getText().toString();
        memoto.cursor = mNodeEditText.getSelectionStart();
        return memoto;
    }
    /**
     * 恢复编辑状态
     */
    private void restoreEditText(Memoto memoto){
        mNodeEditText.setText(memoto.text);
        mNodeEditText.setSelection(memoto.cursor);
    }
}
