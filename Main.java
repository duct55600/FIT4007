import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LeagueTable leagueTable = new LeagueTable();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Tournament tournament = new Tournament(leagueTable.getTeams());

        String mainOption;

        do {
            System.out.println("\n=== Quản lý bóng đá ===");
            System.out.println("1. Thông tin đội bóng");
            System.out.println("2. Quản lý cầu thủ và huấn luyện viên");
            System.out.println("3. Tổng quan mùa giải");
            System.out.println("0. Thoát");
            System.out.print("Chọn tùy chọn (0-3): ");
            mainOption = scanner.nextLine();

            switch (mainOption) {
                case "1":
                    manageTeams(leagueTable, tournament, scanner, random);
                    break;
                case "2":
                    managePlayers(leagueTable, scanner);
                    break;
                case "3":
                    displaySeasonOverview(leagueTable, scanner);
                    break;
                case "0":
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Tùy chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (!mainOption.equals("0"));

        scanner.close();
    }
    private static void displaySeasonOverview(LeagueTable leagueTable, Scanner scanner) {
        String option;

        do {
            System.out.println("\n--- Tổng quan mùa giải ---");
            System.out.println("1. Thống kê số thẻ vàng, thẻ đỏ của từng đội");
            System.out.println("2. Hiển thị số cầu thủ bị chấn thương");
            System.out.println("3. Quản lý nhà tài trợ");
            System.out.println("4. Quản lý sân đấu");
            System.out.println("0. Quay lại menu chính");
            System.out.print("Chọn tùy chọn (0-4): ");
            option = scanner.nextLine();

            switch (option) {
                case "1":
                    displayCardsStatistics(leagueTable);
                    break;
                case "2":
                    displayInjuredPlayers(leagueTable);
                    break;
                case "3":
                    manageSponsors(scanner);
                    break;
                case "4":
                    manageStadiums(scanner);
                    break;
                case "0":
                    System.out.println("Quay lại menu chính...");
                    break;
                default:
                    System.out.println("Tùy chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (!option.equals("0"));
    }
    private static void managePlayer(LeagueTable leagueTable, Scanner scanner) {
        String option;

        do {
            System.out.println("\n--- Quản lý cầu thủ ---");
            System.out.println("1. Thêm cầu thủ");
            System.out.println("2. Xóa cầu thủ");
            System.out.println("3. Hiển thị cầu thủ");
            System.out.println("4. Hiển thị danh sách cầu thủ");
            System.out.println("0. Quay lại menu chính");
            System.out.print("Chọn tùy chọn (0-4): ");
            option = scanner.nextLine();

            switch (option) {
                case "1":
                    addPlayer(leagueTable, scanner);
                    break;
                case "2":
                    removePlayer(leagueTable, scanner);
                    break;
                case "3":
                    displayPlayers(leagueTable, scanner);
                    break;
                case "4":
                    generateSamplePlayers(leagueTable);
                    break;
                case "0":
                    System.out.println("Quay lại menu chính...");
                    break;
                default:
                    System.out.println("Tùy chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (!option.equals("0"));
    }

    private static void addPlayer(LeagueTable leagueTable, Scanner scanner) {
        System.out.print("Nhập tên đội bóng: ");
        String teamName = scanner.nextLine();
        Team team = leagueTable.getTeams().stream()
                .filter(t -> t.getName().equalsIgnoreCase(teamName))
                .findFirst()
                .orElse(null);
        if (team != null) {
            System.out.print("Nhập tên cầu thủ: ");
            String playerName = scanner.nextLine();
            System.out.print("Nhập tuổi cầu thủ: ");
            int playerAge = Integer.parseInt(scanner.nextLine());
            System.out.print("Nhập vị trí cầu thủ: ");
            String playerPosition = scanner.nextLine();
            team.addPlayer(new Player(playerName, playerAge, playerPosition));
            System.out.println("Cầu thủ " + playerName + " đã được thêm vào đội " + teamName);
        } else {
            System.out.println("Không tìm thấy đội bóng " + teamName);
        }
    }

    private static void removePlayer(LeagueTable leagueTable, Scanner scanner) {
        System.out.print("Nhập tên đội bóng: ");
        String teamToRemovePlayer = scanner.nextLine();
        Team teamToRemove = leagueTable.getTeams().stream()
                .filter(t -> t.getName().equalsIgnoreCase(teamToRemovePlayer))
                .findFirst()
                .orElse(null);
        if (teamToRemove != null) {
            System.out.print("Nhập tên cầu thủ cần xóa: ");
            String playerNameToRemove = scanner.nextLine();
            if (teamToRemove.removePlayer(playerNameToRemove)) {
                System.out.println("Cầu thủ " + playerNameToRemove + " đã được xóa khỏi đội " + teamToRemove.getName());
            } else {
                System.out.println("Không tìm thấy cầu thủ " + playerNameToRemove + " trong đội " + teamToRemove.getName());
            }
        } else {
            System.out.println("Không tìm thấy đội bóng " + teamToRemovePlayer);
        }
    }

    private static void displayPlayers(LeagueTable leagueTable, Scanner scanner) {
        System.out.print("Nhập tên đội bóng: ");
        String teamToDisplayPlayers = scanner.nextLine();
        Team teamToDisplay = leagueTable.getTeams().stream()
                .filter(t -> t.getName().equalsIgnoreCase(teamToDisplayPlayers))
                .findFirst()
                .orElse(null);
        if (teamToDisplay != null) {
            teamToDisplay.displayPlayers();
        } else {
            System.out.println("Không tìm thấy đội bóng " + teamToDisplayPlayers);
        }
    }
    private static void manageCoaches(LeagueTable leagueTable, Scanner scanner) {
        String option;

        do {
            System.out.println("\n--- Quản lý huấn luyện viên ---");
            System.out.println("1. Thêm huấn luyện viên");
            System.out.println("2. Sửa huấn luyện viên");
            System.out.println("3. Xóa huấn luyện viên");
            System.out.println("4. Hiển thị danh sách huấn luyện viên");
            System.out.println("0. Quay lại menu chính");
            System.out.print("Chọn tùy chọn (0-4): ");
            option = scanner.nextLine();

            switch (option) {
                case "1":
                    addCoach(leagueTable, scanner);
                    break;
                case "2":
                    updateCoach(leagueTable, scanner);
                    break;
                case "3":
                    removeCoach(leagueTable, scanner);
                    break;
                case "4":
                    generateSampleCoaches(leagueTable);
                    break;
                case "0":
                    System.out.println("Quay lại menu chính...");
                    break;
                default:
                    System.out.println("Tùy chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (!option.equals("0"));
    }

    private static void addCoach(LeagueTable leagueTable, Scanner scanner) {
        System.out.print("Nhập tên đội bóng: ");
        String teamName = scanner.nextLine();
        Team team = leagueTable.getTeams().stream()
                .filter(t -> t.getName().equalsIgnoreCase(teamName))
                .findFirst()
                .orElse(null);
        if (team != null) {
            System.out.print("Nhập tên huấn luyện viên: ");
            String coachName = scanner.nextLine();
            System.out.print("Nhập số năm kinh nghiệm: ");
            int experience = Integer.parseInt(scanner.nextLine());
            team.setCoach(new Coach(coachName, experience));
            System.out.println("Huấn luyện viên " + coachName + " đã được thêm vào đội " + teamName);
        } else {
            System.out.println("Không tìm thấy đội bóng " + teamName);
        }
    }
    private static void managePlayers(LeagueTable leagueTable, Scanner scanner) {
        String option;

        do {
            System.out.println("\n--- Quản lý cầu thủ ---");
            System.out.println("1. Thêm cầu thủ");
            System.out.println("2. Xóa cầu thủ");
            System.out.println("3. Hiển thị cầu thủ");
            System.out.println("4. Quản lý huấn luyện viên");
            System.out.println("0. Quay lại menu chính");
            System.out.print("Chọn tùy chọn (0-4): ");
            option = scanner.nextLine();

            switch (option) {
                case "1":
                    addPlayer(leagueTable, scanner);
                    break;
                case "2":
                    removePlayer(leagueTable, scanner);
                    break;
                case "3":
                    displayPlayers(leagueTable, scanner);
                    break;
                case "4":
                    manageCoaches(leagueTable, scanner);
                    break;
                case "0":
                    System.out.println("Quay lại menu chính...");
                    break;
                default:
                    System.out.println("Tùy chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (!option.equals("0"));
    }
    private static void updateCoach(LeagueTable leagueTable, Scanner scanner) {
        System.out.print("Nhập tên đội bóng: ");
        String teamName = scanner.nextLine();
        Team team = leagueTable.getTeams().stream()
                .filter(t -> t.getName().equalsIgnoreCase(teamName))
                .findFirst()
                .orElse(null);
        if (team != null && team.getCoach() != null) {
            System.out.print("Nhập tên huấn luyện viên: ");
            String coachName = scanner.nextLine();
            System.out.print("Nhập số năm kinh nghiệm mới: ");
            int experience = Integer.parseInt(scanner.nextLine());
            boolean updated = team.updateCoach(coachName, experience);
            if (updated) {
                System.out.println("Huấn luyện viên đã được cập nhật.");
            } else {
                System.out.println("Không tìm thấy huấn luyện viên " + coachName + " trong đội " + teamName);
            }
        } else {
            System.out.println("Không tìm thấy đội bóng hoặc huấn luyện viên không tồn tại.");
        }
    }

    private static void removeCoach(LeagueTable leagueTable, Scanner scanner) {
        System.out.print("Nhập tên đội bóng: ");
        String teamName = scanner.nextLine();
        Team team = leagueTable.getTeams().stream()
                .filter(t -> t.getName().equalsIgnoreCase(teamName))
                .findFirst()
                .orElse(null);
        if (team != null) {
            if (team.removeCoach()) {
                System.out.println("Huấn luyện viên đã được xóa khỏi đội " + teamName);
            } else {
                System.out.println("Không tìm thấy huấn luyện viên trong đội " + teamName);
            }
        } else {
            System.out.println("Không tìm thấy đội bóng " + teamName);
        }
    }
    private static void manageTeams(LeagueTable leagueTable, Tournament tournament, Scanner scanner, Random random) {
        String option;

        do {
            System.out.println("\n--- Thông tin đội bóng ---");
            System.out.println("1. Thêm đội bóng");
            System.out.println("2. Sửa đội bóng");
            System.out.println("3. Xóa đội bóng");
            System.out.println("4. Hiển thị bảng xếp hạng");
            System.out.println("5. Hiển thị danh sách đội bóng");
            System.out.println("6. Tạo lịch thi đấu");
            System.out.println("7. Điền kết quả trận đấu");
            System.out.println("8. Hiển thị lịch thi đấu");
            System.out.println("0. Quay lại menu chính");
            System.out.print("Chọn tùy chọn (0-8): ");
            option = scanner.nextLine();

            switch (option) {
                case "1":
                    addTeam(leagueTable, scanner);
                    break;
                case "2":
                    updateTeam(leagueTable, scanner);
                    break;
                case "3":
                    removeTeam(leagueTable, scanner);
                    break;
                case "4":
                    leagueTable.displayLeagueTable();
                    break;
                case "5":
                    displayTeamList(leagueTable);
                    break;
                case "6":
                    tournament.generateSchedule();
                    System.out.println("Lịch thi đấu đã được tạo.");
                    break;
                case "7":
                    updateMatchResults(tournament, scanner);
                    break;
                case "8":
                    displayTournamentSchedule(tournament);
                    break;
                case "0":
                    System.out.println("Quay lại menu chính...");
                    break;
                default:
                    System.out.println("Tùy chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (!option.equals("0"));
    }

    private static void addTeam(LeagueTable leagueTable, Scanner scanner) {
        System.out.print("Nhập tên đội bóng: ");
        String teamName = scanner.nextLine();
        leagueTable.addTeam(new Team(teamName));
        System.out.println("Đội bóng " + teamName + " đã được thêm.");
    }

    private static void updateTeam(LeagueTable leagueTable, Scanner scanner) {
        System.out.print("Nhập tên đội bóng cũ: ");
        String oldName = scanner.nextLine();
        System.out.print("Nhập tên đội bóng mới: ");
        String newName = scanner.nextLine();
        boolean updated = leagueTable.updateTeam(oldName, newName);
        if (updated) {
            System.out.println("Đội bóng đã được cập nhật thành " + newName);
        } else {
            System.out.println("Không tìm thấy đội bóng " + oldName);
        }
    }

    private static void removeTeam(LeagueTable leagueTable, Scanner scanner) {
        System.out.print("Nhập tên đội bóng cần xóa: ");
        String removeName = scanner.nextLine();
        boolean removed = leagueTable.removeTeam(removeName);
        if (removed) {
            System.out.println("Đội bóng " + removeName + " đã được xóa.");
        } else {
            System.out.println("Không tìm thấy đội bóng " + removeName);
        }
    }

    private static void displayTeamList(LeagueTable leagueTable) {
        System.out.println("Danh sách đội bóng tham gia:");
        for (Team team : leagueTable.getTeams()) {
            System.out.println("- " + team.getName());
        }
    }

    private static void updateMatchResults(Tournament tournament, Scanner scanner) {
        for (Match match : tournament.getMatches()) {
            System.out.print("Nhập kết quả cho trận " + match.getTeamA().getName() + " vs " + match.getTeamB().getName() + " (điểm A điểm B): ");
            String[] scores = scanner.nextLine().split(" ");
            if (scores.length != 2) {
                System.out.println("Đầu vào không hợp lệ. Vui lòng nhập lại.");
                continue;
            }
            try {
                int scoreA = Integer.parseInt(scores[0]);
                int scoreB = Integer.parseInt(scores[1]);
                match.setScore(scoreA, scoreB);
                tournament.updateTeamStats(match.getTeamA(), scoreA > scoreB ? 1 : 0, scoreA < scoreB ? 1 : 0, scoreA == scoreB ? 1 : 0);
                tournament.updateTeamStats(match.getTeamB(), scoreB > scoreA ? 1 : 0, scoreB < scoreA ? 1 : 0, scoreB == scoreA ? 1 : 0);
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập điểm hợp lệ.");
            }
        }
        System.out.println("Kết quả trận đấu đã được cập nhật.");
    }

    private static void displayTournamentSchedule(Tournament tournament) {
        System.out.println("Lịch thi đấu:");
        for (Match match : tournament.getMatches()) {
            System.out.println(match.getTeamA().getName() + " vs " + match.getTeamB().getName());
        }
    }
    private static void generateSamplePlayers(LeagueTable leagueTable) {
        for (Team team : leagueTable.getTeams()) {
            if (team.getPlayers().isEmpty()) {
                // Tạo danh sách cầu thủ mẫu
                team.addPlayer(new Player("Cầu thủ A", 25, "Tiền đạo"));
                team.addPlayer(new Player("Cầu thủ B", 24, "Hậu vệ"));
                team.addPlayer(new Player("Cầu thủ C", 22, "Tiền vệ"));
                System.out.println("Danh sách cầu thủ đã được tạo cho đội " + team.getName());
            } else {
                System.out.println("Danh sách cầu thủ của đội " + team.getName() + " đã có sẵn.");
            }
        }
    }
    private static void generateSampleCoaches(LeagueTable leagueTable) {
        for (Team team : leagueTable.getTeams()) {
            if (team.getCoach() == null) {
                // Tạo huấn luyện viên mẫu
                team.setCoach(new Coach("Huấn luyện viên A", 5));
                System.out.println("Huấn luyện viên đã được tạo cho đội " + team.getName());
            } else {
                System.out.println("Huấn luyện viên của đội " + team.getName() + " đã có sẵn: " + team.getCoach().toString());
            }
        }
    }

    private static void displayCardsStatistics(LeagueTable leagueTable) {
        System.out.println("\n--- Thống kê thẻ phạt ---");
        for (Team team : leagueTable.getTeams()) {
            // Giả sử Team có các thuộc tính thẻ vàng và thẻ đỏ
            System.out.println("Đội: " + team.getName() +
                    " - Thẻ vàng: " + team.getYellowCards() +
                    " - Thẻ đỏ: " + team.getRedCards());
        }
    }
    private static void displayInjuredPlayers(LeagueTable leagueTable) {
        System.out.println("\n--- Cầu thủ bị chấn thương ---");
        for (Team team : leagueTable.getTeams()) {
            List<Player> injuredPlayers = team.getInjuredPlayers(); // Giả sử có phương thức này
            if (injuredPlayers.isEmpty()) {
                System.out.println("Đội " + team.getName() + " không có cầu thủ bị chấn thương.");
            } else {
                System.out.println("Đội " + team.getName() + " có cầu thủ bị chấn thương:");
                for (Player player : injuredPlayers) {
                    System.out.println("- " + player.getName());
                }
            }
        }
    }
    private static void manageSponsors(Scanner scanner) {
        String option;
        List<Sponsor> sponsors = new ArrayList<>();

        do {
            System.out.println("\n--- Quản lý nhà tài trợ ---");
            System.out.println("1. Thêm nhà tài trợ");
            System.out.println("2. Sửa nhà tài trợ");
            System.out.println("3. Xóa nhà tài trợ");
            System.out.println("4. Hiển thị danh sách nhà tài trợ");
            System.out.println("0. Quay lại");
            System.out.print("Chọn tùy chọn (0-4): ");
            option = scanner.nextLine();

            switch (option) {
                case "1":
                    addSponsor(sponsors, scanner);
                    break;
                case "2":
                    updateSponsor(sponsors, scanner);
                    break;
                case "3":
                    removeSponsor(sponsors, scanner);
                    break;
                case "4":
                    displaySponsors(sponsors);
                    break;
                case "0":
                    System.out.println("Quay lại...");
                    break;
                default:
                    System.out.println("Tùy chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (!option.equals("0"));
    }

    private static void addSponsor(List<Sponsor> sponsors, Scanner scanner) {
        System.out.print("Nhập tên nhà tài trợ: ");
        String name = scanner.nextLine();
        sponsors.add(new Sponsor(name));
        System.out.println("Nhà tài trợ " + name + " đã được thêm.");
    }

    private static void updateSponsor(List<Sponsor> sponsors, Scanner scanner) {
        System.out.print("Nhập tên nhà tài trợ cần sửa: ");
        String oldName = scanner.nextLine();
        for (Sponsor sponsor : sponsors) {
            if (sponsor.getName().equalsIgnoreCase(oldName)) {
                System.out.print("Nhập tên mới: ");
                String newName = scanner.nextLine();
                sponsor.setName(newName);
                System.out.println("Nhà tài trợ đã được cập nhật.");
                return;
            }
        }
        System.out.println("Không tìm thấy nhà tài trợ " + oldName);
    }

    private static void removeSponsor(List<Sponsor> sponsors, Scanner scanner) {
        System.out.print("Nhập tên nhà tài trợ cần xóa: ");
        String name = scanner.nextLine();
        sponsors.removeIf(sponsor -> sponsor.getName().equalsIgnoreCase(name));
        System.out.println("Nhà tài trợ " + name + " đã được xóa.");
    }

    private static void displaySponsors(List<Sponsor> sponsors) {
        System.out.println("\n--- Danh sách nhà tài trợ ---");
        if (sponsors.isEmpty()) {
            System.out.println("Chưa có nhà tài trợ nào.");
        } else {
            for (Sponsor sponsor : sponsors) {
                System.out.println("- " + sponsor.getName());
            }
        }
    }
    private static void manageStadiums(Scanner scanner) {
        String option;
        List<Stadium> stadiums = new ArrayList<>(); // Danh sách sân đấu

        do {
            System.out.println("\n--- Quản lý sân đấu ---");
            System.out.println("1. Thêm sân đấu");
            System.out.println("2. Sửa sân đấu");
            System.out.println("3. Xóa sân đấu");
            System.out.println("4. Hiển thị danh sách sân đấu");
            System.out.println("0. Quay lại");
            System.out.print("Chọn tùy chọn (0-4): ");
            option = scanner.nextLine();

            switch (option) {
                case "1":
                    addStadium(stadiums, scanner);
                    break;
                case "2":
                    updateStadium(stadiums, scanner);
                    break;
                case "3":
                    removeStadium(stadiums, scanner);
                    break;
                case "4":
                    displayStadiums(stadiums);
                    break;
                case "0":
                    System.out.println("Quay lại...");
                    break;
                default:
                    System.out.println("Tùy chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (!option.equals("0"));
    }

    private static void addStadium(List<Stadium> stadiums, Scanner scanner) {
        System.out.print("Nhập tên sân đấu: ");
        String name = scanner.nextLine();
        stadiums.add(new Stadium(name));
        System.out.println("Sân đấu " + name + " đã được thêm.");
    }

    private static void updateStadium(List<Stadium> stadiums, Scanner scanner) {
        System.out.print("Nhập tên sân đấu cần sửa: ");
        String oldName = scanner.nextLine();
        for (Stadium stadium : stadiums) {
            if (stadium.getName().equalsIgnoreCase(oldName)) {
                System.out.print("Nhập tên mới: ");
                String newName = scanner.nextLine();
                stadium.setName(newName);
                System.out.println("Sân đấu đã được cập nhật.");
                return;
            }
        }
        System.out.println("Không tìm thấy sân đấu " + oldName);
    }

    private static void removeStadium(List<Stadium> stadiums, Scanner scanner) {
        System.out.print("Nhập tên sân đấu cần xóa: ");
        String name = scanner.nextLine();
        stadiums.removeIf(stadium -> stadium.getName().equalsIgnoreCase(name));
        System.out.println("Sân đấu " + name + " đã được xóa.");
    }

    private static void displayStadiums(List<Stadium> stadiums) {
        System.out.println("\n--- Danh sách sân đấu ---");
        if (stadiums.isEmpty()) {
            System.out.println("Chưa có sân đấu nào.");
        } else {
            for (Stadium stadium : stadiums) {
                System.out.println("- " + stadium.getName());
            }
        }
    }
    
}
