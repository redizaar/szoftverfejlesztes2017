package swdevelop.BudgetManager.client.MainMenu;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import swdevelop.BudgetManager.client.BudgetManager;
import swdevelop.BudgetManager.client.Activity.BudgetManagerActivity;
import swdevelop.BudgetManager.client.Component.BudgetManagerComponent;
import swdevelop.BudgetManager.client.User.User;

public class ComponentHandler extends VerticalPanel{
	private Widget lastDisplayedWidget;
	private User user;
	ArrayList<BudgetManagerComponent> components=new ArrayList<BudgetManagerComponent>();
	public void addToComponents(BudgetManagerComponent component)
	{
		if(!components.contains(component))
		{
			components.add(component);
		}
	}
	public void ComponentActionPerformed(int action)
	{
		switch(action)
		{
			case 1://login performed
				user=BudgetManager.getLoggedInUser();
			break;
			case 2:
				//RecentActivity Clicked
				BudgetManagerActivity.getInstance(this);
			break;
		}
	}
	public ComponentHandler()
	{
		RootPanel.get().add(constructSite());
	}
	private ComponentHandler constructSite() {
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		Button recentActivity = new Button("Recent Activity");
		/*
		 * itt lesz majd a history miket vett
		 * lehessen szûrni dátum alapján
		 * keresni név szerint
		 * típus szerint
		 */
		Button graphs = new Button("Overview in Graphs");
		/*
		 * legyen pár megjelenítési fajta
		 * több szûrés szerint
		 * fajta , milyen drága, hónapos lebontás
		 */
		Button bigPurchases=new Button("Recent expensive items");
		/*
		 * ha az átlagnál magasabb az áruk jelenjenek meg itt
		 * mikor vette , mit , mennyiért .. ilyenek
		 */
		Button monthlyBudget=new Button("Monthly Budget");
		/*
		 * Be lehet állítani egy pénztárcát
		 * ha jön fizetés pl. 10.-én hozzáadja a megadott összeget
		 * max 100kt lehet havonta költeni , ha túl megy rajta akkor értesít
		 * be lehet állítani hogy pl. élelmiszerre 20k
		 * szórakozás 10k stb...
		 */
		Button familyBudget=new Button("Your family's budget (Optional)");
		/*
		 * ezt még nem tudom
		 * tanárnõ kérte
		 */
		recentActivity.setStyleName("sendButton");
		graphs.setStyleName("sendButton");
		bigPurchases.setStyleName("sendButton");
		monthlyBudget.setStyleName("sendButton");
		familyBudget.setStyleName("sendButton");
		horizontalPanel.add(recentActivity);
		horizontalPanel.add(graphs);
		horizontalPanel.add(bigPurchases);
		horizontalPanel.add(monthlyBudget);
		horizontalPanel.add(familyBudget);
		horizontalPanel.setSpacing(20);
		this.add(horizontalPanel);
		return this;
	}
	public void displayComponent(Widget widget)
	{
		if(lastDisplayedWidget==widget)
		{
			return;
		}
		else
		{
			lastDisplayedWidget.setVisible(false);
			widget.setVisible(true);
			lastDisplayedWidget=widget;
		}
	}
}
