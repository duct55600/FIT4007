import java.util.ArrayList;
import java.util.List;

public class LeagueTable {
    private List<Team> teams;

    public LeagueTable() {
        this.teams = new ArrayList<>();
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public boolean updateTeam(String oldName, String newName) {
        for (Team team : teams) {
            if (team.getName().equalsIgnoreCase(oldName)) {
                team.setName(newName);
                return true;
            }
        }
        return false;
    }

    public boolean removeTeam(String teamName) {
        return teams.removeIf(team -> team.getName().equalsIgnoreCase(teamName));
    }

    public void displayLeagueTable() {
        System.out.println("Bảng xếp hạng:");
        for (Team team : teams) {
            System.out.println(team.getName() + " - Điểm: " + team.getPoints());
        }
    }

    public List<Team> getTeams() {
        return teams;
    }
}
