package pl.neverendingcode.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
public class Post implements Serializable {
    private int userId;
    private int id;
    private String title;
    private String body;
}
