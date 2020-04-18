package com.khryniewicki.organizer.main_content.Utills;

import com.khryniewicki.organizer.main_content.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
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

    public static List<String> getListOfIconTitles() {
        List<String> result = new ArrayList<>();
        InputStream in = null;

        try (Stream<Path> walk = Files.walk(Paths.get(new ClassPathResource(
                "src/main/resources/static/img/icons",
                UtillClass.class.getClassLoader()).getPath()))) {
//            "src/main/resources/static/img/icons"
            result = walk.filter(Files::isRegularFile)
                    .map(x -> x.getFileName().toString())
                    .sorted()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<String> getListOfIconsTitlesWrittenManually() {
        ArrayList<String> strings = new ArrayList<>();

        for (int i = 1; i < 100; i++) {
            String tmp;
            if (i > 0 && i < 10)
                tmp = "00" + i + ".png";
            else
                tmp = "0" + i + ".png";
            strings.add(tmp);
        }

        return strings;
    }
}
