package it.academy.firstStep.rolesEnum;

import jdk.swing.interop.SwingInterOpUtils;
import org.hibernate.event.spi.SaveOrUpdateEvent;

public enum Roles {
    ADMIN(1),
    USER(2),
    OPERATOR(5),
    TEST(6),
    SUPER_ROLE(8);


    Roles(int i) {
        value = i;
    }

    int value;

    public int getValue() {
        return value;
    }

    public int  detectId() {
        if (this == ADMIN) {
            return 1;
        } else if (this == USER) {
            return 2;
        } else if (this == OPERATOR) {
            return 5;
        } else if (this == TEST) {
            return 6;
        } else if (this == SUPER_ROLE) {
            return 8;
        }
        return 0;
    }
}
