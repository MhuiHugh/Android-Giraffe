package com.hu.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class HorizontalScrollViewAdapter {
    private final String TAG = this.getClass().getSimpleName();
    private int currentIndex = -1;
    private Context mContext;
    private LayoutInflater mInflater;
    private List<Integer> mDatas;


    public HorizontalScrollViewAdapter(Context context, List<Integer> mDatas) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
    }

    public int getCount() {
        return mDatas.size();
    }

    public Object getItem(int position) {
        return mDatas.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(
                    R.layout.item_horizontal_scrollindicator_view, parent, false);
            viewHolder.view = convertView
                    .findViewById(R.id.collage_item_color);
            viewHolder.indicator = convertView
                    .findViewById(R.id.collage_item_colorselect);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.view.setBackgroundColor(mDatas.get(position));
        viewHolder.indicator.setBackgroundColor(mContext.getResources().getColor(R.color.transparency));
        if (position == currentIndex) {
            viewHolder.indicator.setBackgroundColor(mContext.getResources().getColor(R.color.bulue));
        }
        return convertView;
    }

    public void setCurrentIndex(int index) {
        currentIndex = index;
    }

    private class ViewHolder {
        View view, indicator;
    }

}
