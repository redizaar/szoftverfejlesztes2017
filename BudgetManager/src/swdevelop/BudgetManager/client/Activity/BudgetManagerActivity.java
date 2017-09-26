package swdevelop.BudgetManager.client.Activity;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import swdevelop.BudgetManager.client.Component.BudgetManagerComponent;
import swdevelop.BudgetManager.client.MainMenu.ComponentHandler;

public class BudgetManagerActivity extends BudgetManagerComponent{
	private static BudgetManagerActivity instance=null;
	public static BudgetManagerActivity getInstance(ComponentHandler handler)
	{
		if(instance==null)
		{
			instance=new BudgetManagerActivity(handler);
			handler.addToComponents(instance);
		}
		return instance;
	}
	public BudgetManagerActivity(ComponentHandler handler)
	{
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		Button addButton=new Button("Add new items");
		Label date = new Label("Date");
		Label item_name = new Label("Item's name");
	}
}
