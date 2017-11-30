package com.newlife.jy.curtaincall.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.newlife.jy.curtaincall.R;
import com.newlife.jy.curtaincall.dataBean.HorrorComicBean;

import java.util.List;

/**
 * @author JY
 *         2017/11/30 2:59
 */

public class HorrorComicAdapter extends BaseQuickAdapter<HorrorComicBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean,BaseViewHolder>{

    public HorrorComicAdapter(int layoutResId, @Nullable List<HorrorComicBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HorrorComicBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean item) {
        helper.setText(R.id.mTvHorrorComicTitle,item.getTitle());
    }
}
