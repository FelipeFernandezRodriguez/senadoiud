package co.edu.iudigital.senadoiud.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum VoteType {

    A_FAVOR("A favor"),
    EN_CONTRA("En contra");

    final String name;

}
