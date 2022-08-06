package ru.grobikon.micro.grobikontodo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.grobikon.common.grobikoncommonentity.entity.Category;
import ru.grobikon.common.grobikoncommonentity.entity.Priority;
import ru.grobikon.common.grobikoncommonentity.entity.Task;
import ru.grobikon.micro.grobikontodo.service.CategoryService;
import ru.grobikon.micro.grobikontodo.service.PriorityService;
import ru.grobikon.micro.grobikontodo.service.TaskService;

import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/data")
public class TestDataController {

    // доступ к данным из БД
    private final CategoryService categoryService;
    private final PriorityService priorityService;
    private final TaskService taskService;

    public TestDataController(CategoryService categoryService, PriorityService priorityService, TaskService taskService) {
        this.categoryService = categoryService;
        this.priorityService = priorityService;
        this.taskService = taskService;
    }

    @PostMapping("/init")
    public ResponseEntity<Boolean> init(@RequestBody Long userId) {

        Priority prior1 = new Priority();
        prior1.setColor("#fff");
        prior1.setTitle("Важный");
        prior1.setUserId(userId);

        Priority prior2 = new Priority();
        prior2.setColor("#ffе");
        prior2.setTitle("Неважный");
        prior2.setUserId(userId);

        priorityService.add(prior1);
        priorityService.add(prior2);


        Category cat1 = new Category();
        cat1.setTitle("Работа");
        cat1.setUserId(userId);

        Category cat2 = new Category();
        cat2.setTitle("Семья");
        cat2.setUserId(userId);

        categoryService.add(cat1);
        categoryService.add(cat2);

        // завтра
        Date tomorrow = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(tomorrow);
        c.add(Calendar.DATE, 1);
        tomorrow = c.getTime();

        // неделя
        Date oneWeek = new Date();
        Calendar c2 = Calendar.getInstance();
        c2.setTime(oneWeek);
        c2.add(Calendar.DATE, 7);
        oneWeek = c2.getTime();

        Task task1 = new Task();
        task1.setTitle("Покушать");
        task1.setCategory(cat1);
        task1.setPriority(prior1);
        task1.setCompleted(true);
        task1.setTaskDate(tomorrow);
        task1.setUserId(userId);

        Task task2 = new Task();
        task2.setTitle("Поспать");
        task2.setCategory(cat2);
        task2.setCompleted(false);
        task2.setPriority(prior2);
        task2.setTaskDate(oneWeek);
        task2.setUserId(userId);


        taskService.add(task1);
        taskService.add(task2);

        return ResponseEntity.ok(true);

    }
}
