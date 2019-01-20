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
    public String getDestPriceLevel(){return destDetails.priceLevel.toString();}
    public String getDestPlaceType() {return mapResult.geocodedWaypoints[1].types.toString();}
}
