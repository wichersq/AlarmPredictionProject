import com.google.maps.model.DirectionsResult;
import com.google.maps.model.PlaceDetails;

import java.time.LocalDateTime;

/**
 * A class store extra information about the event (only use to collect training data)
 */
public class GooglePlaceInfo {
    private PlaceDetails destDetails ;
    private PlaceDetails originDetails;
    private DirectionsResult mapResult;
    private LocalDateTime departureTime;
    private String[] priceLevel = {"FREE","INEXPENSIVE","MODERATE", "EXPENSIVE"};

    public GooglePlaceInfo(PlaceDetails destDetails,
            PlaceDetails originDetails , DirectionsResult mapResult){
        this.destDetails = destDetails;
        this.originDetails = originDetails;
        this.mapResult = mapResult;
        departureTime = mapResult.routes[0].legs[0].departureTime;
    }

    public String getDestinationID(){
        return mapResult.geocodedWaypoints[1].placeId;
    }

    public String getOriginID(){
        return mapResult.geocodedWaypoints[0].placeId;
    }

    public String getDepartureDateTime() {
        return departureTime.toString();
    }

    public int getDateOfWeek(){
        return departureTime.getDayOfWeek().getValue();
    }

    public String[] getOpeningPeriod(){
        return destDetails.openingHours.weekdayText;
    }

    public int getDestPriceLevel(){
        String level =  destDetails.priceLevel.toString();
        if(level.equalsIgnoreCase("UNKNOWN"))
            return -1;
        for(int n = 0; n < priceLevel.length; n++){
            if(level.equalsIgnoreCase(priceLevel[n])){
                return n+1;
            }
        }
        return 0;
    }
    public String getDestPlaceType() {return mapResult.geocodedWaypoints[1].types.toString();}

    public float getDestinationRating(){return destDetails.rating;}
}
