/*
 * Copyright (c) 2021 Terminus, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cloud.erda.analyzer.tracing.functions;

import cloud.erda.analyzer.common.models.MetricEvent;
import org.apache.flink.api.java.functions.KeySelector;

import java.util.Map;

/**
 * @author liuhaoyang
 * @date 2021/9/22 02:00
 */
public class MetricTagGroupFunction implements KeySelector<MetricEvent, String> {
    @Override
    public String getKey(MetricEvent metricEvent) throws Exception {

        StringBuilder sb = new StringBuilder();
        sb.append(metricEvent.getName());
        for (Map.Entry<String, String> tag : metricEvent.getTags().entrySet()) {
            sb.append("_").append(tag.getKey()).append("_").append(tag.getValue());
        }
        return sb.toString();
    }
}
