package hu.tomi.shopfloor;

import hu.tomi.shopfloor.controller.ShopFloorController;

public class Main {

	public static void main(String[] args) {
		
		startApp();
	}
	
	private static void startApp() {
 
		ShopFloorController controller = new ShopFloorController();

        controller.startDesktop();
    }
}