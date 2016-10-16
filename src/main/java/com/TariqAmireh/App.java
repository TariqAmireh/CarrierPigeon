package com.TariqAmireh;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import java.util.*;
/**
 * Hello world!
 *
 */
public class App
{
    public static final String TWILIO_ACCOUNT_SID = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    public static final String TWILIO_AUTH_TOKEN = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    public static void main( String[] args) throws TwitterException{
        System.out.println( "Hello World!" );
        Twilio.init(TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN);

        ConfigurationBuilder cf = new ConfigurationBuilder();
        cf.setDebugEnabled(true)
                .setOAuthConsumerKey("7YsxA8FsW0YgLJitnaeux81V1")
                .setOAuthConsumerSecret("r3HexuMnSOXiys66L5Y46N8D11B2xhzg6Ih2bjBoDOPgeEehWu")
                .setOAuthAccessToken("772887561140174848-sFIPHxRohUZZDcRck6xNtH1TUW2MAVw")
                .setOAuthAccessTokenSecret("JOhrCrN6WI721k9TOp2EstrOszRaQRnRNuo75HDHJwuuf");

        // no idea what this is either... just konw it's necessary
        TwitterFactory tf = new TwitterFactory(cf.build());
        twitter4j.Twitter twitter = tf.getInstance();

        //
        Scanner scan = new Scanner(System.in);
        System.out.println("What do you want to search for");
        String querybody = scan.next();
        Query query = new Query(querybody);
        int numberofTweetsRequested = 5;
        query.count(numberofTweetsRequested);
        query.resultType(Query.RECENT);
        QueryResult results = twitter.search(query);

        List<Status> tweets = results.getTweets();
        for (int i = 0; i < numberofTweetsRequested; i++) {
            Status tweet = tweets.get(0);
            String messageBody = "\n" + tweet.getUser().getName() + "  @" + tweet.getUser().getScreenName() +
                                    "\n" + tweet.getText() + "\nRetweets:"
                                    + tweet.getRetweetCount() + "   Favorites:" + tweet.getFavoriteCount() +
                                    "\n----------------------------------------------------";

            Message message = Message.creator(new PhoneNumber("14258941621"),
                                                new PhoneNumber("14255980851"),
                                                messageBody).create();
            }
    }
}

