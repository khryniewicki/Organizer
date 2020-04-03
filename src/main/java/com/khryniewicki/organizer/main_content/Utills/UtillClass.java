package com.khryniewicki.organizer.main_content.Utills;

import com.khryniewicki.organizer.main_content.model.User;
import com.khryniewicki.organizer.main_content.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UtillClass {

    public static User getLoggedInUser() {
        HttpSession session = getCurrentHttpRequest().getSession(false);
        return (User) session.getAttribute("appUser");
    }

    public static HttpServletRequest getCurrentHttpRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            return request;
        }
        return null;
    }

    public static List<String> getListOfIconTitles (){
        List<String> result=new ArrayList<>();
        InputStream in = null;
        try (Stream<Path> walk = Files.walk(Paths.get("/home/konrad/IdeaProjects/github/organizer/src/main/resources/static/img/icons"))) {

            result = walk.filter(Files::isRegularFile)
                    .map(x -> x.getFileName().toString())
                    .sorted()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}
