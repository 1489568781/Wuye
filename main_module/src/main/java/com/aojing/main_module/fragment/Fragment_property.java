package com.aojing.main_module.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aojing.main_module.R;
import com.aojing.main_module.adapter.MyAdapter_Main_Property;
import com.aojing.model.HousingBinds;
import com.aojing.model.ProjectInfo;
import com.aojing.util.Get_Img_Str_From_Http;
import com.aojing.util.StringtoListUtil;
import com.aojing.util.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



/**
 * 房产页面
 * Created by wxw on 2016/12/30.
 */
public class Fragment_property extends Fragment {


    private ImageView fr_property_progress;//请求进度
    private AnimationDrawable animationDrawable;//动画效果
    private View view;//没有绑定房产显示的界面item
    private TextView main_add_property;//显示没有绑定房产的界面里面的添加房产按钮
    private ListView list_view_property;
    private MyAdapter_Main_Property adapterMainProperty = null;
    private TextView tv_add_property;//添加绑定房产

    private List<HousingBinds> houseList = null;
    private int times = 0;//刷新的次数
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.property,container,false);

    }

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            this.update();
            handler.postDelayed(this, 300);//间隔3秒
            if (times == 1) {//刷新两次后结束刷新
                handler.removeCallbacks(runnable);
                //停止刷新
                System.out.println("***********STOP*REFRESH************");
            }
        }
        void update(){
            send();//获取小区、当前用户绑定房产的列表
//            init();//页面显示内容初始化
            times++;
        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        EventBus.getDefault().register(this);
        userName = Utils.readString(getContext(),"key_save_userName");//获取当前的用户名
        fr_property_progress = (ImageView)getActivity().findViewById(R.id.fr_property_progress);

        fr_property_progress.setImageResource(R.drawable.amin_pgbar);
        animationDrawable = (AnimationDrawable)fr_property_progress.getDrawable();
        animationDrawable.start();
        send();

        System.out.println("Frame_property获取当前的useName:"+userName);

        //添加房产事件
        tv_add_property = (TextView)getActivity().findViewById(R.id.tv_add_property);
        tv_add_property.setOnClickListener(new View.OnClickListener() {//添加房产（右上角按钮）
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), Binding_Property.class);
//                startActivity(intent);
            }
        });
