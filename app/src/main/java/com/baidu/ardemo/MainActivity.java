/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.ardemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    private String[] mArName;
    private String[] mArDesciption;
    private ListView mListView;
    private ArrayAdapter mAdapter;
    private List<ListItemBean> mListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        Resources res = getResources();
        mArName = res.getStringArray(R.array.ar_name);
        mArDesciption = res.getStringArray(R.array.ar_description);
    }

    private void initView() {
        mListData = getListItemData();

        mListView = (ListView) findViewById(R.id.demo_list);
        mListView.addFooterView(new ViewStub(this));

        mAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, mArName);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, IntroActivity.class);
                Bundle bundle = new Bundle();
                ListItemBean listItemBean = mListData.get(position);
                bundle.putString("ar_key", listItemBean.getARKey());
                bundle.putInt("ar_type", listItemBean.getARType());
                bundle.putString("name", listItemBean.getName());
                bundle.putString("description", listItemBean.getDescription());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private List<ListItemBean> getListItemData() {
        List<ListItemBean> list = new ArrayList<>();
        // SLAM AR 小熊
        list.add(new ListItemBean(5, "替换AR Key", mArName[0], mArDesciption[0]));
        // 本地识图
        list.add(new ListItemBean(6, "", mArName[1], mArDesciption[1]));
        // 云端识图
        list.add(new ListItemBean(7, "", mArName[2], mArDesciption[2]));
        // Track AR城市地图case
        list.add(new ListItemBean(0, "替换AR Key", mArName[3], mArDesciption[3]));
        // IMU AR 请财神case
        list.add(new ListItemBean(0, "替换AR Key", mArName[4], mArDesciption[4]));
        // 语音
        list.add(new ListItemBean(0, "替换AR Key", mArName[5], mArDesciption[5]));
        // TTS
        list.add(new ListItemBean(0, "替换AR Key", mArName[6], mArDesciption[6]));

        return list;
    }

    private class ListItemBean {
        String mARKey;
        int mARType;
        String mName;
        String mDescription;

        public ListItemBean(int arType, String arKey, String name, String description) {
            this.mARType = arType;
            this.mARKey = arKey;
            this.mName = name;
            this.mDescription = description;
        }

        public String getARKey() {
            return mARKey;
        }

        public int getARType() {
            return mARType;
        }
        public String getName() {
            return mName;
        }

        public String getDescription() {
            return mDescription;
        }
    }
}
