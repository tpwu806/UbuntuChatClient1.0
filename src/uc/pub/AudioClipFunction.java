package uc.pub;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URL;

public class AudioClipFunction {

	// 声音
	private static File file;
	private static File file2;
	private static URL cb;
	private static URL cb2;
	public static AudioClip aau;
	public static AudioClip aau2;

	public static void main(String[] args) {

		try {
			// 消息提示声音
			file = new File("sounds/eo.wav");
			cb = file.toURI().toURL();
			aau = Applet.newAudioClip(cb);
			aau.play();
			// 上线提示声音
			file2 = new File("sounds/ding.wav");
			cb2 = file2.toURI().toURL();
			aau2 = Applet.newAudioClip(cb2);
			aau2.play();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
