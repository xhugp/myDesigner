package com.xhu.util;


import com.qiniu.util.Auth;
import com.xhu.dao.*;
import com.xhu.entity.*;
import com.xhu.exception.CommonException;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

/**
 * Created by gp on 2018/3/10.
 */
@Service
public class CommonUtil {

    @Autowired
    private DtypeDao dtypeDao;
    @Autowired
    private TtypeDao ttypeDao;
    @Autowired
    private RtypeDao rtypeDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private LeaseDao leaseDao;
    @Autowired
    private DemandDao demandDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ToolDao toolDao;
    @Autowired
    private OreqDao oreqDao;
    @Autowired
    private LReqDao lReqDao;
    @Autowired
    private SignDao signDao;
    @Autowired
    private VersionDao versionDao;

    private static List<Dtype> dtype_list = null;
    private static List<Ttype> ttype_list = null;
    private static List<Rtype> rtype_list = null;
    private static List<Role> role_list = null;

    private static Map<Integer,String> dtype_map = null;
    private static Map<Integer,String> ttype_map = null;
    private static Map<Integer,String> role_map = null;

    private static List<Integer> all_count_list = null;

    public static final String RANDOMCODEKEY= "RANDOMVALIDATECODEKEY";//放到session中的key
    private static String code;
    private static long time1;
    private static final String TOKEN = "e430024dc140b3845cee126dc2ae4b741f1145dc:cf0oRU6wzRydSEGzZF8x8AYazzI=:eyJkZWFkbGluZSI6MTUyMjIxODMyMSwiYWN0aW9uIjoiZ2V0IiwidWlkIjoiNjM4NDYzIiwiYWlkIjoiMTQyMzUyMSIsImZyb20iOiJmaWxlIn0=";
    private static final String CHNANEL = "cssr";
    private static final String REGIONHOST = "https://rest-hangzhou.goeasy.io";
    private static final String APPKEY = "BC-b904a79209154acf92c19edfcd2df323";

    private static final String ACCESSKEY = "ALdGxrDcLsmnR9BG4HuVEOXj1IP_zwtxvkBUQstA";
    private static final String SECRETKEY = "JxpUe8AnIMUsiADEktIHwQmhZdK5PXcTEb1BF0sY";
    private static final String BUCKET = "cssr";

    private String randString = "0123456789";//随机产生只有数字的字符串 private String
    //private String randString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";//随机产生只有字母的字符串
    //private String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";//随机产生数字与字母组合的字符串
    private int width = 95;// 图片宽
    private int height = 25;// 图片高
    private int lineSize = 40;// 干扰线数量
    private int stringNum = 4;// 随机产生字符数量

    private Random random = new Random();

