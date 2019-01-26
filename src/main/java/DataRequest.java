import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.PlacesApi;
import com.google.maps.DirectionsApi;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.TravelMode;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.GregorianCalendar;

/**
 * Class DataRequest pulls data from google about the specifed addresses inputted by the user.
 */
public class DataRequest {
    private double METER_PER_MILE = 1609.34;
    private DirectionsResult mapResult = null;
    private PlaceDetails destDetails = null;
    private PlaceDetails originDetails = null;
    private GeoApiContext context;
    private String destinationID = "";
    private String originID = "";
    private String destName = "Invalid";
    private String originName = "Invalid";
    private long durationSec = 0;
    private long distanceMeter;
    private float rating = 0;
    private String startAddress = "Invalid Address";
    private String endAddress = "Invalid Address";
    private String[] openPeriod = {"NAN"};
    private String priceLevel = "NAN";
    private String destPlaceType = "NAN";

    /**
     * Constructor for the class.
     *
     * @param apiKey required key to call google API
     */
    public DataRequest(String apiKey) {
        context = new GeoApiContext.Builder().apiKey(apiKey).build();
    }

    /**
     * Makes Google API call.
     *
     * @param origin      Beginning destination
     * @param destination Ending destination
     * @param travelMode  Mode of transportation
     * @param arrivalTime Time that user must be at event
     */
    public boolean requestMapData(String origin, String destination, String travelMode, GregorianCalendar arrivalTime) {
        Instant time = arrivalTime.toInstant();
        resetDetail();
        try {
            DirectionsApiRequest mapRequest = DirectionsApi.getDirections(context, origin, destination)
                    .mode(TravelMode.valueOf(travelMode)).arrivalTime(time);
            mapResult = mapRequest.await();
            saveMapInformation();
            return requestPlaceDetail();
        } catch (Exception e) {
            return false;
        }

    }

    private void resetDetail() {
        openPeriod = new String[1];
        openPeriod[0] = "NAN";
        priceLevel = "NAN";
        destPlaceType = "NAN";
        destinationID = "";
        originID = "";
        destName = "Invalid";
        originName = "Invalid";
        durationSec = 0;
        distanceMeter = 0;
        rating = 0;
        startAddress = "Invalid Address";
        endAddress = "Invalid Address";
    }

    /**
     * Saves the destDetails of the event specified by the user.
     */
    private void savePlaceDetailsInfo() {
        originName = originDetails.name;
        destName = destDetails.name;


        try {
            rating = destDetails.rating;
        } catch (Exception e) {
        }
        try {
            openPeriod = destDetails.openingHours.weekdayText;
        } catch (Exception e) {
        }
        try {
            priceLevel = destDetails.priceLevel.toString();
        } catch (Exception e2) {
        }
        try {
            destPlaceType = mapResult.geocodedWaypoints[1].types.toString();
        } catch (Exception e3) {
        }
    }

    /**
     * Makes Place API call.
     */
    private boolean requestPlaceDetail() {
        try {
            destDetails = PlacesApi.placeDetails(context, destinationID).await();
            originDetails = PlacesApi.placeDetails(context, originID).await();
            savePlaceDetailsInfo();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Saves all necessary information.
     */
    private void saveMapInformation() {
        durationSec = mapResult.routes[0].legs[0].duration.inSeconds;
        distanceMeter = mapResult.routes[0].legs[0].distance.inMeters;
        destinationID = mapResult.geocodedWaypoints[1].placeId;
        originID = mapResult.geocodedWaypoints[0].placeId;
        startAddress = mapResult.routes[0].legs[0].startAddress;
        endAddress = mapResult.routes[0].legs[0].endAddress;
    }

    /**
     * Accessor for originName.
     *
     * @return name of beginning destination
     */
    public String getOriginName() {
        return originName;
    }

    /**
     * Accessor for distance in mile.
     *
     * @return the distance from beginning destination to ending destination
     */
    public int getDistance() {
        return (int) (distanceMeter / METER_PER_MILE);
    }

    /**
     * Accessor for destName.
     *
     * @return the name of ending destination
     */
    public String getDestName() {
        return destName;
    }

    /**
     * Accessor for Rating.
     *
     * @return the rate of the destination given by google
     */
    public float getRating() {
        return rating;
    }

    /**
     * Accessor for DurationSec.
     *
     * @return how long the travel time is to get from starting destination to ending destination
     */
    public long getDurationSec() {
        return durationSec;
    }

    /**
     * Accessor for StartAddress.
     *
     * @return the starting address
     */
    public String getStartAddress() {
        return startAddress;
    }

    /**
     * Accessor for EndAddress.
     *
     * @return the ending address
     */
    public String getEndAddress() {
        return endAddress;
    }

    public GooglePlaceInfo getGooglePlaceInfo() {
        return new GooglePlaceInfo(originID, destinationID, openPeriod, priceLevel, destPlaceType, rating);
    }
}
