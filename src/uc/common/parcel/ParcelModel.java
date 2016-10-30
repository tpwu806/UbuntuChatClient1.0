package uc.common.parcel;

/**
 * @Description: 服务器和客户端 传递消息的 包的模型
 * 服务器与客户端 之间接收包的参数类型为该接口
 * 这样以后如果有其他类包含了一些可复用的内容可以通过适配器模式来实现传递
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public interface ParcelModel {
	
	public MessageEnum getMessage();
}
