package com.aojing.main_module.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aojing.adapter.MyAdapter;
import com.aojing.main_module.R;
import com.aojing.model.HousingBinds;
import com.aojing.model.ProjectInfo;
import com.aojing.model.Repair;
import com.aojing.ninegrid.NineGridLayout;
import com.aojing.ninegrid.NineGridModel_Broadcast;
import com.aojing.ninegrid.NineGridRepairModel;
import com.aojing.util.Get_Img_Str_From_Http;
import com.aojing.util.MyDialog;
import com.aojing.util.StringtoListUtil;
import com.aojing.util.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



/**
 * 社区模块的页面
 * Created by wxw on 2016/12/30.
 */
public class Fragment_community extends Fragment {
    private String isLogin = "";
    private LinearLayout ll_contact_property;
//    private TextView tv_contact_property;//联系物业图标
    private TextView tv_broadcast_comment;
    private TextView tv_broadcast_good;
    private TextView tv_broadcast_bad;
    private TextView tv_broadcast_content;
    private TextView tv_broadcast_time;
    private LinearLayout ll_complain_repair;//投诉报修
    private Spinner spinner_choose_plot_community;//选择绑定的社区
    private TextView community_project_name;//小区的名称
    private MyAdapter<ProjectInfo> adapter;
    private String readProject = "";
    private String [] projects = null;
    private List<ProjectInfo> datas = null;
    private String propertyCompany_str = "";
    private String project_str = "";
    String choose_zone = "";//存放选择的小区的内容
    private LinearLayout ll_search_parkInfo;
    private TextView tv_more_broadcast;//更多广播
    private LinearLayout ll_one_broadcast;//显示一条信息
    private Button community_add_house;//没有绑定房产的页面的添加房产按钮
    private List<HousingBinds> housingBindsList;
    private String userName = "";
    private TextView tv_community_change_project;//切换小区按钮
    private LinearLayout pay_for_other;
    private NineGridLayout broadcast_layout_nine_grid;
    private MyDialog dialog;

