package id.agile.service;

import id.agile.domain.Project;
import id.agile.exception.CustomException;
import id.agile.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
        try {
            return projectRepository.save(project);
        } catch (org.springframework.dao.DataIntegrityViolationException exists) {
            throw new CustomException("Project Already Exist!");
        } catch (Throwable t) {
            return null;
        }
    }

    public Project findProjectByIdentifier(String project) {
        try {
            return projectRepository.findByProjectIdentifier(project);
        } catch (Throwable t) {
            System.out.println(t);
            throw new CustomException("Cannot find project : "+project);
        }
    }
}
