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
package org.kie.kogito.events.knative.ce.extensions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.kie.kogito.event.CloudEventExtensionConstants;

import io.cloudevents.CloudEventExtensions;
import io.cloudevents.Extension;

// The size of this extension could be reevaluated since we could make use of `type`, `source` and `subject` for processId, referenceId and instanceState

/**
 * CloudEvent extension for Kogito Process.
 */
public class KogitoProcessExtension implements Extension {

    static final Set<String> ALL_KEYS = new HashSet<>(
            Arrays.asList(
                    CloudEventExtensionConstants.PROCESS_INSTANCE_ID,
                    CloudEventExtensionConstants.PROCESS_ROOT_PROCESS_INSTANCE_ID,
                    CloudEventExtensionConstants.PROCESS_ID,
                    CloudEventExtensionConstants.PROCESS_ROOT_PROCESS_ID,
                    CloudEventExtensionConstants.ADDONS,
                    CloudEventExtensionConstants.PROCESS_PARENT_PROCESS_INSTANCE_ID,
                    CloudEventExtensionConstants.PROCESS_INSTANCE_STATE,
                    CloudEventExtensionConstants.PROCESS_REFERENCE_ID,
                    CloudEventExtensionConstants.PROCESS_START_FROM_NODE));

    private final Map<String, Object> innerValues;
    private String kogitoProcessInstanceId;
    private String kogitoRootProcessInstanceId;
    private String kogitoProcessId;
    private String kogitoRootProcessId;
    private String kogitoAddons;
    private String kogitoParentProcessinstanceId;
    private String kogitoProcessInstanceState;
    private String kogitoReferenceId;
    private String kogitoStartFromNode;

    public KogitoProcessExtension() {
        this.innerValues = new HashMap<>();
    }

    public String getKogitoProcessInstanceId() {
        return kogitoProcessInstanceId;
    }

    public void setKogitoProcessInstanceId(String kogitoProcessInstanceId) {
        this.kogitoProcessInstanceId = kogitoProcessInstanceId;
        this.innerValues.put(CloudEventExtensionConstants.PROCESS_INSTANCE_ID, this.kogitoProcessInstanceId);
    }

    public String getKogitoRootProcessInstanceId() {
        return kogitoRootProcessInstanceId;
    }

    public void setKogitoRootProcessInstanceId(String kogitoRootProcessInstanceId) {
        this.kogitoRootProcessInstanceId = kogitoRootProcessInstanceId;
        this.innerValues.put(CloudEventExtensionConstants.PROCESS_ROOT_PROCESS_INSTANCE_ID, this.kogitoRootProcessInstanceId);
    }

    public String getKogitoProcessId() {
        return kogitoProcessId;
    }

    public void setKogitoProcessId(String kogitoProcessId) {
        this.kogitoProcessId = kogitoProcessId;
        this.innerValues.put(CloudEventExtensionConstants.PROCESS_ID, this.kogitoProcessId);
    }

    public String getKogitoRootProcessId() {
        return kogitoRootProcessId;
    }

    public void setKogitoRootProcessId(String kogitoRootProcessId) {
        this.kogitoRootProcessId = kogitoRootProcessId;
        this.innerValues.put(CloudEventExtensionConstants.PROCESS_ROOT_PROCESS_ID, this.kogitoRootProcessId);
    }

    public String getKogitoAddons() {
        return kogitoAddons;
    }

    public void setKogitoAddons(String kogitoAddons) {
        this.kogitoAddons = kogitoAddons;
        this.innerValues.put(CloudEventExtensionConstants.ADDONS, this.kogitoAddons);
    }

    public String getKogitoParentProcessinstanceId() {
        return kogitoParentProcessinstanceId;
    }

    public void setKogitoParentProcessinstanceId(String kogitoParentProcessinstanceId) {
        this.kogitoParentProcessinstanceId = kogitoParentProcessinstanceId;
        this.innerValues.put(CloudEventExtensionConstants.PROCESS_PARENT_PROCESS_INSTANCE_ID, this.kogitoParentProcessinstanceId);
    }

    public String getKogitoProcessInstanceState() {
        return kogitoProcessInstanceState;
    }

