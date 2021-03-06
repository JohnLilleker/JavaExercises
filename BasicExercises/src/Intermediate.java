
public class Intermediate {
	public static void main(String [] args) {
		Intermediate i = new Intermediate();
		System.out.println(i.blackjack(18, 21));
		System.out.println(i.blackjack(20, 18));
		System.out.println(i.blackjack(22, 22));

		System.out.println(i.unqueSum(1, 2, 3));
		System.out.println(i.unqueSum(3, 3, 3));
		System.out.println(i.unqueSum(1, 1, 2));
		
		System.out.println((i.isTooHot(50, true) ? "Too hot" : " Not so hot"));
		System.out.println((i.isTooHot(50, false) ? "Too hot" : " Not so hot"));
		System.out.println((i.isTooHot(60, true) ? "Too hot" : " Not so hot"));
		System.out.println((i.isTooHot(60, false) ? "Too hot" : " Not so hot"));
		System.out.println((i.isTooHot(80, true) ? "Too hot" : " Not so hot"));
		System.out.println((i.isTooHot(80, false) ? "Too hot" : " Not so hot"));
		System.out.println((i.isTooHot(90, true) ? "Too hot" : " Not so hot"));
		System.out.println((i.isTooHot(90, false) ? "Too hot" : " Not so hot"));
		System.out.println((i.isTooHot(95, true) ? "Too hot" : " Not so hot"));
		System.out.println((i.isTooHot(95, false) ? "Too hot" : " Not so hot"));
		System.out.println((i.isTooHot(100, true) ? "Too hot" : " Not so hot"));
		System.out.println((i.isTooHot(100, false) ? "Too hot" : " Not so hot"));
		System.out.println((i.isTooHot(105, true) ? "Too hot" : " Not so hot"));
		System.out.println((i.isTooHot(105, false) ? "Too hot" : " Not so hot"));
	}
	
	public int blackjack(int x, int y) {
		int diff1 = 21 - x;
		int diff2 = 21 - y;
		if (diff1 < 0 && diff2 < 0) return 0;
		
		if (diff1 < 0) return y;
		if (diff2 < 0) return x;
		
		return (diff1 < diff2) ? x : y;
	}
	
	public int unqueSum(int x, int y, int z) {
		int total = 0;
		
		if (x != y && x != z)
			total += x;

		if (y != x && y != z)
			total += y;

		if (z != x && z != y)
			total += z;
		
		return total;
	}
	
	public boolean isTooHot(int temp, boolean isSummer) {
		if (temp < 60) return false;
		
		if (temp > ((isSummer) ? 100 : 90)) return false;
		
		return true;
	}
}
