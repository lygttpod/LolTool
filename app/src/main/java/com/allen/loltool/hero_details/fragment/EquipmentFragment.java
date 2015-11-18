package com.allen.loltool.hero_details.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.allen.loltool.R;
import com.allen.loltool.hero_details.adapter.EquipmentListViewAdapter;
import com.allen.loltool.hero_details.bean.HeroDetailsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Allen on 2015/11/18.
 */
public class EquipmentFragment extends Fragment {

    @Bind(R.id.equipment_listview)
    ListView equipmentListview;
    private HeroDetailsBean heroDetailsBean;
    private static final String ARG = "arg";
    private List<HeroDetailsBean.PlayListEntity> playListEntities;
    private EquipmentListViewAdapter equipmentListViewAdapter;

    public static Fragment newInstance(HeroDetailsBean arg) {
        EquipmentFragment fragment = new EquipmentFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG, arg);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_equipment, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        heroDetailsBean = (HeroDetailsBean) getArguments().getSerializable(ARG);
        playListEntities = new ArrayList<>();
        playListEntities = heroDetailsBean.getPlay_list();
        equipmentListViewAdapter = new EquipmentListViewAdapter(getActivity(), playListEntities);
        equipmentListview.setAdapter(equipmentListViewAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
