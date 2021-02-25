package kr.or.mit;

import java.util.ArrayList;
import java.util.Scanner;

public class PMDS {
	static PMDVO vo = new PMDVO();
	static PMDDB e = new PMDDB();

	static void PM() {
		Scanner sc = new Scanner(System.in);
		PMDS.menu();
		int n = sc.nextInt();
		sc.nextLine();

		try {

			switch (n) {
			case 1:
				System.out.println("지출이 선택되었습니다.");
				System.out.print("지출내용 :");
				vo.setItem(sc.nextLine());
				System.out.print("지출금액 :");
				vo.setMoney(sc.nextInt());
				System.out.println("지출" + vo.getItem() + " " + vo.getMoney() + "원이 맞습니까?(y/n)");
				sc.nextLine();
				if (sc.nextLine().toLowerCase().equals("y")) {
					e.spend(vo);
					System.out.println("완료 되었습니다.");
					Thread.sleep(500);
					PM();
				} else {
					PM();
				}
				break;
			case 2:
				System.out.println("수입이 선택되었습니다.");
				System.out.print("수입내용 :");
				vo.setItem(sc.nextLine());
				System.out.print("수입금액 :");
				vo.setMoney(sc.nextInt());
				System.out.println("수입" + vo.getItem() + " " + vo.getMoney() + "원이 맞습니까?(y/n)");
				sc.nextLine();
				if (sc.nextLine().toLowerCase().equals("y")) {
					e.earn(vo);
					System.out.println("완료 되었습니다.");
					Thread.sleep(500);
					PM();
				} else {
					PM();
				}
				break;
			case 3:
				int sum = e.printmoney();
				System.out.println("잔액이 선택되었습니다.");
				System.out.println("현재 잔액은 " + sum + "원 입니다.");
				System.out.println("상세 내역을 보시겠습니까?(y/n)");
				if (sc.nextLine().toLowerCase().equals("y")) {
					ArrayList<PMDVO> list = e.detail();
					int ssum = 0;
					System.out.println("******************************************************************");
					System.out.println("내용\t|\t수입\t|\t지출\t|\t잔액\t");
					for (PMDVO go : list) {
						System.out.print(go.getItem());
						if (go.getMoney() < 0) {
							System.out.print("\t\t\t\t");
							System.out.print(go.getMoney() * (-1) + "\t\t");
							ssum += go.getMoney();
							System.out.println(ssum);
						} else {
							System.out.print("\t\t" + go.getMoney());
							System.out.print("\t\t\t\t");
							ssum += go.getMoney();
							System.out.println(ssum);
						}
					}
					System.out.println("확인후 엔터를 눌러주세요");
					sc.nextLine();
					PM();
				} else {
					PM();
				}
				break;
			case 4:
				System.out.println("프로그램이 종료 됩니다.");
				break;
			}
		} catch (Exception e) {
			System.out.println("오류발생");
			e.getStackTrace();
		} finally {
			try {
				e.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	static void menu() {

		try {
			System.out.println("********************");
			Thread.sleep(500);
			System.out.println("      용돈 기입장      ");
			Thread.sleep(500);
			System.out.println("********************");
			Thread.sleep(500);
			System.out.println("1. 지출");
			Thread.sleep(500);
			System.out.println("2. 수입");
			Thread.sleep(500);
			System.out.println("3. 잔액");
			Thread.sleep(500);
			System.out.println("4. 종료");
			Thread.sleep(500);
			System.out.println("메뉴 번호를 입력해주세요");
		} catch (InterruptedException e) {
			System.out.println("출력 지연 오류");
			e.getStackTrace();
		}
	}
}
