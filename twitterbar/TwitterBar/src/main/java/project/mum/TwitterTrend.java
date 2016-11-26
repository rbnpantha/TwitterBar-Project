/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.mum;

import com.mum.dto.MyTrend;
import java.util.ArrayList;
import java.util.List;
import twitter4j.GeoLocation;
import twitter4j.Location;
import twitter4j.ResponseList;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Prajil
 */
public class TwitterTrend {

    public List getTrends(double longi, double lati) throws TwitterException {
        List availableTrends = new ArrayList<String>();

        ConfigurationBuilder cf = new ConfigurationBuilder();
        cf.setDebugEnabled(true)
                .setOAuthConsumerKey("TBFU8jBXiIOEde0cnSglw2m7B")
                .setOAuthConsumerSecret("c0tJVVvGgpY2rI1Ol5qmxzMpB1MiBx8PGlLNPG7TYAAVXwYVvL")
                .setOAuthAccessToken("1148852892-OR8mM62nOH4WPJf991X5bCp4zVKT2EU57fBmjWQ")
                .setOAuthAccessTokenSecret("zpXLqUxlkHZT58RDbGEPLnXVB3Kpwp7d8Z4CKb4X4UJW6");
        TwitterFactory tf = new TwitterFactory(cf.build());
        twitter4j.Twitter twitter = tf.getInstance();
        ResponseList<Location> locations;
        List<MyTrend> myTrend = new ArrayList<MyTrend>();
        MyTrend myTwitterTrend;
        GeoLocation geo = new GeoLocation(longi, lati);
        locations = twitter.getClosestTrends(geo);
        Trends trends = twitter.getPlaceTrends(locations.get(0).getWoeid());
        int count = 0;
        for (Trend trend : trends.getTrends()) {
            if (count < 5) {
                myTwitterTrend = new MyTrend(trend.getName(), trend.getURL());
                myTrend.add(myTwitterTrend);
                count++;
                availableTrends.add(trend.getName());
            }
        }
        System.out.println(" available Trends :" + availableTrends);
        return myTrend;
    }
}
