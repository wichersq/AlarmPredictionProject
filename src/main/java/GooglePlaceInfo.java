import com.google.maps.model.DirectionsResult;
import com.google.maps.model.PlaceDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class store extra information about the event (only use to collect training data)
 */
public class GooglePlaceInfo implements Serializable {
    private String[] priceLevelType = {"FREE", "INEXPENSIVE", "MODERATE", "EXPENSIVE"};
    private String destinationID;
    private String originID;
    private String[] openPeriod;
    private int priceLevel;
    private String destPlaceType;
    private float destRating;

    public GooglePlaceInfo(String originID, String destinationID,String[] openPeriod, String priceLevel, String destPlaceType, float rating){
        this.openPeriod = openPeriod;
        this.priceLevel = setDestPriceLevel(priceLevel);
        this.destPlaceType =destPlaceType;
        this.destRating = rating;
        this.destinationID = destinationID;
        this.originID =originID;
    }

    public String getDestinationID() {
        return destinationID;
    }

    public String getOriginID() {
        return originID;
    }

    public String[] getOpeningPeriod() {
        return openPeriod;
    }

    private int setDestPriceLevel(String priceLevel) {

        if (priceLevel.equalsIgnoreCase("UNKNOWN"))
            return -1;
        for (int n = 0; n < priceLevelType.length; n++) {
            if (priceLevel.equalsIgnoreCase(priceLevelType[n])) {
                return n + 1;
            }
        }
        return 0;
    }
    public int getDestPriceLevel() {
        return priceLevel;
    }

//    public String getDestPlaceType() {
//        return destPlaceType;
//    }

    public float getDestinationRating() {
        return destRating;
    }
}
