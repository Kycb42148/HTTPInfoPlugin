package kycb.httpinfoplugin;

import arc.util.*;
import arc.files.Fi;
import arc.util.serialization.*;
import com.sun.net.httpserver.HttpServer;
import mindustry.Vars;
import mindustry.gen.Groups;
import mindustry.mod.Plugin;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class HttpInfoPlugin extends Plugin {

    @Override
    public void init() {
        Json j = new Json();
        j.setOutputType(JsonWriter.OutputType.json);

        Fi configFile = Fi.get("config/mods/http_config.json");
        Config config;
        if (configFile.exists()) {
            config = j.fromJson(Config.class, configFile.readString());
        } else {
            configFile.writeString(j.toJson(config = new Config()));
        }

        try {
            HttpServer httpServer = HttpServer.create();
            httpServer.bind(new InetSocketAddress("localhost", config.getPort()), 0);
            httpServer.createContext("/", httpExchange -> {
                var data = j.toJson(new ServerInfo(Groups.player.size(), Vars.state.wave, Vars.state.map.name())).getBytes(StandardCharsets.UTF_8);
                httpExchange.sendResponseHeaders(200, data.length);
                var out = httpExchange.getResponseBody();
                out.write(data);
            });

            Log.info("Started HTTP server at port " + config.getPort());
            httpServer.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void registerClientCommands(CommandHandler handler) {
    }

    @Override
    public void registerServerCommands(CommandHandler handler) {
    }
}
