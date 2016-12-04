package uc.pub.tool;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * @Description: 
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public class ImagesFunction {
	
	public static ImageIcon defaultHead = new ImageIcon("Image\\Head\\1_100.gif");
	public static ImageIcon blueBorder = new ImageIcon("Image\\client\\login's\\inputbox_hover.png");
	
	/**
	 * @Description:将图像转换为字节数组好用于传输
	 * @auther: wutp 2016年12月4日
	 * @param image
	 * @return
	 * @return byte[]
	 */
	public static byte[] getImage(String image) {
		// 当前程序所在路径
		String route = System.getProperty("user.dir") + "/";
		String headFile = route + image;
		byte[] head = createByte(new File(headFile));
		return head;

	}
	
	/**
	 * @Description:将图像转换为字节数组好用于传输
	 * @auther: wutp 2016年12月4日
	 * @param s
	 * @return
	 * @return byte[]
	 */
	public static byte[] createByte(File s) {
		BufferedImage bu;
		try {
			bu = ImageIO.read(s);
			ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
			try {
				ImageIO.write(bu, "png", imageStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return imageStream.toByteArray();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}
}
