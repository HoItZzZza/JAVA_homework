package practice_2_baekjoon;
import java.util.Scanner;

/**– 프로그램 전체 구조 설명, 기능별 코드 설명 -> 연계한 화면 삽입해 설명,
– 주어진 조건에 해당하는 게임 실행 화면을 단계별로 캡쳐해서 이미지 삽입하고 설명**/

//히히
public class Main {
	static int hero_level, hero_power, hero_hp, hero_defense, hero_mp, hero_experience, hero_money;
	static int monster_hp, monster_defense, monster_power, monster_mp, monster_level, monster_experience, monster_money;
	static String hero_name, monster_name;
	static int choose_place, choose_monster, thrust, choose_potion, monster_damaged, hero_damaged, i;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		hero_level = 1;
		hero_power = 15;
		hero_defense = 25;
		hero_hp = 80;
		hero_experience = 0;
		hero_money = 0;
		
		System.out.print("영웅의 이름을 입력하세요 : ");
		hero_name = in.next();
		System.out.println("이름이 입력되었습니다");
		System.out.println("게임에 입장하였습니다");
		
		System.out.println("********************");
		System.out.printf("현재 Hero의 이름 : %s\n",hero_name);
		System.out.printf("현재 %s의 레벨 : %d\n", hero_name, hero_level);
		System.out.printf("현제 %s의 힘 : %d\n", hero_name, hero_power);
		System.out.printf("현재 %s의 방어력 : %d\n", hero_name, hero_defense);
		System.out.printf("현재 %s의 HP : %d\n", hero_name, hero_hp);
		System.out.printf("현재 %s의 MP : %d\n", hero_name, hero_mp);
		System.out.printf("현재 %s의 경험치 :%d\n", hero_name, hero_experience);
		System.out.printf("현재 %s의 돈 : %d\n", hero_name, hero_money);
		System.out.println("********************");
		
		System.out.println("사냥터에 입장하였습니다");
		Main.before_monster_attack();
		