    private ScrollView scroll_community;
    private RelativeLayout rl_top_community;
    private ViewPager vp_bottom_ads;//底部广告图片
    private ViewPager viewPager; // android-support-v4中的滑动组件
    private List<ImageView> imageViews; //
    private List<ImageView> imageViews_bottom; // 底部广告滑动的图片集合
    int currentItem = 0;//当前显示页
    int currentItem_bottom = 0;//底部广告当前显示页
    private String[] titles; // 图片标题
    private int[] imageResId; // 图片ID
    private int[] imageResId_bottom; // 底部广告图片ID
    private List<View> dots; // 图片标题正文的那些点
    private List<View> dots_bottom; //底部广告图片的那些点
    ImageView imageView = null;
    ImageView imageView_bottom = null;
    private ScheduledExecutorService scheduledExecutorService;
    private ScheduledExecutorService scheduledExecutorService_bottom;
    private TextView tv_title;
    // 切换当前显示的图片
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            viewPager.setCurrentItem(currentItem);// 切换当前显示的图片
        }
    };
    private Handler handler_bottom = new Handler() {
        public void handleMessage(android.os.Message msg) {
            vp_bottom_ads.setCurrentItem(currentItem_bottom);// 切换当前显示的图片
        }
    };
    /**
     * 换行切换任务
     *
     * @author Administrator
     */
    public class ScrollTask implements Runnable {
        public void run() {
            synchronized (viewPager) {
                System.out.println("currentItem: " + currentItem);
                currentItem = (currentItem + 1) % imageViews.size();
                handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
            }
        }

    }
    public class ScrollTask_Bottom implements Runnable {
        public void run() {
            synchronized (vp_bottom_ads) {
                System.out.println("currentItemBottom: " + currentItem_bottom);
                currentItem_bottom = (currentItem_bottom + 1) % imageViews_bottom.size();
                handler_bottom.obtainMessage().sendToTarget(); // 通过Handler切换图片
            }
        }

    }
    /**
     * 当ViewPager中页面的状态发生改变时调用
     *
     * @author Administrator
     */
    public class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        private int oldPosition = 0;


        /**
         * This method will be invoked when a new page becomes selected.
         * position: Position index of the new selected page.
         */
        public void onPageSelected(int position) {
            currentItem = position;
//            tv_title.setText(titles[position]);
            dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            dots.get(position).setBackgroundResource(R.drawable.dot_focused);
            oldPosition = position;
        }

        public void onPageScrollStateChanged(int arg0) {

        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }
    }

    public class MyPageChangeListener_Bottom implements ViewPager.OnPageChangeListener {
        private int oldPosition = 0;


        /**
         * This method will be invoked when a new page becomes selected.
         * position: Position index of the new selected page.
         */
        public void onPageSelected(int position) {
            currentItem_bottom = position;
//            tv_title.setText(titles[position]);
            dots_bottom.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            dots_bottom.get(position).setBackgroundResource(R.drawable.dot_focused);
            oldPosition = position;
        }

        public void onPageScrollStateChanged(int arg0) {

        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }
    }
    /**
     * 填充ViewPager页面的适配器
     *
     * @author Administrator
     */
    public class MyPageAdapter extends PagerAdapter {

        private int [] imageResId;
        private List<ImageView> imageViews;

        public MyPageAdapter(int[] imageResId, List<ImageView> imageViews) {
            this.imageResId = imageResId;
            this.imageViews = imageViews;
        }

        @Override
        public int getCount() {
            return imageResId.length;
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(imageViews.get(arg1));
            return imageViews.get(arg1);
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("TAG","1");

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("TAG","2");

    }

    private View view;
    private boolean isHaveInfo = true;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("TAG","3");

        userName = Utils.readString(getContext(),"key_save_userName");
        housingBindsList = new ArrayList<>();
//        String saveBindInfo = StringtoListUtil.SceneList2String(housingBindsList);
//        Utils.saveString(getContext(), userName + "bindHousingInfo", saveBindInfo);
        String save = Utils.readString(getContext(),userName + "bindHousingInfo");
        try {
            housingBindsList = StringtoListUtil.String2SceneList(save);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        if (housingBindsList != null && !housingBindsList.isEmpty()){
//            System.out.println("集合不为空");
            isHaveInfo = true;
            view = inflater.inflate(R.layout.community, container, false);
//        }else {
//            System.out.println("集合为空");
//            isHaveInfo = false;
//            view = inflater.inflate(R.layout.community_no_bind_house, container, false);
//            TextView textView = (TextView)view.findViewById(R.id.community_add_house);
//            textView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getContext(), Binding_Property.class);
//                    startActivity(intent);
//
//                }
//            });
//        }
//        if (!TextUtils.isEmpty(housingBindsList.get(0).getProjectID())) {}
    return view;

}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("TAG","4");
        if (isHaveInfo) {

            imageResId = new int[]{R.drawable.p1,R.drawable.p3,R.drawable.p2};//表示只有一张图片在轮播
            imageResId_bottom = new int[]{R.drawable.p3,R.drawable.p2};
        titles = new String[imageResId.length];
        titles[0] = "优质果蔬！！大优惠啦！！！";
        titles[1] = "美女又回来啦！头顶阳光，魅力无限@*@";
        titles[2] = "邂逅春天，少不了她的背影。。";
//        titles[3] = "秋风沙沙，你又美丽啦！！";
//        titles[4] = "热血屌丝的反杀Win7";

            imageViews = new ArrayList<ImageView>();
            imageViews_bottom = new ArrayList<ImageView>();
            // 初始化图片资源
            for (int i = 0; i < imageResId.length; i++) {
                imageView = new ImageView(getContext());

                imageView.setImageResource(imageResId[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageViews.add(imageView);
            }

            for (int i = 0; i < imageResId_bottom.length; i++) {
                imageView_bottom = new ImageView(getContext());

                imageView_bottom.setImageResource(imageResId_bottom[i]);
                imageView_bottom.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageViews_bottom.add(imageView_bottom);
            }
            dots = new ArrayList<View>();
            dots.add(getActivity().findViewById(R.id.v_dot0));
            dots.add(getActivity().findViewById(R.id.v_dot1));
            dots.add(getActivity().findViewById(R.id.v_dot2));
            dots.add(getActivity().findViewById(R.id.v_dot3));
            dots.add(getActivity().findViewById(R.id.v_dot4));

            dots_bottom = new ArrayList<View>();
            dots_bottom.add(getActivity().findViewById(R.id.v_dot00));
            dots_bottom.add(getActivity().findViewById(R.id.v_dot11));
            dots_bottom.add(getActivity().findViewById(R.id.v_dot22));
            dots_bottom.add(getActivity().findViewById(R.id.v_dot33));
            dots_bottom.add(getActivity().findViewById(R.id.v_dot44));

            tv_title = (TextView)getActivity().findViewById(R.id.tv_title);
            vp_bottom_ads = (ViewPager) getActivity().findViewById(R.id.vp_bottom_ads);
            viewPager = (ViewPager) getActivity().findViewById(R.id.vp_goods_details);
            viewPager.setAdapter(new MyPageAdapter(imageResId,imageViews));// 设置填充ViewPager页面的适配器
            vp_bottom_ads.setAdapter(new MyPageAdapter(imageResId_bottom,imageViews_bottom));
            // 设置一个监听器，当ViewPager中的页面改变时调用
            viewPager.setOnPageChangeListener(new MyPageChangeListener());
            vp_bottom_ads.setOnPageChangeListener(new MyPageChangeListener_Bottom());

//            rl_top_community = (RelativeLayout)getActivity().findViewById(R.id.rl_top_community);

//            scroll_community = (ScrollView)getActivity().findViewById(R.id.scroll_community);
//            scroll_community.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//
//                    if (v.getY()>20) {
//                        Toast.makeText(getContext(),"hello",Toast.LENGTH_SHORT).show();
//                        rl_top_community.setBackgroundColor(Color.parseColor("#80ffffff"));
//                    }
//                    return false;
//                }
//            });
            ll_search_parkInfo = (LinearLayout) getActivity().findViewById(R.id.ll_search_parkInfo);
            ll_search_parkInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(getContext(), ParkInfo_Choose_Project.class);
//                    startActivity(intent);
                }
            });
            ll_one_broadcast = (LinearLayout)getActivity().findViewById(R.id.ll_one_broadcast);
            View view = View.inflate(getContext(),R.layout.broadcast_item_nine_grid,null);
            ll_one_broadcast.addView(view);
            //点击查看广播详情
            ll_one_broadcast.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(getContext(), Broadcast_Detail.class);
//                    intent.putExtra("projectID", projectID1);
//                    intent.putExtra("company", propertyCompany_str);
//                    intent.putExtra("broadcastID", broadcastID);
//                    intent.putExtra("list", (Serializable) nineGridModel_broadcastList);
////                    startActivityForResult(intent,1);
//                    startActivity(intent);
                }
            });
            //更多广播
            tv_more_broadcast = (TextView)getActivity().findViewById(R.id.tv_more_broadcast);
            tv_more_broadcast.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Intent intent = new Intent(getContext(), Broadcast_List.class);
