package com.allen.loltool.news.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.loltool.R;
import com.allen.loltool.news.bean.NewsBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Allen on 2015/11/24.
 */
public class NewsAdapter extends BaseAdapter {
    private Context mContext;
    private List<NewsBean.ListEntity> listEntities;

    public NewsAdapter(Context mContext, List<NewsBean.ListEntity> listEntities) {
        this.mContext = mContext;
        this.listEntities = listEntities;
    }

    @Override
    public int getCount() {
        return listEntities.size();
    }

    @Override
    public Object getItem(int position) {
        return listEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.fragment_news_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.newsTitle.setText(listEntities.get(position).getTitle());
        viewHolder.newsSummary.setText(listEntities.get(position).getSummary());
        viewHolder.newsPublicationDate.setText(listEntities.get(position).getPublication_date());
//        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.mipmap.news_image_normal)
//                .showImageOnFail(R.mipmap.news_image_normal).cacheInMemory(true)
//                .cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565).build();
//
//        ImageLoader.getInstance()
//                .displayImage(
//                        listEntities.get(position).getImage_url_small(),
//                        viewHolder.newsImg, options);
        Picasso.with(mContext).load(listEntities.get(position)
                .getImage_url_small())
                .error(R.mipmap.news_image_normal)
                .placeholder(R.mipmap.news_image_normal)
                .into(viewHolder.newsImg);
        return convertView;
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'fragment_news_item.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.news_img)
        ImageView newsImg;
        @Bind(R.id.news_title)
        TextView newsTitle;
        @Bind(R.id.news_summary)
        TextView newsSummary;
        @Bind(R.id.news_publication_date)
        TextView newsPublicationDate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
