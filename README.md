# HTTP Info Plugin
Simple plugin that responds with player count, current map and wave on an HTTP request.

## Building
First, make sure you have JDK 17 installed. Then, run the following commands:

* Windows: `gradlew jar`
* *nix/Mac OS: `./gradlew jar`

## Configuration
Edit `http_config.json` file in your `config/mods` directory to change port, example:
```json
{
  "port": 6500
}
```

### Troubleshooting

* If the terminal returns `Permission denied` or `Command not found`, run `chmod +x ./gradlew`.
