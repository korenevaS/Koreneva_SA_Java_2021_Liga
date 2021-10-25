package com.github.korenevaS.queue.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Calendar;
import java.util.UUID;

@Entity
@Table(name = "`receipt`")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "start_of_reception")
    private Calendar startOfReception;

    @Column(name = "creation_date")
    private Calendar creationDate;

    @Column(name = "confirmation")
    private Boolean confirmation;

    @Column(name = "was_used")
    private Boolean wasUsed;

    @Column(name = "link_waiting_for_confirmation")
    private UUID linkWaitingForConfirmation;
}
