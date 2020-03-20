package com.sunjy.secret.boot.Controller.File;

import com.alibaba.fastjson.JSONObject;
import com.sunjy.secret.boot.domain.ScanPool;
import com.sunjy.secret.boot.util.PoolCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.MapUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/QR")
public class QRcodeController {
    private static final Logger log = LoggerFactory.getLogger(QRcodeController.class);
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    Environment environment;

    public String getPort(){
        return environment.getProperty("local.server.port");
    }
    //二维码首页
    @GetMapping("/AttendClass")
    public ModelAndView AttendClass(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("/curriculum/AttendclassQRcode");
        InetAddress localHost = null;
        try {
            localHost = Inet4Address.getLocalHost();
        } catch (UnknownHostException e) {
            log.error(e.getMessage(),e);
        }
        String ip = localHost.getHostAddress();
        String http=ip+":"+getPort();
        request.setAttribute("http", http);
        try {
            String uuid = UUID.randomUUID().toString();
            request.setAttribute("uuid", uuid);
            ScanPool pool = new ScanPool();
            pool.setCreateTime(System.currentTimeMillis());
            Map<String, ScanPool> map = new HashMap<String, ScanPool>(1);
            map.put(uuid, pool);
            PoolCache.cacheMap.put(uuid, pool);
            pool = null;
        } catch (Exception e) {
            System.out.println("pc生成二维码登录"+e.getMessage());
        }
        return mav;
    }

    //判断二维码是否被扫描
    @GetMapping("/getpool")
    @ResponseBody
    public JSONObject Getpool(){
        String uuid=request.getParameter("uuid");
        System.out.println("检测[   " + uuid + "   ]是否登录");
        JSONObject obj = new JSONObject();
        ScanPool pool = null;
        if( !(PoolCache.cacheMap == null || PoolCache.cacheMap.isEmpty()) ) {
            pool = PoolCache.cacheMap.get(uuid);
        }

        try {
            if (pool == null) {
                // 扫码超时,进线程休眠
                Thread.sleep(10 * 1000L);
                obj.put("successFlag","0");
                obj.put("msg","该二维码已经失效,请重新获取");
            } else {
                // 使用计时器，固定时间后不再等待扫描结果--防止页面访问超时
                new Thread(new ScanCounter(uuid, pool)).start();

                //这里得到的ScanPool(时间靠前)和用户使用手机扫码后得到的不是一个,用户扫码后又重新更新了ScanPool对象,并重新放入了redis中,,所以这里要等待上面的计时器走完,才能获得最新的ScanPool
                boolean scanFlag = pool.getScanStatus();
                if (scanFlag) {
                    // 根据uuid从redis中获取pool对象,得到对应的sessionId,返给页面,通过js存cookie中
                    obj.put("successFlag","1");
                    JSONObject ob = new JSONObject();
                    ob.put("cname", "SESSIONKEY");
                    ob.put("cvalue", pool.getSession());
                    obj.put("data",ob);
                } else {
                    obj.put("successFlag","2");
                    obj.put("msg","等待扫描");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return obj;
    }


    @RequestMapping("/scanLogin")
    @ResponseBody
    public JSONObject scanLogin(String uuid){
        JSONObject obj = new JSONObject();
        ScanPool pool = null;
        if( !(PoolCache.cacheMap == null || PoolCache.cacheMap.isEmpty())){
            pool = PoolCache.cacheMap.get(uuid);
        }
        if (pool == null) {
            obj.put("successFlag","0");
            obj.put("msg","该二维码已经失效,请重新获取");
        } else {
            pool.setSession("123123");
            pool.scanSuccess();
            obj.put("msg","扫码成功!");
            obj.put("successFlag","1");

        }


        return obj;
    }




    @RequestMapping("/success")
//    @ResponseBody
    public String success(){
        System.out.println("扫码成功");
        return "/pdc/login";
    }


    @RequestMapping("/scan")
    @ResponseBody
    public String scan(){
        return "haha";
    }



}
//    //手机扫码接口(以id和token作为用户身份登录)
//    public String phoneScanLogin() {
//        DataResultInfo result = null;
//        ScanPool pool = null;
//        if(MapUtils.isNotEmpty(PoolCache.cacheMap)) pool = PoolCache.cacheMap.get(uuid);
//
//        try {
//            if (pool == null) {
//                result = DataResultInfo.getInstance().failure();
//                result.setMessage("该二维码已经失效,请重新获取");
//            } else {
//                if (StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(token)) {
//                    //根据id和token查询后台,获取用户信息userBean
//                    String redisToken = redisUtil.getRedis(RedisKeyConstant.APP_TOKEN+userId);
//                    if(redisToken != null && redisToken.equals(token)){
//                        UserBean userBean = userService.findByUserId(Long.valueOf(userId));
//                        if (userBean != null) {
//                            String sessionId = SessionConstant.SESSION_ID_PRE
//                                    + FormatUtils.password(userBean.getId()
//                                    .toString());
//                            Map<String, String> cookieSession = new HashMap<String, String>();
//                            cookieSession
//                                    .put(CookieConstant.SESSION_KEY, sessionId);
//                            // WrCookie.writeCookie(getResponse(),cookieSession);
//                            // 添加用户信息到redis
//                            boolean re = redisUtil.addUserInfo( RedisKeyConstant.SESSION + sessionId, BeanUtils.toBean(userBean, UserInfo.class));
//                            getSession().setAttribute( SessionConstant.USER_INFO_WEB, BeanUtils.toBean(userBean, UserInfo.class));
//                            getSession().setAttribute( DomainConstant.USER_CENTER_KEY, DomainConstant.USER_CENTER);
//                            pool.setSession(sessionId);
//
//                            pool.scanSuccess();
//                        }else{
//                            result = DataResultInfo.getInstance().failure();
//                            result.setMessage("用户信息获取异常!请稍后再试");
//                        }
//                    } else {
//                        result = DataResultInfo.getInstance().failure();
//                        result.setExtension("11", "用户身份信息失效,请重新登录!");
//                    }
//                } else {
//                    result = DataResultInfo.getInstance().failure();
//                    result.setMessage("请求参数有误!");
//                    return "error";
//                }
//                // 不能清除,否则conn方法得不到pool对象,不会进入线程休眠
//                // System.out.println("清除扫描过的uuid");
//                //PoolCache.cacheMap.remove(uuid);
//            }
//        } catch (Exception e) {
//            Log4jUtil.CommonLog.error("手机扫码 后访问 异常", e);
//        }
//
//        sendJsonMessage(result);
//        return null;
//    }


//    //扫码成功跳转页
//    public String success() {
//        String sessionId = WrCookie.getCookie(super.getRequest(), CookieConstant.SESSION_KEY);
//        UserInfo userInfo = redisUtil.getUserInfo(RedisKeyConstant.SESSION + sessionId);
//
//        super.getRequest().setAttribute(SessionConstant.USER_INFO_WEB, userInfo);
//
//        return SUCCESS;
//    }



    class ScanCounter implements Runnable {

        public Long timeout = 30 * 1000L;

        // 传入的对象
        private String uuid;
        private ScanPool scanPool;

        public ScanCounter(String p, ScanPool scanPool) {
            uuid = p;
            this.scanPool = scanPool;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notifyPool(uuid, scanPool);
        }

        public synchronized void notifyPool(String uuid, ScanPool scanPool) {
            if (scanPool != null) scanPool.notifyPool();
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public ScanPool getScanPool() {
            return scanPool;
        }

        public void setScanPool(ScanPool scanPool) {
            this.scanPool = scanPool;
        }
}
