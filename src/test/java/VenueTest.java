import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;

public class VenueTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void venue_instantiatesCorrectly_true() {
    Venue myVenue = new Venue("Tacoma Dome");
    assertEquals(true, myVenue instanceof Venue);
  }

  @Test
  public void getName_taskInstantiatesWithName_String() {
    Venue myVenue = new Venue("Tacoma Dome");
    assertEquals("Tacoma Dome", myVenue.getName());
  }

  @Test
  public void all_emptyAtFirst_0() {
    assertEquals(0, Venue.all().size());
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame_true() {
    Venue firstVenue = new Venue("Moda Center");
    Venue secondVenue = new Venue("Moda Center");
    assertTrue(firstVenue.equals(secondVenue));
  }

  @Test
  public void save_savesObjectInToTheDatabase_true() {
    Venue myVenue = new Venue("Columbia Center");
    myVenue.save();
    assertTrue(Venue.all().get(0).equals(myVenue));
  }

  @Test
  public void save_assaingsIdToObject_int() {
    Venue myVenue = new Venue("Moda Center");
    myVenue.save();
    Venue savedVenue = Venue.all().get(0);
    assertEquals(myVenue.getId(), savedVenue.getId());
  }

  @Test
  public void find_findsVenuesInDatabase_true() {
    Venue myVenue = new Venue("Moda Center");
    myVenue.save();
    Venue saveVenue = Venue.find(myVenue.getId());
    assertTrue(myVenue.equals(saveVenue));
  }

  @Test
  public void update_updatesVenuesName_true() {
    Venue myVenue = new Venue("SLC City Center");
    myVenue.save();
    myVenue.update("Moda Center");
    assertEquals("Moda Center", Venue.find(myVenue.getId()).getName());
  }

  @Test
  public void delete_deleteVenues_true() {
    Venue myVenue = new Venue("Tacoma Dome");
    myVenue.save();
    int myVenueId = myVenue.getId();
    myVenue.delete();
    assertEquals(null, Venue.find(myVenueId));
  }

@Test
public void addBand_addsBandToVenue_true() {
  Band myBand = new Band("Kid Cudi");
  myBand.save();
  Venue myVenue = new Venue("Moda Center");
  myVenue.save();
  myVenue.addBand(myBand);
  Band savedBand = myVenue.getBands().get(0);
  assertTrue(myBand.equals(savedBand));
}

@Test
public void getBands_returnsAllBands_List() {
  Band myBand = new Band("Matisyahu");
  myBand.save();
  Venue myVenue = new Venue("Moda Center");
  myVenue.save();
  myVenue.addBand(myBand);
  List savedBands = myVenue.getBands();
  assertEquals(1, savedBands.size());
}

@Test
public void delete_deletesAllVenuesAndBandsAssociations() {
  Band myBand = new Band("Matisyahu");
  myBand.save();
  Venue myVenue = new Venue("Moda Center");
  myVenue.save();
  myVenue.addBand(myBand);
  myVenue.delete();
  assertEquals(0, myBand.getVenues().size());
}
}
