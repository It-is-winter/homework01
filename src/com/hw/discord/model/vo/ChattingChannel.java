package com.hw.discord.model.vo;

import java.util.Objects;

public class ChattingChannel {
	private final Integer channelId;	// 각 채널의 고유한 번호를 저장할 필드 : 같은 속성의 채널이 생성되더라도 식별가능하도록 함
	private final String channelName;	// 채널의 이름을 저장할 필드
	private final boolean open;		// 채널이 모든 사람들에게 공개되어있는지 여부를 저장할 필드
	public ChattingChannel(Integer channelId, String channelName, boolean open) {
		this.channelId = channelId;
		this.channelName = channelName;
		this.open = open;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public String getChannelName() {
		return channelName;
	}
	public boolean isOpen() {
		return open;
	}
	@Override
	public int hashCode() {
		return Objects.hash(channelId, channelName, open);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChattingChannel other = (ChattingChannel) obj;
		return Objects.equals(channelId, other.channelId) && Objects.equals(channelName, other.channelName)
				&& open == other.open;
	}

}
