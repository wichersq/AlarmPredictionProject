import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.PlacesApi;

import com.google.maps.DirectionsApi;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.TravelMode;

import java.time.Instant;
import java.util.GregorianCalendar;

/**
 * Class DataRequest pulls data from google about the specifed addresses inputted by the user.
 */
public class DataRequest {
    private DirectionsResult mapResult = null;
    private PlaceDetails details = null;
    private GeoApiContext context;
    private String placeId = "";
    private String destName;
    private String originName;
    private long durationSec;
    private long distanceMeter;
    private float rating;
    private String startAddress;
    private String endAddress;

    /**
     * Constructor for the class.
     * @param apiKey required key to call google API
     */
    public DataRequest(String apiKey){
        context = new GeoApiContext.Builder().apiKey(apiKey).build();
    }

    /**
     * Makes Google API call.
     * @param origin Beginning destination
     * @param destination Ending destination
     * @param travelMode Mode of transportation
     * @param arrivalTime Time that user must be at event
     */
    public void requestMapData(String origin, String destination, String travelMode, GregorianCalendar arrivalTime) {
        Instant time = arrivalTime.toInstant();
        try {
            DirectionsApiRequest mapRequest = DirectionsApi.getDirections(context, origin, destination)
                    .mode(TravelMode.valueOf(travelMode)).arrivalTime(time);
            mapResult = mapRequest.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        saveMapInformation();
        requestPlaceDetail();
    }

    /**
     * Saves the details of the event specified by the user.
     */
    private void savePlaceDetailsInfo() {
        originName = startAddress.split(",")[0];
        destName = details.name;
        rating = details.rating;
    }

    /**
     * Makes Place API call.
     */
    private void requestPlaceDetail() {
        try {
            details = PlacesApi.placeDetails(context, placeId).await();

        } catch (Exception e) {
            e.printStackTrace();
        }
        savePlaceDetailsInfo();
    }

    /**
     * Saves all necessary information.
     */
    private void saveMapInformation() {
        durationSec = mapResult.routes[0].legs[0].duration.inSeconds;
        distanceMeter = mapResult.routes[0].legs[0].distance.inMeters;
        placeId = mapResult.geocodedWaypoints[1].placeId;
        startAddress = mapResult.routes[0].legs[0].startAddress;
        endAddress = mapResult.routes[0].legs[0].endAddress;
    }

    /**
     * Accessor for originName.
     * @return name of beginning destination
     */
    public String getOriginName() {
        return originName;
    }

    /**
     *  Accessor for distanceMeter.
     * @return the distance from beginning destination to ending destination
     */
    public long getDistance() {
        return distanceMeter;
    }

    /**
     *  Accessor for destName.
     * @return the name of ending destination
     */
    public String getDestName() {
        return destName;
    }

    /**
     * Acessor for getRating.
     * @return the rate of the destination given by google
     */
    public float getRating() {
        return rating;
    }

    /**
     * Acessor for getDurationSec.
     * @return how long the travel time is to get from starting detination to ending destination
     */
    public long getDurationSec() {
        return durationSec;
    }

    /**
     * Acessor for getStartAddress.
     * @return the starting address
     */
    public String getStartAddress() {
        return startAddress;
    }

    /**
     * Acessor for getEndAddress.
     * @return the ending address
     */
    public String getEndAddress() {
        return endAddress;
    }
}
