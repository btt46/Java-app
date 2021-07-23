### Requirements
- javafx
- org.json

### Compile

```
$ export PATH_TO_FX=/javafx-sdk-11.0.2/lib

$ javac -classpath json.jar --module-path $PATH_TO_FX --add-modules javafx.controls  *.java 

$ java --module-path $PATH_TO_FX --add-modules javafx.controls App
```

### GUI
![](imgs/gui.png)
