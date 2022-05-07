package jp.gihyo.projava.tasklist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class HomeRestController {
    record TaskItem(String id, String task, String deadline, boolean done) {
    }

    private List<TaskItem> taskItems = new ArrayList<>();

    @RequestMapping(value = "/hello")
    String hello(Model model) {
        model.addAttribute("time", LocalDateTime.now());
        System.out.println("helloの中に入りました。");
        return "hello";
    }

    @GetMapping("/add")
    String addItem(@RequestParam("task") String task, @RequestParam("deadline") String deadline) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        TaskItem item = new TaskItem(id, task, deadline, false);
        taskItems.add(item);

        return "redirect:/list";
    }

    @GetMapping("/list")
    String listItems(Model model) {
        model.addAttribute("taskList", taskItems);
        return "home";
    }
}
