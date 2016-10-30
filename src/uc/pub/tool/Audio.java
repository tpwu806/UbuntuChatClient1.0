package uc.pub.tool;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Description: 
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public class Audio {
	// 声音
	private static File file;
	private static File file2;
	private static URL cb;
	private static URL cb2;
	public static AudioClip aau;
	public static AudioClip aau2;

	static {

		// 消息提示声音
		file = new File("sounds/eo.wav");
		try {
			cb = file.toURI().toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		aau = Applet.newAudioClip(cb);
		// aau.play();
		// 上线提示声音
		file2 = new File("sounds/ding.wav");
		try {
			cb2 = file2.toURI().toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		aau2 = Applet.newAudioClip(cb2);
		// aau2.play();
	}
}
