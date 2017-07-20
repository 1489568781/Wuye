package com.aojing.main_module.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aojing.main_module.R;
import com.aojing.model.BroadcastComment;
import com.aojing.util.Get_Img_Str_From_Http;
import com.aojing.util.Utils;

import java.util.List;


/**
 * 与客服聊天的适配器
 * Created by wxw on 2017/2/27.
 */
public class MyAdapter_Broadcast_Detail extends BaseAdapter{
    //提示是否删除的对话框
    private AlertDialog alertDialog = null;
    private AlertDialog.Builder builder = null;

    List<BroadcastComment> broadcastCommentList ;
    Context context;
    public MyAdapter_Broadcast_Detail(List<BroadcastComment> broadcastCommentList, Context context) {
        this.broadcastCommentList = broadcastCommentList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return broadcastCommentList.size();

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        //返回值需要从0开始，对应数据所要加载的对应布局标识
        return broadcastCommentList.get(position).getType();
    }

    @Override
    public int getViewTypeCount() {
        //不同布局的种类数量
        return 3;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        //获取当前数据的类型
        int type = getItemViewType(position);
        final BroadcastComment broadcastComment = broadcastCommentList.get(position);
        System.out.println("All count:"+getCount());
            switch (type){
                case BroadcastComment.COMMENT:
                    ViewHolder_Comment viewHolder_comment = null;
                    if (convertView==null) {
                        //加载自己消息显示的布局
                        viewHolder_comment = new ViewHolder_Comment();
                        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.broadcast_item_nine_grid_comment, parent, false);
                        viewHolder_comment.icon = ((ImageView) convertView.findViewById(R.id.iv_broadcast_comment_head_img));
                        viewHolder_comment.name = ((TextView) convertView.findViewById(R.id.tv_broadcast_comment_name));
                        viewHolder_comment.comment = ((TextView) convertView.findViewById(R.id.tv_broadcast_comment_content));
                        viewHolder_comment.tv_delete_comment = (TextView)convertView.findViewById(R.id.tv_delete_comment);
                        convertView.setTag(viewHolder_comment);  //控件应用与控件绑定
                    }else {
                        viewHolder_comment = (ViewHolder_Comment) convertView.getTag();
                    }
                    //设置数值
                    viewHolder_comment.name.setText(broadcastComment.getComment_userName());
                    viewHolder_comment.icon.setImageResource(R.drawable.head);
                    viewHolder_comment.comment.setText(broadcastComment.getCommnet_content());
                    //删除事件
                     userName = Utils.readString(context,"key_save_userName");
                    if (userName.equals(broadcastComment.getComment_userName())){
                        viewHolder_comment.tv_delete_comment.setVisibility(View.VISIBLE);
                    }else {
                        viewHolder_comment.tv_delete_comment.setVisibility(View.GONE);
                    }
                    viewHolder_comment.tv_delete_comment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            Toast.makeText(context,"删除"+position,Toast.LENGTH_SHORT).show();
                            alertDialog = null;
                            builder = new AlertDialog.Builder(context);
                            alertDialog = builder.setTitle("确定要解除该评论吗？")
                                    .setMessage("删除后，该评论将不再存在。")
                                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            notifyDataSetChanged();
                                            Toast.makeText(context,"你已经取消~",Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            try {
                                                //删除评论
//                                                broadcastCommentList.remove(position);
                                                getPosition = 0;
                                                getPosition =position;
                                                broadcastCommentID =broadcastCommentList.get(position).getBroadcastCommentID();
                                                userName = Utils.readString(context,"key_save_userName");
                                                projectID = broadcastCommentList.get(position).getProjectID();//项目ID
                                                company = broadcastCommentList.get(position).getCompany();//公司名称
                                                send();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }).create();
                            alertDialog.show();
                        }
                    });


                    break;
                case BroadcastComment.BAD:
                    //加载朋友消息显示的布局
                    ViewHolder vHolder = null;
                    if (convertView==null) {
                        vHolder = new ViewHolder();
                        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lv_broadcast_comment_item, parent, false);
                        vHolder.name = ((TextView) convertView.findViewById(R.id.tv_comment_userName));
                        convertView.setTag(vHolder);  //控件应用与控件绑定
                    }else {
                        vHolder = (ViewHolder) convertView.getTag();
                    }
                    //设置数值
                    if (broadcastComment.getComment_userName().equals("")){
                        vHolder.name.setVisibility(View.GONE);
                    }else {
                        vHolder.name.setText(broadcastComment.getComment_userName()+"觉得很漏~");
                    }

                    break;
                case BroadcastComment.GOOD:
                    //加载朋友消息显示的布局
                    ViewHolder vHolder1 = null;
                    if (convertView==null) {
                        vHolder1 = new ViewHolder();
                        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lv_broadcast_comment_item, parent, false);
                        vHolder1.name = ((TextView) convertView.findViewById(R.id.tv_comment_userName));
                        convertView.setTag(vHolder1);  //控件应用与控件绑定
                    }else {
                        vHolder1 = (ViewHolder) convertView.getTag();
                    }
                    //设置数值
                    if (broadcastComment.getComment_userName().equals("")){
                        vHolder1.name.setVisibility(View.GONE);
                    }else {
                        vHolder1.name.setText(broadcastComment.getComment_userName()+"觉得很赞~");
                    }

                    break;
            }
        return convertView;
    }

    /**
     * Drawable转换成Bitmap
     * */
    public static Bitmap drawableToBitmap(Drawable drawable) {

        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);

        //canvas.setBitmap(bitmap);

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        drawable.draw(canvas);

        return bitmap;

    }

    class ViewHolder_Comment{
        ImageView icon;
        TextView name;
        TextView comment;
        TextView tv_delete_comment;//删除按钮
    }

    class ViewHolder{
        TextView name;
    }


    /**
     * 发送
     */
    private int getPosition = 0;
    private String result = "";
    private void send() {
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... params) {
                sendMsg();
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                    if (s.equals("success")){
                        System.out.println("*******deleteBroadcast*******success!!!!");
                        broadcastCommentList.remove(getPosition);
                        notifyDataSetChanged();
                        Toast.makeText(context,"删除评论成功~",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context,"删除评论失败！",Toast.LENGTH_SHORT).show();
                    }
                }
        }.execute();
    }


    private String userName = "";
    private  String projectID = "";
    private String company = "";
    private String broadcastCommentID = "";
    protected void sendMsg() {
        try {
            Get_Img_Str_From_Http get_img_str_from_http = new Get_Img_Str_From_Http();
            result = get_img_str_from_http.deleteOneBroadcast("deleteSingleComment ",company,projectID,userName,broadcastCommentID );//获取物业公司的所有房间
            System.out.println("*******deleteBroadcast*******" + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
