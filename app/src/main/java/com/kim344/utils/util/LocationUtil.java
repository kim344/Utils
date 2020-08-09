package com.kim344.utils.util;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;

public class LocationUtil  {

    private static final String TAG = "LocationUtil";

    private OnLocationListener listener;

    private LocationManager locationManager;

    public LocationListener[] locationListeners = new LocationListener[]{
            new LocationListener(LocationManager.GPS_PROVIDER),
            new LocationListener(LocationManager.NETWORK_PROVIDER)
    };

    public interface OnLocationListener {
        void onLocationChange(double lon, double lat);
    }

    public LocationUtil(Context context, OnLocationListener listener) {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        this.listener = listener;
    }

    public boolean isProviderEnabled() {
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            return true;
        } else {
            return false;
        }
    }

    private class LocationListener implements android.location.LocationListener {

        Location lastLocation;
        boolean valid = false;
        String provider;

        public LocationListener(String provider) {
            this.provider = provider;
            lastLocation = new Location(provider);
        }

        // Called when the location has changed.
        public void onLocationChanged(Location newLocation) {
            if (newLocation.getLatitude() == 0.0 && newLocation.getLongitude() == 0.0) {
                return;
            }
            if (newLocation != null) {
                newLocation.setTime(System.currentTimeMillis());

                if (newLocation.getLatitude() != 0.0 && newLocation.getLongitude() != 0.0) {
                    listener.onLocationChange(newLocation.getLongitude(), newLocation.getLatitude());
                }
            }

            lastLocation.set(newLocation);
            valid = true;
        }

        // Called when the provider is enabled by the user.
        public void onProviderEnabled(String provider) {
        }

        // Called when the provider is disabled by the user.
        public void onProviderDisabled(String provider) {
            valid = false;
        }

        // Called when the provider status changes.
        public void onStatusChanged(String provider, int status, Bundle extras) {
            if (status == LocationProvider.OUT_OF_SERVICE) {
                valid = false;
            }
        }

        public Location current() {
            return valid ? lastLocation : null;
        }
    }

    public void startLocationReceiving() {
        try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0F, locationListeners[1]);
        } catch (SecurityException e) {
            Log.e(TAG, e.toString());
        } catch (IllegalArgumentException e) {
            Log.e(TAG, e.toString());
        }

        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0F, locationListeners[0]);
        } catch (SecurityException e) {
            Log.e(TAG, e.toString());
        } catch (IllegalArgumentException e) {
            Log.e(TAG, e.toString());
        }
    }

    public void stopLocationReceiving() {
        for (LocationListener locationListener : locationListeners) {
            try {
                locationManager.removeUpdates(locationListener);
            } catch (IllegalArgumentException e) {
                Log.e(TAG, e.toString());
            }
        }
    }

    public Location getCurrentLocation() {
        Location l = null;
        for (LocationListener locationListener : locationListeners) {
            l = locationListener.current();
            if (l != null) {
                break;
            }
        }
        return l;
    }

}
