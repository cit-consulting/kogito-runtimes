/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.kogito.testcontainers;

import org.kie.kogito.resources.TestResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.utility.DockerImageName;

/**
 * Kafka Container for Kogito examples.
 */
public class KogitoKafkaContainer extends KafkaContainer implements TestResource {

    public static final String NAME = "kafka";
    public static final String KAFKA_PROPERTY = "container.image." + NAME;

    private static final Logger LOGGER = LoggerFactory.getLogger(KogitoKafkaContainer.class);

    public KogitoKafkaContainer() {
        super(DockerImageName.parse(System.getProperty(KAFKA_PROPERTY)));
        withLogConsumer(new Slf4jLogConsumer(LOGGER));
    }

    @Override
    public void start() {
        super.start();
        LOGGER.info("Kafka servers: {}", getBootstrapServers());
    }

    @Override
    public int getMappedPort() {
        return getMappedPort(KAFKA_PORT);
    }

    @Override
    public String getResourceName() {
        return NAME;
    }
}
