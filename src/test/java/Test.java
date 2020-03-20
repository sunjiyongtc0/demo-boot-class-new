import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

public static void main(String[] args) {
                long time1 = 1582013768000l;
                String result1 = new SimpleDateFormat("HH:mm").format(new Date(time1 ));
                System.out.println("10位数的时间戳（秒）--->Date:" + result1);
                String s="2020-02-18 17:36:45.0";
                String s1=s.substring(11,16);
                System.out.println(s1);

                ///////////////////////////////////////////////////////////////////////////////
                String url = "http://api.tianapi.com/txapi/ncovcity/index?key=94e5a697ecd81b3ce662b687c15cd965";
                String jsonResult = request(url,null);
                JSONObject jo =JSONObject.parseObject(jsonResult);
                String msg=jo.getString("msg");
                if("success".equals(msg)){
                        JSONArray ja=jo.getJSONArray("newslist");
                        System.out.println(ja);
                        int currentConfirmedCount=0; int deadCount=0;
                        int confirmedCount=0;int comment=0;
                        int suspectedCount=0;int curedCount=0;
                        for(int i=0;i<ja.size();i++){
                                JSONObject j=(JSONObject)ja.get(i);
                                currentConfirmedCount+=j.getString("currentConfirmedCount")==""?0:j.getInteger("currentConfirmedCount");
                                deadCount+=j.getString("deadCount")==""?0:j.getInteger("deadCount");
                                confirmedCount+= j.getString("confirmedCount")==""?0:j.getInteger("confirmedCount");
                                suspectedCount+= j.getString("suspectedCount")==""?0:j.getInteger("suspectedCount");
                                curedCount+= j.getString("curedCount")==""?0:j.getInteger("curedCount");
                        }
                        /**
                         * var SCREEN_ID={};
                         *         SCREEN_ID[410000]="河南";SCREEN_ID[500000]="重庆";SCREEN_ID[610000]="陕西";SCREEN_ID[370000]="山东";SCREEN_ID[330000]="浙江";
                         * */
                        System.out.println(currentConfirmedCount);
                        System.out.println(deadCount);//死亡
                        System.out.println(confirmedCount);//确诊
                        System.out.println(suspectedCount);
                        System.out.println(curedCount);//治愈
                        JSONObject j2=new JSONObject();
                        j2.put("diagnosed",confirmedCount);
                        j2.put("suspect",suspectedCount);
                        j2.put("cured",curedCount);
                        j2.put("death",deadCount);
                        Long l=(new Date()).getTime();
                        String s0="insert into t_t_xgbd_hour_report (time,count,detail)values("+l+",'"+j2.toJSONString()+"','"+jo.toJSONString()+"')";
                        System.out.println(s0);
//                j2.put("diagnosed",confirmedCount);
                }else{
                        System.out.println(url+"数据接口异常"+msg);
                }
                System.out.println(jsonResult);
                System.out.println(getTodayZeroPointTimestamps());

                long testTime=1584367818000l;
        Date date = new Date(testTime);
        DateFormat dateFormat = new SimpleDateFormat("HH");
        String format = dateFormat.format(date);
        System.out.println(format);


String stime="2020-03-15 00:02:53.0";
String subs=stime.substring(11,13);
        System.out.println(subs);

        }
        /**
         * @param urlAll
         *            :请求接口
         * @param httpArg
         *            :参数
         * @return 返回结果
         */
        public static String request(String httpUrl, String httpArg) {
                BufferedReader reader = null;
                String result = null;
                StringBuffer sbf = new StringBuffer();
//                httpUrl = httpUrl + "?" + httpArg;

                try {
                        URL url = new URL(httpUrl);
                        HttpURLConnection connection = (HttpURLConnection) url
                                .openConnection();
                        connection.setRequestMethod("GET");
                        InputStream is = connection.getInputStream();
                        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                        String strRead = null;
                        while ((strRead = reader.readLine()) != null) {
                                sbf.append(strRead);
                                sbf.append("\r\n");
                        }
                        reader.close();
                        result = sbf.toString();
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return result;
        }


        public static Long getTodayZeroPointTimestamps(){
                Long currentTimestamps=System.currentTimeMillis();
                Long oneDayTimestamps= Long.valueOf(60*60*24*1000);
                return currentTimestamps-(currentTimestamps+60*60*8*1000)%oneDayTimestamps;
        }
}
