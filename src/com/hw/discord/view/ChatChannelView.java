package com.hw.discord.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.hw.discord.controller.ChatChannelController;
import com.hw.discord.model.dto.ChatChannelDto;
import com.hw.discord.model.vo.ChatChannel;

/*
 * 사용자에게 입력을 받거나 service에서 전달받은 값을 출력
 */
public class ChatChannelView {
	private ChatChannelController channelController = new ChatChannelController();
	private Scanner sc = new Scanner(System.in);
	/*
	 * 프로그램이 실행되었을 때 사용자에게 보여줄 첫 페이지 : 기본 채널 목록
	 */
	public void showMenu() {
		while(true) { // 사용자가 얼마나 머물지 알 수 없음
			System.out.println();
			System.out.println("*******************************************************");
			System.out.println("\t\t\t채팅채널");
			System.out.println("*******************************************************");
			findAll();
//			System.out.println("1. 채팅채널 만들기");
//			System.out.println("2. 설정하기"); // 채널 수정과 삭제
			System.out.println("=======================================================");
			System.out.println("\t\t\t설정");
			System.out.println("=======================================================");
			System.out.println("1. 채팅채널 만들기");
			System.out.println("2. 채팅채널 변경하기");
			System.out.println("3. 채팅채널 삭제하기");
			System.out.println("4. 채팅방 입장하기");
			System.out.println("5. 프로그램 종료");
			System.out.println();
			System.out.print("원하시는 메뉴 번호를 입력해주세요 >");
			int settingMenu = 0;
			try {
				settingMenu = sc.nextInt();
				sc.nextLine();
			} catch(InputMismatchException e) {
				System.out.println("숫자만 입력해주세요.");
				sc.nextLine();
			}
			
			switch(settingMenu) {
			case 1 : createChannel(); break;
//			case 2 : new ChatChannelSettingsView().settingMenu();
//					 break;
			case 2 : updateChannel(); break;
			case 3 : deleteChannel(); break;
			case 4 : new ChatView().chatMenu(); break;
			case 5 : System.out.println("프로그램 종료");
					 sc.close();
					 return;
			default : System.out.println("다시 입력해주세요."); continue;
			}
		}
	}
	
	/*
	 * 전체 조회
	 */
	private void findAll() {
		List<ChatChannel> channels = channelController.findAll();
		if(!channels.isEmpty()) {
			for(ChatChannel channel : channels) {
				StringBuilder sb = new StringBuilder();
				sb.append(channel.getChannelId());
				sb.append(" |\t");
				sb.append(channel.getChannelName());
				sb.append("\t|\t\t");
				if(channel.isOpen()) {
					sb.append("공개");					
				} else {
					sb.append("비공개");
				}
				System.out.println(sb);
			}
		} else {
			System.out.println("조회할 수 있는 채널이 없습니다.");
		}
	}
	
	/*
	 * 채널 생성
	 * 사용자로부터 채널의 이름과 공개여부 입력 받기
	 */
	private void createChannel() {
		System.out.println();
		System.out.println("-------------------------------------------------------");
		System.out.println("\t\t\t채널 만들기");
		System.out.println("-------------------------------------------------------");
		System.out.print("채널 이름(10자 이하) : ");
		String channelName = sc.nextLine();
		boolean open = checkOpen();
		// view 할일 끝
		
		ChatChannelDto channel = new ChatChannelDto(channelName, open);
		int result = channelController.createChannel(channel);
		
		// 생성 성공여부를 화면에 출력해주기
		if(result == 1) {
			// 생성 성공
			System.out.println(channelName + "채널이 생성되었습니다.");
		} else {
			// 생성 실패
			System.out.println("채널 생성에 실패했습니다.");
		}
	}
	
