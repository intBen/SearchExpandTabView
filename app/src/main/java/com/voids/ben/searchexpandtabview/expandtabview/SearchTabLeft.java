package com.voids.ben.searchexpandtabview.expandtabview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.voids.ben.searchexpandtabview.R;
import com.voids.ben.searchexpandtabview.adapter.SearchItemAdapter;
import com.voids.ben.searchexpandtabview.bean.AreaBean;

import java.util.ArrayList;
import java.util.List;

/**
 *@Description:expandTab 左 地区item
 *@Author:ben
 *@Since:2016年4月13日下午2:31:58  
 */
public class SearchTabLeft extends RelativeLayout implements ViewBaseAction {

    private ListView          mListView;
    private List<AreaBean>    areaList;
    private final String[]    items      = new String[] { "全部地区", "item1", "item2", "item3", "item4", "item5", "item6" };// 显示字段
    private final String[]    itemsVaule = new String[] { "1", "2", "3", "4", "5", "6", "7" };                           // 隐藏id
    private OnSelectListener  mOnSelectListener;
    private SearchItemAdapter adapter;
    private String            mDistance;
    private String            showText   = "地区";
    private Context           mContext;
    private long selectId;

    public String getShowText(){
        return showText;
    }

    public SearchTabLeft(Context context) {
        super (context);
        init (context);
    }

    public SearchTabLeft(Context context, AttributeSet attrs, int defStyle) {
        super (context, attrs, defStyle);
        init (context);
    }

    public SearchTabLeft(Context context, AttributeSet attrs) {
        super (context, attrs);
        init (context);
    }

    private void testData(){
        areaList = new ArrayList<AreaBean> ();
        AreaBean bean;
        for ( int i = 0 ; i < items.length ; i++ ) {
            bean = new AreaBean ();
            bean.setAreaName (items[i]);
            bean.setAreaId (""+i);
            areaList.add (bean);

        }
    }

    /**
     *@Description: 初始化视图
     *@Author:胡帅
     *@Since: 2016年4月13日下午4:30:58
     *@param context
     */
    private void init(Context context){
       
        
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate (R.layout.search_single_item, this, true);
        // setBackgroundDrawable(getResources().getDrawable(R.drawable.choosearea_bg_mid));
        mListView = (ListView) findViewById (R.id.listViewSingle);
        
        // test
        testData();

        adapter = new SearchItemAdapter (context,areaList,0,0,0);
        adapter.setSelectedPosition (0);
        adapter.setTextSize (17);
        if (selectId != 0) {
            for ( int i = 0 ; i < areaList.size () ; i++ ) {
                if (areaList.get (i).getId ()==selectId) {
                    adapter.setSelectedPositionNoNotify (i);
                    showText = areaList.get (i).getAreaName ();
                    break;
                }
            }
        }

        mListView.setAdapter (adapter);
        adapter.setOnItemClickListener (new SearchItemAdapter.OnItemClickListener () {

            @Override
            public void onItemClick(View view,int position){

                if (mOnSelectListener != null) {
                    showText = areaList.get (position).getAreaName ();
                    mOnSelectListener.getValue (areaList.get (position).getAreaId (), areaList.get (position).getAreaName ());
                }
            }
        });
    }

    public void setOnSelectListener(OnSelectListener onSelectListener){
        mOnSelectListener = onSelectListener;
    }

    public interface OnSelectListener {

        public void getValue(String itemCode,String showText);
    }

    @Override
    public void hide(){

    }

    @Override
    public void show(){

    }

}
