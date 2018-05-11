package com.tbd.twitter;

import javax.annotation.PostConstruct;
import javax.swing.text.Document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import twitter4j.FilterQuery;
import twitter4j.JSONException;
import twitter4j.JSONObject;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.json.DataObjectFactory;

import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
@Service
@Configurable
public class TwitterListener {
	@Autowired
	private TwitterStream twitterStream;
	@Autowired
	private MongoTemplate mongo;

	MongoClient mongoo = new MongoClient("46.101.254.135",27017);
	DB database = mongoo.getDB("twitter7");
	DBCollection collection = database.getCollection("futbol");
	@PostConstruct
	public void run() {
		twitterStream.addListener(new StatusListener() {
			public void onStatus(Status status) {
			    String ubicacion=status.getUser().getLocation();
				System. out. println(ubicacion);
                if (ubicacion.indexOf("Chile")>0) {
                	BasicDBObject tweet;
                    tweet = new BasicDBObject("id",status.getId())
                            .append("text",status.getText())
                            .append("like",status. 	getFavoriteCount())
                            .append("geoLocation",status.getGeoLocation())
                            .append("retweet",status.getRetweetCount())
                            .append("locationUser",status.getUser().getLocation())
                            .append("name",status.getUser().getName())
                            .append("followers",status.getUser().getFollowersCount());
                   collection.insert(tweet);
                    System. out. println(ubicacion);
                }
	        }

			@Override
			public void onException(Exception arg0) {
								
			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice arg0) {
							
			}

			@Override
			public void onScrubGeo(long arg0, long arg1) {
								
			}

			@Override
			public void onStallWarning(StallWarning arg0) {
								
			}

			@Override
			public void onTrackLimitationNotice(int arg0) {
								
			}			
		});
		
	    FilterQuery filter=new FilterQuery();
	    filter.track(new String[]{"VamosColoColo","LosCruzados","Catolica","ColoColo","colo colo","colo","Huachipato","UC","Audax"});
	    filter.language(new String[]{"es"});
	    twitterStream.filter(filter);
	}

	public TwitterStream getTwitterStream() {
		return twitterStream;
	}

	public void setTwitterStream(TwitterStream twitterStream) {
		this.twitterStream = twitterStream;
	}

	public MongoTemplate getMongo() {
		return mongo;
	}

	public void setMongo(MongoTemplate mongo) {
		this.mongo = mongo;
	}
}
