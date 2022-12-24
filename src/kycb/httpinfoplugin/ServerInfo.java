package kycb.httpinfoplugin;

public class ServerInfo {
    private int players = 0;
    private int wave = 0;
    private String map = "";

    public ServerInfo(int players, int wave, String map) {
        this.players = players;
        this.wave = wave;
        this.map = map;
    }
}
