/*
 * Copyright 2016 Karl Dahlgren
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.castlemock.web.basis.service.system;

import com.castlemock.core.basis.model.Service;
import com.castlemock.core.basis.model.ServiceResult;
import com.castlemock.core.basis.model.ServiceTask;
import com.castlemock.core.basis.model.system.service.dto.SystemInformationDto;
import com.castlemock.core.basis.service.system.input.GetSystemInformationInput;
import com.castlemock.core.basis.service.system.output.GetSystemInformationOutput;
import com.castlemock.web.basis.service.configuration.AbstractConfigurationGroupService;
import org.apache.catalina.util.ServerInfo;
import org.springframework.beans.factory.annotation.Value;

/**
 * The {@link GetSystemInformationService} is used to retrieve information about the system which
 * the application is deployed on.
 * @author Karl Dahlgren
 * @since 1.7
 */
@org.springframework.stereotype.Service
public class GetSystemInformationService extends AbstractConfigurationGroupService implements Service<GetSystemInformationInput, GetSystemInformationOutput> {

    @Value("${base.file.directory}")
    private String castleMockHomeDirectory;

    /**
     * The process message is responsible for processing an incoming serviceTask and generate
     * a response based on the incoming serviceTask input
     * @param serviceTask The serviceTask that will be processed by the service
     * @return A result based on the processed incoming serviceTask
     * @see ServiceTask
     * @see ServiceResult
     */
    @Override
    public ServiceResult<GetSystemInformationOutput> process(final ServiceTask<GetSystemInformationInput> serviceTask) {
        final SystemInformationDto systemInformation = new SystemInformationDto();
        systemInformation.setJavaVersion(System.getProperty("java.version"));
        systemInformation.setJavaVendor(System.getProperty("java.vendor"));
        systemInformation.setOperatingSystemName(System.getProperty("os.name"));
        systemInformation.setTomcatBuilt(ServerInfo.getServerBuilt());
        systemInformation.setTomcatInfo(ServerInfo.getServerInfo());
        systemInformation.setTomcatVersion(ServerInfo.getServerNumber());
        systemInformation.setAvailableProcessors(Runtime.getRuntime().availableProcessors());
        systemInformation.setTotalMemory(Runtime.getRuntime().totalMemory() / 1000000); // Megabytes
        systemInformation.setMaxMemory(Runtime.getRuntime().maxMemory() / 1000000); // Megabytes
        systemInformation.setFreeMemory(Runtime.getRuntime().freeMemory() / 1000000); // Megabytes
        systemInformation.setCastleMockHomeDirectory(this.castleMockHomeDirectory);
        final GetSystemInformationOutput output = new GetSystemInformationOutput(systemInformation);
        return createServiceResult(output);
    }
}
