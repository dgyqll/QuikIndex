package com.etouse.quikindex;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Administrator on 2017/6/16.
 */

public class QuikAdapter extends BaseAdapter {
    private List<LiangShanBean> mDatas;

    private Context context;

    public QuikAdapter(List<LiangShanBean> mDatas, Context context) {
        this.mDatas = mDatas;
        this.context = context;
    }
    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_list, null);
        }

        ViewHolder holder = ViewHolder.getInstance(convertView);
        holder.tvName.setText(mDatas.get(i).getName());
        holder.tvLetter.setText(mDatas.get(i).getLetter());
        holder.tvLetter.setVisibility(View.VISIBLE);
        if (i > 0) {
            if (mDatas.get(i).getLetter().equals(mDatas.get(i - 1).getLetter())) {
                holder.tvLetter.setVisibility(View.GONE);
            }
        }
        return convertView;
    }

    public static class ViewHolder{

        private  TextView tvLetter;
        private  TextView tvName;

        public ViewHolder(View view) {
            tvLetter = (TextView) view.findViewById(R.id.tv_letter);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(this);
        }

        public static ViewHolder getInstance(View view){
            ViewHolder holder = (ViewHolder) view.getTag();
            if (holder == null) {
                holder = new ViewHolder(view);
            }
            return holder;
        }
    }
}
