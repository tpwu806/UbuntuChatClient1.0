package uc.pub;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class UtilTool {
	// 声音
	private static File file;
	private static File file2;
	private static URL cb;
	private static URL cb2;
	public static AudioClip aau;
	public static AudioClip aau2;

	static{

		// 消息提示声音
		file = new File("sounds/eo.wav");
		try {
			cb = file.toURI().toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		aau = Applet.newAudioClip(cb);
		//aau.play();
		// 上线提示声音
		file2 = new File("sounds/ding.wav");
		try {
			cb2 = file2.toURI().toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		aau2 = Applet.newAudioClip(cb2);
		//aau2.play();
	}



	public static String getTimer() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

}
