package networking;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

//Because LocalDateTimes do not use time zones they are safe to map to Strings without use of SQL functions like date(), time() or datetime()
public class LocalDateTimeStringTypeConverter implements JsonDeserializer<LocalDateTime>, JsonSerializer<LocalDateTime> {


    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private static final DateTimeFormatter utcFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public LocalDateTime fromString(String date){

        String parsable = date;

        if(date.contains("Z"))
            return utcFormatter.parse(parsable, LocalDateTime.FROM);

        return formatter.parse(parsable, LocalDateTime.FROM);

    }


    public String fromDate(LocalDateTime date){

        return date == null ? null : formatter.format(date);

    }

    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        String date = json.getAsString();

        return fromString(date);
    }

    @Override
    public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(fromDate(src));
    }

}
