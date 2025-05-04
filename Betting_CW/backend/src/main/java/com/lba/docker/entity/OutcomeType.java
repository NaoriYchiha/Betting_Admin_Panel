package com.lba.docker.entity;

import java.io.Serializable;

public enum OutcomeType implements Serializable {
    TEAM_1_WIN,  // Победа первой команды
    TEAM_2_WIN,  // Победа второй команды
    DRAW         // Ничья (если применимо)
}
