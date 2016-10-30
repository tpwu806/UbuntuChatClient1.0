package uc.pub.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public class DataTool {
	
	public static String getTimer() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

}
