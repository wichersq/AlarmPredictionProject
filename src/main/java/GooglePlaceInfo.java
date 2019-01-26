import com.google.maps.model.DirectionsResult;
import com.google.maps.model.PlaceDetails;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class store extra information about the event (only use to collect training data)
 */
public class GooglePlaceInfo implements Serializable, Cloneable{
    private String destinationID;
    private String originID;
    private String[] openPeriod;
    private String priceLevel;
    private String destPlaceType;
    private float destRating;

    public GooglePlaceInfo(String originID, String destinationID, String[] openPeriod, String priceLevel, String destPlaceType, float rating) {
        this.openPeriod = openPeriod;
        this.priceLevel = priceLevel;
        this.destPlaceType = destPlaceType;
        this.destRating = rating;
        this.destinationID = destinationID;
        this.originID = originID;
    }

    public String getDestinationID() {
        return destinationID;
    }

    public String getOriginID() {
        return originID;
    }

    public String getOpeningPeriod() {
        StringBuilder str = new StringBuilder();
        for (String s : openPeriod) {
            s = s.replace(", ","&");
            s = s.replace(" – ", " to ");
            System.out.println(s);
            str.append(s);
            str.append("/");
        }
        str.deleteCharAt(str.length() - 1);
        System.out.println(str);
        return str.toString();
    }

    public String getDestPriceLevel() {
        return priceLevel;
    }

//    public String getDestPlaceType() {
//        return destPlaceType;
//    }

    public float getDestinationRating() {
        return destRating;
    }
}
