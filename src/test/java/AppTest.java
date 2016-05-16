import org.sql2o.*;
import org.junit.*;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.junit.Assert.*;

public class AppTest extends FluentTest {
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
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("BAND TRACKER");
  }

  @Test
  public void bandIsCreatedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Bands"));
    fill("#name").with("Add Band");
    submit(".btn");
    assertThat(pageSource()).contains("Nirvana");
  }

  @Test
  public void venueIsCreatedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Venues"));
    fill("#description").with("Moda Center");
    submit(".btn");
    assertThat(pageSource()).contains("Moda Center");
  }

  @Test
  public void bandShowPageDisplaysName() {
    Band testBand = new Band("Smashing Pumpkins");
    testBand.save();
    String url = String.format("http://localhost:4567/bands/%d", testBand.getId());
    goTo(url);
    assertThat(pageSource()).contains("Smashing Pumpkins");
  }

  @Test
  public void venueShowPageDisplaysName() {
    Venue testVenue = new Venue("Tacoma Dome");
    testVenue.save();
    String url = String.format("http://localhost:4567/venues/%d", testVenue.getId());
    goTo(url);
    assertThat(pageSource()).contains("Tacoma Dome");
  }

  @Test
  public void venueIsAddedToBand() {
    Band testBand = new Band("Smashing Pumpkins");
    testBand.save();
    Venue testVenue = new Venue("Tacoma Dome");
    testVenue.save();
    String url = String.format("http://localhost:4567/bands/%d", testBand.getId());
    goTo(url);
    fillSelect("#venue_id").withText("Tacoma Dome");
    submit(".btn");
    assertThat(pageSource()).contains("<li>");
    assertThat(pageSource()).contains("Tacoma Dome");
  }

  @Test
  public void bandIsAddedToVenue() {
    Band testBand = new Band("Bob Marley");
    testBand.save();
    Venue testVenue = new Venue("Mow the lawn");
    testVenue.save();
    String url = String.format("http://localhost:4567/venues/%d", testVenue.getId());
    goTo(url);
    fillSelect("#band_id").withText("Bob Marley");
    submit(".btn");
    assertThat(pageSource()).contains("<li>");
    assertThat(pageSource()).contains("Bob Marley");
  }

}
