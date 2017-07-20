package com.aojing.main_module.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aojing.main_module.R;
import com.aojing.model.ChargeTypeInfo;
import com.aojing.model.HousingAccount;
import com.aojing.model.HousingBinds;
import com.aojing.model.HousingInfo;
import com.aojing.model.Property;
import com.aojing.util.Get_Img_Str_From_Http;
import com.aojing.util.StringtoListUtil;
import com.aojing.util.Utils;
import com.readystatesoftware.viewbadger.BadgeView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 购物车的商品ListView布局的适配器设计
 * Created by wxw on 2016/11/15.
 */
public class MyAdapter_Main_Property extends BaseAdapter
{
    private int get_position = 0;//保存位置
    //提示是否删除的对话框
    private AlertDialog alertDialog = null;
    private AlertDialog.Builder builder = null;

    private Map<String,String> saveHouseInfo = new HashMap<String, String>();//保存房产ID和名称

    /**
     * 这个用来填充list
     */
//    private List<Property> list_property_info;
    /**
     * context上下文,用来获得convertView
     */
    private Context mContext;
    /**
     * 屏幕宽度,由于我们用的是HorizontalScrollView,所以按钮选项应该在屏幕外
     */
    private int mScreentWidth;
    //实体对象
    private BadgeView badgeView;
    private Property property = null;
    private Bitmap bitmap;
    private List<HousingBinds> houseList = null;
    private String houseStr = "";
    private String userName = "";

    private  List<HousingInfo> housingInfos = null;
    private String getHouseStr = "";
    private String houseName = "";
//    double money = 0;//总价
    /**
     * 构造方法
     * @param context
     * @param screenWidth
     */
    public MyAdapter_Main_Property(Context context, int screenWidth,List<HousingBinds> housingBindsList)
    {
        //初始化
        mContext = context;
        mScreentWidth = screenWidth;
        houseList = housingBindsList;
//        String finalBindHouse = null;
//        try {
//            String userName1 = Utils.readString(mContext,"key_save_userName");
//            String getBindHouseInfo = Utils.readString(context,userName1+"bindHousingInfo");
//            houseList = StringtoListUtil.String2SceneList(getBindHouseInfo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String userName1 = Utils.readString(mContext,"key_save_userName");
//        Utils.saveString(mContext,userName1+"bindHousingInfo",finalBindHouse);
    }

    @Override
    public int getCount()
    {
        return houseList==null ? 0 : houseList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        final ViewHolder holder;
        //如果没有设置过,初始化convertView
        if (convertView == null)
        {
            //获得设置的view
            convertView = LayoutInflater.from(mContext).inflate(R.layout.have_property_item, parent, false);

            //初始化holder
            holder = new ViewHolder();

            /**
             * 整个item的水平滚动层
             */
            holder.itemHorizontalScrollView = (HorizontalScrollView) convertView.findViewById(R.id.hsv);

            /**
             * 操作按钮层
             */
            holder.actionLayout = convertView.findViewById(R.id.ll_action);
            holder.delete = (Button) convertView.findViewById(R.id.delete);

            //把位置放到view中,这样点击事件就可以知道点击的是哪一条item
            holder.delete.setTag(position);
            holder.prperty_info = (TextView)convertView.findViewById(R.id.tv_room_address);
            holder.property_img = (ImageView)convertView.findViewById(R.id.property_img);
//            holder.tv_show_tag = (TextView)convertView.findViewById(R.id.tv_show_tag);
            //设置内容view的大小为屏幕宽度,这样按钮就正好被挤出屏幕外
            holder.normalItemContentLayout = convertView.findViewById(R.id.ll_property_content);
            ViewGroup.LayoutParams lp = holder.normalItemContentLayout.getLayoutParams();
            lp.width = mScreentWidth;

            convertView.setTag(holder);
        }
        else//有直接获得ViewHolder
        {
            holder = (ViewHolder) convertView.getTag();
        }

        //设置监听事件
        convertView.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_UP:

                        //获得ViewHolder
                        ViewHolder viewHolder = (ViewHolder) v.getTag();

                        //获得HorizontalScrollView滑动的水平方向值.
                        int scrollX = viewHolder.itemHorizontalScrollView.getScrollX();

                        //获得操作区域的长度
                        int actionW = viewHolder.actionLayout.getWidth();

                        //注意使用smoothScrollTo,这样效果看起来比较圆滑,不生硬
                        //如果水平方向的移动值<操作区域的长度的一半,就复原
                        if (scrollX < actionW / 2)
                        {
                            viewHolder.itemHorizontalScrollView.smoothScrollTo(0, 0);
                        }
                        else//否则的话显示操作区域
                        {
                            viewHolder.itemHorizontalScrollView.smoothScrollTo(actionW, 0);
                        }
                        return true;
                }
                return false;
            }
        });

        //这里防止删除一条item后,ListView处于操作状态,直接还原
        if (holder.itemHorizontalScrollView.getScrollX() != 0)
        {
            holder.itemHorizontalScrollView.scrollTo(0, 0);
        }

        //设置背景颜色,设置填充内容.
