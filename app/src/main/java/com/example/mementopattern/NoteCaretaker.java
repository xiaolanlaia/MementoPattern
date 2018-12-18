package com.example.mementopattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by W on 2018/12/18.
 */

public class NoteCaretaker {
    /**
     * 最大存储数量
     */
    private static final int MAX = 30;
    /**
     * 存储30条记录
     */
    List<Memoto> mMemotos = new ArrayList<Memoto>(MAX);

    int mIndex = 0;
    public void saveMemoto(Memoto memoto){
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
}
