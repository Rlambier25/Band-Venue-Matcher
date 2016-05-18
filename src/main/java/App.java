import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/venues", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("venues", Venue.all());
      model.put("template", "templates/venues.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/bands", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("bands", Band.all());
      model.put("template", "templates/bands.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/bands", (request, response) -> {
      String name = request.queryParams("name");
      Band newBand = new Band(name);
      newBand.save();
      response.redirect("/bands");
      return null;
    });

    post("/venues", (request, response) -> {
      String name = request.queryParams("name");
      Venue newVenue = new Venue(name);
      newVenue.save();
      response.redirect("/venues");
      return null;
    });

    get("/bands/:id", (request,response) -> {
      //Hashmap is where the model stores the Strings and Objects
      HashMap<String, Object> model = new HashMap<String, Object>();
      //band is the instance of Band. find() is what locates teh specific
      //id for which ever band is clicked on or being viewed.
      Band band = Band.find(Integer.parseInt(request.params("id")));
      //model.put broadcasts band which has a placeholder of "band".
      //this talks to the front end templates in the vtl files. $band is referring to this
      //place holder.
      model.put("band", band);
      //Venue.all() has a place holder of "allVenues". The purpose of this is to
      //find all instances of Venue in the model object. This specific one is for
      //creating <option>'s in the select box on the band.vtl page.
      model.put("allVenues", Venue.all());
      //this is referring to the band.vtl page underneath templates.
      //This directs the request,response to display the specific page.
      model.put("template", "templates/band.vtl");
      //the model is a Map, allowing the use of multiple objects keyed by name.
      return new ModelAndView(model, layout);
      //New instance of the VelocityTemplateEngine
    }, new VelocityTemplateEngine());


    //This get request is virtuall the same as above except for Venues. Instead of finding instances
    //of "allVenues" it will find "allBands" associated with the specific venues.
    get("/venues/:id", (request,response) ->{
      HashMap<String, Object> model = new HashMap<String, Object>();
      Venue venue = Venue.find(Integer.parseInt(request.params("id")));
      model.put("venue", venue);
      //model is a map, it consists of model attributes (individual objects). These createQuery
      //constructors for creating a model with the listed attributes.
      model.put("allBands", Band.all());
      model.put("template", "templates/venue.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/add_bands", (request, response) -> {
      int bandId = Integer.parseInt(request.queryParams("band_id"));
      int venueId = Integer.parseInt(request.queryParams("venue_id"));
      Venue venue = Venue.find(venueId);
      Band band = Band.find(bandId);
      venue.addBand(band);
      response.redirect("/venues/" + venueId);
      //returning null is used for returning no data.
      return null;
    });

    post("/add_venues", (request, response) -> {
      int bandId = Integer.parseInt(request.queryParams("band_id"));
      int venueId = Integer.parseInt(request.queryParams("venue_id"));
      Venue venue = Venue.find(venueId);
      Band band = Band.find(bandId);
      band.addVenue(venue);
      response.redirect("/bands/" + bandId);
      return null;
    });

    get("/bands/:id/edit", (request,response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Band band = Band.find(Integer.parseInt(request.params("id")));
      model.put("band", band);
      model.put("template", "templates/band-edit.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/bands/:id", (request,response) -> {
      int bandId = Integer.parseInt(request.params("id"));
      Band band = Band.find(bandId);
      String newName = request.queryParams("name");
      band.update(newName);
      response.redirect("/bands/" + bandId);
      return null;
    });

    post("/bands/:id/delete", (request,response) -> {
      int bandId = Integer.parseInt(request.params("id"));
      Band band = Band.find(bandId);
      band.delete();
      response.redirect("/bands");
      return null;
    });

  }
}
