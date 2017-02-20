package hu.tomi.shopfloor.view;

import hu.tomi.shopfloor.view.ShopFloorMenu;
import hu.tomi.shopfloor.controller.ShopFloorController;

import java.awt.Container;

import javax.swing.JFrame;

public class ShopFloorGUI {
	
	private JFrame mainwindow;
	private ShopFloorController controller;
	
	public ShopFloorGUI (ShopFloorController controller) {
		this.controller = controller;
	}
	
    public void startGUI() {        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }			
        });
    }
    
    private void createAndShowGUI() {

    	String title = "Tomi fantasztikus Java projectje";
    	        
        mainwindow = new JFrame(title);
        
        mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ShopFloorMenu bookMenuBar = new ShopFloorMenu(this);
        
        mainwindow.setJMenuBar(bookMenuBar);
                
        mainwindow.setSize(800,600);
        
        mainwindow.setVisible(true);
	}
	
    public void setContent(Container container) {
        mainwindow.setContentPane(container);
        mainwindow.setVisible(true);
    }
    
    public JFrame getWindow() {
        return mainwindow;
    }
    
    public ShopFloorController getController() {
        return controller;
    }
	
	

}