package com.zxj.wechat.service;

import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.zxj.wechat.model.resp.TextMsg;
import com.zxj.wechat.util.MsgUtil;

public class CoreService {
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		String respMsg = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";

			// xml请求解析
			Map<String, String> requestMap = MsgUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			
			//判断用户输入的文字
			String reqContent = requestMap.get("Content");

			// 回复文本消息
			TextMsg textMsg = new TextMsg();
			textMsg.setToUserName(fromUserName);
			textMsg.setFromUserName(toUserName);
			textMsg.setCreateTime(new Date().getTime());
			textMsg.setMsgType(MsgUtil.RESP_MESSAGE_TYPE_TEXT);

			// 文本消息
			if (msgType.equals(MsgUtil.REQ_MESSAGE_TYPE_TEXT)) {
				if(reqContent.equals("help"))
					respContent = getMainMenu();
				//如果用户发送的是QQ表情，则发送同样的表情给用户
				else if(MsgUtil.isQqFace(reqContent))
					respContent = reqContent;
				else
					respContent = "系统升级中。";
			}
			// 图片消息
			else if (msgType.equals(MsgUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MsgUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MsgUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 音频消息
			else if (msgType.equals(MsgUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			}
			// 事件推送
			else if (msgType.equals(MsgUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MsgUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "感谢关注 GeekerHub !\"\n\"输入help可获取系统服务菜单。";
				}
				// 取消订阅
				else if (eventType.equals(MsgUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MsgUtil.EVENT_TYPE_CLICK)) {
					// TODO 自定义菜单权没有开放，暂不处理该类消息
				}
			}

			textMsg.setContent(respContent);
			respMsg = MsgUtil.textMsgToXml(textMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMsg;
	}

	public static String getMainMenu() {
		StringBuilder menu = new StringBuilder();
		menu.append("主人您好，请回复数字选择服务：").append("\n\n");
		menu.append("1  天气预报").append("\n");
		menu.append("2  公交查询").append("\n");
		menu.append("3  周边搜索").append("\n");
		menu.append("4  歌曲点播").append("\n");
		menu.append("5  经典游戏").append("\n");
		menu.append("6  美女电台").append("\n");
		menu.append("7  人脸识别").append("\n");
		menu.append("8  聊天唠嗑").append("\n\n");
		menu.append("回复“help”显示此帮助菜单");
		return menu.toString();
	}
}
