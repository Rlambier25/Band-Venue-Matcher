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

  get("/bands", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("band", Band.all());
    model.put("template", "templates/bands.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/venues", (request,response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("venues", Venue.all());
    model.put("template", "templates/venues.vtl");
    return new ModelAndView(model, layout);
  } new VelocityTemplateEngine());

  post("/venues", (request, response) -> {
    String name = request.queryParams("name");
    Venue newVenue = new Venue(name);
    newVenue.save();
    response.redirect("/venues");
    return null;
  });

  post("/bands", (request, response) -> {
    String name = request.queryParams("name");
    Band newBand = new Band(name);
    newBand.save();
    response.redirect("/bands");
    return null;
  });

  get("/venues/:id", (request, response) -> {
    HashMap<String, Object>model = new HashMap<String, Object>();
    Venue venue = Venue.find(Integer.parseInt(request.params("id")));
    model.put("venue", venue);
    model.put("allBands", Band.all());
    model.put("template", "templates/venue.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/bands/:id", (request,response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Band band = Band.find(Integer.parseInt(request.params("id")));
    model.put("band", band);
    model.put("allVenues", Venue.all());
    model.put("template", "template/band.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  }
}
