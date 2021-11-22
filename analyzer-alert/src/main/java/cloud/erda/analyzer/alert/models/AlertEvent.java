// Copyright (c) 2021 Terminus, Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package cloud.erda.analyzer.alert.models;

import cloud.erda.analyzer.common.models.MetricEvent;
import lombok.Data;

/**
 * @author: liuhaoyang
 * @create: 2020-01-05 16:24
 **/
@Data
public class AlertEvent {

    private MetricEvent metricEvent;

    private String alertGroup;

    private String alertId;

    private String expressionId;

    private String alertType;

    private String alertIndex;

    private AlertTrigger trigger;

    private AlertLevel level;

    private AlertNotify alertNotify;

    private AlertNotifyTemplate alertNotifyTemplate;

    public AlertEvent copy() {
        AlertEvent alertEvent = new AlertEvent();
        alertEvent.setMetricEvent(metricEvent);
        alertEvent.setAlertGroup(alertGroup);
        alertEvent.setAlertId(alertId);
        alertEvent.setExpressionId(expressionId);
        alertEvent.setAlertType(alertType);
        alertEvent.setAlertIndex(alertIndex);
        alertEvent.setTrigger(trigger);
        alertEvent.setLevel(level);
        alertEvent.setAlertNotify(alertNotify);
        alertEvent.setAlertNotifyTemplate(alertNotifyTemplate);
        return alertEvent;
    }
}
