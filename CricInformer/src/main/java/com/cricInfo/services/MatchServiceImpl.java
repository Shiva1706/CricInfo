package com.cricInfo.services;

import com.cricInfo.entity.Match;
import com.cricInfo.repository.MatchRepo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class MatchServiceImpl implements MatchService{

    private static final Logger log = LoggerFactory.getLogger(MatchServiceImpl.class);
    private MatchRepo matchRepo;

    public MatchServiceImpl(MatchRepo matchRepo) {
        this.matchRepo = matchRepo;
    }

    @Override
    public List<Match> getAllMatches() {
        return this.matchRepo.findAll();
    }

    @Override
    public List<Match> getLiveMatches() {
        List<Match> matches = new ArrayList<>();
        try {
            String url = "https://www.cricbuzz.com/cricket-match/live-scores";
            Document document = Jsoup.connect(url).get();
            Elements liveScoreElements = document.select("div.cb-plyr-tbody.cb-rank-hdr.cb-lv-main");
            for (Element match : liveScoreElements) {
                HashMap<String, String> liveMatchInfo = new LinkedHashMap<>();

                String leagueHeading = match.select("h2.cb-lv-grn-strip").select("a").text();
                String teamsHeading = match.select("h3.cb-lv-scr-mtch-hdr").select("a").text();
                String matchNumberVenue = match.select("span.text-gray").text();
                Elements matchBatTeamInfo = match.select("div.cb-hmscg-bat-txt");
                String battingTeam = matchBatTeamInfo.select("div.cb-hmscg-tm-nm").text();
                String score = matchBatTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
                Elements bowlTeamInfo = match.select("div.cb-hmscg-bwl-txt");
                String bowlTeam = bowlTeamInfo.select("div.cb-hmscg-tm-nm").text();
                String bowlTeamScore = bowlTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
                String textLive = match.select("div.cb-text-live").text();
                String textComplete = match.select("div.cb-text-complete").text();
                //getting match link
                String matchLink = match.select("a.cb-lv-scrs-well.cb-lv-scrs-well-live").attr("href").toString();

                Match match1 = new Match();
                match1.setLeagueHeading(leagueHeading);
                match1.setTeamHeading(teamsHeading);
                match1.setMatchNumberVenue(matchNumberVenue);
                match1.setBattingTeam(battingTeam);
                match1.setBattingTeamScore(score);
                match1.setBowlTeam(bowlTeam);
                match1.setBowlTeamScore(bowlTeamScore);
                match1.setLiveText(textLive);
                match1.setMatchLink(matchLink);
                match1.setTextComplete(textComplete);
                match1.setMatchStatus();


                matches.add(match1);

//                update the match in database
                updateMatch(match1);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }

    private void updateMatch(Match match1) {

        Match match = this.matchRepo.findByTeamHeading(match1.getTeamHeading()).orElse(null);
        if (match == null) {
            this.matchRepo.save(match1);
        } else {

            match1.setMatchId(match.getMatchId());
            this.matchRepo.save(match1);
        }

    }


    @Override
    public List<List<String>> getPointTable() {
        List<List<String>> pointTable = new ArrayList<>();
        String tableURL = "https://www.cricbuzz.com/cricket-series/7476/icc-mens-t20-world-cup-2024/points-table";
        try {
            Document document = Jsoup.connect(tableURL).get();
            List<String> title = new ArrayList<>();
            String titleHead = document.select("div.cb-col-67").select("h3.cb-mat-mnu-wrp").text();
            title.add(titleHead);
            pointTable.add(title);

            Elements table = document.select("table.cb-srs-pnts");
            Elements tableHeads = table.select("thead>tr>*");
            List<String> headers = new ArrayList<>();
            tableHeads.forEach(element -> {
                headers.add(element.text());
            });
            pointTable.add(headers);
            Elements bodyTrs = table.select("tbody>*");
            bodyTrs.forEach(tr -> {
                List<String> points = new ArrayList<>();
                if (tr.hasAttr("class")) {
                    Elements tds = tr.select("td");
                    String team = tds.getFirst().select("div.cb-col-84").text();
                    points.add(team);
                    tds.forEach(td -> {
                        if (td.hasClass("cb-srs-pnts-td")) {
                            points.add(td.text());
                        }
                    });
//                    System.out.println(points);
                    pointTable.add(points);
                }

            });

            System.out.println(pointTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pointTable;
    }
}