    /**
     * 获得字体
     */
    private Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
    }

    /**
     * 获得颜色
     */
    private Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /**
     * 生成随机图片
     */
    public void getRandcode(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();// 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        g.fillRect(0, 0, width, height);//图片大小
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));//字体大小
        g.setColor(getRandColor(110, 133));//字体颜色
        // 绘制干扰线
        for (int i = 0; i <= lineSize; i++) {
            drowLine(g);
        }
        // 绘制随机字符
        String randomString = "";
        for (int i = 1; i <= stringNum; i++) {
            randomString = drowString(g, randomString, i);
        }
        //将生成的随机字符串保存到session中
        session.removeAttribute(RANDOMCODEKEY);
        session.setAttribute(RANDOMCODEKEY, randomString);
        code = randomString;
        time1 = new Date().getTime();
        g.dispose();
        try {
            // 将内存中的图片通过流动形式输出到客户端
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (Exception e) {
            throw new RuntimeException("获取验证码失败");
        }

    }

    /**
     * 绘制字符串
     */
    private String drowString(Graphics g, String randomString, int i) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
                .nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(randString
                .length())));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }

    /**
     * 绘制干扰线
     */
    private void drowLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * 获取随机的字符
     */
    public String getRandomString(int num) {
        return String.valueOf(randString.charAt(num));
    }

    public String validateCode(){
        if(new Date().getTime() - time1 > (60 * 1000)) {
            throw new CommonException("验证码过时");
        }
        return code;
    }

    /**
     * 根据id获取需求类型名称
     * @param id
     * @return
     */
    public String getDtypeName(int id){
        String name = getDtype_map().get(id);
        if(name == null){
            return "";
        }
        return name;
    }

    /**
     * 根据id获取工具类型名称
     * @param id
     * @return
     */
    public String getTtypeName(int id){
        String name = getTtype_map().get(id);
        if(name == null){
            return "";
        }
        return name;
    }

    /**
     * 根据id获取角色名称
     * @param id
     * @return
     */
    public String getRoleName(int id){
        String name = getRole_map().get(id);
        if(name == null){
            return "";
        }
        return name;
    }
    public List<Dtype> getDtype_list(){
        if(null == dtype_list){
            return dtypeDao.list();
        }
        return dtype_list;
    }

    public List<Ttype> getTtype_list(){
        if(null == ttype_list){
            return ttypeDao.list();
        }
        return ttype_list;
    }

    public List<Rtype> getRtype_list(){
        if(null == rtype_list){
            return rtypeDao.list();
        }
        return rtype_list;
    }

    public List<Role> getRole_list(){
        if(null == role_list){
            return roleDao.list();
        }
        return role_list;
    }

    public void addDtype(Dtype dtype){
        try {
            dtypeDao.add(dtype);
            dtype_list = dtypeDao.list();
        }catch (Exception e){
            throw new RuntimeException("内部错误");
        }
    }

    public void addTtype(Ttype ttype){
        try {
            ttypeDao.add(ttype);
            ttype_list = ttypeDao.list();
        }catch (Exception e){
            throw new RuntimeException("内部错误");
        }
    }

    public void addRtype(Rtype rtype){
        try {
            rtypeDao.add(rtype);
            rtype_list = rtypeDao.list();
        }catch (Exception e){
            throw new RuntimeException("内部错误");
        }
    }

    public void addRole(Role role){
        try {
            roleDao.add(role);
            role_list = roleDao.list();
        }catch (Exception e){
            throw new RuntimeException("内部错误");
        }
    }

    /**
     * 获得需求类型
     * @return
     */
    public Map<Integer,String> getDtype_map(){
        if (null == dtype_map) {
            dtype_map = new HashMap<Integer, String>();
            if(null == dtype_list) {
                dtype_list = getDtype_list();
            }
            for (Dtype dtype : dtype_list) {
                dtype_map.put(dtype.getId(), dtype.getName());
            }
        }
        return dtype_map;
    }

    public Map<Integer,String> getTtype_map(){
        if(null == ttype_map){
            ttype_map = new HashMap<Integer, String>();
            if(null == ttype_list) {
                ttype_list = getTtype_list();
            }
            for (Ttype ttype : ttype_list) {
                ttype_map.put(ttype.getId(), ttype.getName());
            }
        }
        return ttype_map;
    }

    public Map<Integer,String> getRole_map(){
        if(null == role_map){
            role_map = new HashMap<Integer, String>();
            if(null == role_list) {
                getRole_list();
            }
            for (Role role : role_list) {
                role_map.put(role.getId(), role.getName());
            }
        }
        return role_map;
    }


    /**
     * 将数据库的日期格式化输出
     * @param date
     * @param includetime
     * @return
     */
    public String formatStringDate(String date,boolean includetime){
        try {
            if(date == null){
                return null;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(date.substring(0,4) + "-");
            sb.append(date.substring(4,6) + "-");
            sb.append(date.substring(6,8));
            if(includetime){
                sb.append(" ");
                sb.append(date.substring(8,10) + ":");
                sb.append(date.substring(10,12));
            }
            return sb.toString();
        }catch (Exception e){
            throw new CommonException("日期格式化失败!" + e.getMessage());
        }

    }

    public String StringDateFormat(String stringDate){
        try {
            if("19900101000000".equals(stringDate) || "20990101000000".equals(stringDate)) {
                return stringDate;
            }
            long time = Long.parseLong(stringDate);
            return dateToString(new Date(time));
        }catch (Exception e){
            throw new CommonException("时间格式化错误"+e.getMessage());
        }

    }

    /**
     * 将date类型时间转化为数据库存储的格式
     * @return
     */
    public String dateToString(Date date){
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }

    /**
     * 获取所有数据的总数
     * @return 包含总数的list
     */
    public List<Integer> getAllCount(Integer companyId){
        try {
            all_count_list = new ArrayList<Integer>();
            all_count_list.add(demandDao.getCount(new Demand(companyId)));
            all_count_list.add(orderDao.getCount(new Order(companyId)));
            all_count_list.add(leaseDao.getCount(new Lease(companyId)));
            all_count_list.add(userDao.getCount(new User(companyId)));
            all_count_list.add(toolDao.getCount(new Tool(companyId)));
            LReq lReq = new LReq();
            lReq.setCompanyId(companyId);
            lReq.setlReqResult("0");

            Oreq oreq = new Oreq();
            oreq.setCompanyId(companyId);
            oreq.setOreqResult("0");
            all_count_list.add(oreqDao.getCount(oreq));
            all_count_list.add(lReqDao.getCount(lReq));
            return all_count_list;
        }catch (Exception e){
            throw new CommonException("查询所以信息数量失败" + e.getMessage());
        }
    }

    public String getToolStateName(String state){
        if("1".equals(state)){
            return "闲置中";
        }
        if("2".equals(state)){
            return "出租中";
        }
        return "";
    }

    public String getToken(){
        return TOKEN;
    }

    public void sendMessage(String mes){
        try {
            GoEasy goEasy = new GoEasy(REGIONHOST,APPKEY);
            goEasy.publish(CHNANEL, mes);
        }catch (Exception e){
            throw new CommonException("消息推送异常");
        }

    }

    public String getUpToken(){
        try {
            Auth auth = Auth.create(ACCESSKEY, SECRETKEY);
            String upToken = auth.uploadToken(BUCKET);
            return upToken;
        }catch (Exception e){
            throw new CommonException("获取上传图片token异常");
        }
    }

    /**
     * 获取过去或者未来 任意天内的日期数组
     * @param intervals      intervals天内
     * @return              日期数组
     */
    public  ArrayList<String> getDay(int intervals ) {
        ArrayList<String> pastDaysList = new ArrayList<>();
        ArrayList<String> fetureDaysList = new ArrayList<>();
        for (int i = 0; i <intervals; i++) {
            pastDaysList.add(getPastDate(intervals-i));
            fetureDaysList.add(getFetureDate(i));
        }
        return pastDaysList;
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public  String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

    /**
     * 获取未来 第 past 天的日期
     * @param past
     * @return
     */
    public  String getFetureDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

    public Map<String,List> getCreateMes(Integer companyId){
        Map<String,List> map = new HashMap<String,List>();
        List<String> list = getDay(7);
        map.put("days",list);
        map.put("demands",getCreateDemand(companyId));
        map.put("orders",getCreateOrder(companyId));
        map.put("leases",getCreateLease(companyId));
        map.put("signs",getCreateSign(companyId));
        return map;
    }

    public  List<Integer> getCreateDemand(Integer companyId) {
        ArrayList<Integer> demands_list = new ArrayList<>();
        String fromTime;
        String toTime;
        Demand demand = new Demand(companyId);
        for(int i = 7;i>0;i--){
            fromTime = getPastDate(i).replace("-","")+"000000";
            toTime = getPastDate(i).replace("-","")+"235959";
            demand.setFromTime(fromTime);
            demand.setToTime(toTime);
            demands_list.add(demandDao.getCount(demand));
        }
        return demands_list;
    }

    public  List<Integer> getCreateOrder(Integer companyId) {
        ArrayList<Integer> list = new ArrayList<>();
        String fromTime;
        String toTime;
        Order order = new Order(companyId);
        for(int i = 7;i>0;i--){
            fromTime = getPastDate(i).replace("-","")+"000000";
            toTime = getPastDate(i).replace("-","")+"235959";
            order.setFromTime(fromTime);
            order.setToTime(toTime);
            list.add(orderDao.getCount(order));
        }
        return list;
    }

    public  List<Integer> getCreateLease(Integer companyId) {
        ArrayList<Integer> list = new ArrayList<>();
        String fromTime;
        String toTime;
        Lease lease = new Lease(companyId);
        for(int i = 7;i>0;i--){
            fromTime = getPastDate(i).replace("-","")+"000000";
            toTime = getPastDate(i).replace("-","")+"235959";
            lease.setFromTime(fromTime);
            lease.setToTime(toTime);
            list.add(leaseDao.getCount(lease));
        }
        return list;
    }

    public  List<Integer> getCreateSign(Integer companyId) {
        ArrayList<Integer> list = new ArrayList<>();
        String fromTime;
        String toTime;
        Sign sign = new Sign(companyId);
        for(int i = 7;i>0;i--){
            fromTime = getPastDate(i).replace("-","")+"000000";
            toTime = getPastDate(i).replace("-","")+"235959";
            sign.setFromTime(fromTime);
            sign.setToTime(toTime);
            list.add(signDao.getCount(sign));
        }
        return list;
    }

    /**利用MD5进行加密*/
    public String EncoderByMd5(String str){
        try {
            //确定计算方法
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            //加密后的字符串
            String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
            return newstr;
        }catch (Exception e){
            throw new CommonException("密码加密失败" + e.getMessage());
        }
    }


    public String getOrderState(String orderState){
        if("1".equals(orderState)){
            return "处理中";
        }else if("2".equals(orderState)){
            return "已结束";
        }else if("3".equals(orderState)){
            return "中断";
        }else if ("4".equals(orderState)){
            return "已取消";
        }else{
            return "";
        }
    }

    public String getLeaseState(String leaseState){
        if("1".equals(leaseState)){
            return "出租中";
        }else if("2".equals(leaseState)){
            return "已结束";
        }else{
            return "";
        }
    }

    public Version getVersion(){
        Version version = versionDao.getVersion();
        version.setUptime(formatStringDate(version.getUptime(),false));
        return version;
    }
}
