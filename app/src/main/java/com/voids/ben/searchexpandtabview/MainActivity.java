package com.voids.ben.searchexpandtabview;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.voids.ben.searchexpandtabview.adapter.StaggeredAdapter;
import com.voids.ben.searchexpandtabview.expandtabview.DividerItemDecoration;
import com.voids.ben.searchexpandtabview.expandtabview.SearchTabLeft;
import com.voids.ben.searchexpandtabview.expandtabview.SearchTabMiddle;
import com.voids.ben.searchexpandtabview.expandtabview.SearchTabRight;
import com.voids.ben.searchexpandtabview.expandtabview.SearchTabView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    @Bind(android.R.id.list)
    RecyclerView rvSomething;
    @Bind(R.id.swipe_refresh_widget)
    SwipeRefreshLayout swipeRefreshWidget;
    @Bind(R.id.search_title)
    EditText searchTitle;
    @Bind(R.id.btn_search)
    Button btnSearch;
    @Bind(R.id.expandtab_view)
    SearchTabView expandtabView;

    private StaggeredAdapter mStaggeredAdapter;

    private ArrayList<View> mViewArray = new ArrayList<View>();
    private SearchTabLeft viewLeft;
    private SearchTabMiddle viewMiddle;
    private SearchTabRight viewRight;
    private String SearchContent;
    private List<String> mPics;


    private String longitude;
    private String latitude;
    private String districtcode;
    private String rank;
    private String specialty;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }

    private void initData() {
        // tab设置
        mViewArray.add(viewLeft);
        mViewArray.add(viewMiddle);
        mViewArray.add(viewRight);

        mPics=new ArrayList<String>();
        for (int i = 0; i < 15; i++) {
            if (i == 2 || i == 4 || i == 5 || i == 8 || i == 10) {
                mPics.add("");
            } else {
                mPics.add("https://www.eff.org/sites/default/files/chrome150_0.jpg");
            }
        }
        mStaggeredAdapter = new StaggeredAdapter(this, mPics);
        rvSomething.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        rvSomething.setAdapter(mStaggeredAdapter);
        // 设置item动画
        rvSomething.setItemAnimator(new DefaultItemAnimator());
        rvSomething.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
        rvSomething.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.HORIZONTAL_LIST));
        initEvent();

        ArrayList<String> mTextArray = new ArrayList<String>();
        mTextArray.add(getResources().getString(R.string.region_serach_title));
        mTextArray.add(getResources().getString(R.string.rank_serach_title));
        mTextArray.add(getResources().getString(R.string.specialty_serach_title));
        expandtabView.setValue(mTextArray, mViewArray);
        expandtabView.setTitle(mTextArray.get(0), 0);
        expandtabView.setTitle(mTextArray.get(1), 1);
        expandtabView.setTitle(mTextArray.get(2), 2);

    }

    private void initView() {

        viewLeft = new SearchTabLeft(this);
        viewMiddle = new SearchTabMiddle(this);
        viewRight = new SearchTabRight(this);
        swipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshWidget.setRefreshing(true);
                Log.d("Swipe", "Refreshing Number");
                (new Handler()).postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        swipeRefreshWidget.setRefreshing(false);
                        Toast.makeText(MainActivity.this, "刷新成功", Toast.LENGTH_SHORT).show();
                    }
                }, 3000);
            }
        });


    }


    private void initEvent()
    {
        mStaggeredAdapter.setOnItemClickLitener(new StaggeredAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this,
                        position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this,
                        position + " long click", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * @Description: expandTab 监听
     * @Author:胡帅
     * @Since: 2016年4月13日下午2:51:20
     */
    private void initListener() {

        viewLeft.setOnSelectListener(new SearchTabLeft.OnSelectListener() {

            @Override
            public void getValue(String itemCode, String showText) {
                districtcode = itemCode;
                onRefresh(viewLeft, showText);
            }
        });

        viewMiddle.setOnSelectListener(new SearchTabMiddle.OnSelectListener() {

            @Override
            public void getValue(String itemLvTwocode, String showText) {

                rank = itemLvTwocode;
                onRefresh(viewMiddle, showText);

            }
        });

        viewRight.setOnSelectListener(new SearchTabRight.OnSelectListener() {

            @Override
            public void getValue(String itemCode, String showText) {

                specialty = itemCode;
                onRefresh(viewRight, showText);
            }
        });

    }

    /**
     * @param view
     * @param showText
     * @Description: 回调更新选中项
     * @Author:胡帅
     * @Since: 2016年4月13日下午2:52:04
     */
    private void onRefresh(View view, String showText) {

        expandtabView.onPressBack();
        int position = getPositon(view);
        if (position >= 0 && !expandtabView.getTitle(position).equals(showText)) {
            expandtabView.setTitle(showText, position);
        }
        Toast.makeText(MainActivity.this, showText, Toast.LENGTH_SHORT).show();


    }

    private int getPositon(View tView) {
        for (int i = 0; i < mViewArray.size(); i++) {
            if (mViewArray.get(i) == tView) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onBackPressed() {

        if (!expandtabView.onPressBack()) {
            finish();
        }

    }


}
