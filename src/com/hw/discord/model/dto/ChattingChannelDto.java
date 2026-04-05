package com.hw.discord.model.dto;

public class ChattingChannelDto {
	private Integer channelId;
	private String channelName;
	private boolean open;
	public ChattingChannelDto() {}
	public ChattingChannelDto(String channelName) {
		this.channelName = channelName;
	}
	public ChattingChannelDto(boolean open) {
		this.open = open;
	}
	public ChattingChannelDto(String channelName, boolean open) {
		this.channelName = channelName;
		this.open = open;
	}
	public ChattingChannelDto(Integer channelId, String channelName, boolean open) {
		this.channelId = channelId;
		this.channelName = channelName;
		this.open = open;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}

}
