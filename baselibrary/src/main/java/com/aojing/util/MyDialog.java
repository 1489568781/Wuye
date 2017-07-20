package com.aojing.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.aojing.baselibrary.R;


/**
 *自定义弹框
 * Created by wxw on 2017/1/19.
 */
public class MyDialog extends Dialog implements View.OnClickListener {
    private Context context;//上下文
    private int layoutResID;//布局文件id
    private int [] listenedItems;//要监控的控件id
    private int gravity = 0;//显示位置
    double width_multiple;//弹框在占屏幕的宽度的倍数

    public MyDialog(Context context,int layoutResID,int[] listenedItems ,double width_multiple){
        super(context, R.style.Dialog_Theme);
        this.context = context;
        this.layoutResID = layoutResID;
        this.listenedItems = listenedItems;
        this.width_multiple = width_multiple;
    }
    public MyDialog(Context context,int layoutResID,int[] listenedItems ,int gravity,double width_multiple){
        super(context,R.style.Dialog_Theme);
        this.context = context;
        this.layoutResID = layoutResID;
        this.listenedItems = listenedItems;
        this.gravity = gravity;
        this.width_multiple = width_multiple;
    }

//    Activity context;
//    public TextView tv_bill_name;//改变金额 的项名称
//    public EditText et_change_money;//输入改变金额 的框
//    private TextView tv_confirm_change;//确认更改的按钮
//    private TextView tv_back;//返回按钮
//
//    String str_name = "";//显示的项名称（如：水费）
//    String str_money = "";//显示原来的交费金额
//    private View.OnClickListener mClickListener;
//
//    public MyDialog(Activity context) {
//        super(context);
//        this.context = context;
//    }
//
//    public MyDialog(Activity context, int themeResId, View.OnClickListener clickListener,String name,String money) {
//        super(context, themeResId);
//        this.context = context;
//        this.mClickListener = clickListener;
//        this.str_name = name;
//        this.str_money = money;
//    }
//
//    protected MyDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
//        super(context, cancelable, cancelListener);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //指定布局
//        this.setContentView(R.layout.change_money_dialog);



//        tv_back = (TextView)findViewById(R.id.tv_back);
//        tv_confirm_change = (TextView)findViewById(R.id.tv_confirm_change);
//        tv_bill_name = (TextView)findViewById(R.id.tv_bill_name);
//        tv_bill_name.setText(str_name);//设置交费项名称
//        et_change_money = (EditText)findViewById(R.id.et_change_money);
//        et_change_money.setHint(str_money);//设置提示金额
//        et_change_money.setText(str_money);//设置金额
        /**
         * 获取窗口对象及参数对象以修改对话框的布局设置, 可以直接调用getWindow(),表示获得这个Activity的Window
         * 对象,这样这可以以同样的方式改变这个Activity的属性.
         * */
        Window dialogWindow = getWindow();
        if (gravity == 0){
            dialogWindow.setGravity(Gravity.CENTER);
        }else {
            dialogWindow.setGravity(gravity);
        }

        dialogWindow.setWindowAnimations(R.style.bottom_menu_animation);
        setContentView(layoutResID);

        WindowManager windowManager = ((Activity)context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();//获取屏幕的宽高
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();//获取对话框当前的参数
//        layoutParams.height = (int)(display.getHeight()*0.4);
        layoutParams.width = (int) (display.getWidth()*width_multiple);//设置宽度为屏幕的0.8
        getWindow().setAttributes(layoutParams);
        setCanceledOnTouchOutside(true);//点击外部取消
        for(int id : listenedItems){
            findViewById(id).setOnClickListener(this);
        }
//        tv_confirm_change.setOnClickListener(mClickListener);
//        tv_back.setOnClickListener(mClickListener);
//        this.setCancelable(true);
    }
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        void OnItemClick(MyDialog dialog, View view);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        dismiss();//表示只要按任何一个控件的id,弹窗都会消失，不管是确定还是取消。
        listener.OnItemClick(this,v);
    }
}
