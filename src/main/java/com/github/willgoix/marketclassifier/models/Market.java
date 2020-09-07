package com.github.willgoix.marketclassifier.models;

import com.github.willgoix.marketclassifier.models.enums.EnumCritera;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @author Willian Gois (github/willgoix) - 02/09/2020
 */
@Entity
@Table(name = "markets")
public class Market {

    private static final String DEFAULT_MARKET_IMAGE = "/images/market_without_image.jpg";

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "lastTimeWasBetter")
    private Date lastTimeWasBetter;

    @ElementCollection
    @MapKeyColumn(name = "critera")
    @Column(name = "rating")
    private Map<EnumCritera, CriteraRating> ratings = new HashMap<>();

    public Market() { }

    public long getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return this.imageUrl == null ? DEFAULT_MARKET_IMAGE : this.imageUrl;
    }

    public void setLastTimeWasBetter(Date lastTimeWasBetter) {
        this.lastTimeWasBetter = lastTimeWasBetter;
    }

    public Date getLastTimeWasBetter() {
        return this.lastTimeWasBetter;
    }

    public CriteraRating getRating(EnumCritera critera) {
        CriteraRating rating = this.ratings.get(critera);

        if (rating == null) {
            rating = new CriteraRating();
            this.ratings.put(critera, rating);
        }

        return rating;
    }

    public Map<EnumCritera, CriteraRating> getRatings() {
        return this.ratings;
    }

    public int getRatingsCount() {
        return this.ratings.values().stream().mapToInt(CriteraRating::getVotesCount).sum();
    }

    public int getWeightedAverage() {
        // média ponderada = ((nota*avalições) + ...)/soma de todas avaliações
        return this.ratings.isEmpty() ? 0 : this.ratings.values().stream().mapToInt(rating -> rating.getRatingAverage() * rating.getVotesCount()).sum() / getRatingsCount();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Market.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("imageUrl='"+ imageUrl +"'")
                .add("lastTimeWasBetter='"+ lastTimeWasBetter +"'")
                .toString();
    }
}
