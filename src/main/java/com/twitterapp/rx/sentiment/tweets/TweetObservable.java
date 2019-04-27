/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twitterapp.rx.sentiment.tweets;

import io.reactivex.Observable;
import twitter4j.*;

/**
 *
 * @author dbeltran
 */
public final class TweetObservable {
    
    public static Observable<Status> tweetObservable(final String[] searchKeywords) {
        
        return Observable.create(subscriber -> {
            final TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
            twitterStream.addListener(new StatusAdapter() {
                public void onStatus(Status status) {
                    subscriber.onNext(status);
                }

                public void onException(Exception ex) {
                    subscriber.onError(ex);
                }
            });
            FilterQuery query = new FilterQuery();
            query.language(new String[]{"en"});
            query.track(searchKeywords);
            twitterStream.filter(query);
        });

    }
    
}
