package dev.jibulani.servlet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SupportServlet extends HttpServlet {

    public static final List<String> cheerUpPhrases = new CopyOnWriteArrayList<>() {{
        add("У тебя все получится!");
    }};

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.getWriter().print(
                cheerUpPhrases.get(
                        ThreadLocalRandom.current().nextInt(cheerUpPhrases.size()) % cheerUpPhrases.size()
                ));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String input = req.getReader().readLine();
        cheerUpPhrases.add(input);
        resp.setContentType("text/plain");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.getWriter().print(String.format("Добавлена фраза: %s", input));
    }
}
