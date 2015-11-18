package com.allen.loltool.hero_details.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.loltool.R;
import com.allen.loltool.hero_details.bean.HeroDetailsBean;
import com.allen.loltool.utils.ToastUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Allen on 2015/11/18.
 */
public class EquipmentListViewAdapter extends BaseAdapter {
    private List<HeroDetailsBean.PlayListEntity> playListEntities;
    private Context context;

    public EquipmentListViewAdapter(Context context, List<HeroDetailsBean.PlayListEntity> playListEntities) {
        this.context = context;
        this.playListEntities = playListEntities;
    }

    @Override
    public int getCount() {
        return playListEntities.size();
    }

    @Override
    public Object getItem(int position) {
        return playListEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.equipment_list_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        String smooth_inborn = playListEntities.get(position).getSmooth_inborn();
        String inborn[] = smooth_inborn.split(" ");

        String smooth_rune = playListEntities.get(position).getSmooth_rune();
        String jinghua = smooth_rune.substring(0, smooth_rune.indexOf("印记"));
        String yinji = smooth_rune.substring(smooth_rune.indexOf("印记"), smooth_rune.indexOf("符印"));
        String fuyin = smooth_rune.substring(smooth_rune.indexOf("符印"), smooth_rune.indexOf("雕纹"));
        String diaowen = smooth_rune.substring(smooth_rune.indexOf("雕纹"));

        viewHolder.nameTV.setText(playListEntities.get(position).getName());
        viewHolder.insertDateTV.setText(playListEntities.get(position).getInsert_date());
        viewHolder.smoothAuthorTV.setText(playListEntities.get(position).getSmooth_author());

        viewHolder.smoothInborn1.setText(inborn[0]);
        viewHolder.smoothInborn2.setText(inborn[1]);
        viewHolder.smoothInborn3.setText(inborn[2]);

        viewHolder.jinghuaTV.setText(jinghua.toString().trim());
        viewHolder.yinjiTV.setText(yinji.toString().trim());
        viewHolder.fuyinTV.setText(fuyin.toString().trim());
        viewHolder.diaowenTV.setText(diaowen.toString().trim());


        return convertView;
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'equipment_list_item.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.imageView)
        ImageView imageView;
        @Bind(R.id.name_TV)
        TextView nameTV;
        @Bind(R.id.insert_date_TV)
        TextView insertDateTV;
        @Bind(R.id.smooth_author_TV)
        TextView smoothAuthorTV;
        @Bind(R.id.smooth_inborn1)
        TextView smoothInborn1;
        @Bind(R.id.smooth_inborn2)
        TextView smoothInborn2;
        @Bind(R.id.smooth_inborn3)
        TextView smoothInborn3;
        @Bind(R.id.jinghua_TV)
        TextView jinghuaTV;
        @Bind(R.id.yinji_TV)
        TextView yinjiTV;
        @Bind(R.id.fuyin_TV)
        TextView fuyinTV;
        @Bind(R.id.diaowen_TV)
        TextView diaowenTV;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
