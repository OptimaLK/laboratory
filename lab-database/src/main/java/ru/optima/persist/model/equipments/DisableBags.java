package ru.optima.persist.model.equipments;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import ru.optima.persist.model.User;

import javax.persistence.*;
import java.util.List;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "disable_bags")
public class DisableBags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinTable(name = "bag_user",
            joinColumns = @JoinColumn(name = "bag_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;
}
