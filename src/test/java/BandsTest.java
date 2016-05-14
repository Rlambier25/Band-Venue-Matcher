import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
import org.fluentlenium.adapter.FluentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class BandsTest extends FluentTest{

  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @Test
  public void Band_instantiatesCorrectly_true() {
    Band myBand = new Band("Mars Volta");
    assertEquals(true, myBand instanceof Band);
  }

  @Test
  public void getName_bandInstantiatesWithName_String() {
    Band myBand = new Band("Red Hot ChiliPeppers");
    assertEquals("Red Hot ChiliPeppers", myBand.getName());
  }

  @Test
  public void all_emptyAtFirst_0() {
    assertEquals(0, Band.all().size());
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame_true() {
    Band firstBand = new Band("Blink-182");
    Band secondBand = new Band("Blink-182");
    assertTrue(firstBand.equals(secondBand));
  }

  @Test
  public void save_savesObjectInToTheDatabase_true() {
    Band myBand = new Band("21 Pil0ts");
    myBand.save();
    assertTrue(Band.all().get(0).equals(myBand));
  }

  @Test
  public void save_assignsIdToObject_int() {
    Band myBand = new Band("Chemical Brothers");
    myBand.save();
    Band savedBand = Band.all().get(0);
    assertEquals(myBand.getId(), savedBand.getId());
  }

  @Test
  public void find_findBandInDatabase_true() {
    Band myBand = new Band("Sia");
    myBand.save();
    Band savedBand = Band.find(myBand.getId());
    assertTrue(myBand.equals(savedBand));
  }

  @Test
  public void addVenue_addsVenueToBand_true() {
    Band myBand = new Band("City of Salt");
    myBand.save();
    Venue myVenue = new Venue("The Gorge");
    myVenue.save();
    myBand.addVenue(myVenue);
    Venue savedVenue = myBand.getVenues().get(0);
    assertTrue(myVenue.equals(savedVenue));
  }

  @Test
  public void getVenues_returnsAllVenues_List() {
    Band myBand = new Band("Modest Mouse");
    myBand.save();
    Venue myVenue = new Venue("Sydney Hall");
    myVenue.save();
    myBand.addVenue(myVenue);
    List savedVenue = myBand.getVenues();
    assertEquals(1, savedVenue.size());
  }

  // @Test
  // public void delete_deletesAllVenuesAndBandAssociations() {
  //   Band myBand = new Band("Beirut");
  //   myBand.save();
  //   Venue myVenue = new Venue("Anthony Hall");
  //   myVenue.save();
  //   myBand.addVenue(myVenue);
  //   myBand.delete();
  //   assertEquals(0, myVenue.getBand().size());
  // }
}
