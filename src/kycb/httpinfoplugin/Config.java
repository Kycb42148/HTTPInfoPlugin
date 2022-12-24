package kycb.httpinfoplugin;

import arc.util.Log;

public class Config {
    private int port = 6500;

    public Config(int port) {
        if (port > 0 && port < 65535)
            this.port = port;
        else Log.info("Port value is out of range");
    }

    public Config() {
        this(6500);
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
