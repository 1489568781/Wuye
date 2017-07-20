package com.aojing.main_module.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.aojing.main_module.R;
import com.aojing.model.HousingInfo;
import com.aojing.util.Get_Img_Str_From_Http;
import com.aojing.util.Utils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.readystatesoftware.viewbadger.BadgeView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;


/**
 * 我的页面
 * Created by wxw on 2017/1/15.
 */
public class Fragment_my extends Fragment {
    private int sum = 0;
    private LinearLayout ll_my_message;
    private TextView tv_my_msg;//我的消息图标
    private ToggleButton mTogBtn_fy;//费用消息提醒开关
    private ToggleButton mTogBtn_sq;//社区消息提醒开关
    private ToggleButton mTogBtn_xt;//系统消息提醒开关
    private RelativeLayout rl_setting;//设置
    private RelativeLayout rl_my_account;//我的个人信息
    private TextView my_info_phoneNo;//用户信息的手机号码显示
    private LinearLayout my_message;//我的消息按钮
    private TextView tv_realName;//真实姓名
    private TextView exit_login;//退出登录
    private RelativeLayout rl_reset_login_password;//修改密码
    private RelativeLayout rl_reset_realName;//修改姓名
    private RelativeLayout rl_reset_bind_phone;//修改绑定手机

    private ImageView userImg;
    private DisplayImageOptions options;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String userName = Utils.readString(getContext(),"key_save_userName");
        String isLogin = Utils.readString(getContext(),userName+"isLogin");
        if (isLogin.equals("yes")){//已经登录，直接到主页
//            Intent intent = new Intent(getContext(), MainActivity.class);
//            startActivity(intent);
//            finish();
             view = inflater.inflate(R.layout.mine_layout,container,false);
        }else { //没有登录则跳到登录界面
//            Intent intent1 = new Intent(getContext(),LoginActivity.class);
//            startActivity(intent1);
            view = null;
//            getActivity().finish();
        }
        return view;
    }
private BadgeView badgeView;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /**
         * 加载图片的显示方式
         * */
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.me_logo)
//                .cacheInMemory(true)//设置是否存在内存中
//                .cacheOnDisk(true)//设置是否缓存在SD卡中
//                .considerExifParams(true)//是否考虑jpg图像EXIF参数（旋转、翻转）
//                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以何种编码方式显示
//                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
                .displayer(new RoundedBitmapDisplayer(20))//设置圆角图片
                .displayer(new FadeInBitmapDisplayer(3000))//图片加载好之后渐入的动画时间
                .build();//构建


        userName = Utils.readString(getContext(),"key_save_userName");
        USER_ICON = new File(Environment.getExternalStorageDirectory(),userName+"getFromService"+".jpg");
        //获取未读的催单消息
        sum = Utils.readInteger(getContext(),"msg_to_read");
        ll_my_message = (LinearLayout)getActivity().findViewById(R.id.ll_my_message);
        userImg = (ImageView) getActivity().findViewById(R.id.userImg) ;
        String imgUrl6 = "file:///mnt/sdcard/"+userName+"getFromService.jpg";
//        ImageLoader.getInstance().displayImage(imgUrl6,userImg,options);//路径、控件的位置、显示的方法
//        userImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //发送头像
//                ShowPickDialog();
//            }
//        });
//        rl_reset_bind_phone = (RelativeLayout)getActivity().findViewById(R.id.rl_reset_bind_phone);
//        rl_reset_bind_phone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), Reset_Bind_Phone.class);
//                startActivity(intent);
//            }
//        });
//        rl_reset_realName = (RelativeLayout)getActivity().findViewById(R.id.rl_reset_realName);
//        rl_reset_realName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), Reset_RealName.class);
//                startActivity(intent);
//            }
//        });
//        rl_reset_login_password = (RelativeLayout)getActivity().findViewById(R.id.rl_reset_login_password);
//        rl_reset_login_password.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), Reset_Login_Password.class);
//                startActivity(intent);
//            }
//        });
        tv_my_msg = (TextView) getActivity().findViewById(R.id.tv_my_msg);
        badgeView = new BadgeView(getContext(),tv_my_msg);
        badgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
        badgeView.setText(String.valueOf(sum));
        badgeView.setBadgeBackgroundColor(Color.RED);
        if (sum>0) {
            badgeView.show();
        }
        //费用消息提醒开关以及点击事件
        mTogBtn_fy = (ToggleButton) getActivity().findViewById(R.id.mTogBtn_fy); // 获取到控件
        if (Utils.readString(getContext(),"notify_msg").equals("yes")){
            mTogBtn_fy.setChecked(true);
        }else {
            mTogBtn_fy.setChecked(false);
        }
        mTogBtn_fy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    //
