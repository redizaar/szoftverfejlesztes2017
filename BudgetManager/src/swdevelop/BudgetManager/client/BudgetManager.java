package swdevelop.BudgetManager.client;

import java.io.IOException;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import swdevelop.BudgetManager.client.User.User;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class BudgetManager implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	public static User user;
	/**
	 * This is the entry point method.
	 */
	public final static BudgetManager instance=null;
	public void onModuleLoad() {
		final Button loginButton = new Button("Login");
		final Button registerButton = new Button("Register");
		final TextBox usernameTextBox = new TextBox();
		final Label userLabel = new Label();
		userLabel.setText("Username: ");
		final PasswordTextBox passwordTextBox = new PasswordTextBox();
		final Label passwordLabel = new Label();
		passwordLabel.setText("Password: ");
		// We can add style names to widgets
		userLabel.setStyleName("loginLabel");
		passwordLabel.setStyleName("loginLabel");
		
		loginButton.setStyleName("sendButton");
		registerButton.setStyleName("sendButton");
		final VerticalPanel verticalPanel = new VerticalPanel();
		HorizontalPanel horizontal1 = new HorizontalPanel();
		horizontal1.add(userLabel);
		horizontal1.add(usernameTextBox);
		final HorizontalPanel horizontal2 = new HorizontalPanel();
		horizontal2.add(passwordLabel);
		horizontal2.add(passwordTextBox);
		verticalPanel.add(horizontal1);
		verticalPanel.add(horizontal2);
		
		final Label registerLabel= new Label();
		registerLabel.setText("Don't have an account ?");		
		
		verticalPanel.getElement().setAttribute("align", "center");
		// Use RootPanel.get() to get the entire body element
		RootPanel.get().add(verticalPanel);
		final VerticalPanel vp=new VerticalPanel();
		vp.add(loginButton);
		vp.add(registerLabel);
		vp.add(registerButton);
		vp.getElement().setAttribute("align", "center");
		RootPanel.get().add(vp);
		// Focus the cursor on the name field when the app loads
		usernameTextBox.setFocus(true);

		
		loginButton.addClickHandler(new ClickHandler() {		
			@Override
			public void onClick(ClickEvent event) {
				usernameTextBox.setEnabled(false);
				passwordTextBox.setEnabled(false);
				registerButton.setEnabled(false);
				try {
					greetingService.greetServer(usernameTextBox.getText(),
							passwordTextBox.getText()
							, new AsyncCallback<Integer>() {
								
								@Override
								public void onSuccess(Integer result) {
									//succesfull login
									if(result!=0)
									{
										user=new User(usernameTextBox.getText(),result);
										verticalPanel.setVisible(false);
										vp.setVisible(false);
										Window.alert("Sikeres bejelentkezes!");
										//new ComponentHandler();
									}
									else
									{
										Window.alert("Hibas jelszo vagy felhasznalo");
									}
								}
								
								@Override
								public void onFailure(Throwable caught) {
									//server failure
								}
							});
				} catch (IOException e) {
					Window.alert("nincs meg az xls file");
					e.printStackTrace();
				}
			}
		});
		
		registerButton.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				loginButton.setVisible(false);
				registerLabel.setVisible(false);
				registerButton.setVisible(false);
				userLabel.setText("Username: ");
				final Label passwordAgainLabel = new Label("  Type in your password again: ");
				final PasswordTextBox passwordAgain = new PasswordTextBox();
				horizontal2.add(passwordAgainLabel);
				horizontal2.add(passwordAgain);
				final Button newRegisertButton=new Button("Register");
				newRegisertButton.setStyleName("buttons");
				verticalPanel.add(newRegisertButton);
				newRegisertButton.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						if(!usernameTextBox.getText().equals(""))
						{
							if(!passwordTextBox.getText().equals(""))
							{
								if(passwordTextBox.getText().equalsIgnoreCase(passwordAgain.getText()))
								{
									try {
										greetingService.registerUser(usernameTextBox.getText(),
												passwordTextBox.getText(),new AsyncCallback<Boolean>() {

											@Override
											public void onFailure(Throwable caught) {
												//nincs meg a file
											}

											@Override
											public void onSuccess(Boolean result) {
												if(result)
												{
													Window.alert("Sikeresen regisztralva!");
													loginButton.setVisible(true);
													registerLabel.setVisible(true);
													registerButton.setVisible(true);
													newRegisertButton.setVisible(false);
													passwordAgainLabel.setVisible(false);
													passwordAgain.setVisible(false);
													passwordTextBox.setText("");
													usernameTextBox.setText("");
												}
												else
												{
													Window.alert("Felhasznalo foglalt!");
												}
											}
										});
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							}
							else
							{
								Window.alert("Give a valid password please");
							}
						}
						else
						{
							Window.alert("Give a valid username please");
						}
					}
				});
			}
		});
		
		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendNameToServer() {
			}
		}
	}
	public static User getLoggedInUser()
	{
		return user;
	}
}