//                    intent.putExtra("projectID", projectID1);
//                    intent.putExtra("company", propertyCompany_str);
//                    startActivity(intent);

                }
            });

            community_project_name = (TextView) getActivity().findViewById(R.id.community_project_name);
            //获取小区
            readProject = Utils.readString(getContext(), "key_get_project");
            datas = new ArrayList<ProjectInfo>();
            try {
                datas = StringtoListUtil.String2SceneList(readProject);
//                for (int i = 0; i < 3; i++) {
//
//                    datas.add(new ProjectInfo("aojing", "幸福里"));
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            ll_contact_property = (LinearLayout) getActivity().findViewById(R.id.ll_contact_property);
            tv_broadcast_bad = (TextView)getActivity().findViewById(R.id.tv_broadcast_bad);
            tv_broadcast_comment = (TextView)getActivity().findViewById(R.id.tv_broadcast_comment);
            tv_broadcast_good = (TextView)getActivity().findViewById(R.id.tv_broadcast_good);
            tv_broadcast_time = (TextView)getActivity().findViewById(R.id.tv_broadcast_time);
            tv_broadcast_content = (TextView)getActivity().findViewById(R.id.tv_broadcast_content);
            broadcast_layout_nine_grid = (NineGridLayout)getActivity().findViewById(R.id.broadcast_layout_nine_grid);
            tv_community_change_project = (TextView)getActivity().findViewById(R.id.tv_community_change_project);
            dialog = new MyDialog(getContext(),R.layout.dialog_list_view_simple_item,new int[]{}, Gravity.BOTTOM,1.0);

            isLogin = Utils.readString(getContext(),userName+"isLogin");
            if (isLogin.equals("yes")){ //已经登录，直接到主页
                tv_community_change_project.setVisibility(View.VISIBLE);
                community_project_name.setText("");
                tv_more_broadcast.setVisibility(View.VISIBLE);
                ll_one_broadcast.setVisibility(View.VISIBLE);
            }else {//没有登录则跳到登录界面
                tv_community_change_project.setVisibility(View.GONE);
                community_project_name.setText("社区");
                tv_more_broadcast.setVisibility(View.GONE);
                ll_one_broadcast.setVisibility(View.GONE);
            }
            //切换小区按钮
            tv_community_change_project.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.show();
                    ListView listView = (ListView)dialog.findViewById(R.id.dialog_list_view_simple);
                    initAdapter();
                    listView.setAdapter(adapter);
                }

            });

            //联系物业图标点击事件
            ll_contact_property.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (isLogin.equals("yes")){//已经登录
//                        Intent intent = new Intent(getContext(), Contact_property.class);
//                        startActivity(intent);
//                    }else {//没有登录则跳到登录界面
//                        Intent intent1 = new Intent(getContext(), LoginActivity.class);
//                        startActivity(intent1);
//                    }

                }
            });

            //帮别人缴费
            pay_for_other = (LinearLayout) getActivity().findViewById(R.id.pay_for_others);
            pay_for_other.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //快捷交费事件
