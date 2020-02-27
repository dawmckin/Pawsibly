private final LocationListener mLocationListener = new LocationListener() {
@Override
public void onLocationChanged(final Location location) {
        //your code here
        }
        };

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
        LOCATION_REFRESH_DISTANCE, mLocationListener);
        }

