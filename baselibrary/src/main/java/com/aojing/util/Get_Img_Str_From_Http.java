package com.aojing.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * 获取服务器的资源的相关方法
 * Created by wxw on 2016/10/27.
 */
//获取图片
public class Get_Img_Str_From_Http {
    public Bitmap Get_Img_From_Http(String str_url_key,String ImgName){
        String str_url = BaseInfo.getIPAndPort()+str_url_key;
        URL url = null;
        Bitmap bitmap = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset","UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
            //发送数据到服务器
            dop.writeBytes("company="+URLEncoder.encode(BaseInfo.getCompany(),"utf-8"));
            dop.writeBytes("&ImgName="+ URLEncoder.encode(ImgName,"utf-8"));//发送用户名
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //接收服务器的返回的信息
            ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(urlConn.getInputStream(), 1024));
            byte[] bs = (byte[]) objectInputStream.readObject();
//            String filepath = "/mnt/sdcard/hello/bbb.jpg";
            if (bs == null) {
                System.out.println("************bs == null*********");
                bitmap = null;
            } else {
                System.out.println("************bs != null*********");
                bitmap = BitmapFactory.decodeByteArray(bs, 0, bs.length);
                }
//            File file = new File(filepath);
//            OutputStream output = new FileOutputStream(file);
//            BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
//            bufferedOutput.write(bs);
//            SavePicturetoSDcard savePicturetoSDcard = new SavePicturetoSDcard();
//            savePicturetoSDcard.savePicturetoFile(bitmap,filepath);
            objectInputStream.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    //把文件转换为流
    public byte[] fileToBytes(File file) {
        byte[] bs = null;
        try {
            InputStream input = new FileInputStream(file);
            bs = new byte[input.available()];
            input.read(bs);
            input.close();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    byteArrayOutputStream);
            objectOutputStream.writeObject(bs);
            bs = byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bs;
    }


