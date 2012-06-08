package com.thingtrack.com.vaddin.addon.phonegap;

import java.util.Map;

import com.thingtrack.com.vaddin.addon.phonegap.GeoLocationListener.Position;
import com.thingtrack.com.vaddin.addon.phonegap.gwt.client.VPhoneGap;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ClientWidget;

@ClientWidget(VPhoneGap.class)
public class PhoneGap extends AbstractComponent {

	private GeoLocationListener geoLocationListener;

	public void addGeoLocationListener(GeoLocationListener listener) {

		geoLocationListener = listener;
	}

	@Override
	public void changeVariables(Object source, Map<String, Object> variables) {

		if (!variables.containsKey("position"))
			return;

		String[] values = (String[]) variables.get("position");
		updateGeoPosition(values);

		super.changeVariables(source, variables);
	}

	private void updateGeoPosition(String[] values) {

		if (geoLocationListener == null)
			return;

		Position position = new Position(Long.valueOf(values[0]),
				Double.valueOf(values[1]), Double.valueOf(values[2]),
				Double.valueOf(values[3]), Double.valueOf(values[4]),
				Double.valueOf(values[5]), Double.valueOf(values[6]),
				Double.valueOf(values[7]));
		
		geoLocationListener.onSuccess(position);

	}
}
