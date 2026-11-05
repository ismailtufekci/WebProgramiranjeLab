package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import mk.ukim.finki.wp.lab.model.Team;
import mk.ukim.finki.wp.lab.service.TeamService;

import org.springframework.stereotype.Component;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Component
public class TeamDetailsServlet extends HttpServlet {

    private final TeamService teamService;
    private final SpringTemplateEngine springTemplateEngine;

    public TeamDetailsServlet(TeamService teamService,
                              SpringTemplateEngine springTemplateEngine) {
        this.teamService = teamService;
        this.springTemplateEngine = springTemplateEngine;
    }

    // POST method
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String teamId = request.getParameter("teamId");
        String playerId = request.getParameter("playerId");

        try {
            teamService.addPlayerToTeam(
                    Long.parseLong(teamId),
                    playerId
            );

            // Successful addition - redirect to team details
            response.sendRedirect(
                    "/teamDetails?teamId=" + teamId
            );

        } catch (IllegalArgumentException e) {

            String errorMessage = URLEncoder.encode(
                    e.getMessage(),
                    StandardCharsets.UTF_8
            );

            // Redirect back to players page with error
            response.sendRedirect(
                    "/players?teamId=" + teamId
                            + "&errorMessage=" + errorMessage
            );
        }
    }

    // GET method
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String teamIdString = request.getParameter("teamId");

        if (teamIdString == null || teamIdString.isEmpty()) {
            response.sendRedirect("/teams");
            return;
        }

        Optional<Team> team;

        try {
            Long teamId = Long.parseLong(teamIdString);
            team = teamService.findById(teamId);

        } catch (IllegalArgumentException e) {
            response.sendRedirect("/teams");
            return;
        }

        WebContext context = new WebContext(
                JakartaServletWebApplication
                        .buildApplication(request.getServletContext())
                        .buildExchange(request, response)
        );

        context.setVariable("team", team);

        response.setContentType("text/html; charset=UTF-8");

        springTemplateEngine.process(
                "teamDetails.html",
                context,
                response.getWriter()
        );
    }
}
