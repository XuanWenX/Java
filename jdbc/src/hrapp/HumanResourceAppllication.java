package hrapp;
import hrapp.command.Command;
import hrapp.command.InsertCommand;
import hrapp.command.PaginationCommand;

import java.util.Scanner;

public class HumanResourceAppllication {
	public static void main(String[] args) {
		System.out.println("1-��ѯ����Ա��");
		System.out.println("2-����Ա����ְ");
		System.out.println("3-��ҳ��ѯԱ������");
		try (Scanner in = new Scanner(System.in)) {
			Integer cmd = in.nextInt();
			Command command = null;
			switch(cmd) {
				case 1:
					command = new PstmtQueryCommand();
					command.execute();
				    break;
				case 2:
					command = new InsertCommand();
					command.execute();
					break;
				case 3:
					command = new PaginationCommand();
					command .execute();
					break;
				}
		}
		}
	}


