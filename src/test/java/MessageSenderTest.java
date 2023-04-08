import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import java.util.Map;

public class MessageSenderTest {

    @Test
    public void testNewYork(){
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("96.44.183.149")).thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService,localizationService);
        String preference = messageSender.send(Map.of("x-real-ip","96.44.183.149"));

        String expected = "Welcome";

        Assertions.assertEquals(expected,preference);
    }

    @Test
    public void testMoscow(){
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("172.0.32.11")).thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService,localizationService);
        String preference = messageSender.send(Map.of("x-real-ip","172.0.32.11"));

        String expected = "Добро пожаловать";

        Assertions.assertEquals(expected,preference);
    }

    @Test
    public void testNeyYork(){
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("96.44.183.149")).thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService,localizationService);
        String preference = messageSender.send(Map.of("x-real-ip","96.44.183.149"));

        String expected = "Welcome";

        Assertions.assertEquals(expected,preference);
    }

    @Test
    public void testEmpty(){
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("96.0.32.11")).thenReturn(new Location("NewYork", Country.USA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService,localizationService);
        String preference = messageSender.send(Map.of("x-real-ip",""));

        String expected = "Welcome";

        Assertions.assertEquals(expected,preference);
    }
    @Test
    public void testNull(){
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("127.0.0.1")).thenReturn(new Location(null, null, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService,localizationService);
        String preference = messageSender.send(Map.of("real-ip","127.0.0.1"));

        String expected = "Welcome";

        Assertions.assertEquals(expected,preference);
    }
}
