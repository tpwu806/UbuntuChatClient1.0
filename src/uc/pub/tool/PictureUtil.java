package uc.pub.tool;

import java.awt.Graphics2D;  
import java.awt.RenderingHints;  
import java.awt.color.ColorSpace;  
import java.awt.image.BufferedImage;  
import java.awt.image.ColorConvertOp;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;  
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;  
  
  
/**
 * @Description: 转换图片颜色
 * @author wutp 2016年11月28日
 * @version 1.0
 */
public class PictureUtil {

	public static void main(String[] args) throws IOException {

		BufferedImage transforImage;
		BufferedImage finalImage;
		BufferedImage srcImage = null;
		//TransGray gray = new TransGray();
		transforImage = srcImage;
		// 这里可能有问题，关于RenderingHints的取值不是很清楚
		finalImage = transformGrayJ2D(transforImage, null);
		File f1 = new File("d:/2graychange.jpg");// 新图像输出
		ImageIO.write(finalImage, "jpg", f1);
		System.out.println("End");
	}
  
    public static BufferedImage transformGrayJ2D(BufferedImage srcImage,  
            RenderingHints hints) {    
        BufferedImage dstImage = new BufferedImage(srcImage.getWidth(),  
                srcImage.getHeight(), srcImage.getType());   
        if (hints == null) {   
            Graphics2D g2 = dstImage.createGraphics();    
            hints = g2.getRenderingHints();    
            g2.dispose();   
            g2 = null;    
        }  
        // 此抽象类用做一个颜色空间标记，标识  
        // Color  
        // 对象的特定颜色空间，或者通过  
        // ColorModel  
        // 对象标识  
        // Image、BufferedImage  
        // 或  
        // GraphicsDevice  
        // 的特定颜色空间。此类包含了可将指定颜色空间中的颜色与  
        // sRGB  
        // 和定义良好的  
        // CIEXYZ  
        // 颜色空间中的颜色进行相互转换的方法。 
        ColorSpace grayCS = ColorSpace.getInstance(ColorSpace.CS_GRAY); 
        // 此类对源图像中的数据执行逐像素的颜色转换。得到的颜色值可以扩展到目标图像的精度。颜色转换可以通过  
        // ColorSpace  
        // 对象的数组或  
        // ICC_Profile  
        // 对象的数组指定。  
        ColorConvertOp colorConvertOp = new ColorConvertOp(grayCS, hints);
  
        colorConvertOp.filter(srcImage, dstImage);// 对源 BufferedImage 进行颜色转换  
  
        return dstImage;  
    }  
    // 将图像转换为字节数组好用于传输
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
 	
 	public static byte[] transformImage(byte[] head)  {
 		byte[] imageByte = null;
 		BufferedImage dstImage;
 		InputStream imageStream = new ByteArrayInputStream(head);
 		BufferedImage srcImage;
		try {
			srcImage = ImageIO.read(imageStream);
		    dstImage = transformGrayJ2D2(srcImage, null);
		    ByteArrayOutputStream bs =new ByteArrayOutputStream();
		    ImageOutputStream imOut =ImageIO.createImageOutputStream(bs);
		    ImageIO.write(dstImage,"png",imOut); //scaledImage1为BufferedImage，jpg为图像的类型
		    imageByte = bs.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return imageByte;
	}
 	
 	/**
 	 * @Description:
 	 * @auther: wutp 2016年11月28日
 	 * @param srcImage
 	 * @param hints
 	 * @return
 	 * @return BufferedImage
 	 */
 	public static BufferedImage transformGrayJ2D2(BufferedImage srcImage,  
            RenderingHints hints) {    
        BufferedImage dstImage = new BufferedImage(srcImage.getWidth(),  
                srcImage.getHeight(), srcImage.getType());   
        if (hints == null) {   
            Graphics2D g2 = dstImage.createGraphics();    
            hints = g2.getRenderingHints();    
            g2.dispose();   
            g2 = null;    
        }  
       
        ColorSpace grayCS = ColorSpace.getInstance(ColorSpace.CS_GRAY);        
        ColorConvertOp colorConvertOp = new ColorConvertOp(grayCS, hints);  
        colorConvertOp.filter(srcImage, dstImage);// 对源 BufferedImage 进行颜色转换   
        return dstImage;  
    }  
} 