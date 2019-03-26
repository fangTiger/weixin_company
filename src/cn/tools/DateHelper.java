package cn.tools;
 
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.goso.utility.DateTools;

public class DateHelper extends DateTools{

	private String minDate;
	private String maxDate;
	
	public String getMaxDate() {
		return maxDate;
	}

	public String getMinDate() {
		return minDate;
	}
	
	/**
	 * 获取多久前的时间
	 * @Title: getBeforeDate
	 * @data:2014-4-28下午12:12:23
	 * @author:zhaopengkui
	 *
	 * @param day
	 * @return
	 * @throws Exception
	 */
	public static String getBeforeDate(int day)throws Exception{
		Date date = new Date();
		String mixDate = "";
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		date = new Date(System.currentTimeMillis()-(0x5265c00L*day));
		mixDate = simpleDateFormat.format(date).toString();
		return mixDate;
	}
	
	/**
	 * 获取时间段内的每一天
	 * @Title: findDates
	 * @data:2014-3-26下午2:23:03
	 * @author:zhangshiyuan
	 *
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param formart 两个时间参数的格式
	 * @return
	 * @throws ParseException
	 */
	public static List<Date> findEveryDates(String startTime, String endTime,
			String formart) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(formart);
		Date dBegin = sdf.parse(startTime);
		Date dEnd = sdf.parse(endTime);
		List<Date> lDate = new ArrayList<Date>();
		lDate.add(dBegin);
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(dEnd);
		// 测试此日期是否在指定日期之后
		while (dEnd.after(calBegin.getTime())) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add(calBegin.getTime());
		}
		return lDate;
	}

	public static String formatDateToStr(int date){
		SimpleDateFormat simpleDateFormat4Time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat4Time.format(date * 1000);
	}
	public void setDateType(int type) {
		Date date = new Date();
		Date today = new Date(System.currentTimeMillis());
		String mixDate = "";
		String maxDate = "";
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
		maxDate = simpleDateFormat.format(today).toString();
		if(type==1){
			//3天内
			date = new Date(System.currentTimeMillis()-(0x5265c00L*(3-1)));
			mixDate = simpleDateFormat.format(date).toString();
		}else if(type==2){
			//一周内
			date = new Date(System.currentTimeMillis()-(0x5265c00L*(7-1)));
			mixDate = simpleDateFormat.format(date).toString();
		}else if(type==3){
			//一月内
			date = new Date(System.currentTimeMillis()-(0x5265c00L*(30-1)));
			mixDate = simpleDateFormat.format(date).toString();
		}else if(type==4){
			//三月内
			date = new Date(System.currentTimeMillis()-(0x5265c00L*(90-1)));
			mixDate = simpleDateFormat.format(date).toString();
		}
		this.maxDate = maxDate;
		this.minDate = mixDate;
		//return simpleDateFormat.format(date);
	}
	/**
	 * 3天内，一周内，3个月内
	 * @Title: setDateType
	 * @data:2012-9-17下午05:58:30
	 * @author:zxd
	 *
	 * @param type
	 * @param datetype
	 */
	public void setDateType(int type,String datetype) {
		Date date = new Date();
		Date today = new Date(System.currentTimeMillis());
		String mixDate = "";
		String maxDate = "";
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat(datetype);
		maxDate = simpleDateFormat.format(today).toString();
		if(type==1){
			//3天内
			date = new Date(System.currentTimeMillis()-(0x5265c00L*(3-1)));
			mixDate = simpleDateFormat.format(date).toString();
		}else if(type==2){
			//一周内
			date = new Date(System.currentTimeMillis()-(0x5265c00L*(7-1)));
			mixDate = simpleDateFormat.format(date).toString();
		}else if(type==3){
			//一月内
			date = new Date(System.currentTimeMillis()-(0x5265c00L*(30-1)));
			mixDate = simpleDateFormat.format(date).toString();
		}else if(type==4){
			//三月内
			date = new Date(System.currentTimeMillis()-(0x5265c00L*(90-1)));
			mixDate = simpleDateFormat.format(date).toString();
		}else if(type==0){
			date = new Date(System.currentTimeMillis()-(0x5265c00L*(0-1)));
			maxDate = simpleDateFormat.format(date).toString();
			mixDate = simpleDateFormat.format(today).toString();
		}
		this.maxDate = maxDate;
		this.minDate = mixDate;
	}
	/**
	 * 改变日志格式
	 * @param datetime
	 * @param oldFormat
	 * @param newFormat
	 * @return
	 */
	public static String formatDateString(String datetime,String oldFormat,String newFormat){
		Date date = null;
		try {
			if(datetime==null||datetime.equals("")){
				return "";
			}
			SimpleDateFormat formatter = new SimpleDateFormat(oldFormat);
			date=formatter.parse(datetime);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return getSpecifiedDate(date.getTime(), newFormat);
	}
	/**
	 * 根据指定的格式显示时间
	 * 
	 * @param time
	 *            long
	 * @param format
	 *            String
	 * author xieyan           
	 * @return String
	 */
	public static String getSpecifiedDate(long time, String format) {
		if (time == 0) {
			return "-";
		}

		Date timeVal = new Date(time);

		String strTime = null;
		try {
			SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
			strTime = simpledateformat.format(timeVal);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return strTime;
	}
	/**
	 * 格式 ： 输出指定格式类型的时间
	 * 
	 * @param formater
	 *            String
	 * author xieyan           
	 * @return String
	 */
	public static String getNowDate(String formater) {
		String strTime = null;
		try {
			SimpleDateFormat simpledateformat = new SimpleDateFormat(formater);
			strTime = simpledateformat.format(new Date());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return strTime;
	}
	public static void main(String args[]){
//	/*	DateHelper dh = new DateHelper();
//		dh.setDateType(0,"yyyy-MM-dd");
//		System.out.println(dh.maxDate);
//		System.out.println(dh.minDate);*/
//		DateHelper.formatStringToLong("2010-10-07 12:31:00",DateHelper.FMT_DATE_DATETIME3);
//		System.out.println(DateHelper.getDayEndSecond("2010-10-07"));
//		System.out.println("------"+("  aa  ".trim())+"------");
//		System.out.println(DateHelper.getCNTimeStr("2010-02-17"));
		//System.out.println(DateHelper.getSpecifiedDate(1389144800000l,DateHelper.FMT_DATE_DATETIME));
		System.out.println(DateHelper.getENTimeStr("2012-02-01"));
	}
	
	/**
	 * string转换成long时间
	 * @param dateString
	 * @param format
	 * @return
	 */
	public static long formatStringToLong(String dateString,String format){
		long result = 0l;
		SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
		try {
			 result = simpledateformat.parse(dateString).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return  result;
	}
	 public static String addDate(String strDate, int days) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date cDate = df.parse(strDate);
		GregorianCalendar gcalendar = new GregorianCalendar();
		gcalendar.setTime(cDate);
		gcalendar.add(Calendar.DATE, days);
		return df.format(gcalendar.getTime()).toString();
	}
	 
	 public static String getDayEndSecond(String dateTime){
		 //String result = "";
		 if(!dateTime.equals("")){
			 try {
				 dateTime = DateHelper.formatDateString(dateTime+" 23:59:59", DateHelper.FMT_DATE_DATETIME, FMT_DATE_DATETIME);
			} catch (Exception e) {
			}
		 }
		 
		 return dateTime;
	 }
	 
	public static Timestamp getTimestampNowDate() {
		Timestamp timestamp = null;
		try {
			timestamp = new Timestamp(new Date().getTime());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return timestamp;
	}
	 
    public static final String FMT_DATE_YYYY = "yyyy";
    public static final String FMT_DATE_YYMMDD = "yyMMdd";
    public static final String FMT_DATE_YYYYMMDD = "yyyyMMdd";
    public static final String FMT_DATE_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String FMT_DATE_YYYY_MM_DD2 = "yyyy/MM/dd";
    public static final String FMT_DATE_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String FMT_DATE_DATETIME3 = "yyyy-MM-dd HH:mm:sss";
    public static final String FMT_DATE_DATETIME2 = "yyyy-MM-dd HH:mm";
    public static final String FMT_DATE_DATETIME4 = "yyyy/MM/dd HH:mm";
    public static final String FMT_DATE_DATETIME_TIGHT = "yyyyMMddHHmmss";
    public static final String FMT_DATE_YY_MM = "yy-MM";
    public static final String FMT_DATE_YYYY_MM = "yyyy-MM";
    public static final String FMT_DATA_YY_MM_DD="yy-MM-dd";
    public static final String FMT_DATE_MM = "MM";
    public static final String FMT_DATE_DD = "dd";
    public static final String FMT_DATE_TIME = "HH:mm:ss";
    public static final String FMT_DATE_HH = "HH";

    public static Map<String, String> MONTH = new HashMap<String, String>();//月份英文缩写形式转换
	static{
		MONTH.put("01","Jan");MONTH.put("02","Feb");MONTH.put("03","Mar");MONTH.put("04","Apr");MONTH.put("05","May");MONTH.put("06","Jun");
		MONTH.put("07","Jul");MONTH.put("08","Aug");MONTH.put("09","Sep");MONTH.put("10","Oct");MONTH.put("11","Nov");MONTH.put("12","Dec");
	}
   public static Map<String, String> MONTHALL = new HashMap<String, String>();//月份全英文缩写形式转换
		static{
			MONTHALL.put("01","January");MONTHALL.put("02","February");MONTHALL.put("03","March");MONTHALL.put("04","April");MONTHALL.put("05","May");MONTHALL.put("06","June");
			MONTHALL.put("07","July");MONTHALL.put("08","August");MONTHALL.put("09","September");MONTHALL.put("10","October");MONTHALL.put("11","November");MONTHALL.put("12","December");
		}
		
    private static Map<String, String> MONTH2 = new HashMap<String, String>();//NOV-11
	static{
		MONTH2.put("Jan","01");MONTH2.put("Feb","02");MONTH2.put("Mar","03");MONTH2.put("Apr","04");MONTH2.put("May","05");MONTH2.put("June","06");
		MONTH2.put("July","07");MONTH2.put("Aug","08");MONTH2.put("Sep","09");MONTH2.put("Oct","10");MONTH2.put("Nov","11");MONTH2.put("Dec","12");
		MONTH2.put("Jul","07");MONTH2.put("Jun", "06");
	}
    
	/**
	 * 英文时间字符串转换(月为双位)
	 * 2012-02-01  --->  01-Feb
	 * @Title: getENTimeStr
	 * @data:2012-8-6上午09:36:16
	 * @author:liweidong
	 *
	 * @param str
	 * @return
	 */
    public static String getENTimeStr(String str){
    	String result = "";
    	if(str!=null && str.length()==10){
    		try {
        		String month = str.substring(5, 7);
        		result = str.substring(str.lastIndexOf("-")+1) + "-" + MONTH.get(month);
			} catch (Exception e) {
				result = str;
				e.printStackTrace();
			}
    	}
    	return result;
    }
    /**
     * 专函月份
     * @Title: getEnMonth
     * @data:2012-9-7下午03:23:59
     * @author:zxd
     *
     * @param Month APR-04
     * @return
     */
    public static String getEnMonth(String Month){
    	String result = "";
    	if(Month!=null && !Month.equals("")){
    		result =  MONTH2.get(Month);
    	}
    	return result;
    }
    public static String getEnMonth1(String Month){
    	String result = "";
    	if(Month!=null && !Month.equals("")){
    		result =  MONTH.get(Month);
    	}
    	return result;
    }
    
    public static String getEnMonthAll(String Month){
    	String result = "";
    	if(Month!=null && !Month.equals("")){
    		result =  MONTHALL.get(Month);
    	}
    	return result;
    }
    /**
     * 转换日期格式Jul.26.2011-2011-7-26
					
     * @Title: changeDate
     * @data:2012-9-7下午03:34:23
     * @author:zxd
     *
     * @param date
     * @return
     */
    public static String changeDate(String date){
    	String result = "";
    	if(date!=null && !date.equals("")){
    		//月份
    		String mm = date.substring(0, date.indexOf("."));
    		//天
    		String dd = date.substring(0, date.lastIndexOf("."));
    		dd = dd.substring(dd.indexOf(".")+1);
    		//year
    		String yy = date.substring(date.lastIndexOf(".")+1,date.length());
    		mm = getEnMonth(mm);
    		result = yy+"-"+mm+"-"+dd;
    	}
		return result;
    }
    
    /**
     * 时间字符串转换
     * 2012-08-01  --->  8月1日
     * @Title: getCNTimeStr
     * @data:2012-9-3下午02:04:17
     * @author:liweidong
     *
     * @param str
     * @return
     */
    public static String getCNTimeStr(String str){
    	String result = "";
    	if(str!=null && str.length()==10){
    		try {
        		String month = str.substring(5, 7);
        		String day = str.substring(8);
        		if(month.startsWith("0")){
        			month = month.substring(1);
        		}
        		if(day.startsWith("0")){
        			day = day.substring(1);
        		}
        		result = month + "月" + day + "日";
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	return result;
    }
    
    /**
     * 固定格式字符串转换
	 * 2012-08-21   ----->   Aug.21.2012
     * @Title: getTimeStr
     * @data:2012-9-10下午03:10:40
     * @author:liweidong
     *
     * @param str
     * @return
     */
    public static String getTimeStr(String str){
    	String result = "";
    	if(str!=null && str.length()==10){
    		try {
    			String month = str.substring(5, 7);
    			String day = str.substring(8);
    			String year = str.substring(0,4);
    			result = MONTH.get(month)+"."+day+"."+year;
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	return result;
    }
    public static String getTimeStr2(String str){
    	String result = "";
    	if(str!=null && str.length()==10){
    		try {
    			String month = str.substring(5, 7);
    			String day = str.substring(8);
    			String year = str.substring(0,4);
    			result = day+"-"+MONTH.get(month)+"-"+year;
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	return result;
    }
    /**
	 * 获取距离当前时间 days 天的时间
	 * @Title: setDate
	 * @data:2012-12-24下午04:09:50
	 * @author:liweidong
	 *
	 * @param days
	 * @param datetype
	 */
	public void setDate(int days,String datetype) {
		Date date = new Date();
		Date today = new Date(System.currentTimeMillis());
		String mixDate = "";
		String maxDate = "";
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat(datetype);
		maxDate = simpleDateFormat.format(today).toString();
		date = new Date(System.currentTimeMillis()-(0x5265c00L*days));
		mixDate = simpleDateFormat.format(date).toString();
		this.maxDate = maxDate;
		this.minDate = mixDate;
	}
}
