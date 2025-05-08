package com.user.microservices.project.UserService.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Table(name = "user_ratings")
public class Rating {

    @Id
    @Column(name = "ID")
    private String ratingId;

    @Column(name = "USERID")
    private String userId;

    @Column(name = "HOTELID")
    private String hotelId;

    @Column(name = "RATING")
    private int rating;

    @Column(name = "FEEDBACK")
    private String feedback;

    private Hotel hotel;

}
