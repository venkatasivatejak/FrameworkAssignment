package utils;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "file:src/test/resources/application.properties"
})
public interface ApplicationPropertyConfig extends Config {

    @Key("browser")
    String browser();

    @Key("cp1_url")
    String cp1Url();

    @Key("dp1_url")
    String dp1Url();

    @Key("dp2_url")
    String dp2Url();
}