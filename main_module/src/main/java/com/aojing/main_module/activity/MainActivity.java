package com.aojing.main_module.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.Toast;

import com.aojing.main_module.R;
import com.aojing.main_module.adapter.MyFragmentPagerAdapter;
import com.aojing.model.HousingBinds;
import com.aojing.model.ProjectInfo;
import com.aojing.util.CheckFileIsExist;
import com.aojing.util.Get_Img_Str_From_Http;
import com.aojing.util.NoScrollViewPager;
import com.aojing.util.StringtoListUtil;
import com.aojing.util.Utils;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private NoScrollViewPager mViewPager;//自定义是否翻页ViewPager
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private TabLayout.Tab one;//房产
    private TabLayout.Tab two;//社区
    private TabLayout.Tab my_icon;//我的

    private String userName = "";
    boolean isExit;//退出程序的标识
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };


    //退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (!isExit){
                isExit = true;
                Toast.makeText(MainActivity.this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                handler.sendEmptyMessageDelayed(0,2000);
            }else {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
                System.exit(0);
            }
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private void init(){
        mViewPager = (NoScrollViewPager) findViewById(R.id.viewPager);
        mViewPager.setNoScroll(true);//设置viewPage不可滑动
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(myFragmentPagerAdapter);
        mViewPager.setCurrentItem(0);//设置当前要选择的
        mTabLayout = (TabLayout)findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);
        //指定Tab的位置
        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);
        my_icon = mTabLayout.getTabAt(2);
        //设置Tab 的图标
        two.setIcon(R.drawable.property_logo1);
        one.setIcon(R.drawable.shequ_logo);
        my_icon.setIcon(R.drawable.me_logo);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources resource = this.getResources();
        String pkgName = this.getPackageName();
        Utils.logStringCache = Utils.getLogText(getApplicationContext());
        userName = Utils.readString(getApplicationContext(),"key_save_userName");
        System.out.println("########ID#####="+Utils.readString(getApplicationContext(),"channelId"));
        System.out.println("########Phone#####="+Utils.readString(getApplicationContext(),"key_phoneNo"));
        setContentView(R.layout.activity_main);
        //保存到数据库
        //测试创建数据库sqlite
//        SQLiteDatabase database = new MyDBOpenHelper(getApplicationContext()).getWritableDatabase();
//        Cursor cursor = database.rawQuery("select * from housingbind",null);
//        List<HousingBinds> housingBindsList = new ArrayList<>();
//        while (cursor.moveToNext()){
//            String guid = cursor.getString(0);
//            String project = cursor.getString(1);
//            String userName = cursor.getString(2);
//            String housingName = cursor.getString(3);
//            String whetherOwner = cursor.getString(4);
//            String phone = cursor.getString(5);
//            String bindDate = cursor.getString(6);
//            String state = cursor.getString(7);
//            HousingBinds housingBinds = new HousingBinds(guid,bindDate,housingName,phone,project,state,userName,whetherOwner);
//            housingBindsList.add(housingBinds);
//        }
//        for (int i = 0; i < housingBindsList.size(); i++) {
//            System.out.println("数据库的信息:guid"+housingBindsList.get(i).getGuid()+"用户名："+housingBindsList.get(i).getUserName());
//
//        }
//        database.execSQL("insert into housingbind(guid,project,userName,housingName,whetherOwner,phone,bindDate,state)" +
//                "values(?,?,?,?,?,?,?,?)", new Object[]{"jsadkghajka", "幸福里","测试",
//                "幸福里A区12栋1单元690号","是","15578420012","20170216", "欠费"});
//        cursor.close();
//        database.close();
        init();
        // 启动百度push
        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY,
                Utils.getMetaValue(MainActivity.this, "api_key"));
        send();//发送手机Id

        // Push: 如果想基于地理位置推送，可以打开支持地理位置的推送的开关
        // PushManager.enableLbs(getApplicationContext());

        // Push: 设置自定义的通知样式，具体API介绍见用户手册，如果想使用系统默认的可以不加这段代码
        // 请在通知推送界面中，高级设置->通知栏样式->自定义样式，选中并且填写值：1，
        // 与下方代码中 PushManager.setNotificationBuilder(this, 1, cBuilder)中的第二个参数对应
