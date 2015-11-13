package com.allen.loltool.free_hero.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.loltool.R;
import com.allen.loltool.common.UrlAddress;
import com.allen.loltool.free_hero.bean.FreeHeroBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hardy on 2015/11/13.
 */
public class FreeHeroAdapter extends BaseAdapter {
    private Context mContext;

    private FreeHeroBean freeHeroBean;

    public FreeHeroAdapter(Context mContext, FreeHeroBean freeHeroBean) {
        this.mContext = mContext;
        this.freeHeroBean = freeHeroBean;
    }

    @Override
    public int getCount() {
        return freeHeroBean.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return freeHeroBean.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.free_hero_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String name = freeHeroBean.getData().get(position).getName();
        String name1 = name.substring(0, name.indexOf(" "));
        String name2 = name.substring(name.indexOf(" "), name.length());

        viewHolder.heroNameTV.setText(name1 + "\n" + name2);

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.default_l)
                .showImageOnFail(R.mipmap.default_l).cacheInMemory(true)
                .cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565).build();

        ImageLoader.getInstance()
                .displayImage(
                        UrlAddress.base_url + freeHeroBean.getData().get(position).getImg(),
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
