package id.agile.web;

import id.agile.domain.Project;
import id.agile.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject
            (
                    @Valid
                    @RequestBody Project project,
                    BindingResult result
            ) {
        if (result.hasErrors()) {
            return new ResponseEntity<String>("Invalid Objects!", HttpStatus.valueOf(404));
        }
        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }
}
