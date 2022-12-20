import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VendingTest {
	private Vending vending;
	
	@BeforeEach
	void init() {
		this.vending = new Vending();
	}

	@Test
	void normalVending() {
		assertEquals(this.vending.getItems().size(), 3);
		Item item = this.vending.getItems().get(1);
		assertEquals(item.getName(), "The Botol");
		assertEquals(item.getStock(), 30);
		Integer resultCode = this.vending.buy(item, 3, new ArrayList<Integer>() {
			{
				add(2000);
				add(10000);
				add(10000);
			}
		});
		assertEquals(resultCode, Vending.SUCCESS_BUY);
		assertEquals(item.getStock(), 27);
	}
	
	@Test
	void emptyStock() {
		Item item = this.vending.getItems().get(0);
		assertEquals(item.getName(), "Aqua");
		assertEquals(item.getStock(), 0);
		Integer resultCode = this.vending.buy(item, 2, new ArrayList<Integer>() {
			{
				add(10000);
			}
		});
		assertEquals(resultCode, Vending.ERROR_EMPTY_STOCK);
		assertEquals(item.getStock(), 0);
	}

	@Test
	void invalidMoney() {
		Item item = this.vending.getItems().get(2);
		assertEquals(item.getName(), "Pocari Sweat");
		assertEquals(item.getStock(), 40);
		Integer resultCode = this.vending.buy(item, 2, new ArrayList<Integer>() {
			{
				add(6000);
				add(4000);
				add(10000);
			}
		});
		assertEquals(resultCode, Vending.ERROR_INVALID_MONEY);
		assertEquals(item.getStock(), 40);
	}
	
	@Test
	void lessMoney() {
		Item item = this.vending.getItems().get(2);
		assertEquals(item.getName(), "Pocari Sweat");
		assertEquals(item.getStock(), 40);
		Integer resultCode = this.vending.buy(item, 2, new ArrayList<Integer>() {
			{
				add(2000);
				add(5000);
				add(10000);
			}
		});
		assertEquals(resultCode, Vending.ERROR_LESS_MONEY);
		assertEquals(item.getStock(), 40);
	}


	@AfterEach
	void terminate() {
		this.vending = null;
	}
}
