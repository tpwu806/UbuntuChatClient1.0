package uc.pub.tool;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


/**
 * @Description: 
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public class ChangeImage {
    
    /*
     * @version 将图像更改为指定大小的圆角图像
     * @return BufferedImage
     */
    public static BufferedImage roundedCornerImage(ImageIcon image,
    		int width,
            int height,
    		int cornerRadius) {
    	
    	return roundedCornerImage(image,
    			width,
    			height,
    			width,
    			height,
    			0,
    			0,
    			cornerRadius
    			);
    }
    
    /*
     * @version 将图像更改为指定大小的圆角图像
     * @return ImageIcon
     */
    public static ImageIcon roundedCornerIcon(ImageIcon image,
            int width,
            int height,
    		int cornerRadius){
    		
    	image = new ImageIcon(roundedCornerImage(image,
    			width,
    			height,
    			width,
    			height,
    			0,
    			0,
    			cornerRadius
    			));
		return image;
    }
    
    /*
     * @version 以给定的图像构建指定 大小在指定位置绘制指定圆角度数及大小的图像
     * @return ImageIcon
     */
    public static ImageIcon roundedCornerIcon(ImageIcon image,
            int width,
            int height,
            int imgWidth,
            int imgHeight,
            int x,
            int y,
    		int cornerRadius) {
    	
		return  image = new ImageIcon(roundedCornerImage(image,
    			width,
    			height,
    			imgWidth,
    			imgHeight,
    			x,
    			y,
    			cornerRadius
    			));
    }  
    /*
     * @version 以给定的图像构建指定 大小在指定位置绘制指定圆角度数及大小的图像
     * @return BufferedImage
     */
    public static BufferedImage roundedCornerImage(ImageIcon image,
            int width,
            int height,
            int imgWidth,
            int imgHeight,
            int x,
            int y,
    		int cornerRadius) {
    	
        BufferedImage output = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        
        //取得 output 的 画笔 
        Graphics2D g2 = output.createGraphics();

        //这里 g2 模式的设置模式 可以不需要。。
        g2.setComposite(AlphaComposite.Src);
        //该算法 可以消除锯齿
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        //添加 印刷板 
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(x, y, imgWidth, imgHeight, cornerRadius,
                cornerRadius));

        // 印模
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY );
        g2.drawImage(image.getImage(), x, y, imgWidth, imgHeight, null);

        g2.dispose();

        return output;
    }
    
    /*
     * @version 将指定图像保存到指定路径下
     * @return success boolean
     */
    public static boolean preservationImage (BufferedImage image, String format, String fileName) {
    	
    	boolean success = true;  	
        try {
        	
        	File file = new File(fileName);
        	file.mkdirs();
			ImageIO.write(image, format, file);
		} catch (IOException e) {
			
			e.printStackTrace();
			success = false;
		} finally {
			
		}
        return success;
    }   
    /*
     * @version 将指定图像以指定格式保存到指定路径下
     * @return success boolean
     */
    public static boolean preservationImage (ImageIcon image, String format, String fileName) {
    	
    	boolean success = true;  	
        try {
        	
        	File file = new File(fileName);
        	file.mkdirs();
	        BufferedImage output = new BufferedImage(image.getIconWidth(), image.getIconHeight(),
	                BufferedImage.TYPE_INT_ARGB);
	        Graphics2D graphics = output.createGraphics();
	        graphics.drawImage(image.getImage(), 0, 0, null);
			ImageIO.write(output, format, file);
		} catch (IOException e) {
			
			e.printStackTrace();
			success = false;
		}
        return success;
    }
    
    
    //用于测试
//    public static void main(String[] args){
//    	
//    	ChangeImage change = new ChangeImage();
//    	
////      40 的为 5
//    	ImageIcon img = new ImageIcon("Image\\Head\\2.png");
//    	change.preservationImage(change.roundedCornerImage(img, 
//    			42,
//    			42,
//    			40,
//    			40,
//    			(42-40)/2,
//    			(42-40)/2,
//    			5),
//    			"png", 
//    			"Image\\Head\\2.png");
//    }
}