//        CustomPushNotificationBuilder cBuilder = new CustomPushNotificationBuilder(
//                resource.getIdentifier(
//                        "notification_custom_builder", "layout", pkgName),
//                resource.getIdentifier("notification_icon", "id", pkgName),
//                resource.getIdentifier("notification_title", "id", pkgName),
//                resource.getIdentifier("notification_text", "id", pkgName));
//        cBuilder.setNotificationFlags(Notification.FLAG_AUTO_CANCEL);
//        cBuilder.setNotificationDefaults(Notification.DEFAULT_VIBRATE);
//        cBuilder.setStatusbarIcon(this.getApplicationInfo().icon);
//        cBuilder.setLayoutDrawable(resource.getIdentifier(
//                "simple_notification_icon", "drawable", pkgName));
////        R.drawable.simple_notification_icon
//        cBuilder.setNotificationSound(Uri.withAppendedPath(
//                MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "6").toString());
//        // 推送高级设置，通知栏样式设置为下面的ID
//        PushManager.setNotificationBuilder(this, 1, cBuilder);
    }
    @Override
    public void onResume() {
        super.onResume();

//        Log.d(TAG, "onResume");
//        updateDisplay();
    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        String action = intent.getAction();
//
//        if (Utils.ACTION_LOGIN.equals(action)) {
//            // Push: 百度账号初始化，用access token绑定
//            String accessToken = intent
//                    .getStringExtra(Utils.EXTRA_ACCESS_TOKEN);
//            PushManager.startWork(getApplicationContext(),
//                    PushConstants.LOGIN_TYPE_ACCESS_TOKEN, accessToken);
//            isLogin = true;
//            initButton.setText("更换百度账号");
//        }
//
//        updateDisplay();
//    }

    /**
     * 发送
     */
    private void send() {
        new AsyncTask<String, Integer, String>() {

            @Override
            protected String doInBackground(String... params) {
                sendMsg();//发送ChannelId
                sendMsg3();//获取头像
//                sendMsg1();//获取小区
//                sendMsg2();//获取绑定房产
                return null;
            }
        }.execute();
    }

    /**
     * http的post形式与服务器交互
     * 发送消息
     */
    protected void sendMsg() {
        try {
            Get_Img_Str_From_Http get_img_str_from_http = new Get_Img_Str_From_Http();
            get_img_str_from_http.PostChannelId("ChannelId",MainActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * http的post形式与服务器交互
     * 发送消息(获取绑定房产信息)
     */
    private String guid;//guid
    private String bindDate;//绑定时间
    private String houseID;//绑定的房间ID
    private String housingName;//绑定的房间
    private String phone;//手机号码
    private String project;//小区（项目）
    private String state;//状态
    private String userName1;//用户名
    private String whetherOwner;//是否业主
    private List<HousingBinds> housingBindsList = null;//绑定房产的信息列表
    private HousingBinds housingBinds = null;
    protected void sendMsg2() {
        try {
            String userName = Utils.readString(getApplicationContext(),"key_save_userName");
            System.out.println("请求绑定信息的用户："+userName);
            Get_Img_Str_From_Http get_img_str_from_http = new Get_Img_Str_From_Http();
            String result1 = get_img_str_from_http.getBindHouseInfo("getbindHouse",userName);
            System.out.println("************getbindHouse****"+result1);
            String result_json = removeBOM(result1);
            if (!result_json.equals("")) {
                //解析Json
                JSONObject jsonObject = new JSONObject(result_json.trim());
                JSONArray jsonArray = jsonObject.getJSONArray("housingBinds");

                housingBindsList = new ArrayList<HousingBinds>();
                JSONObject jsonObject_houseCnMap = jsonObject.getJSONObject("houseCnMap");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                    guid = jsonObject1.getString("guid");
                    bindDate = jsonObject1.getString("bindDate");
                    housingName = jsonObject1.getString("housingName");
//                    phone = jsonObject1.getString("phone");
                    project = jsonObject1.getString("projectID");
                    state = jsonObject1.getString("state");
                    userName1 = jsonObject1.getString("userName");
                    whetherOwner = jsonObject1.getString("whetherOwner");
                    if (!jsonObject_houseCnMap.equals("{}")){
                        JSONObject jsonObject_guid = jsonObject_houseCnMap.getJSONObject(guid);
                        if (!jsonObject_guid.equals("")){
                            housingName = jsonObject_guid.getString("housingName");
                        }else {
                            housingName = "";
                        }
                    }else {
                        housingName = "";
                    }
                    housingBinds = new HousingBinds(guid, bindDate,houseID, housingName, phone, project, state, userName, whetherOwner);
                    housingBindsList.add(housingBinds);
                }
                //保存从服务器获取的绑定房产的信息
                String saveBindInfo = StringtoListUtil.SceneList2String(housingBindsList);
                Utils.saveString(getApplicationContext(), userName1 + "bindHousingInfo", saveBindInfo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * http的post形式与服务器交互
     * 发送消息
     */

    private List<ProjectInfo> zonesList = null;//获取全部的小区信息的列表
    private String saveProject = "";
    private ProjectInfo projectInfo = null;
    protected void sendMsg1() {
        try {
            zonesList = new ArrayList<ProjectInfo>();
            Get_Img_Str_From_Http get_img_str_from_http = new Get_Img_Str_From_Http();
            String result = get_img_str_from_http.GetZones("getProject");
            String result_json = removeBOM(result);
            System.out.println("*********小区*json******"+result_json.trim());
            JSONObject jsonObject = new JSONObject(result_json);
            JSONArray jsonArray = jsonObject.getJSONArray("projectInfos");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                String project = object.getString("project");
                String propertyCompany = object.getString("propertyCompany");
                projectInfo = new ProjectInfo(propertyCompany,project);
                System.out.println("*********小区*******"+project+"*++++++*物业公司："+propertyCompany);
                Utils.saveString(getApplicationContext(),project+"getCompany",propertyCompany);//根据小区保存公司
                zonesList.add(projectInfo);
            }

            //保存新获取的数据
            saveProject = StringtoListUtil.SceneList2String(zonesList);
            Utils.saveString(getApplicationContext(),"key_get_project",saveProject);
            //解析Json
//            zonesList = parseJsonArrayToList(result_json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //处理Json
    public static final String removeBOM(String data) {
        if (TextUtils.isEmpty(data)) {
            return data;
        }
        if (data.startsWith("\ufeff")) {
            return data.substring(1);
        } else {
            return data;
        }
    }
    //获取最新的头像
    protected void sendMsg3() {
        try {
            //判断本地是否已经存在头像，有就不再获取，没有就获取

            CheckFileIsExist checkFileIsExist = new CheckFileIsExist();
            boolean isExists = checkFileIsExist.isExist(Environment.getExternalStorageDirectory()+"/"+userName+"getFromService.jpg") ;
            if (isExists){
                System.out.println("*******头像已经存在！！*******"+isExists );
            }else {
                Get_Img_Str_From_Http get_img_str_from_http = new Get_Img_Str_From_Http();
                String a = get_img_str_from_http.getImg("getHeadImg", userName,"getFromService");
                System.out.println("*******getImg*******" + a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Utils.setLogText(getApplicationContext(), Utils.logStringCache);
        super.onDestroy();
//        handler.removeCallbacks(runnable);//退出刷新
    }

    // 更新界面显示内容
//    private void updateDisplay() {
//        Log.d(TAG, "updateDisplay, logText:" + logText + " cache: "
//                + Utils.logStringCache);
//        if (logText != null) {
//            logText.setText(Utils.logStringCache);
//        }
//        if (scrollView != null) {
//            scrollView.fullScroll(ScrollView.FOCUS_DOWN);
//        }
//    }
//}
}