	/*
	 * 채널 수정
	 * 사용자로부터 원하는 채널아이디와 무엇을 수정할지 입력받기
	 * 무엇을 수정하는지에 따라 채널이름, 채널의 공개여부 입력받기
	 */
	private void updateChannel() {
		int id = 0;
		String channelName = "";
		boolean open = false;
		ChatChannel chatChannel = null;
		while(true) {
			System.out.println();
			System.out.println("-------------------------------------------------------");
			System.out.println("\t\t\t채널 수정하기");
			System.out.println("-------------------------------------------------------");
			findAll();
			System.out.println("-------------------------------------------------------");
			System.out.print("수정하고 싶은 채널의 번호를 입력해주세요 >");
			String channelId = "";
			channelId = sc.nextLine();
			try {
				id = Integer.parseInt(channelId);
			} catch(NumberFormatException e) {
				System.out.println("숫자로 입력해주세요.");
			}
			// 채널 이름만 변경
			// 채널 공개여부만 변경
			// 채널의 이름과 공개여부 변경
			// 어떤걸 변경하고 싶을지 모름
			chatChannel = channelController.findById(id);
			if(chatChannel != null) { // 입력한 값에 일치하는 채널이 있을 때만 아래 코드 수행
				break;
			} else {
				System.out.println("존재하지 않는 채널입니다. 다시 선택해주세요");
				continue;
			}
		}
		int updateMenu = 0;
		while(true) { // 숫자를 입력하지 않았을 때 변경선택 메뉴 다시 보여줌
			System.out.println();
			System.out.println("무엇을 변경하시겠습니까?");
			System.out.println("1. 채널이름만 변경하기");
			System.out.println("2. 채널의 공개여부만 변경하기");
			System.out.println("3. 이름과 공개여부 둘 다 변경하기");
			System.out.println();
			System.out.print("변경하시고 싶은 내용의 번호를 입력해주세요 >");
			try {
				updateMenu = sc.nextInt();
				sc.nextLine();
				break;
			} catch(InputMismatchException e) {
				System.out.println("숫자로 입력해주세요.");
				sc.nextLine();
				continue;
			}
		}
		System.out.println();
		switch(updateMenu) {
		case 1 : System.out.print("변경할 채널 이름(10자 이하) : ");
		channelName = sc.nextLine();
		// channelId와 일치하는 ChatChannel의 Open값 그대로 넣어주기 : 변함없음
		open = chatChannel.isOpen();
		break;
		case 2 : open = checkOpen(); 
		// channelId와 일치하는 ChatChannel의 채널이름값 그대로 넣어주기 : 변함없음
		channelName = chatChannel.getChannelName();
		break;
		case 3 : System.out.print("변경할 채널 이름(10자 이하) : ");
		channelName = sc.nextLine();
		open = checkOpen();
		break;
		}
		// view 역할 끝!
				
		// controller에 값 넘겨주기
		ChatChannelDto channel = new ChatChannelDto(channelName, open);
		int result = channelController.updateChannel(id, channel);
		// 수정 성공 여부 출력해주기
		if(result == 1) {
			System.out.println("변경 성공!");
		} else {
			System.out.println("변경에 실패하였습니다..");
		}
	}
	
	/*
	 * 사용자가 입력한 공개여부가 유효한지 확인하는 메소드
	 */
	private boolean checkOpen() {
		boolean flag = true;
		char or = ' ';
		boolean open = false;
		while(flag) { // O나 X 이외의 값을 입력하면 다시 이 반복문을 수행하도록 함
					  // 사용자가 얼마나 잘못 입력할지 알 수 없음
			System.out.print("채널의 공개를 원하시면 O 아니면 X를 입력해주세요 >");
			or = sc.nextLine().charAt(0);
			if((or != 'O') && (or != 'o') && (or != 'X') && (or != 'x')) {
				System.out.println("O 또는 X를 입력해주세요(소문자도 가능)");
				System.out.println();
				flag = true;
			}
			if((or == 'O') || (or == 'o')) {
				open = true;
				flag = false;
			} else if((or == 'X') || (or == 'x')) {
				open = false;
				flag = false;
			}
		}
		return open;
	}
	
	/*
	 * 채널 삭제 메소드
	 * 채널의 아이디를 입력받아, 아이디에 맞는 채널 삭제하기
	 */
	private void deleteChannel() {
		int id = 0;
		String result = "";
		while(true) {
			System.out.println();
			System.out.println("-------------------------------------------------------");
			System.out.println("\t\t\t채널 삭제하기");
			System.out.println("-------------------------------------------------------");
			findAll();
			System.out.println("-------------------------------------------------------");
			System.out.print("삭제하고 싶은 채널의 아이디를 입력해주세요 >");
			String channelId = sc.nextLine();
			try {
				id = Integer.parseInt(channelId);
			} catch(NumberFormatException e) {
				System.out.println("숫자로 입력해주세요.");
				continue;
			}
			ChatChannel channel = channelController.findById(id);
			if(channel != null) {
				break;
			} else {
				System.out.println("존재하지 않는 채널입니다. 다시 입력해주세요.");
				continue;
			}
		}
		result = channelController.deleteChannel(id);
		if(result != null) {
			System.out.println(result + "채널 삭제 성공했습니다!");
		} else {
			System.out.println("채널 삭제에 실패했습니다..");
		}
	}

}
