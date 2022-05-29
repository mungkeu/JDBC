package com.newlecture.app.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.newlecture.app.entity.Notice;

public class NoticeConsole {

	private NoticeService service;
	private int page;
	private int count; // NoticeCount
	private String searchField;
	private String searchWord;
	
	public NoticeConsole() {
		service = new NoticeService();
		page=1;
		count=0;
		searchField="TITLE";
		searchWord="";
	}

	public void printNoticeList() throws ClassNotFoundException, SQLException {
		List<Notice> list = service.getList(page, searchField, searchWord);
		int count = service.getCount(); // �Ź� �ٽ� ���ؾ� �ϹǷ� ����������.
		
		// �Ź� �ٽ� ���ؾ� �ϹǷ� ����������.
		int lastPage = count/10; // 100 -> 10, 90 ->9, 93/10->9 3���� ���󰣴�.
		lastPage = count%10>0?lastPage+1:lastPage; // ���� �������� �ִٸ� �ݿø� �ʿ�.
		
		System.out.println("������������������������������������������������������������������������");
		System.out.printf("<��������> �� %d �Խñ�\n", count);
		System.out.println("������������������������������������������������������������������������");
		for(Notice n : list) {
			System.out.printf("%d. %s / %s / %s\n",
					n.getId(), n.getTitle(), n.getWriterId(),n.getRegDatel());
		}
		System.out.println("������������������������������������������������������������������������");
		System.out.printf("           %d/%d pages\n", page, lastPage);	
		System.out.println();
	}

	public int inputNoticeMenu() {
		Scanner scan = new Scanner(System.in);
		System.out.print("1.����ȸ / 2.���� / 3.���� / 4.�۾��� / 5.�˻� / 6.����>");
		String menu_ = scan.nextLine();
		int menu = Integer.parseInt(menu_);
		return menu;
	}

	public void movePrevList() {
		if(page==1) {
			System.out.println("=====================");
			System.out.println("[���� �������� �����ϴ�.]");
			System.out.println("=====================");
			return;
		}
		page--;
	}

	public void moveNextList() throws ClassNotFoundException, SQLException {
		// �ߺ� �Ǿ �ٽ� ����Ѵ�. �ǽð� ���α׷�.
		int count = service.getCount(); 	
		int lastPage = count/10; 
		lastPage = count%10>0?lastPage+1:lastPage; 
		
		if(page==lastPage) {
			System.out.println("=====================");
			System.out.println("[���� �������� �����ϴ�.]");
			System.out.println("=====================");
			return;
		}
		page++;
	}

	public void inputSearchWord() {
		Scanner scan = new Scanner(System.in);
		System.out.println("�˻� ����(title/content/writerID) �߿� �ϳ��� �Է��ϼ���");
		System.out.println(" > ");
		searchField = scan.nextLine();
		
		System.out.println("�˻��� > ");
		searchWord = scan.nextLine();
	}
}