//        holder.normalItemContentLayout.setBackgroundResource(colors.get(position));
//        holder.shopping_basket_item_img.setImageBitmap();
//        housingInfos = new ArrayList<HousingInfo>();
//        getHouseStr = Utils.readString(mContext,projectID);
        try {
//            housingInfos = StringtoListUtil.String2SceneList(getHouseStr);
//            for (int i = 0; i < housingInfos.size(); i++) {
//                System.out.println("@@@@@@@@@@@@@@888@@@@@@@@@@@" + housingInfos.get(i).getHousingName());
//            }
//            if (!housingInfos.equals("")){
//
//                for (int i = 0; i < housingInfos.size(); i++) {
//                    saveHouseInfo.put(housingInfos.get(i).getHousingId(),housingInfos.get(i).getHousingName());
//
//                     (housingInfos.get(i).getHousingId().equals(houseList.get(position).getHouseID()))
//                     houseName = housingInfos.get(i).getHousingName();
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.prperty_info.setText(houseList.get(position).getHousingName());
        badgeView = new BadgeView(mContext,holder.prperty_info);
//        if (houseList.get(position).getHousingName().contains("1202")) {
//            badgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
            badgeView.setText("欠");
            badgeView.setBadgeBackgroundColor(Color.RED);
            badgeView.setTextSize(12);
            badgeView.show();
//        }
        //设置点击事件
        //点击item事件
        holder.normalItemContentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                projectID = houseList.get(position).getProjectID();//项目ID
                company = Utils.readString(mContext,projectID+"getCompany");//公司名称
                houseID = houseList.get(position).getHouseID();//房产ID
                System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&"+projectID+"//"+company+"//"+houseID);
//                if (housingName.contains("602")){
//                    badgeView.toggle();
//                }
                SEND_TYPE = 0;
                send();//获取收费项


                /****************测试*待改***********************/
//                Intent intent = new Intent(mContext, Bill_List.class);
//                Intent intent = new Intent(mContext, PropertyDetailActivity.class);
//                intent.putExtra("projectID",houseList.get(position).getProjectID());//项目ID
//                intent.putExtra("houseID",houseList.get(position).getHouseID());//房产ID
//                intent.putExtra("housingName",houseList.get(position).getHousingName());//房产名称
//                mContext.startActivity(intent);
            }
        });
        //点击一条item中的删除按钮
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog = null;
                builder = new AlertDialog.Builder(mContext);
                alertDialog = builder.setTitle("确定要解除绑定吗？")
                        .setMessage("解绑后，您将无法参与社区讨论，不再接收费用账单。")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                notifyDataSetChanged();
                                Toast.makeText(mContext,"你已经取消~",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                houseList.remove(position);
                                try {
                                    //解除绑定
                                    get_position = 0;
                                    userName = Utils.readString(mContext,"key_save_userName");
                                    SEND_TYPE = 1;
                                    projectID = houseList.get(position).getProjectID();//项目ID
                                    company = Utils.readString(mContext,projectID+"getCompany");//公司名称
                                    houseID = houseList.get(position).getHouseID();//房产ID
                                    get_position = position;
                                    System.out.println("&&&&&&&&&&&&&&6666&&&&&&&&&&&&&"+projectID+"//"+company+"//"+houseID+"position:"+get_position);
                                    send();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                notifyDataSetChanged();
                                int sum = getCount();
                                System.out.println("**************sum****00*****"+sum);
                                if (sum == 0){
                                    Utils.saveString(mContext,"property_sum",null);
                                }
                            }
                        }).create();
                alertDialog.show();
            }
        });
        return convertView;
    }

    /**
     * ViewHolder
     *@Title:
     *@Description:主要是避免了不断的view获取初始化.
     *@Author:justlcw
     *@Since:2013-11-22
     *@Version:
     */
    class ViewHolder
    {
        public HorizontalScrollView itemHorizontalScrollView;

        /**
         * 正常（没左侧滑）情况的item内容布局
         */
        public View normalItemContentLayout;
//        public TextView tv_show_tag;//显示欠费图标
        public TextView prperty_info;//房产信息
        public ImageView property_img;//房产图标
        /**
         * 删除操作按钮层
         */
        public View actionLayout;//操作层
        public Button delete;//删除的按钮

    }

    /**
     * http的post形式与服务器交互
     * 发送消息
     */

    private String houseID = "";
    private String projectID = "";
    private String company = "";
    private List<ChargeTypeInfo> chargeTypeList = null;//保存绑定成功的房产信息列表
    private ChargeTypeInfo chargeTypeInfo= null;
    private String chargeTypeStr = "";//房产信息列表转化的字符串

    private String chargeTypeMoney = "";//费用以及余额保存的字符串
    private List<HousingAccount> housingAccountList;//保存房产费用余额列表

    private int SEND_TYPE;
