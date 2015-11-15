package com.allen.loltool.summoner.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.loltool.R;
import com.allen.loltool.all_hero.bean.AllHeroBean;
import com.allen.loltool.common.UrlAddress;
import com.allen.loltool.summoner.bean.SummonerBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by allen on 2015/11/13.
 */
public class SummonerAdapter extends BaseAdapter {
    private Context mContext;


    private List<SummonerBean.DataEntity> dataEntities;

    public SummonerAdapter(Context mContext, List<SummonerBean.DataEntity> dataEntities) {
        this.mContext = mContext;
        this.dataEntities = dataEntities;
    }

    @Override
    public int getCount() {
        return dataEntities.size();
    }

    @Override
    public Object getItem(int position) {
        return dataEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.summoner_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String name = dataEntities.get(position).getName();

        viewHolder.heroNameTV.setText(name);

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.default_l)
                .showImageOnFail(R.mipmap.default_l).cacheInMemory(true)
                .cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565).build();

        ImageLoader.getInstance()
                .displayImage(
                        UrlAddress.base_url + dataEntities.get(position).getImg(),
                        viewHolder.heroImgIV, options);
        return convertView;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'free_hero_item.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.hero_img_IV)
        ImageView heroImgIV;
        @Bind(R.id.hero_name_TV)
        TextView heroNameTV;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
