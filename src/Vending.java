import java.util.ArrayList;
import java.util.List;

public class Vending {
	public final static int SUCCESS_BUY = 200;

	public final static int ERROR_EMPTY_STOCK = 401;
	public final static int ERROR_OVER_STOCK = 401;
	public final static int ERROR_LESS_MONEY = 402;
	public final static int ERROR_INVALID_MONEY = 403;
	
	private List<Item> items;
	private List<Integer> moneyAccepted;
	
	public Vending() {
		this.items = new ArrayList<Item>() {
			{
				add(new Item("Aqua", 5000, 0));
				add(new Item("The Botol", 7000, 30));
				add(new Item("Pocari Sweat", 10000, 40));
			}
		};
		this.moneyAccepted = new ArrayList<Integer>() {
			{
				add(2000);
				add(5000);
				add(10000);
				add(20000);
			}
		};
	}
	
	public List<Item> getItems() {
		return items;
	}

	public int buy(Item item, int qty, List<Integer> moneyInserted) {
		if(item.getStock() == 0)
			return Vending.ERROR_EMPTY_STOCK;
		if(qty > item.getStock())
			return Vending.ERROR_OVER_STOCK;
		for(Integer money: moneyInserted) {
			if(!isValidMoney(money))
				return Vending.ERROR_INVALID_MONEY;
		}
		int total = 0;
		for(Integer money: moneyInserted)
			total += money;
		if(total < item.getPrice()*qty)
			return Vending.ERROR_LESS_MONEY;
		item.setStock(item.getStock()-qty);
		return Vending.SUCCESS_BUY;
	}
	
	private boolean isValidMoney(Integer money) {
		return moneyAccepted.contains(money);
	}
}