//    private static final int SEND_UNBIND = 1;
    /**
     * 发送
     */
    private void send() {
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                switch (SEND_TYPE){
                    case 0:
                        sendMsg(company,projectID,houseID);//发送请求交费类型
                        break;
                    case 1:
                        sendMsg1(company,projectID,houseID);//解绑的请求
                        break;
                }
                return flag;
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (!flag.equals("")) {
//                    tag = flag;
                    if (flag.equals("unBindSuccess")){
                        houseList.remove(get_position);
                        notifyDataSetChanged();
                        String finalBindHouse = null;
                        try {
                            finalBindHouse = StringtoListUtil.SceneList2String(houseList);
                            Utils.saveString(mContext,userName+"bindHousingInfo",finalBindHouse);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
//                flag = Utils.readString(Binding_Property_Check.this,"key_login");
            }

            @Override
            protected void onProgressUpdate(String... values) {
                super.onProgressUpdate(values);
            }
        }.execute();
    }
    /**
     * http的post形式与服务器交互
     * 发送消息解绑
     */
    private String flag = "";
    private String tag = "";
    protected void sendMsg1(String company,String projectID,String houseID) {
        try {
            //解除绑定
            Get_Img_Str_From_Http get_img_str_from_http = new Get_Img_Str_From_Http();
            String result = get_img_str_from_http.GetUnBindHouse("unBindHouse",company,projectID,houseID,userName);
//            String result = "";
            System.out.println("getUnBindHouse**********************发的参数****userName"+userName+"***************"+houseID+"#"+projectID+"#"+company);

            if (!result.equals("")) {
                //解析chargeTypes的Json数据
                JSONObject jsonObject = new JSONObject(result);
                flag = jsonObject.getString("unBindResult");

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void sendMsg(String company,String projectID,String houseID) {
        try {
            //获取交费类型和类型余额
            chargeTypeList = new ArrayList<ChargeTypeInfo>();
            housingAccountList = new ArrayList<HousingAccount>();
            Get_Img_Str_From_Http get_img_str_from_http = new Get_Img_Str_From_Http();
            String result = get_img_str_from_http.GetBillTypeMoney("getChargeType",houseID,projectID,company);
//            String result = "";
            System.out.println("getBalance**********************发的参数*******************"+houseID+"#"+projectID+"#"+company);
            String resultMoney = get_img_str_from_http.Temp("getBalance",houseID,projectID,company);
            System.out.println("********交费项****"+result);
            System.out.println("********交费项余额****"+resultMoney);
            if (!result.equals("")) {
                //解析chargeTypes的Json数据
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("chargeTypes");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = (JSONObject) jsonArray.get(i);
                    String guid = object.getString("guid");
//                    String projectID =object.getString("projectID");
//                    String companyID = object.getString("companyID");
                    String chargeTypeName = object.getString("chargeTypeName");
                    chargeTypeInfo = new ChargeTypeInfo(guid,chargeTypeName);
                    System.out.println("chargeTypeName===>" + chargeTypeName+",对应的guid:"+guid);
                    chargeTypeList.add(chargeTypeInfo);//把交费项添加到列表
                }
                //保存列表
                chargeTypeStr = StringtoListUtil.SceneList2String(chargeTypeList);
                Utils.saveString(mContext, company + projectID + houseID, chargeTypeStr);//以公司和项目为key保存
            }
            if (!resultMoney.equals("")) {
                //解析housingAccounts的Json数据
                JSONObject jsonObject1 = new JSONObject(resultMoney);
                JSONArray jsonArray1 = jsonObject1.getJSONArray("housingAccounts");
                HousingAccount housingAccount = null;
                for (int i = 0; i < jsonArray1.length(); i++) {
                    JSONObject object1 = (JSONObject) jsonArray1.get(i);
                    String charges_str = object1.getString("chargeTypeID");//费用类型
                    String chargesUnit = object1.getString("chargesUnit");//余额的单位
                    String guid_str = object1.getString("guid");//费用余额guid
                    String balance_str = object1.getString("balance");//费用余额
                    String housingName_str = object1.getString("houseID");//费用余额对应的房产
                    String projectID1 = object1.getString("projectID");//费用余额对应小区
                    String state_str = object1.getString("state");//费用余额状态
                    System.out.println("余额balance===>" + balance_str);
                    housingAccount = new HousingAccount(charges_str, balance_str, chargesUnit, guid_str, housingName_str, projectID1, state_str);
                    housingAccountList.add(housingAccount);
                }
                //保存房产账户相关
                chargeTypeMoney = StringtoListUtil.SceneList2String(housingAccountList);
                Utils.saveString(mContext, company + projectID + houseID + "housingAccounts", chargeTypeMoney);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}