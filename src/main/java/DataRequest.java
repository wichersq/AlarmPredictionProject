import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.PlacesApi;

import com.google.maps.DirectionsApi;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.TravelMode;

import java.time.Instant;
import java.util.GregorianCalendar;


public class DataRequest {
    private static DirectionsResult mapResult = null;
    private static PlaceDetails details = null;
    private static GeoApiContext context = new GeoApiContext.Builder()
            .apiKey("")
            .build();
    private static String placeId = "";
    private static String destName;
    private static String originName;
    private static long durationSec;
    private static long distanceMeter;
    private static float rating;
    private static String startAddress;
    private static String endAddress;

    public static void pullMapRequest(String origin, String destination, String travelMode, GregorianCalendar arrivalTime) {
        Instant time = arrivalTime.toInstant();
        try {
            DirectionsApiRequest mapRequest = DirectionsApi.getDirections(context, origin, destination)
                    .mode(TravelMode.DRIVING).arrivalTime(time);
            mapResult = mapRequest.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        getMapInformation();
        pullPlaceDetailRequest();
    }

    public static void getMapInformation() {
        durationSec = mapResult.routes[0].legs[0].duration.inSeconds;
        distanceMeter = mapResult.routes[0].legs[0].distance.inMeters;
        placeId = mapResult.geocodedWaypoints[1].placeId;
        startAddress = mapResult.routes[0].legs[0].startAddress;
        endAddress = mapResult.routes[0].legs[0].endAddress;
    }

    public static String getOriginName() {
        return originName;
    }

    public static long getDistance() {
        return distanceMeter;
    }

    public static String getDestName() {
        return destName;
    }

    public static float getRating() {
        return rating;
    }

    public static long getDurationSec() {
        return durationSec;
    }

    public static String getStartAddress() {
        return startAddress;
    }

    public static String getEndAddress() {
        return endAddress;
    }

    public static void getPlaceDetailsInfo() {
        originName = startAddress.split(",")[0];
        destName = details.name;
        rating = details.rating;
    }

    private static void pullPlaceDetailRequest() {
        try {
            details = PlacesApi.placeDetails(context, placeId).await();

        } catch (Exception e) {
            System.out.println("Can't do the data result");
        }
        getPlaceDetailsInfo();
    }

    public static void main(String[] args) {
        String dest = "570 N Shoreline Blvd Mountain View";
        String origin = "189 Central Ave Mountain View CA";
        DataRequest.pullMapRequest(origin, dest, "DRIVING",
                new GregorianCalendar(12, 12, 2018, 14, 15));
        System.out.println("Distance: " + getDistance());
        System.out.println("Duration: " + getDurationSec());
        System.out.println("Destination name " + getDestName());
        System.out.println("Rating " + getRating());
        System.out.println("Start Address: " + getStartAddress());
        System.out.println("End Address " + getEndAddress());
//                "ChIJwUcmtIJZwokREiWU5DTPSJA"
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