		Main.game();
	}
	
	static void game() {
		do {
			System.out.println("********************");
			System.out.printf("현재 Hero의 이름 : %s\n",hero_name);
			System.out.printf("현재 %s의 레벨 : %d\n", hero_name, hero_level);
			System.out.printf("현제 %s의 힘 : %d\n", hero_name, hero_power);
			System.out.printf("현재 %s의 방어력 : %d\n", hero_name, hero_defense);
			System.out.printf("현재 %s의 HP : %d\n", hero_name, hero_hp);
			System.out.printf("현재 %s의 MP : %d\n", hero_name, hero_mp);
			System.out.printf("현재 %s의 경험치 :%d\n", hero_name, hero_experience);
			System.out.printf("현재 %s의 돈 : %d\n", hero_name, hero_money);
			System.out.println("********************");

			Main.choose();
			
			if (hero_experience >= hero_level*80) {
				Main.level_up();
			}
		}while(true);
	}
	
	//경험치에 따른 레벨업
	static void level_up() {
		if (hero_experience >= hero_level*80) {
			hero_level++;
			System.out.println("********************");
			System.out.printf("%s의 레벨이 %d가 되었습니다\n", hero_name, hero_level);
			hero_money+=100;
			System.out.printf("레벨업 기념으로 돈이 100원 증가하여 %d원이 되었습니다\n", hero_money);
			System.out.println("********************");
		}
		hero_experience = 0;
	}
	
	
	//사냥터 or 포션 상점 입장
	static void choose() {
		System.out.println("1. 사냥터");
		System.out.println("2. 포션 상점");
		Scanner in = new Scanner(System.in);
		System.out.print("입장할 장소를 입력하세요 : ");
		choose_place = in.nextInt();
		if (choose_place == 1) {
			Main.before_monster_attack();
		}else {
			Main.store();
		}
	}
	
	
	//monster한테 공격받는 hero method
	static int hero_attack() {
		if (choose_monster == 1) {
			monster_name = "너구리";
			hero_damaged = 20;
		}else {
			monster_name = "살쾡이";
			hero_damaged = 100;
		}
		System.out.printf("%s의 공격입니다\n", monster_name);
		System.out.printf("데미지는 %d입니다\n", monster_power);
		Main.hero_attacked(hero_hp);
		while (true) {
			if (hero_hp <= 0) {
				System.out.println("히어로가 hp부족으로 죽었습니다 ㅠㅡㅠ");
				hero_hp = 1;
				System.out.println("히어로 부활!");
				break;
			}else {
			Main.monster_attack();
			}
		}
		Main.monster_attack();
		return hero_hp;
	}
	
	//hero_hp 확인하고 게임 계속할지 부활할지 결정
	static void hero_attacked(int sum) {
		sum = hero_hp;
		if (hero_defense >= monster_power) {
			hero_hp = hero_hp;
		}else {
			hero_hp = hero_hp + hero_defense - monster_power;
		}
	}
	
	static void before_monster_attack() {
		Scanner in = new Scanner(System.in);
		monster_damaged = hero_level*10 + hero_power*30;
		
		if (hero_power > 15 && hero_hp > 80) {
			System.out.println("1. 너구리");
			System.out.println("2. 살쾡이");
			System.out.print("전투할 상대를 입력하세요 : ");
			choose_monster = in.nextInt();
			if (choose_monster == 1) {
				monster_name = "너구리";
				monster_hp = 100;
				monster_mp = 0;
				monster_level = 1;
				monster_power = 20;
				monster_defense = 5;
				monster_money = 10;
				monster_experience = 10;
				
				System.out.println("너구리와 전투를 시작합니다");
				Main.monster_attack();
			}else {
				monster_name = "살쾡이";
				monster_hp = 2000;
				monster_mp = 0;
				monster_level = 5;
				monster_power = 100;
				monster_defense = 20;
				monster_money = 30;
				monster_experience = 50;
				
				System.out.println("살쾡이와 전투를 시작합니다");
				Main.monster_attack();
			}
		}else {
			System.out.println("1. 너구리");
			System.out.print("전투할 상대를 입력하세요 : ");
			choose_monster = in.nextInt();
			
			if (choose_monster == 1) {
				monster_name = "너구리";
				monster_hp = 100;
				monster_mp = 0;
				monster_level = 1;
				monster_power = 20;
				monster_defense = 5;
				monster_money = 10;
				monster_experience = 10;
				
				System.out.println("너구리와 전투를 시작합니다");
				Main.monster_attack();
			}
		}
	}
	

	static int monster_attack() {
		Scanner in = new Scanner(System.in);

		while(monster_hp > 0) {
			System.out.printf("%s의 공격입니다\n", hero_name);
			System.out.println("1. 쓰러스트");
			System.out.print("공격 번호를 입력하세요 : ");
			thrust = in.nextInt();
			System.out.printf("데미지는 %d입니다\n", monster_damaged);
			Main.monster_attacked(monster_damaged);
			if (monster_hp <= 0) {
				System.out.printf("%s가 죽었습니다\n", monster_name);
				hero_experience += monster_experience;
				hero_money += monster_money;
				break;
			}else {
				Main.hero_attack();
				}
			Main.game();
			}
		return monster_hp;
	}
	
	static void monster_attacked(int sum) { 
		sum = hero_level*10 + hero_power*30;
		if (monster_defense >= sum) {
			monster_hp = monster_hp;
		}else {
			monster_hp = monster_hp + monster_defense - sum;
		}
	}
	

	static void store() {
		Scanner in = new Scanner(System.in);
		System.out.println("포션 상점에 입장하였습니다");
		System.out.println("1. 힘 증강 포션 (30원)");
		System.out.println("2. 방어력 증강 포션 (30원)");
		System.out.println("3. 경험치 증강 포션 (100원)");
		System.out.println("4. HP 증강 포션 (10원)");
		System.out.println("5. MP 증강 포션 (10원");
		System.out.print("원하는 물건을 입력하세요 : ");
		choose_potion = in.nextInt();
		System.out.println("********************");
		System.out.println("포션 상점에서 물건을 구매 시도하는 중입니다");
		Main.potionStore_show(hero_money, choose_potion);
		System.out.println("구입이 완료되었습니다");
		System.out.printf("%d원 남았습니다\n", hero_money);
		System.out.println("********************");
		Main.choose();
	}
	
	static int potionStore_show(int money, int num) {
		if (choose_potion == 1 || choose_potion == 2) {
			hero_money-=30;
			if (choose_potion == 1) {
				hero_power+=10;
			}else {
				hero_defense+=20;
			}
		}else if (choose_potion == 4 || choose_potion == 5){
			hero_money-=10;
			if (choose_potion == 4) {
				hero_hp+=20;
			}else {
				hero_mp+=20;
			}
		}else{
			hero_money-=100;
			hero_experience+=40;
		}
		return hero_money;
	}
}

