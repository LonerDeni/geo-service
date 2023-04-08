import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

public class GeoServiceImplTest {
    GeoService geoService = new GeoServiceImpl();

    @ParameterizedTest
    @ValueSource(strings = {"172.0.32.11", "172.255.255.99"})
    public void geoServiceMoscow(String ips) {
        String expected = "Moscow";

        Location location = geoService.byIp(ips);

        Assertions.assertEquals(expected, location.getCity());
    }

    @ParameterizedTest
    @ValueSource(strings = {"96.44.183.149", "96.255.172.20"})
    public void geoServiceNewYork(String ips) {
        String expected = "New York";

        Location location = geoService.byIp(ips);

        Assertions.assertEquals(expected, location.getCity());
    }

    @Test
    public void geoServiceLocal() {
        String ip = "127.0.0.1";

        Location location = geoService.byIp(ip);

        Assertions.assertNull(location.getCity());
    }

    @Test
    public void geoServiceNull() {
        String ip = "255.2555.0.1";

        Location location = geoService.byIp(ip);

        Assertions.assertNull(location);
    }
}