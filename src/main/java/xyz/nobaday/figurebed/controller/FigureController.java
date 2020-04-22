package xyz.nobaday.figurebed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import xyz.nobaday.figurebed.entity.Figure;
import xyz.nobaday.figurebed.entity.Page;
import xyz.nobaday.figurebed.service.FigureService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FigureController {

    @Autowired
    private FigureService figureService;

    @GetMapping("/index")
    public String index() {
        return "/index";
    }

    @GetMapping("/upload")
    public String getUploadPage() {
        return "/upload";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam(value="uploadImage") MultipartFile file, Model model) {
        String picURL = figureService.upLoad(file);
        model.addAttribute("picURL", picURL);

        int a = 10;
        System.out.println(picURL);

        return "/upload";
    }

    @GetMapping("/brose")
    public String brose(Model model, Page page) {
        int amount = figureService.totalImages();
        page.setRows(amount);
        page.setPath("/brose");

        List<Figure> figures = figureService.pageQuery(page.getOffset(), page.getLimit());
        model.addAttribute("figures", figures);

        return "/brose";
    }

}
