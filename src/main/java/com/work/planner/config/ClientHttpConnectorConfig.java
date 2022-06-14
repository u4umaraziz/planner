/*
 * Copyright 2017-2021 Commencis Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Commencis Limited.
 * Any reproduction of this material must contain this notice.
 */

package com.work.planner.config;

import io.netty.handler.logging.LogLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

@Configuration
public class ClientHttpConnectorConfig {

    @Bean
    public ClientHttpConnector getClientConnector() {
        final HttpClient httpClient = HttpClient
            .create()
            .wiretap("reactor.netty.http.client.HttpClient", LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL);

        return new ReactorClientHttpConnector(httpClient);
    }
}
