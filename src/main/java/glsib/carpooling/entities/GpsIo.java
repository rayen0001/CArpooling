package glsib.carpooling.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GpsIo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String imei;
    LocalDateTime timestamp;
    String command;
    String response;
    String status;
}
