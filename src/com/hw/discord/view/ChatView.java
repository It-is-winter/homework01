package com.hw.discord.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.hw.discord.controller.ChatController;
import com.hw.discord.model.dto.ChatDto;
import com.hw.discord.model.vo.Chat;

public class ChatView {
	private ChatController chatController = new ChatController();
	private Scanner sc = new Scanner(System.in);
	
	public void chatMenu() {
		while(true) {
			System.out.println();
			System.out.println("=======================================================");
			System.out.println("\t\t\t채팅방");
			System.out.println("=======================================================");
			findAll();
			System.out.println("-------------------------------------------------------");		
			System.out.println("1. 채팅 쓰기");
			System.out.println("2. 채팅 수정하기");
			System.out.println("3. 채팅 삭제하기");
			System.out.println("4. 돌아가기");
			System.out.println();
			System.out.print("원하시는 메뉴의 숫자를 적어주세요 >");
			String chatMenu = sc.nextLine();
			System.out.println();
			
			switch(chatMenu) {
			case "1" : createChat(); break;
			case "2" : updateChat(); break;
			case "3" : deleteChat(); break;
			case "4" : return;
			}
		}
	}
	
	/*
	 * All chats
	 */
	private void findAll() {
		List<Chat> chats = chatController.findAll();
		if(!chats.isEmpty()) {
			for(Chat chat : chats) {
				StringBuilder sb = new StringBuilder();
				sb.append("\t\t\t\t\t");
				sb.append(chat.createDate());
				sb.append("\n");
				sb.append(chat.chatId());
				sb.append(" |\t");
				sb.append(chat.chatText());
				System.out.println(sb);
				System.out.println();
			}
		} else {
			System.out.println("조회할 수 있는 채팅이 없습니다.");
		}
	}
	
	/*
	 * Create a new chat
	 */
	private void createChat() {
		System.out.println("채팅 내용을 적어주세요.(30자 이하)");
		String text = sc.nextLine();
		System.out.println();
		int result = chatController.createChat(new ChatDto(text));
		if(result == 1) {
			System.out.println("채팅 생성 성공했습니다.");
		} else {
			System.out.println("채팅 생성에 실패했습니다.");
		}
	}
	
	/*
	 * Update the chat
	 */
	private void updateChat() {
		int chatId = 0;
		while(true) { // 숫자가 입력되지 않았을 때 다시 채팅 번호를 입력하도록 함
			System.out.println("-------------------------------------------------------");		
			findAll();
			System.out.println("-------------------------------------------------------");		
			System.out.print("변경하실 채팅의 번호를 입력해주세요. >");
			try {
				chatId = sc.nextInt();
				sc.nextLine();
			} catch(InputMismatchException e) {
				System.out.println("숫자로 입력해주세요");
				System.out.println();
				sc.nextLine();
				continue;
			}
			
			Chat chat = chatController.findById(chatId);
			if(chat != null) {
				break;
			} else {
				System.out.println("존재하지 않는 채팅입니다. 다시 입력해주세요.");
			}
		}
		
		System.out.println("변경하고 싶으신 내용을 입력해주세요.(30자 이하)");
		String text = sc.nextLine();
		System.out.println();
		int result = chatController.updateChat(chatId, new ChatDto(text));
		if(result == 1) {
			System.out.println("채팅 수정 성공!");
		} else {
			System.out.println("채팅 수정에 실패하였습니다.");
		}
	}
	
	/*
	 * Delete the chat
	 */
	private void deleteChat() {
		int id = 0;
		while(true) {
			System.out.println();
			System.out.println("-------------------------------------------------------");		
			findAll();
			System.out.println("-------------------------------------------------------");		
			System.out.print("삭제하실 채팅의 번호를 입력해주세요 >");
			String chatId = sc.nextLine();
			try {
				id = Integer.parseInt(chatId);
			} catch(NumberFormatException e) {
				System.out.println("숫자로 입력해주세요.");
				continue;
			}
			Chat chat = chatController.findById(id);
			if(chat != null) {
				break;
			} else {
				System.out.println("존재하지 않는 채팅입니다. 다시 입력해주세요.");
			}
		}
		System.out.println();
		Chat result = chatController.deleteChat(id);
		if(result != null) {
			System.out.println(id + "번 채팅 삭제 완료되었습니다.");
		} else {
			System.out.println(id + "번 채팅 삭제 실패하였습니다.");
		}
	}

}
