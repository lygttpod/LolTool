package com.allen.loltool.server_list.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.loltool.R;
import com.allen.loltool.server_list.bean.ServerListBean;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by allen on 2015/11/12.
 */
public class ServerListAdapter extends BaseExpandableListAdapter {
    private Context mContext;

    private List<ServerListBean.DataEntity> dataEntities;


    public ServerListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public ServerListAdapter(Context mContext, List<ServerListBean.DataEntity> dataEntities) {
        this.mContext = mContext;
        this.dataEntities = dataEntities;
    }


    @Override
    public int getGroupCount() {
        return dataEntities.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int childrenCount = 0;
        childrenCount = dataEntities.get(groupPosition).getList().size();
        return childrenCount;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.server_list_groupview_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.serverListNameTV.setText(dataEntities.get(groupPosition).getName());
        if (isExpanded) {
            viewHolder.serverListNameRightimgIV.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.qtl_submenu_down));
        } else {
            viewHolder.serverListNameRightimgIV.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.qtl_submenu));
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder_children viewHolder_children = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.server_list_childrenview_item, null);
            viewHolder_children = new ViewHolder_children(convertView);
            convertView.setTag(viewHolder_children);
        } else {
            viewHolder_children = (ViewHolder_children) convertView.getTag();
        }

        viewHolder_children.serverListChildrenNameTV.setText(dataEntities.get(groupPosition).getList().get(childPosition).getSname());
        viewHolder_children.serverListChildrenStatusIV.setText(dataEntities.get(groupPosition).getList().get(childPosition).getStatus());
        setColor_wt(dataEntities, groupPosition, childPosition, viewHolder_children);


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    static class ViewHolder_children {
        @Bind(R.id.server_list_children_name_TV)
        TextView serverListChildrenNameTV;
        @Bind(R.id.server_list_children_status_IV)
        TextView serverListChildrenStatusIV;

        ViewHolder_children(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'server_list_groupview_item.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.server_list_name_TV)
        TextView serverListNameTV;
        @Bind(R.id.server_list_name_rightimg_IV)
        ImageView serverListNameRightimgIV;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private void setColor_wt(List<ServerListBean.DataEntity> dataEntities, int groupPosition, int childPosition, ViewHolder_children viewHolder_children) {
        switch (dataEntities.get(groupPosition).getList().get(childPosition).getStatus()) {
            case "正常":
                viewHolder_children.serverListChildrenStatusIV.setTextColor(mContext.getResources().getColor(R.color.green));
                break;
            case "拥挤":
                viewHolder_children.serverListChildrenStatusIV.setTextColor(mContext.getResources().getColor(R.color.yellow));
                break;
            case "满载":
                viewHolder_children.serverListChildrenStatusIV.setTextColor(mContext.getResources().getColor(R.color.red));
                break;
            case "维护":
                viewHolder_children.serverListChildrenStatusIV.setTextColor(mContext.getResources().getColor(R.color.gray));
                break;
        }

    }


}
