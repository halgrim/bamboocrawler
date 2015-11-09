package pl.hybris.bamboo.core;

import pl.hybris.bamboo.core.interfaces.CustomDriver;

/**
 * Created by i323728 on 11/9/15.
 */
//@Configuration
public class DIConfiguration {

    //@Bean
    public CustomDriver customDriver(){
        return new SetUpChromeDriver();
    }
}
