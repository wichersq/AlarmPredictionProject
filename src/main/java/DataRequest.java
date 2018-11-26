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
 * Class DataRequest pulls data from google about the specifed addresses inputted by the user
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
     * 
     * @param apiKey 
     */
    public DataRequest(String apiKey){
        context = new GeoApiContext.Builder().apiKey(apiKey).build();
    }

    /**
     *
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
     * Saves the details of the event specified by the user
     */
    private void savePlaceDetailsInfo() {
        originName = startAddress.split(",")[0];
        destName = details.name;
        rating = details.rating;
    }

    /**
     * 
     */
    private void requestPlaceDetail() {
        try {
            details = PlacesApi.placeDetails(context, placeId).await();

        } catch (Exception e) {
            System.out.println("Can't do the data result");
        }
        savePlaceDetailsInfo();
    }

    /**
     * 
     */
    private void saveMapInformation() {
        durationSec = mapResult.routes[0].legs[0].duration.inSeconds;
        distanceMeter = mapResult.routes[0].legs[0].distance.inMeters;
        placeId = mapResult.geocodedWaypoints[1].placeId;
        startAddress = mapResult.routes[0].legs[0].startAddress;
        endAddress = mapResult.routes[0].legs[0].endAddress;
    }

    /**
     *
     * @return returns name of beginning destination
     */
    public String getOriginName() {
        return originName;
    }

    /**
     *
     * @return returns the distance from beginning destination to ending destination
     */
    public long getDistance() {
        return distanceMeter;
    }

    /**
     *
     * @return returns the name of ending destination 
     */
    public String getDestName() {
        return destName;
    }

    /**
     *
     * @return returns the rate of the destination given by google
     */
    public float getRating() {
        return rating;
    }

    /**
     *
     * @return returns how long the travel time is to get from starting detination to ending destination
     */
    public long getDurationSec() {
        return durationSec;
    }

    /**
     *
     * @return returns the starting address
     */
    public String getStartAddress() {
        return startAddress;
    }

    /**
     *
     * @return returns the ending address
     */
    public String getEndAddress() {
        return endAddress;
    }

    public static void main(String[] args) {
//        DataRequest dataRequest = new DataRequest("");

//        String dest = "570 N Shoreline Blvd Mountain View";
//        String origin = "189 Central Ave Mountain View CA";
//        dataRequest.requestMapData(origin, dest, "DRIVING",
//                new GregorianCalendar(12, 12, 2018, 14, 15));


//        System.out.println("Distance: " + getDistance());
//        System.out.println("Duration: " + getDurationSec());
//        System.out.println("Destination name " + getDestName());
//        System.out.println("Rating " + getRating());
//        System.out.println("Start Address: " + getStartAddress());
//        System.out.println("End Address " + getEndAddress());
////                "ChIJwUcmtIJZwokREiWU5DTPSJA"
//            details.rating.
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            System.out.println(gson.toJson(details));
//
////            GeocodingResult[] results = GeocodingApi.geocode(context,
////                    "1600 Amphitheatre Parkway Mountain View, CA 94043").await();
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            System.out.println(gson.toJson(result));
//
////            PlacesApi.findPlaceFromText(context, "google quad", );
//
//
//        } catch(Exception e){}
    }
}
