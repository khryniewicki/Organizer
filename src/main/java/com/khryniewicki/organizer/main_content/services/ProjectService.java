package com.khryniewicki.organizer.main_content.services;

import com.khryniewicki.organizer.main_content.Utills.UtillClass;
import com.khryniewicki.organizer.main_content.model.Project;
import com.khryniewicki.organizer.main_content.model.User;
import com.khryniewicki.organizer.main_content.model.repositories.ProjectRepository;
import com.khryniewicki.organizer.registration_login_logout.DTO.ProjectDTO;
import com.khryniewicki.organizer.registration_login_logout.DTO.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projektRepository;
    private final UserService userService;

    public Project findProject(Long id_project) {
        Optional<Project> projekt = projektRepository.findById(id_project);
        Project project1 = new Project();
        if (projekt.isPresent()) {
            project1 = projekt.get();
        }
        return project1;
    }

    public Project findProjectByName(String name) {
        Optional<Project> projekt = projektRepository.findByName(name);
        Project project1 = new Project();
        if (projekt.isPresent()) {
            project1 = projekt.get();
        }
        return project1;
    }

    public List<Project> getAllProjectsForUser(User user) {
        return projektRepository.findAll().stream()
                .filter(project -> project.getUsers().stream().
                        anyMatch(userx -> userx.getIdUser() == user.getIdUser()))
                .collect(Collectors.toList());

    }
    public List<User> getAdminNameAndSurname(User user){
        return getAllProjectsForUser(user).stream()
                .map(admin -> (userService.findUserByEmail(admin.getAdmin())))
                .distinct()
                .collect(Collectors.toList());
    }


    public void createProject(ProjectDTO projectDTO) {
        ArrayList<User> users = new ArrayList<>();
        users.add(UtillClass.getLoggedInUser());
        Project project = new Project(projectDTO.getName(), projectDTO.getDescription(), projectDTO.getAvatar(), users);
        projektRepository.save(project);
    }

    public ProjectDTO findProjectAndTransferToDTO(Long id) {
        Project project = findProject(id);
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setAdmin(project.getAdmin());
        projectDTO.setAvatar(project.getAvatar());
        return projectDTO;
    }


    public static String CreateIconRandomViewer() {
        String icontitle = "steam.png";
        List<String> result = UtillClass.getListOfIconTitles();
        int size = result.size();
        if (size != 0) {
            Random random_generator = new Random();
            int number = random_generator.nextInt(size);
            icontitle = "icons/" + result.get(number);
        }

        return icontitle;
    }

    public void updateProject(ProjectDTO projectDTO, Long id) {
        Project project = findProject(id);
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setAdmin(projectDTO.getAdmin());
        project.setAvatar(projectDTO.getAvatar());
        projektRepository.save(project);

    }

    public void deleteProject(Long id) {
        Project project = findProject(id);
        projektRepository.delete(project);
    }

    public void addUserToProject(Long idProject, Long idUser) {
        Optional<Project> ProjectById = projektRepository.findById(idProject);
        User userByEmail = userService.findUserById(idUser);
        if (ProjectById.isPresent()) {
            Project project = ProjectById.get();
            List<User> users = project.getUsers();
            users.add(userByEmail);
            project.setUsers(users);
            projektRepository.save(project);
        }
    }
}
