package kz.cleangov.cleangov.domain;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "investigationsTable")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Investigations {
    @Id
    @UuidGenerator
    @Column(name = "id_investigation", unique = true, updatable = false)
    private String id;
    private String name;
    private String description;
    private String level;
}
