package com.voids.ben.searchexpandtabview.expandtabview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.voids.ben.searchexpandtabview.R;
import com.voids.ben.searchexpandtabview.adapter.SearchSysParamAdapter;
import com.voids.ben.searchexpandtabview.bean.SysParamsBean;

import java.util.ArrayList;
import java.util.List;


/**
 *@Description:expandTab 右 专业特长item
 *@Author:ben
 *@Since:2016年4月13日下午2:33:17  
 */
public class SearchTabRight extends RelativeLayout implements ViewBaseAction {

    private ListView mListView;
    private List<SysParamsBean>    specialtyList;
    private final String[] items = new String[] {"全部特长", "item1", "item2", "item3", "item4", "item5", "item6","item7","item8" };//显示字段
    private final String[] itemsVaule = new String[] { "1", "2", "3", "4", "5", "6","7","8","9" };//隐藏id
    private OnSelectListener mOnSelectListener;
    private SearchSysParamAdapter adapter;
    private String mDistance;
    private long selectId;
    private String showText = "地区";
    private Context mContext;

    public String getShowText() {
     return showText;
    }

    public SearchTabRight(Context context) {
     super(context);
     init(context);
    }

    public SearchTabRight(Context context, AttributeSet attrs, int defStyle) {
     super(context, attrs, defStyle);
     init(context);
    }

    public SearchTabRight(Context context, AttributeSet attrs) {
     super(context, attrs);
     init(context);
    }
    
    //test数据
    private void testData(){
        specialtyList = new ArrayList<SysParamsBean> ();
        SysParamsBean bean;
        for ( int i = 0 ; i < items.length ; i++ ) {
            bean = new SysParamsBean();
            bean.setParName  (items[i]);
            bean.setParCode (""+i);
            specialtyList.add (bean);
        }
    }
    

    /**
     *@Description: 初始化视图
     *@Author:胡帅
     *@Since: 2016年4月13日下午4:30:26
     *@param context
     */
    private void init(Context context) {
     mContext = context;
     LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
     inflater.inflate(R.layout.search_single_item, this, true);
     //setBackgroundDrawable(getResources().getDrawable(R.drawable.choosearea_bg_mid));
     mListView = (ListView) findViewById(R.id.listViewSingle);
     
     testData();
     adapter = new SearchSysParamAdapter(context, specialtyList, 0, 0,0);
     
     adapter.setSelectedPosition (0);
     adapter.setTextSize(17);
     if (mDistance != null) {
      for (int i = 0; i < specialtyList.size (); i++) {
       if (specialtyList.get (i).getId ()==selectId) {
        adapter.setSelectedPositionNoNotify(i);
        showText = specialtyList.get (i).getParName  ();
        break;
       }
      }
     }
     mListView.setAdapter(adapter);
     adapter.setOnItemClickListener(new SearchSysParamAdapter.OnItemClickListener() {

      @Override
      public void onItemClick(View view, int position) {

       if (mOnSelectListener != null) {
        showText = specialtyList.get (position).getParName ();
        mOnSelectListener.getValue(specialtyList.get (position).getParCode (), specialtyList.get (position).getParName ());
       }
      }
     });
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
     mOnSelectListener = onSelectListener;
    }

    public interface OnSelectListener {
     public void getValue(String itemCode, String showText);
    }

    @Override
    public void hide() {
     
    }

    @Override
    public void show() {
     
    }

   }