//                    Intent intent = new Intent(getActivity(), Quick_Pay.class);
//                    startActivity(intent);
                }
            });

            //报修
            ll_complain_repair = (LinearLayout) getActivity().findViewById(R.id.ll_complain_repair);
            ll_complain_repair.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    send();
//                    if (isLogin.equals("yes")){//已经登录
//                        Intent intent = new Intent(getContext(), My_Repair.class);
//                        intent.putExtra("projectID", projectID1);
//                        intent.putExtra("company", propertyCompany_str);
//                        startActivity(intent);
//                    }else {//没有登录则跳到登录界面
//                        Intent intent1 = new Intent(getContext(), LoginActivity.class);
//                        startActivity(intent1);
//                    }


                }
            });

            //在列表获取小区的名称组建数组
            if (datas != null ) { //有数据
                projects = new String[datas.size()];
                for (int i = 0; i < datas.size(); i++) {
                    projects[i] = datas.get(i).getProjectName();
                }



            } else {
                Toast.makeText(getContext(), "服务连接不上~~", Toast.LENGTH_SHORT).show();
                projects = null;
            }
        }else {

//            community_add_house = (Button)getActivity().findViewById(R.id.community_add_house);
//            community_add_house.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent intent = new Intent(getContext(), Binding_Property.class);
//                    startActivity(intent);


//                }
//            });
        }
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == 0){
//            System.out.println("resultCode:"+resultCode+"result:"+data);
//            tv_broadcast_comment.setText("66");
////        tv_broadcast_good.setText(data.getExtras().getString("good"));
////        tv_broadcast_bad.setText(data.getExtras().getString("bad"));
//        }
//
//        Log.e("TAG","result");
//
//    }

    //选择小区的列表的适配器
    private void initAdapter() {
        adapter = new MyAdapter<ProjectInfo>((ArrayList<ProjectInfo>) datas,R.layout.one_text_view_item) {
            @Override
            public void bindView(ViewHolder holder, final ProjectInfo obj) {
                holder.setText(R.id.tv_one_item,obj.getProjectName());
                holder.setOnClickListener(R.id.tv_one_item, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        community_project_name.setText(obj.getProjectName());
                        propertyCompany_str = obj.getCompany();
                        projectID1 = obj.getProjectID();
                        TYPE = GET_BROADCAST_TXT;
                        send();
                        dialog.dismiss();
                    }
                });
            }
        };
    }
    @Override
    public void onStart() {
        super.onStart();
        Log.e("TAG","5");
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService_bottom = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每两秒钟切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 4, 5, TimeUnit.SECONDS);
        scheduledExecutorService_bottom.scheduleAtFixedRate(new ScrollTask_Bottom(), 4, 5, TimeUnit.SECONDS);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("TAG","6");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("TAG","7");
    }

    @Override
    public void onStop() {
        super.onStop();
        scheduledExecutorService.shutdown();
        scheduledExecutorService_bottom.shutdown();
        Log.e("TAG","8");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("TAG","9");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TAG","10");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("TAG","11");

    }

    private int TYPE;
    private static final int GET_BROADCAST_TXT =1;
    private static final int GET_BROADCAST_IMG =2;
    private  String broadcastID = "";
    private String broadcastImgID = "";
    private List<String> broadcastImgIDs = null;
    private void send() {
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... params) {
                switch (TYPE) {
                    case GET_BROADCAST_TXT:
                        sendMsg1();
                        break;
                    case GET_BROADCAST_IMG:
                        sendMsg2(propertyCompany_str, broadcastID);
                        break;

                }

                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                System.out.println("*******oneBroadcastInfo****" + s);
                switch (TYPE) {
                    case GET_BROADCAST_TXT:
                        //解析Json，得到 roomList
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            JSONArray jsonArray = jsonObject.getJSONArray("datas");
                            System.out.println("********000*********" + jsonArray.get(0));
                            if (jsonArray != null && jsonArray.get(0) != null) {
                                JSONObject object_broadcast = (JSONObject) jsonArray.get(0);
                                broadcastID = object_broadcast.getString("broadcastID");
                                String broadcastState = object_broadcast.getString("broadcastState");
                                String company = object_broadcast.getString("company");
                                String content = object_broadcast.getString("content");
                                String createTime = object_broadcast.getString("createTime");
                                String creater = object_broadcast.getString("creater");
                                String projectID = object_broadcast.getString("projectID");
                                String state = object_broadcast.getString("state");
                                if (jsonArray.get(1) != null && !jsonArray.get(1).toString().equals("")) {
                                    JSONArray array_broadcastImgs = (JSONArray) jsonArray.get(1);
                                    broadcastImgIDs = new ArrayList<String>();
                                    urls = new ArrayList<>();
                                    for (int i = 0; i < array_broadcastImgs.length(); i++) {
                                        JSONObject object_img = (JSONObject) array_broadcastImgs.get(i);
                                        broadcastImgID = object_img.getString("broadcastImgID");
                                        System.out.println("##aaaaaaaaaaaaa:"+broadcastImgID);
                                        broadcastImgIDs.add(broadcastImgID);
//                                        String imgAddress = object_img.getString("imgAddress");
                                        String url = "file:///"+Environment.getExternalStorageDirectory()+"/wuye/photo/"+broadcastImgID+".jpg";
                                        urls.add(url);//图片路径
                                    }
//                                    send();//获取图片
//                                    TYPE = GET_BROADCAST_IMG;

                                } else {
                                    urls = null;
                                }
                                int comment = (int) jsonArray.get(2);
                                int good = (int) jsonArray.get(3);
                                int bad = (int) jsonArray.get(4);

                                nineGridModel_broadcast = new NineGridModel_Broadcast();
                                nineGridModel_broadcast.imgUrls = urls;
                                nineGridModel_broadcast.projectID = projectID;
                                nineGridModel_broadcast.company = company;
                                nineGridModel_broadcast.time = createTime;
                                nineGridModel_broadcast.bad = String.valueOf(bad);
                                nineGridModel_broadcast.good = String.valueOf(good);
                                nineGridModel_broadcast.broadcastID = broadcastID;
                                nineGridModel_broadcast.comment = String.valueOf(comment);
                                nineGridModel_broadcast.isShowAll = true;
                                nineGridModel_broadcast.content = content;

                                nineGridModel_broadcastList = new ArrayList<>();
                                nineGridModel_broadcastList.add(nineGridModel_broadcast);

                                tv_broadcast_bad.setText(nineGridModel_broadcast.bad);
                                tv_broadcast_good.setText(nineGridModel_broadcast.good);
                                tv_broadcast_comment.setText(nineGridModel_broadcast.comment);
                                tv_broadcast_content.setText(nineGridModel_broadcast.content);
                                tv_broadcast_time.setText(nineGridModel_broadcast.time);


                            } else {
                                System.out.println("数据为空！！");
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        break;
                    case GET_BROADCAST_IMG:
//                        String url = Environment.getExternalStorageDirectory()+"/wuye/photo/"+broadcastImgID+".jpg";
//                        urls = new ArrayList<>();
//                        urls.add(url);//图片路径
//                        broadcast_layout_nine_grid.setIsShowAll(true);
//                        broadcast_layout_nine_grid.setUrlList(urls);
                        break;
                }
            }
        }.execute();
    }
    /**
     * http的post形式与服务器交互
     * 发送消息
     */

    String result = "";
    private List<Repair> repairList = null;
    private Repair repair = null;
    private  String projectID1 = "";
    private NineGridRepairModel nineGridRepairModel = null;
    private List<NineGridRepairModel> nineGridRepairModelList;
    private int sum = 0;
    String appAddre = "";
    List<String> showImgs = null;
    List<String> urls = null;
    List<NineGridModel_Broadcast> nineGridModel_broadcastList;
    NineGridModel_Broadcast nineGridModel_broadcast = null;
    //报修
    protected void sendMsg() {
        try {
            nineGridRepairModelList = new ArrayList<>();
//            repairList = new ArrayList<Repair>();//接收服务器报修列表
            Get_Img_Str_From_Http get_img_str_from_http = new Get_Img_Str_From_Http();
            result = get_img_str_from_http.getRepairInfo("getUserRepairs",propertyCompany_str,projectID1,userName);//获取物业公司的所有房间
            System.out.println("*******repairInfo****" + result);
//            //解析Json，得到 roomList
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("repairs");
            JSONObject jsonObject1 = jsonObject.getJSONObject("repairImgMap");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                String address = object.getString("address");
                String company = object.getString("company");
                String content = object.getString("content");
                String projectID = object.getString("projectID");
                String proprietor = object.getString("proprietor");
                String repairID = object.getString("repairID");
                String repairTime = object.getString("repairTime");
                String state = object.getString("state");
                String u = jsonObject1.getString(repairID);
                sum = Integer.parseInt(u);
//                    //生成保存在app的图片地址
                if (sum>0) {
                    showImgs = new ArrayList<>();
                    for (int j = 0; j < sum; j++) {
                        appAddre = "file:///" + Environment.getExternalStorageDirectory() + "/wuye/photo/" + repairID + "_0" + (j+1) + ".jpg";
                        showImgs.add(appAddre);
                    }
                }
                System.out.println(repairID+"共有"+sum+"张照片");
                nineGridRepairModel = new NineGridRepairModel();
                nineGridRepairModel.address = address;
                nineGridRepairModel.content = content;
                nineGridRepairModel.isShowAll = true;
                nineGridRepairModel.repairID = repairID;
                nineGridRepairModel.time = repairTime;

                nineGridRepairModel.urlList = showImgs;
                nineGridRepairModelList.add(nineGridRepairModel);

//                repair = new Repair(repairID,repairTime,content,address,sum);
//                repairList.add(repair);

//                System.out.println("#####housingName" + housingInfoList.get(i).getHousingName() + ",HouseID:" + housingInfoList.get(i).gethouseID() + ",projectID:" + projectID);
            }

            String save = StringtoListUtil.SceneList2String(nineGridRepairModelList);
            Utils.saveString(getContext(),"getRepairInfo",save);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void sendMsg1() {
        try {
//            nineGridRepairModelList = new ArrayList<>();
//            repairList = new ArrayList<Repair>();//接收服务器报修列表
            Get_Img_Str_From_Http get_img_str_from_http = new Get_Img_Str_From_Http();
            result = get_img_str_from_http.getBroadcast("getLatestBroadcast",propertyCompany_str,projectID1,userName);//获取物业公司的所有房间
            System.out.println("company:"+propertyCompany_str+"projectID:"+projectID1+"userName:"+userName);
//            result = get_img_str_from_http.getBroadcast("getAllBroadcast",propertyCompany_str,projectID1,userName,"1");//获取物业公司的所有房间
            System.out.println("*******oneBroadcastInfo****" + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取广播图片
    protected void sendMsg2(String company,String broadcastID) {
        try {
            Get_Img_Str_From_Http get_img_str_from_http = new Get_Img_Str_From_Http();
            for (int i = 0; i < broadcastImgIDs.size(); i++) {
                System.out.println("####################999#############"+broadcastImgIDs.get(i));
                result = get_img_str_from_http.getImgs("getOneBroadcastImg",company,projectID1,userName,broadcastID,broadcastImgIDs.get(i));//获取广播图片
                    JSONObject object_one_img = new JSONObject(result);
                    String result1 = object_one_img.getString("result");
                    if (result1.equals("success")) {
                        String img = object_one_img.getString("img");
                        get_img_str_from_http.decoderBase64File(img, Environment.getExternalStorageDirectory()+"/wuye/photo/"+broadcastImgIDs.get(i)+".jpg");
                    }else {

                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //返回的广播详情返回List，设置订阅者
//    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onReturnResult(String msg){
        Log.e("EventBus:","get");
        tv_broadcast_bad.setText(msg);
//        tv_broadcast_good.setText(nineGridModel_broadcast.good);
//        tv_broadcast_comment.setText(nineGridModel_broadcast.comment);
//        tv_broadcast_content.setText(nineGridModel_broadcast.content);
//        tv_broadcast_time.setText(nineGridModel_broadcast.time);

    }

}
