package project.mum;

import com.mum.dto.Tweet;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Twitter {

    public String display(String search) throws TwitterException {
        ConfigurationBuilder cf = new ConfigurationBuilder();
        cf.setDebugEnabled(true)
                .setOAuthConsumerKey("TBFU8jBXiIOEde0cnSglw2m7B")
                .setOAuthConsumerSecret("c0tJVVvGgpY2rI1Ol5qmxzMpB1MiBx8PGlLNPG7TYAAVXwYVvL")
                .setOAuthAccessToken("1148852892-OR8mM62nOH4WPJf991X5bCp4zVKT2EU57fBmjWQ")
                .setOAuthAccessTokenSecret("zpXLqUxlkHZT58RDbGEPLnXVB3Kpwp7d8Z4CKb4X4UJW6");
        TwitterFactory tf = new TwitterFactory(cf.build());
        twitter4j.Twitter twitter = tf.getInstance();
        QueryResult result1;
        int count = 0;
        List<Status> tweets;
        List<Tweet> imageList = new ArrayList();

        result1 = twitter.search(new Query(search));
        tweets = result1.getTweets();
        String imageUrl, text, link, userName, userID;
        for (Status tweet : tweets) {
            imageUrl = tweet.getUser().getBiggerProfileImageURL();
            text = tweet.getText();
            userID = tweet.getUser().getScreenName();
            link = "http://twitter.com/" + userID;
            userName = tweet.getUser().getName();
            Tweet detail = new Tweet(imageUrl, text, userName, link);
            imageList.add(detail);
            count++;
            if (count >= 8) {
                break;
            }
        }

        return new Gson().toJson(imageList);

    }
}
