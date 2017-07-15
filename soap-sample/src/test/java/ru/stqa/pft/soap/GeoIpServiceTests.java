package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("93.125.65.85");
    assertEquals(geoIP.getCountryCode(), "BLR");
  }

  @Test
  public void testInvalidIp() {
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("93.125.65.xx");
    assertEquals(geoIP.getCountryCode(), "RUS");
  }
}