    ///对象转字节流
    public static byte[] objectToBytes(Object object) {
        byte[] bytes = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    //注册
    public String  Get_Str_From_Http( String str_url_key,String phoneNo,String passWord){
        String str_url = BaseInfo.getIPAndPort()+str_url_key;
        String result1 = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset","UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
            //发送数据到服务器
//            dop.writeBytes("company="+URLEncoder.encode(BaseInfo.getCompany(),"utf-8"));
            dop.writeBytes("phone="+ URLEncoder.encode(phoneNo,"utf-8"));//发送注册的手机号
            dop.writeBytes("&password="+ URLEncoder.encode(passWord,"utf-8"));//发送注册的密码
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            if ((reline1=bufferedReader.readLine())!= null){
                //解析Json数据
//                    try {
//                            JSONObject customJson = new JSONObject(reline1.trim());
//                            String myvalue = customJson.getString("zmq");
//                            System.out.println("###########"+myvalue);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }

                result1 = reline1.trim();//一定要去空
                System.out.println("##############"+result1.trim() );
            }else {
                result1 = "";
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    return result1;
    }

    //设置密码信息
    public String  Set_Login_Password( String str_url_key,String phoneNo,String passWord){
        String str_url = BaseInfo.getIPAndPort()+str_url_key;
        String result1 = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset","UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
            //发送数据到服务器
            dop.writeBytes("company="+URLEncoder.encode(BaseInfo.getCompany(),"utf-8"));
            dop.writeBytes("&userID="+ URLEncoder.encode(phoneNo,"utf-8"));//发送用户名
            dop.writeBytes("&password="+ URLEncoder.encode(passWord,"utf-8"));//发送密码

            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            if ((reline1=bufferedReader.readLine())!= null){
                result1 = reline1;
            }else {
                result1 = "";
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result1;
    }

//获取登录的信息
    public String  Is_Login_Info(String str_url_key, String userName, String passWord){
        String str_url = BaseInfo.getIPAndPort()+str_url_key;
        String result1 = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset","UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
            //发送数据到服务器
//            dop.writeBytes("company="+URLEncoder.encode(BaseInfo.getCompany(),"utf-8"));
            dop.writeBytes("userName="+ URLEncoder.encode(userName,"utf-8"));//发送用户名
            dop.writeBytes("&password="+ URLEncoder.encode(passWord,"utf-8"));//发送密码
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            if ((reline1=bufferedReader.readLine())!= null){
                result1 = reline1.trim();
//                result1 = reline1.substring(reline1.indexOf("{"));
                System.out.println("**********result:"+result1.trim());
            }else {
                result1 = "";
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result1;
    }
    //获取登录的信息
    public String  getBindHouseInfo(String str_url_key, String userName){
        String str_url = BaseInfo.getIPAndPort()+str_url_key;
        String result1 = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset","UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
            //发送数据到服务器
//            dop.writeBytes("company="+URLEncoder.encode(BaseInfo.getCompany(),"utf-8"));
            dop.writeBytes("userName="+ URLEncoder.encode(userName,"utf-8"));//发送用户名
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            if ((reline1=bufferedReader.readLine())!= null){
                result1 = reline1.trim();
                System.out.println("**********result#########################:"+result1.trim());
            }else {
                result1 = "";
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result1;
    }

    public String ResponseResult(String str_url_key, Context context, List<Object> objectList){
        String str_url = BaseInfo.getIPAndPort()+str_url_key;
        String result1 = "";
        String reline1 = "";
        String channelId_str = Utils.readString(context,"channelId");
        String phone_str = Utils.readString(context,"key_phoneNo");
        System.out.println("*************channelId**************"+channelId_str+phone_str);

        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset","UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
            //发送数据到服务器
//            dop.writeBytes("&phone="+ URLEncoder.encode(phone_str,"utf-8"));//发送手机ID
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            if ((reline1=bufferedReader.readLine())!= null){
                result1 = reline1.trim();
//                result1 = reline1.substring(reline1.indexOf("{"));
                System.out.println("**********ResponseResult:"+result1.trim());
            }else {
                result1 = "";
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result1;
    }


    //发送登录的信息
    public String  PostChannelId(String str_url_key,Context context){
        String str_url = BaseInfo.getIPAndPort()+str_url_key;
        String result1 = "";
        String reline1 = "";
        String channelId_str = Utils.readString(context,"channelId");
        String phone_str = Utils.readString(context,"key_phoneNo");
        System.out.println("*************channelId**************"+channelId_str+phone_str);

        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset","UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
            //发送数据到服务器
            dop.writeBytes("channelId="+ URLEncoder.encode(channelId_str,"utf-8"));//发送手机ID
            dop.writeBytes("&phone="+ URLEncoder.encode(phone_str,"utf-8"));//发送手机ID
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            if ((reline1=bufferedReader.readLine())!= null){
//                result1 = reline1.trim();
                result1 = reline1.substring(reline1.indexOf("{"));
                System.out.println("**********result:"+result1.trim());
            }else {
                result1 = "";
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result1;
    }

    //发送收到信息的标识
    public String  PostSuccessTag(String str_url_key,Context context,String guid_str,String company){
        String str_url = BaseInfo.getIPAndPort()+str_url_key;
        String result1 = "";
        String reline1 = "";
        String phone_str = Utils.readString(context,"key_phoneNo");
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset","UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
            //发送数据到服务器
//            dop.writeBytes("successTag="+ URLEncoder.encode("success","utf-8"));//发送成功标识
            dop.writeBytes("company="+ URLEncoder.encode(company,"utf-8"));//发送company
            System.out.println("发送的company"+company);
            dop.writeBytes("&messageID="+ URLEncoder.encode(guid_str,"utf-8"));//发送消息的guid
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            if ((reline1=bufferedReader.readLine())!= null){
                result1 = reline1.trim();
            }else {
                result1 = "";
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result1;
    }

    public String  getParkInfo( String str_url_key,String company,String projectID){
        String str_url = BaseInfo.getIPAndPort()+str_url_key;
        String result1 = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset","UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
            //发送数据到服务器
            dop.writeBytes("company="+URLEncoder.encode(BaseInfo.getCompany(),"utf-8"));
            dop.writeBytes("&projectID="+ URLEncoder.encode(projectID,"utf-8"));
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            while ((reline1=bufferedReader.readLine())!= null){
                result1 = reline1.trim();
                System.out.println("#######parkInfo#######"+result1.trim() );
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result1;
    }

    //向服务器获取订单信息


    public String GetPayInfo(String str_url_key,String company,String projectID,String houseID,String payChargeMsg,String userName){
        String str_url = BaseInfo.getIPAndPort()+str_url_key;
        String result1 = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset","UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
            //发送数据到服务器
            dop.writeBytes("company="+ URLEncoder.encode(company,"utf-8"));//发送成功标识
            dop.writeBytes("&projectID="+ URLEncoder.encode(projectID,"utf-8"));//发送手机ID
            dop.writeBytes("&houseID="+ URLEncoder.encode(houseID,"utf-8"));//发送消息的guid
            dop.writeBytes("&userName="+ URLEncoder.encode(userName,"utf-8"));//发送消息的guid
            dop.writeBytes("&payChargeMsg="+ URLEncoder.encode(payChargeMsg,"utf-8"));//
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            if ((reline1=bufferedReader.readLine())!= null){
                result1 = reline1.substring(reline1.indexOf("{"));
                System.out.println("*****I am Here****"+result1);//去除前面两个字节的 bom 头
            }else {
                result1 = "";
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result1;
    }

    //发送支付结果到服务器
    public String postPayResult(String str_url_key,Object return_result){
        String str_url = BaseInfo.getIPAndPort()+str_url_key;
        String result1 = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset","UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
            //发送数据到服务器
            byte[] getResult = objectToBytes(return_result);
            dop.write(getResult);
//            dop.writeBytes("company="+ URLEncoder.encode(company,"utf-8"));//发送成功标识
//            dop.writeBytes("&projectID="+ URLEncoder.encode(projectID,"utf-8"));//发送手机ID
//            dop.writeBytes("&houseID="+ URLEncoder.encode(houseID,"utf-8"));//发送消息的guid
//            dop.writeBytes("&userName="+ URLEncoder.encode(userName,"utf-8"));//发送消息的guid
//            dop.writeBytes("&payChargeMsg="+ URLEncoder.encode(payChargeMsg,"utf-8"));//
//            dop.writeBytes("payResult="+ URLEncoder.encode(return_result,"utf-8"));//发送支付结果到服务器
//            dop.writeBytes("getZones="+ URLEncoder.encode(userName,"utf-8"));//发送手机ID
//            dop.writeBytes("&guid="+ URLEncoder.encode(guid_str,"utf-8"));//发送消息的guid
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            if ((reline1=bufferedReader.readLine())!= null){
//                result1 = reline1.substring(reline1.indexOf("{"));
                System.out.println("*****I am Here****"+reline1);//去除前面两个字节的 bom 头
                result1 =reline1;
            }else {
                result1 = "";
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result1;
    }
    //向服务器获取所有小区信息
    public String GetZones(String str_url_key){
        String str_url = BaseInfo.getIPAndPort()+str_url_key;
        String result1 = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset","UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
            //发送数据到服务器
//            dop.writeBytes("successTag="+ URLEncoder.encode("success","utf-8"));//发送成功标识
//            dop.writeBytes("getZones="+ URLEncoder.encode(userName,"utf-8"));//发送手机ID
//            dop.writeBytes("&guid="+ URLEncoder.encode(guid_str,"utf-8"));//发送消息的guid
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            if ((reline1=bufferedReader.readLine())!= null){
                result1 = reline1.substring(reline1.indexOf("{"));
                System.out.println("*****I am Here****"+result1);//去除前面两个字节的 bom 头
            }else {
                result1 = "";
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result1;
    }
    //向服务器获取某个小区的房间信息(可以获取交费项)
    public String GetAllRoom(String str_url_key,String propertyCompany_str) {
        String str_url = BaseInfo.getIPAndPort() + str_url_key;
        String result1 = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset", "UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
            //发送数据到服务器
            dop.writeBytes("company=" + URLEncoder.encode(propertyCompany_str, "utf-8"));//发送物业公司
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            if ((reline1 = bufferedReader.readLine()) != null) {
                result1 = reline1.substring(reline1.indexOf("{"));
                System.out.println("********RoomsInfo****" + result1);//去除前面两个字节的 bom 头
            } else {
                result1 = "";
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result1;
    }

/**
 * 把文件转化为Base64
 * */
    public static String encodeBase64File(String path) throws Exception {
        File  file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int)file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return Base64.encodeToString(buffer,Base64.DEFAULT);
    }
    /**
     * 把Base64转化为文件保存起来
     * */
    public void decoderBase64File(String base64Code,String savePath) throws Exception {
//byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
        byte[] buffer =Base64.decode(base64Code, Base64.DEFAULT);
        FileOutputStream out = new FileOutputStream(savePath);
        out.write(buffer);
        out.close();

    }


    public String postImg(String str_url_key,String img,String userName) {
        String str_url = BaseInfo.getIPAndPort() + str_url_key;
        String result = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset", "UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
//            OutputStream outputStream = urlConn.getOutputStream();
            //发送数据到服务器

//            String img =  encodeBase64File("/mnt/sdcard/okhttpImg/bad.jpg");
            dop.writeBytes("ImgStr=" + URLEncoder.encode(img, "utf-8"));//发送物业公司
            dop.writeBytes("&userName=" + URLEncoder.encode(userName, "utf-8"));//发送用户名
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            while ((reline1 = bufferedReader.readLine()) != null) {
                System.out.println("********IMG****" + reline1);//去除前面两个字节的 bom 头
                result = reline1;
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String postImgTemp(String str_url_key,String repairInfo) {
        String str_url = BaseInfo.getIPAndPort() + str_url_key;
        String result = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset", "UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
//            OutputStream outputStream = urlConn.getOutputStream();
            //发送数据到服务器

//            String img =  encodeBase64File("/mnt/sdcard/okhttpImg/bad.jpg");
            dop.writeBytes("repairImg=" + URLEncoder.encode(repairInfo, "utf-8"));//发送物业公司
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            while ((reline1 = bufferedReader.readLine()) != null) {
                System.out.println("********IMG****" + reline1);//去除前面两个字节的 bom 头
                result = reline1;
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public String postRepairInfo(String str_url_key,String repairInfo) {
        String str_url = BaseInfo.getIPAndPort() + str_url_key;
        String result = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
//            urlConn.setRequestProperty("Content-Type", "multipart/form-data");//设置meta参数
            urlConn.setRequestProperty("Charset", "UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
//            OutputStream outputStream = urlConn.getOutputStream();
            //发送数据到服务器
            dop.writeBytes("repairInfo=" + URLEncoder.encode(repairInfo, "utf-8"));//发送物业公司
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            while ((reline1 = bufferedReader.readLine()) != null) {
                System.out.println("********IMG****" + reline1);//去除前面两个字节的 bom 头
                result = reline1;
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public String getRepairInfo(String str_url_key,String company,String projectID,String userName){
        String str_url = BaseInfo.getIPAndPort() + str_url_key;
        String result = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
//            urlConn.setRequestProperty("Content-Type", "multipart/form-data");//设置meta参数
            urlConn.setRequestProperty("Charset", "UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
//            OutputStream outputStream = urlConn.getOutputStream();
            //发送数据到服务器
            dop.writeBytes("company=" + URLEncoder.encode(company, "utf-8"));//发送物业公司
            dop.writeBytes("&projectID=" + URLEncoder.encode(projectID, "utf-8"));//发送物业公司
            dop.writeBytes("&userName=" + URLEncoder.encode(userName, "utf-8"));//发送物业公司
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            while ((reline1 = bufferedReader.readLine()) != null) {
                System.out.println("********get repair Info****" + reline1);//去除前面两个字节的 bom 头
                result = reline1;
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getManyBroadcast(String str_url_key,String company,String projectID,String userName,int curPage){
        String str_url = BaseInfo.getIPAndPort() + str_url_key;
        String result = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
//            urlConn.setRequestProperty("Content-Type", "multipart/form-data");//设置meta参数
            urlConn.setRequestProperty("Charset", "UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
//            OutputStream outputStream = urlConn.getOutputStream();
            //发送数据到服务器
            dop.writeBytes("company=" + URLEncoder.encode(company, "utf-8"));//发送物业公司
            dop.writeBytes("&projectID=" + URLEncoder.encode(projectID, "utf-8"));//发送物业公司
            dop.writeBytes("&userName=" + URLEncoder.encode(userName, "utf-8"));//发送物业公司
            dop.writeBytes("&curpage=" + URLEncoder.encode(String.valueOf(curPage), "utf-8"));//发送物业公司
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            while ((reline1 = bufferedReader.readLine()) != null) {
                System.out.println("********get broadcast Info****" + reline1);//去除前面两个字节的 bom 头
                result = reline1;
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public String getBroadcast(String str_url_key,String company,String projectID,String userName){
        String str_url = BaseInfo.getIPAndPort() + str_url_key;
        String result = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
//            urlConn.setRequestProperty("Content-Type", "multipart/form-data");//设置meta参数
            urlConn.setRequestProperty("Charset", "UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
//            OutputStream outputStream = urlConn.getOutputStream();
            //发送数据到服务器
            dop.writeBytes("company=" + URLEncoder.encode(company, "utf-8"));//发送物业公司
            dop.writeBytes("&projectID=" + URLEncoder.encode(projectID, "utf-8"));//发送物业公司
            dop.writeBytes("&userName=" + URLEncoder.encode(userName, "utf-8"));//发送物业公司
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            while ((reline1 = bufferedReader.readLine()) != null) {
                System.out.println("********get broadcast Info****" + reline1);//去除前面两个字节的 bom 头
                result = reline1;
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public String getBroadcastDetail(String str_url_key,String company,String projectID,String userName,String broadcastID){
        String str_url = BaseInfo.getIPAndPort() + str_url_key;
        String result = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
//            urlConn.setRequestProperty("Content-Type", "multipart/form-data");//设置meta参数
            urlConn.setRequestProperty("Charset", "UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
//            OutputStream outputStream = urlConn.getOutputStream();
            //发送数据到服务器
            dop.writeBytes("company=" + URLEncoder.encode(company, "utf-8"));//发送物业公司
            dop.writeBytes("&projectID=" + URLEncoder.encode(projectID, "utf-8"));//发送物业公司
            dop.writeBytes("&userName=" + URLEncoder.encode(userName, "utf-8"));//发送物业公司
            dop.writeBytes("&broadcastID=" + URLEncoder.encode(broadcastID, "utf-8"));//发送物业公司
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            while ((reline1 = bufferedReader.readLine()) != null) {
                System.out.println("********get broadcast Info****" + reline1);//去除前面两个字节的 bom 头
                result = reline1;
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public String getBroadcastClick(String str_url_key,String company,String projectID,String userName,String broadcastID,String estimateType){
        String str_url = BaseInfo.getIPAndPort() + str_url_key;
        String result = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
//            urlConn.setRequestProperty("Content-Type", "multipart/form-data");//设置meta参数
            urlConn.setRequestProperty("Charset", "UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
//            OutputStream outputStream = urlConn.getOutputStream();
            //发送数据到服务器
            dop.writeBytes("company=" + URLEncoder.encode(company, "utf-8"));//发送物业公司
            dop.writeBytes("&projectID=" + URLEncoder.encode(projectID, "utf-8"));//发送物业公司
            dop.writeBytes("&userName=" + URLEncoder.encode(userName, "utf-8"));//发送物业公司
            dop.writeBytes("&broadcastID=" + URLEncoder.encode(broadcastID, "utf-8"));//发送物业公司
            dop.writeBytes("&estimateType=" + URLEncoder.encode(estimateType, "utf-8"));//发送物业公司
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            while ((reline1 = bufferedReader.readLine()) != null) {
                result = reline1;
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //删除评论
    public String deleteOneBroadcast(String str_url_key,String company,String projectID,String userName,String broadcastCommentID){
        String str_url = BaseInfo.getIPAndPort() + str_url_key;
        String result = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
//            urlConn.setRequestProperty("Content-Type", "multipart/form-data");//设置meta参数
            urlConn.setRequestProperty("Charset", "UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
//            OutputStream outputStream = urlConn.getOutputStream();
            //发送数据到服务器
            dop.writeBytes("company=" + URLEncoder.encode(company, "utf-8"));//发送物业公司
            dop.writeBytes("&projectID=" + URLEncoder.encode(projectID, "utf-8"));//发送物业公司
            dop.writeBytes("&userName=" + URLEncoder.encode(userName, "utf-8"));//发送物业公司
            dop.writeBytes("&broadcastCommentID=" + URLEncoder.encode(broadcastCommentID, "utf-8"));//发送物业公司
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            while ((reline1 = bufferedReader.readLine()) != null) {
                System.out.println("********get broadcast Info****" + reline1);//去除前面两个字节的 bom 头
                result = reline1;
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String sendBroadcastComment(String str_url_key,String company,String projectID,String userName,String broadcastID,String commentContent){
        String str_url = BaseInfo.getIPAndPort() + str_url_key;
        String result = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
//            urlConn.setRequestProperty("Content-Type", "multipart/form-data");//设置meta参数
            urlConn.setRequestProperty("Charset", "UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
//            OutputStream outputStream = urlConn.getOutputStream();
            //发送数据到服务器
            dop.writeBytes("company=" + URLEncoder.encode(company, "utf-8"));//发送物业公司
            dop.writeBytes("&projectID=" + URLEncoder.encode(projectID, "utf-8"));//发送物业公司
            dop.writeBytes("&userName=" + URLEncoder.encode(userName, "utf-8"));//发送物业公司
            dop.writeBytes("&broadcastID=" + URLEncoder.encode(broadcastID, "utf-8"));//发送物业公司
            dop.writeBytes("&commentContent=" + URLEncoder.encode(commentContent, "utf-8"));//发送物业公司
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            while ((reline1 = bufferedReader.readLine()) != null) {
                System.out.println("********get broadcast Info****" + reline1);//去除前面两个字节的 bom 头
                result = reline1;
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public String getImgs(String str_url_key,String company,String projectID,String userName,String broadcastID,String broadcastImgID){
        String str_url = BaseInfo.getIPAndPort() + str_url_key;
        String result = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
//            urlConn.setRequestProperty("Content-Type", "multipart/form-data");//设置meta参数
            urlConn.setRequestProperty("Charset", "UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
//            OutputStream outputStream = urlConn.getOutputStream();
            //发送数据到服务器
            dop.writeBytes("company=" + URLEncoder.encode(company, "utf-8"));//发送物业公司
            dop.writeBytes("&projectID=" + URLEncoder.encode(projectID, "utf-8"));//发送物业公司
            dop.writeBytes("&userName=" + URLEncoder.encode(userName, "utf-8"));//发送物业公司
            dop.writeBytes("&broadcastID=" + URLEncoder.encode(broadcastID, "utf-8"));//发送物业公司
            dop.writeBytes("&broadcastImgID=" + URLEncoder.encode(broadcastImgID, "utf-8"));//发送物业公司
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            while ((reline1 = bufferedReader.readLine()) != null) {
                System.out.println("********get broadcastImg Info****" + reline1);//去除前面两个字节的 bom 头
                result = reline1;
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public String sendMsg(String str_url_key,String key,String value){
        String str_url = BaseInfo.getIPAndPort() + str_url_key;
        String result = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset", "UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
//            OutputStream outputStream = urlConn.getOutputStream();
            //发送数据到服务器
            dop.writeBytes(key+"=" + URLEncoder.encode(value, "utf-8"));//发送物业公司
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            while ((reline1 = bufferedReader.readLine()) != null) {
                System.out.println("********get txt return Info****" + reline1);//去除前面两个字节的 bom 头
                result = reline1;
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public String getImg(String str_url_key,String userName,String ImgName) {
        String str_url = BaseInfo.getIPAndPort() + str_url_key;
        String result1 = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset", "UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
//            OutputStream outputStream = urlConn.getOutputStream();
            //发送数据到服务器
//            String img =  encodeBase64File("/mnt/sdcard/okhttpImg/test.jpg");
            dop.writeBytes("userName=" + URLEncoder.encode(userName, "utf-8"));//发送用户名
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            while ((reline1 = bufferedReader.readLine()) != null) {
                System.out.println("********IMG****" + reline1);//去除前面两个字节的 bom 头
                JSONObject object = new JSONObject(reline1);
                String img_str = object.getString("img");
                decoderBase64File(img_str, Environment.getExternalStorageDirectory()+"/"+userName+ImgName+".jpg");
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reline1;
    }
    //向服务器获取某个小区的房间信息(可以获取交费项)
    public String getResetPassWord(String str_url_key,String userName,String oldPwd,String newPwd) {
        String str_url = BaseInfo.getIPAndPort() + str_url_key;
        String result1 = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset", "UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
            //发送数据到服务器
            dop.writeBytes("userName=" + URLEncoder.encode(userName, "utf-8"));
            dop.writeBytes("&oldPwd=" + URLEncoder.encode(oldPwd, "utf-8"));
            dop.writeBytes("&newPwd=" + URLEncoder.encode(newPwd, "utf-8"));
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            if ((reline1 = bufferedReader.readLine()) != null) {
                result1 = reline1.substring(reline1.indexOf("{"));
                System.out.println("********RoomsInfo****" + result1);//去除前面两个字节的 bom 头
            } else {
                result1 = "";
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result1;
    }
    //向服务器获取某个小区的房间信息(可以获取交费项)
    public String getBindPhone(String str_url_key,String userName,String password,String newPhone) {
        String str_url = BaseInfo.getIPAndPort() + str_url_key;
        String result1 = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset", "UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
            //发送数据到服务器
            dop.writeBytes("userName=" + URLEncoder.encode(userName, "utf-8"));
            dop.writeBytes("&password=" + URLEncoder.encode(password, "utf-8"));
            dop.writeBytes("&phone=" + URLEncoder.encode(newPhone, "utf-8"));
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            if ((reline1 = bufferedReader.readLine()) != null) {
                result1 = reline1.substring(reline1.indexOf("{"));
                System.out.println("********RoomsInfo****" + result1);//去除前面两个字节的 bom 头
            } else {
                result1 = "";
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result1;
    }
    //向服务器获取某个小区的房间信息(可以获取交费项)
    public String resetRealName(String str_url_key,String userName,String newRealName) {
        String str_url = BaseInfo.getIPAndPort() + str_url_key;
        String result1 = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset", "UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
            //发送数据到服务器
            dop.writeBytes("userName=" + URLEncoder.encode(userName, "utf-8"));
            dop.writeBytes("&realName=" + URLEncoder.encode(newRealName, "utf-8"));
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            if ((reline1 = bufferedReader.readLine()) != null) {
                result1 = reline1.substring(reline1.indexOf("{"));
                System.out.println("********RoomsInfo****" + result1);//去除前面两个字节的 bom 头
            } else {
                result1 = "";
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result1;
    }
    //向服务器获取某个小区的房间信息(可以获取交费项)
    public String GetRoom(String str_url_key,String propertyCompany_str,String projectID) {
        String str_url = BaseInfo.getIPAndPort() + str_url_key;
        String result1 = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset", "UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
            //发送数据到服务器
            dop.writeBytes("company=" + URLEncoder.encode(propertyCompany_str, "utf-8"));//发送物业公司
//            dop.writeBytes("&project=" + URLEncoder.encode(project_str, "utf-8"));//发送小区名称
            dop.writeBytes("&projectID=" + URLEncoder.encode(projectID, "utf-8"));//发送小区guid
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            if ((reline1 = bufferedReader.readLine()) != null) {
                result1 = reline1.substring(reline1.indexOf("{"));
                System.out.println("********RoomsInfo****" + result1);//去除前面两个字节的 bom 头
            } else {
                result1 = "";
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result1;
    }
    //向服务器获取解绑结果
    public String GetUnBindHouse(String str_url_key,String company,String projectID,String houseID,String userName) {
        String str_url = BaseInfo.getIPAndPort() + str_url_key;
        String result1 = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset", "UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
            //发送数据到服务器
            dop.writeBytes("company=" + URLEncoder.encode(company, "utf-8"));//发送物业公司
            dop.writeBytes("&projectID=" + URLEncoder.encode(projectID, "utf-8"));//发送小区ID
            dop.writeBytes("&houseID=" + URLEncoder.encode(houseID, "utf-8"));//发送房产ID
            dop.writeBytes("&userName=" + URLEncoder.encode(userName, "utf-8"));//发送用户名
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            if ((reline1 = bufferedReader.readLine()) != null) {
                result1 = reline1;
                System.out.println("********RoomsInfo****" + result1);//去除前面两个字节的 bom 头
            } else {
                result1 = "";
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result1;
    }

    //向服务器确认业主信息
    public String GetBindHouseResult(String str_url_key,String phone,String realName,String userName,String housingNameId,String projectId,String company) {
        System.out.println("*********************666666666666666666********************"+housingNameId);
        String str_url = BaseInfo.getIPAndPort() + str_url_key;
        String result1 = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset", "UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
            //发送数据到服务器
            dop.writeBytes("company=" + URLEncoder.encode(company, "utf-8"));//发送房间名称
            dop.writeBytes("&projectID=" + URLEncoder.encode(projectId, "utf-8"));//发送房间名称
            dop.writeBytes("&houseID=" + URLEncoder.encode(housingNameId, "utf-8"));//发送房间名称
            dop.writeBytes("&realPhone=" + URLEncoder.encode(phone, "utf-8"));//发送手机
            dop.writeBytes("&userName=" + URLEncoder.encode(userName, "utf-8"));//发送用户名
            dop.writeBytes("&realName=" + URLEncoder.encode(realName, "utf-8"));//发送用户真实姓名
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            if ((reline1 = bufferedReader.readLine()) != null) {
                result1 = reline1.substring(reline1.indexOf("{"));
                System.out.println("********RoomsInfo****" + result1);//去除前面两个字节的 bom 头
            } else {
                result1 = "";
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
            return result1;
    }
    //向服务器确认业主信息
    public String GetBillTypeMoney(String str_url_key,String houseID,String project,String company) {
        String str_url = BaseInfo.getIPAndPort() + str_url_key;
        String result1 = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset", "UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
            //发送数据到服务器
            dop.writeBytes("company=" + URLEncoder.encode(company, "utf-8"));//发送公司名称
            dop.writeBytes("&projectID=" + URLEncoder.encode(project, "utf-8"));//发送项目名称
            dop.writeBytes("&houseID=" + URLEncoder.encode(houseID, "utf-8"));//发送房间名称
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            while ((reline1 = bufferedReader.readLine()) != null) {
                System.out.println("****111****MoneyInfo****" + reline1);//去除前面两个字节的 bom 头
                result1 = reline1;
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result1;
    }

    //向服务器确认业主信息
    public String GetBillTypeMoneyTest(String str_url_key,String houseID,String project,String company) {
        String str_url = BaseInfo.getIPAndPort() + str_url_key;
        String result1 = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset", "UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
            //发送数据到服务器
            dop.writeBytes("company=" + URLEncoder.encode(company, "utf-8"));//发送公司名称
            dop.writeBytes("&projectID=" + URLEncoder.encode(project, "utf-8"));//发送项目名称
            dop.writeBytes("&houseID=" + URLEncoder.encode(houseID, "utf-8"));//发送房间名称
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
//            ObjectInputStream objectInputStream = new ObjectInputStream(
//                    urlConn.getInputStream());
//            Map<String, String> map = (Map<String, String>) objectInputStream
//                    .readObject();
//            System.out.println("*******MAP****" + map);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
//            System.out.println("****000****MoneyInfo****" + bufferedReader.readLine());//去除前面两个字节的 bom 头
            if ((reline1 = bufferedReader.readLine()) != null) {
                System.out.println("****111****MoneyInfo****" + reline1);//去除前面两个字节的 bom 头
                result1 = reline1.substring(reline1.indexOf("{"));
                System.out.println("***222****MoneyInfo****" + result1);//去除前面两个字节的 bom 头
            } else {
                result1 = "";
            }
            for (int i = 0; (i < 3)&&(reline1==null||reline1.equals("")); i++) {
                reline1 = bufferedReader.readLine();
            }
            System.out.println("****111****MoneyInfo****" + reline1);//去除前面两个字节的 bom 头
            result1 = reline1.substring(reline1.indexOf("{"));
            System.out.println("***222****MoneyInfo****" + result1);//去除前面两个字节的 bom 头

            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result1;
    }

    public String Temp(String str_url_key,String houseID,String project,String company) {
        String str_url = BaseInfo.getIPAndPort() + str_url_key;
        String result1 = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset", "UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
            //发送数据到服务器
            dop.writeBytes("company=" + URLEncoder.encode(company, "utf-8"));//发送公司名称
            dop.writeBytes("&projectID=" + URLEncoder.encode(project, "utf-8"));//发送项目名称
            dop.writeBytes("&houseID=" + URLEncoder.encode(houseID, "utf-8"));//发送房间名称
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
//            System.out.println("###########bs############********************************************************************************");
//            ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(urlConn.getInputStream()));
//            JSONObject object = (JSONObject) objectInputStream.readObject();
////            byte[] bs = (byte[]) objectInputStream.readObject();
//            System.out.println("###########bs############"+object.toString());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

            for (int i = 0; (i < 3)&&(reline1==null||reline1.equals("")); i++) {
                reline1 = bufferedReader.readLine();
            }
            System.out.println("****111****MoneyInfo****" + reline1);//去除前面两个字节的 bom 头

            result1 = reline1.substring(reline1.indexOf("{"));
            System.out.println("***222****MoneyInfo****" + result1);//去除前面两个字节的 bom 头

            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result1;
    }
    //向服务器获取某个小区的费用项的信息
    public String PostPayInfo(String str_url_key,String company,String project,String housingName,String chargeType,String charge) {
        String str_url = BaseInfo.getIPAndPort() + str_url_key;
        String result1 = "";
        String reline1 = "";
        URL url = null;
        try {
            url = new URL(str_url);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);//设置输入流为字节流
            urlConn.setDoOutput(true);//设置输出流为字节流
            urlConn.setRequestMethod("POST");//post方式
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset", "UTF-8");//编码格式
            urlConn.connect();//连接服务器
            DataOutputStream dop = new DataOutputStream(urlConn.getOutputStream());
            //发送数据到服务器
            dop.writeBytes("company=" + URLEncoder.encode(company, "utf-8"));//发送公司
            dop.writeBytes("&projectID=" + URLEncoder.encode(project, "utf-8"));//发送小区名称
            dop.writeBytes("&houseID=" + URLEncoder.encode(housingName, "utf-8"));//发送房间
            dop.writeBytes("&chargeTypeID=" + URLEncoder.encode(chargeType, "utf-8"));//发送交费类型
            dop.writeBytes("&charge=" + URLEncoder.encode(charge, "utf-8"));//发送金额
            dop.flush();//发送，清空缓存
            dop.close();//关闭
            //获取服务器的内容
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            if ((reline1 = bufferedReader.readLine()) != null) {
                result1 = reline1.substring(reline1.indexOf("{"));
                System.out.println("********RoomsInfo****" + result1);//去除前面两个字节的 bom 头
            } else {
                result1 = "";
            }
            bufferedReader.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result1;
    }
}
