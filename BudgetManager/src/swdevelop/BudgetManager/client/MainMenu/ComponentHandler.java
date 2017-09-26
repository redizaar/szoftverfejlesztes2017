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
		 * lehessen sz�rni d�tum alapj�n
		 * keresni n�v szerint
		 * t�pus szerint
		 */
		Button graphs = new Button("Overview in Graphs");
		/*
		 * legyen p�r megjelen�t�si fajta
		 * t�bb sz�r�s szerint
		 * fajta , milyen dr�ga, h�napos lebont�s
		 */
		Button bigPurchases=new Button("Recent expensive items");
		/*
		 * ha az �tlagn�l magasabb az �ruk jelenjenek meg itt
		 * mikor vette , mit , mennyi�rt .. ilyenek
		 */
		Button monthlyBudget=new Button("Monthly Budget");
		/*
		 * Be lehet �ll�tani egy p�nzt�rc�t
		 * ha j�n fizet�s pl. 10.-�n hozz�adja a megadott �sszeget
		 * max 100kt lehet havonta k�lteni , ha t�l megy rajta akkor �rtes�t
		 * be lehet �ll�tani hogy pl. �lelmiszerre 20k
		 * sz�rakoz�s 10k stb...
		 */
		Button familyBudget=new Button("Your family's budget (Optional)");
		/*
		 * ezt m�g nem tudom
		 * tan�rn� k�rte
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
