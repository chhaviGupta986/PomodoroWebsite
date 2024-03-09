package com.ApiGateWay.GateWay.Configuration;

import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class Netty implements WebServerFactoryCustomizer<NettyReactiveWebServerFactory> {

        @Override
        public void customize(NettyReactiveWebServerFactory serverFactory) {
            serverFactory.setPort(8088);
        }
}
