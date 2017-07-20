package com.aojing.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.aojing.baselibrary.R;
import com.aojing.util.Utils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;

/**
 * 通用的适配器
 * Created by wxw on 2016/12/28.
 */
public abstract class MyAdapter<T> extends BaseAdapter {
    private static DisplayImageOptions options;
    private ArrayList<T> mData;
    private int mLayoutRes;           //布局id
    private int mScreenWidth;//屏幕宽度

    public MyAdapter() {
    }

    public MyAdapter(ArrayList<T> mData, int mLayoutRes) {
        this.mData = mData;
        this.mLayoutRes = mLayoutRes;
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



    }

    public MyAdapter(ArrayList<T> mData, int mLayoutRes, int mScreenWidth) {
        this.mData = mData;
        this.mLayoutRes = mLayoutRes;
        this.mScreenWidth = mScreenWidth;
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.bind(parent.getContext(), convertView, parent, mLayoutRes
                , position);
        bindView(holder, getItem(position));
        return holder.getItemView();
    }

    public abstract void bindView(ViewHolder holder, T obj);

    //添加一个元素
    public void add(T data) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.add(data);
        notifyDataSetChanged();
    }

    //往特定位置，添加一个元素
    public void add(int position, T data) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.add(position, data);
        notifyDataSetChanged();
    }

    public void remove(T data) {
        if (mData != null) {
            mData.remove(data);
        }
        notifyDataSetChanged();
    }

    public void remove(int position) {
        if (mData != null) {
            mData.remove(position);
        }
        notifyDataSetChanged();
    }

    public void clear() {
        if (mData != null) {
            mData.clear();
        }
        notifyDataSetChanged();
    }


    public static class ViewHolder {

        private SparseArray<View> mViews;   //存储ListView 的 item中的View
        private View item;                  //存放convertView
        private int position;               //游标
        private Context context;            //Context上下文

        //构造方法，完成相关初始化
        private ViewHolder(Context context, ViewGroup parent, int layoutRes) {
            mViews = new SparseArray<>();
            this.context = context;
            View convertView = LayoutInflater.from(context).inflate(layoutRes, parent, false);
            convertView.setTag(this);
            item = convertView;
        }

        //绑定ViewHolder与item
        public static ViewHolder bind(Context context, View convertView, ViewGroup parent,
                                      int layoutRes, int position) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder(context, parent, layoutRes);
            } else {
                holder = (ViewHolder) convertView.getTag();
                holder.item = convertView;
            }
            holder.position = position;
            return holder;
        }

        @SuppressWarnings("unchecked")
        public <T extends View> T getView(int id) {
            T t = (T) mViews.get(id);
            if (t == null) {
                t = (T) item.findViewById(id);
                mViews.put(id, t);
            }
            return t;
        }


        /**
         * 获取当前条目
         */
        public View getItemView() {
            return item;
        }

        /**
         * 获取条目位置
         */
        public int getItemPosition() {
            return position;
        }

        /**
         * 设置文字
         */
        public ViewHolder setText(int id, CharSequence text) {
            View view = getView(id);
            if (view instanceof TextView) {
                ((TextView) view).setText(text);
            }
            return this;
        }
        /**
         * 设置星级
         */
        public ViewHolder setRating(int id, float text) {
            View view = getView(id);
            if (view instanceof RatingBar) {
                ((RatingBar) view).setRating(text);
            }
            return this;
        }
        /**
         * 设置图片
         */
        public ViewHolder setImageResource(int id, int drawableRes) {
            View view = getView(id);
            if (view instanceof ImageView) {
                ((ImageView) view).setImageResource(drawableRes);
            } else {
                view.setBackgroundResource(drawableRes);
            }
            return this;
        }

        /**
         * 根据路径显示图片
         */
        public ViewHolder setImageByUrl(int id, String url) {
            View view = getView(id);
            if (view instanceof ImageView) {
                String userName = Utils.readString(context,"key_save_userName");
                String imgUrl6 = "file:///mnt/sdcard/"+userName+"getFromService.jpg";
                ImageLoader.getInstance().displayImage(imgUrl6, (ImageView) view,options);//路径、控件的位置、显示的方法
//                ((ImageView) view).setImageResource(drawableRes);
            } else {
                view.setBackgroundResource(R.drawable.wuye);
            }
            return this;
        }
        /**
         * 设置图片bitmap
         */
        public ViewHolder setImageBitmap(int id, Bitmap bitmap) {
            View view = getView(id);
            if (view instanceof ImageView) {
                ((ImageView) view).setImageBitmap(bitmap);
            } else {
//                view.setBackgroundResource(bitmap);
            }
            return this;
        }
        /**
         * 设置点击监听
         */
        public ViewHolder setOnClickListener(int id, View.OnClickListener listener) {
            getView(id).setOnClickListener(listener);
            return this;
        }
        /**
         * 设置监听事件
         */
        public ViewHolder setOnTouchListener(int id, View.OnTouchListener listener){
            getView(id).setOnTouchListener(listener);
            return this;
        }


        /**
         * 设置可见
         */
        public ViewHolder setVisibility(int id, int visible) {
            getView(id).setVisibility(visible);
            return this;
        }

        /**
         * 设置标签
         */
        public ViewHolder setTag(int id, Object obj) {
            getView(id).setTag(obj);
            return this;
        }

        //其他方法可自行扩展

    }

}
