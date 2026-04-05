package com.hw.discord.model.service;

import java.util.ArrayList;
import java.util.List;

import com.hw.discord.model.dto.ChattingChannelDto;
import com.hw.discord.model.vo.ChattingChannel;

public class ChattingChannelService {
	private List<ChattingChannel> channels = new ArrayList();
	private int channelId;
	{
		/*
		 * 기본 채널 목록
		 */
		channels.add(new ChattingChannel(++channelId, "일반", true));
	}
	
	/*
	 * 전체 채널 조회
	 * 생성된 채팅채널들 목록 전체 조회
	 */
	public List<ChattingChannel> findAll() {
		return channels;
	}
	
	/*
	 * 채널 생성 메소드
	 * 채널 이름의 유효성을 검사한 후, channels에 ChattingChannel 추가
	 * 채널을 생성할 때는 반드시 값이 들어와야하고 들어온 값의 길이가 30이 넘으면 안됨
	 */
	public int createChannel(ChattingChannelDto channel) {
		String channelName = channel.getChannelName();
		boolean checked = validateChannelName(channelName);
		if(checked) {
			// 유효성 검사 통과
			channels.add(new ChattingChannel(++channelId, channelName, channel.isOpen()));
			return 1;
		}
		return 0;
	}
	
	/*
	 * 채널 수정 메소드
	 * 채널 이름만 수정 가능
	 * 채널의 공개여부만 수정 가능
	 * 채널의 이름과 공개여부 둘 다 한번에 수정 가능
	 * 수정된 채널의 이름이 비어있거나 30자를 넘는지 확인
	 */
	public int updateChannel(int channelId, ChattingChannelDto channel) {
//		channels.stream()
//				.filter(c -> c.getChannelId() == channelId)
//				.toList();
//		System.out.println("service : " + channelId);
//		System.out.println("service : " + channel.getChannelName());
		int index = indexOf(channelId);
		if(index != -1) {
			String channelName = channel.getChannelName(); 
			boolean checked = validateChannelName(channelName);
			if(checked) {
				channels.set(index, new ChattingChannel(channelId, channel.getChannelName(), channel.isOpen()));
				return 1;
			}
		}
		return 0;
	}
	
	/*
	 * channelId에 해당하는 ChattingChannel 찾는 메소드
	 */
	public ChattingChannel findById(int channelId) {
//		System.out.println("service id : " + channelId);
		int index = indexOf(channelId);
		if(index != -1) {
			return channels.get(index);
		}
		return null;
	}
	
	/*
	 * 채널 삭제 메소드
	 */
	public String deleteChannel(int channelId) {
		int index = indexOf(channelId);
		// 더이상 채널이 없는데 삭제를 시도 할 수 있음
		if(index != -1) {
			ChattingChannel channel = channels.remove(index);
			return channel.getChannelName();
		}
		return null;
	}
	
	/*
	 * 채널 이름 유효성 검사 메소드
	 * 채널 이름이 비어있는지 아닌지 확인
	 * 채널 이름이 30자를 넘는지 안넘는지 확인
	 */
	private boolean validateChannelName(String channelName) {
		if(0 < channelName.length() && channelName.length() <= 30) {
			// 이름에 값이 들어왔나?			이름의 길이가 30자를 넘지 않는가?
			return true;
		}
		return false;
	}
	
	/*
	 * 매개변수로 들어온 channelId와 일치하는 ChattingChannel의 인덱스를 찾는 메소드
	 */
	private int indexOf(int channelId) {
		int index = -1;
		for(ChattingChannel chan : channels) {
			if(chan.getChannelId() == channelId) {
				index = channelId - 1;
				break;
			}
		}
		return index;
	}

}