//                    Toast.makeText(getContext(),"打开费用提醒",Toast.LENGTH_SHORT).show();
                    Utils.saveString(getContext(),"notify_msg","yes");
                }else{
                    //未选中
//                    Toast.makeText(getContext(),"关闭费用提醒",Toast.LENGTH_SHORT).show();
                    Utils.clearSaveInfo(getContext(),"notify_msg");
                }
            }

        });// 添加监听事件
        //社区消息提醒开关以及点击事件
        mTogBtn_sq = (ToggleButton) getActivity().findViewById(R.id.mTogBtn_sq); // 获取到控件
        mTogBtn_sq.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    //选中
//                    Toast.makeText(getContext(),"ON",Toast.LENGTH_SHORT).show();
                }else{
                    //未选中
//                    Toast.makeText(getContext(),"OFF",Toast.LENGTH_SHORT).show();
                }
            }
        });// 添加监听事件
        //系统消息提醒开关以及点击事件
        mTogBtn_xt = (ToggleButton) getActivity().findViewById(R.id.mTogBtn_xt); // 获取到控件
        mTogBtn_xt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    //选中
//                    Toast.makeText(getContext(),"ON",Toast.LENGTH_SHORT).show();
                }else{
                    //未选中
//                    Toast.makeText(getContext(),"OFF",Toast.LENGTH_SHORT).show();
                }
            }
        });// 添加监听事件
        //点击我的消息事件
        tv_my_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                badgeView.hide();
//                if (badgeView.isShown()) {
//                    badgeView.increment(10);
//                } else {
//                    badgeView.show();
//                }

//                TranslateAnimation anim = new TranslateAnimation(-100, 0, 0, 0);
//                anim.setInterpolator(new BounceInterpolator());
//                // 设置动画的持续时间
//                anim.setDuration(1000);
//                // 设置退出的移动动画
//                TranslateAnimation anim2 = new TranslateAnimation(0, -100, 0, 0);
//                anim2.setDuration(500);


//                badgeView.toggle(anim,anim2);
//                Utils.clearSaveInfo(getContext(),"msg_to_read");
////                badgeView.toggle(false);
//                Intent intent = new Intent(getActivity(), My_Message.class);
//                intent.putExtra("sum",sum);
//                startActivityForResult(intent,0);
            }
        });
        String userName = Utils.readString(getContext(),"key_save_userName");
        my_info_phoneNo = (TextView)getActivity().findViewById(R.id.my_info_phoneNo);//用户手机
        String phoneNo = Utils.readString(getActivity(),"key_phoneNo");
        my_info_phoneNo.setText(phoneNo);
        tv_realName = (TextView)getActivity().findViewById(R.id.tv_realName);//真实姓名
        String realName = Utils.readString(getContext(),userName+"realName");
        tv_realName.setText(realName);
        //点击退出登录
        exit_login = (TextView) getActivity().findViewById(R.id.exit_login);
        exit_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"退出登录",Toast.LENGTH_SHORT).show();
                String userName = Utils.readString(getContext(),"key_save_userName");

                Utils.clearSaveInfo(getContext(),userName+"isLogin");
                Utils.clearSaveInfo(getContext(),"key_save_userName");
