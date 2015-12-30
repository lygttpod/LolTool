package com.allen.loltool.hero.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.allen.loltool.R;
import com.allen.loltool.base.BaseFragment;
import com.allen.loltool.common.UrlAddress;
import com.allen.loltool.hero.adapter.FragmentHeroAdapter;
import com.allen.loltool.hero.bean.ADBean;
import com.allen.loltool.hero.bean.ImageBean;
import com.allen.loltool.hero_data.activity.HeroDataActivity;
import com.allen.loltool.server_list.activity.ServerListActivity;
import com.allen.loltool.summoner.activity.SummonerActivity;
import com.allen.loltool.utils.JsonUtils;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Transformers.AccordionTransformer;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Allen on 2015/11/26.
 */
public class HeroFragment extends BaseFragment {

    @Bind(R.id.fragment_hero_listview)
    ListView fragmentHeroListview;
    @Bind(R.id.slider)
    SliderLayout slider;

    private FragmentHeroAdapter fragmentHeroAdapter;

    private AsyncHttpClient client;

    private String[] imgurls = {
            "http://ossweb-img.qq.com/upload/qqtalk/news/201512/291041398094322_282.jpg",
            "http://ossweb-img.qq.com/upload/qqtalk/news/201512/28170749800758_282.jpg",
            "http://ossweb-img.qq.com/upload/qqtalk/news/201512/151241143579294_480.jpg"};

    private List<ImageBean> ad_imgurl = new ArrayList<>();

    public static HeroFragment newInstance() {
        HeroFragment heroFragment = new HeroFragment();
        return heroFragment;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            showAd(ad_imgurl);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hero, container, false);
        ButterKnife.bind(this, view);
//        showAdvertise(imgurls);

        init();
        sendRequest();
        return view;
    }


    private void init() {
        fragmentHeroAdapter = new FragmentHeroAdapter(getActivity());
        fragmentHeroListview.setAdapter(fragmentHeroAdapter);
        fragmentHeroListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent.setClass(getActivity(), HeroDataActivity.class);
                        break;
                    case 1:
                        intent.setClass(getActivity(), SummonerActivity.class);
                        break;
                    case 2:
                        intent.setClass(getActivity(), ServerListActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });
    }

    private void sendRequest() {
        client = new AsyncHttpClient();
        client.get(getActivity(), UrlAddress.AD_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                ADBean adBean = JsonUtils.getObject(new String(responseBody), ADBean.class);
                for (ADBean.ListEntity listEntity : adBean.getList()) {
                    ImageBean imageBean = new ImageBean(listEntity.getTitle(), listEntity.getImage_url_big());
                    ad_imgurl.add(imageBean);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

            @Override
            public void onFinish() {
                super.onFinish();
                handler.sendEmptyMessage(0);
            }
        });
    }

    private void showAd(List<ImageBean> imageBeans) {
        if (imageBeans.size() > 0) {
            slider.setVisibility(View.VISIBLE);
            for (int i = 0; i < imageBeans.size(); i++) {
                TextSliderView textSliderView = new TextSliderView(getActivity());
//          textSliderView.image(R.mipmap.ani).description("安妮");
                textSliderView
                        .description(imageBeans.get(i).getTitle())
                        .image(imageBeans.get(i).getImg());

                slider.addSlider(textSliderView);
                slider.setCustomAnimation(new DescriptionAnimation());
                slider.setPagerTransformer(false, new AccordionTransformer());
                // slider.setPresetTransformer(SliderLayout.Transformer.RotateUp);
                slider.setDuration(5000);
            }
        }


    }

    @Override
    protected void lazyLoad() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onStop() {
        slider.stopAutoCycle();
        super.onStop();
    }
}
