package org.aaroniz.sebgu.data;

public enum GuildId {
    TESTING(1107286128651939900L),
    SEBGU(1018455830175170650L);

    private long id;

    private GuildId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
