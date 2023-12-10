package at.spengergasse.efees.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.List;

@Document("emergencies")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@ToString
public class Emergency {
    @MongoId(FieldType.OBJECT_ID)
    protected ObjectId id;
    @Setter
    @Builder.Default
    private LocalDateTime startTime = LocalDateTime.now();
    @Setter
    private LocalDateTime endTime;
    @Setter
    @Builder.Default
    private String notice = "";
    @Setter
    private boolean finished;
    // @DBRef
    private List<Person> persons;
}
