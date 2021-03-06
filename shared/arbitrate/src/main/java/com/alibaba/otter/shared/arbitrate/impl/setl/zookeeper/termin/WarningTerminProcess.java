/*
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package com.alibaba.otter.shared.arbitrate.impl.setl.zookeeper.termin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.otter.shared.arbitrate.impl.alarm.AlarmClientService;
import com.alibaba.otter.shared.arbitrate.impl.config.ArbitrateConfigUtils;
import com.alibaba.otter.shared.arbitrate.model.TerminEventData;

/**
 * 回滚的终结信号处理
 * 
 * @author jianghang 2011-9-26 下午02:03:02
 * @version 4.0.0
 */
public class WarningTerminProcess implements TerminProcess {

    private static final Logger logger = LoggerFactory.getLogger(WarningTerminProcess.class);
    private AlarmClientService  alarmClientService;

    public boolean process(TerminEventData data) {
        logger.warn("nid:{}[{}:{}]",
                    new Object[] { ArbitrateConfigUtils.getCurrentNid(), data.getPipelineId(),
                            data.getCode() + ":" + data.getDesc() });
        alarmClientService.sendAlarm(ArbitrateConfigUtils.getCurrentNid(), data.getPipelineId(), data.getCode(),
                                     data.getDesc());
        return true;
    }

    // ============= setter / getter =============

    public void setAlarmClientService(AlarmClientService alarmClientService) {
        this.alarmClientService = alarmClientService;
    }
}
