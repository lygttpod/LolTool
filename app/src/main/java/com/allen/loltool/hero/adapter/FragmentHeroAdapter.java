package com.allen.loltool.hero.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.loltool.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Allen on 2015/11/26.
 */
public class FragmentHeroAdapter extends BaseAdapter {
    private Context context;
    private String[] textString = {"英雄资料","召唤师技能","服务器状况"};
    private int[] imageResId = {
            R.mipmap.list_img,
            R.mipmap.list_img,
            R.mipmap.list_img
    };

    public FragmentHeroAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return textString.length;
    }

    @Override
    public Object getItem(int position) {
        return textString[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_hero_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.fragmentHeroItemTextTv.setText(textString[position]);
        viewHolder.fragmentHeroItemImgIv.setImageResource(imageResId[position]);
        return convertView;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'fragment_hero_item.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.fragment_hero_item_img_iv)
        ImageView fragmentHeroItemImgIv;
        @Bind(R.id.fragment_hero_item__text_tv)
        TextView fragmentHeroItemTextTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
