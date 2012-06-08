package com.thingtrack.com.vaddin.addon.phonegap.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableEvent;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableHandler;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutEvent;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutHandler;
import com.googlecode.gwtphonegap.client.geolocation.Coordinates;
import com.googlecode.gwtphonegap.client.geolocation.GeolocationCallback;
import com.googlecode.gwtphonegap.client.geolocation.GeolocationOptions;
import com.googlecode.gwtphonegap.client.geolocation.Position;
import com.googlecode.gwtphonegap.client.geolocation.PositionError;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.Paintable;
import com.vaadin.terminal.gwt.client.UIDL;

public class VPhoneGap extends Widget implements Paintable {

	/** Set the CSS class name to allow styling. */
	public static final String CLASSNAME = "v-phonegap";

	/** Component identifier in UIDL communications. */
	String uidlId;

	/** Reference to the server connection object. */
	ApplicationConnection client;

	/** PhoneGap object **/
	private PhoneGap phoneGap;

	public VPhoneGap() {

		final PhoneGap phoneGap = GWT.create(PhoneGap.class);

		// This method call of the Paintable interface sets
		// the component style name in DOM tree
		setStyleName(CLASSNAME);
	}

	/**
	 * This method must be implemented to update the client-side component from
	 * UIDL data received from server.
	 * 
	 * This method is called when the page is loaded for the first time, and
	 * every time UI changes in the component are received from the server.
	 */
	public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {

		// This call should be made first. Ensure correct
		// implementation, and let the containing layout
		// manage the caption, etc.
		if (client.updateComponent(this, uidl, true))
			return;

		// Save reference to server connection object to be
		// able to send user interaction later
		this.client = client;

		// Save the UIDL identifier for the component
		uidlId = uidl.getId();

		// Initialize phonegap
		if (phoneGap.isPhoneGapInitialized())
			return;

		phoneGap.addHandler(new PhoneGapAvailableHandler() {

			public void onPhoneGapAvailable(PhoneGapAvailableEvent event) {
				startGapModules();
			}
		});

		phoneGap.addHandler(new PhoneGapTimeoutHandler() {

			public void onPhoneGapTimeout(PhoneGapTimeoutEvent event) {
				// TODO Auto-generated method stub

			}
		});

		phoneGap.initializePhoneGap();

	}

	private void startGapModules() {

		watchGeoPosition();

	}

	private void watchGeoPosition() {

		GeolocationOptions options = new GeolocationOptions();
		options.setFrequency(1000);

		phoneGap.getGeolocation().watchPosition(options,
				new GeolocationCallback() {

					@Override
					public void onSuccess(Position position) {

						Coordinates coordinates = position.getCoordinates();

						String[] values = new String[] {
								String.valueOf(position.getTimeStamp()),
								String.valueOf(coordinates.getAccuracy()),
								String.valueOf(coordinates.getAltitude()),
								String.valueOf(coordinates
										.getAltitudeAccuracy()),
								String.valueOf(coordinates.getHeading()),
								String.valueOf(coordinates.getLatitude()),
								String.valueOf(coordinates.getLongitude()),
								String.valueOf(coordinates.getSpeed()) };
						
						client.updateVariable(uidlId, "position", values, true);
					}

					@Override
					public void onFailure(PositionError error) {
						// TODO Auto-generated method stub

					}
				});
	}

	private void updateVariable(String variable, String value) {

		// Updating the state to the server can not be done
		// before the server connection is known, i.e., before
		// updateFromUIDL() has been called.
		if (uidlId == null || client == null)
			return;

		// Communicate the user interaction parameters to server.
		// This call will initiate an AJAX request to the server.
		client.updateVariable(uidlId, variable, value, true);
	}

}
