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
  //
  //   get("/categories", (request, response) -> {
  //     HashMap<String, Object> model = new HashMap<String, Object>();
  //     model.put("categories", Category.all());
  //     model.put("template", "templates/categories.vtl");
  //     return new ModelAndView(model, layout);
  //   }, new VelocityTemplateEngine());
  //
  //   get("/tasks", (request, response) -> {
  //     HashMap<String, Object> model = new HashMap<String, Object>();
  //     model.put("tasks", Task.all());
  //     model.put("template", "templates/tasks.vtl");
  //     return new ModelAndView(model, layout);
  //   }, new VelocityTemplateEngine());
  //
  //   get("/tasks/:id", (request,response) -> {
  //     HashMap<String, Object> model = new HashMap<String, Object>();
  //     Task task = Task.find(Integer.parseInt(request.params("id")));
  //     model.put("task", task);
  //     model.put("allCategories", Category.all());
  //     model.put("template", "templates/task.vtl");
  //     return new ModelAndView(model, layout);
  //   }, new VelocityTemplateEngine());
  //
  //   get("/categories/:id", (request,response) ->{
  //     HashMap<String, Object> model = new HashMap<String, Object>();
  //     Category category = Category.find(Integer.parseInt(request.params("id")));
  //     model.put("category", category);
  //     model.put("allTasks", Task.all());
  //     model.put("template", "templates/category.vtl");
  //     return new ModelAndView(model, layout);
  //   }, new VelocityTemplateEngine());
  //
  //   post("/tasks", (request, response) -> {
  //     HashMap<String, Object> model = new HashMap<String, Object>();
  //     String description = request.queryParams("description");
  //     Task newTask = new Task(description);
  //     newTask.save();
  //     response.redirect("/tasks");
  //     return null;
  //   });
  //
  //   post("/categories", (request, response) -> {
  //     HashMap<String, Object> model = new HashMap<String, Object>();
  //     String name = request.queryParams("name");
  //     Category newCategory = new Category(name);
  //     newCategory.save();
  //     response.redirect("/categories");
  //     return null;
  //   });
  //
  //   post("/add_tasks", (request, response) -> {
  //     int taskId = Integer.parseInt(request.queryParams("task_id"));
  //     int categoryId = Integer.parseInt(request.queryParams("category_id"));
  //     Category category = Category.find(categoryId);
  //     Task task = Task.find(taskId);
  //     category.addTask(task);
  //     response.redirect("/categories/" + categoryId);
  //     return null;
  //   });
  //
  //   post("/add_categories", (request, response) -> {
  //     Integer taskId = Integer.parseInt(request.queryParams("task_id"));
  //     Integer categoryId = Integer.parseInt(request.queryParams("category_id"));
  //     Category category = Category.find(categoryId);
  //     Task task = Task.find(taskId);
  //     task.addCategory(category);
  //     response.redirect("/tasks/" + taskId);
  //     return null;
  //   });
  //
  //   get("/categories/:category_id/tasks/:id", (request, response) -> {
  //     HashMap<String, Object> model = new HashMap<String, Object>();
  //     Category category = Category.find(Integer.parseInt(request.params(":category_id")));
  //     Task task = Task.find(Integer.parseInt(request.params(":id")));
  //     model.put("category", category);
  //     model.put("task", task);
  //     model.put("template", "templates/task.vtl");
  //     return new ModelAndView(model, layout);
  //   }, new VelocityTemplateEngine());
  //
  //   post("/categories/:category_id/tasks/:id", (request, response) -> {
  //     HashMap<String, Object> model = new HashMap<String, Object>();
  //     Task task = Task.find(Integer.parseInt(request.params("id")));
  //     String description = request.queryParams("description");
  //     Category category = Category.find(Integer.parseInt(request.params(":category_id")));
  //
  //     //task object: task's id, description
  //     //category via url param :category_id
  //
  //
  //     task.update(description);
  //     String url = String.format("/categories/%d/tasks/%d", category.getId(), task.getId());
  //     response.redirect(url);
  //     return new ModelAndView(model, layout);
  //   }, new VelocityTemplateEngine());
  //
  }
}
