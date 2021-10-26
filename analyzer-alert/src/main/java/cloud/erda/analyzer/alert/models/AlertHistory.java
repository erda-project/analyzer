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

import cloud.erda.analyzer.common.models.Event;
import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import cloud.erda.analyzer.common.utils.annotations.ClusteringOrder;
import lombok.Data;

/**
 * @author randomnil
 */
@Data
@Table(keyspace = "spot_prod", name = "alert_history")
public class AlertHistory {

    @PartitionKey
    @Column(name = "group_id")
    private String groupId;

    @ClusteringColumn
    @ClusteringOrder(ClusteringOrder.Order.DESC)
    @Column(name = "timestamp")
    private Long timestamp;

    @Column(name = "alert_state")
    private String alertState;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "display_url")
    private String displayUrl;
}
