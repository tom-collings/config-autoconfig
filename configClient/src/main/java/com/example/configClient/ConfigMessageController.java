package com.example.configClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tomcollings on 9/27/17.
 */


@RestController
@RefreshScope
public class ConfigMessageController {

    @Value("${hello.message:default}")
    private String helloMessage;

    @Value("${common.message:commondefault}")
    private String commonMessage;

    @Value("${very.common.message:verycommondefault}")
    private String veryCommonMessage;


    @RequestMapping("/hello")
    String getHello() {
        return this.helloMessage;
    }

    @RequestMapping("/common")
    String getCommon() {
        return this.commonMessage;
    }

    @RequestMapping("/verycommon")
    String getVeryCommon() { return this.veryCommonMessage; }

}
