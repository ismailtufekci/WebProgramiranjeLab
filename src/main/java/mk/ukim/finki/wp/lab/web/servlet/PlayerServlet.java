package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.Servlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Player;
import mk.ukim.finki.wp.lab.model.Team;
import mk.ukim.finki.wp.lab.service.PlayerService;
import mk.ukim.finki.wp.lab.service.TeamService;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.io.UTFDataFormatException;
import java.util.List;
import java.util.Optional;

@Component
@WebServlet(name="", urlPatterns = "/players")
public class PlayerServlet extends HttpServlet {

    private final PlayerService playerService;
    private final TeamService teamService;
    private final SpringTemplateEngine springTemplateEngine;


    public PlayerServlet(PlayerService playerService, TeamService teamService, SpringTemplateEngine springTemplateEngine) {
        this.playerService = playerService;
        this.teamService = teamService;
        this.springTemplateEngine = springTemplateEngine;
    }
    public List<Player> listPlayers() {
        return playerService.listPlayers();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException {

       String teamIdString=request.getParameter("teamId");

       if (teamIdString == null || teamIdString.isEmpty()){
           response.sendRedirect("/teams");
           return;
       }

       Optional<Team> team;
       try {
           Long teamId=Long.parseLong(teamIdString);
           team=teamService.findById(teamId);


       }catch (Exception e){
           response.sendRedirect("/teams");
           return;
       }
       //Setirame promenlivi za thymeleaf
        WebContext context = new WebContext(JakartaServletWebApplication.buildApplication(request.getServletContext()).buildExchange(request,response));
       context.setVariable("team",team);
       context.setVariable("players", listPlayers());
       context.setVariable("errorMessage", request.getParameter("errorMessage"));

       response.setContentType("text/html; charset= UTF-8");

       springTemplateEngine.process("players.html",context,response.getWriter());
    }
}