//                Intent intent = new Intent(getContext(),LoginActivity.class);
//                startActivity(intent);
//                getActivity().finish();
            }
        });
    }

    /**
    * 选择提示对话框
     83.*/
   private void ShowPickDialog() {
       new AlertDialog.Builder(getContext())
       .setTitle("设置头像...")
       .setNegativeButton("相册", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int which) {
               dialog.dismiss();
               getPicFromLocal();
                }
           })
       .setPositiveButton("拍照", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
                getPicFromCamera();
                }
            }).show();
     }
    /**
     * 从本机相册获取图片
     */
    private void getPicFromLocal() {
        Intent intent = new Intent();
        // 获取本地相册方法一
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        intent.setType("image/*");
        //获取本地相册方法二
    intent.setAction(Intent.ACTION_PICK);
    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        "image/*");
        startActivityForResult(intent, CODE_PHOTO_REQUEST);
    }
    /**
     * 通过相机拍摄获取图片，
     * 并存入设置的路径中
     */
    private void getPicFromCamera() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        // 下面这句指定调用相机拍照后的照片存储的路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(USER_ICON));
        startActivityForResult(intent, CODE_CAMERA_REQUEST);
    }
    /**
     * 图片裁剪
     *
     * @param uri
     */
    private void photoClip(Uri uri) {
        // 调用系统中自带的图片剪裁
        Intent intent = new Intent();
        intent.setAction("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
    /*outputX outputY 是裁剪图片宽高
     *这里仅仅是头像展示，不建议将值设置过高
     * 否则超过binder机制的缓存大小的1M限制
     * 报TransactionTooLargeException
     */
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CODE_PHOTO_CLIP);
    }
    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    private byte[] bytes = null;
    private String img = "";
    private Bitmap bitmap = null;
    private void setImageToHeadView(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            bitmap = extras.getParcelable("data");
//            userImg.setImageBitmap(photo);
            System.out.println("#######pic#############");
//           Drawable drawable = new BitmapDrawable(photo);
           ByteArrayOutputStream stream = new ByteArrayOutputStream();
           bitmap.compress(Bitmap.CompressFormat.JPEG, 60, stream);
           bytes = stream.toByteArray();
           // 将图片流以字符串形式存储下来
            img = new String(Base64.encode(bytes,Base64.DEFAULT));
            send();

        }
    }


    private static final int RESULT_OK = 6;
    //相机拍摄的头像文件(本次演示存放在SD卡根目录下)
    private File USER_ICON = null;
    //请求识别码(分别为本地相册、相机、图片裁剪)
    private static final int CODE_PHOTO_REQUEST = 1;
    private static final int CODE_CAMERA_REQUEST = 2;
    private static final int CODE_PHOTO_CLIP = 3;
    private static final int RESULT_CANCELED = 0;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getActivity(), "取消", Toast.LENGTH_LONG).show();
            return;
        }
        switch (requestCode) {
            case RESULT_OK:
                if (data != null){
                    sum = 0;
                }
                break;
            case CODE_CAMERA_REQUEST:
                if (USER_ICON.exists()) {
                    photoClip(Uri.fromFile(USER_ICON));
                }
                break;
            case CODE_PHOTO_REQUEST:
                if (data != null) {
                    photoClip(data.getData());
                }
                break;
            case CODE_PHOTO_CLIP:
                if (data != null) {
                    setImageToHeadView(data);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
       }
            /**
             * 发送
             */
    private void send() {
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... params) {
                sendMsg1();
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String flag = jsonObject.getString("result");
                    if (flag.equals("success")){
                        userImg.setImageBitmap(bitmap);
//                        String imgUrl6 = "file:///mnt/sdcard/user_icon.jpg";
//                        ImageLoader.getInstance().displayImage(imgUrl6,userImg,options);//路径、控件的位置、显示的方法
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }.execute();
    }

    /**
     * http的post形式与服务器交互
     * 发送消息
     */

    private String userName = "";
    String result = "";
    private List<HousingInfo> housingInfoList = null;
    private HousingInfo housingInfo = null;
    private  String projectID = "";
    protected void sendMsg1() {
        try {
            Get_Img_Str_From_Http get_img_str_from_http = new Get_Img_Str_From_Http();
            result = get_img_str_from_http.postImg("setHeadImg",img,userName);//获取物业公司的所有房间
            System.out.println("*******postImg*******" + result);

            String a = get_img_str_from_http.getImg("getHeadImg",userName,"getFromService");
            System.out.println("*******getImg*******" + a);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



//}
}