    public void setKogitoProcessInstanceState(String kogitoProcessInstanceState) {
        this.kogitoProcessInstanceState = kogitoProcessInstanceState;
        this.innerValues.put(CloudEventExtensionConstants.PROCESS_INSTANCE_STATE, this.kogitoProcessInstanceState);
    }

    public String getKogitoReferenceId() {
        return kogitoReferenceId;
    }

    public void setKogitoReferenceId(String kogitoReferenceId) {
        this.kogitoReferenceId = kogitoReferenceId;
        this.innerValues.put(CloudEventExtensionConstants.PROCESS_REFERENCE_ID, this.kogitoReferenceId);
    }

    public String getKogitoStartFromNode() {
        return kogitoStartFromNode;
    }

    public void setKogitoStartFromNode(String kogitoStartFromNode) {
        this.kogitoStartFromNode = kogitoStartFromNode;
        this.innerValues.put(CloudEventExtensionConstants.PROCESS_START_FROM_NODE, this.kogitoStartFromNode);
    }

    @Override
    public void readFrom(CloudEventExtensions extensions) {
        this.setKogitoAddons(getExtension(extensions, CloudEventExtensionConstants.ADDONS));
        this.setKogitoParentProcessinstanceId(getExtension(extensions, CloudEventExtensionConstants.PROCESS_PARENT_PROCESS_INSTANCE_ID));
        this.setKogitoProcessId(getExtension(extensions, CloudEventExtensionConstants.PROCESS_ID));
        this.setKogitoProcessInstanceId(getExtension(extensions, CloudEventExtensionConstants.PROCESS_INSTANCE_ID));
        this.setKogitoProcessInstanceState(getExtension(extensions, CloudEventExtensionConstants.PROCESS_INSTANCE_STATE));
        this.setKogitoReferenceId(getExtension(extensions, CloudEventExtensionConstants.PROCESS_REFERENCE_ID));
        this.setKogitoRootProcessId(getExtension(extensions, CloudEventExtensionConstants.PROCESS_ROOT_PROCESS_ID));
        this.setKogitoRootProcessInstanceId(getExtension(extensions, CloudEventExtensionConstants.PROCESS_ROOT_PROCESS_INSTANCE_ID));
        this.setKogitoStartFromNode(getExtension(extensions, CloudEventExtensionConstants.PROCESS_START_FROM_NODE));
    }

    private String getExtension(CloudEventExtensions extensions, String key) {
        if (extensions.getExtension(key) == null) {
            return "";
        }
        return extensions.getExtension(key).toString();
    }

    @Override
    public Object getValue(String key) {
        return this.innerValues.get(key);
    }

    @Override
    public Set<String> getKeys() {
        return ALL_KEYS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KogitoProcessExtension that = (KogitoProcessExtension) o;
        return Objects.equals(kogitoProcessInstanceId, that.kogitoProcessInstanceId) &&
                Objects.equals(kogitoRootProcessInstanceId, that.kogitoRootProcessInstanceId) &&
                Objects.equals(kogitoProcessId, that.kogitoProcessId) &&
                Objects.equals(kogitoRootProcessId, that.kogitoRootProcessId) &&
                Objects.equals(kogitoAddons, that.kogitoAddons) &&
                Objects.equals(kogitoParentProcessinstanceId, that.kogitoParentProcessinstanceId) &&
                Objects.equals(kogitoProcessInstanceState, that.kogitoProcessInstanceState) &&
                Objects.equals(kogitoReferenceId, that.kogitoReferenceId) &&
                Objects.equals(kogitoStartFromNode, that.kogitoStartFromNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kogitoProcessInstanceId, kogitoRootProcessInstanceId, kogitoProcessId, kogitoRootProcessId, kogitoAddons, kogitoParentProcessinstanceId, kogitoProcessInstanceState,
                kogitoReferenceId, kogitoStartFromNode);
    }

    @Override
    public String toString() {
        return "KogitoProcessExtension{" +
                "procInstanceId='" + kogitoProcessInstanceId + '\'' +
                ", processId='" + kogitoProcessId + '\'' +
                ", referenceId='" + kogitoReferenceId + '\'' +
                '}';
    }
}
