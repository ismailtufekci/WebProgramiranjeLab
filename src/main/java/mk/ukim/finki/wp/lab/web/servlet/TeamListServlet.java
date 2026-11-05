package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Team;
import mk.ukim.finki.wp.lab.service.TeamService;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@Component
@WebServlet(name = "teamListServlet", urlPatterns = "/teams")
public class TeamListServlet  extends HttpServlet {
    private final TeamService teamService;
    private final SpringTemplateEngine springTemplateEngine;

    public TeamListServlet(TeamService teamService, SpringTemplateEngine springTemplateEngine) {
        this.teamService = teamService;
        this.springTemplateEngine = springTemplateEngine;
    }


   public List<Team> listTeams(){
          return teamService.listTeams();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<Team> teams = listTeams();


        JakartaServletWebApplication application =
                JakartaServletWebApplication.buildApplication(
                        request.getServletContext()
                );

        WebContext context = new WebContext(
                application.buildExchange(request, response)
        );


        context.setVariable("teams", teams);

        response.setContentType("text/html; charset=UTF-8");

        springTemplateEngine.process(
                "listTeams.html",
                context,
                response.getWriter()
        );
    }
    }


