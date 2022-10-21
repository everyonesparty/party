package everyonesparty.party.presentation.exception.error;


import java.util.HashMap;
import java.util.Map;

public class PresentaionErrorMap {

    private static final Map<String, PresentationError> errorMap = new HashMap<>();

    public static void setError(String key, PresentationError presentationError){
        errorMap.put(key, presentationError);
    }

    public static PresentationError getError(String key){
        return errorMap.get(key);
    }
}