//        init();//初始化
    }

    public void init(){
        try {
            View view = View.inflate(getContext(),R.layout.no_property_item,null);
            list_view_property = (ListView) getActivity().findViewById(R.id.list_view_property);
            list_view_property.addFooterView(view);
            String getHosingBinds = Utils.readString(getContext(), userName + "bindHousingInfo");
            houseList = new ArrayList<HousingBinds>();
            houseList = StringtoListUtil.String2SceneList(getHosingBinds);
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            adapterMainProperty = new MyAdapter_Main_Property(getActivity(),dm.widthPixels,houseList);
            list_view_property.setAdapter(adapterMainProperty);
            list_view_property.setEmptyView(getActivity().findViewById(R.id.empty_show));
            main_add_property = (TextView)getActivity().findViewById(R.id.main_add_property1);//添加房产
            main_add_property.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //先去除之前的数据
//                        Utils.clearSaveInfo(getContext(),"key_get_project");
//                        send();//请求服务器获取所有小区的列表
                    Toast.makeText(getActivity(),"add property",Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getActivity(), Binding_Property.class);
//                    intent.putExtra("projectID",projectID);
//                    startActivity(intent);
                    }
                });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送
     */
    private void send() {
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... params) {
                Utils.clearSaveInfo(getActivity(),userName + "bindHousingInfo");
//                sendMsg1();//获取绑定房产信息
//                sendMsg();//获取小区
                return result_json;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                animationDrawable.stop();
                fr_property_progress.setImageResource(0);
                if (!result_json.equals("")) {
                    //解析Json
                    try {
                        JSONObject jsonObject = new JSONObject(result_json.trim());
                        JSONArray jsonArray = jsonObject.getJSONArray("housingBinds");
                        JSONObject jsonObject_houseCnMap = jsonObject.getJSONObject("houseCnMap");
                        housingBindsList = new ArrayList<HousingBinds>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                            guid = jsonObject1.getString("guid");
                            bindDate = jsonObject1.getString("bindDate");
                            houseID = jsonObject1.getString("houseID");
//                            phone = jsonObject1.getString("phone");
                            projectID = jsonObject1.getString("projectID");
                            state = jsonObject1.getString("state");
                            userName1 = jsonObject1.getString("userName");
                            whetherOwner = jsonObject1.getString("whetherOwner");
                            if (!jsonObject_houseCnMap.equals("{}")) {
                                JSONObject jsonObject_guid = jsonObject_houseCnMap.getJSONObject(houseID);
                                if (!jsonObject_guid.equals("")) {
                                    hosingName = jsonObject_guid.getString("housingName");
                                } else {
                                    hosingName = "";
                                }

                            } else {
                                hosingName = "";
                            }
                            housingBinds = new HousingBinds(guid, bindDate, houseID, hosingName, null, projectID, state, userName1, whetherOwner);
                            housingBindsList.add(housingBinds);
                        }
                        //保存从服务器获取的绑定房产的信息
                        if (userName.equals(userName1)) { //判断是否是当前用户,是就保存
                            String saveBindInfo = StringtoListUtil.SceneList2String(housingBindsList);
                            Utils.saveString(getContext(), userName1 + "bindHousingInfo", saveBindInfo);
                        }
                    }catch (Exception e){
                    e.printStackTrace();
                    }
                }
                init();
            }

        }.execute();
    }

    /**
     * http的post形式与服务器交互
     * 发送消息
     */

    private List<ProjectInfo> zonesList = null;//获取全部的小区信息的列表
    private String saveProject = "";
    private ProjectInfo projectInfo = null;
    protected void sendMsg() {
        try {
            zonesList = new ArrayList<ProjectInfo>();
            Get_Img_Str_From_Http get_img_str_from_http = new Get_Img_Str_From_Http();
            String result = get_img_str_from_http.GetZones("getProject");
            String result_json = removeBOM(result);
            System.out.println("*********小区*json******"+result_json.trim());
            if (!result_json.equals("")) {
                JSONObject jsonObject = new JSONObject(result_json);
                JSONArray jsonArray = jsonObject.getJSONArray("projectInfos");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = (JSONObject) jsonArray.get(i);
                    String project = object.getString("projectName");
                    String propertyCompany = object.getString("propertyCompany");
                    String guid = object.getString("guid");//小区的guid
                    projectInfo = new ProjectInfo(propertyCompany, project,guid);
                    System.out.println("*********小区*******" + project + "*++++++*物业公司：" + propertyCompany);
                    Utils.saveString(getContext(), guid + "getCompany", propertyCompany);//根据小区保存公司
                    Utils.saveString(getContext(), project + "getCompanyGuid",guid);//根据小区保存公司
                    zonesList.add(projectInfo);
                }

                //保存新获取的数据
                saveProject = StringtoListUtil.SceneList2String(zonesList);
                Utils.saveString(getContext(), "key_get_project", saveProject);
                //解析Json
//            zonesList = parseJsonArrayToList(result_json);
            }
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
    /**
     * http的post形式与服务器交互
     * 发送消息(获取绑定房产信息)
     */
    private String userName;
    private String guid;//guid
    private String bindDate;//绑定时间
    private String houseID;//绑定的房间的Id
    private String hosingName;//房产名称
//    private String phone="";//手机号码
    private String projectID;//小区（项目）
    private String state;//状态
    private String userName1;//用户名
    private String whetherOwner;//是否业主
    private List<HousingBinds> housingBindsList = null;//绑定房产的信息列表
    private HousingBinds housingBinds = null;
    private String result_json = "";
    protected void sendMsg1() {
        try {
            System.out.println("请求绑定信息的用户："+userName);
            Get_Img_Str_From_Http get_img_str_from_http = new Get_Img_Str_From_Http();
            String result1 = get_img_str_from_http.getBindHouseInfo("getbindHouse",userName);
            System.out.println("************getbindHouse****"+result1);
            result_json = removeBOM(result1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        times = 0;
//        handler.postDelayed(runnable,300);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);

    }
}
